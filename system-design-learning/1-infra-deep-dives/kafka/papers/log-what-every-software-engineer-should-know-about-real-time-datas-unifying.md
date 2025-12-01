
# Source  
Original concepts summarized from:  
https://engineering.linkedin.com/distributed-systems/log-what-every-software-engineer-should-know-about-real-time-datas-unifying

---

# State Machine Replication Principle (Explained Simply)

**If two deterministic machines start in the same state,  
and receive the same inputs in the same order,  
they will end in the same state.**

This principle is the foundation of:
- Database replication  
- Raft  
- Paxos  
- Kafka  
- Event sourcing  
- Distributed consistency models  

---

## Determinism & Why It Matters

A system is deterministic if:
- Same input → same output  
- Independent of timing or randomness  
- No reliance on clocks, thread ordering, or external nondeterministic behavior  

Non-deterministic operations break replication because two nodes may produce different results.

---

## Why Logs are Needed

Replicas run in:
- Different servers  
- Different racks  
- Different regions / time zones  

Without a log:
- Operations arrive in different orders  
- A replica may process **A → B**, another does **B → A**  
- State diverges → inconsistency  

With a log:
- All nodes read entries in order: 1, 2, 3, …  
- Order is identical everywhere  
- Timing differences do not matter  
- State stays consistent  

✔ **The log becomes the global truth.**

---

## Logs as Distributed Clocks

> "The timestamps that index the log now act as the clock for the state."

Meaning:

Instead of a replica saying:

> "My state is X"

It can say:

> "I have replayed the log up to offset **2000**."

If you know:
- the **log**
- the **offset** processed

👉 You know the **entire state** of the replica.

This makes the system:
- Replayable  
- Auditable  
- Fault-tolerant  
- Easy to rebuild  

---

# Replication Models in Distributed Systems

Distributed systems literature distinguishes **two major replication approaches**:

---

## 1. Active-Active (State Machine Model)

This is the pure state machine replication model.

### How it works:
- All replicas receive **the same log of inputs**  
- Each replica independently executes every operation  
- Because they are deterministic → states converge  

### Example
Operations logged:
```
+1
*2
+3
```

Each replica performs:
```
Initial value = 0

+1 → 1
*2 → 2
+3 → 5
```

Every replica ends with value **5**.

### Pros
- Reads/writes can happen on all replicas  
- No single leader bottleneck  
- High availability  

### Cons
- Requires all replicas to be deterministic  
- More CPU usage (every replica does the full computation)  
- More complex to implement 

## Common Misconceptions About Active-Active

- **Active–Active ≠ Multiple Writers:**
   Active–active does **not** mean each replica accepts writes independently. All replicas apply the same operations, but the log of operations comes from a single sequencer/leader.

- **Who Appends to the Log?**
   The "writer" is the entity that appends to the log—not the replicas themselves. Replicas are “active” in executing operations, but not in independently deciding or ordering writes.

- **Examples:**
   - Raft: Only the leader appends to the log; followers execute operations.
   - Paxos: A coordinator/sequencer chooses the order.
   - Kafka: Only the partition leader appends to the log.
   - Databases: Only the primary writes the WAL.

- **Why “Active–Active”?**
   All replicas actively execute operations, maintain full state, can serve reads, and process the same operations. Replicas stay in sync by independently applying the ordered log. Not all replicas accept writes directly; there is always one authority for ordering.

- **Who Determines the Order?**
   - Single leader (Raft, Kafka, PostgreSQL WAL)
   - Rotating leader (Paxos)
   - Global timestamp allocator (Spanner)
   - Log server (Kafka partition leader)

---

## 2. Primary–Backup (Active–Passive Model)

Also called:
- leader–follower  
- master–slave  

### How it works:
- **One leader** handles all writes  
- Leader executes the operations  
- Leader logs **state changes**, not the original requests  
- Followers replay only the resulting changes  

### Example
Operations:
```
+1
*2
+3
```

Leader executes:
```
0 + 1 = 1
1 * 2 = 2
2 + 3 = 5
```

Leader logs results:
```
1
2
5
```

