package modelo;

import java.util.ArrayList;
import modelo.Entidade;

public class Servidor 
{
	private ArrayList<Entidade> filaEntidades;
	private int entidadesQueEntraram;
	private int entidadesQueSairam;
	
	public Servidor()
	{
		this.filaEntidades = new ArrayList<Entidade>();
		this.entidadesQueEntraram = 0;
		this.entidadesQueSairam = 0;
	}
	
	public ArrayList<Entidade> getFilaEntidades()
	{
		return this.filaEntidades;
	}
	
	public int getEntidadesQueEntraram()
	{
		return this.entidadesQueEntraram;
	}
	
	public int getEntidadesQueSairam()
	{
		return this.entidadesQueSairam;
	}
}
