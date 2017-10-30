package modelo;

public class Entidade 
{
    private int tipo;
    private double tempoDeServico;
    private double tempoQueEntrouNoSistema;

    public Entidade(int tipo, double ts, double tempoAtual) 
    {
        this.tipo = tipo;
        this.tempoDeServico = ts;
        this.tempoQueEntrouNoSistema = tempoAtual;
    }

    public int getTipo() 
    {
        return this.tipo;
    }

    public double getTempoDeServico() 
    {
        return this.tempoDeServico;
    }
    
    public double getTempoQueEntrouNoSistema()
    {
    	return this.tempoQueEntrouNoSistema;
    }
}
