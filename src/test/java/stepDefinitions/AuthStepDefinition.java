package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageObjects.AuthPage;
import utils.Constants;
import utils.TestContextSetup;

public class AuthStepDefinition {
    TestContextSetup testContextSetup;
    AuthPage authPage;

    public AuthStepDefinition(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        authPage = testContextSetup.pageObjectManager.getLoginPage();
    }

    @Given("usuario se autentica")
    public void usuarioSeAutentica() {
        authPage.clickAuthorizeButton();
        authPage.typeApiKey(Constants.API_KEY);
        authPage.clickModalAuthorizeButton();
        authPage.clickModalCloseButton();
    }
}
