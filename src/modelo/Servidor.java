package modelo;

import java.util.ArrayList;

public class Servidor 
{
	boolean ocupado, ativo;
	private ArrayList<Entidade> filaServidor;
	double processTime, timeOut;
	
	public Servidor(){

	this.ocupado = false;
	this.ativo = true;
	this.filaServidor = new ArrayList<Entidade>();
	}

	public boolean retornaOcupado(){
		return ocupado;	
	}
	public boolean retornaAtivo(){
		return ativo;
	}
	public void setOcupado(boolean ocupado){
		this.ocupado = ocupado;
	}
	public void setAtivo(boolean ativo){
		this.ativo = ativo;
	}
	public void addToQueue(Entidade entidade){
		this.filaServidor.add(entidade);
	}
	
	////////////////////
	public void assingProcess(Entidade entidade){
		if(this.retornaOcupado() == false){
			this.processTime = entidade.retornaTs();
			this.setOcupado(true);
		}
	}
	
	public void newArrival(Entidade entidade){
		if(this.filaServidor.size() == 0 && retornaOcupado() == false){
			assingProcess(entidade);
		}
		else{
			addToQueue(entidade);
		}
	}
	
	public void process(){
		if(this.processTime>0){
			this.processTime -=1;
			if(this.processTime == 0){
				this.setOcupado(ocupado);
			}
		}
		else if(this.filaServidor.size() == 0){
			assingProcess(this.filaServidor.remove(0));
		}
	}
	////////////////////
	public void setTimeOut(double tempo){
		this.timeOut = tempo;
		this.setAtivo(false);
	}
	public void tryRecover(){
		this.timeOut -=1;
		if(this.timeOut <= 0){
			this.setAtivo(true);
		}
	}
	
	
	
}
