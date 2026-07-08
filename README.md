# template-react

An enterprise-grade, production-ready full-stack template for building Multi-Tenant Software-As-A-Service (SaaS) applications. It combines a robust **Spring Boot 3.x (Java 21)** backend implementing Hexagonal Architecture & Domain-Driven Design (DDD) with a premium **React (Vite + TypeScript + Shadcn UI)** admin dashboard frontend.

![Dashboard Preview](frontend/public/images/Template%20React.png)

---

## 🚀 Repository Naming Scheme
This project follows a clean template naming structure for multi-frontend setups sharing the same Spring Boot backend:

*   **`template-react`** (This repository)
*   **`template-angular`** (For the Angular version)
*   **`template-vanilla`** (For the Vanilla JS version)

---

## 🏗️ Architecture Overview

The project is structured as a monorepo containing:
*   `/frontend`: React SPA, styled with TailwindCSS & Radix UI (via Shadcn), using TanStack Router.
*   `/backend`: Spring Boot REST API, built with a single-module Gradle layout using Hexagonal Ports & Adapters Architecture.

### Backend Hexagonal Layer Separation
Within `backend/src/main/java/com/elitemanagement/features/[feature_name]/`:
1.  **Domain:** Core business logic, Entities (Aggregate Roots), and Ports (interfaces). Pure Java with zero framework dependency.
2.  **Application:** Use Cases implementation orchestrating domain rules.
3.  **Infrastructure:** Adapters implementing ports (e.g. REST controllers, JPA repository adapters, JWT decoders).

---

## ⚡ Key Features

### Frontend
- **Light/Dark Mode** & RTL (Right-to-Left) language support out of the box.
- **10+ Pre-built Pages** (Dashboard, Users management, Roles, Settings, Profile).
- **TanStack Router** for type-safe routing.
- **Shadcn UI** customized components (Calendar, Dialog, Select, Sidebar, Sonner).

### Backend
- **Java 21 LTS & Spring Boot 3.3.0**.
- **Dynamic Multi-Tenancy:** Uses PostgreSQL schemas dynamically resolved per request using a custom `TenantConnectionProvider` matching the client request headers or JWT payload.
- **Permission-Based Security (RBAC):** Fine-grained permission strings (e.g., `read:users`, `write:users`) verified via method-level annotations (`@PreAuthorize`).
- **JJWT Token Signature:** Token generation, parsing, and context binding.
- **Flyway Migrations:** Fully version-controlled database migrations per schema.
- **ArchUnit Guards:** Automated tests validating architectural package boundaries on compile time.

---

## 🛠️ Local Development Setup

### Prerequisites
- [Docker](https://www.docker.com/)
- [Java 21 (Temurin LTS recommended)](https://adoptium.net/)
- [Node.js (v18+) & pnpm](https://nodejs.org/)

### 1. Database (PostgreSQL)
Start the PostgreSQL container from the root directory using the environment variables declared in `.env`:
```bash
docker-compose up -d
```

### 2. Run Backend
Navigate to the `/backend` folder. Open in your favorite IDE (IntelliJ / VS Code) to let it automatically resolve dependencies, or run using terminal:
```bash
cd backend
./gradlew bootRun
```
*The server will run on `http://localhost:8080`.*

### 3. Run Frontend
Navigate to the `/frontend` folder, install dependencies and start the Vite dev server:
```bash
cd frontend
pnpm install
pnpm run dev
```
*The web app will run on `http://localhost:5173` (or check console output).*

---

## 📁 Repository Directory Structure

```text
├── .env                                # Local database env variables
├── docker-compose.yml                  # PostgreSQL service runner
├── backend/                            # Spring Boot 3.x backend
│   ├── src/main/java/com/elitemanagement/
│   │   ├── config/                     # Core configs (SecurityConfig, TenantDatabaseConfig)
│   │   ├── shared/                     # Shared kernel (TenantContext, GlobalExceptionHandler)
│   │   └── features/                   # Business contexts (auth, users)
│   │       ├── auth/
│   │       └── users/
│   └── src/main/resources/
│       ├── application.yml             # App properties
│       └── db/migration/               # Flyway SQL migrations
└── frontend/                           # React + Vite frontend
    ├── src/
    │   ├── components/                 # Tailwind + Shadcn components
    │   ├── features/                   # Client-side views and states
    │   ├── context/                    # Auth and global contexts
    │   └── routes/                     # TanStack Router files
    └── package.json
```
