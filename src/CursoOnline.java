class CursoOnline extends Curso {
    private String plataforma;
    private boolean possuiSuporte;

    public CursoOnline(int id, String nome, double precoBase, String plataforma, boolean possuiSuporte) {
        super(id, nome, precoBase);
        this.plataforma = plataforma;
        this.possuiSuporte = possuiSuporte;
    }

    @Override
    public double calcularPrecoFinal() {
        return possuiSuporte ? precoBase + 100 : precoBase;
    }

    @Override
    public String toString() {
        return super.toString() + " | " + plataforma + " | " + possuiSuporte;
    }
}