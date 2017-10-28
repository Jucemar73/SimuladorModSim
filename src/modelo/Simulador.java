package modelo;

import java.util.ArrayList;

import controle.Controle;

public class Simulador {
	
    private Servidor serv1;
    private Servidor serv2;
    
    private Gerador gerador;
    private Calculador calculador;
    private Entidade entidadeNova;
    
    private Controle controle;
    
    private int unidadeDeTempo; // 0 para seg, 1 para min, 2 para horas
    
    private long delay;
    
    private int tempoTotal;
    private int tempoFinal;
    
    private int tempoOcupado1;
    private int tempoOcupado2;
    
    private int tempoAtivo1;
    private int tempoAtivo2;
    
    private int tempoFalha1;
    private int tempoFalha2;
    
    private int tempoEmFila1;
    private int tempoEmFila2;
    
    private int totalEmFila1;
    private int totalEmFila2;
    
    private int tempoSistema1;
    private int tempoSistema2;
    
    private int numeroEntidades1;
    private int numeroEntidades2;
    
    private int maxSize;
    private int bloqueados1;
    private int bloqueados2;
    
    private int numeroTrocas1;
    private int numeroTrocas2;
    
    private int modeGerador;
    private int modeArrival;
    private int modeEmFalha;
    private int modeParaFalha;
    
    private int nextArrivalTime;
    private int nextFailureTime1;
    private int nextFailureTime2;
    private int nextWakeTime1;
    private int nextWakeTime2;
    
    private double op1Arr;
    private double op2Arr;
    private double op3Arr;
    
    private double op1EmFalha;
    private double op2EmFalha;
    private double op3EmFalha;
    
    private double op1ParaFalha;
    private double op2ParaFalha;
    private double op3ParaFalha;
    
    private double op1Ent;
    private double op2Ent;
    private double op3Ent;
    
    // Construtor
    
    public Simulador(Controle controle) { // TODO verificar valores iniciais de vari�veis
        serv1 = new Servidor();
        serv2 = new Servidor();
        gerador = new Gerador();
        calculador = new Calculador();
        entidadeNova = null;
        this.controle = controle;
        
        this.unidadeDeTempo = 0;
        this.delay = 0;
        
        tempoTotal = 0;
        tempoFinal = 0;
        
        tempoOcupado1 = 0;
        tempoOcupado2 = 0;
        
        tempoAtivo1 = 0;
        tempoAtivo2 = 0;
        
        tempoFalha1 = 0;
        tempoFalha2 = 0;
        
        tempoEmFila1 = 0;
        tempoEmFila2 = 0;
        
        totalEmFila1 = 0;
        totalEmFila2 = 0;
        
        tempoSistema1 = 0;
        tempoSistema2 = 0;
        
        numeroEntidades1 = 0;
        numeroEntidades2 = 0;
        
        numeroTrocas1 = 0;
        numeroTrocas2 = 0;
        
        modeArrival = 0; // TEC
        modeGerador = 0; // TS
        modeEmFalha = 0; // TEF
        modeParaFalha = 0; // TF
        
        nextArrivalTime = 0;
        nextFailureTime1 = 0;
        nextFailureTime2 = 0;
        nextWakeTime1 = 0;
        nextWakeTime2 = 0;
        
        op1Arr = 0;
        op2Arr = 0;
        op3Arr = 0;
        
        op1Ent = 0;
        op2Ent = 0;
        op3Ent = 0;
        
        op1ParaFalha = 0;
        op2ParaFalha = 0;
        op3ParaFalha = 0;
        
        op1EmFalha = 0;
        op2EmFalha = 0;
        op3EmFalha = 0;
        
    }

    // Gets
    
    public Servidor getServ1() {
        return this.serv1;
    }

    public Servidor getServ2() {
        return this.serv2;
    }
    
    public long getDelay() {
    	return this.delay;
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
    	
    	System.out.println("TEMPO FINAL DEFINIDO: " + this.tempoFinal);
    }
    
