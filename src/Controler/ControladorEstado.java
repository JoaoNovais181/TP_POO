package Controler;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Model.GesModel;
import View.Apresentacao;
import Files.Load_Save;
import Files.Parser;
import Files.ParserException;

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
        g = g2;
        return g2;
    }

    public GesModel CarregarEstadoTXT (GesModel g, Apresentacao a)
    {
        String s;
        do
        {
            s  = this.i.lerString(a, "Introduza o nome do ficheiro .txt a carregar: ");
        } while(!s.contains(".txt"));

        Parser p = new Parser();
        GesModel g2;
        try
        {
            g2 = p.parse(s);
        }
        catch (ParserException | IOException e)
        {
            a.printMessage("Erro no parse: " + e.getMessage());
            e.printStackTrace();
            g2 = g;
        }
        g = g2;
        return g2;
    }

    private boolean verificaData (String data)
    {
        String[] tokens = data.split("[- :]");
        if (tokens.length!=5) return false;
        for (int i=0 ; i<tokens.length ; i++)
        {
            try
            {
                Integer.parseInt(tokens[i]);
            }
            catch (NumberFormatException e)
            {
                return false;
            }
        }
        return true;
    }

    public LocalDateTime lerData (Apresentacao a)
    {
        String s;
        do{
            s = this.i.lerString(a, "Introduza uma data no formato dd-MM-yyyy HH:mm: ");
        } while(!this.verificaData(s));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime data = LocalDateTime.parse(s, formatter);
        return data;
    }
}