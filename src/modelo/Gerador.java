package modelo;
import java.util.Random;

public class Gerador {

	private Calculador calculador;
	
	public Gerador(){
	this.calculador = new Calculador();
	}
	
	public Entidade geraEntidade(double seed,int mode, double det){
		int tipo = 1;
		if(seed >= 0.5){
			tipo = 2;
		}
		double ts;
		if(mode == 0){
			ts = det*100;
		}
		else if(mode == 1){
			ts = this.calculador.probNormal(det,seed);
		}
		else if(mode == 2){
			ts =  this.calculador.probUniforme(det,seed);
		}
		else if(mode == 3){
			ts =  this.calculador.probTriangular((det-seed), seed,(det+seed));
		}
		else{
			ts =  this.calculador.probExponencial(det);
		}
		return new Entidade(tipo, ts);
	}	

}
