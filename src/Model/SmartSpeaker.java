package Model;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/*********************************************************************************/
/** DISCLAIMER: Este código foi criado e alterado durante as aulas práticas      */
/** de POO. Representa uma solução em construção, com base na matéria leccionada */ 
/** até ao momento da sua elaboração, e resulta da discussão e experimentação    */
/** durante as aulas. Como tal, não deverá ser visto como uma solução canónica,  */
/** ou mesmo acabada. É disponibilizado para auxiliar o processo de estudo.      */
/** Os alunos são encorajados a testar adequadamente o código fornecido e a      */
/** procurar soluções alternativas, à medida que forem adquirindo mais           */
/** conhecimentos de POO.                                                        */
/*********************************************************************************/

/**
 * Um SmartSpeaker é um SmartDevice que além de ligar e desligar permite também
 * reproduzir som.
 * Consegue ligar-se a um canal (por simplificação uma rádio online) e permite
 * a regulação do seu nível de volume.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SmartSpeaker extends SmartDevice {
    public static final int MAX = 20; //volume máximo
    
    private int volume;
    private String channel;


    /**
     * Constructor for objects of class SmartSpeaker
     */
    public SmartSpeaker() {
        super();
        // initialise instance variables
        this.channel = "";
        this.volume = 0;
    }

    public SmartSpeaker(String s) {
        super(s);
        // initialise instance variables
        this.channel = "";
        this.volume = 0;
    }

    public SmartSpeaker(String cod, boolean on, String channel, int volume) {
        super(cod, on);
        // initialise instance variables
        this.channel = channel;
        if (volume<0) this.volume=0;
        else if (volume>MAX) this.volume = MAX;
        else this.volume = volume;
    }

    public SmartSpeaker(String cod, boolean on, String channel, int volume, LocalDateTime ultimaFaturacao) {
        super(cod, on, ultimaFaturacao);
        // initialise instance variables
        this.channel = channel;
        if (volume<0) this.volume=0;
        else if (volume>MAX) this.volume = MAX;
        else this.volume = volume;
    }

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
    public double calculaConsumo(LocalDateTime data) 
    {
        if (data.isBefore(this.getUltimaFaturacao()))
            return -1;
        
        double horas = ChronoUnit.HOURS.between(this.getUltimaFaturacao(), data);
        return horas * (this.volume/5) * 0.15 ;
    }

}