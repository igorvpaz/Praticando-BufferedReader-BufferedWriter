class Aluno {
    private int id;
    private String nome;
    private Curso cursoMatriculado;
    private double[][] notas;

    public Aluno(int id, String nome, Curso cursoMatriculado, double[][] notas) {
        this.id = id;
        this.nome = nome;
        this.cursoMatriculado = cursoMatriculado;
        this.notas = notas;
    }

    public double getMediaFinal() {
        return cursoMatriculado.calcularMedia(notas);
    }

    @Override
    public String toString() {
        return nome + " | " + cursoMatriculado.getNome() + " | " + getMediaFinal();
    }
}
