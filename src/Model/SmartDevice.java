package Model;
import java.time.LocalDateTime;

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
 * A classe SmartDevice é um contactor simples.
 * Permite ligar ou desligar circuitos. 
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class SmartDevice {

    private String id;
    private boolean on;
    private LocalDateTime ultimaFaturacao;

    /**
     * Constructor for objects of class SmartDevice
     */
    public SmartDevice() {
        this.id = "";
        this.on = false;
        this.ultimaFaturacao = LocalDateTime.now();
    }

    public SmartDevice(String s) {
        this.id = s;
        this.on = false;
        this.ultimaFaturacao = LocalDateTime.now();
    }

    public SmartDevice(String s, boolean b) {
        this.id = s;
        this.on = b;
        this.ultimaFaturacao = LocalDateTime.now();
    }

    public SmartDevice(String s, boolean b, LocalDateTime ultimaFaturacao) {
        this.id = s;
        this.on = b;
        this.ultimaFaturacao = ultimaFaturacao;
    }

    public SmartDevice(SmartDevice sd)
    {
        this.id = sd.getID();
        this.on = sd.getOn();
        this.ultimaFaturacao = sd.getUltimaFaturacao();
    }

    public void turnOn() {
        this.on = true;
    }
    
    public void turnOff() {
        this.on = false;
    }

    public abstract double calculaConsumo (LocalDateTime data);

    // public abstract SmartDevice clone ();
    // {
    //     return new SmartDevice(this);
    // }

    public LocalDateTime getUltimaFaturacao () {return this.ultimaFaturacao;}
    
    public boolean getOn() {return this.on;}
    
    public void setOn(boolean b) {this.on=b;}
    
    public String getID() {return this.id;}

    public abstract String toString();
    // {
        // return "SmartDevice{" + "id='" + id + ", on=" + on + "}";
    // }

}
