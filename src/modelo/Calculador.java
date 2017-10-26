package modelo;

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

    public double numeroMedioFilas(int nFila, int tempo) {
        return nFila / tempo;
    }

    public double taxaMediaOcupacao(int tempoOcupado, int tempoAtivo) {
        return tempoOcupado / tempoAtivo;
    }

    public double tempoMedioEmFila(int totalEmFila, int tempo) {
        return totalEmFila / tempo;
    }

    public double tempoEmFalha(int tempoFalha, int tempoTotal) {
        return tempoFalha / tempoTotal;
    }
}
