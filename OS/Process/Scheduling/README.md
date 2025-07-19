# Process Scheduling in Operating Systems

## What is Process Scheduling?

Process scheduling is the activity of the process manager that handles the removal of the running process from the CPU and selection of another process based on a particular strategy. It's like a traffic controller at a busy intersection - deciding which cars (processes) get to go through the intersection (CPU) and in what order.

**Why do we need scheduling?**
- Multiple processes want to use the CPU simultaneously
- CPU can only execute one process at a time
- Need to ensure fair allocation of CPU time
- Maintain system responsiveness and efficiency

## How Process Scheduling Works

### Basic Scheduling Flow

1. **Process Creation**: When a process is created, it enters the system with initial priority and scheduling parameters
2. **Ready Queue**: Processes waiting for CPU time are placed in ready queues based on their priority
3. **Scheduler Selection**: The scheduler selects the next process to run based on the scheduling algorithm
4. **Context Switch**: The CPU switches from the current process to the selected process
5. **Execution**: The selected process runs until it voluntarily yields, is preempted, or blocks for I/O

### Process States in Scheduling

```
NEW → READY → RUNNING → TERMINATED
        ↑         ↓
        ←— BLOCKED/WAITING
```

- **NEW**: Process is being created
- **READY**: Process is ready to run and waiting in the ready queue
- **RUNNING**: Process is currently executing on the CPU
- **BLOCKED/WAITING**: Process is waiting for I/O or other events
- **TERMINATED**: Process has finished execution

### Ready Queue Data Structure

The **Ready Queue** is typically implemented as a **Priority Queue** or multiple queues:

**Priority Queue Implementation:**
```
High Priority Queue:  [Process A] → [Process B] → NULL
Medium Priority Queue: [Process C] → [Process D] → [Process E] → NULL  
Low Priority Queue:   [Process F] → NULL
```

**Why Priority Queue?**
- Processes with higher priority get CPU first
- Efficient insertion and deletion (O(log n))
- Easy to maintain priority order
- Can have multiple levels (multilevel queues)


## Process Priority Communication

### How Processes Tell the Scheduler About Their Priority

Processes communicate their scheduling needs through several simple mechanisms:

#### 1. Nice Values (Linux)
```c
// Basic priority setting
nice(5);        // Lower priority (be nice to others)
nice(-5);       // Higher priority (need more CPU)
```

#### 2. System Calls
```c
// Set priority for current process
setpriority(PRIO_PROCESS, 0, 10);  // Set to priority 10

// Get current priority  
int priority = getpriority(PRIO_PROCESS, 0);
```

#### 3. Command Line Tools
```bash
# Run process with lower priority
nice -n 10 ./my_program

# Change priority of running process
renice -n 5 -p 1234
```

#### 4. Process Creation with Priority
```c
// Fork and set child priority
pid_t pid = fork();
if (pid == 0) {
    nice(10);  // Child runs with lower priority
    exec("./child_program");
}
```

## Scheduling Algorithms

### 1. First-Come, First-Served (FCFS)
**Description**: Execute processes in the order they arrive, like a queue at a bank.

**Pros**: 
- Simple to implement
- Fair in terms of arrival order
- No starvation

**Cons**: 
- Poor average waiting time
- Convoy effect (short processes wait for long ones)

**Example**: P1 arrives first (10ms), P2 arrives (2ms), P3 arrives (3ms)
```
Timeline: [P1--------][P2--][P3---]
Wait time: P1=0, P2=10, P3=12 → Average = 7.33ms
```

### 2. Shortest Job First (SJF)
**Description**: Execute the process with the shortest CPU burst time first.

**Pros**: 
- Optimal average waiting time
- Good for batch systems

**Cons**: 
- Requires prediction of burst time
- Starvation of long processes
- Not practical for interactive systems

**Example**: Same processes but SJF order
```
Timeline: [P2--][P3---][P1--------]
Wait time: P1=5, P2=0, P3=2 → Average = 2.33ms (better!)
```

### 3. Priority Scheduling
**Description**: Execute highest priority process first.

**Pros**: 
- Important processes get preference
- Flexible priority assignment

**Cons**: 
- Starvation of low-priority processes
- Priority inversion problems

**Example**: P1(priority=3), P2(priority=1), P3(priority=2)
```
Timeline: [P2][P3][P1] (lower number = higher priority)
```

### 4. Round Robin (RR)
**Description**: Each process gets a fixed time slice (quantum) in circular order.

**Pros**: 
- Good for interactive systems
- No starvation
- Fair CPU sharing

**Cons**: 
- Context switch overhead
- Performance depends on quantum size

