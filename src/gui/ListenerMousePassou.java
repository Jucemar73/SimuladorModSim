package gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class ListenerMousePassou implements MouseListener 
{	
	private JButton botao;
	private Color corTexto;
	private Color corBrilho;
	
	public ListenerMousePassou(Color corTexto, JButton botao) 
	{
		this.botao = botao;
		this.corTexto = corTexto;
		this.corBrilho = Color.MAGENTA;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) 
	{
		this.botao.setForeground(this.corBrilho);
	}

	@Override
	public void mouseExited(MouseEvent arg0) 
	{
		this.botao.setForeground(this.corTexto);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
