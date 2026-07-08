# template-react

Una plantilla full-stack de nivel empresarial lista para producción para construir aplicaciones Software-As-A-Service (SaaS) Multi-Tenant. Combina un backend robusto en **Spring Boot 3.x (Java 21)** que implementa Arquitectura Hexagonal y Diseño Guiado por el Dominio (DDD) con un frontend premium de panel de administración en **React (Vite + TypeScript + Shadcn UI)**.

![Dashboard Preview](frontend/public/images/Template%20React.png)

---

## 🚀 Esquema de Nombres del Repositorio
Este proyecto sigue una estructura de nombres limpia para configuraciones multi-frontend que comparten el mismo backend de Spring Boot:

*   **`template-react`** (Este repositorio)
*   **`template-angular`** (Para la versión de Angular)
*   **`template-vanilla`** (Para la versión de Vanilla JS)

---

## 🏗️ Resumen de la Arquitectura

El proyecto está estructurado como un monorepositorio que contiene:
*   `/frontend`: SPA en React, estilizada con TailwindCSS y Radix UI (mediante Shadcn), utilizando TanStack Router.
*   `/backend`: API REST en Spring Boot, construida con un diseño de monódulo Gradle utilizando Arquitectura Hexagonal de Puertos y Adaptadores.

### Separación de Capas Hexagonales en el Backend
Dentro de `backend/src/main/java/com/elitemanagement/features/[nombre_caracteristica]/`:
1.  **Domain (Dominio):** Lógica de negocio central, Entidades (Aggregate Roots) y Puertos (interfaces). Java puro con cero dependencias de frameworks.
2.  **Application (Aplicación):** Implementación de Casos de Uso que orquestan las reglas de dominio.
3.  **Infrastructure (Infraestructura):** Adaptadores que implementan los puertos (ej. controladores REST, adaptadores de repositorios JPA, decodificadores JWT).

---

## ⚡ Características Clave

### Frontend
- **Modo Claro/Oscuro** y soporte para idiomas RTL (de derecha a izquierda) desde el primer momento.
- **Más de 10 páginas pre-construidas** (Panel de control, gestión de usuarios, roles, configuraciones, perfil).
- **TanStack Router** para un enrutamiento seguro de tipos.
- **Componentes personalizados de Shadcn UI** (Calendario, Diálogos, Selects, Sidebar, Sonner).

### Backend
- **Java 21 LTS y Spring Boot 3.3.0**.
- **Multi-Tenancy Dinámico:** Utiliza esquemas de PostgreSQL resueltos dinámicamente por petición a través de un `TenantConnectionProvider` personalizado que coincide con las cabeceras de la petición del cliente o el payload del JWT.
- **Seguridad Basada en Permisos (RBAC):** Cadenas de permisos de grano fino (ej. `read:users`, `write:users`) verificadas a través de anotaciones a nivel de método (`@PreAuthorize`).
- **Firma de Tokens con JJWT:** Generación, parseo y vinculación al contexto de tokens.
- **Migraciones con Flyway:** Migraciones de bases de datos completamente controladas por versiones por esquema.
- **Guardas con ArchUnit:** Pruebas automatizadas que validan los límites arquitectónicos de los paquetes en tiempo de compilación.

---

## 🛠️ Configuración para Desarrollo Local

### Requisitos Previos
- [Docker](https://www.docker.com/)
- [Java 21 (Se recomienda Temurin LTS)](https://adoptium.net/)
- [Node.js (v18+) y pnpm](https://nodejs.org/)

### 1. Base de Datos (PostgreSQL)
Inicia el contenedor de PostgreSQL desde el directorio raíz utilizando las variables de entorno declaradas en `.env`:
```bash
docker-compose up -d
```

### 2. Ejecutar el Backend
Dirígete a la carpeta `/backend`. Ábrela en tu IDE favorito (IntelliJ / VS Code) para permitir que resuelva automáticamente las dependencias, o ejecútalo mediante la terminal:
```bash
cd backend
./gradlew bootRun
```
*El servidor correrá en `http://localhost:8080`.*

### 3. Ejecutar el Frontend
Dirígete a la carpeta `/frontend`, instala las dependencias e inicia el servidor de desarrollo de Vite:
```bash
cd frontend
npm install
npm run dev
```
*La aplicación web correrá en `http://localhost:5173` (o revisa la salida de la consola).*

---

## 📁 Estructura de Directorios del Repositorio

```text
├── .env                                # Variables de entorno locales de la base de datos
├── docker-compose.yml                  # Ejecutor del servicio PostgreSQL
├── backend/                            # Backend en Spring Boot 3.x
│   ├── src/main/java/com/elitemanagement/
│   │   ├── config/                     # Configs centrales (SecurityConfig, TenantDatabaseConfig)
│   │   ├── shared/                     # Núcleo compartido (TenantContext, GlobalExceptionHandler)
│   │   └── features/                   # Contextos de negocio (auth, users)
│   │       ├── auth/
│   │       └── users/
│   └── src/main/resources/
│       ├── application.yml             # Propiedades de la aplicación
│       └── db/migration/               # Migraciones SQL de Flyway
└── frontend/                           # Frontend en React + Vite
    ├── src/
    │   ├── components/                 # Componentes Tailwind + Shadcn
    │   ├── features/                   # Vistas y estados del lado del cliente
    │   ├── context/                    # Contextos de autenticación y globales
    │   └── routes/                     # Archivos del enrutador TanStack
    └── package.json
```
