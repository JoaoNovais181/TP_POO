package Model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.Serializable;

public abstract class Fornecedor implements Serializable
{

    private String nome;
    private HashMap<String, CasaInteligente> casas;  // Considera-se como key o NIF do proprietario
    private double valorBase, imposto;

    public Fornecedor (String nome, double valorBase, double imposto)
    {
        this.nome = nome;
        this.valorBase = valorBase;
        this.imposto = imposto;
        this.casas = new HashMap<>();
    }

    public String getNome ()
    {
        return this.nome;
    }

    public double getValorBase ()
    {
        return this.valorBase;
    }

    public double getImposto ()
    {
        return this.imposto;
    }

    public void setValorBase (double valorBase)
    {
        this.valorBase = valorBase;
    }

    public void setImposto (double imposto)
    {
        this.imposto = imposto;
    }

    public double getVolumeFaturacao ()
    {
        double v = 0;

        for (CasaInteligente casa : this.casas.values())
            v += casa.getValorTotalFaturacao();

        return v;
    }

    public List<Fatura> getFaturas ()
    {
        List<Fatura> v = new ArrayList<Fatura>();

        for (CasaInteligente casa : this.casas.values())
            v.addAll(casa.getFaturas());

        return v;
    }



    public HashMap<String, CasaInteligente> getCasas ()
    {
        return (HashMap<String, CasaInteligente>) this.casas.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry->entry.getValue()));
    }

    public void addCasa (CasaInteligente casa)   // Se ja existir alguma casa com o NIF da casa dada como argumento, é substituida
    {
        this.casas.put(casa.getNIFproprietario(), casa);
    }

    public void removeCasa (CasaInteligente casa)
    {
        this.casas.remove(casa.getNIFproprietario());
    }

    public abstract double faturacao (double consumoTotal, int numeroDispositivos);

    public abstract String toString(); 

    public abstract Fornecedor clone();

    public boolean equals (Object o)
    {
        if (this==o) return true;

        if ((o==null) || (o.getClass() != this.getClass())) return false;

        Fornecedor f = (Fornecedor) o;
        return (this.valorBase == f.getValorBase()) && (this.imposto == f.getImposto()) && (this.nome.equals(f.getNome())) &&
                this.casas.equals(f.getCasas());
    }
    // {
            // String s = "Fornecedor{ nome: " + this.nome + ", valor base=" + this.getValorBase() + ", imposto=" + this.getImposto() + "\nformula= ";
            // int nForms = this.formulas.size(), i=0;
            // if (nForms > 1)
            // {
                // for (Map.Entry<Integer,Formula> formula : this.formulas.entrySet())
                // {
                    // if (i++<nForms-1)
                        // s += "\nse nº dispositivos <= " + formula.getKey() + ":\n\t" + formula.getValue();
                    // else
                        // s += "\nse nº dispositivos > " + (formula.getKey()-1) + ":\n\t" + formula.getValue();
                // }
            // }
            // else
                // s += this.formulas.get(0).toString();
            // return s + "}";
    // }

}