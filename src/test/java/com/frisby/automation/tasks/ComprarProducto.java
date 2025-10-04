package com.frisby.automation.tasks;

import com.frisby.automation.interactions.ManejarPopUp;
import com.frisby.automation.interactions.ScrollHastaElemento;
import com.frisby.automation.interactions.ScrollYClick;
import com.frisby.automation.ui.CompraProductoPage;
import com.frisby.automation.ui.popups.PopUpHorarioAtencionPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ComprarProducto implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitUntil.the(CompraProductoPage.BTN_DOMICILIO, isVisible()).forNoMoreThan(10).seconds(),
                Click.on(CompraProductoPage.BTN_DOMICILIO),
                Click.on(CompraProductoPage.SELECT_DIRECCION),
                // aqui pop up horario de atencion - OK
                ManejarPopUp.siAparece(PopUpHorarioAtencionPage.BTN_OK_HORARIO_ATENCION, PopUpHorarioAtencionPage.BTN_OK_HORARIO_ATENCION),
                Click.on(CompraProductoPage.SELECT_CATEGORIA),
                Click.on(CompraProductoPage.BTN_AGREGAR_AL_CARRITO),
                Click.on(CompraProductoPage.SELECT_ACOMPANIANTE_FRANCESA),
                //HACER SCROLL A TODOS DE AQUI HACIA ABAJO
                ScrollYClick.en(CompraProductoPage.SELECT_ACOMPANIANTE_SALSERO),
                ScrollYClick.en(CompraProductoPage.SELECT_ACOMPANIANTE_BEBIDA),
                ScrollYClick.en(CompraProductoPage.SELECT_ACOMPANIANTE_SALSA_SOBRES),
                ScrollYClick.en(CompraProductoPage.BTN_AGREGAR),
                // ir a carrito de compra y confirmar pedido
                Click.on(CompraProductoPage.BTN_IR_CARRITO),
                Click.on(CompraProductoPage.BTN_CONFIRMAR_PEDIDO)
        );
    }

    public static ComprarProducto enLaApp() {
        return instrumented(ComprarProducto.class);
    }
}
