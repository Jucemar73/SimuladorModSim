package modelo;

import java.util.ArrayList;

import controle.Controle;

public class Simulador 
{
	// Objetos (7)
	
    private Servidor serv1;
    private Servidor serv2;
    private Gerador gerador;
    private Calculador calculador;
    private Entidade entidadeNova;
    private Controle controle;
    private Estado proxEstado;
    
    // Display Janela (2)
    
    private int unidadeDeTempo; // 0 para seg, 1 para min, 2 para horas
    private long delay;
    
    // Argumentos (16)
    
    private int tipoTec;
    private int tipoTs;
    private int tipoTef;
    private int tipoTf;
    private double arg0Tec;
    private double arg1Tec;
    private double arg2Tec;
    private double arg0Ts;
    private double arg1Ts;
    private double arg2Ts;
    private double arg0Tef;
    private double arg1Tef;
    private double arg2Tef;
    private double arg0Tf;
    private double arg1Tf;
    private double arg2Tf;
    
    // Discretos (7)
    
    private double proxTempoDeChegada;
    private double proxTempoDeFalha1;
    private double proxTempoDeFalha2;
    private double proxTempoDeReparo1;
    private double proxTempoDeReparo2;
    private double proxTempoDeTerminoDeServico1;
    private double proxTempoDeTerminoDeServico2;
    
    // Estatísticas (6)
    
    private int numBloqueios1; // Número total de entidades tipo 1 bloqueadas.
    private int numBloqueios2; // Número total de entidades tipo 2 bloqueadas.
    private int numFalhas1;    // Número total de falhas do serv1.
    private int numFalhas2;    // Número total de falhas do serv2.
    private int numTrocas1;    // Número total de trocas tipo 1.
    private int numTrocas2;    // Número total de trocas tipo 2.
    
    // Tempo
    
    private double tempoAtual;    // Tempo atual da simulação
    private double tempoFinal;    // Tempo final da simulação (máximo)
    private double tempoOcupado1; // Tempo total que o serv1 ficou ocupado
    private double tempoOcupado2; // Tempo total que o serv2 ficou ocupado
    private double tempoAtivo1;   // Tempo total que o serv1 ficou ativo
    private double tempoAtivo2;   // Tempo total que o serv2 ficou ativo
    private double tempoFalha1;   // Tempo total que o serv1 ficou falhado
    private double tempoFalha2;   // Tempo total que o serv2 ficou falhado
    
    // Construtor
    
    public Simulador(Controle controle) 
    {
    	// Objetos (7)
    	
        serv1 = new Servidor("1");
        serv2 = new Servidor("2");
        calculador = new Calculador();
        gerador = new Gerador(calculador);
        entidadeNova = null;
        this.controle = controle;
        this.proxEstado = Estado.CHEGADA;
        
        // Display Janela (2)
        
        this.unidadeDeTempo = 0;
        this.delay = 0; 
        
        // Argumentos (16)
        
        this.tipoTec = 0;
        this.tipoTs  = 0;
        this.tipoTef = 0;
        this.tipoTf  = 0;
        this.arg0Tec = 0;
        this.arg1Tec = 0;
        this.arg2Tec = 0;
        this.arg0Ts  = 0;
        this.arg1Ts  = 0;
        this.arg2Ts  = 0;
        this.arg0Tef = 0;
        this.arg1Tef = 0;
        this.arg2Tef = 0;
        this.arg0Tf  = 0;
        this.arg1Tf  = 0;
        this.arg2Tf  = 0;
        
        // Discretos (7)
        
        this.proxTempoDeChegada = 0;
        this.proxTempoDeFalha1 = Double.MAX_VALUE; 
        this.proxTempoDeFalha2 = Double.MAX_VALUE;
        this.proxTempoDeReparo1 = Double.MAX_VALUE; 
        this.proxTempoDeReparo2 = Double.MAX_VALUE; 
        this.proxTempoDeTerminoDeServico1 = Double.MAX_VALUE; 
        this.proxTempoDeTerminoDeServico2 = Double.MAX_VALUE; 
        
        // Estatísticas (6)
        
        this.numBloqueios1 = 0;
        this.numBloqueios2 = 0;
        this.numFalhas1 = 0;
        this.numFalhas2 = 0;
        this.numTrocas1 = 0;
        this.numTrocas2 = 0;
        
        // Tempo (8)
        
        this.tempoAtual = 0;
        this.tempoFinal = 0;
        this.tempoOcupado1 = 0;
        this.tempoOcupado2 = 0;
        this.tempoAtivo1 = 0;
        this.tempoAtivo2 = 0;
        this.tempoFalha1 = 0;
        this.tempoFalha2 = 0;
    }

