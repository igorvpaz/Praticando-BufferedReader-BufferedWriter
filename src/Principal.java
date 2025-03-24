import java.io.*;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do arquivo de cursos:  /  Endereço completo do arquivo com o formato especificado ");
        String arquivoCursos = scanner.nextLine();

        System.out.print("Digite o nome do arquivo de alunos: Endereço completo do arquivo com o formato especificado");
        String arquivoAlunos = scanner.nextLine();

        SistemaGestaoCursos sistema = new SistemaGestaoCursos();

        try {
            sistema.carregarCursos(arquivoCursos);
            sistema.carregarAlunos(arquivoAlunos);
        } catch (IOException e) {
            System.out.println("Erro ao carregar arquivos: " + e.getMessage());
            return;
        }

        // Exibir todos os cursos com seus preços finais
        System.out.println("\nCursos disponíveis:");
        for (int i = 0; i < sistema.qtdCursos; i++) {
            Curso curso = sistema.cursos[i];
            System.out.println(curso.toString() + " | Preço final: " + curso.calcularPrecoFinal());
        }

        // Exibir todos os alunos com suas médias
        System.out.println("\nAlunos matriculados:");
        for (int i = 0; i < sistema.qtdAlunos; i++) {
            Aluno aluno = sistema.alunos[i];
            System.out.println(aluno.toString());
        }

        // Gerar relatórios
        try {
            sistema.gerarRelatorioCursos("relatorioCursos.txt");
            sistema.gerarRelatorioAlunos("relatorioAlunos.txt");
            System.out.println("Relatórios gerados com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao gerar relatórios: " + e.getMessage());
        }

        scanner.close();
    }
}
