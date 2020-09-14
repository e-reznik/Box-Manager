package model;

import lombok.Data;

@Data
public class Skill {

    private String name;
    private double ability;
    private double power;
    private double impact;
    private double probability;

    public Skill(String name, double ability, double power, double impact, double probability) {
        this.name = name;
        this.ability = ability;
        this.power = power;
        this.impact = impact;
        this.probability = probability;
    }
    
    public String equals(){
        return name;
    }

}
