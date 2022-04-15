package Controler;

import Model.GesModel;

import java.util.List;

import Model.Fornecedor;
import View.Apresentacao;

public class ControladorFornecedor 
{
    private Input i;

    public ControladorFornecedor ()
    {
        this.i = new Input();
    }

    public String lerFornecedor (GesModel g, Apresentacao a)
    {
        List<String> l = g.getNomeFornecedores();
        a.printMenuSelecaoFornecedor(l);
        int op = (int)this.i.lerDouble(a, "Selecao: ", 0, g.getNumFornecedores());
        if (op>0) return l.get(op-1);
        return this.criarFornecedor(g, a);
    }

    public String criarFornecedor(GesModel g, Apresentacao a)
    {
        String nome;
        do
        {
            nome = this.i.lerString(a, "Introduza o nome do Fornecedor: ");
        } while(g.existeFornecedor(nome));

        double valorBase = this.i.lerDouble(a, "Introduza o valor base do custo diário do kwh de energia: ", 0);
        double imposto = this.i.lerDouble(a, "Introduza o valor do fator multiplicativo do imposto: ", 0);

        Fornecedor f = new Fornecedor(nome, valorBase, imposto);
        g.addFornecedor(f);
        return nome;
    }
}