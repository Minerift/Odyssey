package odyssey;

import odyssey.util.SortedList;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Quest<T extends Odyssey.Profile> implements Comparable<Quest<T>> {

    protected static final AtomicInteger NEXT_ID = new AtomicInteger();

    protected final int id = NEXT_ID.getAndIncrement();

    protected final Odyssey<T> odyssey;
    protected final String name;
    protected final List<String> desc;
    //protected final List<Odyssey.Requirement<T>> reqs;
    protected final List<Odyssey.Reward<T>> rewards;

    public Quest(Odyssey<T> odyssey, String name,
                 List<String> desc,
                 //List<Odyssey.Requirement<T>> reqs,
                 List<Odyssey.Reward<T>> rewards) {
        this.odyssey = odyssey;
        this.name = name;
        this.desc = Collections.unmodifiableList(desc);
        //this.reqs = Collections.unmodifiableList(reqs);
        this.rewards = Collections.unmodifiableList(rewards);
    }

    @Override
    public final int compareTo(@NotNull Quest<T> o) {
        return id - o.id;
    }

    public abstract QuestProgress start(T profile);
    public abstract SortedList<Odyssey.Requirement<T>> getAllRequirements();
    public abstract boolean isReqComplete(Odyssey.Requirement<T> req);

    public List<Odyssey.Reward<T>> getRewards() {
        return rewards;
    }

    public Odyssey<T> odyssey() {
        return odyssey;
    }

    public QuestRegistry<T> registry() {
        return odyssey.getRegistry();
    }

    // Marks the requirement complete for the current stage
    public void markReqComplete(T profile, Odyssey.Requirement<T> req) {

    }

}
