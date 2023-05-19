package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.ActualizarMascotaPage;
import utils.TestContextSetup;

public class ActualizarMascotaStepDefinition {

    TestContextSetup testContextSetup;
    ActualizarMascotaPage actPage;

    public ActualizarMascotaStepDefinition(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        actPage = testContextSetup.pageObjectManager.getActualizarMascotasPage();
    }

    @When("usuario selecciona la opcion actualizar mascota")
    public void usuarioSeleccionaLaOpcionActualizarMascota() {
        actPage.clickUpdatePetButton();
        actPage.clickTryItOutButton();
    }

    @And("usuario ingresa los datos necesarios para actualizar la mascota")
    public void usuarioIngresaLosDatosNecesariosParaActualizarLaMascota() {
    }

    @And("usuario ejecuta la operacion actualizar")
    public void usuarioEjecutaLaOperacionActualizar() {
        actPage.clickExecuteButton();
    }
}


