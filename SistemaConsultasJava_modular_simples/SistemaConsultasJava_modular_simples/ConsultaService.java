import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ConsultaService {
    private Repositorio<Consulta> repositorio;

    public ConsultaService(Repositorio<Consulta> repositorio) {
        this.repositorio = repositorio;
    }

    public void criarAutomaticamente5Consultas() {
        repositorio.adicionar(new Consulta(
                1,
                "João Silva",
                Especialidade.CARDIOLOGIA,
                LocalDateTime.of(2026, 6, 15, 14, 0),
                250.0
        ));

        repositorio.adicionar(new Consulta(
                2,
                "Maria Souza",
                Especialidade.PEDIATRIA,
                LocalDateTime.of(2026, 6, 16, 9, 30),
                180.0
        ));

        repositorio.adicionar(new Consulta(
                3,
                "Carlos Lima",
                Especialidade.ORTOPEDIA,
                LocalDateTime.of(2026, 6, 17, 10, 0),
                220.0
        ));

        repositorio.adicionar(new Consulta(
                4,
                "Ana Paula",
                Especialidade.DERMATOLOGIA,
                LocalDateTime.of(2026, 6, 18, 15, 30),
                200.0
        ));

        repositorio.adicionar(new Consulta(
                5,
                "Pedro Alves",
                Especialidade.CARDIOLOGIA,
                LocalDateTime.of(2026, 6, 19, 8, 0),
                300.0
        ));
    }

    public void cadastrarConsulta(int codigo, String nome, Especialidade especialidade,
                                  LocalDateTime data, double valor)
            throws HorarioIndisponivelException {
        verificarHorarioDisponivel(data);

        Consulta consulta = new Consulta(codigo, nome, especialidade, data, valor);
        repositorio.adicionar(consulta);
    }

    public void removerConsulta(int codigo) throws ConsultaNaoEncontradaException {
        Consulta consulta = buscarPorCodigo(codigo);
        repositorio.remover(consulta);
    }

    public List<Consulta> filtrarPorEspecialidade(Especialidade especialidade) {
        Predicate<Consulta> filtro = c -> c.getEspecialidade() == especialidade;
        return repositorio.filtrar(filtro);
    }

    public List<Consulta> filtrarPorValorAcima(double valor) {
        Predicate<Consulta> filtro = c -> c.getValorConsulta() > valor;
        return repositorio.filtrar(filtro);
    }

    public void reagendarConsulta(int codigo, LocalDateTime novaData)
            throws ConsultaNaoEncontradaException, HorarioIndisponivelException {
        Consulta consulta = buscarPorCodigo(codigo);
        verificarHorarioDisponivel(novaData);
        consulta.setDataConsulta(novaData);
    }

    public void ordenarConsultasPorData() {
        Comparator<Consulta> comparadorPorData =
                (c1, c2) -> c1.getDataConsulta().compareTo(c2.getDataConsulta());

        repositorio.listar().sort(comparadorPorData);
    }

    public void cancelarConsulta(int codigo) throws ConsultaNaoEncontradaException {
        Consulta consulta = buscarPorCodigo(codigo);
        consulta.cancelar();
    }

    public List<Consulta> listarConsultas() {
        return repositorio.listar();
    }

    private Consulta buscarPorCodigo(int codigo) throws ConsultaNaoEncontradaException {
        return repositorio.buscar(c -> c.getCodigo() == codigo);
    }

    private void verificarHorarioDisponivel(LocalDateTime data)
            throws HorarioIndisponivelException {
        for (Consulta consulta : repositorio.listar()) {
            if (!consulta.isCancelada() && consulta.getDataConsulta().equals(data)) {
                throw new HorarioIndisponivelException("Horário indisponível.");
            }
        }
    }
}
