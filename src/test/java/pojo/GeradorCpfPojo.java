package pojo;

public class GeradorCpfPojo {
    public String gerarCPF(){

        long i = (long) (Math.random() * 9999999999999l);
        String cpf = Long.toString(i);

        return cpf;
    }

    public String gerarCPFPontuacao(){

        long i = (long)(Math.random() * 999);
        String cpfPontuacao = Long.toString(i);

        return cpfPontuacao;
    }
}
