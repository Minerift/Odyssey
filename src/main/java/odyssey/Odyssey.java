package odyssey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Odyssey<E> {

    // NOTE: singleton structure
    private static Odyssey INSTANCE = null;
    public static <F> Odyssey getLibrary() {
        if(INSTANCE == null) INSTANCE = new Odyssey<F>();
        return INSTANCE;
    }

    // TODO: fix list generic
    private Map<E, List> quests;

    private Odyssey() {
        this.quests = new HashMap<>();
    }

    public void registerProfile(E profile) {
        quests.putIfAbsent(profile, new ArrayList<>());
    }

    public static abstract class QuestInfo {

        private List<Requirement> requirements;
        private List<Reward> rewards;

    }

    public static abstract class Requirement {

        public abstract boolean meetsRequirement(WrappedProfile wrappedProfile);
        public abstract void takeRequirement(WrappedProfile wrappedProfile);

    }

    public static abstract class Reward {

        public abstract void giveReward(WrappedProfile wrappedProfile);

    }

    public static final class WrappedProfile {

        private Object actual;

        private WrappedProfile(Object actual) {
            this.actual = actual;
        }

        public <F> F unwrap() {
            return (F) actual;
        }

    }

}
