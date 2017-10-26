package modelo;

import java.util.ArrayList;
import modelo.Calculador;
import modelo.Entidade;
import modelo.Gerador;
import modelo.Servidor;

public class Simulador {
    private Servidor serv1 = new Servidor();
    private Servidor serv2 = new Servidor();
    private Gerador gerador;
    private Calculador calculador;
    private Entidade entidadeNova;
    private int tempoTotal;
    private int tempoFinal;
    private int tempoFila1;
    private int tempoFila2;
    private int tempoOcupado1;
    private int tempoOcupado2;
    private int tempoAtivo1;
    private int tempoAtivo2;
    private int tempoFalha1;
    private int tempoFalha2;
    private int tempoEmFila1;
    private int tempoEmFila2;
    private int tempoMedioEmFilaTotal;
    private int totalEmFila1;
    private int totalEmFila2;
    private int tempoSistema1;
    private int tempoSitema2;
    private int numeroEntidades1;
    private int numeroEntidades2;
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

    public Servidor getServ1() {
        return this.serv1;
    }

    public Servidor getServ2() {
        return this.serv2;
    }

    public void iniciarSimulacao() {
    }

    public void simulacao() {
        while (this.tempoTotal <= this.tempoFinal) {
            if (this.tempoTotal == this.nextArrivalTime) {
                this.entidadeNova = this.gerador.geraEntidade(this.modeGerador, this.op1Ent, this.op2Ent, this.op3Ent);
                if (this.entidadeNova.retornaTipo() == 1) {
                    ++this.numeroEntidades1;
                } else {
                    ++this.numeroEntidades2;
                }
                this.assingToServer(new Entidade(this.entidadeNova));
                this.nextArrivalTime += (int)this.geraTempo(this.modeArrival, this.op1Arr, this.op2Arr, this.op3Arr);
            }
            if (this.tempoTotal == this.nextFailureTime1) {
                this.nextWakeTime1 = this.tempoTotal + (int)this.geraTempo(this.modeEmFalha, this.op1EmFalha, this.op2EmFalha, this.op3EmFalha);
                this.serv1.setAtivo(false);
            }
            if (this.tempoTotal == this.nextFailureTime2) {
                this.nextWakeTime2 = this.tempoTotal + (int)this.geraTempo(this.modeEmFalha, this.op1EmFalha, this.op2EmFalha, this.op3EmFalha);
                this.serv1.setAtivo(false);
            }
            if (this.tempoTotal == this.nextFailureTime1) {
                this.nextFailureTime1 = this.tempoTotal + (int)this.geraTempo(this.modeParaFalha, this.op1ParaFalha, this.op2ParaFalha, this.op3ParaFalha);
                this.serv1.setAtivo(true);
            }
            if (this.tempoTotal == this.nextFailureTime2) {
                this.nextFailureTime2 = this.tempoTotal + (int)this.geraTempo(this.modeParaFalha, this.op1ParaFalha, this.op2ParaFalha, this.op3ParaFalha);
                this.serv1.setAtivo(true);
            }
            if (this.serv1.retornaAtivo()) {
                this.serv1.process();
            }
            if (this.serv2.retornaAtivo()) {
                this.serv2.process();
            }
            this.totalEmFila1 += this.serv1.getSizeFila();
            this.totalEmFila2 += this.serv2.getSizeFila();
            if (this.serv1.retornaAtivo()) {
                ++this.tempoAtivo1;
                if (this.serv1.retornaOcupado()) {
                    ++this.tempoOcupado1;
                }
            } else {
                ++this.tempoFalha1;
            }
            if (this.serv2.retornaAtivo()) {
                ++this.tempoAtivo2;
                if (this.serv2.retornaOcupado()) {
                    ++this.tempoOcupado2;
                }
            } else {
                ++this.tempoFalha1;
            }
            this.atualizaEstatisticas();
        }
    }

    public void assingToServer(Entidade entidade) {
        if (entidade.retornaTipo() == 1) {
            if (!this.serv1.retornaAtivo() && this.serv2.retornaAtivo()) {
                ++this.numeroTrocas1;
                this.serv2.newArrival(new Entidade(entidade));
            } else {
                this.serv1.newArrival(new Entidade(entidade));
            }
        } else if (!this.serv2.retornaAtivo() && this.serv1.retornaAtivo()) {
            ++this.numeroTrocas2;
            this.serv1.newArrival(entidade);
        } else {
            this.serv2.newArrival(entidade);
        }
    }

    public double geraTempo(int mode, double op1, double op2, double op3) {
        if (mode == 0) {
            return this.tempoFinal + 10;
        }
        if (mode == 1) {
            return (double)this.tempoFinal + this.calculador.probNormal(op1, op2);
        }
        if (mode == 2) {
            return (double)this.tempoFinal + this.calculador.probUniforme(op1, op2);
        }
        if (mode == 3) {
            return (double)this.tempoFinal + this.calculador.probTriangular(op1, op2, op3);
        }
        return (double)this.tempoFinal + this.calculador.probExponencial(op1);
    }

    public void atualizaEstatisticas() {
        this.calculador.numeroMedioFilas(this.totalEmFila1, this.tempoTotal);
        this.calculador.numeroMedioFilas(this.totalEmFila2, this.tempoTotal);
        this.calculador.numeroMedioFilas(this.totalEmFila1 + this.totalEmFila2, this.tempoTotal);
        this.calculador.taxaMediaOcupacao(this.tempoOcupado1, this.tempoAtivo1);
        this.calculador.taxaMediaOcupacao(this.tempoOcupado2, this.tempoAtivo2);
        this.calculador.tempoMedioEmFila(this.tempoEmFila1, this.tempoTotal);
        this.calculador.tempoMedioEmFila(this.tempoEmFila2, this.tempoTotal);
        this.calculador.tempoEmFalha(this.tempoFalha1, this.tempoTotal);
        this.calculador.tempoEmFalha(this.tempoFalha2, this.tempoTotal);
        this.mediaPonderada(this.serv1.returnaEstadosFila());
        this.mediaPonderada(this.serv2.returnaEstadosFila());
    }

    public double mediaPonderada(ArrayList<Integer> lista) {
        int temp = 0;
        int i = 0;
        while (i <= this.tempoTotal) {
            temp += lista.remove(0) / this.tempoTotal;
            ++i;
        }
        return temp;
    }
}