    // Gets
    
    public Servidor getServ1() 
    {
        return this.serv1;
    }

    public Servidor getServ2() 
    {
        return this.serv2;
    }
    
    public long getDelay() 
    {
    	return this.delay;
    }
    
    public double getTempoAtual()
    {
    	return this.tempoAtual;
    }
    
    // Sets
    
    public void setUnidadeTempo(int unidade)
    {
    	this.unidadeDeTempo = unidade;
    }
    
    public void setTempoFinal(int t)
    {
    	if(this.unidadeDeTempo == 0) // segundos
        	this.tempoFinal = t;
    	
    	if(this.unidadeDeTempo == 1) // minutos
    		this.tempoFinal = (t * 60);
    	
    	if(this.unidadeDeTempo == 2) // horas
    		this.tempoFinal = (t * 3600);
    	
    	//this.tempoFinal--;
    	
    	System.out.println("TEMPO FINAL DEFINIDO: " + this.tempoFinal);
    }
    
    public void setDelay(int modo)
    {
    	if(modo == 0) // Lento
    		this.delay = 1000;
    	if(modo == 1) // Normal
    		this.delay = 500;
    	if(modo == 2) // Rápido
    		this.delay = 100;
    	if(modo == 3) // Instantâneo
    		this.delay = 1;
    	
    	System.out.println("DELAY DEFINIDO: " + this.delay + "ms");
    }
    
	public void setModoTec(int modo)
	{
		this.tipoTec = modo;
		
		System.out.println("MODO TEC DEFINIDO: " + modo);
	}

	public void setModoTs(int modo)
	{
		this.tipoTs = modo;
		
		System.out.println("MODO TS DEFINIDO: " + modo);
	}

	public void setModoTef(int modo)
	{
		this.tipoTef = modo;
		
		System.out.println("MODO TEF DEFINIDO: " + modo);
	}

	public void setModoTf(int modo)
	{
		this.tipoTf = modo;
		
		System.out.println("MODO TF DEFINIDO: " + modo);
	}

	public void setParametroTec(String texto)
	{
		double par0 = 0;
		double par1 = 0;
		double par2 = 0;
		int cont = 0;
		String temp = "";

		for(int i = 0; i < texto.length(); i++)
		{
			if(!(texto.charAt(i) == ',')) // Se não é vírgula
				temp += texto.charAt(i);
			else // Caso seja vírgula reseta
			{
				if(cont == 0)
					par0 = Double.parseDouble(temp);
				if(cont == 1)
					par1 = Double.parseDouble(temp);
				if(cont == 2)
					par2 = Double.parseDouble(temp);
				temp = "";
				cont++;
			}
		}
		if(cont == 0)
			par0 = Double.parseDouble(temp);
		if(cont == 1)
			par1 = Double.parseDouble(temp);
		if(cont == 2)
			par2 = Double.parseDouble(temp);
		temp = "";
		
		this.arg0Tec = par0;
		this.arg1Tec = par1;
		this.arg2Tec = par2;
		
		System.out.println("PARÂMETROS TEC: " + par0 + ", " + par1 + ", " + par2);
	}

