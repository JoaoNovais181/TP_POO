package Model;

import java.io.Serializable;
import java.util.Map;

public class Formula implements Serializable
{
    private int pos=-1, ch;
    private String formula;

    public Formula(String formula)
    {
        this.pos = -1;
        this.ch = -1;
        this.formula = formula;
    }

    private void avancaChar ()
    {
        this.ch = (++pos<this.formula.length()) ?this.formula.charAt(pos) :-1;
    }

    private boolean come (int charAComer)
    {
        while (this.ch == ' ') this.avancaChar();
        if (this.ch == charAComer)
        {
            this.avancaChar();
            return true;
        } 
        return false;
    }

    public double calcula (Map<String, Double> vars)
    {
        this.avancaChar();
        double x = this.calculaAdSub(vars);
        // if (pos < this.formula.length()) throw new RuntimeException("Unexpected: " + (char)ch);
        return x;
    }

    private double calculaAdSub (Map<String, Double> vars)
    {
        double x = this.calculaMultDiv(vars);
        while (true)
        {
            if (this.come('+')) x += this.calculaMultDiv(vars);
            else if (this.come('-')) x -= this.calculaMultDiv(vars);
            else return x;
        }
    }

    private double calculaMultDiv (Map<String, Double> vars)
    {
        double x = this.calculaValsFuncExpPar(vars);
        while (true)
        {
            if (this.come('*')) x *= this.calculaValsFuncExpPar(vars);
            else if (this.come('/')) x /= this.calculaValsFuncExpPar(vars);
            else if (this.come('^')) x /= Math.pow(x, this.calculaValsFuncExpPar(vars));
            else return x;
        }
    }

    private double calculaValsFuncExpPar (Map<String, Double> vars)
    {
        if (this.come('+')) return this.calculaValsFuncExpPar(vars);
        if (this.come('-')) return -this.calculaValsFuncExpPar(vars);
        
        double x;
        int posIn = this.pos;
        if (this.come('('))
        {
            x = this.calculaAdSub(vars);
            if (!this.come(')')) throw new RuntimeException("Falta ')'");
        }
        else if ((this.ch >= '0' && this.ch <= '9') || this.ch == '.')
        {
            while ((this.ch >= '0' && this.ch <= '9') || this.ch == '.')  this.avancaChar();
            x = Double.parseDouble(this.formula.substring(posIn, this.pos));
        }
        else if (this.ch >= 'a' && this.ch <= 'z') 
        {
            while (this.ch >= 'a' && this.ch <= 'z') this.avancaChar();
            String var = this.formula.substring(posIn, this.pos);
            if (vars.containsKey(var)) x = vars.get(var);
            else throw new RuntimeException("Variavel sem valor difinido: " + var);
        } 
        else 
        {
            throw new RuntimeException("Unexpected: " + (char)ch);
        }

        return x;
    }

    public String toString()
    {
        return this.formula;
    }

}