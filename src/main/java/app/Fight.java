package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Boxer;
import model.Coefficients;
import model.Skill;

public class Fight implements Coefficients {

    private final static Logger LOGGER = Logger.getLogger(Fight.class.getName());
    private final Random random = new Random();

    private final List<Boxer> boxers;
    private List<String> defSkills;
    private List<String> ofSkills;

    int points1;
    int points2;

    public Fight(List<Boxer> boxers) {
        this.boxers = boxers;

        loadSkills();
        fight();
    }

    /**
     * Starts a fight.
     */
    private void fight() {
        LOGGER.log(Level.INFO, "Let's get ready to rumble!");

        // Determining the first boxer to attack
        int intBoxer = random.nextInt(2);
        while (!checkIfEnd()) {
            LOGGER.log(Level.INFO, "--- new attack ---");

            Boxer boxer1 = boxers.get(intBoxer % 2);
            Skill attack = attack(boxer1);

            intBoxer++; // alternating boxers

            Boxer boxer2 = boxers.get(intBoxer % 2);
            Skill defense = defend(boxer2);

            checkImpact(boxer1, attack, boxer2, defense);
        }

        LOGGER.log(Level.INFO, boxers.get(0).getName() + " - Power: " + boxers.get(0).getPower() + ". Endurance: " + boxers.get(0).getEndurance());
        LOGGER.log(Level.INFO, boxers.get(1).getName() + " - Power: " + boxers.get(1).getPower() + ". Endurance: " + boxers.get(1).getEndurance());
        LOGGER.log(Level.INFO, "Points: " + points1 + ":" + points2);
    }

    /**
     * Randomly executes an attack.
     *
     * @param intBoxer
     */
    private Skill attack(Boxer boxer) {
        String skillTemp = ofSkills.get(random.nextInt(ofSkills.size()));

        Optional<Skill> oskill = boxer.getOffense().getSkills().stream().filter(p -> p.getName().equals(skillTemp)).findFirst();
        Skill skill = oskill.get();

        LOGGER.log(Level.INFO, boxer.getName() + " uses " + skill.getName());

        return skill;
    }

    /**
     * Randomly executes a defense.
     *
     * @param intBoxer
     */
    private Skill defend(Boxer boxer) {
        String skillTemp = defSkills.get(random.nextInt(defSkills.size()));
        Optional<Skill> oskill = boxer.getDefense().getSkills().stream().filter(p -> p.getName().equals(skillTemp)).findFirst();
        Skill skill = oskill.get();

        LOGGER.log(Level.INFO, boxer.getName() + " uses " + skill.getName());

        return skill;
    }

    private void checkImpact(Boxer boxer1, Skill ofSkill, Boxer boxer2, Skill defSkill) {
        double res1;
        double res2;

        res1 = ((boxer1.getEndurance() * COEENDURANCE)
                + (boxer1.getStrength() * COESTRENGHT)
                + (boxer1.getMotivation() * COEMOTIVATION)
                + (ofSkill.getAbility() * COEABILITY)
                + (ofSkill.getImpact() * COEIMPACT)
                + (random.nextInt(10) * COELUCK)) / 10;

        res2 = ((boxer2.getEndurance() * COEENDURANCE)
                + (boxer2.getStrength() * COESTRENGHT)
                + (boxer2.getMotivation() * COEMOTIVATION)
                + (defSkill.getAbility() * COEABILITY)
                + (defSkill.getImpact() * COEIMPACT)
                + (random.nextInt(10) * COELUCK)) / 10;

        if (res1 > res2) {
            boxer2.setPower(boxer2.getPower() - res1);
            boxer1.setMotivation(boxer1.getMotivation() + COEMOTIVATIONATTACK); // increasing psyche after successfull attack
            points1++;
            LOGGER.log(Level.INFO, boxer1.getName() + " wins this attack: " + res1 + ":" + res2);
        } else {
            boxer1.setPower(boxer1.getPower() - res2);
            boxer2.setMotivation(boxer2.getMotivation() + COEMOTIVATIONDEFENSE); // increasing psyche after successfull defense
            LOGGER.log(Level.INFO, boxer2.getName() + " wins this attack: " + res1 + ":" + res2);
        }

        boxer1.setEndurance(boxer1.getEndurance() - COEENDURANCEATTACK); // attacking boxer
        boxer2.setEndurance(boxer2.getEndurance() - COEENDURANCEDEFENSE); // defending boxer
    }

    /**
     * Check after every attack, if both boxers still have enough power and
     * endurance to continue.
     *
     * @return true, if at least 1 of the boxers doesn't have enough power or
     * endurance: <=0
     */
    private boolean checkIfEnd() {
        if (boxers.get(0).getPower() <= 0) {
            LOGGER.log(Level.INFO, "Winner by KO: " + boxers.get(1).getName());
            return true;
        } else if (boxers.get(1).getPower() <= 0) {
            LOGGER.log(Level.INFO, "Winner by KO: " + boxers.get(0).getName());
            return true;
        } else if (boxers.get(0).getEndurance() <= 0) {
            LOGGER.log(Level.INFO, "Winner by RSC: " + boxers.get(1).getName());
            return true;
        } else if (boxers.get(1).getEndurance() <= 0) {
            LOGGER.log(Level.INFO, "Winner by RSC: " + boxers.get(0).getName());
            return true;
        }
        return false;
    }

    /* --- Init --- */
    /**
     * Putting the skills into a list depending on their probability.
     * Probability of 3 means, that skill will be put into the list 3 times.
     */
    private void loadSkills() {
        List<Skill> defSkillsTemp = boxers.get(0).getDefense().getSkills();
        List<Skill> ofSkillsTemp = boxers.get(0).getOffense().getSkills();
        defSkills = new ArrayList<>();
        ofSkills = new ArrayList<>();

        defSkillsTemp.forEach(s -> {
            for (int i = 0; i < s.getProbability(); i++) {
                defSkills.add(s.getName());
            }
        });
        ofSkillsTemp.forEach(s -> {
            for (int i = 0; i < s.getProbability(); i++) {
                ofSkills.add(s.getName());
            }
        });
    }
}
