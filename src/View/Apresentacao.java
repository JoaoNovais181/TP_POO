package View;

import java.io.Serializable;
import java.util.List;
import Model.GesModel;

public class Apresentacao implements Serializable
{

    private ApresentacaoMain am;
    private ApresentacaoCasaInteligente ac;
    private ApresentacaoSmartDevice asd;
    private ApresentacaoFornecedor af;
    private Output o;

    public Apresentacao () 
    {
        this.am = new ApresentacaoMain();
        this.ac = new ApresentacaoCasaInteligente();
        this.asd = new ApresentacaoSmartDevice();
        this.af = new ApresentacaoFornecedor();
        this.o = new Output(); 
    }

    public void printMainMenu()
    {
        this.am.printMainMenu();
    }

    public void printIntroduzaID ()
    {
        this.asd.printIntroduzaID();
    }

    public void printMenuCor ()
    {
        this.asd.printMenuCor();
    }

    public void printMenuCriacao ()
    {
        this.am.printMenuCriacao();
    }

    public void printMessage (String message)
    {
        this.o.printMessage(message);
    }

    public void printMessageWLineAbove (String message)
    {
        this.o.printLine(message.length()+2);
        this.o.printMessage(message);
    }

    public void printMessageWLineUnder (String message)
    {
        this.o.printMessage(message);
        this.o.printLine(message.length()+2);
    }

    public void printMenuSelecaoFornecedor (List<String> l)
    {
        this.af.printMenuSelecaoFornecedor(l);
    }

    public void printMenuSelecaoCasa (List<String> l)
    {
        this.ac.printMenuSelecaoCasa(l);
    }

    public void printMenuSelecaoTipoSmartDevice ()
    {
        this.asd.printMenuSelecaoTipoSmartDevice();
    }

    public void printMenuSelecaoLocation (List<String> l)
    {
        this.ac.printMenuSelecaoLocation(l);
    }

    public void listarDevices (GesModel g)
    {
        this.asd.listarDevices(g.getDevices());
    }

    public void listarFornecedores (GesModel g)
    {
        this.af.listarFornecedores(g.getFornecedores());
    }

    public void listarCasasInteligentes (GesModel g)
    {
        this.ac.listarCasasInteligentes(g.getCasas()); 
    }

    public void printHoraAtual (GesModel g)
    {
        String hora = g.getHoraAtual().toString();
        this.o.printLine(hora.length()+2);
        this.o.printMessage(hora);
        this.o.printLine(hora.length()+2);
    }
}