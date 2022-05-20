
import View.Apresentacao;
import Model.*;

import java.io.IOException;
import java.io.Serializable;

// import Files.Parse;
import Controler.Controlador;
import Files.Load_Save;
import Files.Parser;

public class App implements Serializable
{

    
    public static void main(String[] args) 
    {
        Apresentacao a = new Apresentacao();
        Controlador c = new Controlador();
        GesModel g;
        Parser p = new Parser();
        Load_Save l = new Load_Save("../input_files/default.dat");
        try {
            // g=(GesModel)l.load();
            g = p.parse("../input_files/log.txt");
            c.controlador(g, a);
        } catch (Exception e) {
            a.printMessageWLineUnder("Erro ao abrir modelo default. A criar novo..\n" + e.getMessage());
            g=new GesModel();
            c.controlador(g, a);
            // try {
            //     l.resetFileInfo();
            //     l.save(g);
            // } catch (IOException e1) {
            //     e1.printStackTrace();
            // }
        }
    }
}