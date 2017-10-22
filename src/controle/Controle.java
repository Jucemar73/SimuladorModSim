package controle;

import gui.Janela;
import modelo.Simulador;

public class Controle 
{
	private Janela janela;
	private Simulador simulador;
	
	public Controle()
	{
		this.simulador = new Simulador();
	}
	
	public Janela getJanela()
	{
		return this.janela;
	}
	
	public Simulador getSimulador()
	{
		return this.simulador;
	}
	
	public void iniciarJanela()
	{
		System.out.println("Janela inicializada.");
		this.janela = new Janela(this);
	}
	
	public void iniciar()
	{
		// TODO Ligar execução do programa com interface
	}
	
	public void sair()
	{
		System.out.println("Encerrando...");
		System.exit(0);
	}
}
