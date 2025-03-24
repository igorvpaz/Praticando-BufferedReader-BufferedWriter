import java.io.*;
import java.util.Arrays;

public class SistemaGestaoCursos {
    private static final int MAX_CURSOS = 50;
    private static final int MAX_ALUNOS = 200;

    Curso[] cursos = new Curso[MAX_CURSOS];
    Aluno[] alunos = new Aluno[MAX_ALUNOS];
    int qtdCursos = 0;
    int qtdAlunos = 0;

    public void carregarCursos(String arquivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split("\\|");
                if (partes.length < 5) throw new IOException("Formato inválido");

                int id = Integer.parseInt(partes[0].trim());
                String tipo = partes[1].trim();
                String nome = partes[2].trim();
                double precoBase = Double.parseDouble(partes[3].trim());

                if (tipo.equals("PRESENCIAL")) {
                    String local = partes[4].trim();
                    int cargaHoraria = Integer.parseInt(partes[5].trim());
                    adicionarCurso(new CursoPresencial(id, nome, precoBase, local, cargaHoraria));
                } else if (tipo.equals("ONLINE")) {
                    String plataforma = partes[4].trim();
                    boolean possuiSuporte = Boolean.parseBoolean(partes[5].trim());
                    adicionarCurso(new CursoOnline(id, nome, precoBase, plataforma, possuiSuporte));
                }
            }
        }
    }

    public void carregarAlunos(String arquivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split("\\|");
                if (partes.length < 4) throw new IOException("Formato inválido");

                int id = Integer.parseInt(partes[0].trim());
                String nome = partes[1].trim();
                int idCurso = Integer.parseInt(partes[2].trim());

                Curso curso = buscarCursoPorId(idCurso);
                if (curso == null) throw new IOException("Curso não encontrado");

                String[] notasStr = partes[3].trim().split(";");
                double[][] notas = new double[notasStr.length][];
                for (int i = 0; i < notasStr.length; i++) {
                    String[] notasIndividuais = notasStr[i].split(",");
                    notas[i] = Arrays.stream(notasIndividuais).mapToDouble(Double::parseDouble).toArray();
                }

                adicionarAluno(new Aluno(id, nome, curso, notas));
            }
        }
    }

    public void adicionarCurso(Curso curso) {
        if (qtdCursos >= MAX_CURSOS) throw new RuntimeException("Impossível adicionar mais cursos");
        cursos[qtdCursos++] = curso;
    }

    public void adicionarAluno(Aluno aluno) {
        if (qtdAlunos >= MAX_ALUNOS) throw new RuntimeException("Impossível adicionar mais alunos");
        alunos[qtdAlunos++] = aluno;
    }

    public void gerarRelatorioCursos(String arquivoSaida) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoSaida))) {
            Arrays.sort(cursos, 0, qtdCursos, (c1, c2) -> Double.compare(c1.calcularPrecoFinal(), c2.calcularPrecoFinal()));
            for (int i = 0; i < qtdCursos; i++) {
                bw.write(cursos[i].toString() + " | Preço Final: " + cursos[i].calcularPrecoFinal());
                bw.newLine();
            }
        }
    }

    public void gerarRelatorioAlunos(String arquivoSaida) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoSaida))) {
            Arrays.sort(alunos, 0, qtdAlunos, (a1, a2) -> Double.compare(a2.getMediaFinal(), a1.getMediaFinal()));
            for (int i = 0; i < qtdAlunos; i++) {
                bw.write(alunos[i].toString());
                bw.newLine();
            }
        }
    }

    private Curso buscarCursoPorId(int id) {
        for (int i = 0; i < qtdCursos; i++) {
            if (cursos[i].getId() == id) {
                return cursos[i];
            }
        }
        return null;
    }
}