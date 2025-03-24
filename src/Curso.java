abstract class Curso implements Avaliavel {
    protected int id;
    protected String nome;
    protected double precoBase;

    public Curso(int id, String nome, double precoBase) {
        this.id = id;
        this.nome = nome;
        this.precoBase = precoBase;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public double getPrecoBase() { return precoBase; }

    public abstract double calcularPrecoFinal();

    @Override
    public String toString() {
        return id + " | " + nome + " | " + precoBase;
    }

    @Override
    public double calcularMedia(double[][] notas) {
        double maiorMedia = 0;
        for (double[] alunoNotas : notas) {
            double soma = 0;
            for (double nota : alunoNotas) {
                soma += nota;
            }
            double media = soma / alunoNotas.length;
            if (media > maiorMedia) {
                maiorMedia = media;
            }
        }
        return maiorMedia;
    }
}
