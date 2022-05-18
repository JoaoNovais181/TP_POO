package Controler;

import Model.*;
import View.Apresentacao;
import java.io.Serializable;
import java.time.LocalDateTime;


public class Controlador implements Serializable
{

    private ControladorCasa ca;
    private ControladorSmartDevice cs;
    private ControladorFornecedor cf;
    private ControladorEstado ce;
    private Input i;

    public Controlador ()
    {
        this.ca = new ControladorCasa();
        this.cs = new ControladorSmartDevice();
        this.cf = new ControladorFornecedor();
        this.ce = new ControladorEstado();
        this.i = new Input();
    }

    public void controlador (GesModel g, Apresentacao a)
    {
        boolean running = true;
        while (running)
        {
            int op;
            a.clear();
            a.printMainMenu();
            op = (int)i.lerDouble(a, "Introduza uma opcao:", 0, 5);
            switch (op)
            {
                case 0:
                    a.printMessageWLineAbove("A sair..");
                    running = false;
                    break;
                case 1:
                    this.controladorCriacao(g, a);
                    break;
                case 2:
                    this.controladorFaturacao(g, a);
                    break;
                case 3:
                    this.controladorListagem(g, a);
                    break;
                case 4:
                    a.printHoraAtual(g);
                    a.printMessageWLineUnder(" 0 ) Voltar");
                    op = (int)i.lerDouble(a, "Introduza uma opcao:", 0, 0);
                    break;
                case 5:
                    this.controladorEstado(g, a);
                    break;
                default:
                    break;
            }
        }
    }

    public void controladorListagem (GesModel g, Apresentacao a)
    {
        boolean running = true;
        while (running)
        {
            int op;
            a.clear();
            a.printMenuListagem();
            op = (int)i.lerDouble(a, "Introduza uma opcao:", 0, 3);
            switch(op)
            {
                case 0:
                    running = false;
                    break;
                case 1:
                    a.listarDevices(g);
                    a.printMessageWLineUnder(" 0 ) Voltar");
                    op = (int)i.lerDouble(a, "Introduza uma opcao:", 0, 0);
                    break;
                case 2:
                    a.listarCasasInteligentes(g);
                    a.printMessageWLineUnder(" 0 ) Voltar");
                    op = (int)i.lerDouble(a, "Introduza uma opcao:", 0, 0);
                    break;
                case 3:
                    a.listarFornecedores(g);
                    a.printMessageWLineUnder(" 0 ) Voltar");
                    op = (int)i.lerDouble(a, "Introduza uma opcao:", 0, 0);
                    break;
                default:
                    break;
            }
        }
    }


    public void controladorCriacao (GesModel g, Apresentacao a)
    {
        boolean running = true;
        
        while (running)
        {
            a.clear();
            a.printMenuCriacao();
            int opc = (int)this.i.lerDouble(a, "Introduza uma opcao: ", 0, 3);
            switch (opc) 
            {
                case 0:
                    running = false;
                    break;
                case 1:
                    this.cs.controladorCriacaoSmartDevice(g, a, this.ca, this.cf);
                    break;
                case 2:
                    this.ca.controladorCriacaoCasa(g, a, this.cf);
                    break;
                case 3:
                    this.cf.criarFornecedor(g, a);
                    break;
                default:
                    break;
            }
        }
    }

    public void controladorEstado (GesModel g, Apresentacao a)
    {
        boolean running = true;

        while (running)
        {
            a.clear();
            a.printMenuEstado();
            int opc = (int)this.i.lerDouble(a, "Introduza uma opcao:", 0, 2);
            switch (opc)
            {
                case 0:
                    running = false;
                    break;
                case 1:
                    this.ce.SalvarEstado(g, a);
                    break;
                case 2:
                    this.ce.CarregarEstado(g, a);
                    break;
            }
        }
    }

    public void controladorFaturacao (GesModel g, Apresentacao a)
    {
        boolean running = true;

        while (running)
        {
            a.clear();
            a.printMenuFaturacao();
            int opc = (int)this.i.lerDouble(a, "Introduza uma opcao:", 0, 3);
            switch (opc)
            {
                case 0:
                    running = false;
                    break;
                case 1:
                    LocalDateTime d = this.ce.lerData(a);
                    while (d.isBefore(g.getHoraAtual()))
                    {
                        a.printMessageWLineAbove("erro: Data inserida anterior à data atual do modelo.\n");
                        d = this.ce.lerData(a);
                    }
                    g.calclulaFaturacao(d);
                    break;
                case 2:
                    a.listarFaturas(g);
                    opc = (int)i.lerDouble(a, "Introduza uma opcao:", 0, 0);
                    break;
                case 3:
                default:
                    break;
            }
        }
    }
    
}