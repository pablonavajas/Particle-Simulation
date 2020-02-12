package simulation;

import java.lang.reflect.InvocationTargetException;
import java.security.cert.CollectionCertStoreParameters;
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

    //Initialize time
    currentTime = 1;

    // Initialize a new queue of events
    queue = new MinPriorityQueue<>();

    // Add one tick as the first event
    queue.add(new Tick(1));
//    System.out.println("Added the first Tick");


    // Add predicted collision events for all particles
    // in their initial state
    for (Collision c : m.predictAllCollisions(1)) {
      queue.add(c);
      //     System.out.println("Added element from constructor");
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

    screen.run();
    // Implemented by Yura
    // Main operation of the simulation
    Event currentEvent = queue.remove();

    //if there is an event in the queue
    while (currentEvent != null) {
      //if event is valid
      if (currentEvent instanceof Tick)
        System.out.println("IT IS AN INSTANCE OF TICK");

      if (currentEvent.isValid()) {
        System.out.println("Current Time: " + currentTime);
        double dt = currentEvent.time() - currentTime;
        currentTime = currentEvent.time();
        model.moveParticles(dt);
        System.out.println(currentTime);
        currentEvent.happen(this);
      }
      currentEvent = queue.remove();
    }
  }

  @Override
  public void reactTo(Tick tick) {
    // Implemented by Yura
    try {
      Thread.sleep(FRAME_INTERVAL_MILLIS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("This screen should update");
    screen.update();
    queue.add(new Tick(tick.time() + 1));
  }

  @Override
  public void reactTo(Collision c) {
    Particle[] particles = c.getParticles(); //returns ann array of two or one particles

    for (Collision c1 : model.predictCollisions(particles[0], currentTime)) {
      queue.add(c1);
    }

    if (particles.length > 1) {
      for (Collision c1 : model.predictCollisions(particles[1], currentTime)) {
        queue.add(c1);
      }
    }

    System.out.println("SOME COLLISIONS");

  }
}
