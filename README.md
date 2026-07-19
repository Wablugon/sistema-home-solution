# Home Solution

Sistema de escritorio en Java (Swing) para la gestión de empleados y proyectos de una empresa de servicios para el hogar. Permite dar de alta empleados (contratados y de planta permanente), crear proyectos con sus tareas, asignar responsables, registrar retrasos, finalizar tareas y proyectos, y consultar el estado general del sistema.

## Arquitectura

El sistema está organizado en capas, separando el modelo de dominio, la lógica de negocio y la interfaz de usuario:

```
app/          Punto de entrada de la aplicación (Main).
entidades/    Modelo de dominio: Empleado, EmpleadoContratado, EmpleadoDePlanta,
              Proyecto, Tarea, Categoria, Estado, Tupla.
servicios/    Lógica de negocio: ProyectoService, EmpleadoService, AsignacionService.
sistema/      Fachada del backend: IHomeSolution (contrato) y HomeSolution (implementación).
excepciones/  Excepciones controladas del dominio (HomeSolutionException y subclases).
util/         Validaciones de datos de entrada para la capa de servicios.
gui/          Capa de presentación (Swing): paneles, formularios y diálogos.
gui/util/     Utilidades de la capa de presentación: UITheme (estilo visual)
              y DialogoUtils (alertas y confirmaciones).
tests/        Pruebas unitarias de entidades y servicios.
```

La interfaz gráfica se comunica con el backend **exclusivamente** a través del contrato `IHomeSolution` (patrón Facade), sin acceder directamente a `entidades`, `servicios` ni `excepciones`. Esto mantiene el patrón MVC y permite reemplazar la implementación del backend sin afectar la GUI.

## Funcionalidades

- **Empleados**
  - Alta de empleados contratados (valor por hora).
  - Alta de empleados de planta permanente (valor por día + categoría).
  - Listado de empleados registrados.
  - Consulta de retrasos por empleado.
- **Proyectos**
  - Alta de un nuevo proyecto con datos del cliente, domicilio, fechas y tareas iniciales.
  - Listado de proyectos filtrado por estado (pendiente, activo, finalizado).
  - Consulta del detalle completo de un proyecto.
  - Cálculo del costo actual del proyecto.
  - Finalización de un proyecto.
- **Tareas**
  - Alta de nuevas tareas sobre un proyecto existente.
  - Asignación de responsable (manual o automática, priorizando al empleado con menos retrasos).
  - Reasignación de responsable.
  - Registro de retrasos.
  - Finalización de tareas individuales.

## Estructura de la capa gráfica (`gui`)

| Clase                          | Responsabilidad                                                                          |
| ------------------------------ | ---------------------------------------------------------------------------------------- |
| `PanelManager`                 | Controlador de vistas: navegación entre pantallas y acceso al backend (`IHomeSolution`). |
| `PanelPrincipal`               | Menú principal de la aplicación.                                                         |
| `FormularioEmpleado`           | Alta de empleados contratados y permanentes.                                             |
| `FormularioProyecto`           | Alta de un nuevo proyecto y sus tareas iniciales.                                        |
| `FormularioTarea`              | Diálogo modal para agregar una tarea a un proyecto existente.                            |
| `FormularioProyectoFinalizado` | Diálogo modal para capturar la fecha de finalización de un proyecto.                     |
| `GestionEmpleados`             | Listado de empleados y consulta de retrasos.                                             |
| `GestionProyectos`             | Gestión detallada de un proyecto: tareas, asignaciones y estado.                         |
| `ListaProyectos`               | Listado de proyectos filtrado por estado.                                                |
| `InformacionProyecto`          | Diálogo modal de solo lectura con el detalle de un proyecto.                             |
| `gui.util.UITheme`             | Colores, fuentes y componentes estilizados reutilizables.                                |
| `gui.util.DialogoUtils`        | Alertas, errores y confirmaciones centralizadas (`JOptionPane`).                         |

## Requisitos

- JDK 8 o superior.
- No requiere dependencias externas ni gestor de build (Maven/Gradle): es un proyecto Java estándar.

## Cómo ejecutar

### Desde una IDE (recomendado)

1. Importar el proyecto como proyecto Java estándar (IntelliJ IDEA, Eclipse o NetBeans).
2. Ejecutar la clase `app.Main`.

### Desde línea de comandos

```bash
# Compilar todas las fuentes hacia una carpeta de salida
javac -d out $(find . -name "*.java")

# Ejecutar
java -cp out app.Main
```

## Notas de diseño

- La navegación entre pantallas usa códigos centralizados como constantes en `PanelManager` (`PANTALLA_PRINCIPAL`, `PANTALLA_NUEVO_EMPLEADO`, etc.) en lugar de números mágicos sueltos en cada vista.
- Las vistas con datos dinámicos (listados de empleados, proyectos y su gestión) se reconstruyen cada vez que se muestran, para reflejar siempre el estado actual del backend.
- Todas las alertas, errores y confirmaciones se canalizan a través de `DialogoUtils` para mantener consistencia visual y de mensajes en toda la aplicación.
