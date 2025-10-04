package com.frisby.automation.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CompraProductoPage {

    public static final Target BTN_DOMICILIO = Target.the("botón domicilio")
            .located(By.id("com.frisby.frisby:id/butNuevoPedido"));

    public static final Target SELECT_DIRECCION = Target.the("seleccionar dirección")
            .located(By.id("com.frisby.frisby:id/checkBoxDirecciones"));


    // pop up horario_de_atencion
    // id = com.frisby.frisby:id/confirm_button

    public static final Target SELECT_CATEGORIA = Target.the("seleccionar categoría")
            .located(By.xpath("(//android.widget.ImageView[@resource-id='com.frisby.frisby:id/ImagenTarjeta'])[4]"));



    public static final Target BTN_AGREGAR_AL_CARRITO = Target.the("agregar producto de la categoria al carrito")
            .located(By.xpath("(//android.widget.ImageButton[@content-desc='TODO'])[1]"));


    public static final Target SELECT_ACOMPANIANTE_FRANCESA = Target.the("seleccionar acompañante francesa")
            .located(By.xpath("(//android.widget.RadioButton[@resource-id='com.frisby.frisby:id/radioDiviDeta'])[1]"));

    public static final Target SELECT_ACOMPANIANTE_SALSERO = Target.the("seleccionar acompañante salsero")
            .located(By.xpath("(//android.widget.RadioButton[@resource-id='com.frisby.frisby:id/radioDiviDeta'])[3]"));


    public static final Target SELECT_ACOMPANIANTE_BEBIDA = Target.the("seleccionar acompañante bebida")
            .located(By.xpath("(//android.widget.RadioButton[@resource-id='com.frisby.frisby:id/radioDiviDeta'])[1]"));

    public static final Target SELECT_ACOMPANIANTE_SALSA_SOBRES = Target.the("seleccionar acompañante salsa sobres")
            .located(By.xpath("(//android.widget.RadioButton[@resource-id='com.frisby.frisby:id/radioDiviDeta'])[4]"));

    public static final Target BTN_AGREGAR = Target.the("botón agregar al carrito")
            .located(By.id("com.frisby.frisby:id/butAgregarDivi"));

    public static final Target BTN_IR_CARRITO = Target.the("botón ir al carrito")
            .located(By.id("com.frisby.frisby:id/butCarritoProductos"));

    public static final Target BTN_CONFIRMAR_PEDIDO = Target.the("botón confirmar pedido")
            .located(By.id("com.frisby.frisby:id/butConfirmarPedido"));


    // pop up horario_de_atencion
    // id = com.frisby.frisby:id/confirm_button

    public static final Target TXT_CONFIRMACION = Target.the("mensaje de confirmación del pedido")
            .located(By.id("com.frisby.frisby:id/confirm_button"));
}
