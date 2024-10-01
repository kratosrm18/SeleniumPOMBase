package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.AuthPage;
import pageObjects.InnovaPage;
import utils.TestContextSetup;

public class MyStepdefs {
    TestContextSetup testContextSetup;
    InnovaPage innovaPage;

    public MyStepdefs(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        innovaPage = testContextSetup.pageObjectManager.getInnovaPage();
    }
    @Given("usuario ingresa a la web")
    public void usuarioIngresaALaWeb() {
        innovaPage.llenarEmailyClave();
    }

    @When("usuario selecciona residentado")
    public void usuarioSeleccionaResidentado() {
        innovaPage.seleccionarResi();

    }

    @And("usuario ingresa a Panopto en otra ventana")
    public void usuarioIngresaAPanoptoEnOtraVentana() {
        innovaPage.cambiarVentana();
        innovaPage.llenarUseryClave();
    }

    @And("usuario ingresa a videoteca mis clases")
    public void usuarioIngresaAVideotecaMisClases() throws InterruptedException {
        innovaPage.misClases();
    }

    @Then("usuario scrapea xd")
    public void usuarioScrapeaXd() {
        innovaPage.scrapStart();
    }
}
