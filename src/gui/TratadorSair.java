package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TratadorSair implements ActionListener 
{
	private Janela pai;
	
	public TratadorSair(Janela janela) 
	{
		this.pai = janela;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		this.pai.sair();
	}
}
