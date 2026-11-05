import javax.swing.*;
import java.awt.BorderLayout;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.StringJoiner;

/** Multiprogramming: jobs are loaded into memory while space allows and share the CPU round-robin. */
public class MultiprogrammingApp extends JPanel {
    private final JTextField jobsField = UiKit.field("A:200:5, B:300:3, C:500:4, D:150:2, E:250:3", 34);
    private final JTextField memoryField = UiKit.field("800", 8);
    private final JTextField quantumField = UiKit.field("2", 6);
    private final JTextArea output = UiKit.outputArea();

    public MultiprogrammingApp() {
        super(new BorderLayout());
        JButton run = UiKit.button("Simulate");
        run.addActionListener(e -> simulate());
        JPanel form = UiKit.form(
                "Jobs (name:memoryKB:burst, ...)", jobsField,
                "Total memory (KB)", memoryField,
                "Time quantum", quantumField);
        add(UiKit.page(form, UiKit.actionRow(run), output), BorderLayout.CENTER);
    }

    private void simulate() {
        try {
            List<Job> jobs = new ArrayList<>();
            for (String token : jobsField.getText().split("\\s*,\\s*")) {
                String[] parts = token.trim().split("\\s*:\\s*");
                if (parts.length != 3) throw new IllegalArgumentException("Use name:memory:burst, e.g. A:200:5");
                jobs.add(new Job(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
            }
            int totalMemory = Integer.parseInt(memoryField.getText().trim());
            int quantum = Integer.parseInt(quantumField.getText().trim());
            if (quantum <= 0) throw new IllegalArgumentException("Quantum must be greater than 0.");
            UiKit.show(output, simulate(jobs, totalMemory, quantum));
        } catch (RuntimeException ex) {
            UiKit.error(this, ex.getMessage());
        }
    }

    private static String simulate(List<Job> jobs, int totalMemory, int quantum) {
        for (Job j : jobs) {
            if (j.memory > totalMemory)
                throw new IllegalArgumentException(j.name + " needs more memory than the system has.");
        }

        Deque<Job> waiting = new ArrayDeque<>(jobs);  // jobs not yet in memory (FIFO)
        List<Job> inMemory = new ArrayList<>();
        Deque<Job> ready = new ArrayDeque<>();        // in memory and waiting for the CPU
        StringBuilder sb = new StringBuilder();
        int used = 0;
        int time = 0;
        int maxDegree = 0;

        used = admit(waiting, inMemory, ready, used, totalMemory, sb);
        maxDegree = Math.max(maxDegree, inMemory.size());

        while (!ready.isEmpty()) {
            Job j = ready.poll();
            int run = Math.min(quantum, j.remaining);
            sb.append(String.format("t=%3d-%-3d Run %-3s | mem %4d/%d KB | in memory %s | waiting %s%n",
                    time, time + run, j.name, used, totalMemory, names(inMemory), names(waiting)));
            time += run;
            j.remaining -= run;

            if (j.remaining == 0) {
                used -= j.memory;
                inMemory.remove(j);
                sb.append(String.format("  [DONE] %s finished at t=%d, memory freed%n", j.name, time));
                used = admit(waiting, inMemory, ready, used, totalMemory, sb);
                maxDegree = Math.max(maxDegree, inMemory.size());
            } else {
                ready.add(j);
            }
        }

        sb.append("\nAll jobs finished at t=").append(time).append("\n");
        sb.append("Degree of multiprogramming (max jobs in memory at once): ").append(maxDegree).append("\n");
        return sb.toString();
    }

    /** Loads waiting jobs in FIFO order while they fit in memory. Returns the new memory usage. */
    private static int admit(Deque<Job> waiting, List<Job> inMemory, Deque<Job> ready,
                             int used, int totalMemory, StringBuilder sb) {
        while (!waiting.isEmpty() && used + waiting.peek().memory <= totalMemory) {
            Job j = waiting.poll();
            used += j.memory;
            inMemory.add(j);
            ready.add(j);
            sb.append("  [LOAD] ").append(j.name).append(" (").append(j.memory).append(" KB) loaded into memory\n");
        }
        return used;
    }

    private static String names(Collection<Job> jobs) {
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        for (Job j : jobs) sj.add(j.name);
        return sj.toString();
    }

    private static class Job {
        final String name;
        final int memory;
        int remaining;

        Job(String name, int memory, int burst) {
            this.name = name;
            this.memory = memory;
            this.remaining = burst;
        }
    }
}