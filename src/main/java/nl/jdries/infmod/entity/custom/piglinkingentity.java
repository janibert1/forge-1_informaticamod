package nl.jdries.infmod.entity.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class piglinkingentity extends Monster {

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;


    // Timers for abilities.
    private int fireballTimer = 20;          // Fireball every second (20 ticks)
    private int blazeSpawnDuration = 0;      // Duration remaining for blaze spawn phase (400 ticks = 20 seconds)
    private int blazeSpawnIntervalTimer = 0; // Timer for next blaze spawn (every 2 seconds or 40 ticks)

    public piglinkingentity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return AgeableMob.createLivingAttributes()
                .add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.MOVEMENT_SPEED, 0.23F)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ARMOR, 2.0)
                .add(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE, 1.0);

    }

    @Override
    protected void registerGoals() {
        // Example goal: Random strolling (you can add additional goals as needed)
        this.goalSelector.addGoal(0, new RandomStrollGoal(this, 1));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 2, false));

        // Targeting players
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 40;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }

        // Only execute abilities on the server side and when there is a target.
        if (!this.level().isClientSide() && this.getTarget() != null) {
            // --- FIREBALL SHOOTING ---
            fireballTimer--;
            if (fireballTimer <= 0) {
                shootFireballAtTarget();
                fireballTimer = 20; // Reset for 1 second intervals.
            }

            // --- BLAZE SPAWNING ---
            if (blazeSpawnDuration <= 0) {
                // Start a new blaze spawn phase lasting 20 seconds.
                blazeSpawnDuration = 400;      // 20 seconds * 20 ticks per second.
                blazeSpawnIntervalTimer = 40;  // Spawn a blaze every 2 seconds.
            } else {
                blazeSpawnDuration--;
                blazeSpawnIntervalTimer--;
                if (blazeSpawnIntervalTimer <= 0) {
                    spawnBlaze();
                    blazeSpawnIntervalTimer = 40; // Reset interval.
                }
            }
        }
    }

    private void shootFireballAtTarget() {
        if (this.getTarget() == null) {
            return;
        }
        // Calculate firing direction toward the target.
        Vec3 targetVec = this.getTarget().position().add(0, this.getTarget().getEyeHeight() * 0.5, 0);
        Vec3 bossVec = this.position().add(0, this.getEyeHeight(), 0);
        Vec3 direction = targetVec.subtract(bossVec).normalize();

        // Create and launch the fireball.
        LargeFireball fireball = new LargeFireball(this.level(), this, direction, 2);
        fireball.moveTo(this.getX(), this.getEyeY(), this.getZ(), 0, 0);
        this.level().addFreshEntity(fireball);
    }

    private void spawnBlaze() {
        // Random offset within a 4-block radius.
        double offsetX = (Math.random() - 0.5) * 4;
        double offsetZ = (Math.random() - 0.5) * 4;
        Blaze blaze = new Blaze(EntityType.BLAZE, this.level());
        blaze.moveTo(this.getX() + offsetX, this.getY(), this.getZ() + offsetZ, 0, 0);
        this.level().addFreshEntity(blaze);
    }
    @Override
    public boolean isOnFire() {
        return false;
    }

   @Override
   public boolean fireImmune(){
       return true;}
    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.getEntity() instanceof Blaze || source.getEntity() instanceof LargeFireball || source.getEntity() instanceof piglinkingentity){
            // Reduce the explosion damage by half, for example
            amount *= 0;
        }
        return super.hurt(source, amount);
    }


}