	public void setParametroTs(String texto)
	{
		double par0 = 0;
		double par1 = 0;
		double par2 = 0;
		int cont = 0;
		String temp = "";

		for(int i = 0; i < texto.length(); i++)
		{
			if(!(texto.charAt(i) == ',')) // Se não é vírgula
				temp += texto.charAt(i);
			else // Caso seja vírgula reseta
			{
				if(cont == 0)
					par0 = Double.parseDouble(temp);
				if(cont == 1)
					par1 = Double.parseDouble(temp);
				if(cont == 2)
					par2 = Double.parseDouble(temp);
				temp = "";
				cont++;
			}
		}
		
		if(cont == 0)
			par0 = Double.parseDouble(temp);
		if(cont == 1)
			par1 = Double.parseDouble(temp);
		if(cont == 2)
			par2 = Double.parseDouble(temp);
		temp = "";
		
		this.arg0Ts = par0;
		this.arg1Ts = par1;
		this.arg2Ts = par2;
		
		System.out.println("PARÂMETROS TS: " + par0 + ", " + par1 + ", " + par2);
	}

	public void setParametroTef(String texto)
	{
		double par0 = 0;
		double par1 = 0;
		double par2 = 0;
		int cont = 0;
		String temp = "";

		for(int i = 0; i < texto.length(); i++)
		{
			if(!(texto.charAt(i) == ',')) // Se não é vírgula
				temp += texto.charAt(i);
			else // Caso seja vírgula reseta
			{
				if(cont == 0)
					par0 = Double.parseDouble(temp);
				if(cont == 1)
					par1 = Double.parseDouble(temp);
				if(cont == 2)
					par2 = Double.parseDouble(temp);
				temp = "";
				cont++;
			}
		}
		if(cont == 0)
			par0 = Double.parseDouble(temp);
		if(cont == 1)
			par1 = Double.parseDouble(temp);
		if(cont == 2)
			par2 = Double.parseDouble(temp);
		temp = "";

		this.arg0Tef = par0;
		this.arg1Tef = par1;
		this.arg2Tef = par2;
		
		System.out.println("PARÂMETROS TEF: " + par0 + ", " + par1 + ", " + par2);
	}

	public void setParametroTf(String texto) 
	{
		double par0 = 0;
		double par1 = 0;
		double par2 = 0;
		int cont = 0;
		String temp = "";
		
		for(int i = 0; i < texto.length(); i++)
		{
			if(!(texto.charAt(i) == ',')) // Se não é vírgula
				temp += texto.charAt(i);
			else // Caso seja vírgula reseta
			{
				if(cont == 0)
					par0 = Double.parseDouble(temp);
				if(cont == 1)
					par1 = Double.parseDouble(temp);
				if(cont == 2)
					par2 = Double.parseDouble(temp);
				temp = "";
				cont++;
			}
			
		}
		if(cont == 0)
			par0 = Double.parseDouble(temp);
		if(cont == 1)
			par1 = Double.parseDouble(temp);
		if(cont == 2)
			par2 = Double.parseDouble(temp);
		temp = "";
		
		
		this.arg0Tf = par0;
		this.arg1Tf = par1;
		this.arg2Tf = par2;
		
		System.out.println("PARÂMETROS TF: " + par0 + ", " + par1 + ", " + par2);
	}
    
    // Outros métodos

	public double gerarTempo(int mode, double op1, double op2, double op3) // testado, ok 100%
    {
        if (mode == 0) // Constante
            return this.tempoAtual + op1;
        if (mode == 1) // Exponencial
            return this.tempoAtual + this.calculador.probExponencial(op1);
        if (mode == 2) // Normal
        	return this.tempoAtual + this.calculador.probNormal(op1, op2);
        if (mode == 3) // Triangular
            return this.tempoAtual + this.calculador.probTriangular(op1, op2, op3);
        			   // Uniforme
        	return this.tempoAtual + this.calculador.probUniforme(op1, op2);
    }
    
