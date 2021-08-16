package odyssey.testing.rewards;

import odyssey.Odyssey;
import odyssey.testing.MineriftProfile;

public class SoulReward extends Odyssey.Reward {

    private int amount;

    public SoulReward(int amount) {
        this.amount = amount;
    }

    @Override
    public void giveReward(Odyssey.WrappedProfile wrappedProfile) {

        MineriftProfile profile = wrappedProfile.unwrap();
        profile.souls += amount;

    }
}
