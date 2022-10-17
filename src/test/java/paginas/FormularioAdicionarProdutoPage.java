package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class FormularioAdicionarProdutoPage {
    private WebDriver navegador;

    public FormularioAdicionarProdutoPage(WebDriver navegador) {
        this.navegador = navegador;
    }

    public FormularioAdicionarProdutoPage informarProdutoNome(String produtoNome) {
        navegador.findElement(By.id("produtonome")).sendKeys(produtoNome);

        return this;
    }

    public FormularioAdicionarProdutoPage informarProdutoValor(String produtoValor) {
        navegador.findElement(By.id("produtovalor")).sendKeys(produtoValor);

        return this;
    }

    public FormularioAdicionarProdutoPage informarProdutoCores(String produtoCores) {
        navegador.findElement(By.id("produtocores")).sendKeys(produtoCores);

        return this;
    }

    public ListaDeProdutosPage submeterOFormularioDeAdicaoComErro() {
        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        return new ListaDeProdutosPage(navegador);
    }

    public EditarProdutoPage submeterOFormularioDeAdicao() {
        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        return new EditarProdutoPage(navegador);
    }
}
