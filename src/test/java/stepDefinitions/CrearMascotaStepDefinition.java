package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pageObjects.CrearMascotaPage;
import utils.TestContextSetup;

public class CrearMascotaStepDefinition {

    TestContextSetup testContextSetup;
    CrearMascotaPage crearPage;

    public CrearMascotaStepDefinition(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        crearPage = testContextSetup.pageObjectManager.getCrearMascotaPage();
    }

    @When("usuario selecciona la opcion agregar nueva mascota")
    public void usuarioSeleccionaLaOpcionAgregarNuevaMascota() {
        crearPage.clickAddPetButton();
        crearPage.clickTryItOutButton();
    }

    @Then("se recibe una respuesta exitosa del servidor")
    public void seRecibeUnaRespuestaExitosaDelServidor() {
        Assert.assertEquals("La respuesta del servidor no fue exitosa (200)",200,crearPage.getResponseCode());
    }

    @Then("se recibe una respuesta fallida del servidor")
    public void seRecibeUnaRespuestaFallidaDelServidor() {
        Assert.assertEquals("La respuesta del servidor fue exitosa (200)",400,crearPage.getResponseCode());
    }

    @And("usuario selecciona {int} de la mascota")
    public void usuarioSeleccionaIdDeLaMascota(int id) {
        crearPage.setPetId(id);
    }

    @And("usuario selecciona {string} erroneo de la mascota")
    public void usuarioSeleccionaIdErroneoDeLaMascota(String id) {
        crearPage.setWrongPetId(id);
    }

    @And("usuario introduce el {string} de la mascota")
    public void usuarioIntroduceElNombreDeLaMascota(String name) {
        crearPage.setPetName(name);
    }

    @And("usuario selecciona {int} de la cateogria")
    public void usuarioSeleccionaCategoryIdDeLaCateogria(int catId) {
        crearPage.setCategoryId(catId);
    }

    @And("usuario selecciona {string} de la categoria")
    public void usuarioSeleccionaNombreCategoriaDeLaCategoria(String category) {
        crearPage.setCategoryName(category);
    }

    @And("usuario ingresa el {string} de la foto de la mascota")
    public void usuarioIngresaElUrlDeLaFotoDeLaMascota(String photo) {
        crearPage.setPhotoUrl(photo);
    }

    @And("usuario ingresa {string} de la mascota")
    public void usuarioIngresaTagDeLaMascota(String tag) {
        crearPage.setTag(tag);
    }

    @And("usuario selecciona el {string} de la mascota")
    public void usuarioSeleccionaElStatusDeLaMascota(String status) {
        crearPage.setStatus(status);
    }

    @And("usuario limpia los parametros por defecto")
    public void usuariolimpialosparametrospordefecto() {
        crearPage.clearParams();
    }

    @And("usuario establece los parametros")
    public void usuarioestableceparametros() {
        crearPage.sendKeysParams();
    }

    @And("usuario ejecuta la operacion de creacion")
    public void usuarioEjecutaLaOperacionDeCreacion() {
        crearPage.clickExecuteButton();
    }
}


