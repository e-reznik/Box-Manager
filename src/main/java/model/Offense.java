package model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Offense {

    List<Skill> skills = new ArrayList<>();

    public Offense() {
    }

    public Offense(int[] intJeb, int[] intCross, int[] intHook) {
        Skill jeb = new Skill("jab", intJeb[0], intJeb[1], 5);
        Skill cross = new Skill("cross", intCross[0], intCross[1], 3);
        Skill hook = new Skill("hook", intHook[0], intHook[1], 1);

        skills.add(jeb);
        skills.add(cross);
        skills.add(hook);
    }

}
