package pageObjects;

import io.cucumber.java.en_old.Ac;
import org.openqa.selenium.WebDriver;
import stepDefinitions.ActualizarMascotaStepDefinition;

public class PageObjectManager {
    public WebDriver driver;
    public AuthPage authPage;
    public CrearMascotaPage crearMascotaPage;
    public ListarMascotasPage listarMascotasPage;
    public EliminarMascotaPage eliminarMascotaPage;
    public ActualizarMascotaPage actualizarMascotaPage;
    public InnovaPage innovaPage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public AuthPage getLoginPage() {
        authPage = new AuthPage(driver);
        return authPage;
    }

    public CrearMascotaPage getCrearMascotaPage() {
        crearMascotaPage = new CrearMascotaPage(driver);
        return crearMascotaPage;
    }

    public ListarMascotasPage getListarMascotasPage() {
        listarMascotasPage = new ListarMascotasPage(driver);
        return listarMascotasPage;
    }

    public EliminarMascotaPage getEliminarMascotasPage() {
        eliminarMascotaPage = new EliminarMascotaPage(driver);
        return eliminarMascotaPage;
    }

    public ActualizarMascotaPage getActualizarMascotasPage() {
        actualizarMascotaPage = new ActualizarMascotaPage(driver);
        return actualizarMascotaPage;
    }

    public InnovaPage getInnovaPage() {
        innovaPage = new InnovaPage(driver);
        return innovaPage;
    }
}
