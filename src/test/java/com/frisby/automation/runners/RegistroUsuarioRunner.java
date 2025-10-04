package com.frisby.automation.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/registro_usuario.feature",
        glue = {"com.frisby.automation.stepdefinitions", "com.frisby.automation.hooks"},
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class RegistroUsuarioRunner {}
