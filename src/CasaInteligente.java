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

    private String morada;
    private Map<String, SmartDevice> devices; // identificador -> SmartDevice
    private Map<String, List<String>> locations; // Espaço -> Lista codigo dos devices

    /**
     * Constructor for objects of class CasaInteligente
     */
    public CasaInteligente() {
        // initialise instance variables
        this.morada = "";
        this.devices = new HashMap();
        this.locations = new HashMap();
    }

    public CasaInteligente(String morada) {
        // initialise instance variables
        this.morada = morada;
        this.devices = new HashMap();
        this.locations = new HashMap();
    }

    public String getMorada ()
    {
        return this.morada;
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
    }
    
    public boolean roomHasDevice (String s1, String s2) 
    {
        if (this.hasRoom(s1))
            return this.locations.get(s1).stream().anyMatch(dev -> s2.equals(dev));
        return false;
    }
}
