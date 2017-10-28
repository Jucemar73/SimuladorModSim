package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TratadorSelecionarVelocidade implements ActionListener 
{
	private Janela pai;
	
	public TratadorSelecionarVelocidade(Janela janela) 
	{
		this.pai = janela;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		this.pai.selecionarVelocidade();
	}
}
