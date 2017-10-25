package modelo;

public class Calculador {


	public double probExponencial(double det) {
		// TODO Auto-generated method stub
		return det;
	}

	public double probTriangular(double min,double med, double max) {
		  // TODO Auto-generated method stub
		  double F = (max - min) / (med - min);
		     double rand = Math.random();
		     if (rand < F) {
		         return min + Math.sqrt(rand * (med - min) * (max - min));
		     } else {
		         return med - Math.sqrt((1 - rand) * (med - min) * (med - max));
		     }
		 }

	public double probUniforme(double det, double seed) {
		// TODO Auto-generated method stub
		return 0;
	}

	public double probNormal(double det, double dp) {
		return det+dp;
	}

	
	
}
