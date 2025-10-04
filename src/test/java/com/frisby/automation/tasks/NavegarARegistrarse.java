package com.frisby.automation.tasks;

import com.frisby.automation.ui.PantallaInicioPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

public class NavegarARegistrarse implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(PantallaInicioPage.registrate)

        );

    }

    public static Performable enLaAplicacion() {
        return new NavegarARegistrarse();
    }
}
