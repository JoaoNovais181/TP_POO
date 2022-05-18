package Files;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import Model.*;

public class Parser implements Serializable
{
    
    public void parse (GesModel g, String filename)
    {
        List<String> lines = lerFicheiro(filename);
        
    }

    private List<String> lerFicheiro (String filename)
    {
        List<String> lines = new ArrayList<String>();
        try
        {
            lines = Files.readAllLines(Paths.get(filename));
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage() + "Erro a ler ficheiro");
        }
        return lines;
    }
}