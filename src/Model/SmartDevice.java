package Model;
import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class SmartDevice implements Serializable  
{

    private String id;
    private boolean on;
    // private LocalDateTime ultimaFaturacao;

    public SmartDevice() {
        this.id = "";
        this.on = false;
        // this.ultimaFaturacao = LocalDateTime.now();
    }

    public SmartDevice(String s) {
        this.id = s;
        this.on = false;
        // this.ultimaFaturacao = LocalDateTime.now();
    }

    public SmartDevice(String s, boolean b) {
        this.id = s;
        this.on = b;
        // this.ultimaFaturacao = LocalDateTime.now();
    }

    // public SmartDevice(String s, boolean b, LocalDateTime ultimaFaturacao) {
        // this.id = s;
        // this.on = b;
        // this.ultimaFaturacao = ultimaFaturacao;
    // }

    public SmartDevice(SmartDevice sd)
    {
        this.id = sd.getID();
        this.on = sd.getOn();
        // this.ultimaFaturacao = sd.getUltimaFaturacao();
    }

    public void turnOn() {
        this.on = true;
    }
    
    public void turnOff() {
        this.on = false;
    }

    public abstract double calculaConsumo (LocalDateTime atual, LocalDateTime nova);


    // public LocalDateTime getUltimaFaturacao () {return this.ultimaFaturacao;}
    
    public boolean getOn() {return this.on;}
    
    public void setOn(boolean b) {this.on=b;}
    
    public String getID() {return this.id;}

    public abstract String toString();

    public abstract SmartDevice clone();
}
