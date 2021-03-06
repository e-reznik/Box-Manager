package model;

import lombok.Data;

@Data
public class Boxer {

    private String name;
    private Offense offense;
    private Defense defense;
    private double power = 10;
    private double endurance;
    private double strength;
    private double motivation;

    // weight, size
    // tactics (offensive, defensive)
    
    public Boxer(String name, Offense offense, Defense defense, double endurance, double strength, double motivation) {
        this.name = name;
        this.offense = offense;
        this.defense = defense;
        this.endurance = endurance;
        this.strength = strength;
        this.motivation = motivation;
    }

}
