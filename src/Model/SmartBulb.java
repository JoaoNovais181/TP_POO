package Model;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class SmartBulb extends SmartDevice   
{
    public static final int WARM = 2;
    public static final int NEUTRAL = 1;
    public static final int COLD = 0;
    
    private int tone;
    private int dimensao;

    public SmartBulb() {
        super(); // Chamar o construtor de SmartDevice por omissao
        this.tone = NEUTRAL;
    }

    public SmartBulb(String id, boolean on, double consumoEnergeticoHora, int tone, int dimensao) {
        super(id, true, consumoEnergeticoHora);
        // initialise instance variables
        this.tone = tone;
        this.dimensao = dimensao;
    }

    public SmartBulb(SmartBulb bulb)
    {
        super(bulb.getID(), bulb.getOn(), bulb.getConsumoEnergeticoHora());
        this.tone = bulb.getTone();
        this.dimensao = bulb.getDimensao();
    }

    public void setTone(int t) 
    {
        if (t>WARM) this.tone = WARM;
        else if (t<COLD) this.tone = COLD;
        else this.tone = t;
    }

    public int getTone() 
    {
        return this.tone;
    }

    public void setDimensao(int dimensao)
    {
        this.dimensao = dimensao;
    }

    public int getDimensao()
    {
        return this.dimensao;
    }

    public String toString ()
    {
        return "SmartBulb{id: " + this.getID() + ", on=" + this.getOn() + ", tone=" + ((this.tone==1) ?"Neutral" :(this.tone > 1) ?"WARM" : "COLD") + "}";
    }

    @Override
    public double calculaConsumo (LocalDateTime atual, LocalDateTime nova)
    {
        if (nova.isBefore(atual))
            return -1;
        
        double horas = ChronoUnit.HOURS.between(atual, nova);
        return this.getConsumoEnergeticoHora() * (10 + (4 - this.tone - 1) * 3) * horas / 1000;
    }

    @Override
    public SmartBulb clone ()
    {
        return new SmartBulb(this);
    }
}

