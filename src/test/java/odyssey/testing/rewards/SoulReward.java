package odyssey.testing.rewards;

import odyssey.Odyssey;
import odyssey.testing.MineriftProfile;

public class SoulReward extends Odyssey.Reward<MineriftProfile> {

    private int amount;

    public SoulReward(int amount) {
        this.amount = amount;
    }

    @Override
    public void giveReward(MineriftProfile profile) {
        profile.souls += amount;
    }
}
