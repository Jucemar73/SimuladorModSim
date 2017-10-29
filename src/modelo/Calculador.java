package modelo;

import java.util.ArrayList;
import java.util.Random;

public class Calculador 
{
	private Random random;
	
	public Calculador()
	{
		this.random = new Random();
	}
	
    public double probExponencial(double media) 
    {
        double rand = Math.random();
        return Math.log(1.0 - rand) / (- media);
    }
    
    public double probNormal(double media, double dp) 
    {
    	double resultado = this.random.nextGaussian() * dp + media;
    	while(resultado < 0) // Evitar tempo negativo
    		resultado = this.random.nextGaussian() * dp + media;
    	return resultado;
    }

    public double probTriangular(double min, double med, double max) 
    {
        double F = (max - min) / (med - min);
        double rand = Math.random();
        
        if (rand < F) 
            return min + Math.sqrt(rand * (med - min) * (max - min));
        
        return med - Math.sqrt((1.0 - rand) * (med - min) * (med - max));
    }

    public double probUniforme(double min, double max) 
    {
        double rand = Math.random();
        System.err.println("Resultado unif = " + (min + rand * (max - min)));
        return min + rand * (max - min);
    }
    
    public double taxaMediaOcupacao(double tempoOcupado, double tempoAtivo) // TODO testar
    { 
        return tempoOcupado / tempoAtivo;
    }

    public double tempoMedioEmFila(double tempoEmFila, double tempo) // TODO testar
    {
        return tempoEmFila / tempo;
    }

    public double tempoEmFalha(double tempoFalha, double tempoTotal) // TODO testar
    {
        return tempoFalha / tempoTotal;
    }

    public double mediaPonderada(ArrayList<Integer> lista, double tempo) // TODO testar
    {
        int temp = 0;
        int i = 0;
        int atual = 0;
        while (i <= tempo && lista.size() > 0) 
        {
        	atual = lista.remove(0);
            temp += (atual/tempo)*(1/tempo); // (variável / tempo) * peso
            ++i;
        }
        return temp;
    }
    
    public double mediaPonderadaTotal(ArrayList<Integer> lista1, ArrayList<Integer> lista2, double tempo) // TODO testar
    { 
    	int temp = 0;
    	int i = 0;
    	int atual1 = 0;
    	int atual2 = 0;
    	int[] s = new int[lista1.size()];
    	
    	for(int t = 0; t < lista1.size();t++)
    	{
    		atual1 = lista1.remove(0);
    		atual2 = lista2.remove(0);
    		s[t] = atual1 + atual2;
    	}
    	
    	while (i <= tempo && lista1.size()>0) 
    		temp += (s[i]/tempo) * (1/s.length);

    	return temp;
    }
    
    public double tempoMedioNoSistema(double tempoEmFila,double tempoOcupado, double tempoTotal) // TODO testar
    {
    	return (tempoEmFila+tempoOcupado)/tempoTotal;
    }
}
