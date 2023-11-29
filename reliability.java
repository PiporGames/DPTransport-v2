
/**
 * Enumeration class reliable - write a description of the enum class here
 * 
 * @author Jose, Manuel & David
 * @version 2023.11.27
 */
public enum reliability
{
    HIGH (10),
    LOW (5);
    
    private int Value;
    
    reliability(int Value){
        this.Value = Value;
    }
    
    public int getValor(){
        return(this.Value);
    }
}
