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

	public Double mediaAritmeticaLista(ArrayList<Double> lista) 
	{
		double media = 0;
		for (int i = 0; i < lista.size(); i++)
			media += lista.get(i);
		
		return (media / lista.size());
	}

}
