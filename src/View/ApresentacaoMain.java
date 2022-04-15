
package View;

public class ApresentacaoMain {

    private Output o;

    public ApresentacaoMain ()
    {
        this.o = new Output();
    }

    public void printMainMenu ()
    {
        o.printMenu((new String[]{"Criar", "Apresentar Faturas"}), "Menu Principal", 0);
    }
    
    public void printMenuCriacao ()
    {
        o.printMenu((new String[]{"Criar SmartDevice", "Criar Casa Inteligente", "Criar Fornecedor"}), "Menu de Criação", 1);
    }
}