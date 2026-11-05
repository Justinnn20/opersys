import java.util.ArrayList;
import java.util.List;

/** Banker's Algorithm: safety check and resource-request handling for deadlock avoidance. */
public class BankersAlgorithm {
    private final int processCount;
    private final int resourceCount;
    private final int[] available;
    private final int[][] max;
    private final int[][] allocation;

    public BankersAlgorithm(int[] available, int[][] max, int[][] allocation) {
        this.processCount = max.length;
        this.resourceCount = available.length;
        this.available = available.clone();
        this.max = max;
        this.allocation = allocation;
        validate();
    }

    private void validate() {
        if (allocation.length != processCount)
            throw new IllegalArgumentException("Allocation must have " + processCount + " rows (one per process).");
        for (int p = 0; p < processCount; p++) {
            if (max[p].length != resourceCount || allocation[p].length != resourceCount)
                throw new IllegalArgumentException("Every row must have " + resourceCount + " values.");
            for (int r = 0; r < resourceCount; r++)
                if (allocation[p][r] > max[p][r])
                    throw new IllegalArgumentException("P" + p + " allocation is larger than its max.");
        }
    }

    public int getProcessCount() {
        return processCount;
    }

    public int[] getAvailable() {
        return available.clone();
    }

    public int need(int p, int r) {
        return max[p][r] - allocation[p][r];
    }

    public int[] getNeed(int p) {
        int[] row = new int[resourceCount];
        for (int r = 0; r < resourceCount; r++) row[r] = need(p, r);
        return row;
    }

    /** Returns a safe execution order, or an empty list if the state is unsafe. */
    public List<Integer> findSafeSequence() {
        int[] work = available.clone();
        boolean[] finished = new boolean[processCount];
        List<Integer> sequence = new ArrayList<>();
        boolean progress = true;

        while (progress) {
            progress = false;
            for (int p = 0; p < processCount; p++) {
                if (!finished[p] && canFinish(p, work)) {
                    for (int r = 0; r < resourceCount; r++) work[r] += allocation[p][r];
                    finished[p] = true;
                    sequence.add(p);
                    progress = true;
                }
            }
        }
        return sequence.size() == processCount ? sequence : new ArrayList<>();
    }

    private boolean canFinish(int p, int[] work) {
        for (int r = 0; r < resourceCount; r++)
            if (need(p, r) > work[r]) return false;
        return true;
    }

    /** Grants the request only if resources are available and the resulting state is safe. */
    public boolean request(int p, int[] req) {
        if (p < 0 || p >= processCount) throw new IllegalArgumentException("There is no process P" + p + ".");
        if (req.length != resourceCount) throw new IllegalArgumentException("Request must have " + resourceCount + " values.");
        for (int r = 0; r < resourceCount; r++)
            if (req[r] > need(p, r))
                throw new IllegalStateException("P" + p + " requests more than its declared need.");
        for (int r = 0; r < resourceCount; r++)
            if (req[r] > available[r]) return false;

        for (int r = 0; r < resourceCount; r++) {
            available[r] -= req[r];
            allocation[p][r] += req[r];
        }
        if (findSafeSequence().isEmpty()) {
            for (int r = 0; r < resourceCount; r++) {
                available[r] += req[r];
                allocation[p][r] -= req[r];
            }
            return false;
        }
        return true;
    }
}