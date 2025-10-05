package com.frisby.automation.stepdefinitions;

import com.frisby.automation.questions.ConfirmacionPedido;
import com.frisby.automation.tasks.ComprarProducto;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import static org.hamcrest.core.IsEqual.equalTo;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;


public class CompraProductoStepDefinition {

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
                seeThat(ConfirmacionPedido.esVisible(), equalTo(true))
        );
    }
}
