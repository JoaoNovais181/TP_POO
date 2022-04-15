package Model;
import java.time.LocalDateTime;

public class Fatura {

    private LocalDateTime inicio, fim;
    private double consumo, custo;

    public Fatura ()
    {
        this.inicio = null;
        this.fim = null;
        this.consumo = 0;
        this.custo = 0;
    }

    public Fatura (LocalDateTime inicio, LocalDateTime fim, double consumo, double custo)
    {
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
        return "------------------------------------------------------------"+
                "Data inicial: " + this.inicio.toString() + "\nData final: " + 
                this.fim.toString() + "\nConsumo Total: " + this.consumo + 
                "\nCusto Total: " + this.custo +
                "------------------------------------------------------------";
    }
}