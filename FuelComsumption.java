
/**
 * Enumeration class FuelConsumption
 *
 * @author José, Manuel y David
 * @version 2023
 */
public enum FuelComsumption
{
    HIGH(8),
    MEDIUM(6),
    LOW(4);
    
    private final int comsumption;
    
    FuelComsumption(int comsumption) {
        this.comsumption = comsumption;
    }
    
    public int getComsumption() {
        return comsumption;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<fuel consumption: ");
        switch (comsumption){
            case 4:
                sb.append("Low");
                break;
            case 6:
                sb.append("Medium");
                break;
            case 8:
                sb.append("High");
                break;
            default:
                sb.append("UNKNOWN");
                break;
        }
        
        sb.append(" (value: " + comsumption + ")>");
        return sb.toString();
    }
}
