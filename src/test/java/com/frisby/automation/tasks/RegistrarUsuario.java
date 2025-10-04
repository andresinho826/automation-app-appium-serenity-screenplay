package com.frisby.automation.tasks;


import com.frisby.automation.interactions.ManejarPopUp;
import com.frisby.automation.ui.RegistroUsuarioPage;
import com.frisby.automation.ui.popups.PopUpCalificarAppPage;
import com.frisby.automation.ui.popups.PopUpGuardaContrasenaPage;
import com.frisby.automation.ui.popups.PopUpInformacionPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class RegistrarUsuario implements Task {


    private final String documento;
    private final String email;
    private final String nombres;
    private final String apellidos;
    private final String contrasena;
    private final String confirmarContrasena;

    public RegistrarUsuario(String documento, String email,
                            String nombres, String apellidos, String contrasena, String confirmarContrasena) {

        this.documento = documento;
        this.email = email;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.contrasena = contrasena;
        this.confirmarContrasena = confirmarContrasena;
    }

    public static RegistrarUsuario conDatos( String documento, String email,
                                            String nombres, String apellidos, String contrasena, String confirmarContrasena) {
        return Tasks.instrumented(RegistrarUsuario.class, documento, email,
                nombres, apellidos, contrasena, confirmarContrasena);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                // pop up informacion - OK
                ManejarPopUp.siAparece(PopUpInformacionPage.BTN_OK_INFORMACION,PopUpInformacionPage.BTN_OK_INFORMACION),


                //Click.on(RegistroUsuarioPage.SELECT_TIPO_DOCUMENTO),
                //Click.on(RegistroUsuarioPage.OPCION_TIPO_DOCUMENTO),
                Enter.theValue(documento).into(RegistroUsuarioPage.DOCUMENTO),
                Enter.theValue(email).into(RegistroUsuarioPage.EMAIL),
                Click.on(RegistroUsuarioPage.SELECT_FECHA_NACIMIENTO),
                Click.on(RegistroUsuarioPage.OPCION_FECHA_NACIMIENTO),
                Enter.theValue(nombres).into(RegistroUsuarioPage.NOMBRES),
                Enter.theValue(apellidos).into(RegistroUsuarioPage.APELLIDOS),
                Click.on(RegistroUsuarioPage.GENERO),
                Enter.theValue(contrasena).into(RegistroUsuarioPage.CONTRASENA),
                Enter.theValue(confirmarContrasena).into(RegistroUsuarioPage.CONFIRMAR_CONTRASENA),
                Click.on(RegistroUsuarioPage.POLITICAS),
                Click.on(RegistroUsuarioPage.BOTON_REGISTRAR),

                // pop up guardar contraseña
                ManejarPopUp.siAparece(PopUpGuardaContrasenaPage.BTN_GUARDAR,PopUpGuardaContrasenaPage.BTN_GUARDAR),

                // aqui vendria el otro popup te gusta la aplicacion frisby
                ManejarPopUp.siAparece(PopUpCalificarAppPage.BTN_NO_GRACIAS,PopUpCalificarAppPage.BTN_NO_GRACIAS),

                WaitUntil.the(RegistroUsuarioPage.MENSAJE_BIENVENIDA, isVisible()).forNoMoreThan(10).seconds()
        );
    }
}
