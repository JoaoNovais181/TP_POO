package Model;

public class FornecedorTipo2 extends Fornecedor
{

    public FornecedorTipo2(String nome, double valorBase, double imposto) {
        super(nome, valorBase, imposto);
    }

    @Override
    public double faturacao(double consumoTotal, int numeroDispositivos) {
        return ((numeroDispositivos>20) ?1 : 0.75) * (consumoTotal * this.getValorBase()) * (1+this.getImposto());
    }

    @Override
    public String toString() {
        String s = "Fornecedor{ nome: " + this.getNome() + ", valor base=" + this.getValorBase() + ", imposto=" + this.getImposto() + "\nformula= ";
        s += "\nse nº dispositivos <= 20:\n\t 0.75 * consumoTotal * valorBase * (1+imposto)";
        s += "\nse nº dispositivos > 20:\n\t consumoTotal * valorBase * (1+imposto)";
        return s + "}";
    }
    
}