package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ListenerTecladoTempo implements KeyListener 
{
	private Janela pai;
	
	public ListenerTecladoTempo(Janela janela) 
	{
		this.pai = janela;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {}

	@Override
	public void keyReleased(KeyEvent arg0) 
	{
		this.pai.definaParametroTempo();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}

}
