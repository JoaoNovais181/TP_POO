
import View.Apresentacao;
import Model.*;

import java.io.IOException;
import java.io.Serializable;

// import Files.Parse;
import Controler.Controlador;
import Files.Load_Save;

public class App implements Serializable
{

    
    public static void main(String[] args) 
    {
        Apresentacao a = new Apresentacao();
        Controlador c = new Controlador();
        GesModel g;
        Load_Save l = new Load_Save("../input_files/default.dat");
            try {
                g=(GesModel)l.load();
                c.controlador(g, a);
            } catch (ClassNotFoundException | IOException e) {
                a.printMessageWLineUnder("Erro ao abrir modelo default. A criar novo..");
                g=new GesModel();
                c.controlador(g, a);
            }
    }
}