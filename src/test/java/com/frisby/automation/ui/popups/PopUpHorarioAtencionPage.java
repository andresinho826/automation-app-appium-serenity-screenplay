package com.frisby.automation.ui.popups;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class PopUpHorarioAtencionPage {

    public static final Target BTN_OK_HORARIO_ATENCION = Target.the("botón OK del pop-up")
            .located(By.id("com.frisby.frisby:id/confirm_button"));
}
