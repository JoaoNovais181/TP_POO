package View;

import java.util.List;

public class Apresentacao {

    private ApresentacaoMain am;
    private ApresentacaoSmartDevice asd;
    private ApresentacaoFornecedor af;
    private Output o;

    public Apresentacao ()
    {
        this.am = new ApresentacaoMain();
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

    public void printMenuSelecaoFornecedor (List<String> l)
    {
        this.o.printList(l, "Selecione um Fornecedor", 2);
    }

    public void printMenuSelecaoCasa (List<String> l)
    {
        this.o.printList(l, "Selecione uma Casa", 2);
    }

    public void printMenuSelecaoTipoSmartDevice ()
    {
        this.o.printMenu((new String[]{"SmartBulb", "SmartSpeaker", "SmartCamera"}), "Selecione o tipo de Smart Device", 3);
    }
}