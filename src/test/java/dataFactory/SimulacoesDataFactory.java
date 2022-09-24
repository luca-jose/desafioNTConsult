package dataFactory;

import pojo.GeradorCpfPojo;
import pojo.SimulacoesPojo;

public class SimulacoesDataFactory {

    public static SimulacoesPojo adicionarSimulacao() {

        SimulacoesPojo insercao = new SimulacoesPojo();
        GeradorCpfPojo cpf = new GeradorCpfPojo();

        insercao.setNome("Luan");
        insercao.setCPF(cpf.gerarCPF());
        insercao.setEmail("fulano@gmail.com");
        insercao.setValor(11000.00);
        insercao.setParcelas(3);
        insercao.setSeguro(true);

        return insercao;
    }

    public static SimulacoesPojo validarCpfDuplicado() {

        SimulacoesPojo insercao = new SimulacoesPojo();

        insercao.setNome("Igor");
        insercao.setCPF("66414919004");
        insercao.setEmail("fulano@gmail.com");
        insercao.setValor(11000.00);
        insercao.setParcelas(3);
        insercao.setSeguro(true);

        return insercao;
    }

    public static SimulacoesPojo validarCpfPontuacao() {

        SimulacoesPojo simulacaoCpfInvalido = new SimulacoesPojo();
        GeradorCpfPojo geradorNumero = new GeradorCpfPojo();

        simulacaoCpfInvalido.setNome("Yuri");
        simulacaoCpfInvalido.setCPF(geradorNumero.gerarCPFPontuacao() + ".222.111-77");
        simulacaoCpfInvalido.setEmail("fulano@gmail.com");
        simulacaoCpfInvalido.setValor(1000.00);
        simulacaoCpfInvalido.setParcelas(2);
        simulacaoCpfInvalido.setSeguro(true);


        return simulacaoCpfInvalido;
    }

    public static SimulacoesPojo validarNomeVazio() {

        SimulacoesPojo nomeVazio = new SimulacoesPojo();
        GeradorCpfPojo cpf = new GeradorCpfPojo();

        nomeVazio.setNome("");
        nomeVazio.setCPF(cpf.gerarCPF());
        nomeVazio.setEmail("fulano@gmail.com");
        nomeVazio.setValor(11000.00);
        nomeVazio.setParcelas(3);
        nomeVazio.setSeguro(true);

        return nomeVazio;
    }

    public static SimulacoesPojo validarEmailInvalido() {

        SimulacoesPojo EmailInvalido = new SimulacoesPojo();
        GeradorCpfPojo cpf = new GeradorCpfPojo();

        EmailInvalido.setNome("Mateus");
        EmailInvalido.setCPF(cpf.gerarCPF());
        EmailInvalido.setEmail("email.com");
        EmailInvalido.setValor(10000.00);
        EmailInvalido.setParcelas(1);
        EmailInvalido.setSeguro(true);

        return EmailInvalido;
    }

    public static SimulacoesPojo validarParcelasMenor() {

        SimulacoesPojo parcelaMenor = new SimulacoesPojo();
        GeradorCpfPojo cpf = new GeradorCpfPojo();

        parcelaMenor.setNome("José");
        parcelaMenor.setCPF(cpf.gerarCPF());
        parcelaMenor.setEmail("email@email.com");
        parcelaMenor.setValor(1000.00);
        parcelaMenor.setParcelas(1);
        parcelaMenor.setSeguro(true);

        return parcelaMenor;
    }

    public static SimulacoesPojo validarParcelasMaior() {

        SimulacoesPojo parcelaMaior = new SimulacoesPojo();
        GeradorCpfPojo cpf = new GeradorCpfPojo();

        parcelaMaior.setNome("João");
        parcelaMaior.setCPF(cpf.gerarCPF());
        parcelaMaior.setEmail("email@email.com");
        parcelaMaior.setValor(1000.00);
        parcelaMaior.setParcelas(59);
        parcelaMaior.setSeguro(true);

        return parcelaMaior;
    }

    public static SimulacoesPojo validarValorMenor() {

        SimulacoesPojo valorMenor = new SimulacoesPojo();
        GeradorCpfPojo cpf = new GeradorCpfPojo();

        valorMenor.setNome("Carlos");
        valorMenor.setCPF(cpf.gerarCPF());
        valorMenor.setEmail("fulano@gmail.com");
        valorMenor.setValor(500.00);
        valorMenor.setParcelas(3);
        valorMenor.setSeguro(true);

        return valorMenor;
    }

    public static SimulacoesPojo validarValorMaior() {

        SimulacoesPojo valorMaior = new SimulacoesPojo();
        GeradorCpfPojo cpf = new GeradorCpfPojo();

        valorMaior.setNome("Carlos");
        valorMaior.setCPF(cpf.gerarCPF());
        valorMaior.setEmail("fulano@gmail.com");
        valorMaior.setValor(50000.00);
        valorMaior.setParcelas(3);
        valorMaior.setSeguro(true);

        return valorMaior;


    }

    public static SimulacoesPojo validarAlteracao() {

        SimulacoesPojo insercao = new SimulacoesPojo();
        GeradorCpfPojo cpf = new GeradorCpfPojo();

        insercao.setNome("Carlos");
        insercao.setCPF("66414919004");
        insercao.setEmail("fula@gmail.com");
        insercao.setValor(50000.00);
        insercao.setParcelas(3);
        insercao.setSeguro(true);

        return insercao;
    }

    public static SimulacoesPojo validarAlteracaoCpfInvalido() {

        SimulacoesPojo insercao = new SimulacoesPojo();
        GeradorCpfPojo cpf = new GeradorCpfPojo();

        insercao.setNome("Carlos");
        insercao.setCPF("17822353751");
        insercao.setEmail("fulano@gmail.com");
        insercao.setValor(50000.00);
        insercao.setParcelas(3);
        insercao.setSeguro(true);

        return insercao;
    }

    public static SimulacoesPojo validarAlteracaoParcelasMenor() {

        SimulacoesPojo parcelaMenor = new SimulacoesPojo();

        parcelaMenor.setNome("José");
        parcelaMenor.setCPF("17822386034");
        parcelaMenor.setEmail("email@email.com");
        parcelaMenor.setValor(1000.00);
        parcelaMenor.setParcelas(1);
        parcelaMenor.setSeguro(true);

        return parcelaMenor;
    }

    public static SimulacoesPojo validarAlteracaoParcelasMaior() {

        SimulacoesPojo parcelaMaior = new SimulacoesPojo();

        parcelaMaior.setNome("João");
        parcelaMaior.setCPF("17822386034");
        parcelaMaior.setEmail("email@email.com");
        parcelaMaior.setValor(1000.00);
        parcelaMaior.setParcelas(63);
        parcelaMaior.setSeguro(true);

        return parcelaMaior;
    }
}


