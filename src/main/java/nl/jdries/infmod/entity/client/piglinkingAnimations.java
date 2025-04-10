package nl.jdries.infmod.entity.client;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class piglinkingAnimations {
    public static final AnimationDefinition ANIMATION1 = AnimationDefinition.Builder.withLength(1f).looping()
            .addAnimation("bone",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 0f, 180f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 360f),
                                    AnimationChannel.Interpolations.LINEAR))).build();
}
