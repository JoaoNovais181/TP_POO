package Controler;

import java.io.Serializable;

import Model.GesModel;
import Model.SmartBulb;
import Model.SmartSpeaker;
import Model.SmartCamera;
import View.Apresentacao;

public class ControladorSmartDevice implements Serializable
{

    private Input i;

    public ControladorSmartDevice()
    {
        this.i = new Input();
    }

    public void controladorCriacaoSmartDevice (GesModel g, Apresentacao a, ControladorCasa ca, ControladorFornecedor cf)
    {
        String nifCasa = ca.lerCasa(g, a);
        if (nifCasa == null)
        {
            nifCasa = ca.controladorCriacaoCasa(g, a, cf);
        }
        String location = ca.lerLocation(g, a, nifCasa);
        a.printMenuSelecaoTipoSmartDevice();
        int type = (int)this.i.lerDouble(a, "Selecione uma opcao: ", 0, 3);
        switch (type)
        {
            case 0:
                return;
            case 1:
                this.criarSmartBulb(g, a, nifCasa, location);
                break;
            case 2:
                this.criarSmartSpeaker(g, a, nifCasa, location);
                break;
            case 3:
                this.criarSmartCamera(g, a, nifCasa, location);
                break;
            default:
                break;
        }
        a.printMessage("Smart Device criado!");
    }

    public int lerCor (Apresentacao a)
    {
        double n = -1;
        
        do{
            a.printMenuCor();
            n = i.lerDouble();
        } while (n < 1 || n > 3);

        return (int)n;
    }

    public void criarSmartBulb (GesModel g, Apresentacao a, String NIFproprietario, String location)
    {
        String id = null;
        do
        {
            id = i.lerString(a, "Introduza o ID da SmartBulb:");
        } while (g.existeDevice(id));

        boolean on = i.lerSimNao(a, "A SmartBulb está ligada?(S/N)");
        int cor = this.lerCor(a);
        
        SmartBulb sb = new SmartBulb(id, on, cor);
        g.addSmartDevice(sb, NIFproprietario, location);
    }

    public void criarSmartSpeaker (GesModel g, Apresentacao a, String NIFproprietario, String location)
    {
        String id = null;
        do
        {
            id = i.lerString(a, "Introduza o ID do SmartSpeaker:");
        } while (g.existeDevice(id));

        boolean on = i.lerSimNao(a, "O SmartSpeaker está ligado?(S/N)");

        String channel = i.lerString(a, "Introduza o canal da coluna: ");
        int volume = (int)i.lerDouble(a, "Introduza o volume (0-20): ", 0, 20);
        
        SmartSpeaker ss = new SmartSpeaker(id, on, channel, volume);
        g.addSmartDevice(ss, NIFproprietario, location);
    }

    public void criarSmartCamera (GesModel g, Apresentacao a, String NIFproprietario, String location)
    {
        String id = null;
        do
        {
            id = i.lerString(a, "Introduza o ID da SmartCamera:");
        } while (g.existeDevice(id));
    
        boolean on = i.lerSimNao(a, "A SmartCamera está ligada?(S/N)");
        
        int resX = (int)i.lerDouble(a, "Introduza a resolução horizontal:", 0), resY = (int)i.lerDouble(a, "Introduza a resolução Vertical:", 0);
        double tamFicheiro = i.lerDouble(a, "Introduza o tamanho do ficheiro: ", 0);

        SmartCamera sc = new SmartCamera(id, on, resX, resY, tamFicheiro);
        g.addSmartDevice(sc, NIFproprietario, location);
    }

}

