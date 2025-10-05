package com.frisby.automation.ui.popups;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class PopUpAcompanaTuPedidoPage {

    public static final Target BTN_NO_GRACIAS = Target.the("Click en boton no gracias")
            .located(By.id("com.frisby.frisby:id/butNoGracias"));


    public static final Target BTN_AGREGAR_AL_CARRITO_UN_ACOMPANANTE = Target.the("Agregando postre como acompañante")
            .located(By.xpath("(//android.widget.ImageButton[@content-desc='TODO'])[1]"));


}
