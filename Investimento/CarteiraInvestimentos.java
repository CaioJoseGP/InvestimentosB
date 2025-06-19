import java.util.ArrayList;

public class CarteiraInvestimentos {
    private Cliente cliente;
    private double saldoTotal;
    private ArrayList<Investimento> investimentos;

    public CarteiraInvestimentos(Cliente cliente) {
        this.cliente = cliente;
        this.saldoTotal = 0;
        this.investimentos = new ArrayList<>();
    }

    public void adicionarInvestimento(Investimento investimento) {
        if(this.cliente.getIdentificadorDocumento().equals(investimento.getCliente().getIdentificadorDocumento())) {
            this.investimentos.add(investimento);
        }
    }

    public void removerInvestimento(Investimento investimento) {
        //TODO sobrescrever o método Hash e sla fazer a comparação de conteudo (?)
        this.investimentos.remove(investimento);
    }

    private double somarValorInvestimentos() {
        double total = 0;

        for(Investimento investimento : investimentos) {
            total += investimento.getSaldo();
        }

        return total;
    }

    public double calcularValorTotalInvestido() {
        this.saldoTotal = somarValorInvestimentos();
        return saldoTotal;
    }

    public void renderMensal() {
        for(Investimento investimento : investimentos) {
            investimento.simularPassagemDeMes();
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getSaldoTotal() {
        return saldoTotal;
    }

    public ArrayList<Investimento> getInvestimentos() {
        return investimentos;
    }
}
