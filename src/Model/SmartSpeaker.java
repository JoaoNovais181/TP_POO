package Model;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class SmartSpeaker extends SmartDevice
{
    public static final int MAX = 20; //volume máximo
    
    private int volume;
    private String channel;
    private String marca;


    public SmartSpeaker() {
        super();
        this.channel = "";
        this.volume = 0;
    }

    public SmartSpeaker(String cod, boolean on, double consumoEnergeticoHora, String channel, String marca, int volume) {
        super(cod, on, consumoEnergeticoHora);
        this.channel = channel;
        if (volume<0) this.volume=0;
        else if (volume>MAX) this.volume = MAX;
        else this.volume = volume;
        this.marca = marca;
    }

    public SmartSpeaker (SmartSpeaker speaker)
    {
        super(speaker.getID(), speaker.getOn(), speaker.getConsumoEnergeticoHora());
        this.channel = speaker.getChannel();
        this.volume = speaker.getVolume();
        this.marca = speaker.getMarca();
    }

    // public SmartSpeaker(String cod, boolean on, String channel, int volume, LocalDateTime ultimaFaturacao) {
        // super(cod, on, ultimaFaturacao);
        // initialise instance variables
        // this.channel = channel;
        // if (volume<0) this.volume=0;
        // else if (volume>MAX) this.volume = MAX;
        // else this.volume = volume;
    // }

    public void volumeUp() {
        if (this.volume<MAX) this.volume++;
    }

    public void volumeDown() {
        if (this.volume>0) this.volume--;
    }

    public int getVolume() 
    {
        return this.volume;
    }
    
    public String getChannel() 
    {
        return this.channel;
    }
    
    public void setChannel(String c) 
    {
        this.channel=c;
    }

    public String getMarca()
    {
        return this.marca;
    }

    public void setMarca(String marca)
    {
        this.marca = marca;
    }

    @Override
    public String toString ()
    {
        return "SmarSpeaker{id: " + this.getID() + ", on=" + this.getOn() + ", Channel: " + this.channel + ", Volume: " + this.volume + "}"; 
    }

    @Override
    public double calculaConsumo(LocalDateTime atual, LocalDateTime nova) {
        if (nova.isBefore(atual))
            return -1;
        
        double horas = ChronoUnit.HOURS.between(atual, nova);
        return this.getConsumoEnergeticoHora() * horas * (1000 / (30-this.volume)) / 1000 ;
    }

    @Override
    public SmartSpeaker  clone() 
    {
        return new SmartSpeaker(this);
    }

}
