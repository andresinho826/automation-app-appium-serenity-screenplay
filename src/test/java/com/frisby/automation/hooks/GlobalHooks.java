package com.frisby.automation.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;
import org.openqa.selenium.WebDriver;

public class GlobalHooks {

    @Before(order = 0)
    public void configurarEscenario() {
        // Solo configurar el escenario
        // Serenity maneja automáticamente el driver y BrowseTheWeb
        OnStage.setTheStage(new OnlineCast());
    }


    /*
    @After
    public void cerrarEscenario() {
        // Solo necesitas cerrar el telón
        //OnStage.drawTheCurtain();
    }

     */

    @After
    public void cerrarAplicacion() {
        try {
            // Obtener el driver actual de Serenity
            WebDriver driver = ThucydidesWebDriverSupport.getDriver();

            if (driver != null) {
                System.out.println("Cerrando sesión y limpiando driver...");
                driver.quit(); // Cierra TODAS las ventanas y sesiones abiertas
            }

            // Limpia los actores para el siguiente escenario
            OnStage.drawTheCurtain();
            System.out.println("Escenario finalizado correctamente. Entorno limpio.");
        } catch (Exception e) {
            System.err.println("Error al cerrar la sesión del driver: " + e.getMessage());
        }
    }
}