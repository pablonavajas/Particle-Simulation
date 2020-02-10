package simulation;

public abstract class AbstractEvent implements Event {

    // (it was empty, modified by Pablo)
    private final double time;

    /**
     * Constructor for AbstractEvent.
     */
    public AbstractEvent(double time) {
        // (it was empty, implemented by Pablo)

        this.time = time;
    }

    /**
     * Returns the time (in ticks) at which this event will occur.
     */
    @Override
    public double time() {
        // (it was empty, implemented by Pablo)
        return time;
    }

    /**
     * Compares this object with the specified Event.
     */
    @Override
    public int compareTo(Event that) {
        // (it was empty, implemented by Pablo)

        if (this.time() > that.time()) {
            return 1;
        }
        else if (this.time() < that.time()){
            return -1;
        }
        return 0;
    }

}
