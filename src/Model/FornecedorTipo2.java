package Model;

public class FornecedorTipo2 extends Fornecedor
{

    public FornecedorTipo2(String nome, double valorBase, double imposto) {
        super(nome, valorBase, imposto);
    }

    public FornecedorTipo2(Fornecedor f)
    {
        super(f.getNome(), f.getValorBase(), f.getImposto());
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

    @Override
    public boolean equals(Object o)
    {
        if (this==o) return true;

        if ((o==null) || (o.getClass() != this.getClass())) return false;

        Fornecedor f = (Fornecedor) o;

        return (super.equals(f)) && (o instanceof FornecedorTipo2);
    }

    @Override
    public Fornecedor clone() 
    {
        return new FornecedorTipo2(this);
    }
    
    
}