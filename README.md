# Sistema de Gestión de Licencias Vehiculares

Sistema de escritorio desarrollado en Java (Swing) bajo arquitectura MVC para la gestión integral del ciclo de vida de licencias de conducir (Solicitud, Validación, Exámenes y Emisión).

---

## Requisitos del Sistema

Antes de ejecutar el proyecto, asegúrese de tener instalado:

* **Java Development Kit (JDK):** Versión 21 o superior.
* **Apache Maven:** Para la gestión de dependencias.
* **Conexión a Internet:** Requerida para conectar con la base de datos en la nube (Railway).

---

## Configuración y Variables

### Base de Datos

El proyecto está configurado por defecto para conectarse a una instancia de **MySQL en Railway**.

* **Archivo de Configuración:** `src/main/java/dao/ConexionDB.java`
* **Credenciales predeterminadas:**
  * **Host:** `yamanote.proxy.rlwy.net`
  * **Puerto:** `17281`
  * **Base de datos:** `railway`
  * **Usuario:** `root`

> **Nota:** Si desea cambiar la base de datos a un entorno local, modifique las variables estáticas `URL`, `USER` y `PASS` en la clase `ConexionDB`.

---

## Compilación e Instalación

Siga estos pasos para construir el proyecto desde cero:

1. **Clonar el repositorio:**
```bash
   git clone <URL_DEL_REPOSITORIO>
   cd sistema_entrega_licencias
```

2. **Descargar dependencias y compilar:**
   Ejecute el siguiente comando en la raíz del proyecto (donde está el `pom.xml`) para descargar las librerías (`iText`, `BCrypt`, `FlatLaf`, `MySQL Driver`):
```bash
   mvn clean install
```
   *Este comando generará una carpeta `target` con los archivos compilados.*

---

## Ejecución

Existen varias formas de levantar la aplicación:

### Opción A: Desde Maven (Recomendada para Desarrollo)
```bash
mvn exec:java -Dexec.mainClass="Main"
```

### Opción B: Ejecutar el JAR

### Opción C: Ejecutar el EXE

---

## Credenciales de Acceso

Para las pruebas funcionales, utilice los siguientes usuarios pre-registrados:

| Rol | Usuario | Contraseña |
|---|---|---|
| ADMIN | `Alubs` | `1234` |
| ANALISTA | _(pendiente)_ | _(pendiente)_ |

---

## Estructura del Proyecto
```
src/main/java
├── dao         # Capa de Acceso a Datos (JDBC)
├── model       # Entidades y Modelos de Dominio
├── service     # Lógica de Negocio y Validaciones
└── ui          # Interfaz Gráfica (Swing + FlatLaf)
    ├── frames  # Ventanas Principales
    └── panels  # Vistas Modulares
```

---

## Demo del Sistema

<div align="center">
  <a href="https://drive.google.com/file/d/1SNNRbMikOttYbWQWE5IftjskOsissprB/view">
    <img src="https://drive.google.com/thumbnail?id=1SNNRbMikOttYbWQWE5IftjskOsissprB&sz=w800" alt="Demo del sistema" width="600"/>
    <br/>
    <img src="https://img.shields.io/badge/▶%20Ver%20Demo-Click%20aquí-red?style=for-the-badge&logo=google-drive&logoColor=white" alt="Ver demo"/>
  </a>
</div>


## Diseño de Prototipo de las Pantallas

![Prototipo 1](https://github.com/user-attachments/assets/6386e493-56c0-4dfa-8416-da720c1d75fd)
![Prototipo 2](https://github.com/user-attachments/assets/398fbc64-f299-49d0-9b23-5f652f08327b)
