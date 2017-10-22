package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TratadorIniciar implements ActionListener 
{
	private Janela pai;
	
	public TratadorIniciar(Janela janela) 
	{
		this.pai = janela;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		this.pai.iniciar();
	}
}
