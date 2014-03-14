package Player;

/**
 * Created by Matthew on 12/03/14.
 */
public class Bloodline {
    private String name;
    private float hpMultiplier;
    private float mpMultiplier;
    private float staminaMultiplier;

    public Bloodline(String[] info){
        this.name = info[0];
        this.hpMultiplier = Float.parseFloat(info[1]);
        this.mpMultiplier = Float.parseFloat(info[2]);
        this.staminaMultiplier = Float.parseFloat(info[3]);
    }

    public String toString(){
        return "Bloodline = " + name + ", hpMultiplier = " + hpMultiplier + ", mpMultiplier = " + mpMultiplier
                + ", staminaMultiplier = " + staminaMultiplier;
    }

    public String getName(){
        return name;
    }

    public float getHpMultiplier() {
        return hpMultiplier;
    }

    public float getMpMultiplier() {
        return mpMultiplier;
    }

    public float getStaminaMultiplier() {
        return staminaMultiplier;
    }
}
