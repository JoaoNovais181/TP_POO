
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
        this.o.printMenu((new String[]{"Criar", "Faturação", "Menu de Listagem", "Apresentar Hora Atual", "Salvar/Carregar estado"}), 
                            "Menu Principal", 0);
    }

    public void printMenuFaturacao ()
    {
        this.o.printMenu((new String[]{"Avançar o tempo", "Listar Faturas", "Estatísticas"}), "Menu de Faturação", 1);
    }
    
    public void printMenuCriacao ()
    {
        this.o.printMenu((new String[]{"Criar SmartDevice", "Criar Casa Inteligente", "Criar Fornecedor"}), "Menu de Criação", 1);
    }

    public void printMenuEstado()
    {
        this.o.printMenu((new String[]{"Salvar estado atual", "Carregar estado de ficheiro"}), "Salvar/Carregar estado", 1);
    }

    public void printMenuListagem ()
    {
        this.o.printMenu((new String[]{"Listar SmartDevices", "Listar Casas Inteligentes", "Listar Fornecedores"}), "Menu de Listagem", 1);
    }

    public void printMenuCriacaoFormula ()
    {
        this.o.printMenu((new String[]{"Formula não condicional", "Formula condicional"}), "Qual tipo de formula pretende", -1);
    }
}