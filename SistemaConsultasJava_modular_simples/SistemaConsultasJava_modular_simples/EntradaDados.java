import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class EntradaDados {
    private Scanner scanner;
    private DateTimeFormatter formatadorData;

    public EntradaDados() {
        scanner = new Scanner(System.in);
        formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    }

    public int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Número inválido.");
            }
        }
    }

    public double lerDouble(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Double.parseDouble(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Valor inválido.");
            }
        }
    }

    public String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    public LocalDateTime lerData(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                String texto = scanner.nextLine();
                return LocalDateTime.parse(texto, formatadorData);
            } catch (Exception e) {
                System.out.println("Data inválida.");
            }
        }
    }

    public Especialidade lerEspecialidade() {
        int opcao;

        do {
            System.out.println("Especialidade:");
            System.out.println("1 - CARDIOLOGIA");
            System.out.println("2 - PEDIATRIA");
            System.out.println("3 - ORTOPEDIA");
            System.out.println("4 - DERMATOLOGIA");

            opcao = lerInteiro("Escolha: ");

            switch (opcao) {
                case 1:
                    return Especialidade.CARDIOLOGIA;
                case 2:
                    return Especialidade.PEDIATRIA;
                case 3:
                    return Especialidade.ORTOPEDIA;
                case 4:
                    return Especialidade.DERMATOLOGIA;
                default:
                    System.out.println("Especialidade inválida.");
            }
        } while (true);
    }
}
