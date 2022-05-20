package Model;

public class FornecedorTipo3 extends Fornecedor
{

    public FornecedorTipo3(String nome, double valorBase, double imposto) {
        super(nome, valorBase, imposto);
    }

    @Override
    public double faturacao(double consumoTotal, int numeroDispositivos) {
        double variavel = 0;
        if (numeroDispositivos < 5)
            variavel = 0.65;
        else if (numeroDispositivos < 10)
            variavel = 0.80;
        else 
            variavel = 0.95;
        return variavel * (consumoTotal * this.getValorBase()) * (1+this.getImposto());
    }

    @Override
    public String toString() {
        String s = "Fornecedor{ nome: " + this.getNome() + ", valor base=" + this.getValorBase() + ", imposto=" + this.getImposto() + "\nformula= ";
        s += "\nse nº dispositivos < 5:\n\t 0.65 * consumoTotal * valorBase * (1+imposto)";
        s += "\nse nº dispositivos < 10:\n\t 0.8 * consumoTotal * valorBase * (1+imposto)";
        s += "\nse nº dispositivos >= 10:\n\t 0.95 * consumoTotal * valorBase * (1+imposto)";
        return s + "}";
    }

    @Override
    public boolean equals(Object o)
    {
        if (this==o) return true;

        if ((o==null) || (o.getClass() != this.getClass())) return false;

        Fornecedor f = (Fornecedor) o;

        return (super.equals(f)) && (o instanceof FornecedorTipo3);
    }
    
}