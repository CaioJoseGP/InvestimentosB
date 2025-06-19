class Debenture extends Investimento {
    private String nomeEmpresaEmissora;

    public Debenture(PessoaJuridica cliente, double saldo, double taxaJurosAnual, double percentualTributacaoPJ, String nomeEmpresaEmissora) {
        super(cliente, saldo, taxaJurosAnual / 12, percentualTributacaoPJ);
        this.nomeEmpresaEmissora = nomeEmpresaEmissora;
    }

    @Override
    public double calcularSaldoProjetado(int numeroMeses) {
        double saldoProjetadoBruto = this.getSaldo();

        for(int i = 1; i <= numeroMeses; i++) {
            double saldoProjetadoAnterior = saldoProjetadoBruto;
            saldoProjetadoBruto = this.calcSaldoProjetadoBruto(saldoProjetadoAnterior);
        }

        double rendimentoBruto = saldoProjetadoBruto - this.getSaldo();
        double impostoDevido = rendimentoBruto * this.getTaxaEncargo();

        double saldoProjetadoLiquido = saldoProjetadoBruto - impostoDevido;
        return saldoProjetadoLiquido;
    }

    @Override
    public void simularPassagemDeMes() {
        super.aplicar(this.calcGanhoMensal());
    }

    // gets e sets
    public String getNomeEmpresaEmissora() {
        return nomeEmpresaEmissora;
    }

    public void setNomeEmpresaEmissora(String nomeEmpresaEmissora) {
        this.nomeEmpresaEmissora = nomeEmpresaEmissora;
    }
}