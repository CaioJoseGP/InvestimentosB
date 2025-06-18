import java.util.ArrayList;

public class CarteiraInvestimentos {
    private Cliente cliente;
    private double saldoTotal;
    private ArrayList<Investimento> investimentos;

    public CarteiraInvestimentos(Cliente cliente, ArrayList<Investimento> investimentos) {
        this.cliente = cliente;
        this.saldoTotal = 0;
        this.investimentos = new ArrayList<>(investimentos);
    }

    public void adicionarInvestimento(Investimento investimento) {
        if(this.cliente == investimento.getCliente()) {
            this.investimentos.add(investimento);
        }
    }

    public double calcularValorTotalInvestido() {
        double total = 0;

        for(Investimento investimento : investimentos) {
            total += investimento.getSaldo();
        }

        this.saldoTotal = total;
        return saldoTotal;
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

    public void setInvestimentos(ArrayList<Investimento> investimentos) {
        this.investimentos = new ArrayList<>(investimentos);
    }
}
