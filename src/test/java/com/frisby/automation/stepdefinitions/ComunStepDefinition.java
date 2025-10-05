package com.frisby.automation.stepdefinitions;

import com.frisby.automation.tasks.IniciarSesion;
import com.frisby.automation.ui.CompraProductoPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ComunStepDefinition {

    @Given("que el usuario ha iniciado sesión correctamente")
    public void queElUsuarioHaIniciadoSesionCorrectamente() {
        // Reutilizar la tarea de inicio de sesión si ya existe
        OnStage.theActorCalled("Usuario").attemptsTo(
                IniciarSesion.conCredenciales("andresqa91@gmail.com", "contrasena123")
        );
    }

    @And("está en la Home page")
    public void estaEnLaHomePage() {
        // Puedes validar o esperar la visibilidad de un elemento del home
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(CompraProductoPage.BTN_DOMICILIO, isVisible()).forNoMoreThan(10).seconds(),
                Click.on(CompraProductoPage.BTN_DOMICILIO)
        );
    }


}
