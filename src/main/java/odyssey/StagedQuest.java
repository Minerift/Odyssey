package odyssey;

import java.util.Collections;
import java.util.List;

public class StagedQuest<T extends Odyssey.Profile> extends Quest<T> {

    public static <T extends Odyssey.Profile> StagedQuest.Builder<T> builder() {

    }

    public static <T extends Odyssey.Profile> StagedQuest<T> create(String name,
                                                                    List<Odyssey.Requirement<T>> reqs,
                                                                    List<Odyssey.Reward<T>> rewards) {
        return new StagedQuest<>(name, Collections.emptyList(), reqs, rewards);
    }

    public static <T extends Odyssey.Profile> StagedQuest<T> create(String name,
                                                                    List<String> desc,
                                                                    List<Odyssey.Requirement<T>> reqs,
                                                                    List<Odyssey.Reward<T>> rewards) {
        return new StagedQuest<>(name, desc, reqs, rewards);
    }

    private StagedQuest(String name,
                        List<String> desc,
                        Stage[]
                        List<Odyssey.Reward<T>> rewards) {
        super(name, desc, /*reqs,*/ rewards);
    }

    public boolean hasCompleted(T profile) {
        reqs.forEach();
    }

    public boolean hasCompletedStage() {

    }

    @Override
    public List<Odyssey.Requirement<T>> getAllRequirements() {
        return List.of(); // TODO
    }

    public static class Stage {

    }

    public static class Stages {
        private List<Stage> stages;
        private
    }

    public static class Builder<T extends Odyssey.Profile> {

        private Builder() {

        }

        public StagedQuest<T> build() {

        }

    }
}
