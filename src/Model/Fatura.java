package Model;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Fatura implements Serializable
{
    private String nomeProprietario, NIFproprietario;
    private LocalDateTime inicio, fim;
    private double consumo, custo;

    public Fatura (String nomeProprietario, String NIFproprietario, LocalDateTime inicio, LocalDateTime fim, double consumo, double custo)
    {
        this.nomeProprietario = nomeProprietario;
        this.NIFproprietario = NIFproprietario;
        this.inicio = inicio;
        this.fim = fim;
        this.consumo = consumo;
        this.custo = custo;
    }

    public LocalDateTime getInicio ()
    {
        return this.inicio;
    }

    public LocalDateTime getFim ()
    {
        return this.fim;
    }

    public double getConsumo ()
    {
        return this.consumo;
    }

    public double getCusto ()
    {
        return this.custo;
    }


    public String toString ()
    {
        String inicioStr = (this.inicio.getDayOfMonth() + "/" + this.inicio.getMonthValue() + "/" + this.inicio.getYear() + " " + this.inicio.getHour() + ":" + this.inicio.getMinute());
        String fimStr = (this.fim.getDayOfMonth() + "/" + this.fim.getMonthValue() + "/" + this.fim.getYear() + " " + this.fim.getHour() + ":" + this.fim.getMinute());
        return "------------------------------------------------------------"+
                "\nNome: " + this.nomeProprietario + "\nNIF: " + this.NIFproprietario +
                "\nData inicial: " + inicioStr + "\nData final: " + 
                fimStr + "\nConsumo Total: " + this.consumo + "kWh" +
                "\nCusto Total: " + this.custo + "€" + 
                "\n------------------------------------------------------------";
    }
}