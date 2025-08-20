package odyssey;

import odyssey.util.SortedList;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.*;
import java.util.function.IntUnaryOperator;

public class QuestRegistry<T extends Odyssey.Profile> {

    public static <T extends Odyssey.Profile> QuestRegistry.Builder<T> builder() {
        return new Builder<T>(EMPTY_QUESTS, EMPTY_LOOKUP, Builder.DEFAULT_GROWER);
    }

    private static final int INIT_SIZE = 16;
    private static final Quest[] EMPTY_QUESTS = new Quest[0];
    private static final BitSet[] EMPTY_LOOKUP = new BitSet[0];

    private Quest<T>[] quests;
    private BitSet[] req2QuestLookup;

    public QuestRegistry(Quest<T>[] quests, BitSet[] req2QuestLookup) {
        this.quests = quests;
        this.req2QuestLookup = req2QuestLookup;
    }

    public List<Quest<T>> getQuests(Odyssey.Requirement<T> req) {
        BitSet set = req2QuestLookup[req.id];
        if(set.isEmpty()) {
            return Collections.emptyList();
        }
        List<Quest<T>> res = new ArrayList<>();
        for(int i = set.nextSetBit(0); i >= 0; i = set.nextSetBit(i+1)) {
            res.add(quests[i]);
            if(i == Integer.MAX_VALUE) { // should never occur, but provided for correctness
                break;
            }
        }
        return res;
    }

    public Iterator<Quest<T>> getQuestsIter(Odyssey.Requirement<T> req) {
        BitSet set = req2QuestLookup[req.id];
        return new Iterator<>() {
            private int i = set.nextSetBit(0);

            @Override
            public boolean hasNext() {
                return i >= 0;
            }

            @Override
            public Quest<T> next() {
                Quest<T> res = quests[i];
                i = set.nextSetBit(i+1);
                if(i == Integer.MAX_VALUE) {
                    i = -1;
                }
                return res;
            }
        };
    }

    public static class Builder<T extends Odyssey.Profile> {

        private static final IntUnaryOperator DEFAULT_GROWER = (i) -> i == 0 ? 2 : i * 2;

        private final IntUnaryOperator grower;

        private Quest<T>[] quests;
        private BitSet[] req2QuestLookup;

        private void init() {
            quests = new Quest[INIT_SIZE];
            req2QuestLookup = new BitSet[INIT_SIZE];
        }

        protected Builder(Quest<T>[] quests, BitSet[] req2QuestLookup, IntUnaryOperator grower) {
            this.quests = quests;
            this.req2QuestLookup = req2QuestLookup;
            this.grower = grower;
        }

        public Builder<T> addQuest(@NonNull Quest<T> quest) {
            return addQuest(quest, false);
        }

        public Builder<T> addQuest(Quest<T> quest, boolean overwrite) {
            if(quests == EMPTY_QUESTS) {
                init();
            }

            if(quests[quest.id] != null && !overwrite) {
                throw new RuntimeException("TODO: custom ex; quest already registered with id");
            }

            append(quest);
            return this;
        }

        private void append(Quest<T> quest) {
            // add quest
            if(quests.length <= quest.id) {
                quests = resize(quests, quest.id);
            }
            quests[quest.id] = quest;


            SortedList<Odyssey.Requirement<T>> reqs = quest.getAllRequirements();
            if(reqs.size() == 0) {
                return;
            }

            int lastId = reqs.get(reqs.size()-1).id;
            if(req2QuestLookup.length <= lastId) {
                req2QuestLookup = resize(req2QuestLookup, lastId);
            }

            for(Odyssey.Requirement<T> req : reqs) {
                BitSet quests = req2QuestLookup[req.id];
                if(quests == null) {
                    req2QuestLookup[req.id] = quests = new BitSet();
                }
                quests.set(quest.id);
            }
        }

        private <A> A[] resize(A[] array, int id) {
            int size = grower.applyAsInt(quests.length);
            if(size < id) {
                size = id;
            }
            A[] buf = (A[]) new Object[size];
            System.arraycopy(array, 0, buf, 0, array.length);
            return buf;
        }

        public QuestRegistry<T> build() {
            return new QuestRegistry<>(quests, req2QuestLookup);
        }
    }

}
