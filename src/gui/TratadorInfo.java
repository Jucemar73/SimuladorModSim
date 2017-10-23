package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TratadorInfo implements ActionListener
{
	private Janela pai;
	
	public TratadorInfo(Janela janela) 
	{
		this.pai = janela;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		this.pai.mostreInfo();
	}
}
