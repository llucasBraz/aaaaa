import java.time.LocalDateTime;

public class Consulta {
    private int codigo;
    private String nomePaciente;
    private Especialidade especialidade;
    private LocalDateTime dataConsulta;
    private double valorConsulta;
    private boolean cancelada;

    public Consulta(int codigo, String nomePaciente, Especialidade especialidade,
                    LocalDateTime dataConsulta, double valorConsulta) {
        this.codigo = codigo;
        this.nomePaciente = nomePaciente;
        this.especialidade = especialidade;
        this.dataConsulta = dataConsulta;
        this.valorConsulta = valorConsulta;
        this.cancelada = false;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public LocalDateTime getDataConsulta() {
        return dataConsulta;
    }

    public double getValorConsulta() {
        return valorConsulta;
    }

    public boolean isCancelada() {
        return cancelada;
    }

    public void setDataConsulta(LocalDateTime dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public void cancelar() {
        this.cancelada = true;
    }
}
