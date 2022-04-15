package Model;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Fornecedor {

    private String nome;
    private double valorBase, imposto;
    private HashMap<String, CasaInteligente> casas;  // Considera-se como key o NIF do proprietario

    public Fornecedor ()
    {
        this.nome = "";
        this.valorBase = 0;
        this.imposto = 0;
        this.casas = new HashMap<String, CasaInteligente>();
    }

    public Fornecedor (String nome, double valorBase, double imposto)
    {
        this.nome = nome;
        this.valorBase = valorBase;
        this.imposto = imposto;
        this.casas = new HashMap<String, CasaInteligente>();
    }

    public Fornecedor (Fornecedor umFornecedor)
    {
        this.nome = umFornecedor.getNome();
        this.valorBase = umFornecedor.getValorBase();
        this.imposto = umFornecedor.getImposto();
        this.casas = (HashMap<String, CasaInteligente>) umFornecedor.getCasas().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry->entry.getValue()));
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

    public Fornecedor clone()
    {
        return new Fornecedor(this);
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

    public double faturacao (double consumoTotal, int numeroDispositivos)
    {
        if (numeroDispositivos < 10)
            return 0.6*(1+this.imposto)*valorBase*consumoTotal;
        if (numeroDispositivos<20)
            return 0.75*(1+this.imposto)*this.valorBase*consumoTotal;
        return 0.9*(1+this.imposto)*this.valorBase*consumoTotal;
    }

}