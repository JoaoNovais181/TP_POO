package Controler;

import Model.GesModel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import Model.Formula;
import Model.Fornecedor;
import View.Apresentacao;

public class ControladorFornecedor implements Serializable
{
    private Input i;

    public ControladorFornecedor ()
    {
        this.i = new Input();
    }

    private boolean VerificaFormula (String formula, Apresentacao a)
    {
        String[] tokens = formula.split(" ");
        boolean foundVB = false, foundI = false, foundC = false;
        for (String token : tokens)
        {
            if (token.contains("valorbase")) foundVB = true;
            if (token.contains("imposto")) foundI = true;
            if (token.contains("consumototal")) foundC = true;
        }

        if (!foundVB) { a.printMessageWLineAbove("Nao introduziu valorbase na formula."); return false; }
        if (!foundI) { a.printMessageWLineAbove("Nao introduziu imposto na formula."); return false; }
        if (!foundC) { a.printMessageWLineAbove("Nao introduziu consumototal na formula."); return false; }

        return true;
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

        HashMap<Integer,Formula> formulas = this.controladorCriacaoFormula(a);

        Fornecedor f = new Fornecedor(nome, valorBase, imposto, formulas);
        g.addFornecedor(f);
        a.printMessageWLineUnder("Fornecedor Criado!");
        return nome;
    }

    private boolean verificaCondicao (int c, int lant, int latu, Apresentacao a)
    {
        if (latu<0) { a.printMessage("Limite de dispositivos deve ser >0."); return false; }
        if (latu==0 && c<1) { a.printMessage("Deve ter pelo menos 1 condicao"); return false; }
        if (latu!=0 && latu <= lant) { a.printMessage("Limite deve ser maior que o anterior"); return false; }
        return true;
    }

    private HashMap<Integer, Formula> controladorCriacaoFormula (Apresentacao a)
    {
        HashMap<Integer, Formula> formulas = new HashMap<Integer, Formula>();
        a.printMenuCriacaoFormula();
        int opc = (int)this.i.lerDouble(a, "Introduza uma opcao: ", 1, 2);

        if (opc==1)
        {
            String formula;
            do
            {
                formula = this.i.lerString(a, "Introduza a formula para o calculo da faturacao: ");
            } while(!this.VerificaFormula(formula,a));
            Formula form = new Formula(formula);
            formulas.put((Integer)0, form);
            return formulas;
        }

        int c = 0, lant = -1, latu;

        do 
        {
            do 
            {
                latu = (int)this.i.lerDouble(a, ("Introduza o numero de dispositivos para a " + (c+1) + "-ésima condicao ou 0 para terminar condicoes."), 0);
            } while (!this.verificaCondicao(c, lant, latu, a));
            
            String formula;
            do
            {
                formula = this.i.lerString(a, "Introduza a formula para o calculo da faturacao: ");
            } while(!this.VerificaFormula(formula,a));
            Formula form = new Formula(formula);

            if (latu==0)
                formulas.put((Integer)(lant+1), form);
            else
                formulas.put((Integer)latu, form);
            lant = latu;
            c++;

        } while (latu!=0);

        return formulas;
    }
}