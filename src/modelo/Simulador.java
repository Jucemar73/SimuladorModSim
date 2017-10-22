package modelo;

import java.util.ArrayList;

public class Simulador 
{
	private Servidor serv1;
	private Servidor serv2;
	private ArrayList<Evento> listaDeEventos;
	
	public Simulador()
	{
		this.serv1 = new Servidor();
		this.serv2 = new Servidor();
		this.listaDeEventos = new ArrayList<Evento>();
	}
	
	public Servidor getServ1()
	{
		return this.serv1;
	}
	
	public Servidor getServ2()
	{
		return this.serv2;
	}
	
	public ArrayList<Evento> getListaDeEventos()
	{
		return this.listaDeEventos;
	}
	
	public void iniciarSimulacao()
	{
		// TODO simulação
	}
}
