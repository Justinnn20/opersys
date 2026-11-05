/** Process Control Block: one process as seen by the CPU scheduler. */
public class PCB {
    public final String name;
    public final int arrival;
    public final int burst;
    public final int priority;
    public int remaining;
    public int start = -1;
    public int finish;

    public PCB(String name, int arrival, int burst, int priority) {
        this.name = name;
        this.arrival = arrival;
        this.burst = burst;
        this.priority = priority;
        this.remaining = burst;
    }
}