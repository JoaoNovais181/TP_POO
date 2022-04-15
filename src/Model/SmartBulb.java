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
 * Uma SmartBulb é uma lâmpada inteligente que além de ligar e desligar (já que
 * é subclasse de SmartDevice) também permite escolher a intensidade da iluminação 
 * (a cor da mesma).
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SmartBulb extends SmartDevice {
    public static final int WARM = 2;
    public static final int NEUTRAL = 1;
    public static final int COLD = 0;
    
    private int tone;

    /**
     * Constructor for objects of class SmartBulb
     */
    public SmartBulb() {
        super(); // Chamar o construtor de SmartDevice por omissao
        // initialise instance variables
        this.tone = NEUTRAL;
    }

    public SmartBulb(String id, boolean on, int tone) {
        super(id, true);
        // initialise instance variables
        this.tone = tone;
    }

    public SmartBulb(String id, boolean on, int tone, LocalDateTime ultimaFaturacao) {
        super(id, true, ultimaFaturacao);
        // initialise instance variables
        this.tone = tone;
    }

    public SmartBulb(String id) {
        super(id, false); // Chamar o construtor de SmartDevice parametrizado com id
        // initialise instance variables
        this.tone = NEUTRAL;
    }

    public void setTone(int t) {
        if (t>WARM) this.tone = WARM;
        else if (t<COLD) this.tone = COLD;
        else this.tone = t;
    }

    public int getTone() {
        return this.tone;
    }

    public String toString ()
    {
        return "SmartBulb{id: " + this.getID() + ", on=" + this.getOn() + ", tone=" + ((this.tone==1) ?"Neutral" :(this.tone > 1) ?"WARM" : "COLD") + "}";
    }

    @Override
    public double calculaConsumo (LocalDateTime data)
    {
        if (data.isBefore(this.getUltimaFaturacao()))
            return -1;
        
        double horas = ChronoUnit.HOURS.between(this.getUltimaFaturacao(), data);
        return (1.1 - (this.tone+1)*0.3) * horas;
    }
}
