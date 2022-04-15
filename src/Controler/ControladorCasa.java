package Controler;

import java.util.ArrayList;
import java.util.List;

import Model.CasaInteligente;
import Model.Fornecedor;
import Model.GesModel;
import View.Apresentacao;

public class ControladorCasa {

    private Input i;

    public ControladorCasa ()
    {
        this.i = new Input();
    }

    public String lerCasa (GesModel g, Apresentacao a)
    {
        List<String> l = g.getNIFCasas();
        a.printMenuSelecaoCasa(l);
        int op = (int)this.i.lerDouble(a, "Introduza uma opcao", 0);
        if (op>0) return l.get(op);
        return null;
    }

    public void listarCasas (GesModel g, Apresentacao a)
    {
        ArrayList<CasaInteligente> l = (ArrayList<CasaInteligente>)g.getCasas();
        for (int i=0 ; i<l.size() ; i++)
        {
            System.out.println(" " + (i+1) + " ) " + l.get(i).toString());
        }
        System.out.println(" 0 ) Criar nova");
    }

    public void listarSalas (GesModel g, Apresentacao a, String nif)
    {
        CasaInteligente c = g.getCasa(nif);
        int i=1;
        for (String sala : c.getLocations().keySet())
        {
            System.out.println(" " + (i++) + " ) " + sala);
        }
        System.out.println(" 0 ) Criar nova");
    }

    public String criarCasa (GesModel g, Apresentacao a, Fornecedor f)
    {
        String nome = i.lerString(a, "Introduza o nome do dono da casa: ");
        String nif;
        do 
        {
            nif = i.lerString(a, "Introduza o NIF do dono da casa: ");
        } while (g.existeCasaInteligente(nif));

        CasaInteligente c = new CasaInteligente(nome, nif, f);
        g.addCasa(c);
        return nif;
    }
}

