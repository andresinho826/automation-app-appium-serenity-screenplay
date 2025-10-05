package com.frisby.automation.interactions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;

public class ScrollHastaElementoYClick implements Interaction {

    private final String criterio;
    private final TipoBusqueda tipoBusqueda;
    private final boolean hacerClick;
    private final int maxSwipes;
    private final Target targetElement;

    public enum TipoBusqueda {
        TEXTO,
        TEXTO_EXACTO,
        RESOURCE_ID,
        DESCRIPCION,
        CLASS_NAME,
        TARGET  // Nuevo tipo para trabajar con Target
    }

    // Constructor principal
    public ScrollHastaElementoYClick(String criterio, TipoBusqueda tipoBusqueda, boolean hacerClick, int maxSwipes, Target targetElement) {
        this.criterio = criterio;
        this.tipoBusqueda = tipoBusqueda;
        this.hacerClick = hacerClick;
        this.maxSwipes = maxSwipes;
        this.targetElement = targetElement;
    }

    // Constructor para búsquedas por String
    public ScrollHastaElementoYClick(String criterio, TipoBusqueda tipoBusqueda, boolean hacerClick, int maxSwipes) {
        this(criterio, tipoBusqueda, hacerClick, maxSwipes, null);
    }

    // Constructor para búsquedas por Target
    private ScrollHastaElementoYClick(Target target, boolean hacerClick, int maxSwipes) {
        this(null, TipoBusqueda.TARGET, hacerClick, maxSwipes, target);
    }

    // ============================================
    // MÉTODOS ESTÁTICOS CON TARGET
    // ============================================

    /**
     * Busca un Target haciendo scroll y hace click
     * @param target Target mapeado en Page Object
     * @return ScrollHastaElementoYClick
     */
    public static ScrollHastaElementoYClick hacia(Target target) {
        return new ScrollHastaElementoYClick(target, true, 10);
    }

    /**
     * Busca un Target haciendo scroll sin hacer click
     * @param target Target mapeado en Page Object
     * @return ScrollHastaElementoYClick
     */
    public static ScrollHastaElementoYClick soloBuscarHacia(Target target) {
        return new ScrollHastaElementoYClick(target, false, 10);
    }

    /**
     * Alias para mantener compatibilidad
     */
    public static ScrollHastaElementoYClick alTarget(Target target) {
        return hacia(target);
    }

    // ============================================
    // MÉTODOS ESTÁTICOS CON STRING (ANTERIORES)
    // ============================================

    public static ScrollHastaElementoYClick porTexto(String texto) {
        return new ScrollHastaElementoYClick(texto, TipoBusqueda.TEXTO, true, 10);
    }

    public static ScrollHastaElementoYClick porTextoExacto(String texto) {
        return new ScrollHastaElementoYClick(texto, TipoBusqueda.TEXTO_EXACTO, true, 10);
    }

    public static ScrollHastaElementoYClick porResourceId(String resourceId) {
        return new ScrollHastaElementoYClick(resourceId, TipoBusqueda.RESOURCE_ID, true, 10);
    }

    public static ScrollHastaElementoYClick porDescripcion(String descripcion) {
        return new ScrollHastaElementoYClick(descripcion, TipoBusqueda.DESCRIPCION, true, 10);
    }

    public static ScrollHastaElementoYClick porClassName(String className) {
        return new ScrollHastaElementoYClick(className, TipoBusqueda.CLASS_NAME, true, 10);
    }

    public static ScrollHastaElementoYClick soloBuscarPorTexto(String texto) {
        return new ScrollHastaElementoYClick(texto, TipoBusqueda.TEXTO, false, 10);
    }

    public static ScrollHastaElementoYClick soloBuscarPorResourceId(String resourceId) {
        return new ScrollHastaElementoYClick(resourceId, TipoBusqueda.RESOURCE_ID, false, 10);
    }

    // ============================================
    // CONFIGURACIÓN ADICIONAL
    // ============================================

    public ScrollHastaElementoYClick conMaxSwipes(int swipes) {
        return new ScrollHastaElementoYClick(this.criterio, this.tipoBusqueda, this.hacerClick, swipes, this.targetElement);
    }

    public ScrollHastaElementoYClick sinClick() {
        return new ScrollHastaElementoYClick(this.criterio, this.tipoBusqueda, false, this.maxSwipes, this.targetElement);
    }

    // ============================================
    // IMPLEMENTACIÓN DE INTERACTION
    // ============================================

    @Override
    public <T extends Actor> void performAs(T actor) {
        if (tipoBusqueda == TipoBusqueda.TARGET) {
            buscarYClickearTarget(actor);
        } else {
            buscarYClickearPorCriterio(actor);
        }
    }

    // ============================================
    // BÚSQUEDA POR TARGET
    // ============================================

