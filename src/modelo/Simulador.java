package modelo;

import java.util.ArrayList;

import controle.Controle;

public class Simulador 
{
	// Objetos (6)
	
    private Servidor serv1;
    private Servidor serv2;
    private Gerador gerador;
    private Calculador calculador;
    private Entidade entidadeNova;
    private Controle controle;
    
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
    private double proxTempoDeReinicio1;
    private double proxTempoDeReinicio2;
    private double proxTempoDeTerminoDeServico1;
    private double proxTempoDeTerminoDeServico2;
    
    // Estatísticas (8)
    
    private int numEntidades1; // Número total de entidades tipo 1.
    private int numEntidades2; // Número total de entidades tipo 2.
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
    	// Objetos (6)
    	
        serv1 = new Servidor();
        serv2 = new Servidor();
        calculador = new Calculador();
        gerador = new Gerador(calculador);
        entidadeNova = null;
        this.controle = controle;
        
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
        this.proxTempoDeReinicio1 = Double.MAX_VALUE; 
        this.proxTempoDeReinicio2 = Double.MAX_VALUE; 
        this.proxTempoDeTerminoDeServico1 = Double.MAX_VALUE; 
        this.proxTempoDeTerminoDeServico2 = Double.MAX_VALUE; 
        
        // Estatísticas (8)
        
        this.numEntidades1 = 0;
        this.numEntidades2 = 0;
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
    	
    	this.tempoFinal--;
    	
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

    public void atualizaEstatisticas() // TODO Fix
    {
    	ArrayList<Double> estatisticas = new ArrayList<Double>();
    	
    	Double d0, d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14,
    		   d15, d16, d17, d18, d19, d20, d21, d22, d23, d24, d25, d26;
    	
    	/*
    	
        d0 = this.calculador.mediaPonderada(this.serv1.getFilaDeEstados(), this.tempoAtual); //Número Médio de Entidades nas Filas 1.
        d1 = this.calculador.mediaPonderada(this.serv2.getFilaDeEstados(), this.tempoAtual); //Número Médio de Entidades nas Filas 2.
        d2 = this.calculador.mediaPonderadaTotal(this.serv1.getFilaDeEstados(),this.serv2.getFilaDeEstados(), this.tempoAtual); //Número Médio de Entidades nas Filas Total.
        d3 = this.calculador.taxaMediaOcupacao(this.tempoOcupado1, this.tempoAtivo1); //Taxa Média de Ocupação dos Servidores 1.
        d4 = this.calculador.taxaMediaOcupacao(this.tempoOcupado2, this.tempoAtivo2); //Taxa Média de Ocupação dos Servidores 2.
        d5 = this.calculador.tempoMedioEmFila(this.tempoEmFila1, this.tempoAtual); //Tempo Médio de uma Entidade na Fila 1.
        d6 = this.calculador.tempoMedioEmFila(this.tempoEmFila2, this.tempoAtual); //Tempo Médio de uma Entidade na Fila 2.
        d7 = this.calculador.tempoEmFalha(this.tempoFalha1, this.tempoAtual); //Tempo Medio em falha 1
        d8 = this.calculador.tempoEmFalha(this.tempoFalha2, this.tempoAtual); //Tempo Medio em falha 2
        d9 = this.calculador.tempoMedioNoSistema(tempoEmFila1,tempoOcupado1,tempoAtual); //Tempo Médio no Sistema 1.
        d10 = this.calculador.tempoMedioNoSistema(tempoEmFila2,tempoOcupado2,tempoAtual); //Tempo Médio no Sistema 2.
        d11 = this.calculador.tempoMedioNoSistema((tempoEmFila1+tempoEmFila2),(tempoOcupado1+tempoOcupado2),tempoAtual); //Tempo Médio no Sistema Total.
        d12 = (double) this.numEntidades1; //Contador de Entidades 1.
		d13 = (double) this.numEntidades2; //Contador de Entidades 2.
		d14 = (double) this.numEntidades1+this.numEntidades2; //Contador de Entidades Total.
		d15 = (double) this.serv1.getEntidadesSaida(); //Contador de Entidades Sairam 1.
		d16 = (double) this.serv2.getEntidadesSaida(); //Contador de Entidades Sairam 2.
		d17 = (double) this.serv1.getEntidadesSaida()+this.serv2.getEntidadesSaida(); //Contador de Entidades Sairam Total.
		d18 = (double) this.numeroEntidades1 - serv1.getEntidadesSaida(); //Contador de Entidades 1 no Sistema.
		d19 = (double) this.numeroEntidades2 - serv2.getEntidadesSaida(); //Contador de Entidades 2 no Sistema.
		d20 = (double) this.numeroEntidades1+ numeroEntidades2 - (serv1.getEntidadesSaida()+serv2.getEntidadesSaida()); //Contador de Entidades Total no Sistema.
		d21 = (double) this.numFalhas1; //Contador numero de Falhas 1.
		d22 = (double) this.numFalhas2; //Contador numero de Falhas 2.
		d23 = (double) this.numTrocas1; //Numero Trocas 1.
		d24 = (double) this.numTrocas2; // Número Trocas 2.
		d25 = (double) this.numBloqueios1; //Numero Bloqueados 1.
		d26 = (double) this.numBloqueios1; //Numero Bloqueados 2.
		
    	estatisticas.add(d0);
    	estatisticas.add(d1);
    	estatisticas.add(d2);
    	estatisticas.add(d3);
    	estatisticas.add(d4);
    	estatisticas.add(d5);
    	estatisticas.add(d6);
    	estatisticas.add(d7);
    	estatisticas.add(d8);
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
    	estatisticas.add(this.tempoAtual); // Manda tempo atual no último slot
    	
    	*/
    	
    	//this.controle.atualizaEstatisticas(estatisticas);
    } 
    
