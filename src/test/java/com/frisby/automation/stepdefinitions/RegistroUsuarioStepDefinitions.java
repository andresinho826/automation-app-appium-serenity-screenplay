package com.frisby.automation.stepdefinitions;


import com.frisby.automation.questions.MensajeBienvenida;
import com.frisby.automation.tasks.NavegarARegistrarse;
import com.frisby.automation.tasks.RegistrarUsuario;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;


import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class RegistroUsuarioStepDefinitions {

    @Given("que el usuario está en la pantalla de Registrarse")
    public void queElUsuarioEstaEnLaPantallaDeRegistrarse() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                NavegarARegistrarse.enLaAplicacion()

        );
    }

    @When("ingresa los datos del formulario:")
    public void ingresaLosDatosDelFormulario(DataTable dataTable) {
        // Tomamos la primera fila de la tabla como Map<String, String>

        for (Map<String, String> fila : dataTable.asMaps(String.class, String.class)) {
            String documento = fila.get("Número de documento");
            String email = fila.get("Correo electrónico");
            String nombres = fila.get("Nombres");
            String apellidos = fila.get("Apellidos");
            String contrasena = fila.get("Contraseña");
            String contrasenaConfirmacion = fila.get("Confirmar contraseña");


            OnStage.theActorInTheSpotlight().attemptsTo(
                    RegistrarUsuario.conDatos(documento, email, nombres, apellidos, contrasena, contrasenaConfirmacion)


            );
        }
    }


    @When("hace clic en el botón Registrar")
    public void haceClicEnElBotónRegistrar() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                //RegistrarUsuario.hacerClicEn(boton)
        );
    }

    @Then("el sistema debería mostrar el mensaje Bienvenido")
    public void elSistemaDeberíaMostrarElMensajeBienvenido() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(MensajeBienvenida.esVisible(), equalTo(true))
        );
    }
}