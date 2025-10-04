package com.frisby.automation.ui.popups;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class PopUpInformacionPage {

    public static final Target BTN_OK_INFORMACION = Target.the("Pop up de informacion")
            .located(By.id("com.frisby.frisby:id/confirm_button"));
}