	public void estadoDeChegada()
	{
		if(this.tempoAtual == this.proxTempoDeChegada) // Se é tempo de chegada
        {      	
        	// Cria entidade nova
            this.entidadeNova = this.gerador.geraEntidade(this.tipoTs, this.arg0Ts, this.arg1Ts, this.arg2Ts);
            // Define o próximo evento de chegada
            this.proxTempoDeChegada = this.gerarTempo(this.tipoTec, this.arg0Tec, this.arg1Tec, this.arg2Tec);
            
            // Adiciona entidade nova ao servidor respectivo
            if(this.entidadeNova.getTipo() == 1) 
            {
                this.serv1.adicionaEntidade(entidadeNova);
                // Se não está ocupado nem quebrado executará
                if(this.serv1.estaOcupado() == false && this.serv1.filaEstaVazia() == false && this.serv1.estaQuebrado() == false) 
                	this.proxTempoDeTerminoDeServico1 = this.serv1.executarServico() + this.tempoAtual;
                if(this.serv1.estaQuebrado() == false && this.proxTempoDeFalha1 == Double.MAX_VALUE) // Falha inicial
                	this.proxTempoDeFalha1 = this.gerarTempo(this.tipoTef, arg0Tef, arg1Tef, arg2Tef);
            }
            else
            {
                this.serv2.adicionaEntidade(entidadeNova);
             // Se não está ocupado nem quebrado executará
                if(this.serv2.estaOcupado() == false && this.serv2.filaEstaVazia() == false && this.serv2.estaQuebrado() == false) 
                	this.proxTempoDeTerminoDeServico2 = this.serv2.executarServico() + this.tempoAtual; 
                if(this.serv2.estaQuebrado() == false && this.proxTempoDeFalha2 == Double.MAX_VALUE) // Falha inicial
                	this.proxTempoDeFalha2 = this.gerarTempo(this.tipoTef, arg0Tef, arg1Tef, arg2Tef);
            }
        }
	}
	
	public void estadoTerminoServico1()
	{
        if(this.tempoAtual == this.proxTempoDeTerminoDeServico1) // Se já é tempo de serviço 1
        {
        	this.serv1.acabarServico();
        	if(this.serv1.filaEstaVazia() == false)
        		this.proxTempoDeTerminoDeServico1 = this.serv1.executarServico() + this.tempoAtual;	
        	else
        		this.proxTempoDeTerminoDeServico1 = Double.MAX_VALUE;
        }
	}
	
	public void estadoTerminoServico2()
	{
        if(this.tempoAtual == this.proxTempoDeTerminoDeServico2) // Se já é tempo de serviço 2
        {
        	this.serv2.acabarServico();
        	if(this.serv2.filaEstaVazia() == false)
        		this.proxTempoDeTerminoDeServico2 = this.serv2.executarServico() + this.tempoAtual;
        	else
        		this.proxTempoDeTerminoDeServico2 = Double.MAX_VALUE;
        }
	}
	
	public void estadoFalha1()
	{
		if(this.tempoAtual == this.proxTempoDeFalha1) // Se já é hora de falhar o servidor 1
		{
			this.serv1.quebrar();
			this.proxTempoDeTerminoDeServico1 = Double.MAX_VALUE; // Não faz serviço enquanto quebrado
			this.proxTempoDeFalha1 = Double.MAX_VALUE; // Não pode falhar enquanto está falhado
			this.proxTempoDeReparo1 = this.gerarTempo(tipoTf, arg0Tf, arg1Tf, arg2Tf); // Gera próximo evento de reinício para serv 1
		}
	}
	
	public void estadoFalha2()
	{
		if(this.tempoAtual == this.proxTempoDeFalha2) // Se já é hora de falhar o servidor 2
		{
			this.serv2.quebrar();
			this.proxTempoDeTerminoDeServico2 = Double.MAX_VALUE; // Não faz serviço enquanto quebrado
			this.proxTempoDeFalha2 = Double.MAX_VALUE; // Não pode falhar enquanto está falhado
			this.proxTempoDeReparo2 = this.gerarTempo(tipoTf, arg0Tf, arg1Tf, arg2Tf); // Gera próximo evento de reinício para serv 2
		}
	}
	
