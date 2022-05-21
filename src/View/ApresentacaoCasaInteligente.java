package View;

import java.io.Serializable;
import java.util.List;

import Model.CasaInteligente;
import Model.Fatura;

public class ApresentacaoCasaInteligente implements Serializable
{
    private Output o;

    public ApresentacaoCasaInteligente ()
    {
        this.o = new Output();
    }


    public void printMenuSelecaoCasa (List<String> l)
    {
        this.o.printList(l, "Selecione uma Casa", 2);
    }
    
    public void printMenuSelecaoLocation (List<String> l)
    {
        this.o.printList(l, "Selecione uma Sala", 2);
    }

    public void listarCasasInteligentes (List<CasaInteligente> casas)
    {
        if (casas.size()==0)
        {
            String msg = "Não há Casas Inteligentes!";
            this.o.printLine(msg.length()+2);
            this.o.printMessage(msg);
            this.o.printLine(msg.length()+2);
            return ;
        }
        int maxSize = 60;

        this.o.printLine(maxSize);
        this.o.printMessage("Lista de Casas Inteligentes");
        this.o.printLine(maxSize);
        for (CasaInteligente casa : casas)
            this.o.printMessage(casa.toString());
        this.o.printLine(maxSize);
    }

    public void listarFaturas (List<Fatura> faturas)
    {
        if (faturas.size()==0)
        {
            String msg = "Não há Faturas!";
            this.o.printLine(msg.length()+2);
            this.o.printMessage(msg);
            this.o.printLine(msg.length()+2);
            return ;
        }
        String message = "Lista de Faturas";
        int maxSize = message.length()+6;

        this.o.printLine(maxSize);
        System.out.println(message);
        this.o.printLine(maxSize);

        for (Fatura o : faturas)
            this.o.printMessage(o.toString());

        this.o.printMessage(" 0 ) Voltar ");
        this.o.printLine(maxSize);
    }

    public void printMaisConsumidoras (List<String> l)
    {
        String message = "Casas Mais Consumidoras";
        int maxSize = message.length()+6;
        
        this.o.printLine(maxSize);
        System.out.println(message);
        this.o.printLine(maxSize);

        for (String s : l)
            this.o.printMessage(s);

        this.o.printMessage(" 0 ) Voltar ");
        this.o.printLine(maxSize);
    }

}