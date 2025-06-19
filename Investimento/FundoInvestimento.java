class FundoInvestimento extends Investimento {
    private String nomeFundo;
    private String cnpjGestora;

    public FundoInvestimento(Cliente cliente, double saldo, double rendimentoMensalBruto, double taxaAdmAnual, String nomeFundo, String cnpjGestora) {
        super(cliente, saldo, rendimentoMensalBruto, taxaAdmAnual / 12);
        this.nomeFundo = nomeFundo;
        this.cnpjGestora = cnpjGestora;
    }

    private double calcEncargoTotal(double saldoAnterior) {
        double taxaEncargoTotal = saldoAnterior * this.getTaxaEncargo();
        return taxaEncargoTotal;
    }

    @Override
    public double calcularSaldoProjetado(int numeroMeses) {
        if(numeroMeses == 0) {
            return super.getSaldo();
        }

        //TODO tentar fazer sem for e sem recursiva - estudar formulas

        double saldoAnterior = this.calcularSaldoProjetado(numeroMeses - 1);
        double saldoProjetadoLiquido = super.calcSaldoProjetadoBruto(saldoAnterior) - this.calcEncargoTotal(saldoAnterior);

        return saldoProjetadoLiquido;
    }

    @Override
    public void simularPassagemDeMes() {
        super.aplicar(this.calcGanhoMensal());
        super.resgatar(this.getSaldo() * (this.getTaxaEncargo()));
    }

    // gets e sets
    public String getNomeFundo() {
        return nomeFundo;
    }

    public void setNomeFundo(String nomeFundo) {
        this.nomeFundo = nomeFundo;
    }

    public String getCnpjGestora() {
        return cnpjGestora;
    }
}