package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditarProdutoPage {
    private WebDriver navegador;

    public EditarProdutoPage(WebDriver navegador) {
        this.navegador = navegador;
    }

    public String capturarMensagemCapturadaSucesso() {
        return navegador.findElement(By.cssSelector(".toast.rounded")).getText();
    }
}
