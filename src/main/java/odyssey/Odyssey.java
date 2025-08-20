package odyssey;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Odyssey<T extends Odyssey.Profile> {

    public static <T extends Profile> Odyssey<T> create() {
        return new Odyssey<>(new HashMap<>());
    }

    public static <T extends Profile> Odyssey<T> create(Map<T, List<?>> quests) {
        return new Odyssey<>(quests);
    }

    public Odyssey(Map<T, List<?>> questProgress) {
        this.questProgress = questProgress;
    }

    private Map<T, List<?>> questProgress; //FIXME: update
    private QuestRegistry<T> registry;

    public void registerProfile(T profile) {
        questProgress.putIfAbsent(profile, new ArrayList<>());
    }

    public QuestRegistry<T> getRegistry() {
        return registry;
    }

    public static abstract class Requirement<T extends Profile> implements Comparable<Requirement<T>> {

        protected static final AtomicInteger NEXT_ID = new AtomicInteger();

        protected final int id = NEXT_ID.getAndIncrement();

        public abstract boolean isMet(T profile);
        public abstract void complete(T profile);

        @Override
        public int compareTo(@NotNull Requirement<T> o) {
            return id - o.id;
        }

        // internal complete fn for additional completion
        protected void _complete(T profile, Quest<T> quest) {
            if(quest.isReqComplete(this)) {
                quest.markReqComplete();
            }

            complete(profile);
        }

        // Marks complete
        public void markComplete(T profile, Quest<T> quest) {
            quest.registry();
        }

        /*public boolean isCompleted(T profile) {

        }*/

    }

    public static abstract class Reward<T extends Profile> {

        public abstract void giveReward(T profile);

    }

    public interface Profile {
        // empty
    }

    public static class Builder<T extends Profile> {

        private QuestRegistry<T> registry;

        private Builder() {
            this.registry = null; // TODO
        }

        public Builder<T> loadQuests(Int2ObjectMap<Quest<T>> quests) {
            //this.quests = quests;
            return this;
        }

        public Builder<T> registerQuest() {

            return this;
        }

        public Builder<T> registerQuest(Quest<T> quest) {
            return this;
        }

        public Odyssey<T> build() {
            return new Odyssey<>(Collections.emptyMap()); //FIXME
        }

    }

}
