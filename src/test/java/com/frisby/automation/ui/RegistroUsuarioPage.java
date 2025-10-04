package com.frisby.automation.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class RegistroUsuarioPage {


    // pop up informacion


    public static final Target SELECT_TIPO_DOCUMENTO = Target.the("flecha para desplegar los tipos de documentos")
            .located(By.id("com.frisby.frisby:id/ralativeLayoutTiposDocumentos"));

    public static final Target OPCION_TIPO_DOCUMENTO = Target.the("lista desplegable con todos los tipo de documento")
            .located(By.xpath("//android.widget.TextView[@resource-id='android:id/text1' and @text='Cedula de ciudadania']"));

    public static final Target DOCUMENTO = Target.the("campo documento")
            .located(By.id("com.frisby.frisby:id/txtDocumento"));

    public static final Target EMAIL = Target.the("campo correo electrónico")
            .located(By.id("com.frisby.frisby:id/txtEmail"));


    public static final Target SELECT_FECHA_NACIMIENTO = Target.the("campo fecha nacimiento")
            .located(By.id("com.frisby.frisby:id/imageView_arrow2"));

    public static final Target OPCION_FECHA_NACIMIENTO = Target.the("campo fecha nacimiento")
            .located(By.id("android:id/button1"));

    public static final Target NOMBRES = Target.the("campo nombres")
            .located(By.id("com.frisby.frisby:id/txtNombres"));

    public static final Target APELLIDOS = Target.the("campo apellidos")
            .located(By.id("com.frisby.frisby:id/txtApellidos"));

    public static final Target GENERO = Target.the("selector género")
            .located(By.id("com.frisby.frisby:id/radioMasculino"));

    public static final Target CONTRASENA = Target.the("campo contraseña")
            .located(By.id("com.frisby.frisby:id/txtClave"));

    public static final Target CONFIRMAR_CONTRASENA = Target.the("campo confirmar contraseña")
            .located(By.id("com.frisby.frisby:id/txtRepetirClave"));

    public static final Target POLITICAS = Target.the("checkbox políticas de privacidad")
            .located(By.id("com.frisby.frisby:id/switchPoliticas"));

    public static final Target BOTON_REGISTRAR = Target.the("botón registrar")
            .located(By.id("com.frisby.frisby:id/butRegistrarCuenta"));

    // GUARDAR CONTRASEÑA POP UP



    public static final Target MENSAJE_BIENVENIDA = Target.the("mensaje de bienvenida")
            .located(By.id("//android.widget.ImageView[@content-desc='logo']"));
}
