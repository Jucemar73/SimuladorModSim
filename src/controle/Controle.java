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
	
	public void playPause(boolean pausa)
	{
		if(pausa)
		{
			// TODO lógica pause
		}
		else // Se não pausado
		{
			this.simulador.iniciarSimulacao();
		}
			
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

	public void definaFuncaoEstatisticaTec(int numFuncao) // Seta na lógica o modo de TEC
	{
		this.simulador.setModoTec(numFuncao);
	}
	
	public void definaFuncaoEstatisticaTs(int numFuncao) // Seta na lógica o modo de TS
	{
		this.simulador.setModoTs(numFuncao);
	}
	
	public void definaFuncaoEstatisticaTef(int numFuncao) // Seta na lógica o modo de TEF
	{
		this.simulador.setModoTef(numFuncao);
	}
	
	public void definaFuncaoEstatisticaTf(int numFuncao) // Seta na lógica o modo de TF
	{
		this.simulador.setModoTf(numFuncao);
	}
	
	public void definaUnidadeTempo(int numUnidadeTempo) // 0 para seg, 1 para min, 2 para horas
	{
		this.simulador.setUnidadeTempo(numUnidadeTempo);
	}
	
	public void definaParametroTec(String texto)
	{
		try 
		{
			this.simulador.setParametroTec(texto);
		} 
		catch (Exception e) 
		{
			System.err.println("Valor inválido inserido no parâmetro TEC!");
		}
	}

	public void definaParametroTs(String texto) 
	{
		try 
		{
			this.simulador.setParametroTs(texto);
		} 
		catch (Exception e) 
		{
			System.err.println("Valor inválido inserido no parâmetro TS!");
		}
		
	}

	public void definaParametroTef(String texto) 
	{
		try 
		{
			this.simulador.setParametroTef(texto);
		} 
		catch (Exception e) 
		{
			System.err.println("Valor inválido inserido no parâmetro TEF!");
		}
		
	}

	public void definaParametroTf(String texto) 
	{
		try 
		{
			this.simulador.setParametroTf(texto);
		} 
		catch (Exception e) 
		{
			System.err.println("Valor inválido inserido no parâmetro TF!");
		}
		
	}

	public void definaParametroTempo(String texto)
	{
		try 
		{
			this.simulador.setTempoFinal(Integer.parseInt(texto));
		} 
		catch (Exception e) 
		{
			System.err.println("Valor inválido inserido no parâmetro de tempo!");
		}
	}

	public void atualizaEstatisticas(ArrayList<Double> estatisticas) 
	{
		this.janela.atualizaEstatisticas(estatisticas);
	}

}
