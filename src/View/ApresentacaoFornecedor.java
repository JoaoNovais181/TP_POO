package View;

import java.io.Serializable;
import java.util.List;
import Model.Fornecedor;

public class ApresentacaoFornecedor implements Serializable
{
    private Output o;

    public ApresentacaoFornecedor ()
    {
        this.o = new Output();
    }

    public void printMenuSelecaoFornecedor (List<String> l)
    {
        this.o.printList(l, "Selecione um Fornecedor", 2);
    }

    public void listarFornecedores (List<Fornecedor> fornecedores)
    {
        if (fornecedores.size()==0)
        {
            String msg = "Não há Fornecedores!";
            this.o.printLine(msg.length()+2);
            this.o.printMessage(msg);
            this.o.printLine(msg.length()+2);
            return ;
        }
        int maxSize = 60;

        this.o.printLine(maxSize);
        this.o.printMessage("Lista de Fornecedores");
        this.o.printLine(maxSize);
        for (Fornecedor fornecedor : fornecedores)
            this.o.printMessage(fornecedor.toString() + "\n");
        this.o.printLine(maxSize);
    }
}