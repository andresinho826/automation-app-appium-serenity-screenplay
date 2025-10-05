package com.frisby.automation.stepdefinitions;

import com.frisby.automation.tasks.IniciarSesion;
import com.frisby.automation.ui.IniciaSesionPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;

public class IniciaSesionStepDefinition {

    @Given("que el usuario está en la pantalla de Inicio de sesion")
    public void queElUsuarioEstaEnLaPantallaDeInicioDeSesion() {
        OnStage.theActorCalled("Usuario");
    }

    @When("ingresa credenciales {string} {string}")
    public void ingresaCredenciales(String usuario, String contrasena) {

        OnStage.theActorInTheSpotlight().attemptsTo(
                IniciarSesion.conCredenciales(usuario, contrasena)
        );
    }

    @When("hace clic en el botón inicia sesion")
    public void haceClicEnElBotonIniciaSesion() {
        // Ya se hace dentro del Task
    }

    @Then("el sistema debería iniciar sesion exitosamente")
    public void elSistemaDeberiaMostrarElHomePage() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Ensure.that(IniciaSesionPage.MENSAJE_HOME).isDisplayed()
        );
    }
}
