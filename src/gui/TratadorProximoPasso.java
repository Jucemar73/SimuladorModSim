package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TratadorProximoPasso implements ActionListener 
{
	private Janela pai;
	
	public TratadorProximoPasso(Janela janela)
	{
		this.pai = janela;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		this.pai.avancaProximoPasso();
	}

}
