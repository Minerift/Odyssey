package odyssey.testing.requirements;

import odyssey.Odyssey;
import odyssey.testing.MineriftProfile;

public class GoldRequirement extends Odyssey.Requirement {

    private int goldRequired;

    public GoldRequirement(int goldRequired) {
        this.goldRequired = goldRequired;
    }

    @Override
    public boolean meetsRequirement(Odyssey.WrappedProfile wrappedProfile) {

        // Get profile
        MineriftProfile profile = wrappedProfile.unwrap();

        // Check whether player has enough gold
        return profile.gold >= goldRequired;
    }

    @Override
    public void takeRequirement(Odyssey.WrappedProfile wrappedProfile) {

        // Get profile
        MineriftProfile profile = wrappedProfile.unwrap();

        // Deduct gold
        profile.gold -= goldRequired;

    }
}
