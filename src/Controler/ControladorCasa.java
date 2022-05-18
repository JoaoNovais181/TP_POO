package Controler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Model.CasaInteligente;
import Model.Fornecedor;
import Model.GesModel;
import View.Apresentacao;

public class ControladorCasa implements Serializable
{

    private Input i;

    public ControladorCasa ()
    {
        this.i = new Input();
    }

    public String controladorCriacaoCasa (GesModel g, Apresentacao a, ControladorFornecedor cf)
    {
        a.printMessageWLineAbove("Criação de Casa");
        Fornecedor f = g.getFornecedor(cf.lerFornecedor(g, a));
        return this.criarCasa(g, a, f);
    }

    public String lerCasa (GesModel g, Apresentacao a)
    {
        List<String> l = g.getNIFCasas();
        if (l.size()==0) 
        {
            a.printMessageWLineAbove("Não Existem casas!");
            return null;
        }
        a.printMenuSelecaoCasa(l);
        int op = (int)this.i.lerDouble(a, "Introduza uma opcao", 0);
        if (op>0) return l.get(op-1);
        return null;
    }

    private boolean verificaLocation (Apresentacao a, List<String> locations, String sala)
    {
        boolean r = locations.contains(sala);
        if (r)
            a.printMessageWLineAbove("Já existe uma divisão com esse nome.");
        return r;
    }

    public String lerLocation (GesModel g, Apresentacao a, String nif)
    {
        CasaInteligente c = g.getCasa(nif);
        List<String> l = c.getLocations().keySet().stream().collect(Collectors.toList());
        int op;
        if (l.size() == 0)
        {
            a.printMessageWLineAbove("Não tem divisões!");
            a.printMessageWLineAbove("A criar divisão!\n");
            op = 0;
        }
        else
        {
            a.printMenuSelecaoLocation(l);
            op = (int)this.i.lerDouble(a, "Introduza uma opcao:", 0);
        }
        if (op>0) return l.get(op-1);
        String loc;
        do
        {
            loc = this.i.lerString(a, "Introduza o nome da Divisao: ");
        } while(this.verificaLocation(a, l, loc));
        c.addRoom(loc);
        return loc;
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

    private boolean isNum (String str)
    {
        for (int i=0 ; i<str.length() ; i++)
            if (str.charAt(i) < '0' || str.charAt(i) > '9') return false;
        return true;
    }

    private boolean verificaNIF (GesModel g, Apresentacao a, String nif)
    {
        boolean r = g.existeCasaInteligente(nif);
        if (r)
            a.printMessageWLineAbove("Já existe uma casa com esse NIF.");

        if (nif.length() != 9 || !this.isNum(nif))
        { 
            a.printMessageWLineAbove("NIF deve ser constituido por 9 digitos");
            r=true;
        }
        
        return r;
    }

    public String criarCasa (GesModel g, Apresentacao a, Fornecedor f)
    {
        String nome = i.lerString(a, "Introduza o nome do dono da casa: ");
        String nif;
        do 
        {
            nif = i.lerString(a, "Introduza o NIF do dono da casa: ");
        } while (this.verificaNIF(g,a,nif));

        CasaInteligente c = new CasaInteligente(nome, nif, f);
        g.addCasa(c);
        return nif;
    }
}

