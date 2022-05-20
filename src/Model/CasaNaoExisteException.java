package Model;

public class CasaNaoExisteException extends Exception 
{
    public CasaNaoExisteException(String message)
    {
        super(message);
    }
}