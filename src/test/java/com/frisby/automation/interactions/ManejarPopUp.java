package com.frisby.automation.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;

public class ManejarPopUp implements Interaction {

    private final Target popup;
    private final Target botonCerrar;


    public ManejarPopUp(Target popup, Target botonCerrar) {
        this.popup = popup;
        this.botonCerrar = botonCerrar;
    }

    public static ManejarPopUp siAparece(Target popup, Target botonCerrar) {
        return Tasks.instrumented(ManejarPopUp.class, popup, botonCerrar);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        if (popup.resolveFor(actor).isVisible()) {
            System.out.println("Pop-up detectado, cerrando...");
            actor.attemptsTo(Click.on(botonCerrar));
        } else {
            System.out.println("No hay pop-up, continuando flujo normal...");
        }

    }
}



