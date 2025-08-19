package odyssey.testing.requirements;

import odyssey.Odyssey;
import odyssey.testing.MineriftProfile;

public class GoldRequirement extends Odyssey.Requirement<MineriftProfile> {

    private int goldRequired;

    public GoldRequirement(int goldRequired) {
        this.goldRequired = goldRequired;
    }

    @Override
    public boolean isMet(MineriftProfile profile) {
        // Check whether player has enough gold
        return profile.gold >= goldRequired;
    }

    @Override
    public void complete(MineriftProfile profile) {

        // Deduct gold
        profile.gold -= goldRequired;
    }
}
