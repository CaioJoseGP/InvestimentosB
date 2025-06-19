import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        // Define o Locale para usar o padrão brasileiro de formatação de números
        Locale.setDefault(new Locale("pt", "BR"));

        System.out.println("--- INICIANDO SIMULAÇÃO FINANCEIRA ---");
        System.out.println("========================================\n");

        // --- Criação do Cliente Pessoa Física e sua Carteira ---
        PessoaFisica clientePF = new PessoaFisica("João Silva", "joao.silva@email.com", "123.456.789-00");
        CarteiraInvestimentos carteiraPF = new CarteiraInvestimentos(clientePF);
        System.out.println("Cliente Pessoa Física criado: " + clientePF.getNome());

        // --- Criação e Adição de Investimentos para Pessoa Física ---
        TesouroPrefixado tesouro = new TesouroPrefixado(clientePF, 10000.00, 0.12, "Tesouro Prefixado 2029");
        FundoInvestimento fundo = new FundoInvestimento(clientePF, 5000.00, 0.01, 0.02, "Fundo Multimercado Alpha", "11.222.333/0001-44");
        
        carteiraPF.adicionarInvestimento(tesouro);
        carteiraPF.adicionarInvestimento(fundo);
        System.out.println("Investimentos adicionados à carteira de " + clientePF.getNome());
        exibirPatrimonio(carteiraPF, 0);

        // --- Demonstração de Aplicação e Resgate ---
        System.out.println("\n--- Operações de Aplicação e Resgate ---");
        System.out.printf("Saldo inicial do Tesouro: R$%,.2f\n", tesouro.getSaldo());
        tesouro.aplicar(2000.00);
        System.out.printf("Saldo do Tesouro após aplicação de R$2.000,00: R$%,.2f\n", tesouro.getSaldo());
        tesouro.resgatar(500.00);
        System.out.printf("Saldo do Tesouro após resgate de R$500,00: R$%,.2f\n", tesouro.getSaldo());
        exibirPatrimonio(carteiraPF, 0);


        System.out.println("\n----------------------------------------\n");


        // --- Criação do Cliente Pessoa Jurídica e sua Carteira ---
        PessoaJuridica clientePJ = new PessoaJuridica("Tech Solutions S.A.", "contato@techsolutions.com", "98.765.432/0001-10");
        CarteiraInvestimentos carteiraPJ = new CarteiraInvestimentos(clientePJ);
        System.out.println("Cliente Pessoa Jurídica criado: " + clientePJ.getNome());

        // --- Criação e Adição de Investimentos para Pessoa Jurídica ---
        Debenture debenture = new Debenture(clientePJ, 50000.00, 0.15, 0.20, "Debênture Infra Tech");
        AcaoBolsa acao = new AcaoBolsa(clientePJ, 20000.00, 15.00, "TSOL4", "Tech Solutions S.A.");
        
        carteiraPJ.adicionarInvestimento(debenture);
        carteiraPJ.adicionarInvestimento(acao);
        System.out.println("Investimentos adicionados à carteira de " + clientePJ.getNome());
        exibirPatrimonio(carteiraPJ, 0);


        // --- Simulação da Passagem de Meses ---
        System.out.println("\n--- SIMULANDO PASSAGEM DE TEMPO ---");
        for (int i = 1; i <= 3; i++) {
            System.out.println("\n---------------- Mês " + i + " ----------------");
            // Render mensal para as carteiras
            carteiraPF.renderMensal();
            carteiraPJ.renderMensal();

            System.out.println(">> Carteira de " + clientePF.getNome() + ":");
            exibirPatrimonio(carteiraPF, i);

            System.out.println("\n>> Carteira de " + clientePJ.getNome() + ":");
            exibirPatrimonio(carteiraPJ, i);
        }
        System.out.println("========================================\n");


        // --- Teste de Validações e Restrições ---
        System.out.println("--- TESTANDO RESTRIÇÕES DE CLIENTE ---");
        System.out.println("Tentando criar Debênture para Pessoa Física...");
        // A linha abaixo causaria um erro de compilação, pois o construtor de Debenture
        // espera um objeto PessoaJuridica, não PessoaFisica.
        // Isso demonstra a restrição em tempo de compilação.
        // Debenture debentureInvalida = new Debenture(clientePF, 10000.00, 0.15, 0.20, "Debênture Inválida");
        System.out.println("-> SUCESSO: O código não compila ao tentar criar Debênture para Pessoa Física, como esperado.");

        System.out.println("\nTentando criar Tesouro Prefixado para Pessoa Jurídica...");
        // A linha abaixo também causaria um erro de compilação pelo mesmo motivo.
        // O construtor de TesouroPrefixado espera um objeto PessoaFisica.
        // TesouroPrefixado tesouroInvalido = new TesouroPrefixado(clientePJ, 10000.00, 0.12, "Tesouro Inválido");
        System.out.println("-> SUCESSO: O código não compila ao tentar criar Tesouro Prefixado para Pessoa Jurídica, como esperado.");
        
        System.out.println("\n--- SIMULAÇÃO FINALIZADA ---");
    }

    /**
     * Exibe o patrimônio detalhado e consolidado de uma carteira de investimentos.
     * @param carteira A carteira de investimentos a ser exibida.
     * @param mes O número do mês atual na simulação (0 para estado inicial).
     */
    public static void exibirPatrimonio(CarteiraInvestimentos carteira, int mes) {
        String estado = (mes == 0) ? "Estado Inicial" : "Após Mês " + mes;
        System.out.println("\n--- Patrimônio Consolidado (" + estado + ") ---");
        System.out.println("Cliente: " + carteira.getCliente().getNome());
        
        // Exibe o saldo de cada investimento individualmente
        for (Investimento inv : carteira.getInvestimentos()) {
             System.out.printf("   - Saldo em %s: R$%,.2f\n", inv.getClass().getSimpleName(), inv.getSaldo());
        }

        // Calcula e exibe o valor total consolidado
        double total = carteira.calcularValorTotalInvestido();
        System.out.printf(">>> PATRIMÔNIO TOTAL CONSOLIDADO: R$%,.2f\n", total);
    }
}