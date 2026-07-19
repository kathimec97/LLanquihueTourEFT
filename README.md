![Duoc UC](https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png)
# 🧠 Caso: Agencia de Turismo – Desarrollo Orientado a Objetos I

## 👤 Autor del proyecto
- **Nombre completo:** [Katherine del Carmen Avila Mecía]
- **Sección:** [002A]
- **Carrera:** Analista Programador Computacional
- **Sede:** [Campus Virtual]

---

## ⬆️ Entrega Actual: Integrando persistencia de datos, gestores y excepciones
## Clases e Interfaces principales
* **Interface `Registrable`**: Define el contrato común `registrar()`, `mostrarDatos()` y `cambiarATextoPlano()` para todas las entidades gestionables.
* **Clase `GestorTours`**: Gestiona las colecciones de datos (Listas) y la persistencia de datos (lectura/escritura de archivos `.txt`)
* **Modelo de datos**: Jerarquía de clases (`Contacto`, `Cliente`, `Guia`, `Proveedor`, `OperadorDeTransporte`) que implementan herencia para evitar la duplicación de código.
* **Excepciones (`Paquete util`)**: Clases personalizadas (`RutInvalidException`, `CorreoInvalidException`, etc.) para controlar la validez de los datos ingresados.

## Características destacadas
* **Herencia y Polimorfismo**: Uso de una superclase `Contacto` y la interfaz `Registrable` para procesar y guardar de manera uniforme distintas entidades en los archivos de texto.
* **Manejo de Excepciones**: Validación robusta en tiempo de ejecución para evitar el ingreso de datos corruptos al sistema.
* **Consola Interactiva (UI)**: Menú intuitivo en la clase `Main` que permite reservar tours, modificar precios y consultar historiales.
* **Persistencia**: Carga automática de datos al inicio y guardado de cambios al salir utilizando archivos de texto plano.

## Instrucciones de ejecución
1. Asegúrate de tener configurado el JDK en tu entorno (IntelliJ IDEA recomendado).
2. Clona o descarga este repositorio en tu computadora local.
3. Verifica que en el directorio raíz del proyecto existan los archivos: `Cliente.txt`, `Guia.txt`, `Operadordetransporte.txt`, `Proveedor.txt` y `Tour.txt`.
4. Navega hasta el paquete `cl.duoc.llanquihuetour.ui` y ejecuta la clase principal `Main.java`.
5. Utiliza el teclado numérico para navegar por las diferentes opciones del menú en la consola.

## 📂 Estructura General del Proyecto

```plaintext
LlanquihueTour/
── src/
    └── cl.duoc.llanquihuetour/
        ├── data/                 
        │   └── GestorTours.java
        ├── model/                               
        │   ├── Cliente.java                                                
        │   ├── Contacto.java                
        │   ├── Guia.java
        │   ├── OperadorDeTransporte.java
        │   ├── Proveedor.java
        │   ├── Tour.java
        │   └── Registrable.java
        ├── ui/
        │   └── Main.java
        └── util/
            ├── CorreoInvalidException.java
            ├── DireccionInvalidaException.java
            ├── RutInvalidException.java
            └── TelefonoInvalidException.java
── (Archivos de texto en la raíz)
    ├── Cliente.txt
    ├── Guia.txt
    ├── Operadordetransporte.txt
    ├── Proveedor.txt
    └── Tour.txt