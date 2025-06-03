# TCP (Transmission Control Protocol)

TCP stands for **Transmission Control Protocol** — a protocol that not only enables data transmission but also provides mechanisms to **control** and manage the communication.

### Key Features

- **Layer 4 (Transport Layer)** protocol.
- Uses **segments** as its Protocol Data Unit (PDU).
- Since it's Layer 4, TCP is aware of **ports**, allowing it to direct traffic to specific processes (one per port).
- Introduces the **concept of a connection** between client and server — there’s mutual awareness between them.
- **Connection-oriented**: Data can only be sent **after** a connection is established. Packets sent without a handshake will be dropped.
- Establishes connection via a **3-way handshake** to verify both ends are reachable before data exchange.
- **Stateful protocol**: Keeps track of the session state (see below).
- TCP header is **20 bytes** (can extend to 60 bytes with options).
- Ensures **reliable communication** through:
  - **Acknowledgments** (ACK) for received segments.
  - **Retransmission** of lost segments.
- Supports **bi-directional communication** (unlike HTTP, which is strictly request-response).

---

### TCP Handshake (3-Way Handshake)

1. **SYN** – **Synchronize sequence numbers**. Used to initiate a TCP connection
2. **SYN-ACK** – Server acknowledges and replies with its own SYN.
3. **ACK** – Client acknowledges the server's SYN.
Once the 3 steps are complete, a **reliable and full-duplex** connection is established, and data exchange can begin.

- When a TCP connection begins, both client and server must **agree on initial sequence numbers** (ISN).
- This is called **synchronization**.
- Sequence numbers are crucial to:
  - Track how many bytes have been sent/received.
  - Ensure **ordered**, **reliable** delivery.
- The `SYN` flag signals:  
  - "Let’s start talking — here is my starting sequence number."
```text
Client                             Server
  | ---- SYN (seq=x) ------------>    |
  | <--- SYN-ACK (seq=y, ack=x+1) --- |
  | --- ACK (ack=y+1) ----------->    |
```

---

### TCP Connection Teardown (Graceful Close)
- TCP uses the 4-way termination process:
- **FIN** - (**Finish**)  Used to **gracefully terminate** a connection.
```text
Client                     Server
  | ---- FIN ---->         |
  | <--- ACK ----          |
  |                        |
  | <--- FIN ----          |
  | ---- ACK ---->         |
```
- One side sends a FIN to signal it’s done sending.
- The other side responds with ACK, then later sends its own FIN.
- Final ACK confirms closure.
- Each direction of the connection is closed independently.

---

### TCP Connection and Sockets

- A **file descriptor** (i.e., a socket) is created once the TCP handshake completes.
- After `SYN → SYN-ACK → ACK`, both client and server maintain a **live connection** using that socket, ready to **read/write** data.

---

### What Does "TCP is Stateful" Mean?

- TCP **remembers the state** of each connection throughout its lifecycle — similar to an ongoing conversation.
- TCP Tracks:
    - **Connection states**: `SYN_SENT`, `SYN_RECEIVED`, `CLOSED`, etc.
    - **Sequence numbers**: Track byte order for reliability.
    - **Acknowledgment numbers**: Confirm what has been received.
    - **Buffers**: Store received data before processing.
    - **Control flags**: For flow/congestion control, retransmission timers, etc.

> **State** = All the metadata needed to ensure the connection is **reliable, ordered, and complete**.

### Where Is TCP State Stored?

- On both **client and server sides**, inside the **OS kernel’s TCP stack**.
- Each system maintains a **TCP connection table**. You can inspect this using tools like:
  - `netstat -an`
  - `ss -t`

### ACK (Acknowledgment in TCP)

- When a segment (e.g., Segment 1) is sent, the receiver sends back an **ACK** to confirm it was received.
- TCP supports **pipelining** — you can send multiple segments (e.g., Segment 1, 2, 3) without waiting for individual ACKs.
- Instead of receiving a separate ACK for each segment, the receiver can send a **cumulative ACK**.
  - For example, an **ACK 4** means that segments 1 to 3 have been successfully received (i.e., the receiver is expecting byte 4 next).
- This improves efficiency and performance in high-throughput scenarios.

---

### MTU (Maximum Transmission Unit)

- **Definition**: The **MTU** is the maximum size (in bytes) of a packet that can be transmitted over a network interface without requiring fragmentation.
- **Scope**: Each network interface (on routers, switches, computers, etc.) may have its **own MTU setting**.
- **Default MTU**:
  - On most Ethernet networks: **1500 bytes**
  - Resulting in:
    - **MSS (Maximum Segment Size)** = 1460 bytes
    - MSS = MTU - IP Header (20 bytes) - TCP Header (20 bytes)
- **MTU includes**:
  - Payload
  - IP + TCP headers
  - **Does NOT include**: Ethernet frame headers (like MAC address fields)

### What If a Router in the Path Has a Smaller MTU?

Suppose your system sends a packet with size = 1500 bytes, but **some router in the path** only supports an MTU of 1400 bytes.

Two things can happen:

1. **Fragmentation** (if DF bit is not set)
- If the IP packet’s **DF (Don’t Fragment) bit is unset**, the router will:
  - Split the packet into **multiple IP fragments**
  - Send them onward
  - The **receiver reassembles** the fragments into the full packet

2. **Packet Drop + ICMP "Fragmentation Needed"** (if DF bit is set)
- If the DF bit **is set** (common in modern TCP/IP stacks):
  - The router **drops the packet**
  - Sends back an **ICMP "Fragmentation Needed"** message to the sender
  - The sender may then:
    - Lower the MTU using **Path MTU Discovery (PMTUD)**
    - Retry the packet with a smaller size

---

## What Happens If TCP Sequence Numbers Run Out?
- TCP uses a **32-bit sequence number** field (4 bytes).
- This gives a total range of **2³² = 4,294,967,296** bytes.
- It represents the **byte number** in the stream, not the packet count.
- Refer diagram: https://madpackets.com/2018/04/25/tcp-sequence-and-acknowledgement-numbers-explained/
- Once the sequence number reaches the maximum (2³² - 1), it wraps back to **0**.
- TCP ensures that data from one wrap isn't confused with old segments using:
  - Sequence number logic
  - Time-based safeguards (MSL, timestamps)
- In long-lived, high-throughput connections, TCP options like **timestamps** and **window scaling** are essential for safe operation.
