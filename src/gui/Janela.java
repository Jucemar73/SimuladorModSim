package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
	private JButton voltar;
	private JButton info;
	private JButton sair;
	
	private JButton playPause;
	private JButton restart;
	
	// Etiquetas
	
	private JLabel descricao;
	
	// Métodos privados
	
	private String textoDescricao()
	{
        String texto =    "<html> &nbsp&nbsp&nbsp INE5425 - Modelagem & Simulação - Trabalho I<br/>"
        				+ "<html> &nbsp&nbsp&nbsp <br/>"
        				+ "<html> &nbsp&nbsp&nbsp 2017/2<br/>"
        				+ "<html> &nbsp&nbsp&nbsp <br/>"
        				+ "<html> &nbsp&nbsp&nbsp Alunos:<br/>"
        				+ "<html> &nbsp&nbsp&nbsp <br/>"
        				+ "<html> &nbsp&nbsp&nbsp Gustavo José Carpeggiani<br/>"
        				+ "<html> &nbsp&nbsp&nbsp Marcello da Silva Klingelfus Junior <br/>"
        				+ "<html> &nbsp&nbsp&nbsp <br/>";
		return texto;
	}
	
	private void atualizaPlayPause()
	{
		String texto = this.playPause.getText();
		if(texto.equals("►"))
			this.playPause.setText("||");
		else
			this.playPause.setText("►");
	}
	
	private void reiniciaSimulacao() 
	{
		this.playPause.setText("►");
	}
	
	// Construtor
	
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
		iniciar.addMouseListener(new ListenerMousePassou(this.corTexto, iniciar));
		
		voltar = new JButton("Voltar");
		voltar.setToolTipText("Retorna ao menu principal.");
		voltar.setFont(this.fonte);
		voltar.setForeground(this.corTexto);
		voltar.setBounds(20, 20, 180, 80);
		voltar.setFocusPainted(false);
		voltar.setMargin(new Insets(0, 0, 0, 0));
		voltar.setContentAreaFilled(false);
		voltar.setBorderPainted(this.bordasLigadas);
		voltar.setOpaque(false);
		voltar.addActionListener(new TratadorVoltar(this));
		voltar.setVisible(false);
		voltar.addMouseListener(new ListenerMousePassou(this.corTexto, voltar));
		
		info = new JButton("Info");
		info.setToolTipText("Informações sobre este trabalho.");
		info.setFont(this.fonte);
		info.setForeground(this.corTexto);
		info.setBounds(20, 220, 180, 80);
		info.setFocusPainted(false);
		info.setMargin(new Insets(0, 0, 0, 0));
		info.setContentAreaFilled(false);
		info.setBorderPainted(this.bordasLigadas);
		info.setOpaque(false);
		info.addActionListener(new TratadorInfo(this));
		info.addMouseListener(new ListenerMousePassou(this.corTexto, info));
		
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
		sair.addMouseListener(new ListenerMousePassou(this.corTexto, sair));
		
		playPause = new JButton("►");
		playPause.setToolTipText("Play");
		playPause.setFont(this.fonte);
		playPause.setForeground(this.corTexto);
		playPause.setBounds(100, 150, 50, 50);
		playPause.setFocusPainted(false);
		playPause.setMargin(new Insets(0, 0, 0, 0));
		playPause.setContentAreaFilled(false);
		playPause.setBorderPainted(this.bordasLigadas);
		playPause.setOpaque(false);
		playPause.addActionListener(new TratadorPlayPause(this));
		playPause.setVisible(false);
		playPause.addMouseListener(new ListenerMousePassou(this.corTexto, playPause));
		
		restart = new JButton("←"); 
		restart.setToolTipText("Reiniciar");
		restart.setFont(this.fonte);
		restart.setForeground(this.corTexto);
		restart.setBounds(50, 150, 50, 50);
		restart.setFocusPainted(false);
		restart.setMargin(new Insets(0, 0, 0, 0));
		restart.setContentAreaFilled(false);
		restart.setBorderPainted(this.bordasLigadas);
		restart.setOpaque(false);
		restart.addActionListener(new TratadorRestart(this));
		restart.setVisible(false);
		restart.addMouseListener(new ListenerMousePassou(this.corTexto, restart));
		
		container.add(iniciar);
		container.add(voltar);
		container.add(info);
		container.add(sair);
		container.add(playPause);
		container.add(restart);
		
		// Criação dos labels da interface
		
		descricao = new JLabel(this.textoDescricao());
		descricao.setFont(this.fonte);
		descricao.setForeground(Color.BLACK);
		descricao.setBounds(0, 0, 800, 600);
		descricao.setVisible(false);
		
		container.add(descricao);
		
		setVisible(true);
	}
	
	// Métodos públicos
	
	public Color getCorTexto()
	{
		return this.corTexto;
	}
	
	public void iniciar()
	{
		this.voltar.setVisible(true);
		this.playPause.setVisible(true);
		this.restart.setVisible(true);
		
		this.iniciar.setVisible(false);
		this.info.setVisible(false);
		this.sair.setVisible(false);
		
		this.controle.iniciar();
	}
	
	public void voltar() 
	{
		this.voltar.setVisible(false);
		this.descricao.setVisible(false);
		this.playPause.setVisible(false);
		this.restart.setVisible(false);
		
		this.iniciar.setVisible(true);
		this.info.setVisible(true);
		this.sair.setVisible(true);
		
		this.reiniciaSimulacao(); // Reinicia a interface de simulação
		this.restart(); // Quando volta ao menu principal reinicia
	}

	public void mostreInfo()
	{
		this.voltar.setVisible(true);
		this.descricao.setVisible(true);
		
		this.iniciar.setVisible(false);
		this.info.setVisible(false);
		this.sair.setVisible(false);
	}
	
	public void sair()
	{
		this.controle.sair();
	}

	public void playPause() 
	{
		this.atualizaPlayPause();
		this.controle.playPause();
	}

	public void restart() 
	{
		this.controle.restart();
	}
	
}
