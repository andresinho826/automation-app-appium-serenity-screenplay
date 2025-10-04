package com.frisby.automation.ui.popups;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class PopUpGuardaContrasenaPage {

    public static final Target BTN_GUARDAR = Target.the("botón registrar")
            .located(By.xpath("//android.widget.Button[@resource-id='android:id/autofill_save_no']"));
}
