package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import pageObjects.ListarMascotasPage;
import utils.TestContextSetup;

public class ListarMascotasStepDefinition {

    TestContextSetup testContextSetup;
    ListarMascotasPage listarPage;

    public ListarMascotasStepDefinition(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        listarPage = testContextSetup.pageObjectManager.getListarMascotasPage();
    }

    @When("usuario selecciona la opcion encontrar mascotas por tag")
    public void usuarioSeleccionaLaOpcionEncontrarMascotasPorTag() {
        listarPage.clickFindByTagButton();
        listarPage.clickTryItOutButton();
    }

    @And("usuario ingresa el {string} a buscar")
    public void usuarioIngresaElTagABuscar(String t) {
        listarPage.clickAddStringButton();
        listarPage.sendKeysFilterTextBox(t);
    }

    @And("usuario ejecuta la operacion de encontrar mascotas")
    public void usuarioEjecutaLaOperacionDeEncontrarMascotas() {
        listarPage.clickExecuteButton();
    }

    @And("usuario selecciona el {string} a buscar")
    public void usuarioSeleccionaElStatusABuscar(String s) {
        listarPage.selectStatus(s);
    }

    @When("usuario selecciona la opcion encontrar mascotas por status")
    public void usuarioSeleccionaLaOpcionEncontrarMascotasPorStatus() {
        listarPage.clickFindByStatusButton();
        listarPage.clickTryItOutButton();
    }
}