Followers apply these final states.

### Pros
- Followers do less computation  
- Simpler implementation  
- Good performance  

### Cons
- Leader is a single point of bottleneck  
- Failover is required on leader crash  
- Followers cannot process writes  

---

## Why Ordering Matters (Toy Example)

Service state: a single number starting at **0**

Operations:
1. `+1`
2. `*2`

Correct order:
```
(0 + 1) = 1
(1 * 2) = 2
```

Reordered:
```
(0 * 2) = 0
(0 + 1) = 1
```

Results diverge:
- In-order = **2**
- Out-of-order = **1**

This is why **logs enforce strict ordering**.

Even in the primary-backup model:
- Leader must apply operations in order  
- Followers must apply *state changes* in the same order  

Without ordering → replicas diverge → system breaks.

---

## Active-Active vs Active-Passive (Summary Table)

| Feature | Active–Active (State Machine Model) | Active–Passive (Primary–Backup) |
|--------|-------------------------------------|---------------------------------|
| What is logged? | Inputs / operations | Outputs / state changes |
| Who executes operations? | Every replica | Only the leader |
| Determinism required? | Strongly yes | Less strict |
| Performance | More CPU cost | More efficient per replica |
| Consistency | Stronger | Depends on leader logs |
| Write Scalability | Limited | Limited (single leader) |
| Failure handling | No single leader | Leader failover needed |

---

# Distributed Log as a Model of Consensus

The distributed log can be viewed as the **data structure that models the problem of consensus itself**.

A log is essentially a sequence of decisions:

```
What is the next value to append?
What is the next operation to commit?
What is the next state transition?
```

Consensus algorithms exist *primarily* to answer this question reliably across multiple machines.

---

## Logs in Consensus Algorithms

Consensus is about making multiple machines agree on:
- What value to choose
- What operation to perform next
- What order to apply changes

**The log is the perfect data structure for this** because it captures a sequence of agreed-upon decisions.

### Paxos

**Basic Paxos** is designed to reach consensus on a **single value**.

**The Problem:**
- Real systems don't just need to agree on one thing
- They need to agree on a **sequence** of things (operations, commands, transactions)
- This requires agreeing on many values over time

**How Paxos Works (Simplified):**

1. **Proposer** suggests a value
2. **Acceptors** (multiple nodes) vote on whether to accept it
3. If a majority accepts → consensus is reached
4. The agreed value is chosen

**Roles:**
- **Proposer**: Suggests values
- **Acceptor**: Votes on proposals
- **Learner**: Learns the chosen value

**Phases:**
1. **Prepare phase**: Proposer asks acceptors if they'll consider a proposal
2. **Accept phase**: If majority agrees, proposer sends the actual value
3. **Learn phase**: Once accepted by majority, learners are notified

**Key Insight:**
- Paxos guarantees **safety** (never choose conflicting values)
- Even with network partitions and node failures

---

### Multi-Paxos

Since basic Paxos only handles **one decision**, real systems use **Multi-Paxos** to handle a **sequence** of decisions.

**How Multi-Paxos extends Paxos:**
- The log is modeled as a **series of slots**
- Each slot = one instance of Paxos consensus
- Slot 0: Paxos instance #0
- Slot 1: Paxos instance #1
- Slot 2: Paxos instance #2
- And so on...

**Multi-Paxos Optimization:**
- Instead of running full Paxos for every slot, elect a **leader**
- Leader can append to the log without the prepare phase
- Much faster than basic Paxos
- Only re-run prepare phase when leader changes

**Why "squint to see the log"?**
- Basic Paxos paper doesn't explicitly describe a log
- But implementations naturally build one
- Multi-Paxos makes the log explicit

**Real-World Usage:**
- Google Chubby (lock service)
- Google Spanner (globally distributed database)
- Many distributed databases use Paxos variants

---

## Protocols Where Logs Are First-Class Citizens

Some consensus protocols explicitly make the **log** their primary abstraction from the start:

### ✔ RAFT

**Design Philosophy:**
- Designed to be **understandable** (unlike Paxos)
- Log is the **central concept**
- Everything revolves around replicating the log

