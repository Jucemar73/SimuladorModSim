package modelo;

import java.util.ArrayList;
import modelo.Entidade;

public class Servidor 
{
	private ArrayList<Entidade> filaServidor;
	private ArrayList<Integer> estadosFila;
	private boolean ocupado;
	private boolean ativo;
	private double processTime;
	private double timeOut;
	private int entidadesSaida;
	
	public Servidor()
	{
		this.filaServidor = new ArrayList<Entidade>();
		this.estadosFila = new ArrayList<Integer>();
		this.ocupado = false;
		this.ativo = true;
		this.processTime = 0;
		this.timeOut = 0;
		this.entidadesSaida = 0;
	}
	
	public int getTamFila() 
	{
		return this.filaServidor.size();
	}
	
	public int getEntidadesSaida()
	{
		return this.entidadesSaida;
	}

	public ArrayList<Integer> getFilaDeEstados() 
	{
		return this.estadosFila;
	}

	public boolean estaOcupado() 
	{
		return this.ocupado;
	}

	public boolean estaAtivo() 
	{
		return this.ativo;
	}

	public void setOcupado(boolean ocupado) 
	{
		this.ocupado = ocupado;
	}

	public void setAtivo(boolean ativo)
	{
		this.ativo = ativo;
	}
	
	public void setTimeOut(double tempo)
	{
		this.timeOut = tempo;
		this.setAtivo(false);
	}

	public void adicionarNaFila(Entidade entidade) 
	{
		this.filaServidor.add(entidade);
	}

	public void atribuirProcesso(Entidade entidade)
	{
		if (!this.estaOcupado())
		{
			this.processTime = entidade.getTs();
			this.setOcupado(true);
		}
	}

	public void novaChegada(Entidade entidade) 
	{
		if (this.filaServidor.size() == 0 && !this.estaOcupado())
			this.atribuirProcesso(entidade);
		else
			this.adicionarNaFila(entidade);
	}

	public void processamento() 
	{
		if (this.processTime > 0.0) 
		{
			this.processTime -= 1.0;
			if (this.processTime == 0.0)
				this.setOcupado(false);
			this.entidadesSaida++;
		}
		if (this.processTime == 0 && this.filaServidor.size() != 0) 
			this.atribuirProcesso(this.filaServidor.remove(0));	
	}

	public void atualizaListaEstados() 
	{
		this.estadosFila.add(this.filaServidor.size());
	}

	public void tentarReiniciar() 
	{
		this.timeOut -= 1.0;
		if (this.timeOut <= 0.0)
			this.setAtivo(true);
	}
}
