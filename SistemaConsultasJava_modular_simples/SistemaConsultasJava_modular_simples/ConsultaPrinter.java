import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class ConsultaPrinter {
    private DateTimeFormatter formatadorData;

    public ConsultaPrinter() {
        formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    }

    public void exibirLista(List<Consulta> consultas) {
        Consumer<Consulta> exibirConsulta = c -> {
            System.out.println("\nCódigo: " + c.getCodigo());
            System.out.println("Paciente: " + c.getNomePaciente());
            System.out.println("Especialidade: " + formatarEspecialidade(c.getEspecialidade()));
            System.out.println("Data: " + c.getDataConsulta().format(formatadorData));
            System.out.println("Valor: R$ " + String.format("%.2f", c.getValorConsulta()));
            System.out.println("Cancelada: " + (c.isCancelada() ? "Sim" : "Não"));
        };

        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta encontrada.");
        } else {
            for (Consulta consulta : consultas) {
                exibirConsulta.accept(consulta);
            }
        }
    }

    public void gerarRelatorioResumido(List<Consulta> consultas) {
        Function<Consulta, String> converterParaString =
                c -> c.getNomePaciente() + " - " +
                        formatarEspecialidade(c.getEspecialidade()) + " - " +
                        c.getDataConsulta().format(formatadorData);

        System.out.println("\n===== RELATÓRIO RESUMIDO =====");

        for (Consulta consulta : consultas) {
            System.out.println(converterParaString.apply(consulta));
        }
    }

    public String formatarEspecialidade(Especialidade especialidade) {
        switch (especialidade) {
            case CARDIOLOGIA:
                return "Cardiologia";
            case PEDIATRIA:
                return "Pediatria";
            case ORTOPEDIA:
                return "Ortopedia";
            case DERMATOLOGIA:
                return "Dermatologia";
            default:
                return "";
        }
    }
}
