package View;

import java.io.Serializable;
import java.util.List;
import Model.CasaInteligente;

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
        int maxSize = 0;
        for (CasaInteligente casa : casas)
        {
            String str = casa.toString();
            if (str.length()+4 > maxSize) maxSize = str.length()+4;
        }

        this.o.printLine(maxSize);
        this.o.printMessage("Lista de Casas Inteligentes");
        this.o.printLine(maxSize);
        for (CasaInteligente casa : casas)
            this.o.printMessage(casa.toString());
        this.o.printLine(maxSize);
    }
}