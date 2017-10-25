package modelo;

import java.util.ArrayList;

public class Simulador 
{
	private Servidor serv1;
	private Servidor serv2;
	private ArrayList<Evento> listaDeEventos;
	private Gerador gerador;
	private Calculador calculador;
	private Entidade entidadeNova;
	//private ArrayList<Entidade> filaServidor1, filaServidor2;
	private int tempoTotal,tempoFinal;
	private int tempoFila1,tempoFila2;
	private int tempoOcpServidor1, tempoOcpServidor2;
	private int tempoMedioFila;
	private int tempoSistema1, tempoSitema2;
	private int numeroEntidades;
	private int modeGerador,modeArrival,modeEmFalha,modeParaFalha;
	private int nextArrivalTime, nextFailureTime1,nextFailureTime2,nextWakeTime1,nextWakeTime2;
	private double seed, det,detEmFalha,detParaFalha,detArrival;
	
	
	public Simulador()
	{
		this.serv1 = new Servidor();
		this.serv2 = new Servidor();
		this.listaDeEventos = new ArrayList<Evento>();
		//this.filaServidor1 = new ArrayList<Entidade>();
		//this.filaServidor2 = new ArrayList<Entidade>();
	}
	
	public Servidor getServ1()
	{
		return this.serv1;
	}
	
	public Servidor getServ2()
	{
		return this.serv2;
	}
	
	public ArrayList<Evento> getListaDeEventos()
	{
		return this.listaDeEventos;
	}
	
	public void iniciarSimulacao()
	{
		// set all em '0' chama simular()
	}
	
	public void simulacao(){
		while(tempoTotal <= tempoFinal){
			//Gerar prox Entidade.
			if(tempoTotal == nextArrivalTime){
				assingToServer(gerador.geraEntidade(seed, modeGerador, det));
				nextArrivalTime += (int) geraTempo(seed,modeArrival,detArrival);
			}
			//Manda o servidor Falhar
			if(tempoTotal == nextFailureTime1){
				nextWakeTime1 = tempoTotal + (int) geraTempo(seed,modeEmFalha,detEmFalha);
				serv1.setAtivo(false);
			}

			if(tempoTotal == nextFailureTime2){
				nextWakeTime2 = tempoTotal + (int) geraTempo(seed,modeEmFalha,detEmFalha);
				serv1.setAtivo(false);
			}
			//Recupara um servidor em falha.
			if(tempoTotal == nextFailureTime1){
				nextFailureTime1 = tempoTotal + (int) geraTempo(seed,modeParaFalha,detParaFalha);
				serv1.setAtivo(true);
			}

			if(tempoTotal == nextFailureTime2){
				nextFailureTime2 = tempoTotal + (int) geraTempo(seed,modeParaFalha,detParaFalha);
				serv1.setAtivo(true);
			}
			
			//Se servidor ativo processa
			if(this.serv1.retornaAtivo()==true){
				this.serv1.process();
			}
			if(this.serv2.retornaAtivo()==true){
				this.serv2.process();
			}
			
		}
		//geraRelatorio();
	}
	
	public void assingToServer(Entidade entidade){
		if(entidade.retornaTipo()==1){
			if(this.serv1.retornaAtivo() == false && 
			   this.serv2.retornaAtivo()== true){
				
				this.serv2.newArrival(new Entidade(entidade));
			}
			
			else{	
				this.serv1.newArrival(new Entidade(entidade));
			}
		}
		else{
			if(this.serv2.retornaAtivo() == false && 
			   this.serv1.retornaAtivo()== true){
	
				this.serv1.newArrival(entidade);
					}
			else{	
				this.serv2.newArrival(entidade);
				}
		}
	}

////////////////////////////////////////////
	public double numeroMedioNaFila1(){
		return tempoFila1/tempoTotal;
	}

	public double numeroMedioNaFila2(){
		return tempoFila2/tempoTotal;
	}

	public double numeroMedioNaFilaTotal(){
		return numeroMedioNaFila1()+numeroMedioNaFila2();
	}
///////////////////////////////////////////
	public double geraTempo(double seed,int mode, double det){
		//FAZER TODOS OS GERADORES, 'me mata, nao aguento mais'
		if(mode== 0){
			return tempoFinal+10;
		}
		else if(mode == 1){
			return (tempoFinal+this.calculador.probNormal(det,seed));
		}
		else if(mode == 2){
			return tempoFinal+this.calculador.probUniforme(det,seed);
		}
		else if(mode == 3){
			return tempoFinal+this.calculador.probTriangular((det-seed), seed,(det+seed));
		}
		else{
			return tempoFinal+this.calculador.probExponencial(det);
		}
		
	}
	


}
