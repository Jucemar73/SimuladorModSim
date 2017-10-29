package modelo;

import modelo.Calculador;
import modelo.Entidade;

public class Gerador
{
    private Calculador calculador;
    
    public Gerador(Calculador calc)
    {
    	this.calculador = calc;
    }

    public Entidade geraEntidade(int mode, double op1, double op2, double op3) 
    {
        int tipo = 1;
        if (Math.random() >= 0.5) 
            tipo = 2;
        
        double ts = mode == 0 ? op1 * 100.0 // Constante
        		 : (mode == 1 ? this.calculador.probExponencial(op1) 
        		 : (mode == 2 ? this.calculador.probNormal(op1, op2) 
        		 : (mode == 3 ? this.calculador.probTriangular(op1, op2, op3) 
        		 : 				this.calculador.probUniforme(op1, op2))));
        
        return new Entidade(tipo, ts);
    }
}
