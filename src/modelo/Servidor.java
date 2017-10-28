package modelo;

import java.util.ArrayList;
import modelo.Entidade;

public class Servidor {

	private boolean ocupado = false;
	private boolean ativo = true;
	private ArrayList<Entidade> filaServidor = new ArrayList<Entidade>();
	private ArrayList<Integer> estadosFila = new ArrayList<Integer>();
	private double processTime;
	private double timeOut;
	private int entidadesSaida = 0;

	public boolean retornaOcupado() {
		return this.ocupado;
	}

	public boolean retornaAtivo() {
		return this.ativo;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public void addToQueue(Entidade entidade) {
		this.filaServidor.add(entidade);
	}

	public int getSizeFila() {
		return this.filaServidor.size();
	}

	public ArrayList<Integer> returnaEstadosFila() {
		return this.estadosFila;
	}

	public void assingProcess(Entidade entidade) {
		if (!this.retornaOcupado()) {
			this.processTime = entidade.retornaTs();
			this.setOcupado(true);
		}
	}

	public void newArrival(Entidade entidade) {
		if (this.filaServidor.size() == 0 && !this.retornaOcupado())
			this.assingProcess(entidade);
		else
			this.addToQueue(entidade);
	}

	public void process() {
		if (this.processTime > 0.0) {
			this.processTime -= 1.0;
			if (this.processTime == 0.0)
				this.setOcupado(false);
				this.entidadesSaida++;
		}
		if (this.processTime == 0 && this.filaServidor.size() != 0) {
			this.assingProcess(this.filaServidor.remove(0));
		}
	}

	public void atualizaListaEstados() {
		this.estadosFila.add(this.filaServidor.size());
	}
	
	public int retornaSaida() {
		return this.entidadesSaida;
	}
	
	public void setTimeOut(double tempo) {
		this.timeOut = tempo;
		this.setAtivo(false);
	}

	public void tryRecover() {
		this.timeOut -= 1.0;
		if (this.timeOut <= 0.0)
			this.setAtivo(true);
	}
}
