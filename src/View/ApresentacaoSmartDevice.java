package View;

public class ApresentacaoSmartDevice {

    private Output o;

    public ApresentacaoSmartDevice ()
    {
        this.o = new Output();
    }

    public void printIntroduzaID ()
    {
        System.out.print("Introduza ID do dispositivo: ");
    }
    
    public void printMenuCor()
    {
        this.o.printMenu((new String[]{"Cold", "Neutral", "Warm"}), "Qual é a cor do dispositivo?", -1);
    }
}