class AcaoBolsa extends Investimento {
    private String codigoAcao;
    private String nomeEmpresa;

    public AcaoBolsa(Cliente cliente, double saldo, double taxaCorretagemFixaMensal, String codigoAcao, String nomeEmpresa) {
        super(cliente, saldo, 0.008, taxaCorretagemFixaMensal);
        this.codigoAcao = codigoAcao;
        this.nomeEmpresa = nomeEmpresa;
    }

    @Override
    public double calcularSaldoProjetado(int numeroMeses) {
        double saldoProjetadoBruto = this.getSaldo();

        for(int i = 1; i <= numeroMeses; i++) {
            double saldoProjetadoAnterior = saldoProjetadoBruto;
            saldoProjetadoBruto = this.calcSaldoProjetadoBruto(saldoProjetadoAnterior);
        }

        double totalTaxasCorretagem = this.getTaxaEncargo() * numeroMeses;
        double saldoProjetadoLiquido = saldoProjetadoBruto - totalTaxasCorretagem;
        
        if(saldoProjetadoLiquido < 0) {
            return 0;
        }

        return saldoProjetadoLiquido;
    }

    @Override
    public void simularPassagemDeMes() {
        super.aplicar(this.calcGanhoMensal());
        super.resgatar(this.getTaxaEncargo());
    }

    // gets e sets
    public String getCodigoAcao() {
        return codigoAcao;
    }

    public void setCodigoAcao(String codigoAcao) {
        this.codigoAcao = codigoAcao;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }
}