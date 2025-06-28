## OSI

- A standard communication model was needed, imagine if you have to build different version of apps so that it works for LTE, fiber, WiFi etc.
- Without a standard model, upgrading network equipments becomes difficult 
- Innovations can be done in each layer seperately without affecting the rest of the layers.


### OSI 7 Layers
- Application layer (HTTP/gRPC/FTP)
- Presentation layer (Encoding, serde)
- Session Layer (Connection Establishment, TLS)
- Transport layer (TCP: Segment, UDP: Datagram) Ports
- Network layer (Packets) IP address
- Data link layer (Frames is the unit that is transmitted) Mac address
- Physical (Electric signals)


### TCP/IP Model
- Simpler to deal with Layers 5,6,7 as just one layer i.e Application layer. This is exactly what TCP/IP does

Layers
- Application layer (Combines Application, Presentation & Session layers into one layer)
- Transport layer
- Network layer
- Data link layer
- Physical layer (Not officially covered in the model)

---

## Example

So let's say you want to visit http://google.com in your browser. Here is what will happen, I'll skip DNS for now and assume we got the IP address of google.com. Let us say the client source IP is 9.8.7.6 and source port is 6666, destination is


1) Client know the IP of google.com (say its 1.2.3.4)

2) Client wants to send an HTTP GET / request to google.com so it builds the GET request.

3) Client know its HTTP (and not HTTPS) so the default port is 80 (since none is provided in the URL)

4) The client attempts to establish a connection and prepares a SYN segment at Layer 4

4) The segment needs destination port , and source port  (we are still in layer 4)

5) The segment is built as  < 6666 | (SYN) | 80 > layer 4 job is done

6) The network layer 3 kicks in and add source IP and Destination ip

7) The IP Packet will look <9.8.7.6 | 6666 | (SYN) | 80 | 1.2.3.4 > layer 3 job is done.

8) The layer 2 logic kicks in to add Destination MAC and source MAC (through ARP)

9) The layer 2 frame looks like (<AAAA| 9.8.7.6 | 6666 | (SYN) | 80 | 1.2.3.4 | BBBB > where BBBB is for sure our gateway (router) and AAAA is our machine

10) frame goes to the router, goes up the stack up layer 3 so the route knows where to send it.. (I'm skipping all that)

11) Finally a frame arrives at the final machine which owns the IP 1.2.3.4 (layer 2)

12) we go up the stack to get Layer 3 IP,

14) we go one more time layer 4 and aha its a TCP protocol and its a SYN, the OS kicks in

15) the OS puts the SYN in a queue matching the destination port and immediately send back an SYN/ACK

16) The GET / Request is still not sent btw

17) The OS SYN/ACK goes to the same steps 1-9 to build the segment-Ip-frame but the destination is now the client

18) the client receives the frame, gets the IP packet, gets the segment, and matches it with a SYN it already sent and complete the handshake by  sending an ACK( same steps 1-9 put the ACK in a segment, which goes in an IP packet)

19) the destination OS gets the final ACK, matches it with the SYN in its queue and finally we have a full connection!

20) the OS add the connection to the Accept queue for the backend http server to consume

21) the Backend call accept() which accepts the connection and now we have a file descriptor on the backend so it can read and write from it

22) We STILL did not send the actual request

23) the client now can safely send the request now that it has a connection

24) The client sends the GET request it prepared in step 2 in a segment (assume the entire request fits a single segment)

25) now that we have a segment same 1-9 steps apply, put the destination port/source port

26) OS receives the IP packet, gets the segments, get the data, OS puts the raw data in receive buffer

27) Backend calls read() on the connection to read the bytes,

28) the backend parses the bytes and now understands its a get request

29) the backend processes the get / request by reading it from disk/or DB

30) backend responses back with a HTTP response. and same steps follow..



--- 

## 🔍 Networking Questions

### Since HTTP is built on top of TCP, How is HTTP stateless while TCP is Stateful?

- Layers are isolated and independent
- HTTP does not care about and is independent of any of the lower-level protocols used to transport itself, even though it is itself stateless.
- So, the lower level protocol is stateful doesn’t mean anything on top of it automatically becomes stateful :)
- Application layer does not need to concern itself with lower layers.
- HTTP while it is built on top of TCP , HTTP itself doesn’t store any state related to HTTP on the client or the server. When you send a get request from fetch or axios you never worry about establishing connections correct? The HTTP api does all that for you.


### 🧭 How does the gateway (router) know where to forward the packet?

- Routers use **routing tables**.
- The **default gateway** checks the **destination IP** (`1.2.3.4`) and looks for:
- A **matching route** in its routing table.
- If no match is found → forwards to **default route** (`0.0.0.0/0`).
- It sends the packet to the **next hop**, which may be:
    - A directly connected router.
    - An upstream ISP router.

> Routers talk to each other using protocols like **BGP** (Border Gateway Protocol) to build and exchange routing tables.


### 🧭 How do routers on the internet know where to send packets?

- **Core internet routers** use **BGP** to announce IP prefixes (e.g., Google owns 1.2.3.0/24).
- When a packet destined for `1.2.3.4` is seen, each router:
1. Looks up the destination IP in its routing table.
2. Chooses the **best next hop**.
3. Forwards the packet to that next hop.
- This repeats until the packet reaches Google’s infrastructure.


### 🌐 Is an "Internet Gateway" a router?

Yes, but with **context**:
- **In your home/office:** the **default gateway** is the router that connects to your ISP.
- **In cloud environments (e.g., AWS):** an **Internet Gateway** is a logical component allowing instances in a VPC to reach the internet.
- So yes, it **performs routing**, NAT, and more, depending on setup.


### 🔢 What Layer is the Internet Gateway?

- Operates primarily at:
- **Layer 3 (Network)** → routing IP packets.
- Also touches **Layer 4 (Transport)** for NAT (e.g., PAT).


### Which layer does switch, router, proxy and firewal operate
- Switch operates on layer 2 as it needs Mac to be visible
- Router operates on layer 3 as it needs IP to be visible
- Proxy and Firewall operate on layer 4