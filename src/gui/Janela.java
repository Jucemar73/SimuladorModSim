package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Insets;
import java.util.ArrayList;

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
	private JButton funcaoEstatisticaTc;
	
	// Etiquetas
	
	private JLabel descricao;
	private JLabel estatisticas;
	private JLabel tituloSelecaoFuncoes;
	
	// TextField
	
	private JTextField campoTec;
	private JTextField campoTs;
	private JTextField campoTef;
	private JTextField campoTc;
	
	// Estatísticas
	
	private double tme1;
	private double tme2;
	private double tmos;
	private double tmef1;
	private double tmef2;
	private double tmes;
	private double ce;
	private double nf;
	private double nts;
	private double eb;
	
	// Variáveis de controle
	
	private int numFuncaoEstatisticaTec;
	private int numFuncaoEstatisticaTs;
	private int numFuncaoEstatisticaTef;
	private int numFuncaoEstatisticaTc;
	
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
	
	private void atualizaTextoEstatisticas() 
	{
        String texto =    
        		  "<html> &nbsp&nbsp&nbsp <br/>"
        		+ "<html> &nbsp&nbsp&nbsp <br/>"
        		+ "<html> &nbsp&nbsp&nbsp <br/>"
        		+ "<html> &nbsp&nbsp&nbsp <br/>"
        		+ "<html> &nbsp&nbsp&nbsp <br/>"
        		+ "<html> &nbsp&nbsp&nbsp <br/>"
        		+ "<html> &nbsp&nbsp&nbsp <br/>"
        		+ "<html> &nbsp&nbsp&nbsp <br/>"
        		+ "<html> &nbsp&nbsp&nbsp <br/>"
        		+ "<html> &nbsp&nbsp&nbsp <br/>"
        		+ "<html> &nbsp&nbsp&nbsp <br/>"
        		+ "<html> &nbsp&nbsp&nbsp <br/>"
        		+ "<html> &nbsp&nbsp&nbsp Estatísticas: <br/>"
        		+ "<html> &nbsp&nbsp&nbsp <br/>"
        		+ "<html> &nbsp&nbsp&nbsp Número médio de entidades na fila 1: " + this.tme1 + " <br/>"
        		+ "<html> &nbsp&nbsp&nbsp Número médio de entidades na fila 2: " + this.tme2 + " <br/>"
        		+ "<html> &nbsp&nbsp&nbsp Taxa média de ocupação dos servidores: " + this.tmos + " <br/>"
        		+ "<html> &nbsp&nbsp&nbsp Tempo médio de uma Entidade na fila 1: " + this.tmef1 + " <br/>"
        		+ "<html> &nbsp&nbsp&nbsp Tempo médio de uma Entidade na fila 2: " + this.tmef2 + " <br/>"
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
		this.playPause.setText("►");
		
		this.numFuncaoEstatisticaTec = -1;
		this.numFuncaoEstatisticaTs = -1;
		this.numFuncaoEstatisticaTef = -1;
		this.numFuncaoEstatisticaTc = -1;
		this.funcaoEstatisticaTec.setText("Modo estatístico");
		this.funcaoEstatisticaTs.setText("Modo estatístico");
		this.funcaoEstatisticaTef.setText("Modo estatístico");
		this.funcaoEstatisticaTc.setText("Modo estatístico");
		
		this.campoTec.setText("");
		this.campoTs.setText("");
		this.campoTef.setText("");
		this.campoTc.setText("");
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
					+ "nbsp&nbsp MODO TC <br/>";
		return texto;
	}
	// Construtor
	
	public Janela(Controle controle)
	{
		this.tme1 = 0;
		this.tme2 = 0;
		this.tmos = 0;
		this.tmef1 = 0;
		this.tmef2 = 0;
		this.tmes = 0;
		this.ce = 0;
		this.nf = 0;
		this.nts = 0;
		
		this.numFuncaoEstatisticaTec = -1;
		this.numFuncaoEstatisticaTs = -1;
		this.numFuncaoEstatisticaTef = -1;
		this.numFuncaoEstatisticaTc = -1;
		
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
		
		funcaoEstatisticaTec = new JButton("Estatística TEC");
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
		
		funcaoEstatisticaTs = new JButton("Estatística TS");
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
		
		funcaoEstatisticaTef = new JButton("Estatística TEF");
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
		
		funcaoEstatisticaTc = new JButton("Estatística TC");
		funcaoEstatisticaTc.setToolTipText("Clique para selecionar a função estatística para TC");
		funcaoEstatisticaTc.setFont(new Font("Arial", Font.PLAIN, 18));
		funcaoEstatisticaTc.setForeground(this.corTexto);
		funcaoEstatisticaTc.setBounds(640, 20, 140, 80);
		funcaoEstatisticaTc.setFocusPainted(false);
		funcaoEstatisticaTc.setMargin(new Insets(0, 0, 0, 0));
		funcaoEstatisticaTc.setContentAreaFilled(false);
		funcaoEstatisticaTc.setBorderPainted(this.bordasLigadas);
		funcaoEstatisticaTc.setOpaque(false);
		funcaoEstatisticaTc.addActionListener(new TratadorSelecionarFuncao(this, 3)); // 3 para TC
		funcaoEstatisticaTc.setVisible(false);
		funcaoEstatisticaTc.addMouseListener(new ListenerMousePassou(this.corTexto, funcaoEstatisticaTc));
		
		container.add(iniciar);
		container.add(voltar);
		container.add(info);
		container.add(sair);
		container.add(playPause);
		container.add(restart);
		container.add(funcaoEstatisticaTec);
		container.add(funcaoEstatisticaTs);
		container.add(funcaoEstatisticaTef);
		container.add(funcaoEstatisticaTc);
		
		// Criação dos labels da interface
		
		descricao = new JLabel(this.textoDescricao());
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
		
		container.add(descricao);
		container.add(estatisticas);
		container.add(tituloSelecaoFuncoes);
		
		// Criação dos text fields
		
		campoTec = new JTextField(5);
		campoTec.setToolTipText("Digite aqui os parâmetros.");
		campoTec.setForeground(Color.BLACK);
		campoTec.setBounds(275, 100, 40, 20);
		campoTec.setVisible(false);
		
		campoTs = new JTextField(5);
		campoTs.setToolTipText("Digite aqui os parâmetros.");
		campoTs.setForeground(Color.BLACK);
		campoTs.setBounds(415, 100, 40, 20);
		campoTs.setVisible(false);
		
		campoTef = new JTextField(5);
		campoTef.setToolTipText("Digite aqui os parâmetros.");
		campoTef.setForeground(Color.BLACK);
		campoTef.setBounds(555, 100, 40, 20);
		campoTef.setVisible(false);
		
		campoTc = new JTextField(5);
		campoTc.setToolTipText("Digite aqui os parâmetros.");
		campoTc.setForeground(Color.BLACK);
		campoTc.setBounds(695, 100, 40, 20);
		campoTc.setVisible(false);
		
		container.add(campoTec);
		container.add(campoTs);
		container.add(campoTef);
		container.add(campoTc);
				
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
		this.funcaoEstatisticaTec.setVisible(true);
		this.funcaoEstatisticaTs.setVisible(true);
		this.funcaoEstatisticaTef.setVisible(true);
		this.funcaoEstatisticaTc.setVisible(true);
		
		this.campoTec.setVisible(false); // TODO Verificar necessidade deste componente
		this.campoTs.setVisible(false); // TODO Caso sim, criar tratadores
		this.campoTef.setVisible(false);
		this.campoTc.setVisible(false);
		
		this.iniciar.setVisible(false);
		this.info.setVisible(false);
		this.sair.setVisible(false);
		
		this.controle.iniciar();
	}
	
	public void voltar() 
	{
		this.voltar.setVisible(false);
		this.descricao.setVisible(false);
		this.estatisticas.setVisible(false);
		this.tituloSelecaoFuncoes.setVisible(false);
		this.playPause.setVisible(false);
		this.restart.setVisible(false);
		this.funcaoEstatisticaTec.setVisible(false);
		this.funcaoEstatisticaTs.setVisible(false);
		this.funcaoEstatisticaTef.setVisible(false);
		this.funcaoEstatisticaTc.setVisible(false);
		
		this.campoTec.setVisible(false); 
		this.campoTs.setVisible(false);
		this.campoTef.setVisible(false);
		this.campoTc.setVisible(false);
		
		this.iniciar.setVisible(true);
		this.info.setVisible(true);
		this.sair.setVisible(true);
		
		this.reiniciaInterfaceSimulacao(); // Reinicia a interface de simulação
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
		this.reiniciaInterfaceSimulacao();
		this.controle.restart();
	}
	
	public void atualizaEstatisticas(ArrayList<Double> estatisticas)
	{
		this.tme1 = estatisticas.get(0);
		this.tme2 = estatisticas.get(1);
		this.tmos = estatisticas.get(2);
		this.tmef1 = estatisticas.get(3);
		this.tmef2 = estatisticas.get(4);
		this.tmes = estatisticas.get(5);
		this.ce = estatisticas.get(6);
		this.nf = estatisticas.get(7);
		this.nts = estatisticas.get(8);
		this.eb = estatisticas.get(9);
		
		this.atualizaTextoEstatisticas();
	}

	public void altereFuncaoEstatistica(int numBotao) // 0 para TEC, 1 para TS, 2 para TEF, 3 para TC
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
			this.numFuncaoEstatisticaTc++;
			if(this.numFuncaoEstatisticaTc > 4)
				this.numFuncaoEstatisticaTc = 0;
			if(this.numFuncaoEstatisticaTc == 0)
			{
				this.funcaoEstatisticaTc.setText("Constante");
				this.controle.definaFuncaoEstatisticaTc(this.numFuncaoEstatisticaTc);
			}
			if(this.numFuncaoEstatisticaTc == 1)
			{
				this.funcaoEstatisticaTc.setText("Exponencial");
				this.controle.definaFuncaoEstatisticaTc(this.numFuncaoEstatisticaTc);
			}
			if(this.numFuncaoEstatisticaTc == 2)
			{
				this.funcaoEstatisticaTc.setText("Normal");
				this.controle.definaFuncaoEstatisticaTc(this.numFuncaoEstatisticaTc);
			}
			if(this.numFuncaoEstatisticaTc == 3)
			{
				this.funcaoEstatisticaTc.setText("Triangular");
				this.controle.definaFuncaoEstatisticaTc(this.numFuncaoEstatisticaTc);
			}
			if(this.numFuncaoEstatisticaTc == 4)
			{
				this.funcaoEstatisticaTc.setText("Uniforme");
				this.controle.definaFuncaoEstatisticaTc(this.numFuncaoEstatisticaTc);
			}
		}
	}
	
}
