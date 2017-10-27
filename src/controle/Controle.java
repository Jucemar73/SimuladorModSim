package controle;

import java.util.ArrayList;

import gui.Janela;
import modelo.Simulador;

public class Controle 
{
	private Janela janela;
	private Simulador simulador;
	
	public Controle()
	{
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
		this.janela = new Janela(this);
		System.out.println("Janela inicializada.");
	}
	
	public void iniciarSimulador()
	{
		this.simulador = new Simulador(this);
		System.out.println("Objeto simulador criado.");
	}
	
	public void playPause()
	{
		// TODO l�gica no modelo, caso tenha pause
	}
	
	public void restart() 
	{
		this.simulador = new Simulador(this); // Recria o objeto
		System.out.println("Objeto simulador recriado.");
	}
	
	public void sair()
	{
		System.out.println("Encerrando...");
		System.exit(0);
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
	
	public void definaUnidadeTempo(int numUnidadeTempo) // 0 para seg, 1 para min, 2 para horas
	{
		// TODO l�gica no modelo
	}

	public void atualizaEstatisticas(ArrayList<Double> estatisticas) 
	{
		this.janela.atualizaEstatisticas(estatisticas);
	}

}
