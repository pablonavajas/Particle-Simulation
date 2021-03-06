package simulation;

public class ParticleWallCollision extends Collision {

  private final Particle particle;
  private final Wall wall;

  ParticleWallCollision(Particle ps, Wall w, double t) {

    super(t, new Particle[]{ps});
    this.particle = ps;
    this.wall = w;
  }

  @Override
  public void happen(ParticleEventHandler h) {
    Particle.collide(particle, wall);
    h.reactTo(this);
  }
}
