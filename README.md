Perfecto 🔥 — entonces aquí tienes tu **README.md actualizado**, completamente adaptado al **entorno Gradle**, manteniendo las buenas prácticas, estructura y contexto de tu proyecto **Serenity + Screenplay + Appium + Cucumber con Java**.

---

## 🧾 README.md

```markdown
# 🚀 Proyecto de Automatización Móvil — Frisby App 🧩  
**Automatización E2E con Java, Serenity BDD, Screenplay, Cucumber y Appium (Gradle Build)**

---

## 📱 Descripción General

Este proyecto implementa un **framework de automatización móvil** para pruebas **E2E (End-to-End)** sobre la aplicación **Frisby App**, usando el patrón **Screenplay** de **Serenity BDD** y **Cucumber (Gherkin)** para describir los escenarios de negocio de forma legible.

El objetivo principal es garantizar la **calidad funcional de la aplicación móvil**, simulando el flujo real de usuario en Android: inicio de sesión, navegación, selección de productos y confirmación de pedidos.

---

## 🧠 Arquitectura del Proyecto

```

src
└── test
├── java
│   └── com.frisby.automation
│       ├── runners/           # Ejecutores de Cucumber + Serenity
│       ├── stepdefinitions/   # Glue code de Gherkin a Screenplay
│       ├── tasks/             # Acciones de negocio (Login, Compra, etc.)
│       ├── interactions/      # Acciones reutilizables (Scroll, Tap, Popups)
│       ├── questions/         # Validaciones del flujo
│       ├── userinterfaces/    # Page Objects con localizadores
│       ├── drivers/           # Configuración del AppiumDriver
│       ├── hooks/             # Before y After globales
│       └── utils/             # Constantes y helpers
│
└── resources
└── features/
├── login.feature
├── compra_producto.feature
└── buscar_producto.feature

````

---

## ⚙️ Tecnologías y Herramientas

| Componente | Descripción |
|-------------|--------------|
| **Lenguaje** | Java 17+ |
| **Framework** | Serenity BDD + Screenplay Pattern |
| **BDD** | Cucumber |
| **Mobile Testing** | Appium (Android Native App) |
| **Build Tool** | Gradle |
| **IDE** | IntelliJ IDEA / VS Code |
| **Reportes** | Serenity HTML / JSON Reports |

---

## 🧩 Ejemplo de Escenario Gherkin

```gherkin
Feature: Compra de productos en Frisby App
  Como usuario registrado
  Quiero seleccionar un combo y confirmarlo
  Para validar el flujo completo de compra

  Scenario: Agregar un combo al carrito y confirmar pedido
    Given que el usuario ha iniciado sesión correctamente
    And está en la Home page
    When da click en el botón domicilio
    And selecciona la dirección
    And selecciona una categoría
    And selecciona los acompañantes
    And da click en el botón agregar
    And se dirige al carrito de compra
    And da click en el botón confirmar pedido
    Then el sistema confirma el pedido
````

---





## 🧱 serenity.properties

```properties
webdriver.driver=appium
appium.hub=http://127.0.0.1:4723/wd/hub
appium.platformName=Android
appium.deviceName=emulator-5554
appium.automationName=UiAutomator2
appium.app=src/test/resources/apks/Frisby_1.7.0.apk
serenity.take.screenshots=AFTER_EACH_STEP
serenity.project.name=Automation Frisby App
```

---

## 🛠️ Configuración del `build.gradle`

```groovy
plugins {
    id 'java'
    id 'net.serenity-bdd.aggregator' version '3.9.8'
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation 'net.serenity-bdd:serenity-core:3.9.8'
    testImplementation 'net.serenity-bdd:serenity-screenplay:3.9.8'
    testImplementation 'net.serenity-bdd:serenity-screenplay-webdriver:3.9.8'
    testImplementation 'net.serenity-bdd:serenity-cucumber:3.9.8'
    testImplementation 'io.appium:java-client:8.5.0'
    testImplementation 'org.slf4j:slf4j-simple:2.0.9'
}

test {
    useJUnitPlatform()
}
```

---

## ▶️ Ejecución del Proyecto

1. **Instalar dependencias:**

   ```bash
   gradle clean build
   ```

2. **Levantar Appium Server:**

   ```bash
   appium
   ```

3. **Iniciar el emulador Android o conectar dispositivo físico.**

4. **Ejecutar los escenarios de Cucumber:**

   ```bash
   gradle clean test
   ```

5. **Generar reporte Serenity:**

   ```bash
   gradle aggregate
   ```

6. **Abrir reporte HTML:**

   ```
   build/reports/serenity/index.html
   ```

---

## 🧰 Buenas Prácticas Aplicadas

✅ Separación entre *Tasks*, *Interactions*, *Questions* y *UI*
✅ Reutilización de Interactions genéricas
✅ Uso de Hooks globales (`@Before`, `@After`) centralizados
✅ Naming Convention clara y descriptiva


---

## 👨‍💻 Autor

| Nombre              | Rol                    | Enlace                        |
| ------------------- | ---------------------- | ----------------------------- |
| **Andres Castillo** | QA Automation Engineer | [GitHub](https://github.com/andresinho826/) |



> 💬 *"Automatizar es traducir la intención del usuario al lenguaje de las máquinas sin perder la historia humana del producto."*
> — *Serenity BDD Philosophy*

