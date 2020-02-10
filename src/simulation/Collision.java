package simulation;

public abstract class Collision extends AbstractEvent{

    // Hey Iura and Dave! I have added the comments just so you know what I modified
    // (no internal variables given, created by Pablo)
    private final Particle[] particles;

    // Internal variables to keep track of nº of collisions upon creation
    private final int countA;
    private final int countB;

    /**
     * Constructor for Collision
     */
    public Collision(double t, Particle[] ps) {
        // (it was empty, implemented by Pablo)

        super(t);
        this.particles = ps;

        // Initialise variables to keep track of nº of collisions upon creation
        countA = ps[0].collisions();
        // There can only be one particle involved in the collision
        if (ps[1] != null) {
            countB = ps[1].collisions();
        }
        else {
            countB = -1;
        }
    }

    /**
     * Returns true if this Collision is (still) valid.
     * inspired by :
     * https://github.com/fracpete/princeton-java-algorithms/blob/master/src/main/java/edu/princeton/cs/algorithms/CollisionSystem.java
     */
    @Override
    public boolean isValid() {
        // (it was empty, implemented by Pablo)
        // Check if there have been any collisions after creation of event

        if (particles[0].collisions() != countA) {
            return false;
        }
        if (particles[1] != null && particles[1].collisions() != countB) {
            return false;
        }
        return true;
    }

    /**
     * Returns an array containing the Particles involved in this Collision.
     */
    public Particle[] getParticles() {
        // (it was empty, implemented by Pablo)
        return particles;
    }
}
