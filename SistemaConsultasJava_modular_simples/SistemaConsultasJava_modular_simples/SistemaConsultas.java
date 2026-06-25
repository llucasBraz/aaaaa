import java.time.LocalDateTime;
import java.util.List;

public class SistemaConsultas {
    private EntradaDados entrada;
    private ConsultaPrinter printer;
    private ConsultaService service;

    public SistemaConsultas() {
        Repositorio<Consulta> repositorio = new Repositorio<>();
        entrada = new EntradaDados();
        printer = new ConsultaPrinter();
        service = new ConsultaService(repositorio);
    }

    public void iniciar() {
        service.criarAutomaticamente5Consultas();

        System.out.println("===== CONSULTAS CADASTRADAS AUTOMATICAMENTE =====");
        printer.exibirLista(service.listarConsultas());

        int opcao;

        do {
            mostrarMenu();
            opcao = entrada.lerInteiro("Escolha uma opção: ");

            try {
                executarOpcao(opcao);
            } catch (ConsultaNaoEncontradaException e) {
                System.out.println(e.getMessage());
            } catch (HorarioIndisponivelException e) {
                System.out.println(e.getMessage());
            }

        } while (opcao != 0);
    }

    private void mostrarMenu() {
        System.out.println("\n===== MENU =====");
        System.out.println("1 - Cadastrar consultas");
        System.out.println("2 - Remover consultas");
        System.out.println("3 - Filtrar consultas por especialidade");
        System.out.println("4 - Filtrar consultas com valor acima de um valor informado");
        System.out.println("5 - Reagendar uma consulta");
        System.out.println("6 - Ordenar consultas por data");
        System.out.println("7 - Gerar relatório resumido das consultas");
        System.out.println("8 - Cancelar uma consulta");
        System.out.println("9 - Exibir todas as consultas cadastradas");
        System.out.println("0 - Sair");
    }

    private void executarOpcao(int opcao)
            throws ConsultaNaoEncontradaException, HorarioIndisponivelException {
        switch (opcao) {
            case 1:
                cadastrarConsulta();
                break;
            case 2:
                removerConsulta();
                break;
            case 3:
                filtrarPorEspecialidade();
                break;
            case 4:
                filtrarPorValor();
                break;
            case 5:
                reagendarConsulta();
                break;
            case 6:
                ordenarPorData();
                break;
            case 7:
                gerarRelatorioResumido();
                break;
            case 8:
                cancelarConsulta();
                break;
            case 9:
                exibirTodasConsultas();
                break;
            case 0:
                System.out.println("Sistema encerrado.");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private void cadastrarConsulta() throws HorarioIndisponivelException {
        int codigo = entrada.lerInteiro("Código: ");
        String nome = entrada.lerTexto("Nome do paciente: ");
        Especialidade especialidade = entrada.lerEspecialidade();
        LocalDateTime data = entrada.lerData("Data da consulta (dd/MM/yyyy HH:mm): ");
        double valor = entrada.lerDouble("Valor da consulta: ");

        service.cadastrarConsulta(codigo, nome, especialidade, data, valor);

        System.out.println("Consulta cadastrada com sucesso.");
    }

    private void removerConsulta() throws ConsultaNaoEncontradaException {
        int codigo = entrada.lerInteiro("Código da consulta para remover: ");

        service.removerConsulta(codigo);

        System.out.println("Consulta removida com sucesso.");
    }

    private void filtrarPorEspecialidade() {
        Especialidade especialidade = entrada.lerEspecialidade();

        List<Consulta> consultas = service.filtrarPorEspecialidade(especialidade);

        printer.exibirLista(consultas);
    }

    private void filtrarPorValor() {
        double valor = entrada.lerDouble("Informe o valor mínimo: ");

        List<Consulta> consultas = service.filtrarPorValorAcima(valor);

        printer.exibirLista(consultas);
    }

    private void reagendarConsulta()
            throws ConsultaNaoEncontradaException, HorarioIndisponivelException {
        int codigo = entrada.lerInteiro("Código da consulta para reagendar: ");
        LocalDateTime novaData = entrada.lerData("Nova data da consulta (dd/MM/yyyy HH:mm): ");

        service.reagendarConsulta(codigo, novaData);

        System.out.println("Consulta reagendada com sucesso.");
    }

    private void ordenarPorData() {
        service.ordenarConsultasPorData();

        System.out.println("Consultas ordenadas por data.");
        printer.exibirLista(service.listarConsultas());
    }

    private void gerarRelatorioResumido() {
        printer.gerarRelatorioResumido(service.listarConsultas());
    }

    private void cancelarConsulta() throws ConsultaNaoEncontradaException {
        int codigo = entrada.lerInteiro("Código da consulta para cancelar: ");

        service.cancelarConsulta(codigo);

        System.out.println("Consulta cancelada com sucesso.");
    }

    private void exibirTodasConsultas() {
        printer.exibirLista(service.listarConsultas());
    }
}
