package com.frisby.automation.interactions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.actions.Click;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class ScrollYClick implements Interaction {

    private final Target elemento;
    private final int maxScrolls;

    public ScrollYClick(Target elemento, int maxScrolls) {
        this.elemento = elemento;
        this.maxScrolls = maxScrolls;
    }

    public ScrollYClick(Target elemento) {
        this(elemento, 10);
    }

    public static ScrollYClick en(Target elemento) {
        return Tasks.instrumented(ScrollYClick.class, elemento);
    }

    public static ScrollYClick en(Target elemento, int maxScrolls) {
        return Tasks.instrumented(ScrollYClick.class, elemento, maxScrolls);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        AppiumDriver appiumDriver = (AppiumDriver) driver;

        // Intentar encontrar el elemento haciendo scroll
        if (!isElementVisible(actor)) {
            scrollHastaEncontrarElemento(appiumDriver, actor);
        }

        // Hacer click en el elemento
        actor.attemptsTo(Click.on(elemento));
    }

    private boolean isElementVisible(Actor actor) {
        try {
            WebElementFacade element = elemento.resolveFor(actor);
            return element.isVisible();
        } catch (Exception e) {
            return false;
        }
    }

    private void scrollHastaEncontrarElemento(AppiumDriver driver, Actor actor) {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);

        TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);

        for (int i = 0; i < maxScrolls; i++) {
            if (isElementVisible(actor)) {
                return;
            }

            touchAction
                    .press(PointOption.point(startX, startY))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                    .moveTo(PointOption.point(startX, endY))
                    .release()
                    .perform();

            // Pequeña pausa entre scrolls
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}