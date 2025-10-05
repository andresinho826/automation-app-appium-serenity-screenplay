package com.frisby.automation.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

public class EsperarUnMomento implements Interaction {

    private final int milisegundos;

    private EsperarUnMomento(int milisegundos) {
        this.milisegundos = milisegundos;
    }

    public static EsperarUnMomento de(int milisegundos) {
        return new EsperarUnMomento(milisegundos);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        try {
            System.out.println(">>> Esperando " + milisegundos + "ms...");
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}