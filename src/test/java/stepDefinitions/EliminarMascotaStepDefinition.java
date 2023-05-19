package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import pageObjects.EliminarMascotaPage;
import utils.TestContextSetup;

public class EliminarMascotaStepDefinition {

    TestContextSetup testContextSetup;
    EliminarMascotaPage eliminarPage;

    public EliminarMascotaStepDefinition(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        eliminarPage = testContextSetup.pageObjectManager.getEliminarMascotasPage();
    }

    @When("usuario selecciona la opcion eliminar mascota")
    public void usuarioSeleccionaLaOpcionEliminarMascota() {
        eliminarPage.clickDeletePetButton();
        eliminarPage.clickTryItOutButton();
    }

    @And("usuario ingresa el {int} de la mascota a eliminar")
    public void usuarioIngresaElIdDeLaMascotaAEliminar(int id) {
        eliminarPage.sendKeysPetIdTextBox(id);
    }

    @And("usuario ejecuta la operacion eliminar")
    public void usuarioEjecutaLaOperacionEliminar() {
        eliminarPage.clickExecuteButton();
    }
}


