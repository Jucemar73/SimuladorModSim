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
		// TODO l�gica no modelo, iniciar a execu��o do programa
	}
	
	public void sair()
	{
		System.out.println("Encerrando...");
		System.exit(0);
	}

	public void playPause()
	{
		// TODO l�gica no modelo, caso tenha pause
	}

	public void restart() 
	{
		this.simulador = new Simulador(); // Recria o objeto
	}

	public void definaFuncaoEstatisticaTec(int numFuncao) // Seta na l�gica o modo de TEC
	{
		// TODO l�gica no modelo
	}
	
	public void definaFuncaoEstatisticaTs(int numFuncao) // Seta na l�gica o modo de TS
	{
		// TODO l�gica no modelo
	}
	
	public void definaFuncaoEstatisticaTef(int numFuncao) // Seta na l�gica o modo de TEF
	{
		// TODO l�gica no modelo
	}
	
	public void definaFuncaoEstatisticaTc(int numFuncao) // Seta na l�gica o modo de TC
	{
		// TODO l�gica no modelo
	}
}
