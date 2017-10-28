package controle;

import java.util.TimerTask;

public class TarefaTimer extends TimerTask
{
	private Controle controle;
	
	public TarefaTimer(Controle controle) 
	{
		this.controle = controle;
	}
	
	@Override
	public void run() 
	{
		if(this.controle.estaRodando() == true)
			this.controle.rodaSimulacao();
		else
			this.cancel();
	}

}
