package testes.restricoes;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Prova técnica de Testes de API")
public class RestricoesTest {
    @BeforeEach
    public void beforeEach(){

        //Configurando dados da API
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/api/v1";

    }

    @Test
    @DisplayName("Validar Consulta de CPF (Possui Restrições)")
    public void testValidarConsultaSucessoRestricoesDeCPF(){

        given()
        .when()
            .log().all()
            .get("/restricoes/97093236014")
        .then()
            .assertThat()
                //Mensagem exibida diferente da mensagem espera no Swagger e Documento com regras de negócio
                .body("mensagem", equalTo("O CPF 97093236014 possui restrição"))
                //Validando estrutura do response
                .body(JsonSchemaValidator.matchesJsonSchema(new File("JsonSchemaConsultaInexistente.json")))
                    .statusCode(200);

    }

    @Test
    @DisplayName("Validar Consulta de CPF (Não Possui de Restrições)")
    public void testValidarConsultaErroRestricoesDeCPF(){

        given()
        .when()
            .log().all()
            .get("/restricoes/87093236014")
        .then()
            .assertThat()
                //Validando Status HTTP
                .statusCode(204);

    }
}
