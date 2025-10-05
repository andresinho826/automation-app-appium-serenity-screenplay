package com.frisby.automation.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ConfirmarPedidoPage {

    public static final Target BUSCAR_NUMERO_TELEFONO = Target.the("buscar el numero de telefono")
            .located(By.id("com.frisby.frisby:id/butBuscarTelefono"));


    public static final Target SELECT_NUMERO_TELEFONO = Target.the("seleccionar el numero de telefono")
            .located(By.id("com.frisby.frisby:id/checkBoxTelefono"));


    public static final Target SELECT_FORMA_DE_PAGO = Target.the("seleccionar la forma de pago")
            .located(By.id("com.frisby.frisby:id/radioEfectivo"));

    public static final Target BTN_SIGUIENTE = Target.the("click en el boton siguiente")
            .located(By.id("com.frisby.frisby:id/butSiguienteConfirmar"));

    public static final Target BTN_FINALIZAR_PEDIDO = Target.the("click en el boton siguiente")
            .located(By.id("com.frisby.frisby:id/butFinalizarPedido"));


}
