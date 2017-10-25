package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TratadorRestart implements ActionListener 
{
	private Janela pai;
	
	public TratadorRestart(Janela janela)
	{
		this.pai = janela;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		this.pai.restart();
	}
}
