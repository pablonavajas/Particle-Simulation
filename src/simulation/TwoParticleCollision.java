package simulation;

public class TwoParticleCollision extends Collision {

  private final Particle particleA;
  private final Particle particleB;

  TwoParticleCollision(Particle psA, Particle psB, double t) {

    super(t, new Particle[]{psA, psB});
    this.particleA = psA;
    this.particleB = psB;
  }

  @Override
  public void happen(ParticleEventHandler h) {
    particleA.collide(particleA, particleB);
    h.reactTo(this);
  }
}
