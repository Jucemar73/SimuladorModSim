package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TratadorSelecionarFuncao implements ActionListener 
{
	private Janela pai;
	private int num; // 0 para TEC, 1 para TS, 2 para TEF, 3 para TC
	
	public TratadorSelecionarFuncao(Janela janela, int num)
	{
		this.pai = janela;
		this.num = num;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		this.pai.selecionarFuncaoEstatistica(this.num);
	}
}
