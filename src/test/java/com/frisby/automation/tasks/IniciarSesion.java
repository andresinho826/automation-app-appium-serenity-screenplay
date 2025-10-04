package com.frisby.automation.tasks;

import com.frisby.automation.ui.IniciaSesionPage;
import com.frisby.automation.ui.PantallaInicioPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

public class IniciarSesion implements Task {

    private final String usuario;
    private final String contrasena;

    public IniciarSesion(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public static IniciarSesion conCredenciales(String usuario, String contrasena) {
        return Tasks.instrumented(IniciarSesion.class, usuario, contrasena);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(PantallaInicioPage.iniciarSesion),
                Enter.theValue(usuario).into(IniciaSesionPage.CAMPO_USUARIO),
                Enter.theValue(contrasena).into(IniciaSesionPage.CAMPO_CONTRASENA),
                Click.on(IniciaSesionPage.BOTON_INICIAR_SESION)
        );

    }
}
