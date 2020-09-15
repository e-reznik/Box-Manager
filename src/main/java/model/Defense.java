package model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Defense {

    List<Skill> skills = new ArrayList<>();

    public Defense() {
    }

    public Defense(int[] intBlock, int[] intParry, int[] intSlip) {
        Skill block = new Skill("block", intBlock[0], intBlock[1], 3);
        Skill parry = new Skill("parry", intParry[0], intParry[1], 2);
        Skill slip = new Skill("slip", intSlip[0], intSlip[1], 1);

        skills.add(block);
        skills.add(parry);
        skills.add(slip);
    }
}
