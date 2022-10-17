package modulos.produtos;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v85.log.Log;
import paginas.ListaDeProdutosPage;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Testes Web do Módulo de Produtos")
public class ProdutosTest {

    private WebDriver navegador;

    @BeforeEach
    public void beforeEach() {
        //abrir o navegador Chrome v106
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        this.navegador = new ChromeDriver();

        //vou maximizar a tela
        this.navegador.manage().window().maximize();

        //vou definir um tempo de espera padrão de 5 segundos
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //navegar para a página da Lojinha Web
        this.navegador.get("http://165.227.93.41/lojinha-web/v2/");
    }

    @Test
    @DisplayName("Não é permitido registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoComValorIgualAZero() {
        String mensagemApresentada = new LoginPage(navegador)
                .informarUsuario("admin")
                .informarSenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioDeAdicaoDeNovoProduto()
                .informarProdutoNome("XBOX ONE")
                .informarProdutoValor("000")
                .informarProdutoCores("preto, branco")
                .submeterOFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Não é permitido registrar um produto com valor acima de sete mil")
    public void testNaoEPermitidoRegistrarProdutoComValorAcimaDeSeteMil() {
        String mensagemApresenta = new LoginPage(navegador)
                .informarUsuario("admin")
                .informarSenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioDeAdicaoDeNovoProduto()
                .informarProdutoNome("XBOX ONE")
                .informarProdutoValor("700001")
                .informarProdutoCores("preto, branco")
                .submeterOFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();
        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresenta);
    }

    @Test
    @DisplayName("Posso adicionar produtos que estejam na faixa permitida entre R$0,01 e R$7000,01")
    public void testAdicionarProdutoALista() {
        String mensagemApresentada = new LoginPage(navegador)
                .informarUsuario("admin")
                .informarSenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioDeAdicaoDeNovoProduto()
                .informarProdutoNome("XBOX ONE")
                .informarProdutoValor("700000")
                .informarProdutoCores("preto, branco")
                .submeterOFormularioDeAdicao()
                .capturarMensagemCapturadaSucesso();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }

    @AfterEach
    public void afterEach() {
        //vou fechar o navegador
        this.navegador.quit();
    }

}
