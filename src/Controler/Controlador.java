package Controler;

import Model.*;
import View.Apresentacao;
import java.io.Serializable;


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
            a.printMainMenu();
            op = (int)i.lerDouble(a, "Introduza a opcao", 0, 7);
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
                    a.listarDevices(g);
                    break;
                case 3:
                    a.listarCasasInteligentes(g);
                    break;
                case 4:
                    a.listarFornecedores(g);
                    break;
                case 5:
                    a.printHoraAtual(g);
                    break;
                case 6:
                    this.ce.SalvarEstado(g,a);
                    break;
                case 7:
                    g = this.ce.CarregarEstado(g, a);
                    break;
                default:
                    break;
            }
        }
    }




    public void controladorCriacao (GesModel g, Apresentacao a)
    {
        a.printMenuCriacao();
        int opc = (int)this.i.lerDouble(a, "Introduza uma opcao: ", 0, 3);
        switch (opc) 
        {
            case 0:
                return;
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