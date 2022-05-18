package Model;

public class FornecedorTipo4 extends Fornecedor
{

    public FornecedorTipo4(String nome, double valorBase, double imposto) {
        super(nome, valorBase, imposto);
    }

    @Override
    public double faturacao(double consumoTotal, int numeroDispositivos) {
        double multiplicadorImposto = 1;
        if (numeroDispositivos < 10)
            multiplicadorImposto = 0.6;
        return (consumoTotal * this.getValorBase()) * (1+ multiplicadorImposto * this.getImposto());
    }

    @Override
    public String toString() {
        String s = "Fornecedor{ nome: " + this.getNome() + ", valor base=" + this.getValorBase() + ", imposto=" + this.getImposto() + "\nformula= ";
        s += "\nse nº dispositivos < 5:\n\t 0.65 * consumoTotal * valorBase * (1+imposto)";
        s += "\nse nº dispositivos < 10:\n\t 0.8 consumoTotal * valorBase * (1+imposto)";
        s += "\nse nº dispositivos >= 10:\n\t 0.95 consumoTotal * valorBase * (1+imposto)";
        return s + "}";
    }
    
}