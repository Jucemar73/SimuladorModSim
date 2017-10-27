package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TratadorSelecionarTempo implements ActionListener 
{
	private Janela pai;
	
	public TratadorSelecionarTempo(Janela janela) 
	{
		this.pai = janela;
	}
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		this.pai.selecionarTempo();
	}

}
