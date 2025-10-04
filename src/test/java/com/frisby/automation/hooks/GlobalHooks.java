package com.frisby.automation.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.openqa.selenium.WebDriver;

public class GlobalHooks {
    private WebDriver driver;
    //private Actor cliente;

    @Before
    public void configurarEscenario() {
        // Configurar Screenplay
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("Cliente");
        OnStage.theActorInTheSpotlight().can(BrowseTheWeb.with(driver));
    }

    @After
    public void cerrarDriver() {

        System.out.println(">>> Finalizando escenario...");
        OnStage.drawTheCurtain();
        /*
        if (driver != null) {
            driver.quit();
        }

         */
    }
}
