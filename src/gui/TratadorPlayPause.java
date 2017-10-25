package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TratadorPlayPause implements ActionListener 
{
	private Janela pai;
	
	public TratadorPlayPause(Janela janela)
	{
		this.pai = janela;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		this.pai.playPause();
	}
}
