package model;

import lombok.Data;

@Data
public class Skill {

    /*
           Most of the skills of his offense and defense take 4 parameters: ability,
     impact, power and probabilty.<br>
     
      Abilty: How well the boxer can execute that skill.<br>
      Power: How much power it costs that boxer.<br>
      Impact: How that skill affects the other boxer.<br>
      Probability: How often that skill is executed. E.g. a jab is executed
      more often than a hook. The probability is static.
     */
    
    private String name;
    private double ability;
//    private double power;
    private double impact;
    private double probability;

    public Skill(String name, double ability, double impact, double probability) {
        this.name = name;
        this.ability = ability;
//        this.power = power;
        this.impact = impact;
        this.probability = probability;
    }

    public String equals() {
        return name;
    }

}
