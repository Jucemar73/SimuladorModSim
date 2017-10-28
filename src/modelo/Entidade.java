package modelo;

public class Entidade 
{
    private int tipo;
    private double ts;

    public Entidade(int tipo, double ts) 
    {
        this.tipo = tipo;
        this.ts = ts;
    }

    public Entidade(Entidade entidade) 
    {
        this.tipo = entidade.getTipo();
        this.ts = entidade.getTs();
    }

    public int getTipo() 
    {
        return this.tipo;
    }

    public double getTs() 
    {
        return this.ts;
    }
}
