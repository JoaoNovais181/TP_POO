/*********************************************************************************/
/** DISCLAIMER: Este código foi criado e alterado durante as aulas práticas      */
/** de POO. Representa uma solução em construção, com base na matéria leccionada */ 
/** até ao momento da sua elaboração, e resulta da discussão e experimentação    */
/** durante as aulas. Como tal, não deverá ser visto como uma solução canónica,  */
/** ou mesmo acabada. É disponibilizado para auxiliar o processo de estudo.      */
/** Os alunos são encorajados a testar adequadamente o código fornecido e a      */
/** procurar soluções alternativas, à medida que forem adquirindo mais           */
/** conhecimentos de POO.                                                        */
/*********************************************************************************/

import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;


/**
 * A CasaInteligente faz a gestão dos SmartDevices que existem e dos 
 * espaços (as salas) que existem na casa.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CasaInteligente {

    private String nomeProprietario, NIFproprietario;
    private Map<String, SmartDevice> devices; // identificador -> SmartDevice
    private Map<String, List<String>> locations; // Espaço -> Lista codigo dos devices
    private Fornecedor fornecedor;

    /**
     * Constructor for objects of class CasaInteligente
     */
    public CasaInteligente() {
        // initialise instance variables
        this.nomeProprietario = "";
        this.NIFproprietario = "";
        this.devices = new HashMap<>();
        this.locations = new HashMap<>();
        this.fornecedor = null;
    }

    public CasaInteligente(String nomeProprietario, String NIFproprietario, Fornecedor fornecedor) {
        // initialise instance variables
        this.nomeProprietario = nomeProprietario;
        this.NIFproprietario = NIFproprietario;
        this.devices = new HashMap<>();
        this.locations = new HashMap<>();
        this.fornecedor = fornecedor;
        fornecedor.addCasa(this);
    }

    public CasaInteligente(CasaInteligente umaCasaInteligente)
    {
        this.nomeProprietario = umaCasaInteligente.getNomeProprietario();
        this.NIFproprietario = umaCasaInteligente.getNIFproprietario();
        this.devices = new HashMap<>();
        this.devices = (HashMap<String, SmartDevice>) umaCasaInteligente.getDevices().entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey() , entry -> entry.getValue()));
        this.locations = new HashMap<>();   
        this.locations = (HashMap<String, List<String>>) umaCasaInteligente.getLocations().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue()));
    }

    public String getNomeProprietario ()
    {
        return this.nomeProprietario;
    }

    public String getNIFproprietario ()
    {
        return this.NIFproprietario;
    }

    public HashMap<String, SmartDevice> getDevices ()
    {
        return (HashMap<String, SmartDevice>) this.devices.entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
    }

    public HashMap<String, List<String>> getLocations ()
    {
        return (HashMap<String, List<String>>) this.locations.entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
    }

    public Fornecedor getFornecedor ()
    {
        return this.fornecedor;
    }

    // public CasaInteligente clone ()
    // {
        // return new CasaInteligente(this);
    // }

    public String toString ()
    {
        return "CasaInteligente{Nome: " + this.nomeProprietario + ", NIF: " + this.NIFproprietario + "}";
    }
    

    public void mudaFornecedor (Fornecedor novoFornecedor)
    {
        this.fornecedor.removeCasa(this);
        novoFornecedor.addCasa(this);
        this.fornecedor = novoFornecedor;
    }

    
    public void setDeviceOn(String devCode) {
        this.devices.get(devCode).turnOn();
    }
    
    public boolean existsDevice(String id) 
    {
        return this.devices.keySet().stream().anyMatch(str -> str.equals(id));
    }
    
    public void addDevice(SmartDevice s) 
    {
        this.devices.put(s.getID(), s);
    }
    
    public SmartDevice getDevice(String s) 
    {
        return this.devices.get(s);
    }
    
    public void setOn(String s, boolean b) 
    {
        this.devices.entrySet().stream().filter(entry -> s.equals(entry.getKey())).map(entry -> {entry.getValue().setOn(b); return entry;}).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    
    public void setAllOn(boolean b) 
    {
        this.devices.entrySet().stream().map(entry -> {entry.getValue().setOn(b); return entry;}).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }
    
    public void addRoom(String s) 
    {
        this.locations.put(s, new ArrayList<String>());
    }
    
    public boolean hasRoom(String s) 
    {
        return this.locations.keySet().stream().anyMatch(key -> s.equals(key));
    }
    
    public void addToRoom (String s1, String s2) 
    {
        if (this.hasRoom(s1))
        {
            List<String> dev = this.locations.get(s1);
            dev.add(s2);
        }
        else
        {
            this.addRoom(s1);
            this.addToRoom(s1, s2);
        }
    }
    
    public boolean roomHasDevice (String s1, String s2) 
    {
        if (this.hasRoom(s1))
            return this.locations.get(s1).stream().anyMatch(dev -> s2.equals(dev));
        return false;
    }
}
