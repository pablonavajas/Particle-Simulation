package simulation;

public abstract class Collision extends AbstractEvent {

  private final Particle[] particles;

  private final int countA;
  private final int countB;

  /**
   * Constructor for Collision
   * Note: an array of maximum of two particles should be passed in
   */
  public Collision(double t, Particle[] ps) {

    super(t);
    this.particles = ps;

    countA = ps[0].collisions();

    if ((ps.length > 1)){
      countB = ps[1].collisions();
    } else {
      countB = -1;
    }
  }

  /**
   * Returns true if this Collision is (still) valid.
   */
  @Override
  public boolean isValid() {
    if (particles[0].collisions() != countA) {
      return false;
    }
    if ((particles.length > 1) && (particles[1].collisions() != countB)) {
      return false;
    }
    return true;
  }

  /**
   * Returns an array containing the Particles involved in this Collision.
   */
  public Particle[] getParticles() {
    return particles;
  }
}
