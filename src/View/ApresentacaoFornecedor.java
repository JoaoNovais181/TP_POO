package View;

import java.util.List;

public class ApresentacaoFornecedor
{
    private Output o;

    public ApresentacaoFornecedor ()
    {
        this.o = new Output();
    }

    public void printMenuSelecaoFornecedor (List<String> l)
    {
        this.o.printMenu((String[])l.toArray(), "Selecione um Fornecedor", 2);
    }
}