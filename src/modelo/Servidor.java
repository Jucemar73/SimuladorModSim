package modelo;

import java.util.ArrayList;

public class Servidor 
{
	private ArrayList<Entidade> filaEntidades;
	private double entidadesQueEntraram;
	private double entidadesQueSairam;
	private double numeroDeQuebras;
	private double numeroDeReparos;
	private boolean ocupado;
	private boolean quebrado;
	private String nome;
	
	public Servidor(String nome)
	{
		this.filaEntidades = new ArrayList<Entidade>();
		this.entidadesQueEntraram = 0;
		this.entidadesQueSairam = 0;
		this.numeroDeQuebras = 0;
		this.numeroDeReparos = 0;
		this.ocupado = false;
		this.quebrado = false;
		this.nome = nome;
	}
	
	public ArrayList<Entidade> getFilaEntidades()
	{
		return this.filaEntidades;
	}
	
	public double getEntidadesQueEntraram()
	{
		return this.entidadesQueEntraram;
	}
	
	public double getEntidadesQueSairam()
	{
		return this.entidadesQueSairam;
	}
	
	public double getNumeroDeQuebras()
	{
		return this.numeroDeQuebras;
	}
	
	public double getNumeroDeReparos()
	{
		return this.numeroDeReparos;
	}
	
	public String getNome()
	{
		return this.nome;
	}
	
	public boolean estaOcupado()
	{
		return this.ocupado;
	}
	
	public boolean estaQuebrado()
	{
		return this.quebrado;
	}
	
	public boolean filaEstaVazia()
	{
		boolean resultado = false;
		if(this.filaEntidades.size() == 0)
			resultado = true;
		return resultado;
	}
	
	public void adicionaEntidade(Entidade ent)
	{
		this.filaEntidades.add(ent);
		this.entidadesQueEntraram++;
	}

	public double executarServico() 
	{
		this.ocupado = true;
		return this.filaEntidades.get(0).getTempoDeServico();
	}
	
	public void acabarServico()
	{
		this.ocupado = false;
		this.filaEntidades.remove(0);
		this.entidadesQueSairam++;
	}
	
	public void quebrar()
	{
		this.quebrado = true;
		this.numeroDeQuebras++;
		
	}
	
	public void reparar()
	{
		this.quebrado = false;
		this.numeroDeReparos++;
	}
}
