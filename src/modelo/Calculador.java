package modelo;

import java.util.ArrayList;

public class Calculador {
    public double probExponencial(double media) {
        double rand = Math.random();
        return Math.log(1.0 - rand) / (- media);
    }

    public double probTriangular(double min, double med, double max) {
        double F = (max - min) / (med - min);
        double rand = Math.random();
        if (rand < F) {
            return min + Math.sqrt(rand * (med - min) * (max - min));
        }
        return med - Math.sqrt((1.0 - rand) * (med - min) * (med - max));
    }

    public double probUniforme(double min, double max) {
        double rand = Math.random();
        return min + rand * (max - min);
    }

    public double probNormal(double media, double dp) {
        double rand = Math.random();
        return (rand - media) / dp;
    }
/*
    public double numeroMedioFilas(int nFila, int tempo) {
        return nFila / tempo;
    }
*/
    public double taxaMediaOcupacao(int tempoOcupado, int tempoAtivo) {
        return tempoOcupado / tempoAtivo;
    }

    public double tempoMedioEmFila(int totalEmFila, int tempo) {
        return totalEmFila / tempo;
    }

    public double tempoEmFalha(int tempoFalha, int tempoTotal) {
        return tempoFalha / tempoTotal;
    }

    public double mediaPonderada(ArrayList<Integer> lista,int tempo) {
        int temp = 0;
        int i = 0;
        while (i <= tempo && lista.size() > 0) 
        {
            temp += (lista.remove(0)/tempo)*(1/tempo); //variavel/tempo * peso
            ++i;
        }
        return temp;
    }
    public double mediaPonderadaTotal(ArrayList<Integer> lista1,ArrayList<Integer> lista2,int tempo) {
    	int temp = 0;
    	int i = 0;
    	int[] s = new int[lista1.size()];
    	for(int t = 0; t < lista1.size();t++) {
    		s[t] = lista1.remove(0) + lista2.remove(0);
    	}
    	while (i <= tempo && lista1.size()>0) {
    		temp += (s[i]/tempo) * (1/s.length);
    	
    	}
    
    	return temp;
    }
    
    public double tempoMedioNoSistema(int tempoEmFila,int tempoOcupado, int tempoTotal) {
  	  return (tempoEmFila+tempoOcupado)/tempoTotal;
    }
}
