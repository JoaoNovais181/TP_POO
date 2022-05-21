package Model;

public class FornecedorTipo1 extends Fornecedor
{

    public FornecedorTipo1(String nome, double valorBase, double imposto) {
        super(nome, valorBase, imposto);
    }

    public FornecedorTipo1(Fornecedor f)
    {
        super(f.getNome(), f.getValorBase(), f.getImposto());
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

    @Override
    public boolean equals(Object o)
    {
        if (this==o) return true;

        if ((o==null) || (o.getClass() != this.getClass())) return false;

        Fornecedor f = (Fornecedor) o;

        return (super.equals(f)) && (o instanceof FornecedorTipo1);
    }

    @Override
    public Fornecedor clone() 
    {
        return new FornecedorTipo1(this);
    }
    
}