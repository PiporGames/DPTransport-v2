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
    
        public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<reliable: ");
        switch (Value){
            case 10:
                sb.append("High");
                break;
            case 5:
                sb.append("Low");
                break;
            default:
                sb.append("UNKNOWN");
                break;
        }
        
        sb.append(" (value: " + Value + ")>");
        return sb.toString();
    }

}