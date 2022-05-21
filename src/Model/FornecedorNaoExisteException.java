package Model;

public class FornecedorNaoExisteException extends Exception
{
    public FornecedorNaoExisteException(String message)
    {
        super(message);
    }
}