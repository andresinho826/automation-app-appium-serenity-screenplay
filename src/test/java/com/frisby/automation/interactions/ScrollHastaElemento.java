package com.frisby.automation.interactions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScrollHastaElemento implements Interaction {

    private final String texto;
    private final boolean hacerClick;
    private final TipoBusqueda tipoBusqueda;

    public enum TipoBusqueda {
        TEXTO_EXACTO,
        TEXTO_CONTIENE,
        RESOURCE_ID,
        DESCRIPCION
    }

    public ScrollHastaElemento(String texto, boolean hacerClick, TipoBusqueda tipoBusqueda) {
        this.texto = texto;
        this.hacerClick = hacerClick;
        this.tipoBusqueda = tipoBusqueda;
    }

    public ScrollHastaElemento(String texto, boolean hacerClick) {
        this(texto, hacerClick, TipoBusqueda.TEXTO_CONTIENE);
    }

    public ScrollHastaElemento(String texto) {
        this(texto, true, TipoBusqueda.TEXTO_CONTIENE);
    }

    public static ScrollHastaElemento conTexto(String texto) {
        return Tasks.instrumented(ScrollHastaElemento.class, texto);
    }

    public static ScrollHastaElemento conTextoExacto(String texto) {
        return Tasks.instrumented(ScrollHastaElemento.class, texto, true, TipoBusqueda.TEXTO_EXACTO);
    }

    public static ScrollHastaElemento conTextoSinClick(String texto) {
        return Tasks.instrumented(ScrollHastaElemento.class, texto, false);
    }

    public static ScrollHastaElemento conResourceId(String resourceId) {
        return Tasks.instrumented(ScrollHastaElemento.class, resourceId, true, TipoBusqueda.RESOURCE_ID);
    }

    public static ScrollHastaElemento conDescripcion(String descripcion) {
        return Tasks.instrumented(ScrollHastaElemento.class, descripcion, true, TipoBusqueda.DESCRIPCION);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        if (!(driver instanceof AppiumDriver)) {
            throw new IllegalStateException("Este interaction requiere AppiumDriver para aplicaciones móviles");
        }

        AppiumDriver appiumDriver = (AppiumDriver) driver;

        try {
            WebElement elemento = buscarYScrollearElemento(appiumDriver);

            if (elemento != null && hacerClick) {
                elemento.click();
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(
                    "No se pudo encontrar el elemento con " + tipoBusqueda + ": '" + texto + "' después del scroll", e
            );
        }
    }

    private WebElement buscarYScrollearElemento(AppiumDriver appiumDriver) {
        String uiSelector = construirUiSelector();

        String scrollCommand = "new UiScrollable(new UiSelector().scrollable(true).instance(0))"
                + ".setMaxSearchSwipes(10)"
                + ".scrollIntoView(" + uiSelector + ")";

        return appiumDriver.findElement(MobileBy.AndroidUIAutomator(scrollCommand));
    }

    private String construirUiSelector() {
        switch (tipoBusqueda) {
            case TEXTO_EXACTO:
                return "new UiSelector().text(\"" + texto + "\")";

            case TEXTO_CONTIENE:
                return "new UiSelector().textContains(\"" + texto + "\")";

            case RESOURCE_ID:
                return "new UiSelector().resourceId(\"" + texto + "\")";

            case DESCRIPCION:
                return "new UiSelector().descriptionContains(\"" + texto + "\")";

            default:
                return "new UiSelector().textContains(\"" + texto + "\")";
        }
    }
}