package modelo;

public class Entidade 
{
    private int tipo;
    private double tempoDeServico;

    public Entidade(int tipo, double ts) 
    {
        this.tipo = tipo;
        this.tempoDeServico = ts;
    }

    public int getTipo() 
    {
        return this.tipo;
    }

    public double getTempoDeServico() 
    {
        return this.tempoDeServico;
    }
}
