# CPU (Central Processing Unit)

The CPU is the primary component of a computer that performs most of the processing inside the computer. It's often called the "brain" of the computer because it executes instructions from programs and coordinates all computer operations.

## What is a CPU?

The Central Processing Unit (CPU) is a hardware component that:
- Executes instructions from computer programs
- Performs arithmetic and logical operations
- Controls and coordinates other computer components
- Manages data flow between memory, storage, and peripherals

## CPU Components

### 1. **Control Unit (CU)**
- Fetches instructions from memory
- Decodes instructions to determine what operations to perform
- Coordinates the execution of instructions
- Manages the flow of data between CPU components

### 2. **Arithmetic Logic Unit (ALU)**
- Performs arithmetic operations (addition, subtraction, multiplication, division)
- Executes logical operations (AND, OR, NOT, XOR)
- Handles comparison operations
- Processes bit manipulation operations

### 3. **Registers**
- Small, high-speed storage locations within the CPU
- Store data temporarily during instruction execution
- Types include:
  - **Program Counter (PC)**: Points to the next instruction to execute
  - **Instruction Register (IR)**: Holds the current instruction being executed
  - **Accumulator**: Stores intermediate arithmetic results
  - **General-purpose registers**: Used for various data storage needs

### 4. **Cache Memory**
- High-speed memory located close to or within the CPU
- Stores frequently accessed data and instructions
- Levels: L1 (fastest, smallest), L2, L3 (slower, larger)
- Reduces memory access time significantly

## CPU Cores and Parallel Processing

### Understanding CPU Cores

A **CPU core** is an independent processing unit within a CPU chip. Modern CPUs are multi-core processors containing multiple cores on a single chip.

#### Common Core Configurations:
- **Single-core**: One processing unit (older systems)
- **Dual-core**: Two processing units
- **Quad-core**: Four processing units
- **Hexa-core**: Six processing units
- **Octa-core**: Eight processing units
- **Many-core**: 12, 16, 32+ cores (server/workstation CPUs)

### Process Execution and Core Limitation

**Important Clarification**: The number of processes that can run simultaneously depends on the number of CPU cores and threads, not a fixed number like 4.

#### True Parallel Execution:
- If you have a **4-core CPU**, only **4 processes can truly execute simultaneously**
- Each core can handle one process at a time for true parallel execution
- With **Hyper-Threading/SMT** (Simultaneous Multithreading), each core can handle 2 threads, so a 4-core CPU with HT can handle 8 threads simultaneously

#### Context Switching Illusion:
- **Context switching** allows the OS to rapidly switch between processes
- Each process gets a small time slice (typically 1-10 milliseconds)
- The switching happens so fast that it creates the illusion of multiple processes running simultaneously
- This is called **time-sharing** or **multiprogramming**

```
Example with 4-core CPU:
┌─────────────────────────────────────────────────────────┐
│ Core 1: Process A → Process E → Process I → Process A   │
│ Core 2: Process B → Process F → Process J → Process B   │
│ Core 3: Process C → Process G → Process K → Process C   │
│ Core 4: Process D → Process H → Process L → Process D   │
└─────────────────────────────────────────────────────────┘
Time slices: 10ms each, context switch every 10ms
```

## CPU Architecture Types

### 1. **CISC (Complex Instruction Set Computer)**
- Examples: x86, x86-64 (Intel, AMD)
- Large number of complex instructions
- Variable instruction lengths
- More transistors for instruction decoding

### 2. **RISC (Reduced Instruction Set Computer)**
- Examples: ARM, RISC-V, PowerPC
- Simple, uniform instructions
- Fixed instruction lengths
- Faster execution per instruction

### 3. **Hybrid Architectures**
- Modern processors often combine CISC and RISC principles
- CISC instructions decoded into RISC-like micro-operations internally

## CPU Performance Metrics

### 1. **Clock Speed (Frequency)**
- Measured in Hertz (Hz), typically GHz
- Indicates how many cycles per second the CPU can execute
- Higher frequency generally means faster processing

### 2. **Instructions Per Cycle (IPC)**
- Number of instructions executed per clock cycle
- Varies based on CPU architecture and instruction complexity
- More important than raw clock speed for performance

### 3. **Cache Size and Hierarchy**
- L1 Cache: 32-64 KB per core (fastest)
- L2 Cache: 256 KB - 1 MB per core
- L3 Cache: 8-32 MB shared across cores (slower but larger)

### 4. **Thermal Design Power (TDP)**
- Maximum heat generated under typical workload
- Affects cooling requirements and power consumption

## CPU Scheduling and Process Management

### Process States in CPU Context:
1. **Running**: Currently executing on a CPU core
2. **Ready**: Waiting for CPU time
3. **Blocked/Waiting**: Waiting for I/O or other resources

### Scheduling Algorithms:
- **Round Robin**: Each process gets equal time slices
- **Priority Scheduling**: Higher priority processes run first
- **Shortest Job First**: Processes with shortest execution time run first
- **Multilevel Feedback Queue**: Dynamic priority adjustment

## Modern CPU Features

### 1. **Pipelining**
- Breaks instruction execution into stages
- Multiple instructions can be in different pipeline stages simultaneously
- Increases throughput without increasing individual instruction speed

### 2. **Branch Prediction**
- Predicts which way conditional branches will go
- Reduces pipeline stalls from incorrect predictions
- Modern CPUs achieve 95%+ prediction accuracy

### 3. **Out-of-Order Execution**
- Executes instructions in optimal order, not program order
- Reduces CPU idle time by finding independent instructions to execute
- Results are committed in original program order

### 4. **Simultaneous Multithreading (SMT/Hyper-Threading)**
- Single core appears as multiple logical processors
- Improves resource utilization by running multiple threads
- Intel calls it Hyper-Threading, AMD calls it SMT

## CPU and Operating System Interaction

### System Calls
The OS provides an interface between applications and hardware:
```c
// Example: Getting process information
#include <sys/types.h>
#include <unistd.h>

int main() {
    pid_t pid = getpid();        // Get current process ID
    printf("Process ID: %d\n", pid);
    
    // CPU-intensive task
    for (long i = 0; i < 1000000000; i++) {
        // This will consume CPU cycles
    }
    return 0;
}
```

### Interrupts
- Hardware signals that require immediate CPU attention
- Types: Timer interrupts, I/O completion, hardware errors
- CPU stops current execution, handles interrupt, then resumes

## CPU Performance Monitoring

### Common Tools:
- **Windows**: Task Manager, Performance Monitor, CPU-Z
- **Linux**: top, htop, vmstat, iostat, perf
- **macOS**: Activity Monitor, top, system_profiler
