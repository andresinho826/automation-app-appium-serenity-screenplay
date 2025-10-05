package com.frisby.automation.tasks;

import com.frisby.automation.interactions.Log;
import com.frisby.automation.interactions.ManejarPopUp;
import com.frisby.automation.interactions.ScrollHastaElementoYClick;
import com.frisby.automation.ui.CompraProductoPage;
import com.frisby.automation.ui.ConfirmarPedidoPage;
import com.frisby.automation.ui.popups.PopUpAcompanaTuPedidoPage;
import com.frisby.automation.ui.popups.PopUpHorarioAtencionPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.*;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ComprarProducto implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {


        actor.attemptsTo(

                WaitUntil.the(CompraProductoPage.SELECT_DIRECCION, isVisible()).forNoMoreThan(10).seconds(),
                Log.mensaje("..... ANTES DEL SELECCIONAR DIRRECCION"),
                Click.on(CompraProductoPage.SELECT_DIRECCION),
                Log.mensaje("DEPUES DEL SELECCIONAR DIRRECCION....."),
                // manejo de pop up horario de atencion
                ManejarPopUp.siAparece(PopUpHorarioAtencionPage.BTN_OK_HORARIO_ATENCION, PopUpHorarioAtencionPage.BTN_OK_HORARIO_ATENCION),
                Click.on(CompraProductoPage.SELECT_CATEGORIA),
                Click.on(CompraProductoPage.BTN_AGREGAR_AL_CARRITO),
                Click.on(CompraProductoPage.SELECT_ACOMPANIANTE_FRANCESA),
                Click.on(CompraProductoPage.SELECT_ACOMPANIANTE_SALSERO),
                //Uso del scroll
                ScrollHastaElementoYClick.alTarget(CompraProductoPage.SELECT_ACOMPANIANTE_BEBIDA),
                ScrollHastaElementoYClick.alTarget(CompraProductoPage.SELECT_ACOMPANIANTE_SALSA_SOBRES),
                Click.on(CompraProductoPage.BTN_AGREGAR),
                // ir a carrito de compra y confirmar pedido
                Click.on(CompraProductoPage.BTN_IR_CARRITO),
                Log.mensaje("PASANDO EL BOTON DE CARRITO DE COMPRAS"),
                Click.on(CompraProductoPage.BTN_CONFIRMAR_PEDIDO),
                Log.mensaje("HEMOS DADO CLICK EN EL BOTON DE PEDIDO DE CONFIRMACION EXITOSAMENTE"),
                ManejarPopUp.siAparece(PopUpHorarioAtencionPage.BTN_OK_HORARIO_ATENCION, PopUpHorarioAtencionPage.BTN_OK_HORARIO_ATENCION),

                Log.mensaje("HEMOS PASADO EL POP UP DE HORARIO DE ATENCION...."),
                ManejarPopUp.siAparece(PopUpAcompanaTuPedidoPage.BTN_NO_GRACIAS, PopUpAcompanaTuPedidoPage.BTN_NO_GRACIAS),
                //WaitUntil.the(CompraProductoPage.TXT_CONFIRMACION, isVisible()).forNoMoreThan(5).seconds()

                // confirmacion del pedido
                Click.on(ConfirmarPedidoPage.BUSCAR_NUMERO_TELEFONO),
                Click.on(ConfirmarPedidoPage.SELECT_NUMERO_TELEFONO),
                Click.on(ConfirmarPedidoPage.SELECT_FORMA_DE_PAGO),
                Click.on(ConfirmarPedidoPage.BTN_SIGUIENTE),
                Click.on(ConfirmarPedidoPage.BTN_FINALIZAR_PEDIDO),
                WaitUntil.the(CompraProductoPage.TXT_CONFIRMACION, isVisible()).forNoMoreThan(10).seconds(),
                Log.mensaje("HEMOS validado el mensaje de pedido enviado con exito...."),
                Click.on(CompraProductoPage.BTN_ACEPTAR_PEDIDO_ENVIADO_CON_EXITO),
                // El proceso finaliza cuando regresamos al home page
                WaitUntil.the(CompraProductoPage.BTN_DOMICILIO, isVisible()).forNoMoreThan(5).seconds()


        );
    }

    public static ComprarProducto enLaApp() {
        return instrumented(ComprarProducto.class);
    }
}
