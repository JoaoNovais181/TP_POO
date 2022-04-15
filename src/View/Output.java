package View;

import java.util.List;

// import java.util.List;

public class Output
{

    public void clear ()
    {
        for (int i=0 ; i<10 ; i++)
            System.out.println("");
    }

    public void printLine (int width)
    {
        for (int i=0 ; i<width ; i++) 
            System.out.print("-");
        System.out.println("");
    }

    public void printMenu (String[] entrys, String title, int type)
    {
        int maxSize = title.length()+6, nEntrys = entrys.length;

        for (String entry : entrys)
            if (entry.length() +6 > maxSize) maxSize = entry.length() + 6;

        printLine(maxSize);
        System.out.println(title);
        printLine(maxSize);

        for (int i=1 ; i<nEntrys+1 ; i++)
        {
            System.out.println(" " + i + " ) " + entrys[i-1] + " ");
        }

        if (type==0)
            System.out.println(" 0 ) Sair ");
        else if (type==1)
            System.out.println(" 0 ) Voltar ");
        else if (type==2)
            System.out.println(" 0 ) Criar Novo ");
        printLine(maxSize);
    }

    public void printList (List<String> l, String message, int type)
    {
        this.printMenu((String[])l.toArray(), message, type);
    }

    public void printMessage (String message)
    {
        System.out.println(message);
    }
}