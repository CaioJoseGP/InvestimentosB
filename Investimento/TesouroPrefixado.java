class TesouroPrefixado extends Investimento {
    private String nomeTitulo;

    public TesouroPrefixado(PessoaFisica cliente, double saldo, double taxaJurosAnual, String nomeTitulo) {
        super(cliente, saldo, taxaJurosAnual / 12, 0.15);
        this.nomeTitulo = nomeTitulo;
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
    public String getNomeTitulo() {
        return nomeTitulo;
    }

    public void setNomeTitulo(String nomeTitulo) {
        this.nomeTitulo = nomeTitulo;
    }
}