# Real-Time Finance Platform (Kafka + Spring Boot)

This project demonstrates a real-time, event-driven backend system.

## What it does
- Exposes a REST API to accept transactions (`POST /transactions`)
- Publishes each transaction as an event to Kafka topic `transactions.v1`
- Provides a health endpoint (`GET /health`)
- Returns clean validation errors for bad requests (HTTP 400 + field messages)

## Architecture
Client -> Transaction Service (Spring Boot REST API) -> Kafka Topic (transactions.v1)

## Run locally

### 1) Start Kafka (Docker)
From repo root:
```bash
cd infra/docker
docker compose up -d
docker ps
