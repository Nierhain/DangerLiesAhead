package de.nierhain.danger.gui.particle;

        import net.minecraft.client.particle.Particle;
        import net.minecraft.world.World;

public class ParticleLevelUp extends Particle {
    public ParticleLevelUp(World world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        super(world, x, y ,z, velocityX, velocityY, velocityZ);

        final float ALPHA_VALUE = 0.99f;
        this.particleAlpha = ALPHA_VALUE;

        motionX = velocityX;
        motionY = velocityY;
        motionZ = velocityZ;


    }
}
