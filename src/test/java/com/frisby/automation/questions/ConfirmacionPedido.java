package com.frisby.automation.questions;

import com.frisby.automation.ui.CompraProductoPage;
import com.frisby.automation.ui.RegistroUsuarioPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ConfirmacionPedido implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        return CompraProductoPage.TXT_CONFIRMACION.resolveFor(actor).isVisible();
    }

    public static ConfirmacionPedido esVisible() {
        return new ConfirmacionPedido();
    }
}

