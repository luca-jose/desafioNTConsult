package testes.simulacoes;

import dataFactory.SimulacoesDataFactory;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.basePath;
import static org.hamcrest.Matchers.equalTo;

public class SimulacoesTest {

    public String cpf;

        @BeforeEach
        public void beforeEach() {

            //Configurando dados da API
            baseURI = "http://localhost";
            port = 8080;
            basePath = "/api/v1";

        }

        @Test
        @DisplayName("Validar que a Simulação foi Cadastrada")
        public void testValidarInsercaoSimulacao () {

            given()
                    .contentType(ContentType.JSON)
                    .body(SimulacoesDataFactory.adicionarSimulacao())
            .when()
                    .log().all()
                    .post("/simulacoes")
            .then()
                    .log().all()
                    .assertThat()
                    //validando status HTTP
                    .statusCode(201)
                    //Validando estrutura do response
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/JsonSchema.json"));

        }

        @Test
        @DisplayName("Validar que a Simulação Não foi Cadastrada (CPF já Cadastrado)")
        public void testValidarErroInsercaoSimulacaoCpfCadastrado () {

            given()
                    .contentType(ContentType.JSON)
                    .body(SimulacoesDataFactory.validarCpfDuplicado())
            .when()
                    .log().all()
                    .post("/simulacoes")
            .then()
                    .log().all()
                    .assertThat()
                    //Validando estrutura do response
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/JsonSchemaCpf.json"))
                    //Status code citado no Swagger e documentação errados, valor esperado 400.
                    .statusCode(409);

        }

        @Test
        @DisplayName("Validar que a Simulação Não foi Cadastrada (CPF com Pontuação)")
        public void testValidarErroInsercaoSimulacaoCpfPontuacao () {

            given()
                    .contentType(ContentType.JSON)
                    .body(SimulacoesDataFactory.validarCpfPontuacao())
            .when()
                    .log().all()
                    .post("/simulacoes")
            .then()
                    .log().all()
                    .assertThat()
                    //Validando estrutura do response
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/jsonSchemaCpf.json"))
                    //validando status HTTP
                    .statusCode(400);

        }

        @Test
        @DisplayName("Validar que a Simulação Não foi Cadastrada (Nome vazio)")
        public void testValidarErroInsercaoSimulacaoNomeVazio () {

            given()
                    .contentType(ContentType.JSON)
                    .body(SimulacoesDataFactory.validarNomeVazio())
            .when()
                    .log().all()
                    .post("/simulacoes")
            .then()
                    .log().all()
                    .assertThat()
                    //validando status HTTP
                    .statusCode(400);

        }

        @Test
        @DisplayName("Validar que a Simulação Não foi Cadastrada (Email Inválido)")
        public void testValidarErroInsercaoSimulacaoEmailInvalido () {

            given()
                    .contentType(ContentType.JSON)
                    .body(SimulacoesDataFactory.validarEmailInvalido())
            .when()
                    .log().all()
                    .post("/simulacoes")
            .then()
                    .log().all()
                    .assertThat()
                    //Validando estrutura do response
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/JsonSchemaEmail.json"))
                    //validando status HTTP
                    .statusCode(400);

        }

        @Test
        @DisplayName("Validar que a Simulação não foi Cadastrada (Valor < R$ 1.000)")
        public void testValidarErroInsercaoSimulacaoValorMenor () {

            given()
                    .contentType(ContentType.JSON)
                    .body(SimulacoesDataFactory.validarValorMenor())
            .when()
                    .log().all()
                    .post("/simulacoes")
            .then()
                    .log().all()
                    .assertThat()
                    //validando status HTTP
                    .statusCode(400)
                    //Validando estrutura do response
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/JsonSchemaValor.json"));

        }

        @Test
        @DisplayName("Validar que a Simulação não foi Cadastrada (Valor > R$ 40.000")
        public void testValidarErroInsercaoSimulacaoValorMaior () {

            given()
                    .contentType(ContentType.JSON)
                    .body(SimulacoesDataFactory.validarValorMaior())
            .when()
                    .log().all()
                    .post("/simulacoes")
            .then()
                    .log().all()
                    .assertThat()
                    //validando status HTTP
                    .statusCode(400)
                    //Validando estrutura do response
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/JsonSchemaValor.json"));

        }

        @Test
        @DisplayName("Validar que a Simulação Não foi Cadastrada (Nº Parcelas < 2)")
        public void testValidarErroInsercaoSimulacaoParcelasMenor () {

            given()
                    .contentType(ContentType.JSON)
                    .body(SimulacoesDataFactory.validarParcelasMenor())
            .when()
                    .log().all()
                    .post("/simulacoes")
            .then()
                    .log().all()
                    .assertThat()
                    //Validando estrutura do response
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/JsonSchemaParcelas.json"))
                    //validando status HTTP
                    .statusCode(400);

        }

