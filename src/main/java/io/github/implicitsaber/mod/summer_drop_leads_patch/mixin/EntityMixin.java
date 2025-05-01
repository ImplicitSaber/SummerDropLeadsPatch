package io.github.implicitsaber.mod.summer_drop_leads_patch.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.Leashable;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow public abstract boolean isLogicalSideForUpdatingMovement();

    @Shadow private World world;

    @Inject(method = "baseTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiler/Profiler;pop()V"))
    private void summer_drop_leads_patch$patch(CallbackInfo ci) {
        Entity e = (Entity) (Object) this;
        if(this.world.isClient() && e instanceof Leashable l && this.isLogicalSideForUpdatingMovement()) {
            Leashable.LeashData data = l.getLeashData();
            Entity holder = l.getLeashHolder();
            if(data != null && holder != null && e.getWorld() == holder.getWorld()) {
                double dist = l.getDistanceToCenter(holder);
                if(dist <= l.getLeashSnappingDistance()) {
                    if(dist > l.getElasticLeashDistance() - holder.getWidth() - e.getWidth()) l.applyElasticity(holder, data);
                    e.setYaw((float) (e.getYaw() - data.momentum));
                    data.momentum *= Leashable.getSlipperiness((Entity & Leashable) e);
                }
            }
        }
    }

}
