package Controler;
import java.time.LocalDateTime;
import java.util.HashMap;

import Model.*;
import View.Apresentacao;

public class Controlador {

    private ControladorCasa ca;
    private ControladorSmartDevice cs;
    private ControladorFornecedor cf;
    private Input i;

    public Controlador ()
    {
        this.ca = new ControladorCasa();
        this.cs = new ControladorSmartDevice();
        this.cf = new ControladorFornecedor();
        this.i = new Input();
    }

    public void controlador (GesModel g, Apresentacao a)
    {
        int op;
        a.printMainMenu();
        op = (int)i.lerDouble(a, "Introduza a opcao", 1, 1);
        if (op == 1)
        {
            this.controladorCriacao(g, a);

        }
    }

    public void controladorCriacao (GesModel g, Apresentacao a)
    {
        a.printMenuCriacao();
        
    }

    public String controladorCriacaoCasa (GesModel g, Apresentacao a)
    {
        Fornecedor f = g.getFornecedor(this.cf.lerFornecedor(g, a));
        return this.ca.criarCasa(g, a, f);
    }

    public void controladorCriacaoSmartDevice (GesModel g, Apresentacao a)
    {
        String nifCasa = this.ca.lerCasa(g, a);
        if (nifCasa == null)
        {
            nifCasa = this.controladorCriacaoCasa(g, a);
        }
        a.printMenuSelecaoTipoSmartDevice();
        int type = (int)this.i.lerDouble(a, "Selecione uma opcao: ", 0, 2);
        switch (type)
        {
            case 0:
                this.cs.criarSmartBulb(g, a, nifCasa, location);
        }
    }
}