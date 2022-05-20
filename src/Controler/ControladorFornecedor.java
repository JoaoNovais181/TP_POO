package Controler;

import Model.GesModel;

import java.io.Serializable;
import java.util.List;

import Model.Fornecedor;
import Model.FornecedorTipo1;
import Model.FornecedorTipo2;
import Model.FornecedorTipo3;
import Model.FornecedorTipo4;
import Model.FornecedorTipo5;
import View.Apresentacao;

public class ControladorFornecedor implements Serializable
{
    private Input i;

    public ControladorFornecedor ()
    {
        this.i = new Input();
    }

    public String lerFornecedor (GesModel g, Apresentacao a)
    {
        List<String> l = g.getNomeFornecedores();
        if (l.size() == 0)
        {
            a.printMessageWLineAbove("Não há fornecedores!");
            return this.criarFornecedor(g,a);
        }
        a.printMenuSelecaoFornecedor(l);
        int op = (int)this.i.lerDouble(a, "Selecao: ", 0, g.getNumFornecedores());
        if (op>0) return l.get(op-1);
        return this.criarFornecedor(g, a);
    }

    private boolean verificaFornecedor (GesModel g, Apresentacao a, String nome)
    {
        boolean r = g.existeFornecedor(nome);
        if (r)
            a.printMessageWLineAbove("Já existe um Fornecedor com esse nome.");
        return r;
    }

    public String criarFornecedor(GesModel g, Apresentacao a)
    {
        a.printMessageWLineAbove("Criação de Fornecedor\n");
        String nome;
        do
        {
            nome = this.i.lerString(a, "Introduza o nome do Fornecedor: ");
        } while(this.verificaFornecedor(g, a, nome));

        double valorBase = this.i.lerDouble(a, "Introduza o valor base do custo diário do kwh de energia: ", 0);
        double imposto = this.i.lerDouble(a, "Introduza o valor do fator multiplicativo do imposto: ", 0);
        int tipoFornecedor = (int) this.i.lerDouble(a, "Qual tipo de fornecedor deseja? (1,2,3,4 ou 5)", 1, 5);

        Fornecedor f = null;
        switch(tipoFornecedor)
        {
            case 1: f = new FornecedorTipo1(nome, valorBase, imposto);
                    break;
            case 2: f = new FornecedorTipo2(nome,valorBase, imposto);
                    break;
            case 3: f = new FornecedorTipo3(nome,valorBase, imposto);
                    break;
            case 4: f = new FornecedorTipo4(nome,valorBase, imposto);
                    break;
            case 5: f = new FornecedorTipo5(nome,valorBase, imposto);
                    break;
            default: break;
        }
        g.addFornecedor(f);
        a.printMessageWLineUnder("Fornecedor Criado!");
        return nome;
    }

}