	public void estadoTerminoFalha1()
	{
		if(this.tempoAtual == this.proxTempoDeReparo1) // Se já é tempo de reparar o servidor 1
		{
			this.serv1.reparar();
			if(serv1.getFilaEntidades().size() > 0) // Caso tenha tarefas na fila, encaminha
				this.proxTempoDeTerminoDeServico1 = this.serv1.executarServico() + this.tempoAtual; // Gera evento do próximo final de serviço
			else
				this.proxTempoDeTerminoDeServico1 = Double.MAX_VALUE;
			this.proxTempoDeReparo1 = Double.MAX_VALUE; // Não pode reiniciar se funcionando
			this.proxTempoDeFalha1 = this.gerarTempo(tipoTef, arg0Tef, arg1Tef, arg2Tef);
		}
	}
	
	public void estadoTerminoFalha2()
	{
		if(this.tempoAtual == this.proxTempoDeReparo2) // Se já é tempo de reparar o servidor 2
		{
			this.serv2.reparar();
			if(serv2.getFilaEntidades().size() > 0) // Caso tenha tarefas na fila, encaminha
				this.proxTempoDeTerminoDeServico2 = this.serv2.executarServico() + this.tempoAtual; // Gera evento do próximo final de serviço
			else
				this.proxTempoDeTerminoDeServico2 = Double.MAX_VALUE;
			this.proxTempoDeReparo2 = Double.MAX_VALUE; // Não pode reiniciar se funcionando
			this.proxTempoDeFalha2 = this.gerarTempo(tipoTef, arg0Tef, arg1Tef, arg2Tef);
		}
	}
	
    public void simulacao() // TODO Simulação
    {
    	/*
    	System.out.println("*************BEFORE***************");
    	System.out.println("Tempo Atual: " + this.tempoAtual);
    	System.out.println("Prox tempo chegada: " + this.proxTempoDeChegada);
    	System.out.println("Prox tempo término de serviço 1: " + this.proxTempoDeTerminoDeServico1);
    	System.out.println("Prox tempo término de serviço 2: " + this.proxTempoDeTerminoDeServico2);
    	System.out.println("Prox tempo falha serv1: " + this.proxTempoDeFalha1);
    	System.out.println("Prox tempo falha serv2: " + this.proxTempoDeFalha2);
    	System.out.println("Prox tempo reparo serv1: " + this.proxTempoDeReparo1);
    	System.out.println("Prox tempo reparo serv2: " + this.proxTempoDeReparo2);
    	System.out.println("ESTADO QUE IRÁ ACONTECER AGORA: " + this.proxEstado);
    	*/
    	
        if(this.tempoAtual < this.tempoFinal) // Se ainda tem tempo de simulação, entra
        {
        	
        	switch(this.proxEstado) // Determinar o estado
        	{
			case CHEGADA : this.estadoDeChegada();
				break;
			case TERMINO_SERVICO_1 : this.estadoTerminoServico1();
				break;
			case TERMINO_SERVICO_2 : this.estadoTerminoServico2();
				break;
			case FALHA_1 : this.estadoFalha1();
				break;
			case FALHA_2 : this.estadoFalha2();
				break;
			case TERMINO_FALHA_1 : this.estadoTerminoFalha1();
				break;
			case TERMINO_FALHA_2 : this.estadoTerminoFalha2();
				break;
			default: System.err.println("ERRO Switch Case!");
				break;
			}
        	   
            // Após a execução do estado atual, determina o seguinte
            
            // TEMPO É DISCRETO, AVANÇA PARA PRÓXIMO EVENTO, pega o evento seguinte de menor tempo
        	
        	// Caso base
        	this.tempoAtual = this.tempoFinal;
        	
        	if(this.tempoAtual > this.proxTempoDeChegada)
        	{
        		this.tempoAtual = this.proxTempoDeChegada; 
        		this.proxEstado = Estado.CHEGADA;
        	}
            
            if(this.tempoAtual > this.proxTempoDeTerminoDeServico1)
            {
            	this.tempoAtual = this.proxTempoDeTerminoDeServico1;
            	this.proxEstado = Estado.TERMINO_SERVICO_1;
            }
       
            if(this.tempoAtual > this.proxTempoDeTerminoDeServico2)
            {
            	this.tempoAtual = this.proxTempoDeTerminoDeServico2;
            	this.proxEstado = Estado.TERMINO_SERVICO_2;
            }
            
            if(this.tempoAtual > this.proxTempoDeFalha1)
            {
            	this.tempoAtual = this.proxTempoDeFalha1;
            	this.proxEstado = Estado.FALHA_1;
            }
            
            if(this.tempoAtual > this.proxTempoDeFalha2)
            {
            	this.tempoAtual = this.proxTempoDeFalha2;
            	this.proxEstado = Estado.FALHA_2;
            }
            
            if(this.tempoAtual > this.proxTempoDeReparo1)
            {
            	this.tempoAtual = this.proxTempoDeReparo1;
            	this.proxEstado = Estado.TERMINO_FALHA_1;
            }
            if(this.tempoAtual > this.proxTempoDeReparo2)
            {
            	this.tempoAtual = this.proxTempoDeReparo2;
            	this.proxEstado = Estado.TERMINO_FALHA_2;
            }
            
            System.out.println("************AFTER***************");
        	System.out.println("Tempo Atual: " + this.tempoAtual);
        	System.out.println("Prox tempo chegada: " + this.proxTempoDeChegada);
        	System.out.println("Prox tempo término de serviço 1: " + this.proxTempoDeTerminoDeServico1);
        	System.out.println("Prox tempo término de serviço 2: " + this.proxTempoDeTerminoDeServico2);
        	System.out.println("Prox tempo falha serv1: " + this.proxTempoDeFalha1);
        	System.out.println("Prox tempo falha serv2: " + this.proxTempoDeFalha2);
        	System.out.println("Prox tempo reparo serv1: " + this.proxTempoDeReparo1);
        	System.out.println("Prox tempo reparo serv2: " + this.proxTempoDeReparo2);
        	System.out.println("ESTADO QUE IRÁ ACONTECER A SEGUIR: " + this.proxEstado);
            
            this.atualizaEstatisticas(); 
        }
        else // senão tem mais tempo, simulação acaba  
        	this.controle.encerrarSimulacao();
    }
    
