package View;

import java.io.Serializable;
import java.util.List;

import Model.SmartDevice;

public class ApresentacaoSmartDevice implements Serializable
{

    private Output o;

    public ApresentacaoSmartDevice ()
    {
        this.o = new Output();
    }

    public void printIntroduzaID ()
    {
        System.out.print("Introduza ID do dispositivo: ");
    }

    public void printMenuSelecaoTipoSmartDevice ()
    {
        this.o.printMenu((new String[]{"SmartBulb", "SmartSpeaker", "SmartCamera"}), "Selecione o tipo de Smart Device", -1);
    }
    
    public void printMenuCor()
    {
        this.o.printMenu((new String[]{"Cold", "Neutral", "Warm"}), "Qual é a cor do dispositivo?", -1);
    }

    public void listarDevices (List<SmartDevice> devices)
    {
        if (devices.size()==0)
        {
            String msg = "Não há SmartDevices!";
            this.o.printLine(msg.length()+2);
            this.o.printMessage(msg);
            this.o.printLine(msg.length()+2);
            return ;
        }
        int maxSize = 60;

        this.o.printLine(maxSize);
        this.o.printMessage("Lista de SmartDevices");
        this.o.printLine(maxSize);
        for (SmartDevice device : devices)
            this.o.printMessage(device.toString());
        this.o.printLine(maxSize);
    }

    public void selecaoDevices (List<String> devices)
    {
        this.o.printList(devices, "Selecione um SmartDevice", 1);
    }
}