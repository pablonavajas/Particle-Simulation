package simulation;

public class Tick extends AbstractEvent {
  // Fully created this class

  /**
   * Constructor for Tick
   */
  public Tick(double t) {
    // (it was empty, implemented by Pablo)
    super(t);
  }

  @Override
  public void happen(ParticleEventHandler h) {
    // As per spec: "the only thing it must do is make a callback to the ParticleEventHandler"
    h.reactTo(this);
    System.out.println("TICK HAPPENED");
  }

  @Override
  public boolean isValid() {
    // As per the spec: "a Tick is always valid"
    return true;
  }
}