    public void atualizaEstatisticas() // TODO Estatísticas
    {
    	ArrayList<Double> estatisticas = new ArrayList<Double>();
    	
    	Double d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14,
    		    d15, d16, d17, d18, d19, d20, d21, d22, d23, d24, d25, d26;
    	
    	estatisticas.add(this.tempoAtual); // Manda tempo atual no primeiro slot
    	d1 = this.serv1.getEntidadesQueEntraram();
    	d2 = this.serv2.getEntidadesQueEntraram();
    	d3 = this.serv1.getEntidadesQueSairam();
    	d4 = this.serv2.getEntidadesQueSairam();
    	d5 = this.serv1.getNumeroDeQuebras();
    	d6 = this.serv2.getNumeroDeQuebras();
    	d7 = this.serv1.getNumeroDeReparos();
    	d8 = this.serv2.getNumeroDeReparos();
    	
    	estatisticas.add(d1);
    	estatisticas.add(d2);
    	estatisticas.add(d3);
    	estatisticas.add(d4);
    	estatisticas.add(d5);
    	estatisticas.add(d6);
    	estatisticas.add(d7);
    	estatisticas.add(d8);
    	
    	/*

    	estatisticas.add(d9);
    	estatisticas.add(d10);
    	estatisticas.add(d11);
    	estatisticas.add(d12);
    	estatisticas.add(d13);
    	estatisticas.add(d14);
    	estatisticas.add(d15);
    	estatisticas.add(d16);
    	estatisticas.add(d17);
    	estatisticas.add(d18);
    	estatisticas.add(d19);
    	estatisticas.add(d20);
    	estatisticas.add(d21);
    	estatisticas.add(d22);
    	estatisticas.add(d23);
    	estatisticas.add(d24);
    	estatisticas.add(d25);
    	estatisticas.add(d26);	
    	*/
    	
    	this.controle.atualizaEstatisticas(estatisticas);
    }
    
}
