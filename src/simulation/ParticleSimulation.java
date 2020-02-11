package simulation;

import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;

import utils.MinPriorityQueue;

public class ParticleSimulation implements Runnable, ParticleEventHandler {

  private static final long FRAME_INTERVAL_MILLIS = 40;

  private final ParticlesModel model;
  private final ParticlesView screen;
  private double currentTime;
  private MinPriorityQueue<Event> queue;

  /**
   * Constructor.
   */
  public ParticleSimulation(String name, ParticlesModel m) {
    // Constructor implemented by Yura
    model = m;
    screen = new ParticlesView(name, m);

    // Initialize a new queue of events
    queue = new MinPriorityQueue<>();

    // Add one tick as the first event
    queue.add(new Tick(1));

    // Add predicted collision events for all particles
    // in their initial state
    for (Collision c : m.predictAllCollisions(1)) {
      queue.add(c);
    }
  }

  /**
   * Runs the simulation.
   */
  @Override
  public void run() {
    try {
      SwingUtilities.invokeAndWait(screen);
    } catch (InvocationTargetException | InterruptedException e) {
      e.printStackTrace();
    }

    // Implemented by Yura
    // Main operation of the simulation
    Event currentEvent = queue.remove();
    while (currentEvent != null) {
      if (currentEvent.isValid()) {
        currentTime = currentEvent.time();
        currentEvent.happen(this);
      }
      currentEvent = queue.remove();
    }
  }

  @Override
  public void reactTo(Tick tick) {
    // Implemented by Yura
    if (tick.time() < currentTime) {
      try {
        Thread.sleep(FRAME_INTERVAL_MILLIS);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      screen.run();
      queue.add(new Tick(System.currentTimeMillis()));
    }
  }

  // TODO: finish this method (Yura)
  @Override
  public void reactTo(Collision c) {
    // Implemented by Yura
    if(c.time() < currentTime){
      // c.getParticles() will get you all particles involved in this collision
      // how can we add the new collisions to the queue for the particles that just collided?
    }
  }
}