    public void simulacao() // TODO CONSERTAR
    {
        if(this.tempoAtual <= this.tempoFinal) // Se ainda tem tempo de simulação, entra
        {
            if(this.tempoAtual == this.proxTempoDeChegada) // Se é tempo de chegada
            {
            	// Cria entidade nova
                this.entidadeNova = this.gerador.geraEntidade(this.tipoTs, this.arg0Ts, this.arg1Ts, this.arg2Ts);
                
                if (this.entidadeNova.getTipo() == 1) 
                {
                    ++this.numEntidades1;
                    this.serv1.getFilaEntidades().add(entidadeNova);
                }
                else
                {
                    ++this.numEntidades2;
                    this.serv2.getFilaEntidades().add(entidadeNova);
                }
                
                // Define o próximo evento de chegada
                this.proxTempoDeChegada = this.gerarTempo(this.tipoTec, this.arg0Tec, this.arg1Tec, this.arg2Tec);
                
            }
            
            
            //System.out.println("SERV1 ATIVO?" + serv1.estaAtivo());
            //System.out.println("SERV2 ATIVO?" + serv2.estaAtivo());
            //System.out.println("SERV1 OCUPADO?" + serv1.estaOcupado());
            //System.out.println("SERV2 OCUPADO?" + serv2.estaOcupado());
            
            // TEMPO É DISCRETO, AVANÇA PARA PRÓXIMO EVENTO, pega o evento seguinte de menor tempo
            
            tempoAtual = proxTempoDeChegada; // Caso base
            if(tempoAtual > proxTempoDeFalha1)
            	tempoAtual = proxTempoDeFalha1;
            
            if(tempoAtual > proxTempoDeFalha2)
            	tempoAtual = proxTempoDeFalha2;
            
            if(tempoAtual > proxTempoDeReinicio1)
            	tempoAtual = proxTempoDeReinicio1;
            
            if(tempoAtual > proxTempoDeReinicio2)
            	tempoAtual = proxTempoDeReinicio2;
            
            this.atualizaEstatisticas(); 
        }
        else // senão tem mais tempo, simulação acaba  
        	this.controle.encerrarSimulacao();
    }
    
}
