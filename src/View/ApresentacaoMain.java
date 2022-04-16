
package View;

import java.io.Serializable;

public class ApresentacaoMain implements Serializable
{

    private Output o;

    public ApresentacaoMain ()
    {
        this.o = new Output();
    }

    public void printMainMenu ()
    {
        o.printMenu((new String[]{"Criar", "Listar SmartDevices", "Listar Casas Inteligentes", "Listar Fornecedores", "Apresentar Hora Atual", "Salvar Estado Atual", "Carregar estado"}), 
                            "Menu Principal", 0);
    }
    
    public void printMenuCriacao ()
    {
        o.printMenu((new String[]{"Criar SmartDevice", "Criar Casa Inteligente", "Criar Fornecedor"}), "Menu de Criação", 1);
    }
}