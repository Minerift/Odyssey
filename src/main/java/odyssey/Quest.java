package odyssey;

import java.util.Collections;
import java.util.List;

public abstract class Quest<T extends Odyssey.Profile> {

    protected final String name;
    protected final List<String> desc;
    //protected final List<Odyssey.Requirement<T>> reqs;
    protected final List<Odyssey.Reward<T>> rewards;

    public Quest(String name,
                 List<String> desc,
                 //List<Odyssey.Requirement<T>> reqs,
                 List<Odyssey.Reward<T>> rewards) {
        this.name = name;
        this.desc = Collections.unmodifiableList(desc);
        //this.reqs = Collections.unmodifiableList(reqs);
        this.rewards = Collections.unmodifiableList(rewards);
    }

    public abstract List<Odyssey.Requirement<T>> getAllRequirements();

    public List<Odyssey.Reward<T>> getRewards() {
        return rewards;
    }

}
