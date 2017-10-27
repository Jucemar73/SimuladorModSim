package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ListenerTecladoDefinaValor implements KeyListener 
{
	private Janela pai;
	private int num;
	
	public ListenerTecladoDefinaValor(Janela janela, int num) 
	{
		this.pai = janela;
		this.num = num;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		this.pai.definaParametroEstatistico(num);
	}

	@Override
	public void keyTyped(KeyEvent e) {}

}
