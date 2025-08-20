package odyssey;

import odyssey.util.SortedList;

import java.util.Collections;
import java.util.List;

public class StagedQuest<T extends Odyssey.Profile> extends Quest<T> {

    public static <T extends Odyssey.Profile> StagedQuest.Builder<T> builder() {
        return new Builder<>();
    }

    public static <T extends Odyssey.Profile> StagedQuest<T> create(Odyssey<T> odyssey,
                                                                    String name,
                                                                    List<Stage> stages,
                                                                    List<Odyssey.Reward<T>> rewards) {
        return new StagedQuest<>(odyssey, name, Collections.emptyList(), stages, rewards);
    }

    public static <T extends Odyssey.Profile> StagedQuest<T> create(Odyssey<T> odyssey,
                                                                    String name,
                                                                    List<String> desc,
                                                                    List<Stage> stages,
                                                                    List<Odyssey.Reward<T>> rewards) {
        return new StagedQuest<>(odyssey, name, desc, stages, rewards);
    }

    private final List<Stage<T>> stages;
    private StagedQuest(Odyssey<T> odyssey,
                        String name,
                        List<String> desc,
                        List<Stage> stages, // consider Stage[]
                        List<Odyssey.Reward<T>> rewards) {
        super(odyssey, name, desc, rewards);
        this.stages = Collections.unmodifiableList(stages);
    }

    public boolean hasCompleted(T profile) {
        //reqs.forEach();
        return false;
    }

    public boolean hasCompletedStage() {
        return false;
    }

    @Override
    public SortedList<Odyssey.Requirement<T>> getAllRequirements() {
        return new SortedList<>(); // TODO
    }

    @Override
    public boolean isReqComplete(Odyssey.Requirement<T> req) {
        return false;
    }

    public static class Stage<T extends Odyssey.Profile> {

        public static <T extends Odyssey.Profile> Stage<T> of(Odyssey.Requirement<T> ... reqs) {
            return new Stage<>(reqs);
        }

        public final SortedList<Odyssey.Requirement<T>> reqs;

        public Stage(Odyssey.Requirement<T>[] reqs) {
            this.reqs = new SortedList<>(reqs.length);
            for(Odyssey.Requirement<T> req : reqs) {
                this.reqs.add(req);
            }
        }
    }

    public static class Stages {
        private List<Stage> stages;
        //private
    }

    public static class Builder<T extends Odyssey.Profile> {

        private Stage<T>[] stages;

        private Builder() {

        }

        public StagedQuest<T> build() {

        }

    }
}
