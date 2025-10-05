package com.frisby.automation.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
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
        return new ManejarPopUp(popup, botonCerrar);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        try {
            // Esperar un momento breve para que aparezca el popup
            Thread.sleep(500);

            // Verificar si el elemento existe y es visible SIN esperar mucho
            if (esPopupVisible(actor)) {
                System.out.println(">>> Pop-up detectado: " + popup.getName() + ", cerrando...");
                actor.attemptsTo(Click.on(botonCerrar));

                // Esperar a que el popup se cierre
                Thread.sleep(500);
                System.out.println(">>> Pop-up cerrado");
            } else {
                System.out.println(">>> No hay pop-up, continuando flujo normal...");
            }
        } catch (Exception e) {
            System.out.println(">>> Pop-up no presente o error al cerrar, continuando...");
        }
    }

    private boolean esPopupVisible(Actor actor) {
        try {
            // isCurrentlyVisible() NO espera, solo verifica en el momento
            return popup.resolveFor(actor).isCurrentlyVisible();
        } catch (Exception e) {
            return false;
        }
    }
}