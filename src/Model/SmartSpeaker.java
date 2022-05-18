package Model;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class SmartSpeaker extends SmartDevice
{
    public static final int MAX = 20; //volume máximo
    
    private int volume;
    private String channel;


    public SmartSpeaker() {
        super();
        this.channel = "";
        this.volume = 0;
    }

    public SmartSpeaker(String s) {
        super(s);
        this.channel = "";
        this.volume = 0;
    }

    public SmartSpeaker(String cod, boolean on, String channel, int volume) {
        super(cod, on);
        this.channel = channel;
        if (volume<0) this.volume=0;
        else if (volume>MAX) this.volume = MAX;
        else this.volume = volume;
    }

    public SmartSpeaker (SmartSpeaker speaker)
    {
        super(speaker.getID(), speaker.getOn());
        this.channel = speaker.getChannel();
        this.volume = speaker.getVolume();
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

    public int getVolume() {return this.volume;}
    
    public String getChannel() {return this.channel;}
    
    public void setChannel(String c) {this.channel=c;}

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
        return horas * (1000 / (30-this.volume)) / 1000 ;
    }

    @Override
    public SmartSpeaker  clone() 
    {
        return new SmartSpeaker(this);
    }

}
