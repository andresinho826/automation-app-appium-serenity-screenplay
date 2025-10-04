package com.frisby.automation.questions;

import com.frisby.automation.ui.RegistroUsuarioPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class MensajeBienvenida implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        return RegistroUsuarioPage.MENSAJE_BIENVENIDA.resolveFor(actor).isVisible();
    }

    public static MensajeBienvenida esVisible() {
        return new MensajeBienvenida();
    }
}