**Example**: Quantum = 4ms
```
Timeline: [P1----][P2----][P3----][P1----][P2--] etc.
```

### 5. Multilevel Queue Scheduling
**Description**: Multiple queues with different priorities and algorithms.

**Structure**:
```
System Processes (Highest Priority) → FCFS
Interactive Processes              → Round Robin  
Batch Processes (Lowest Priority)  → SJF
```

**Pros**: 
- Different algorithms for different process types
- Good separation of process types

**Cons**: 
- Starvation between queues
- Complex to implement

### 6. Completely Fair Scheduler (CFS) - Linux Default
**Description**: Maintains virtual runtime for each process, always schedules process with lowest virtual runtime.

**Key Concept**: 
```c
// Simplified concept
if (process_A.virtual_runtime < process_B.virtual_runtime) {
    schedule(process_A);
} else {
    schedule(process_B);
}
```

**Pros**: 
- True fairness
- Good interactive performance
- Scales well

**Cons**: 
- Complex implementation
- Overhead of virtual runtime calculations

## Real-World Examples

### InnoDB Database Engine Scheduling

MySQL's InnoDB engine demonstrates advanced I/O scheduling in database systems:

#### InnoDB Architecture
```
User Queries
     ↓
┌─────────────────────┐
│   Buffer Pool       │ ← Memory cache of database pages
└─────────────────────┘
     ↓
┌─────────────────────┐
│  I/O Scheduler      │ ← Prioritizes different I/O operations  
│                     │
│ ┌─────┐ ┌─────┐     │
│ │Read │ │Write│     │ ← Different thread pools
│ │Thrds│ │Thrds│     │
│ └─────┘ └─────┘     │
└─────────────────────┘
     ↓
   Disk Storage
```

#### InnoDB Priority System
InnoDB assigns different priorities to I/O operations:

1. **Highest Priority**: Redo log writes (transaction durability)
2. **High Priority**: User query reads (someone is waiting)
3. **Medium Priority**: Transaction commits  
4. **Low Priority**: Background page flushing
5. **Lowest Priority**: Checkpoint writes


### Nginx Web Server Scheduling

Nginx uses event-driven architecture for handling thousands of concurrent connections:

#### Nginx Architecture
```
Internet Requests
        ↓
┌──────────────────┐
│  Master Process  │ ← Manages worker processes
└──────────────────┘
        ↓
┌──────────────────┐
│ Worker Processes │ ← Handle actual requests
│                  │
│ ┌──────────────┐ │
│ │ Event Loop   │ │ ← Single thread handles many connections
│ │              │ │
│ │ Connection1  │ │
│ │ Connection2  │ │ ← Non-blocking I/O
│ │ Connection3  │ │
│ │ ...          │ │
│ └──────────────┘ │
└──────────────────┘
```

#### Nginx Event-Driven Model
Traditional server: 1 thread per connection
```
Thread1 → Connection1 (blocked on I/O)
Thread2 → Connection2 (blocked on I/O)
Thread3 → Connection3 (blocked on I/O)
```

Nginx: 1 thread handles many connections
```
Event Loop → Connection1 (ready to read)
           → Connection2 (ready to write)  
           → Connection3 (waiting for data)
           → Connection4 (ready to read)
```

#### Nginx Configuration
```nginx
# Number of worker processes (usually = CPU cores)
worker_processes auto;

# Events block - connection handling
events {
    worker_connections 1024;    # Max connections per worker
    use epoll;                  # Efficient event notification (Linux)
    multi_accept on;           # Accept multiple connections at once
}

# HTTP block - request processing
http {
    sendfile on;               # Efficient file transfers
    keepalive_timeout 65;      # Keep connections alive
    
    # Load balancing (simple round-robin)
    upstream backend {
        server backend1.example.com;
        server backend2.example.com;
        server backend3.example.com;
    }
}
```

#### Why Nginx is Fast
1. **Event-driven**: No blocking on I/O operations
2. **Single-threaded workers**: No context switching overhead
3. **Asynchronous**: Handle multiple requests simultaneously
4. **Efficient system calls**: Uses epoll (Linux) for event notification

## Summary

Process scheduling is about fairly and efficiently sharing the CPU among multiple processes. Different algorithms work better for different scenarios:

- **FCFS**: Simple but can cause delays
- **SJF**: Optimal for known job lengths  
- **Priority**: Good for important tasks
- **Round Robin**: Fair for interactive systems
- **CFS**: Modern fair scheduling

Real-world systems like InnoDB and Nginx show how scheduling principles apply beyond just CPU scheduling - they're used for I/O operations, network connections, and resource management in general.

The key is understanding that scheduling is fundamentally about resource allocation and ensuring system responsiveness while maintaining fairness.
