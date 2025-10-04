package com.frisby.automation.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class IniciaSesionPage {

    public static final Target CAMPO_USUARIO = Target.the("campo de correo o documento")
            .located(By.id("com.frisby.frisby:id/txtDocumentoEmailSesion"));

    public static final Target CAMPO_CONTRASENA = Target.the("campo de contraseña")
            .located(By.id("com.frisby.frisby:id/txtPasswordSesion"));

    public static final Target BOTON_INICIAR_SESION = Target.the("botón Iniciar sesión")
            .located(By.id("com.frisby.frisby:id/butIngresarSesion"));

    public static final Target MENSAJE_HOME = Target.the("mensaje o elemento visible del home")
            .located(By.id("com.frisby.frisby:id/butMiCuenta"));
}
