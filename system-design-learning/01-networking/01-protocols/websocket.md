# WebSocket

- WebSocket starts as an HTTP/1.1 Upgrade request and, after the handshake, switches to a persistent, full‑duplex protocol over the same TCP connection. (HTTP/2 uses an extended CONNECT instead of Upgrade.)
- Common uses: chat, realtime feeds, games, telemetry.

---

## How it works (short)
1. TCP 3‑way handshake opens a TCP connection.
2. Client sends an HTTP/1.1 Upgrade request with WebSocket headers.
3. Server replies with a 101 Switching Protocols response if it accepts.
4. Protocol switches: HTTP ends, WebSocket frames start on the same TCP connection.
5. Keepalive: ping/pong frames or TCP keepalives prevent idle timeouts.

### Upgrade request example
```http
GET /chat HTTP/1.1
Host: example.com
Upgrade: websocket
Connection: Upgrade
Sec-WebSocket-Key: dGhlIHNhbXBsZSBub25jZQ==
Sec-WebSocket-Version: 13
Origin: https://example.com
```

### Upgrade response example
```http
HTTP/1.1 101 Switching Protocols
Upgrade: websocket
Connection: Upgrade
Sec-WebSocket-Accept: s3pPLMBiTxaQ9kYGzzhZRbK+xOo=
```
- The server computes `Sec-WebSocket-Accept` from the client's `Sec-WebSocket-Key` as follows:
  1. Concatenate the client's key and the magic GUID `258EAFA5-E914-47DA-95CA-C5AB0DC85B11`.
  2. Compute the SHA-1 digest of the result.
  3. Base64-encode the digest.

Purpose: this proves to the client that the server received the exact `Sec-WebSocket-Key` and prevents simple spoofing or replay of upgrade responses by intermediaries; it verifies the server completed the handshake correctly.

---

## Network traversal — does WebSocket "fool" routers or proxies?

“WS uses HTTP upgrade and fools routers/proxies?”

- Short answer: **Yes — mostly correct.**

⚠️ Caveats:
- Deep Packet Inspection (DPI) firewalls can still block or terminate WebSocket connections based on content or policy.
- Corporate proxies may close or interfere with long‑lived idle connections (idle timeouts, connection reaping).
- Some proxies actively rewrite or inspect Upgrade requests and may prevent the handshake.

Summary: It’s not that WS "fools" the network — it is *designed to be compatible* by starting as HTTP on ports commonly allowed (80, 443). Routers typically only see TCP packets and do not care about the application protocol, but intelligent intermediaries can still interfere.

Practical notes:
- Use `wss://` (TLS) to avoid content‑based blocking and to prevent intermediaries from inspecting payloads.
- Add keepalives and reconnect logic to handle proxies that drop idle connections.
- If necessary, use fallback strategies (long polling, SSE) or use a tunnel (e.g., WebSocket over HTTPS proxying) when intermediaries are hostile.

---

## WebSocket frames (high level)
- Communication after the handshake is via frames, not HTTP messages.
- Frame fields include: FIN, Opcode (text/binary/close/ping/pong), Mask bit, Payload length, Payload data.
- Clients MUST mask payloads sent to servers; servers typically do not mask responses.
- Control frames (ping/pong/close) are limited in size and not fragmented; messages can be fragmented across data frames.

### Why clients MUST mask payloads
- Browsers XOR the payload with a random 4‑byte mask before sending (RFC 6455). The server must unmask before processing.
- Reason: masking protects intermediaries and legacy proxies from cross‑protocol attacks and cache/proxy poisoning. Without masking, a malicious web page could craft bytes that, when forwarded through an intermediary, might be interpreted as a valid HTTP request or other protocol message. Masking makes client payloads appear random to intermediaries and prevents such abuses.

---

## Important concepts
- Layers: Application → WebSocket/HTTP, Transport → TCP, Network → IP.
- Browser constraint: browsers can’t open raw TCP sockets, so WebSocket piggybacks on HTTP to gain browser support and firewall traversal.
- Authentication & TLS: starting as HTTP enables cookies/headers and TLS (`wss://`).
- Extensions & subprotocols: negotiated during handshake (e.g., `permessage-deflate`, custom subprotocols).

---

## Closing & keepalive
- Close: one side sends a Close frame (optional status code); the peer should reply with Close and the TCP connection is closed.
- Keepalive: use ping/pong frames to detect dead peers and keep connections alive through intermediaries.

---

## Security & deployment notes (practical)
- TLS / wss:
  - Use `wss://` in production for confidentiality and integrity. For `wss://`, the client establishes a TLS connection (HTTPS) to the server first (typically port 443) and then sends the HTTP Upgrade over that secured channel. You cannot upgrade a plain `ws://` connection to TLS after the fact — pick `wss://` from the start when encryption is required.
- Handshake validation and authentication:
  - Always validate `Origin` (browser clients) and require authentication at the handshake (Authorization header, secure cookies, tokens) as appropriate.
  - Consider short‑lived tokens and re‑validation for critical operations.
- Resource and DoS protection:
  - Enforce max message size, max number of concurrent connections per client/IP, and request rate limits.
  - Monitor connection counts and memory per connection; implement connection quotas and graceful eviction policies.
- Connection health:
  - Use ping/pong and idle timeouts to detect dead peers and to keep connections alive through proxies.
- Proxy and load balancer configuration:
  - Ensure proxies forward `Upgrade` and `Connection: Upgrade` headers. Example (Nginx): `proxy_http_version 1.1; proxy_set_header Upgrade $http_upgrade; proxy_set_header Connection "Upgrade"; proxy_read_timeout 3600;`.
  - Use WebSocket‑aware load balancers or configure TCP‑level load balancing with sticky sessions if your backend requires session affinity.
- Extensions and subprotocols:
  - Only enable trusted extensions (e.g., `permessage-deflate`) after verifying DoS and computational cost implications.
  - Validate and whitelist allowed subprotocols.
- Message handling:
  - Sanitize and validate incoming messages, enforce schema and size limits, and close connections on protocol violations.
- Operational practices:
  - Use mature libraries on both client and server sides; rotate TLS certificates, collect metrics (connection counts, latencies, errors), and plan for horizontal scaling (pub/sub, message brokers) rather than relying on sticky state alone.

---

## ws vs wss (summary)
- `ws://` — WebSocket over plain TCP (no TLS); default port 80. Not secure.
- `wss://` — WebSocket over TLS (WebSocket inside HTTPS/TLS); default port 443. Use for production.

Key differences:
- Encryption: `wss://` encrypts traffic and authenticates server certificate; `ws://` does not.
- Handshake transport: `wss://` does the TLS handshake first, then the WebSocket Upgrade over the encrypted channel.
- Use case: `wss://` for any sensitive data or when browser security policies require HTTPS; `ws://` only for trusted internal networks or testing.

Use `wss://` unless you have a controlled, isolated environment and you explicitly accept the risks of sending unencrypted WebSocket traffic.

---

## When to use WebSocket
- Use WebSocket for bidirectional, low‑latency interactions.
- Alternatives: Server‑Sent Events (SSE) for server→client streams and long polling when WebSocket is unavailable.
