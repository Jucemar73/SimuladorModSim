package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;

import controle.Controle;

public class Janela extends JFrame
{
	private static final long serialVersionUID = 4495490767334813447L;
	private boolean bordasLigadas;
	private Color corTexto;
	private Font fonte;
	private Container container;
	private Controle controle;
	
	// Botões
	
	private JButton iniciar;
	private JButton sair;
	
	public Janela(Controle controle)
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Trabalho I - Modelagem & Simulação");
		this.setSize(800, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		// Parâmetros de controle
		
		this.controle = controle;
		this.corTexto = Color.RED;
		this.fonte = new Font("Arial", Font.BOLD, 25);
		this.bordasLigadas = true;
		this.container = this.getContentPane();
		this.container.setLayout(null);
		
		// Criação dos botões da interface
		
		iniciar = new JButton("Iniciar");
		iniciar.setToolTipText("Inicia a simulação.");
		iniciar.setFont(this.fonte);
		iniciar.setForeground(this.corTexto);
		iniciar.setBounds(20, 20, 180, 80);
		iniciar.setFocusPainted(false);
		iniciar.setMargin(new Insets(0, 0, 0, 0));
		iniciar.setContentAreaFilled(false);
		iniciar.setBorderPainted(this.bordasLigadas);
		iniciar.setOpaque(false);
		iniciar.addActionListener(new TratadorIniciar(this));
		
		sair = new JButton("Sair");
		sair.setToolTipText("Sai do programa.");
		sair.setFont(this.fonte);
		sair.setForeground(this.corTexto);
		sair.setBounds(20, 470, 180, 80);
		sair.setFocusPainted(false);
		sair.setMargin(new Insets(0, 0, 0, 0));
		sair.setContentAreaFilled(false);
		sair.setBorderPainted(this.bordasLigadas);
		sair.setOpaque(false);
		sair.addActionListener(new TratadorSair(this));
		
		container.add(iniciar);
		container.add(sair);

		setVisible(true);
	}
	
	public void iniciar()
	{
		this.controle.iniciar();
	}
	
	public void sair()
	{
		this.controle.sair();
	}
}
