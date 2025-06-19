abstract class Investimento {
    private Cliente cliente;
    private double saldo;
    private double taxaGanho;
    private double taxaEncargo;

    public Investimento(Cliente cliente, double saldo, double taxaGanho, double taxaEncargo) {
        this.cliente = cliente;
        this.saldo = saldo;
        this.taxaGanho = taxaGanho;
        this.taxaEncargo = taxaEncargo;
    }

    public void aplicar(double valor) {
        if(valor > 0) {
            this.saldo += valor;
        }
    }

    public void resgatar(double valor) {
        if(valor > 0 && this.saldo >= valor) {
            this.saldo -= valor;

        } else if(valor > 0 && this.saldo < valor) {
            this.saldo = 0;
        }
    }

    public double calcSaldoProjetadoBruto(double saldoAtual) {
        double saldoProjetadoBruto = saldoAtual * (1 + this.taxaGanho);
        return saldoProjetadoBruto;
    }

    public double calcGanhoMensal() {
        double ganhoMensal = this.saldo * this.taxaGanho;
        return ganhoMensal;
    }

    public abstract double calcularSaldoProjetado(int numeroMeses);
    public abstract void simularPassagemDeMes();

    // gets e sets
    public double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getTaxaGanho() {
        return taxaGanho;
    }

    public double getTaxaEncargo() {
        return taxaEncargo;
    }
}