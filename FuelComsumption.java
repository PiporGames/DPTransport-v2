
/**
 * Enumeration class FuelConsumption - write a description of the enum class here
 *
 * @author (your name here)
 * @version (version number or date here)
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
}
