package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TratadorVoltar implements ActionListener
{
	private Janela pai;
	
	public TratadorVoltar(Janela janela)
	{
		this.pai = janela;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		this.pai.voltar();
	}
}
