package Model;

public class FornecedorTipo5 extends Fornecedor
{

    public FornecedorTipo5(String nome, double valorBase, double imposto) {
        super(nome, valorBase, imposto);
    }

    public FornecedorTipo5(Fornecedor f)
    {
        super(f.getNome(), f.getValorBase(), f.getImposto());
    }


    @Override
    public double faturacao(double consumoTotal, int numeroDispositivos) {
        double multiplicadorImposto = 1;
        double multiplicadorValorBase = 1;
        if (numeroDispositivos > 10)
        {
            multiplicadorImposto = 1.5;
            multiplicadorValorBase = 0.75;
        }
        return (consumoTotal * this.getValorBase() * multiplicadorValorBase) * (1+ multiplicadorImposto * this.getImposto());
    }

    @Override
    public String toString() {
        String s = "Fornecedor{ nome: " + this.getNome() + ", valor base=" + this.getValorBase() + ", imposto=" + this.getImposto() + "\nformula= ";
        s += "\nse nº dispositivos <= 10:\n\t consumoTotal * valorBase * (1+imposto)";
        s += "\nse nº dispositivos > 10:\n\t consumoTotal * valorBase * 0.75 * (1+ (1.5*imposto))";
        return s + "}";
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this==o) return true;

        if ((o==null) || (o.getClass() != this.getClass())) return false;

        Fornecedor f = (Fornecedor) o;

        return (super.equals(f)) && (o instanceof FornecedorTipo5);
    }

    @Override
    public Fornecedor clone() 
    {
        return new FornecedorTipo5(this);
    }
    
}