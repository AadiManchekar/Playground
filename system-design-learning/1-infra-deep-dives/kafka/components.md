# Kafka Components

## 1. Broker
A Kafka server that stores partitions and serves producer/consumer requests.
- Handles read/write operations
- Manages partition replicas
- Performs log compaction and cleanup

## 2. Topic
Logical stream of messages.
- Named feed/category where records are published
- Can have multiple partitions for parallelism
- Configured with retention policies

## 3. Partition
Physical log file inside a topic.
Ensures parallelism and ordering.
- Immutable, ordered sequence of records
- Each record has an offset
- Distributed across brokers for scalability

## 4. Producer
Writes messages to Kafka topics.
Handles retries, batching, compression.
- Can specify partition key for routing
- Supports idempotent writes
- Configurable acknowledgment levels (acks=0,1,all)

## 5. Consumer
Reads messages from Kafka topics.
Supports consumer groups.
- Maintains offset position
- Can read from specific offset or timestamp
- Supports both manual and automatic offset commits

## 6. Consumer Group
Group of consumers sharing work.
Each partition → only 1 consumer in a group.
- Enables horizontal scaling
- Automatic rebalancing on consumer join/leave
- Offset commits tracked per group

## 7. Zookeeper / Kraft
Responsible for metadata management.
Kraft is Kafka's modern replacement for Zookeeper.
- Manages broker membership
- Controller election
- Topic/partition metadata storage

## 8. Offset
Unique identifier for each record within a partition.
- Sequential, immutable position
- Used for consumer tracking
- Stored in internal `__consumer_offsets` topic

## 9. Replication Factor
Number of copies of data across brokers.
- Ensures fault tolerance and high availability
- One leader, rest are followers (replicas)
- Minimum 3 recommended for production

## 10. Leader & Follower
**Leader**: Handles all reads/writes for a partition.
**Follower**: Replicates data from leader, acts as backup.
- Leader election happens on failure
- In-Sync Replicas (ISR) track healthy followers

## 11. Controller
Special broker managing cluster metadata.
- One controller per cluster
- Handles partition leader election
- Manages broker lifecycle events

## 12. Log Segment
Physical file storing partition data.
- Partitions divided into segments
- Old segments deleted based on retention policy
- Facilitates efficient log compaction

## 13. Record (Message)
Individual unit of data in Kafka.
- Key (optional): for partitioning/compaction
- Value: actual payload
- Timestamp: when produced
- Headers: metadata key-value pairs

## 14. Schema Registry
External component for managing message schemas.
- Ensures data compatibility
- Supports Avro, JSON Schema, Protobuf
- Version control for schema evolution

## 15. Connector (Kafka Connect)
Framework for streaming data between Kafka and external systems.
- Source Connectors: Import data into Kafka
- Sink Connectors: Export data from Kafka
- Pre-built connectors for databases, cloud storage, etc.

## 16. Streams API
Library for building real-time streaming applications.
- Stateful/stateless transformations
- Windowing operations
- Join streams and tables

---