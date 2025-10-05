package com.frisby.automation.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

public class Log implements Interaction {

    private final String mensaje;

    private Log(String mensaje) {
        this.mensaje = mensaje;
    }

    public static Log mensaje(String mensaje) {
        return new Log(mensaje);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        System.out.println(mensaje);
    }
}