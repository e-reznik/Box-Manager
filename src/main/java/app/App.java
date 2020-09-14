package app;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import javax.inject.Named;
import model.Boxer;
import model.Defense;
import model.Offense;

@Named
public class App {

    private final static Logger LOGGER = Logger.getLogger(App.class.getName());
    private final Random random = new Random();
    private List<Boxer> boxers;
    
    public void init() {
        boxers = new LinkedList<>();

        boxers.add(createBoxer("Nightmare"));
        boxers.add(createBoxer("Tornado"));

        boxers.forEach(f -> {
            System.out.println(f.toString());
        });

        Fight fight = new Fight(boxers);
    }

    /**
     * Creates a new boxer.
     *
     * Most of the skills of his offense and defense take 4 parameters: ability,
     * impact, power and probabilty.<br>
     *
     * Abilty: How well the boxer can execute that skill.<br>
     * Power: How much power it costs that boxer.<br>
     * Impact: How that skill affects the other boxer.<br>
     * Probability: How often that skill is executed. E.g. a jab is executed
     * more often than a hook. The probability is static.
     *
     * @param name Name of the boxer
     * @return a new Boxer with a set of skills
     */
    private Boxer createBoxer(String name) {
        Defense defense;
        Offense offense;
        Boxer fighter = null;

        int[] ints = random.ints(1, 11).limit(21).toArray();

        for (int j = 0; j < ints.length; j++) {
            int[] block = {ints[0], ints[1], ints[2]};
            int[] parry = {ints[3], ints[4], ints[5]};
            int[] slip = {ints[6], ints[7], ints[8]};
            defense = new Defense(block, parry, slip);

            int[] jab = {ints[9], ints[10], ints[11]};
            int[] cross = {ints[12], ints[13], ints[14]};
            int[] hook = {ints[15], ints[16], ints[17]};
            offense = new Offense(jab, cross, hook);

            int stamina = ints[18];
            int strength = ints[19];
            int psyche = ints[20];

            fighter = new Boxer(name, offense, defense, stamina, strength, psyche);
        }

        return fighter;
    }
}
