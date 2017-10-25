package modelo;

public class Calculador {


	public double probExponencial(double media) {
		  double rand = Math.random();
		  return  Math.log(1-rand)/(-media);
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

	public double probUniforme(double min, double max) {
		  double rand = Math.random();
		  return min+rand*(max-min);
		 }

	public double probNormal(double media, double dp) {
		  double rand = Math.random();
		  return (rand-media)/dp;
		 }
}
