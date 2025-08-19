package odyssey;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;

import java.util.*;
import java.util.function.Supplier;

public class Odyssey<T extends Odyssey.Profile> {

    public static <T extends Profile> Odyssey<T> create() {
        return new Odyssey<>(new HashMap<>());
    }

    public static <T extends Profile> Odyssey<T> create(Map<T, List<?>> quests) {
        return new Odyssey<>(quests);
    }

    public Odyssey(Map<T, List<?>> quests) {
        this.quests = quests;
    }

    // TODO: fix list generic
    private Map<T, List<?>> quests;

    public void registerProfile(T profile) {
        quests.putIfAbsent(profile, new ArrayList<>());
    }

    public void registerQuest() {

    }

    public static abstract class Requirement<T extends Profile> {

        public abstract boolean isMet(T profile);
        public abstract void complete(T profile);

    }

    public static abstract class Reward<T extends Profile> {

        public abstract void giveReward(T profile);

    }

    public interface Profile {
        // empty
    }

    public static class Builder<T extends Profile> {

        private Int2ObjectMap<Quest<T>> quests;

        private Builder() {
            this.quests = new Int2ObjectArrayMap<>();
        }

        public Builder<T> loadQuests(Int2ObjectMap<Quest<T>> quests) {
            this.quests = quests;
            return this;
        }

        public Builder<T> registerQuest() {

            return this;
        }

        public Builder<T> registerQuest(Quest<T> quest) {
            return this;
        }

        public Odyssey<T> build() {
            return new Odyssey<>(); //FIXME
        }

    }

}
