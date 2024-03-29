package View;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import Model.Fatura;
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

    public void printMenuEstado() 
    {
        this.am.printMenuEstado();
    }

    public void printMenuListagem ()
    {
        this.am.printMenuListagem();
    }

    public void printMenuFaturacao ()
    {
        this.am.printMenuFaturacao();
    }

    public void printMenuCriacaoFormula ()
    {
        this.am.printMenuCriacaoFormula();
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
        this.o.clear();
        this.asd.listarDevices(g.getDevices());
    }

    public void listarFornecedores (GesModel g)
    {
        this.o.clear();
        this.af.listarFornecedores(g.getFornecedores());
    }

    public void listarCasasInteligentes (GesModel g)
    {
        this.o.clear();
        this.ac.listarCasasInteligentes(g.getCasas()); 
    }

    public void listarFaturas (GesModel g)
    {
        this.o.clear();
        this.ac.listarFaturas(g.getFaturas());
    }

    public void listarFaturas (List<Fatura> faturas)
    {
        this.ac.listarFaturas(faturas);
    }



    public void printHoraAtual (GesModel g)
    {
        LocalDateTime l = g.getHoraAtual();
        String hora = (l.getDayOfMonth() + "/" + l.getMonthValue() + "/" + l.getYear() + " " + l.getHour() + ":" + l.getMinute());
        this.o.printLine(hora.length()+2);
        this.o.printMessage(hora);
        this.o.printLine(hora.length()+2);
    }

    public void printMenuMudancas ()
    {
        this.am.printMenuMudancas();
    }

    public void printMenuMudancasFornecedor()
    {
        this.am.printMenuMudancasFornecedor();
    }

    public void selecaoDevices (List<String> devices)
    {
        this.asd.selecaoDevices(devices);
    }

    public void clear()
    {
        this.o.clear();
    }

    public void printMenuEstatisticas ()
    {
        this.am.printMenuEstatisticas();
    }

    public void printMaisConsumidoras (List<String> l)
    {
        this.ac.printMaisConsumidoras(l);
    }
}