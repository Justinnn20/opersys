import javax.swing.*;
import java.awt.BorderLayout;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;

/** CPU Scheduling: FCFS, SJF, Priority (non-preemptive) and Round Robin. */
public class CpuSchedulingApp extends JPanel {
    private final JTextField namesField = UiKit.field("P1, P2, P3, P4", 26);
    private final JTextField arrivalField = UiKit.field("0, 1, 2, 3", 26);
    private final JTextField burstField = UiKit.field("8, 4, 9, 5", 26);
    private final JTextField priorityField = UiKit.field("3, 1, 4, 2", 26);
    private final JTextField quantumField = UiKit.field("2", 6);
    private final JComboBox<String> algoBox =
            new JComboBox<>(new String[]{"FCFS", "SJF", "Priority", "Round Robin"});
    private final JTextArea output = UiKit.outputArea();

    public CpuSchedulingApp() {
        super(new BorderLayout());
        JButton run = UiKit.button("Run Simulation");
        run.addActionListener(e -> runSimulation());
        JPanel form = UiKit.form(
                "Process names", namesField,
                "Arrival times", arrivalField,
                "Burst times", burstField,
                "Priorities", priorityField,
                "Algorithm", algoBox,
                "Time quantum (RR only)", quantumField);
        add(UiKit.page(form, UiKit.actionRow(run), output), BorderLayout.CENTER);
    }

    private void runSimulation() {
        try {
            String[] names = UiKit.names(namesField.getText());
            int[] arrival = UiKit.ints(arrivalField.getText());
            int[] burst = UiKit.ints(burstField.getText());
            int[] priority = UiKit.ints(priorityField.getText());
            int n = names.length;
            if (arrival.length != n || burst.length != n || priority.length != n)
                throw new IllegalArgumentException("Every list must have the same number of values.");

            List<PCB> procs = new ArrayList<>();
            for (int i = 0; i < n; i++) procs.add(new PCB(names[i], arrival[i], burst[i], priority[i]));

            String algo = (String) algoBox.getSelectedItem();
            StringBuilder gantt = new StringBuilder();
            if (algo.equals("Round Robin")) {
                int q = Integer.parseInt(quantumField.getText().trim());
                if (q <= 0) throw new IllegalArgumentException("Quantum must be greater than 0.");
                roundRobin(procs, q, gantt);
            } else {
                nonPreemptive(procs, algo, gantt);
            }
            UiKit.show(output, report(algo, procs, gantt.toString()));
        } catch (RuntimeException ex) {
            UiKit.error(this, ex.getMessage());
        }
    }

    /** FCFS, SJF and Priority: once a process starts it runs to completion. */
    private static void nonPreemptive(List<PCB> procs, String algo, StringBuilder gantt) {
        List<PCB> left = new ArrayList<>(procs);
        int time = 0;
        while (!left.isEmpty()) {
            List<PCB> ready = new ArrayList<>();
            for (PCB p : left) if (p.arrival <= time) ready.add(p);

            if (ready.isEmpty()) {
                int next = Integer.MAX_VALUE;
                for (PCB p : left) next = Math.min(next, p.arrival);
                addSlice(gantt, "IDLE", time, next);
                time = next;
                continue;
            }

            PCB chosen = ready.get(0);
            for (PCB p : ready) if (better(p, chosen, algo)) chosen = p;

            chosen.start = time;
            addSlice(gantt, chosen.name, time, time + chosen.burst);
            time += chosen.burst;
            chosen.finish = time;
            left.remove(chosen);
        }
    }

    private static boolean better(PCB a, PCB b, String algo) {
        if (algo.equals("SJF")) {
            return a.burst != b.burst ? a.burst < b.burst : a.arrival < b.arrival;
        }
        if (algo.equals("Priority")) {
            return a.priority != b.priority ? a.priority < b.priority : a.arrival < b.arrival;
        }
        return a.arrival < b.arrival; // FCFS
    }

    /** Round Robin: each process gets at most one quantum before going to the back of the queue. */
    private static void roundRobin(List<PCB> procs, int quantum, StringBuilder gantt) {
        List<PCB> pending = new ArrayList<>(procs);
        pending.sort(Comparator.comparingInt((PCB p) -> p.arrival));
        Deque<PCB> ready = new ArrayDeque<>();
        int time = 0;
        int next = 0;
        int done = 0;

        while (done < procs.size()) {
            while (next < pending.size() && pending.get(next).arrival <= time) ready.add(pending.get(next++));

            if (ready.isEmpty()) {
                int arrive = pending.get(next).arrival;
                addSlice(gantt, "IDLE", time, arrive);
                time = arrive;
                continue;
            }

            PCB p = ready.poll();
            if (p.start < 0) p.start = time;
            int run = Math.min(quantum, p.remaining);
            addSlice(gantt, p.name, time, time + run);
            time += run;
            p.remaining -= run;

            // New arrivals enter the queue before the process that just used its quantum.
            while (next < pending.size() && pending.get(next).arrival <= time) ready.add(pending.get(next++));

            if (p.remaining == 0) {
                p.finish = time;
                done++;
            } else {
                ready.add(p);
            }
        }
    }

    private static void addSlice(StringBuilder sb, String name, int start, int end) {
        if (end > start) sb.append(String.format("| %s (%d-%d) ", name, start, end));
    }

    private static String report(String algo, List<PCB> procs, String gantt) {
        StringBuilder sb = new StringBuilder();
        sb.append("Algorithm: ").append(algo).append("\n\nGantt Chart:\n").append(gantt).append("|\n\n");
        sb.append(String.format("%-10s%-10s%-10s%-10s%-12s%-14s%-12s%n",
                "Process", "Arrival", "Burst", "Start", "Finish", "Turnaround", "Waiting"));
        double totalTurn = 0;
        double totalWait = 0;
        for (PCB p : procs) {
            int turnaround = p.finish - p.arrival;
            int waiting = turnaround - p.burst;
            totalTurn += turnaround;
            totalWait += waiting;
            sb.append(String.format("%-10s%-10d%-10d%-10d%-12d%-14d%-12d%n",
                    p.name, p.arrival, p.burst, p.start, p.finish, turnaround, waiting));
        }
        sb.append(String.format("%nAverage turnaround time: %.2f%n", totalTurn / procs.size()));
        sb.append(String.format("Average waiting time:    %.2f%n", totalWait / procs.size()));
        return sb.toString();
    }
}