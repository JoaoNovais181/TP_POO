package Model;

public class FornecedorTipo1 extends Fornecedor
{

    public FornecedorTipo1(String nome, double valorBase, double imposto) {
        super(nome, valorBase, imposto);
    }

    @Override
    public double faturacao(double consumoTotal, int numeroDispositivos) {
        return (consumoTotal * this.getValorBase()) * (1+this.getImposto());
    }

    @Override
    public String toString() {
        String s = "Fornecedor{ nome: " + this.getNome() + ", valor base=" + this.getValorBase() + ", imposto=" + this.getImposto() + "\nformula= ";
        s += "consumoTotal * valorBase * (1+imposto)";
        return s + "}";
    }
    
}