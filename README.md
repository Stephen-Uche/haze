

# Haze

Haze is a lightweight Redis-like key/value database written in Java that speaks RESP (Redis Serialization Protocol).

## Features

- RESP-compatible command parsing
- In-memory key/value storage
- Basic list operations
- Optional password authentication
- Snapshot-style save-to-file support
- Configurable port via CLI or environment variables

## Requirements

- Java 21 (project is configured with `maven.compiler.release=21`)
- Maven 3.8.1+ (for building/running with Maven)

## Run Locally

### Option 1: Run with Maven

```bash
mvn compile exec:java -Dexec.mainClass=org.fungover.haze.Main
```

### Option 2: Run compiled classes directly

If the project is already compiled and the dependencies exist in your local Maven cache:

```bash
java -cp "target/classes:$HOME/.m2/repository/org/apache/logging/log4j/log4j-api/2.22.1/log4j-api-2.22.1.jar:$HOME/.m2/repository/org/apache/logging/log4j/log4j-core/2.22.1/log4j-core-2.22.1.jar" org.fungover.haze.Main
```

### Option 3: Run with Docker

```bash
docker pull fungover/haze
docker run -p 6379:6379 fungover/haze
```

## Configuration

Haze supports CLI flags and environment variables.

### Port

- CLI: `-p 6440` or `--port 6440`
- Env: `HAZE_PORT=6440`
- Default: `6379`

### Password (optional)

- CLI: `-pw yourpassword` or `--password yourpassword`
- Env: `HAZE_PASSWORD=yourpassword`

## Supported Commands

- `SET`
- `GET`
- `DEL`
- `PING`
- `SETNX`
- `EXISTS`
- `SAVE`
- `AUTH`
- `INCR`
- `DECR`
- `GETDEL`
- `RPUSH`
- `LPUSH`
- `LPOP`
- `RPOP`
- `LLEN`
- `LMOVE`
- `LTRIM`
- `LINDEX`
- `LSET`
- `LINSERT`

## Example Commands

```text
PING
SET key1 Hi
GET key1
SETNX mykey Hello
EXISTS key1
DEL key1
RPUSH mylist a b c
LPOP mylist
```

## Notes

- Data is stored in memory while the server is running.
- `SAVE` writes a snapshot file under `~/fungover/haze/`.
- The server uses virtual threads for handling client connections.
