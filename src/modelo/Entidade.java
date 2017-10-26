package modelo;

public class Entidade {
    int tipo;
    double ts;

    public Entidade(int tipo, double ts) {
        this.tipo = tipo;
        this.ts = ts;
    }

    public Entidade(Entidade entidade) {
        this.tipo = entidade.retornaTipo();
        this.ts = entidade.retornaTs();
    }

    public int retornaTipo() {
        return this.tipo;
    }

    public double retornaTs() {
        return this.ts;
    }
}
