package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

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
	
	private JButton funcaoEstatisticaTec;
	private JButton funcaoEstatisticaTs;
	private JButton funcaoEstatisticaTef;
	private JButton funcaoEstatisticaTf;
	
	private JButton unidadeTempo;
	private JButton velocidadeExec;
	
	// Etiquetas
	
	private JLabel descricao;
	private JLabel estatisticas;
	private JLabel tituloSelecaoFuncoes;
	private JLabel tituloSelecaoTempo;
	private JLabel tituloSelecaoVelocidade;
	
	// TextField
	
	private JTextField campoTec;
	private JTextField campoTs;
	private JTextField campoTef;
	private JTextField campoTf;
	
	private JTextField campoTempo;
	
	// Estatísticas
	
	private double nmef1;
	private double nmef2;
	private double nmefTotal;
	private double tmos1;
	private double tmos2;
	private double tmef1;
	private double tmef2;
	private double tf1;
	private double tf2;
	private double mp1;
	private double mp2;
	
	private double tmes;
	private double ce;
	private double nf;
	private double nts;
	private double eb;
	
	private int cicloAtual;
	
	// Variáveis de controle
	
	private int numFuncaoEstatisticaTec;
	private int numFuncaoEstatisticaTs;
	private int numFuncaoEstatisticaTef;
	private int numFuncaoEstatisticaTf;
	
	private int numUnidadeTempo;
	private int numVelocidade;
	
	private boolean pausa;
	
	// Métodos privados
	
	private String definaTextoDescricao()
	{
        String texto =    "<html> &nbsp&nbsp&nbsp UFSC - Universidade Federal de Santa Catarina <br/>"
        				+ "<html> &nbsp&nbsp&nbsp INE5425 - Modelagem & Simulação <br/>"
        				+ "<html> &nbsp&nbsp&nbsp <br/>"
        				+ "<html> &nbsp&nbsp&nbsp Trabalho I - Simulador de 2 servidores com 2 filas<br/>"
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
	
	private String definaTextoTituloSelecaoFuncoes() 
	{
		String texto = "<html> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
					+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
					+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
					+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
					+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
					+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
					+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
					+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
					+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp MODO"
					+ " TEC &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
					+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
					+ "&nbsp&nbsp MODO TS &nbsp&nbsp&nbsp&"
					+ "nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&"
					+ "nbsp&nbsp MODO TEF &nbsp&nbsp&nbsp&nbsp&"
					+ "nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&"
					+ "nbsp&nbsp MODO TF <br/>";
		return texto;
	}
	
	private String definaTextoTituloSelecaoTempo() 
	{
		String texto = "<html> &nbsp&nbsp&nbsp&nbsp TEMPO </br>";
		return texto;
	}
	
	private String definaTextoTituloSelecaoVelocidade() 
	{
		String texto = "<html> VELOCIDADE DE SIMULAÇÃO </br>";
		return texto;
	}
	
	private void atualizaTextoEstatisticas() 
	{
        String texto =    
        		  "<html> &nbsp&nbsp&nbsp <br/>"
        		+ "<html> &nbsp&nbsp&nbsp <br/>"
        		+ "<html> &nbsp&nbsp&nbsp <br/>"
        		+ "<html> &nbsp&nbsp&nbsp <br/>"
        		+ "<html> &nbsp&nbsp&nbsp <br/>"
        		+ "<html> &nbsp&nbsp&nbsp <br/>"
        		+ "<html> &nbsp&nbsp&nbsp Estatísticas: <br/>"
        		+ "<html> &nbsp&nbsp&nbsp <br/>"
        		
        		+ "<html> &nbsp&nbsp&nbsp Número médio de entidades na fila 1: " + this.nmef1 
        		+ " &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp " 
        		+ " &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp " 
        		+ " &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp "         		
        		+ " Ciclo Atual: &nbsp&nbsp "         		
        		+ this.cicloAtual + " <br/>"
        		+ "<html> &nbsp&nbsp&nbsp Número médio de entidades na fila 2: " + this.nmef2 + " <br/>"
        		+ "<html> &nbsp&nbsp&nbsp Média total de entidades na fila : " + this.nmefTotal + " <br/>"
        		
        		+ "<html> &nbsp&nbsp&nbsp Taxa média de ocupação do servidor 1: " + this.tmos1 + " <br/>"
        		+ "<html> &nbsp&nbsp&nbsp Taxa média de ocupação do servidor 2: " + this.tmos2 + " <br/>"
        		+ "<html> &nbsp&nbsp&nbsp Tempo médio de uma entidade na fila 1: " + this.tmef1 + " <br/>"
        		+ "<html> &nbsp&nbsp&nbsp Tempo médio de uma entidade na fila 2: " + this.tmef2 + " <br/>"
        		
        		+ "<html> &nbsp&nbsp&nbsp Tempo de falha 1: " + this.tf1 + " <br/>"
        		+ "<html> &nbsp&nbsp&nbsp Tempo de falha 2: " + this.tf2 + " <br/>"
        		
        		+ "<html> &nbsp&nbsp&nbsp Média ponderada 1: " + this.mp1 + " <br/>"
        		+ "<html> &nbsp&nbsp&nbsp Média ponderada 2: " + this.mp2 + " <br/>"
        		
        		+ "<html> &nbsp&nbsp&nbsp Tempo médio das entidades no Sistema: " + this.tmes + " <br/>"
        		+ "<html> &nbsp&nbsp&nbsp Contador de entidades: " + this.ce + " <br/>"
        		+ "<html> &nbsp&nbsp&nbsp Número de falhas: " + this.nf + " <br/>"
        		+ "<html> &nbsp&nbsp&nbsp Número de trocas de servidor: " + this.nts + " <br/>"
				+ "<html> &nbsp&nbsp&nbsp Número de entidades bloqueadas: " + this.eb + " <br/>";
        this.estatisticas.setText(texto);
	}
	
	private void atualizaPlayPause()
	{
		String texto = this.playPause.getText();
		if(texto.equals("►"))
			this.playPause.setText("||");
		else
			this.playPause.setText("►");
	}
	
	private void reiniciaInterfaceSimulacao() 
	{
		this.numFuncaoEstatisticaTec = -1;
		this.numFuncaoEstatisticaTs = -1;
		this.numFuncaoEstatisticaTef = -1;
		this.numFuncaoEstatisticaTf = -1;
		this.numUnidadeTempo = -1;
		this.numVelocidade = -1;
		this.pausa = true;
		
		this.playPause.setText("►");
		this.funcaoEstatisticaTec.setText("Modo estatístico");
		this.funcaoEstatisticaTs.setText("Modo estatístico");
		this.funcaoEstatisticaTef.setText("Modo estatístico");
		this.funcaoEstatisticaTf.setText("Modo estatístico");
		this.unidadeTempo.setText("Unidade temporal");
		this.velocidadeExec.setText("Velocidade");
		
		// Reinicia as estatísticas
		
		this.nmef1 = 0;
		this.nmef2 = 0;
		this.nmefTotal = 0;
		this.tmos1 = 0;
		this.tmos2 = 0;
		this.tmef1 = 0;
		this.tmef2 = 0;
		this.tf1 = 0;
		this.tf2 = 0;
		this.mp1 = 0;
		this.mp2 = 0;
		this.tmes = 0;
		this.ce = 0;
		this.nf = 0;
		this.nts = 0;
		this.eb = 0;
		
		this.campoTec.setText("");
		this.campoTs.setText("");
		this.campoTef.setText("");
		this.campoTf.setText("");
		this.campoTempo.setText("");
	}

	
	// Construtor
	
	public Janela(Controle controle)
	{
		this.nmef1 = 0;
		this.nmef2 = 0;
		this.nmefTotal = 0;
		this.tmos1 = 0;
		this.tmos2 = 0;
		this.tmef1 = 0;
		this.tmef2 = 0;
		this.tf1 = 0;
		this.tf2 = 0;
		this.mp1 = 0;
		this.mp2 = 0;
		this.tmes = 0;
		this.ce = 0;
		this.nf = 0;
		this.nts = 0;
		
		this.cicloAtual = 0;
		
		this.numFuncaoEstatisticaTec = -1;
		this.numFuncaoEstatisticaTs = -1;
		this.numFuncaoEstatisticaTef = -1;
		this.numFuncaoEstatisticaTf = -1;
		
		this.numUnidadeTempo = -1;
		this.numVelocidade = -1;
		
		this.pausa = true;
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Trabalho I - Modelagem & Simulação");
		this.setSize(800, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		// Parâmetros de controle
		
		this.controle = controle;
		this.corTexto = Color.BLACK;
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
		info.setToolTipText("Informações sobre a autoria do software.");
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
		playPause.setBounds(110, 110, 50, 50);
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
		restart.setBounds(60, 110, 50, 50);
		restart.setFocusPainted(false);
		restart.setMargin(new Insets(0, 0, 0, 0));
		restart.setContentAreaFilled(false);
		restart.setBorderPainted(this.bordasLigadas);
		restart.setOpaque(false);
		restart.addActionListener(new TratadorRestart(this));
		restart.setVisible(false);
		restart.addMouseListener(new ListenerMousePassou(this.corTexto, restart));
		
		funcaoEstatisticaTec = new JButton("Modo estatístico");
		funcaoEstatisticaTec.setToolTipText("Clique para selecionar a função estatística para TEC");
		funcaoEstatisticaTec.setFont(new Font("Arial", Font.PLAIN, 18));
		funcaoEstatisticaTec.setForeground(this.corTexto);
		funcaoEstatisticaTec.setBounds(220, 20, 140, 80);
		funcaoEstatisticaTec.setFocusPainted(false);
		funcaoEstatisticaTec.setMargin(new Insets(0, 0, 0, 0));
		funcaoEstatisticaTec.setContentAreaFilled(false);
		funcaoEstatisticaTec.setBorderPainted(this.bordasLigadas);
		funcaoEstatisticaTec.setOpaque(false);
		funcaoEstatisticaTec.addActionListener(new TratadorSelecionarFuncao(this, 0)); // 0 para TEC
		funcaoEstatisticaTec.setVisible(false);
		funcaoEstatisticaTec.addMouseListener(new ListenerMousePassou(this.corTexto, funcaoEstatisticaTec));
		
		funcaoEstatisticaTs = new JButton("Modo estatístico");
		funcaoEstatisticaTs.setToolTipText("Clique para selecionar a função estatística para TS");
		funcaoEstatisticaTs.setFont(new Font("Arial", Font.PLAIN, 18));
		funcaoEstatisticaTs.setForeground(this.corTexto);
		funcaoEstatisticaTs.setBounds(360, 20, 140, 80);
		funcaoEstatisticaTs.setFocusPainted(false);
		funcaoEstatisticaTs.setMargin(new Insets(0, 0, 0, 0));
		funcaoEstatisticaTs.setContentAreaFilled(false);
		funcaoEstatisticaTs.setBorderPainted(this.bordasLigadas);
		funcaoEstatisticaTs.setOpaque(false);
		funcaoEstatisticaTs.addActionListener(new TratadorSelecionarFuncao(this, 1)); // 1 para TS
		funcaoEstatisticaTs.setVisible(false);
		funcaoEstatisticaTs.addMouseListener(new ListenerMousePassou(this.corTexto, funcaoEstatisticaTs));
		
		funcaoEstatisticaTef = new JButton("Modo estatístico");
		funcaoEstatisticaTef.setToolTipText("Clique para selecionar a função estatística para TEF");
		funcaoEstatisticaTef.setFont(new Font("Arial", Font.PLAIN, 18));
		funcaoEstatisticaTef.setForeground(this.corTexto);
		funcaoEstatisticaTef.setBounds(500, 20, 140, 80);
		funcaoEstatisticaTef.setFocusPainted(false);
		funcaoEstatisticaTef.setMargin(new Insets(0, 0, 0, 0));
		funcaoEstatisticaTef.setContentAreaFilled(false);
		funcaoEstatisticaTef.setBorderPainted(this.bordasLigadas);
		funcaoEstatisticaTef.setOpaque(false);
		funcaoEstatisticaTef.addActionListener(new TratadorSelecionarFuncao(this, 2)); // 2 para TEF
		funcaoEstatisticaTef.setVisible(false);
		funcaoEstatisticaTef.addMouseListener(new ListenerMousePassou(this.corTexto, funcaoEstatisticaTef));
		
		funcaoEstatisticaTf = new JButton("Modo estatístico");
		funcaoEstatisticaTf.setToolTipText("Clique para selecionar a função estatística para TF");
		funcaoEstatisticaTf.setFont(new Font("Arial", Font.PLAIN, 18));
		funcaoEstatisticaTf.setForeground(this.corTexto);
		funcaoEstatisticaTf.setBounds(640, 20, 140, 80);
		funcaoEstatisticaTf.setFocusPainted(false);
		funcaoEstatisticaTf.setMargin(new Insets(0, 0, 0, 0));
		funcaoEstatisticaTf.setContentAreaFilled(false);
		funcaoEstatisticaTf.setBorderPainted(this.bordasLigadas);
		funcaoEstatisticaTf.setOpaque(false);
		funcaoEstatisticaTf.addActionListener(new TratadorSelecionarFuncao(this, 3)); // 3 para TC
		funcaoEstatisticaTf.setVisible(false);
		funcaoEstatisticaTf.addMouseListener(new ListenerMousePassou(this.corTexto, funcaoEstatisticaTf));
		
		unidadeTempo = new JButton("Unidade temporal");
		unidadeTempo.setToolTipText("Clique para selecionar a unidade de tempo da simulação.");
		unidadeTempo.setFont(new Font("Arial", Font.PLAIN, 18));
		unidadeTempo.setForeground(this.corTexto);
		unidadeTempo.setBounds(550, 300, 200, 100);
		unidadeTempo.setFocusPainted(false);
		unidadeTempo.setMargin(new Insets(0, 0, 0, 0));
		unidadeTempo.setContentAreaFilled(false);
		unidadeTempo.setBorderPainted(this.bordasLigadas);
		unidadeTempo.setOpaque(false);
		unidadeTempo.addActionListener(new TratadorSelecionarTempo(this));
		unidadeTempo.setVisible(false);
		unidadeTempo.addMouseListener(new ListenerMousePassou(this.corTexto, unidadeTempo));
		
		velocidadeExec = new JButton("Velocidade");
		velocidadeExec.setToolTipText("Clique para selecionar a velocidade da execução da simulação.");
		velocidadeExec.setFont(new Font("Arial", Font.PLAIN, 18));
		velocidadeExec.setForeground(this.corTexto);
		velocidadeExec.setBounds(550, 460, 200, 100);
		velocidadeExec.setFocusPainted(false);
		velocidadeExec.setMargin(new Insets(0, 0, 0, 0));
		velocidadeExec.setContentAreaFilled(false);
		velocidadeExec.setBorderPainted(this.bordasLigadas);
		velocidadeExec.setOpaque(false);
		velocidadeExec.addActionListener(new TratadorSelecionarVelocidade(this));
		velocidadeExec.setVisible(false);
		velocidadeExec.addMouseListener(new ListenerMousePassou(this.corTexto, velocidadeExec));
		
		container.add(iniciar);
		container.add(voltar);
		container.add(info);
		container.add(sair);
		container.add(playPause);
		container.add(restart);
		container.add(funcaoEstatisticaTec);
		container.add(funcaoEstatisticaTs);
		container.add(funcaoEstatisticaTef);
		container.add(funcaoEstatisticaTf);
		container.add(unidadeTempo);
		container.add(velocidadeExec);
		
		// Criação dos labels da interface
		
		descricao = new JLabel(this.definaTextoDescricao());
		descricao.setFont(this.fonte);
		descricao.setForeground(Color.BLACK);
		descricao.setBounds(0, 0, 800, 600);
		descricao.setVisible(false);
		
		estatisticas = new JLabel();
		this.atualizaTextoEstatisticas();
		estatisticas.setFont(new Font("Arial", Font.PLAIN, 18));
		estatisticas.setForeground(Color.BLACK);
		estatisticas.setBounds(0, 0, 800, 600);
		estatisticas.setVisible(false);
		
		tituloSelecaoFuncoes = new JLabel(this.definaTextoTituloSelecaoFuncoes());
		tituloSelecaoFuncoes.setFont(new Font("Arial", Font.PLAIN, 16));
		tituloSelecaoFuncoes.setForeground(Color.BLACK);
		tituloSelecaoFuncoes.setBounds(0, 0, 800, 20);
		tituloSelecaoFuncoes.setVisible(false);
		
		tituloSelecaoTempo = new JLabel(this.definaTextoTituloSelecaoTempo());
		tituloSelecaoTempo.setFont(new Font("Arial", Font.PLAIN, 16));
		tituloSelecaoTempo.setForeground(Color.BLACK);
		tituloSelecaoTempo.setBounds(600, 280, 200, 20);
		tituloSelecaoTempo.setVisible(false);
		
		tituloSelecaoVelocidade = new JLabel(this.definaTextoTituloSelecaoVelocidade());
		tituloSelecaoVelocidade.setFont(new Font("Arial", Font.PLAIN, 16));
		tituloSelecaoVelocidade.setForeground(Color.BLACK);
		tituloSelecaoVelocidade.setBounds(540, 430, 300, 40);
		tituloSelecaoVelocidade.setVisible(false);
		
		container.add(descricao);
		container.add(estatisticas);
		container.add(tituloSelecaoFuncoes);
		container.add(tituloSelecaoTempo);
		container.add(tituloSelecaoVelocidade);
		
		// Criação dos text fields
		
		campoTec = new JTextField();
		campoTec.setToolTipText("Digite aqui os parâmetros de TEC.");
		campoTec.setForeground(Color.BLACK);
		campoTec.setBounds(275, 100, 40, 20);
		campoTec.setVisible(false);
		campoTec.addKeyListener(new ListenerTecladoDefinaValor(this, 0));
		
		campoTs = new JTextField();
		campoTs.setToolTipText("Digite aqui os parâmetros de TS.");
		campoTs.setForeground(Color.BLACK);
		campoTs.setBounds(415, 100, 40, 20);
		campoTs.setVisible(false);
		campoTs.addKeyListener(new ListenerTecladoDefinaValor(this, 1));
		
		campoTef = new JTextField();
		campoTef.setToolTipText("Digite aqui os parâmetros de TEF.");
		campoTef.setForeground(Color.BLACK);
		campoTef.setBounds(555, 100, 40, 20);
		campoTef.setVisible(false);
		campoTef.addKeyListener(new ListenerTecladoDefinaValor(this, 2));
		
		campoTf = new JTextField();
		campoTf.setToolTipText("Digite aqui os parâmetros de TF.");
		campoTf.setForeground(Color.BLACK);
		campoTf.setBounds(695, 100, 40, 20);
		campoTf.setVisible(false);
		campoTf.addKeyListener(new ListenerTecladoDefinaValor(this, 3));
		
		campoTempo = new JTextField();
		campoTempo.setToolTipText("Digite aqui o valor do tempo para simulação.");
		campoTempo.setForeground(Color.BLACK);
		campoTempo.setBounds(635, 400, 40, 20);
		campoTempo.setVisible(false);
		campoTempo.addKeyListener(new ListenerTecladoTempo(this));
		
		container.add(campoTec);
		container.add(campoTs);
		container.add(campoTef);
		container.add(campoTf);
		container.add(campoTempo);
				
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
		this.estatisticas.setVisible(true);
		this.tituloSelecaoFuncoes.setVisible(true);
		this.tituloSelecaoTempo.setVisible(true);
		this.tituloSelecaoVelocidade.setVisible(true);
		this.funcaoEstatisticaTec.setVisible(true);
		this.funcaoEstatisticaTs.setVisible(true);
		this.funcaoEstatisticaTef.setVisible(true);
		this.funcaoEstatisticaTf.setVisible(true);
		this.unidadeTempo.setVisible(true);
		this.velocidadeExec.setVisible(true);
		
		this.campoTec.setVisible(true); 
		this.campoTs.setVisible(true); 
		this.campoTef.setVisible(true);
		this.campoTf.setVisible(true);
		this.campoTempo.setVisible(true);
		
		this.iniciar.setVisible(false);
		this.info.setVisible(false);
		this.sair.setVisible(false);
		
		this.controle.iniciarSimulador(); // Cria o objeto para simulação
	}
	
	public void voltar() 
	{
		this.voltar.setVisible(false);
		this.descricao.setVisible(false);
		this.estatisticas.setVisible(false);
		this.tituloSelecaoFuncoes.setVisible(false);
		this.tituloSelecaoTempo.setVisible(false);
		this.tituloSelecaoVelocidade.setVisible(false);
		this.playPause.setVisible(false);
		this.restart.setVisible(false);
		this.funcaoEstatisticaTec.setVisible(false);
		this.funcaoEstatisticaTs.setVisible(false);
		this.funcaoEstatisticaTef.setVisible(false);
		this.funcaoEstatisticaTf.setVisible(false);
		this.unidadeTempo.setVisible(false);
		this.velocidadeExec.setVisible(false);
		
		this.campoTec.setVisible(false); 
		this.campoTs.setVisible(false);
		this.campoTef.setVisible(false);
		this.campoTf.setVisible(false);
		this.campoTempo.setVisible(false);
		
		this.iniciar.setVisible(true);
		this.info.setVisible(true);
		this.sair.setVisible(true);
		
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
		this.pausa = !pausa;
		this.atualizaPlayPause();
		this.controle.playPause(pausa);
	}

	public void restart() 
	{
		this.reiniciaInterfaceSimulacao();
		this.controle.restart();
	}
	
	public void atualizaEstatisticas(ArrayList<Double> estatisticas)
	{
		this.cicloAtual++;
		
		System.out.println("CICLO ATUAL = " + this.cicloAtual);
		
		//this.nmef1 = estatisticas.get(0);
		//this.nmef2 = estatisticas.get(1);
		//this.nmefTotal = estatisticas.get(2);
		//this.tmos1 = estatisticas.get(3);
		//this.tmos2 = estatisticas.get(4);
		//this.tmef1 = estatisticas.get(5);
		//.tmef2 = estatisticas.get(6);
		//this.tf1 = estatisticas.get(7);
		//this.tf2 = estatisticas.get(8);
		//this.mp1 = estatisticas.get(9);
		//this.mp2 = estatisticas.get(10);
		
		// TODO outras estatísticas no modelo
		
		//this.tmes = estatisticas.get(11);
		//this.ce = estatisticas.get(12);
		//this.nf = estatisticas.get(13);
		//this.nts = estatisticas.get(14);
		//this.eb = estatisticas.get(15);
		
		this.atualizaTextoEstatisticas();
	}

	public void selecionarFuncaoEstatistica(int numBotao) // 0 para TEC, 1 para TS, 2 para TEF, 3 para TF
	{
		if(numBotao == 0)
		{
			this.numFuncaoEstatisticaTec++;
			if(this.numFuncaoEstatisticaTec > 4)
				this.numFuncaoEstatisticaTec = 0;
			if(this.numFuncaoEstatisticaTec == 0)
			{
				this.funcaoEstatisticaTec.setText("Constante");
				this.controle.definaFuncaoEstatisticaTec(this.numFuncaoEstatisticaTec);
			}
			if(this.numFuncaoEstatisticaTec == 1)
			{
				this.funcaoEstatisticaTec.setText("Exponencial");
				this.controle.definaFuncaoEstatisticaTec(this.numFuncaoEstatisticaTec);
			}
			if(this.numFuncaoEstatisticaTec == 2)
			{
				this.funcaoEstatisticaTec.setText("Normal");
				this.controle.definaFuncaoEstatisticaTec(this.numFuncaoEstatisticaTec);
			}
			if(this.numFuncaoEstatisticaTec == 3)
			{
				this.funcaoEstatisticaTec.setText("Triangular");
				this.controle.definaFuncaoEstatisticaTec(this.numFuncaoEstatisticaTec);
			}
			if(this.numFuncaoEstatisticaTec == 4)
			{
				this.funcaoEstatisticaTec.setText("Uniforme");
				this.controle.definaFuncaoEstatisticaTec(this.numFuncaoEstatisticaTec);
			}
		}
		if(numBotao == 1)
		{
			this.numFuncaoEstatisticaTs++;
			if(this.numFuncaoEstatisticaTs > 4)
				this.numFuncaoEstatisticaTs = 0;
			if(this.numFuncaoEstatisticaTs == 0)
			{
				this.funcaoEstatisticaTs.setText("Constante");
				this.controle.definaFuncaoEstatisticaTs(this.numFuncaoEstatisticaTs);
			}
			if(this.numFuncaoEstatisticaTs == 1)
			{
				this.funcaoEstatisticaTs.setText("Exponencial");
				this.controle.definaFuncaoEstatisticaTs(this.numFuncaoEstatisticaTs);
			}
			if(this.numFuncaoEstatisticaTs == 2)
			{
				this.funcaoEstatisticaTs.setText("Normal");
				this.controle.definaFuncaoEstatisticaTs(this.numFuncaoEstatisticaTs);
			}
			if(this.numFuncaoEstatisticaTs == 3)
			{
				this.funcaoEstatisticaTs.setText("Triangular");
				this.controle.definaFuncaoEstatisticaTs(this.numFuncaoEstatisticaTs);
			}
			if(this.numFuncaoEstatisticaTs == 4)
			{
				this.funcaoEstatisticaTs.setText("Uniforme");
				this.controle.definaFuncaoEstatisticaTs(this.numFuncaoEstatisticaTs);
			}
		}
		if(numBotao == 2)
		{
			this.numFuncaoEstatisticaTef++;
			if(this.numFuncaoEstatisticaTef > 4)
				this.numFuncaoEstatisticaTef = 0;
			if(this.numFuncaoEstatisticaTef == 0)
			{
				this.funcaoEstatisticaTef.setText("Constante");
				this.controle.definaFuncaoEstatisticaTef(this.numFuncaoEstatisticaTef);
			}
			if(this.numFuncaoEstatisticaTef == 1)
			{
				this.funcaoEstatisticaTef.setText("Exponencial");
				this.controle.definaFuncaoEstatisticaTef(this.numFuncaoEstatisticaTef);
			}
			if(this.numFuncaoEstatisticaTef == 2)
			{
				this.funcaoEstatisticaTef.setText("Normal");
				this.controle.definaFuncaoEstatisticaTef(this.numFuncaoEstatisticaTef);
			}
			if(this.numFuncaoEstatisticaTef == 3)
			{
				this.funcaoEstatisticaTef.setText("Triangular");
				this.controle.definaFuncaoEstatisticaTef(this.numFuncaoEstatisticaTef);
			}
			if(this.numFuncaoEstatisticaTef == 4)
			{
				this.funcaoEstatisticaTef.setText("Uniforme");
				this.controle.definaFuncaoEstatisticaTef(this.numFuncaoEstatisticaTef);
			}
		}
		if(numBotao == 3)
		{
			this.numFuncaoEstatisticaTf++;
			if(this.numFuncaoEstatisticaTf > 4)
				this.numFuncaoEstatisticaTf = 0;
			if(this.numFuncaoEstatisticaTf == 0)
			{
				this.funcaoEstatisticaTf.setText("Constante");
				this.controle.definaFuncaoEstatisticaTf(this.numFuncaoEstatisticaTf);
			}
			if(this.numFuncaoEstatisticaTf == 1)
			{
				this.funcaoEstatisticaTf.setText("Exponencial");
				this.controle.definaFuncaoEstatisticaTf(this.numFuncaoEstatisticaTf);
			}
			if(this.numFuncaoEstatisticaTf == 2)
			{
				this.funcaoEstatisticaTf.setText("Normal");
				this.controle.definaFuncaoEstatisticaTf(this.numFuncaoEstatisticaTf);
			}
			if(this.numFuncaoEstatisticaTf == 3)
			{
				this.funcaoEstatisticaTf.setText("Triangular");
				this.controle.definaFuncaoEstatisticaTf(this.numFuncaoEstatisticaTf);
			}
			if(this.numFuncaoEstatisticaTf == 4)
			{
				this.funcaoEstatisticaTf.setText("Uniforme");
				this.controle.definaFuncaoEstatisticaTf(this.numFuncaoEstatisticaTf);
			}
		}
	}

	public void definaParametroEstatistico(int num) // 0 para TEC, 1 para TS, 2 para TEF, 3 para TC
	{
		if(num == 0) // TEC
			this.controle.definaParametroTec(this.campoTec.getText());
		if(num == 1) // TS
			this.controle.definaParametroTs(this.campoTs.getText());
		if(num == 2) // TEF
			this.controle.definaParametroTef(this.campoTef.getText());
		if(num == 3) // TF
			this.controle.definaParametroTf(this.campoTf.getText());
	}
	
	public void selecionarTempo() 
	{
		this.numUnidadeTempo++;
		if(this.numUnidadeTempo > 2)
		{
			this.numUnidadeTempo = 0;
		}
		if(this.numUnidadeTempo == 0) // Segundos
		{
			this.unidadeTempo.setText("Segundos");
			this.controle.definaUnidadeTempo(this.numUnidadeTempo);
		}
		if(this.numUnidadeTempo == 1) // Horas
		{
			this.unidadeTempo.setText("Minutos");
			this.controle.definaUnidadeTempo(this.numUnidadeTempo);
		}
		if(this.numUnidadeTempo == 2) // Dias
		{
			this.unidadeTempo.setText("Horas");
			this.controle.definaUnidadeTempo(this.numUnidadeTempo);
		}
	}

	public void definaParametroTempo() 
	{
		this.controle.definaParametroTempo(this.campoTempo.getText()); // Manda a string para ser lida
	}

	public void selecionarVelocidade() 
	{
		this.numVelocidade++;
		if(this.numVelocidade > 3)
			this.numVelocidade = 0;
		if(this.numVelocidade == 0)
		{
			this.velocidadeExec.setText("Lento");
			this.controle.definaVelocidadeSimulacao(this.numVelocidade);
		}
		if(this.numVelocidade == 1)
		{
			this.velocidadeExec.setText("Normal");
			this.controle.definaVelocidadeSimulacao(this.numVelocidade);
		}
		if(this.numVelocidade == 2)
		{
			this.velocidadeExec.setText("Rápido");
			this.controle.definaVelocidadeSimulacao(this.numVelocidade);
		}
		if(this.numVelocidade == 3)
		{
			this.velocidadeExec.setText("Instantâneo");
			this.controle.definaVelocidadeSimulacao(this.numVelocidade);
		}
	}
	
}
