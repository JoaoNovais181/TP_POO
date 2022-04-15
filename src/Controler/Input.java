package Controler;

import java.util.Scanner;

import View.Apresentacao;

public class Input {

    Scanner sc;

    public Input ()
    {
        this.sc = new Scanner(System.in);
    }

    public String lerString(Apresentacao a, String message)
    {
        String s = null;
        do
        {
            a.printMessage(message);
            s = sc.nextLine();
        } while (s==null || s.isBlank());
        return s;
    }

    public double lerDouble (Apresentacao a, String message, double min, double max)
    {
        double n = -1;

        do{
            a.printMessage(message);
            try {
                String line = this.sc.nextLine();
                n = Double.parseDouble(line);
            } catch (NumberFormatException nfe) {
                a.printMessage(nfe.getMessage());
                n = -1;
            }
        } while (n < min || n > max);

        return n;
    }

    public double lerDouble (Apresentacao a, String message, double min)
    {
        double n = -1;

        do{
            a.printMessage(message);
            try {
                String line = this.sc.nextLine();
                n = Double.parseDouble(line);
            } catch (NumberFormatException nfe) {
                a.printMessage(nfe.getMessage());
                n = -1;
            }
        } while (n < min);

        return n;
    }

    public double lerDouble ()
    {
        double n = -1;

        do{
            try {
                String line = this.sc.nextLine();
                n = Double.parseDouble(line);
            } catch (NumberFormatException nfe) {
                n = -1;
            }
        } while (n<0);

        return n;
    }

    public boolean lerSimNao (Apresentacao a, String message)
    {
        String line;

        do
        {
            a.printMessage(message);
            line = this.sc.nextLine();
        } while (!line.toUpperCase().equals("S") && !line.toUpperCase().equals("N"));

        return line.toUpperCase().equals("S");
    }
}