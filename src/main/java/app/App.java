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
     * @param name Name of the boxer
     * @return a new Boxer with a set of skills
     */
    private Boxer createBoxer(String name) {
        Defense defense;
        Offense offense;

        int[] ints110 = random.ints(1, 11).limit(21).toArray();
        int[] ints14 = random.ints(1, 5).limit(21).toArray();
        int[] ints47 = random.ints(4, 8).limit(21).toArray();
        int[] ints710 = random.ints(7, 11).limit(21).toArray();

        int[] block = {ints110[0], ints14[1]};
        int[] parry = {ints110[3], ints47[4]};
        int[] slip = {ints110[6], ints710[7]};
        defense = new Defense(block, parry, slip);

        int[] jab = {ints110[9], ints14[10]};
        int[] cross = {ints110[12], ints47[13]};
        int[] hook = {ints110[15], ints710[16]};
        offense = new Offense(jab, cross, hook);

        int stamina = ints110[18];
        int strength = ints110[19];
        int motivation = ints110[20];

        Boxer boxer = new Boxer(name, offense, defense, stamina, strength, motivation);
        return boxer;
    }
}
