# Kafka Overview

Apache Kafka is a **distributed, log-based message queue** and **streaming platform** used for real-time data pipelines and event-driven architectures.

---

## What Does Log-Based Mean?

Kafka stores messages in an **append-only, immutable log**. Messages are written sequentially to disk, making Kafka:

- Extremely fast due to sequential disk writes  
- Durable because logs are persisted  
- Replayable (consumers can re-read messages anytime)  
- Scalable due to partitioned logs  

---

## Why Kafka Is Called a Message Queue

Kafka behaves like a message queue because:

- Producers send messages to topics  
- Consumers read messages from topics  
- It supports consumer groups & partition rebalancing  
- It ensures strict ordering **within a partition**  

But unlike traditional queues such as **RabbitMQ or SQS**, Kafka **does NOT remove messages after consumption**.  
Instead, messages are retained based on:

- **Time-based retention**  
- **Size-based retention**  

This makes Kafka ideal for **replay, auditing, and event sourcing**.

---

## Why Kafka Is Called a Stream Processing Platform

Kafka is also considered a streaming system because:

- Data flows through Kafka as a continuous stream of ordered records  
- Consumers can process events indefinitely  
- Kafka Streams API enables real-time transformations, joins, and aggregations  
- Stream processing is built on top of perpetual logs  

Thus, Kafka = **Message Queue + Distributed Commit Log + Stream Processing Engine**.

---

## Kafka vs Traditional Message Queues

| Feature              | Kafka                              | SQS / RabbitMQ                          |
|---------------------|-------------------------------------|-----------------------------------------|
| Storage Model       | Log-based, persistent               | Mostly memory-based (RabbitMQ), distributed queue (SQS) |
| Message Removal     | Retained after consume              | Removed after consume                   |
| Replayability       | Full replay anytime                 | No replay                               |
| Throughput          | Very high                           | Moderate                                |
| Scalability         | Horizontal via partitions           | Limited by node capacity                |
| Ordering Guarantee  | Per partition                       | Per queue / no partitioning             |

Traditional MQs focus on **low latency**.  
Kafka focuses on **throughput, durability, replay, and partitioned scaling**.

---

## Push vs Pull: How Kafka Delivers Messages

Kafka is a **pull-based** system.

Consumers pull messages like:
```
“Give me the next batch of 10 messages.”
```

Advantages:

- Consumer controls rate — natural backpressure  
- Simpler Kafka broker design  
- Consumers decide their own batch size and offset progression  

---

## How Kafka Assigns Messages to Partitions

Kafka uses two strategies:

### 1. **When Key Is Present**
- Kafka hashes the key  
- Message goes to a **deterministic partition** based on hash  
- Guarantees ordering per key  

### 2. **When No Key Is Present**
- Kafka uses **round-robin** assignment  
- Balances load across partitions  
- Ordering is **not guaranteed**

---

## Handling Hot Partitions

A partition becomes *hot* when too many messages are routed to it.

Example: **BookMyShow** — many users booking the same show → same key → same partition becomes hot.

Solutions:

### 1. **Remove the Key**
Useful when ordering does NOT matter.

### 2. **Composite Keys**
Example:  
```
eventId + userId
```
Benefits:
- Spreads load  
- Preserves ordering *per user*  

### 3. **Key Sharding (EventID-N)**
Example:
```
eventId-1
eventId-2
...
eventId-10
```
This distributes traffic across 10 partitions.

---

## Consumer Groups & Partition Assignment

### Case 1: More Consumers Than Partitions  
**4 consumers, 3 partitions**  
- Only 3 consumers receive data  
- The **4th consumer stays idle**  

### Case 2: More Partitions Than Consumers  
**4 partitions, 3 consumers**  
One consumer handles **two partitions**, e.g.:

- C1 → P0  
- C2 → P1  
- C3 → P2, P3  

Kafka ensures:  
- One partition → one consumer **within the same group**  

### Can Two Consumer Groups Read the Same Topic?

✔ **Yes.**

- Each group maintains its **own offsets**  
- Reads data **independently**  
- Perfect for microservices  

This is why Kafka works like a **pub-sub system**.

---

## Producer & Consumer Retries

---

## Producer Retries

Retries occur due to:

- Broker downtime  
- Leader election  
- Network failures  
- Partition becoming temporarily unavailable  

Producer settings:

- `retries`: number of retries  
- `retry.backoff.ms`: delay between retries  
- **Exponential backoff**: increasing retry intervals  
- `idempotence=true`: ensures no duplicate messages  

With idempotence:

- Kafka assigns sequence numbers  
- Duplicates are dropped by broker  

---

## Consumer Retries

Flow:

1. Consumer receives message  
2. Tries to process **N in-memory retries**  
3. If still failing → send to **Retry Topic**  
4. Retry Topic consumer processes again  
5. If still failing → send to **Dead Letter Queue (DLQ)**  

### Why In-Memory Retries First?

- Handles **transient failures** (e.g., DB hiccups, 500 errors)  
- Faster than publishing to another topic  
- Reduces unnecessary topic writes  
- Good for small retry counts (2–5 retries)

### Retention Guidelines:

- Retry topic: **minutes → hours**  
- DLQ: **days → weeks**  
- Main topic: **hours → days**  

---

## Acknowledgements vs Commits

### Producer Acknowledgements (`acks`)

| acks | Meaning |
|------|---------|
| 0 | Producer does NOT wait for any confirmation |
| 1 | Leader acknowledges write |
| all | Leader + ALL in-sync replicas acknowledge |

**Important:**
- `acks` apply **only to producers**  
- Controls write durability, not consumer state  

---

### Consumer Commits

Consumers commit **offsets**, not messages.

- **Acknowledgment = message received**  
- **Commit = message successfully processed**  

Commit types:
- Auto commit (unstable)  
- Manual commit (recommended)  

---

## Partition Replication

Replication occurs **per partition**, not per topic.

### Example Setup

Topic: `orders`  
- 4 partitions (P0, P1, P2, P3)  
- Replication factor = 3  
- 3 brokers (B1, B2, B3)  

Total replicas:
```
4 partitions × 3 replicas = 12 partition replicas
```

Each partition has:

- 1 **leader**  
- 2 **followers**  

### Partition Distribution Example

```
Broker 1: P0-Leader, P1-Follower, P2-Follower
Broker 2: P1-Leader, P2-Follower, P3-Leader
Broker 3: P0-Follower, P3-Follower, P2-Leader
```

Kafka ensures:

- No two replicas of the same partition are on the same broker  
- ISR (In-Sync Replicas) hold the latest data  
- If leader fails → an ISR follower becomes leader  

### How Partition Placement Works

Steps:

1. Spread leaders evenly  
2. Spread followers across different brokers  
3. Ensure balanced replica count  
4. Support **rack awareness** for multi-AZ setups  

---

## Full Message Lifecycle (Producer → Kafka → Consumer)

1. Producer creates message  
2. Kafka determines target partition  
   - Hash(key) → deterministic partition  
   - No key → round-robin  
3. Message written to **leader partition**  
4. With `acks=all`:  
   - Leader writes message  
   - Followers replicate  
   - Producer receives acknowledgment  
5. Consumer polls for messages  
6. Consumer processes message  
7. If successful → commits offset  
8. If failure → in-memory retries → retry topic → DLQ  

---

# Summary

Kafka is:

- Distributed  
- Log-based  
- Pull-driven  
- Scalable via partitioning  
- Durable & replayable  
- Fault tolerant with replication  
- Ideal for event-driven architectures  

It provides:

- Strong ordering guarantees  
- High throughput  
- Replayability  
- Horizontal scalability  
