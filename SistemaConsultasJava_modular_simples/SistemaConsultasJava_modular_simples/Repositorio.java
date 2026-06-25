import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Repositorio<T> {
    private ArrayList<T> elementos;

    public Repositorio() {
        elementos = new ArrayList<>();
    }

    public void adicionar(T elemento) {
        elementos.add(elemento);
    }

    public void remover(T elemento) {
        elementos.remove(elemento);
    }

    public List<T> listar() {
        return elementos;
    }

    public T buscar(Predicate<T> criterio) throws ConsultaNaoEncontradaException {
        for (T elemento : elementos) {
            if (criterio.test(elemento)) {
                return elemento;
            }
        }

        throw new ConsultaNaoEncontradaException("Consulta não encontrada.");
    }

    public List<T> filtrar(Predicate<T> criterio) {
        List<T> filtrados = new ArrayList<>();

        for (T elemento : elementos) {
            if (criterio.test(elemento)) {
                filtrados.add(elemento);
            }
        }

        return filtrados;
    }
}
