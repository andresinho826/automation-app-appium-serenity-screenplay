package com.frisby.automation.tasks;

import com.frisby.automation.ui.IniciaSesionPage;
import com.frisby.automation.ui.PantallaInicioPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.questions.Visibility;

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

        // Verifica si el actor ya está en Home Page
        boolean estaEnHome = Visibility.of(IniciaSesionPage.MENSAJE_HOME).answeredBy(actor);

        if (estaEnHome) {
            System.out.println("El usuario ya está en sesión. Continuando flujo desde Home Page...");
            return; // No hace login nuevamente
        }

        // Verifica si está en la pantalla de inicio (botón iniciar sesión visible)
        boolean estaEnPantallaInicio = Visibility.of(PantallaInicioPage.iniciarSesion).answeredBy(actor);

        if (estaEnPantallaInicio) {
            System.out.println("Usuario no autenticado. Iniciando sesión...");
            actor.attemptsTo(
                    Click.on(PantallaInicioPage.iniciarSesion),
                    Enter.theValue(usuario).into(IniciaSesionPage.CAMPO_USUARIO),
                    Enter.theValue(contrasena).into(IniciaSesionPage.CAMPO_CONTRASENA),
                    Click.on(IniciaSesionPage.BOTON_INICIAR_SESION)
            );
        } else {
            System.out.println("No se reconoce la pantalla actual. No se ejecutó inicio de sesión.");
        }
    }
}
