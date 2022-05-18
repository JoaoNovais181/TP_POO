package Model;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class SmartCamera extends SmartDevice 
{
    private int resX, resY;
    private double tamFicheiro;

    public SmartCamera ()
    {
        super();
        this.resX = 0;
        this.resY = 0;
        this.tamFicheiro = 0;
    }

    public SmartCamera (String id, boolean on, int resX, int resY, double tamFicheiro)
    {
        super(id, on);
        this.resX = resX;
        this.resY = resY;
        this.tamFicheiro = tamFicheiro;
    }

    public SmartCamera (SmartCamera camera)
    {
        super(camera.getID(), camera.getOn());
        this.resX = camera.getResX();
        this.resY = camera.getResY();
        this.tamFicheiro = camera.getTamFicheiro();
    }

    // public SmartCamera (String id, boolean on, LocalDateTime ultimaFaturacao, int resX, int resY, double tamFicheiro)
    // {
        // super(id, on, ultimaFaturacao);
        // this.resX = resX;
        // this.resY = resY;
        // this.tamFicheiro = tamFicheiro;
    // }


    public int getResX ()
    {
        return this.resX;
    }

    public int getResY()
    {
        return this.resY;
    }

    public double getTamFicheiro ()
    {
        return this.tamFicheiro;
    }

    @Override
    public double calculaConsumo(LocalDateTime atual, LocalDateTime nova) {
        if (nova.isBefore(atual))
            return -1;
        
        double horas = ChronoUnit.HOURS.between(atual, nova);
        return horas * this.tamFicheiro*8/1000000 * (this.resX * this.resY)/1000000 / 1000;
    }

    @Override
    public String toString() {
        return "SmartCamera{id: " + this.getID() + ", on=" + this.getOn() + ", Resolution: " + this.resX + "x" + this.resY + ", File Size: " + this.tamFicheiro + "}"; 
    }
    
    @Override
    public SmartCamera  clone() 
    {
        return new SmartCamera(this);
    }
}