package Controler;

import java.io.IOException;
import java.io.Serializable;
import Model.GesModel;
import View.Apresentacao;
import Files.Load_Save;


public class ControladorEstado implements Serializable
{
    private Input i;

    public ControladorEstado ()
    {
        this.i = new Input();
    }

    public void SalvarEstado (GesModel g, Apresentacao a)
    {
        String s;
        do
        {
            s = this.i.lerString(a, "Introduza o nome do ficheiro com a extensao .dat (por exemplo: estado.dat): ");
        } while(s.equals("default.dat"));
        Load_Save l = new Load_Save("../input_files/"+s);
        try 
        {
            l.save(g);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GesModel CarregarEstado (GesModel g, Apresentacao a)
    {
        String s  = this.i.lerString(a, "Introduza o nome do ficheiro .dat a carregar: ");
        Load_Save l = new Load_Save("../input_files/"+s);
        GesModel g2 = null;
        try {
            g2 = (GesModel)l.load();
        } catch (ClassNotFoundException | IOException e) {
            a.printMessage("Ficheiro nao existente!");
            e.printStackTrace();
            g2=g;
        }
        return g2;
    }
}