    public void setDelay(int modo)
    {
    	if(modo == 0) // Lento
    		this.delay = 1000;
    	if(modo == 1) // Normal
    		this.delay = 500;
    	if(modo == 2) // R�pido
    		this.delay = 100;
    	if(modo == 3) // Instant�neo
    		this.delay = 1;
    	
    	System.out.println("DELAY DEFINIDO: " + this.delay + "ms");
    }
    
	public void setModoTec(int modo)
	{
		this.modeArrival = modo;
		
		System.out.println("MODO TEC DEFINIDO: " + modo);
	}

	public void setModoTs(int modo)
	{
		this.modeGerador = modo;
		
		System.out.println("MODO TS DEFINIDO: " + modo);
	}

	public void setModoTef(int modo)
	{
		this.modeEmFalha = modo;
		
		System.out.println("MODO TEF DEFINIDO: " + modo);
	}

	public void setModoTf(int modo)
	{
		this.modeParaFalha = modo;
		
		System.out.println("MODO TF DEFINIDO: " + modo);
	}

	public void setParametroTec(String texto)
	{
		double par0 = 0;
		double par1 = 0;
		double par2 = 0;
		int cont = 0;
		String temp = "";
		
		if(this.modeArrival == 0 || this.modeArrival == 1) // Const, exp
			par0 = Double.parseDouble(texto);
		if(this.modeArrival == 2 || this.modeArrival == 3 || this.modeArrival == 4) // Norm, Tri, Uni
		{
			for(int i = 0; i < texto.length(); i++)
			{
				if(!(texto.charAt(i) == ',')) // Se n�o � v�rgula
					temp += texto.charAt(i);
				else // Caso seja v�rgula reseta
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
		}
		
		this.op1Arr = par0;
		this.op2Arr = par1;
		this.op3Arr = par2;
		
		System.out.println("PAR�METROS TEC: " + par0 + ", " + par1 + ", " + par2);
	}

	public void setParametroTs(String texto)
	{
		double par0 = 0;
		double par1 = 0;
		double par2 = 0;
		int cont = 0;
		String temp = "";

		if(this.modeGerador == 0 || this.modeGerador == 1) // Const, exp
			par0 = Double.parseDouble(texto);
		if(this.modeGerador == 2 || this.modeGerador == 3 || this.modeGerador == 4) // Norm, Tri, Uni
		{
			for(int i = 0; i < texto.length(); i++)
			{
				if(!(texto.charAt(i) == ',')) // Se n�o � v�rgula
					temp += texto.charAt(i);
				else // Caso seja v�rgula reseta
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
		}
		
		this.op1Ent = par0;
		this.op2Ent = par1;
		this.op3Ent = par2;
		
		System.out.println("PAR�METROS TS: " + par0 + ", " + par1 + ", " + par2);
	}

	public void setParametroTef(String texto)
	{
		double par0 = 0;
		double par1 = 0;
		double par2 = 0;
		int cont = 0;
		String temp = "";

		if(this.modeEmFalha == 0 || this.modeEmFalha == 1) // Const, exp
			par0 = Double.parseDouble(texto);
		if(this.modeEmFalha == 2 || this.modeEmFalha == 3 || this.modeEmFalha == 4) // Norm, Tri, Uni
		{
			for(int i = 0; i < texto.length(); i++)
			{
				if(!(texto.charAt(i) == ',')) // Se n�o � v�rgula
					temp += texto.charAt(i);
				else // Caso seja v�rgula reseta
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
		}
		
		
		this.op1ParaFalha = par0;
		this.op2ParaFalha = par1;
		this.op3ParaFalha = par2;
		
		System.out.println("PAR�METROS TEF: " + par0 + ", " + par1 + ", " + par2);
	}

	public void setParametroTf(String texto) 
	{
		double par0 = 0;
		double par1 = 0;
		double par2 = 0;
		int cont = 0;
		String temp = "";

		if(this.modeParaFalha == 0 || this.modeParaFalha == 1) // Const, exp
			par0 = Double.parseDouble(texto);
		if(this.modeParaFalha == 2 || this.modeParaFalha == 3 || this.modeParaFalha == 4) // Norm, Tri, Uni
		{
			for(int i = 0; i < texto.length(); i++)
			{
				if(!(texto.charAt(i) == ',')) // Se n�o � v�rgula
					temp += texto.charAt(i);
				else // Caso seja v�rgula reseta
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
		}
		
		this.op1EmFalha = par0;
		this.op2EmFalha = par1;
		this.op3EmFalha = par2;
		
		System.out.println("PAR�METROS TF: " + par0 + ", " + par1 + ", " + par2);
	}
    
    // Outros m�todos

    public void simulacao() // TODO CONSERTAR
    {
        if (this.tempoTotal <= this.tempoFinal) 
        {
            this.tempoTotal++;
        
            /*
            
            if (this.tempoTotal == this.nextArrivalTime || this.tempoTotal == 0)
            {
                this.entidadeNova = this.gerador.geraEntidade(this.modeGerador, this.op1Ent, this.op2Ent, this.op3Ent);
                if (this.entidadeNova.retornaTipo() == 1) 
                    ++this.numeroEntidades1;
                else 
                    ++this.numeroEntidades2;

                this.assingToServer(new Entidade(this.entidadeNova));
                this.nextArrivalTime += (int)this.geraTempo(this.modeArrival, this.op1Arr, this.op2Arr, this.op3Arr);
            }
            
            if (this.tempoTotal == this.nextFailureTime1)
            {
                this.nextWakeTime1 = this.tempoTotal + (int)this.geraTempo(this.modeEmFalha, this.op1EmFalha, this.op2EmFalha, this.op3EmFalha);
                this.serv1.setAtivo(false);
            }
            
            if (this.tempoTotal == this.nextFailureTime2)
            {
                this.nextWakeTime2 = this.tempoTotal + (int)this.geraTempo(this.modeEmFalha, this.op1EmFalha, this.op2EmFalha, this.op3EmFalha);
                this.serv2.setAtivo(false);
            }
            
            if (this.tempoTotal == this.nextFailureTime1) 
            {
                this.nextFailureTime1 = this.tempoTotal + (int)this.geraTempo(this.modeParaFalha, this.op1ParaFalha, this.op2ParaFalha, this.op3ParaFalha);
                this.serv1.setAtivo(true);
            }
            
            if (this.tempoTotal == this.nextFailureTime2)
            {
                this.nextFailureTime2 = this.tempoTotal + (int)this.geraTempo(this.modeParaFalha, this.op1ParaFalha, this.op2ParaFalha, this.op3ParaFalha);
                this.serv2.setAtivo(true);
            }
            
            if (this.serv1.retornaAtivo()) 
                this.serv1.process();
            if (this.serv2.retornaAtivo()) 
                this.serv2.process();
            
            this.totalEmFila1 += this.serv1.getSizeFila();
            this.totalEmFila2 += this.serv2.getSizeFila();
            
            if(this.serv1.retornaAtivo()) 
            {
                ++this.tempoAtivo1;
                if (this.serv1.retornaOcupado()) 
                    ++this.tempoOcupado1;     
            } 
            else
                ++this.tempoFalha1;
            
            if(this.serv2.retornaAtivo()) 
            {
                ++this.tempoAtivo2;
                if (this.serv2.retornaOcupado()) 
                    ++this.tempoOcupado2;
            } 
            else
                ++this.tempoFalha1;
            
            */
            
            this.atualizaEstatisticas();
            
        }
        else // Simula��o acabou
        {
        	this.controle.encerrarSimulacao();
        }
    }

	public void assingToServer(Entidade entidade) { // Vers�o que veio do telegram
		if (entidade.retornaTipo() == 1) {
			if (this.serv1.retornaAtivo() == false && this.serv2.retornaAtivo() == true) {
				if (maxSize > serv2.getSizeFila() && maxSize >= 0) {
					numeroTrocas1++;
					this.serv2.newArrival(new Entidade(entidade));
				} else {
					bloqueados1++;
				}
			} else {
				if (maxSize > serv1.getSizeFila() && maxSize >= 0) {
					this.serv1.newArrival(new Entidade(entidade));
				} else {
					bloqueados1++;
				}
			}
		} else {
			if (this.serv2.retornaAtivo() == false && this.serv1.retornaAtivo() == true) {
				if (maxSize > serv1.getSizeFila() && maxSize >= 0) {
					numeroTrocas2++;
					this.serv1.newArrival(entidade);
				} else {
					bloqueados2++;
				}

			} else {
				if (maxSize > serv2.getSizeFila() && maxSize >= 0) {
					this.serv2.newArrival(entidade);
				} else {
					bloqueados2++;
				}
			}
		}
	}

    public double geraTempo(int mode, double op1, double op2, double op3) 
    {
        if (mode == 0) 
            return this.tempoFinal + 10;
        if (mode == 1) 
            return (double)this.tempoFinal + this.calculador.probExponencial(op1);
        if (mode == 2) 
        	return (double)this.tempoFinal + this.calculador.probNormal(op1, op2);
        if (mode == 3) 
            return (double)this.tempoFinal + this.calculador.probTriangular(op1, op2, op3);
        
        return (double)this.tempoFinal + this.calculador.probUniforme(op1, op2);
    }

    public void atualizaEstatisticas() 
    {
    	ArrayList<Double> estatisticas = new ArrayList<Double>();
    	
    	/*
        Double d0 = this.calculador.numeroMedioFilas(this.totalEmFila1, this.tempoTotal);
        Double d1 = this.calculador.numeroMedioFilas(this.totalEmFila2, this.tempoTotal);
        Double d2 = this.calculador.numeroMedioFilas(this.totalEmFila1 + this.totalEmFila2, this.tempoTotal);
        Double d3 = this.calculador.taxaMediaOcupacao(this.tempoOcupado1, this.tempoAtivo1);
        Double d4 = this.calculador.taxaMediaOcupacao(this.tempoOcupado2, this.tempoAtivo2);
        Double d5 = this.calculador.tempoMedioEmFila(this.tempoEmFila1, this.tempoTotal);
        Double d6 = this.calculador.tempoMedioEmFila(this.tempoEmFila2, this.tempoTotal);
        Double d7 = this.calculador.tempoEmFalha(this.tempoFalha1, this.tempoTotal);
        Double d8 = this.calculador.tempoEmFalha(this.tempoFalha2, this.tempoTotal);
        Double d9 = this.mediaPonderada(this.serv1.returnaEstadosFila());
        Double d10 = this.mediaPonderada(this.serv2.returnaEstadosFila());

    	estatisticas.add(d0); // nmef1
    	estatisticas.add(d1); // nmef2
    	estatisticas.add(d2); // nmef total
    	estatisticas.add(d3); // tmos1
    	estatisticas.add(d4); // tmos2
    	estatisticas.add(d5); // tmef1
    	estatisticas.add(d6); // tmef2
    	estatisticas.add(d7); // tf1
    	estatisticas.add(d8); // tf2
    	estatisticas.add(d9); // mp1
    	estatisticas.add(d10); // mp2
    	
    	*/
    	
    	// TODO Adicionar as outras ao array
    	
    	// TODO tmes: tempo medio entidade no sistema
    	// TODO ce: contador entidades
    	// TODO nf: num falhas
    	// TODO nts: num trocas servidor
    	// TODO neb: num entidades bloqueadas
    	
    	this.controle.atualizaEstatisticas(estatisticas);
    }

    public double mediaPonderada(ArrayList<Integer> lista) {
        int temp = 0;
        int i = 0;
        while (i <= this.tempoTotal && lista.size() > 0) 
        {
            temp += lista.remove(0) / this.tempoTotal;
            ++i;
        }
        return temp;
    }
    
    public double tempoMedioNoSistema(int tempoEmFila,int tempoOcupado, int tempoTotal) {
    	  return (tempoEmFila+tempoOcupado)/tempoTotal;
    }
    
}
