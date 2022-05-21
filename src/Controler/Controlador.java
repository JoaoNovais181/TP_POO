package Controler;

import Model.*;
import View.Apresentacao;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


public class Controlador implements Serializable
{

    private ControladorCasa ca;
    private ControladorSmartDevice cs;
    private ControladorFornecedor cf;
    private ControladorEstado ce;
    private Input i;
    private GesModel g;

    public Controlador ()
    {
        this.ca = new ControladorCasa();
        this.cs = new ControladorSmartDevice();
        this.cf = new ControladorFornecedor();
        this.ce = new ControladorEstado();
        this.i = new Input();
        this.g = new GesModel();
    }

    public void controlador (GesModel ges, Apresentacao a) throws CasaNaoExisteException, NumberFormatException, MudancaInvalidaException, FornecedorNaoExisteException, SmartDeviceNaoExisteException
    {
        this.g = ges;
        boolean running = true;
        while (running)
        {
            int op;
            a.clear();
            a.printMainMenu();
            op = (int)i.lerDouble(a, "Introduza uma opcao:", 0, 6);
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
                case 6:
                    this.controladorMudancas(g, a);
                    break;
                default:
                    break;
            }
        }
    }

    public void controladorListagem (GesModel ges, Apresentacao a)
    {
        this.g = ges;
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


    public void controladorCriacao (GesModel ges, Apresentacao a) throws CasaNaoExisteException
    {
        this.g = ges;
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

    public void controladorEstado (GesModel ges, Apresentacao a)
    {
        boolean running = true;

        while (running)
        {
            a.clear();
            a.printMenuEstado();
            int opc = (int)this.i.lerDouble(a, "Introduza uma opcao:", 0, 4);
            switch (opc)
            {
                case 0:
                    running = false;
                    break;
                case 1:
                    this.ce.SalvarEstado(g.clone(), a);
                    break;
                case 2:
                    this.g = this.ce.CarregarEstado(g, a);
                    break;
                case 3: 
                    this.g = this.ce.CarregarEstadoTXT(g, a);
                    break;
                case 4: 
                    this.g = new GesModel();
                    break;
            }
        }
    }

    public void controladorFaturacao (GesModel g, Apresentacao a) throws NumberFormatException, MudancaInvalidaException, FornecedorNaoExisteException, CasaNaoExisteException, SmartDeviceNaoExisteException
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
                    g.ParseMudancas();
                    break;
                case 2:
                    a.listarFaturas(g);
                    opc = (int)i.lerDouble(a, "Introduza uma opcao:", 0, 0);
                    break;
                case 3:
                    this.controladorEstatisticas(g, a);
                    break;
                default:
                    break;
            }
        }
    }

    public void controladorMudancas (GesModel g, Apresentacao a)
    {  
        boolean running = true;

        while (running)
        {
            a.clear();
            a.printMenuMudancas();
            int opc = (int)this.i.lerDouble(a, "Introduza uma opcao:", 0, 3);
            switch(opc)
            {
                case 0:
                    running = false;
                    break;
                case 1:
                    this.controladorMudancasFornecedor(g, a);
                    break;
                case 2:
                    String casa = this.ca.lerCasa(g, a);
                    String fornecedor = this.cf.lerFornecedor2(g, a);

                    g.AddMudanca("Casa-" + casa + ",Fornecedor:" + fornecedor);
                    break;
                case 3:
                    String device = this.cs.lerDevice(g, a);

                    a.printMessage("1) Desligar SmartDevice");
                    a.printMessage("2) Ligar SmartDevice");

                    int op = (int) this.i.lerDouble(a, "Introduza uma opcao:", 1, 2);

                    g.AddMudanca("SmartDevice-" + device + ",SetOn:" + ((op==1) ?"on" : "off"));
                    break;
                default:
                    break;
            }
        }
    }

    public void controladorMudancasFornecedor (GesModel g, Apresentacao a)
    {
        boolean running = true;

        String fornecedor;
        while (running) 
        {
            a.clear();
            a.printMenuMudancasFornecedor();
            int opc = (int)this.i.lerDouble(a, "Introduza uma opcao:", 0, 2);
            switch(opc)
            {
                case 0:
                    running = false;
                    break;
                case 1:
                    fornecedor = this.cf.lerFornecedor2(g, a);

                    double valorBaseNovo = this.i.lerDouble(a, "Introduza o novo Valor Base:", 0);

                    g.AddMudanca("Fornecedor-" + fornecedor + ",ValorBase:" + valorBaseNovo);
                    break;
                case 2:
                    fornecedor = this.cf.lerFornecedor2(g, a);

                    double imposto = this.i.lerDouble(a, "Introduza o novo fator multiplicativo do imposto:", 0);

                    g.AddMudanca("Fornecedor-" + fornecedor + ",Imposto:" + imposto);
                    break;
                default:
                    break;
            }
        }
    }

    public void controladorEstatisticas (GesModel g, Apresentacao a)
    {
        boolean running = true;

        while (running)
        {
            a.clear();
            a.printMenuEstatisticas();
            int opc = (int)this.i.lerDouble(a, "Introduza uma opcao:", 0, 4);
            switch(opc)
            {
                case 0:
                    running = false;
                    break;
                case 1:
                    CasaInteligente cmg = g.casaMaisGastadora();

                    if (cmg == null)
                    {
                        a.printMessageWLineUnder("Não há dados de faturação ainda!");
                        continue;
                    }

                    a.clear();
                    a.printMessageWLineUnder("Casa Mais Gastadora");
                    a.printMessageWLineUnder("Valor Gasto: " + cmg.getValorTotalFaturacao() + "€ -> "+ cmg.toString());
                    opc = (int)this.i.lerDouble(a, "0) Voltar", 0, 0);
                    break;
                case 2: 
                    Fornecedor f = g.getFornecedorMaisFaturante();

                    if (f == null)
                    {
                        a.printMessageWLineUnder("Não há dados de faturação ainda!");
                        continue;
                    }

                    a.clear();
                    a.printMessageWLineUnder("Fornecedor Mais Faturante");
                    a.printMessage("Valor da Faturacao: " + f.getVolumeFaturacao() + "€ -> " + f.toString());
                    a.printMessage("------------------------");
                    opc = (int)this.i.lerDouble(a, "0) Voltar", 0, 0);
                    break;
                case 3:
                    String fornecedor = this.cf.lerFornecedor2(g, a);
                    List<Fatura> l = g.getFaturasFornecedor(fornecedor);

                    a.clear();
                    a.listarFaturas(l);
                    opc = (int)this.i.lerDouble(a, "0) Voltar", 0, 0);
                    break;
                case 4:
                    LocalDateTime li = this.ce.lerData(a), ls;
                    do
                    {
                        ls = this.ce.lerData(a);
                    } while (ls.isBefore(li));

                    List<String> m = g.getMaioresConsumidores(li, ls);

                    a.clear();
                    a.printMaisConsumidoras(m);
                    opc = (int)this.i.lerDouble(a, "0) Voltar", 0, 0);
                    break;
                default:
                    break;
            }
        }
    }
    
}