**How Raft Works:**

1. **Leader Election**
   - Nodes start as followers
   - If no heartbeat from leader → become candidate
   - Request votes from other nodes
   - Node with majority votes becomes leader

2. **Log Replication**
   - Leader receives client requests
   - Appends to its own log
   - Sends entries to followers
   - Waits for majority acknowledgment
   - Commits the entry
   - Applies to state machine

3. **Safety Rules**
   - Leader never overwrites or deletes entries
   - If two logs contain an entry with same index & term → identical up to that point
   - If entry is committed → all future leaders will have it

**Key Properties:**
- **Term numbers** ensure correct leader selection
- **Log matching property** prevents divergence
- **Commitment rules** ensure durability

**Real-World Usage:**
- etcd (Kubernetes' metadata store)
- Consul (service discovery)
- CockroachDB (distributed SQL database)

---

### ✔ ZAB (ZooKeeper Atomic Broadcast)

Used by **Apache ZooKeeper**.

**Purpose:**
- Maintain a totally ordered broadcast
- Ensure all nodes see updates in the same order
- Provide strong consistency

**How ZAB Works:**

1. **Leader Election Phase**
   - Nodes elect a leader
   - Leader receives all write requests

2. **Atomic Broadcast Phase**
   - Leader assigns a **zxid** (ZooKeeper transaction ID) to each update
   - zxid = (epoch, counter)
   - Leader broadcasts to followers
   - Followers acknowledge
   - Leader commits when majority acknowledges

3. **Recovery Phase**
   - If leader fails → new election
   - New leader synchronizes state with followers
   - Ensures no committed updates are lost

**ZAB vs Paxos:**
- ZAB optimized for primary-backup model
- Paxos more general-purpose
- ZAB simpler for total order broadcast

**Real-World Usage:**
- ZooKeeper (coordination service)
- HBase (uses ZooKeeper for coordination)
- Kafka (used ZooKeeper before KRaft)

---

### ✔ Viewstamped Replication

One of the **earliest** consensus protocols (predates Paxos paper).

**Key Concepts:**
- Similar to Raft in many ways
- Uses **view numbers** (like Raft's terms)
- Primary-backup replication with leader election

**How It Works:**
- Primary receives client requests
- Assigns sequence numbers
- Sends to backups
- Backups acknowledge
- Primary commits and replies to client

**Historical Importance:**
- Influenced Raft's design
- Shows log-based replication isn't new
- Demonstrates convergent evolution of ideas

---

These protocols treat the log as the **central artifact** rather than an emergent structure.

**Common Patterns Across Raft, ZAB, Viewstamped Replication:**
1. Leader-based approach
2. Sequential log entries
3. Majority quorum for commits
4. Recovery through log replay
5. Total ordering of operations

---

## Why the Log Fits Consensus Perfectly

Consensus requires:

1. Agreement → All nodes must see the same value  
2. Total order → All nodes must apply operations in the same order  
3. Durability → Once agreed, a value cannot be undone  

A distributed log satisfies all three:

- **Each log entry = one consensus decision**  
- **Log index = ordering**  
- **Appending only at the end = immutability**  

Thus:

> A distributed log *is* a sequence of consensus decisions.

This is why the distributed log appears everywhere:
- Raft logs  
- ZAB logs  
- Spanner’s TrueTime‑ordered logs  
- Postgres WAL + replication  
- MySQL binlog  
- Kafka commit logs  
- Event sourcing  

The log unifies different areas of distributed systems into a single abstraction.

---


# State Machine Replication, Logs & Replication Models

## State Machine Replication Principle (Explained Simply)

**If two deterministic machines start in the same state,  
and receive the same inputs in the same order,  
they will end in the same state.**

This principle is the foundation of:
- Database replication  
- Raft  
- Paxos  
- Kafka  
- Event sourcing  
- Distributed consistency models  

---

## Determinism & Why It Matters

A system is deterministic if:
- Same input → same output  
- Independent of timing or randomness  
- No reliance on clocks, thread ordering, or external nondeterministic behavior  

Non-deterministic operations break replication because two nodes may produce different results.

---

## Why Logs are Needed

Replicas run in:
- Different servers  
- Different racks  
- Different regions / time zones  

Without a log:
- Operations arrive in different orders  
- A replica may process **A → B**, another does **B → A**  
- State diverges → inconsistency  

With a log:
- All nodes read entries in order: 1, 2, 3, …  
- Order is identical everywhere  
- Timing differences do not matter  
- State stays consistent  

✔ **The log becomes the global truth.**

---

## Logs as Distributed Clocks

The article says:

> “The timestamps that index the log now act as the clock for the state.”

Meaning:

Instead of a replica saying:

> “My state is X”

It can say:

> “I have replayed the log up to offset **2000**.”

If you know:
- the **log**
- the **offset** processed

👉 You know the **entire state** of the replica.

This makes the system:
- Replayable  
- Auditable  
- Fault-tolerant  
- Easy to rebuild  

---

# Replication Models in Distributed Systems

Distributed systems literature distinguishes **two major replication approaches**:

---

# 1. Active-Active (State Machine Model)

This is the pure state machine replication model.

### How it works:
- All replicas receive **the same log of inputs**  
- Each replica independently executes every operation  
- Because they are deterministic → states converge  

### Example
Operations logged:
```
+1
*2
+3
```

Each replica performs:
```
Initial value = 0

+1 → 1
*2 → 2
+3 → 5
```

Every replica ends with value **5**.

### Pros
- Reads/writes can happen on all replicas  
- No single leader bottleneck  
- High availability  

### Cons
- Requires all replicas to be deterministic  
- More CPU usage (every replica does the full computation)  
- More complex to implement  

---

# 2. Primary–Backup (Active–Passive Model)

Also called:
- leader–follower  
- master–slave  

### How it works:
- **One leader** handles all writes  
- Leader executes the operations  
- Leader logs **state changes**, not the original requests  
- Followers replay only the resulting changes  

### Example
Operations:
```
+1
*2
+3
```

Leader executes:
```
0 + 1 = 1
1 * 2 = 2
2 + 3 = 5
```

Leader logs results:
```
1
2
5
```

Followers apply these final states.

### Pros
- Followers do less computation  
- Simpler implementation  
- Good performance  

### Cons
- Leader is a single point of bottleneck  
- Failover is required on leader crash  
- Followers cannot process writes  

---

# Why Ordering Matters (Toy Example)

Service state: a single number starting at **0**

Operations:
1. `+1`
2. `*2`

Correct order:
```
(0 + 1) = 1
(1 * 2) = 2
```

Reordered:
```
(0 * 2) = 0
(0 + 1) = 1
```

Results diverge:
- In-order = **2**
- Out-of-order = **1**

This is why **logs enforce strict ordering**.

Even in the primary-backup model:
- Leader must apply operations in order  
- Followers must apply *state changes* in the same order  

Without ordering → replicas diverge → system breaks.

---

# Active-Active vs Active-Passive (Summary Table)

| Feature | Active–Active (State Machine Model) | Active–Passive (Primary–Backup) |
|--------|-------------------------------------|---------------------------------|
| What is logged? | Inputs / operations | Outputs / state changes |
| Who executes operations? | Every replica | Only the leader |
| Determinism required? | Strongly yes | Less strict |
| Performance | More CPU cost | More efficient per replica |
| Consistency | Stronger | Depends on leader logs |
| Write Scalability | Limited | Limited (single leader) |
| Failure handling | No single leader | Leader failover needed |

---

# Conclusion

State machine replication + logs form the backbone of distributed systems.

✔ If replicas get the same ordered log → states stay identical  
✔ Logs remove nondeterminism  
✔ Logs act as distributed clocks  
✔ Active-active logs **inputs**  
✔ Primary-backup logs **state changes**  

This idea underlies:
- Kafka  
- Postgres WAL  
- MySQL Binlog  
- Raft  
- Paxos  
- Event sourcing architectures  

This principle is one of the most powerful abstractions in distributed systems.