        @Test
        @DisplayName("Validar que a Simulação Não foi Cadastrada (Nº Parcelas > 48)")
        public void testValidarErroInsercaoSimulacaoParcelasMaior () {

            given()
                    .contentType(ContentType.JSON)
                    .body(SimulacoesDataFactory.validarParcelasMaior())
            .when()
                    .log().all()
                    .post("/simulacoes")
            .then()
                    .log().all()
                    .assertThat()
                    //validando status HTTP
                    .statusCode(400)
                    //Validando estrutura do response
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/JsonSchemaParcelas.json"));

        }

        @Test
        @DisplayName("Validar Retorno de Todas as Simulações Existentes")
        public void testValidarSimulacoesExistentes () {
            given()
            .when()
                    .log().all()
                    .get("/simulacoes")
            .then()
                    .log().all()
                    .assertThat()
                    //Validando estrutura do response
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/JsonSchemaConsultaTodos.json"))
                    //validando status HTTP
                    .statusCode(200);

        }

        @Test
        @DisplayName("Validar Retorno de uma Consulta de Simulação Existente (Especifica)")
        public void testValidarSimulacoesExistentesEspecifica () {
            given()
            .when()
                    .log().all()
                    .get("/simulacoes/66414919004")
                    .then()
            .log().all()
                    .assertThat()
                    //Validando estrutura do response
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/JsonSchema.json"))
                    //validando status HTTP
                    .statusCode(200);

        }

        @Test
        @DisplayName("Validar Retorno de uma Consulta de Simulação (CPF Não Cadastrado)")
        public void testValidarErroSimulacoesExistentesEspecifica () {
            given()
            .when()
                    .log().all()
                    //Passando como parametro no IRL, CPF que não foi cadastrado na simulação (Inexistente)
                    .get("/simulacoes/23414135355")
            .then()
                    .assertThat()
                    //Validando response recebido com response esperadocampo "mensagem"
                    .body("mensagem", equalTo("CPF 23414135355 não encontrado"))
                    //validando estrutura do response
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/JsonSchemaConsultaInexistente.json"))
                    //validando status HTTP
                    .statusCode(404);

        }

        @Test
        @DisplayName("Validar que a Simulação foi Alterada")
        public void testValidarAlteracaoSimulacao () {

            given()
                    .contentType(ContentType.JSON)
                    .body(SimulacoesDataFactory.validarAlteracao())
                    .when()
                    .log().all()
                    .put("/simulacoes/66414919004")
                    .then()
                    .log().all()
                    .assertThat()
                    //validando status HTTP
                    .statusCode(200)
                    //Validando estrutura do response
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/JsonSchema.json"));

        }

        @Test
        @DisplayName("Validar que a Simulação não foi Alterada (CPF inválido)")
        public void testValidarNaoAlteracaoSimulacaoCpf () {

            given()
                    .contentType(ContentType.JSON)
                    .body(SimulacoesDataFactory.validarAlteracaoCpfInvalido())
            .when()
                    .log().all()
                    .put("/simulacoes/19626829001")
            .then()
                    .log().all()
                    .assertThat()
                    //validando status HTTP
                    .statusCode(404)
                    //Validando estrutura do response
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/JsonSchemaConsultaInexistente.json"));

        }

        @Test
        @DisplayName("Validar que a Simulação não foi Alterada (Nº Parcelas < 2)")
        public void testValidarNaoAlteracaoSimulacaoParcelasMenor () {

            given()
                    .contentType(ContentType.JSON)
                    .body(SimulacoesDataFactory.validarAlteracaoParcelasMenor())
            .when()
                    .log().all()
                    .put("/simulacoes/17822386034")
            .then()
                    .log().all()
                    .assertThat()
                    //validando status HTTP
                    .statusCode(400)
                    //Validando estrutura do response
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/JsonSchemaParcelas.json"));

        }

        @Test
        @DisplayName("Validar que a Simulação não foi Alterada (Nº Parcelas > 48)")
        public void testValidarNaoAlteracaoSimulacaoParcelasMaior () {

            given()
                    .contentType(ContentType.JSON)
                    .body(SimulacoesDataFactory.validarAlteracaoParcelasMaior())
            .when()
                    .log().all()
                    .put("/simulacoes/17822386034")
            .then()
                    .log().all()
                    .assertThat()
                    //validando status HTTP
                    .statusCode(400)
                    //Validando estrutura do response
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/JsonSchemaParcelas.json"));

        }

        @Test
        @DisplayName("Validar que a Simulação foi deletada")
        public void testValidarSimulacaoDeletada () {

            given()
            .when()
                    .log().all()
                    .delete("/simulacoes/" + 11)
            .then()
                    .log().all()
                    .assertThat()
                    //validando status HTTP
                    .statusCode(200);

        }

        @Test
        @DisplayName("Validar que a Simulação não foi deletada")
        public void testValidarSimulacaoNaoDeletada () {

            given()
            .when()
                    .log().all()
                    .delete("/simulacoes/" + 999999)
            .then()
                    .log().all()
                    .assertThat()
                    //validando status HTTP
                    .statusCode(404);

        }
}






