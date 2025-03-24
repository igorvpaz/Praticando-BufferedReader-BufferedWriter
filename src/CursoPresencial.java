class CursoPresencial extends Curso {
    private String local;
    private int cargaHoraria;

    public CursoPresencial(int id, String nome, double precoBase, String local, int cargaHoraria) {
        super(id, nome, precoBase);
        this.local = local;
        this.cargaHoraria = cargaHoraria;
    }

    @Override
    public double calcularPrecoFinal() {
        return precoBase + (20 * cargaHoraria);
    }

    @Override
    public String toString() {
        return super.toString() + " | " + local + " | " + cargaHoraria;
    }
}

