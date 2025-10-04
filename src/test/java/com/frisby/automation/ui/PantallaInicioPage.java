package com.frisby.automation.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class PantallaInicioPage {

    public static Target registrate = Target.the("Boton Registrate").located(By.id("com.frisby.frisby:id/button1"));

    public static Target iniciarSesion = Target.the("Boton Iniciar Sesion").located(By.id("com.frisby.frisby:id/button2"));

    public static Target RecuperarContrasena = Target.the("Recuperar Contraseña Link").located(By.id("com.frisby.frisby:id/textView"));


}