    private void buscarYClickearTarget(Actor actor) {
        System.out.println("========================================");
        System.out.println(">>> Buscando Target con scroll");
        System.out.println("    Target: " + targetElement.getName());
        System.out.println("    Max swipes: " + maxSwipes);
        System.out.println("    Hacer click: " + hacerClick);
        System.out.println("========================================");

        AppiumDriver driver = obtenerAppiumDriver(actor);

        // Verificar si el elemento ya es visible
        if (esElementoVisible(actor)) {
            System.out.println("✓ Target ya es visible, no se requiere scroll");
            if (hacerClick) {
                clickEnTarget(actor);
            }
            return;
        }

        // Hacer scroll hasta encontrarlo
        boolean encontrado = false;
        for (int i = 0; i < maxSwipes; i++) {
            realizarSwipe(driver);
            esperarUnMomento(300);

            if (esElementoVisible(actor)) {
                System.out.println("✓ Target encontrado después de " + (i + 1) + " swipes");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.err.println("========================================");
            System.err.println("✗ ERROR: Target no encontrado");
            System.err.println("  Target: " + targetElement.getName());
            System.err.println("  Swipes realizados: " + maxSwipes);
            System.err.println("========================================");
            throw new NoSuchElementException(
                    "No se encontró el Target '" + targetElement.getName() + "' después de " + maxSwipes + " swipes"
            );
        }

        if (hacerClick) {
            clickEnTarget(actor);
        }

        System.out.println("========================================");
    }

    private boolean esElementoVisible(Actor actor) {
        try {
            WebElementFacade element = targetElement.resolveFor(actor);
            return element.isCurrentlyVisible();
        } catch (Exception e) {
            return false;
        }
    }

    private void clickEnTarget(Actor actor) {
        try {
            targetElement.resolveFor(actor).click();
            System.out.println("✓ Click exitoso en Target: " + targetElement.getName());
        } catch (Exception e) {
            System.err.println("✗ Error al hacer click en Target: " + targetElement.getName());
            throw new RuntimeException("Error al hacer click en el elemento", e);
        }
    }

    // ============================================
    // BÚSQUEDA POR CRITERIO STRING
    // ============================================

    private void buscarYClickearPorCriterio(Actor actor) {
        AppiumDriver appiumDriver = obtenerAppiumDriver(actor);

        try {
            System.out.println("========================================");
            System.out.println(">>> Buscando elemento con scroll");
            System.out.println("    Criterio: " + criterio);
            System.out.println("    Tipo: " + tipoBusqueda);
            System.out.println("    Max swipes: " + maxSwipes);
            System.out.println("    Hacer click: " + hacerClick);
            System.out.println("========================================");

            WebElement elemento = buscarElementoConScroll(appiumDriver);

            if (elemento != null && hacerClick) {
                elemento.click();
                System.out.println("✓ Click exitoso en elemento: " + criterio);
            } else if (elemento != null) {
                System.out.println("✓ Elemento encontrado (sin click): " + criterio);
            }

            System.out.println("========================================");

        } catch (NoSuchElementException e) {
            System.err.println("========================================");
            System.err.println("✗ ERROR: Elemento no encontrado");
            System.err.println("  Criterio: " + criterio);
            System.err.println("  Tipo: " + tipoBusqueda);
            System.err.println("  Swipes realizados: " + maxSwipes);
            System.err.println("========================================");

            throw new NoSuchElementException(
                    "No se encontró el elemento después de " + maxSwipes + " swipes.\n" +
                            "Tipo de búsqueda: " + tipoBusqueda + "\n" +
                            "Criterio: '" + criterio + "'", e
            );
        }
    }

    // ============================================
    // MÉTODOS PRIVADOS AUXILIARES
    // ============================================

    private AppiumDriver obtenerAppiumDriver(Actor actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        if (driver instanceof WebDriverFacade) {
            WebDriver proxiedDriver = ((WebDriverFacade) driver).getProxiedDriver();
            if (proxiedDriver instanceof AppiumDriver) {
                return (AppiumDriver) proxiedDriver;
            }
        } else if (driver instanceof AppiumDriver) {
            return (AppiumDriver) driver;
        }

        throw new IllegalStateException("Se requiere AppiumDriver para esta interacción");
    }

    private WebElement buscarElementoConScroll(AppiumDriver driver) {
        String uiAutomatorScript = construirScriptUiScrollable();
        System.out.println(">>> Script UiAutomator: " + uiAutomatorScript);
        return driver.findElement(AppiumBy.androidUIAutomator(uiAutomatorScript));
    }

    private String construirScriptUiScrollable() {
        String selectorElemento = construirSelectorElemento();

        return "new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
                ".setMaxSearchSwipes(" + maxSwipes + ")" +
                ".setSwipeDeadZonePercentage(0.1)" +
                ".scrollIntoView(" + selectorElemento + ")";
    }

    private String construirSelectorElemento() {
        switch (tipoBusqueda) {
            case TEXTO:
                return "new UiSelector().textContains(\"" + escaparTexto(criterio) + "\")";
            case TEXTO_EXACTO:
                return "new UiSelector().text(\"" + escaparTexto(criterio) + "\")";
            case RESOURCE_ID:
                return "new UiSelector().resourceId(\"" + criterio + "\")";
            case DESCRIPCION:
                return "new UiSelector().descriptionContains(\"" + escaparTexto(criterio) + "\")";
            case CLASS_NAME:
                return "new UiSelector().className(\"" + criterio + "\")";
            default:
                return "new UiSelector().textContains(\"" + escaparTexto(criterio) + "\")";
        }
    }

    private String escaparTexto(String texto) {
        if (texto == null) return "";
        return texto.replace("\"", "\\\"")
                .replace("\\", "\\\\")
                .replace("\n", "\\n");
    }

    private void realizarSwipe(AppiumDriver driver) {
        Dimension size = driver.manage().window().getSize();
        int centerX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        Sequence swipe = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger, Duration.ofMillis(200)))
                .addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), centerX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));
    }

    private void esperarUnMomento(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}