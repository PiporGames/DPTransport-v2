/**
 * Enumeration "class" Reliability
 * 
 * @author Jose, Manuel & David
 * @version 2023.11.27
 */
public enum Reliability
{
    HIGH(10),
    LOW(5);

    private final int Value;

    Reliability(int Value){
        this.Value = Value;
    }

    public int getValor(){
        return(this.Value);
    }
}