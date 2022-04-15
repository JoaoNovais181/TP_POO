package Controler;

import Model.GesModel;
import Model.SmartBulb;
import Model.SmartSpeaker;
import Model.SmartCamera;
import View.Apresentacao;

public class ControladorSmartDevice {

    private Input i;

    public ControladorSmartDevice()
    {
        this.i = new Input();
    }

    public int lerCor (Apresentacao a)
    {
        double n = -1;
        
        do{
            a.printMenuCor();
            n = i.lerDouble();
        } while (n < 0 || n > 2);

        return (int)n;
    }

    public void criarSmartBulb (GesModel g, Apresentacao a, String NIFproprietario, String location)
    {
        String id = null;
        do
        {
            id = i.lerString(a, "Introduza o ID da SmartBulb");
        } while (g.existeDevice(id, NIFproprietario));

        boolean on = i.lerSimNao(a, "A SmartBulb está ligada?");
        int cor = this.lerCor(a);
        
        SmartBulb sb = new SmartBulb(id, on, cor, g.getHoraAtual());
        g.addSmartDevice(sb, NIFproprietario, location);
    }

    public void criarSmartSpeaker (GesModel g, Apresentacao a, String NIFproprietario, String location)
    {
        String id = null;
        do
        {
            id = i.lerString(a, "Introduza o ID da SmartBulb");
        } while (g.existeDevice(id, NIFproprietario));

        boolean on = i.lerSimNao(a, "A SmartBulb está ligada?");

        String channel = i.lerString(a, "Introduza o canal da coluna: ");
        int volume = (int)i.lerDouble(a, "Introduza o volume: ", 0, 20);
        
        SmartSpeaker ss = new SmartSpeaker(id, on, channel, volume, g.getHoraAtual());
        g.addSmartDevice(ss, NIFproprietario, location);
    }

    public void criarSmartCamera (GesModel g, Apresentacao a, String NIFproprietario, String location)
    {
        String id = null;
        do
        {
            id = i.lerString(a, "Introduza o ID da SmartBulb");
        } while (g.existeDevice(id, NIFproprietario));
    
        boolean on = i.lerSimNao(a, "A SmartBulb está ligada?");
        
        int resX = (int)i.lerDouble(), resY = (int)i.lerDouble();
        double tamFicheiro = i.lerDouble();

        SmartCamera sc = new SmartCamera(id, on, g.getHoraAtual(), resX, resY, tamFicheiro);
        g.addSmartDevice(sc, NIFproprietario, location);
    }

}

