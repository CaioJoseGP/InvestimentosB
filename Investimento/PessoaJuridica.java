public class PessoaJuridica extends Cliente {
    private String cnpj;

    public PessoaJuridica(String nome, String email, String cnpj) {
        super(nome, email);
        this.cnpj = cnpj;
    }

    @Override
    public String getIdentificadorDocumento() {
        return this.cnpj;
    }
}
