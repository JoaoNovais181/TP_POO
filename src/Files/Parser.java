package Files;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Model.*;

public class Parser implements Serializable
{

    public Parser ()
    {

    }
    
    public GesModel parse (String filename) throws IOException, ParserException
    {
        List<String> lines = lerFicheiro(filename);
        GesModel g = new GesModel();
        Random r = new Random();
        CasaInteligente casaAtual = null;
        String divisaoAtual = null;
        int idSD = 1;
        
        for (String line : lines)
        {
            String[] tokens = line.split(":");

            if (tokens[0].equals("Fornecedor"))
            {
                int tipo = r.nextInt(4);
                double valorBase;
                do
                {
                    valorBase = r.nextDouble();
                } while(valorBase<=0);
                double imposto;
                do
                {
                    imposto = r.nextDouble();
                } while(imposto<=0);
                Fornecedor f = null;
                switch (tipo)
                {
                    case 0: f = new FornecedorTipo1(tokens[1], valorBase, imposto);
                            break;
                    case 1: f = new FornecedorTipo2(tokens[1], valorBase, imposto);
                            break;
                    case 2: f = new FornecedorTipo3(tokens[1], valorBase, imposto);
                            break;
                    case 3: f = new FornecedorTipo4(tokens[1], valorBase, imposto);
                            break;
                    case 4: f = new FornecedorTipo5(tokens[1], valorBase, imposto);
                            break;
                    default:
                            break;
                }
                g.addFornecedor(f);
            }
            else if (tokens[0].equals("Casa"))
            {
                String[] casaTokens = tokens[1].split(",");
                if (casaTokens.length != 3)
                    throw new ParserException("Número de argumentos para casa diferentes de 3!");
                Fornecedor f = g.getFornecedor(casaTokens[2]);
                CasaInteligente casa = new CasaInteligente(casaTokens[0], casaTokens[1], f);
                g.addCasa(casa);
                casaAtual = casa;
            }   
            else if (tokens[0].equals("Divisao"))
            {
                try
                {
                    casaAtual.addRoom(tokens[1]);
                    divisaoAtual = tokens[1];
                }
                catch (NullPointerException e)
                {
                    throw new ParserException("Tentou adicionar divisao sem ter adicionado casa antes!");
                }
            }
            else if (tokens[0].equals("SmartBulb"))
            {
                if (casaAtual != null)
                {
                    if (divisaoAtual != null)
                    {
                        String[] sbTokens = tokens[1].split(",");

                        if (sbTokens.length != 3)
                            throw new ParserException("Numero de argumentos para criacao de SmartBulb diferente de 3!");

                        int cor;
                        if (sbTokens[0].equals("Neutral"))
                            cor = SmartBulb.NEUTRAL;
                        else if (sbTokens[0].equals("Warm"))
                            cor = SmartBulb.WARM;
                        else if (sbTokens[0].equals("Cold"))
                            cor = SmartBulb.COLD;
                        else
                            throw new ParserException("Cor invalida na criação da SmartBulb!");
                        int dimensao;
                        double consumoEnergeticoHora;
                        try
                        {
                            dimensao = Integer.parseInt(sbTokens[1]);
                            consumoEnergeticoHora = Double.parseDouble(sbTokens[2]);
                        }
                        catch (NumberFormatException nfe)
                        {
                            throw new ParserException("Erro ao converter campo para numero: " + nfe.getMessage());
                        }

                        boolean on = (r.nextDouble() > 0.5) ?true : false;
                        String name = new String("SmartDevice" + idSD++);

                        SmartBulb sb = new SmartBulb(name, on, consumoEnergeticoHora, cor, dimensao);
                        g.addSmartDevice(sb, casaAtual.getNIFproprietario(), divisaoAtual);
                    }
                    else
                        throw new ParserException("Tentou adicionar SmartBulb sem ter adicionado um quarto antes!");
                }
                else
                    throw new ParserException("Tentou adicionar SmartBulb sem ter adicionado casa antes!");
            }
            else if (tokens[0].equals("SmartSpeaker"))
            {
                if (casaAtual != null)
                {
                    if (divisaoAtual!= null)
                    {
                        String[] ssTokens = tokens[1].split(",");

                        if (ssTokens.length != 4)
                            throw new ParserException("Numero de argumentos para criacao de SmartSpeaker diferente de 4!");

                        int volume;
                        double consumoEnergeticoHora;
                        try
                        {
                            volume = Integer.parseInt(ssTokens[0]);
                            consumoEnergeticoHora = Double.parseDouble(ssTokens[3]);
                        }
                        catch (NumberFormatException nfe)
                        {
                            throw new ParserException("Erro ao converter campo para número: " + nfe.getMessage());
                        }
                        
                        boolean on = (r.nextDouble() > 0.5) ?true : false;
                        String name = new String("SmartDevice" + idSD++);

                        SmartSpeaker ss = new SmartSpeaker(name, on, consumoEnergeticoHora, ssTokens[1], ssTokens[2], volume);
                        g.addSmartDevice(ss, casaAtual.getNIFproprietario(), divisaoAtual);
                    }
                    else
                        throw new ParserException("Tentou adicionar SmartBulb sem ter adicionado um quarto antes!");
                }
                else
                    throw new ParserException("Tentou adicionar SmartBulb sem ter adicionado um quarto antes!");

            }
            else if (tokens[0].equals("SmartCamera"))
            {
                if (casaAtual != null)
                {
                    if (divisaoAtual!= null)
                    {
                        String[] scTokens = tokens[1].split(",");

                        if (scTokens.length != 3)
                            throw new ParserException("Numero de argumentos para criacao de SmartCamera diferente de 3!");

                        int resX, resY;
                        String[] res = scTokens[0].split("[( x )]");

                        if (res.length != 3)
                            throw new ParserException("Campo resolucao com formato errado: " + scTokens[0]);
                        
                        int tamanhoFicheiro;
                        double consumoEnergeticoHora;

                        try
                        {
                            resX = Integer.parseInt(res[1]);
                            resY = Integer.parseInt(res[2]);
                            tamanhoFicheiro = Integer.parseInt(scTokens[1]);
                            consumoEnergeticoHora = Double.parseDouble(scTokens[2]);
                        }
                        catch (NumberFormatException nfe)
                        {
                            throw new ParserException("Erro ao converter campo para número: " + nfe.getMessage());
                        }

                        boolean on = (r.nextDouble() > 0.5) ?true : false;
                        String name = new String("SmartDevice" + idSD++);

                        SmartCamera sc = new SmartCamera(name, on, consumoEnergeticoHora, resX, resY, tamanhoFicheiro);
                        g.addSmartDevice(sc, casaAtual.getNIFproprietario(), divisaoAtual);
                    }
                    else
                        throw new ParserException("Tentou adicionar SmartBulb sem ter adicionado um quarto antes!");
                }
                else
                    throw new ParserException("Tentou adicionar SmartBulb sem ter adicionado um quarto antes!");
            }
            else
                throw new ParserException("Erro de input: token nao reconhecida em " + tokens[0]);
        }

        return g;
    }

    private List<String> lerFicheiro (String filename) throws IOException
    {
        List<String> lines = new ArrayList<String>();
        try
        {
            lines = Files.readAllLines(Paths.get(filename));
        }
        catch (IOException e)
        {
            throw new IOException(e.getMessage() + "Erro a ler ficheiro");
        }
        return lines;
    }
}