package com.frisby.automation.stepdefinitions;

import com.frisby.automation.tasks.ComprarProducto;
import com.frisby.automation.tasks.IniciarSesion;
import com.frisby.automation.ui.CompraProductoPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.es.*;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.containsString;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

public class CompraProductoStepDefinition {

    @Given("que el usuario ha iniciado sesión correctamente")
    public void queElUsuarioHaIniciadoSesionCorrectamente() {
        // Reutilizar la tarea de inicio de sesión si ya existe
        OnStage.theActorCalled("Usuario").wasAbleTo(
                // Puedes llamar una Task existente, ejemplo:
                // IniciarSesion.conCredenciales("user", "pass")
                IniciarSesion.conCredenciales("andresqa91@gmail.com", "contrasena123")
        );
    }

    @And("está en la Home page")
    public void estaEnLaHomePage() {
        // Puedes validar o esperar la visibilidad de un elemento del home
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(CompraProductoPage.BTN_DOMICILIO, isVisible()).forNoMoreThan(10).seconds()
        );
    }

    @When("da click en el botón domicilio")
    @And("selecciona la dirección")
    @And("selecciona una categoría")
    @And("selecciona los acompañantes")
    @And("da click en el botón agregar")
    @And("se dirige al carrito de compra")
    @And("da click en el botón confirmar pedido")
    public void realizaElFlujoDeCompra() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ComprarProducto.enLaApp()
        );
    }

    @Then("el sistema confirma el pedido")
    public void elSistemaConfirmaElPedido() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(WebElementQuestion.the(CompraProductoPage.TXT_CONFIRMACION),
                        isVisible())
        );
    }
}
