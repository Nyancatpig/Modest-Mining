package com.ncpbails.modestmining.item.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class WeatheredCopperBowItem extends BowItem {

    public WeatheredCopperBowItem(Properties builder) {
        super(builder);
    }

    public void releaseUsing(ItemStack bowStack, Level worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof Player) {
            Player Player = (Player)entityLiving;
            boolean hasInfinity = Player.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, bowStack) > 0;
            ItemStack ammoStack = Player.getProjectile(bowStack);

            int timeDrawn = this.getUseDuration(bowStack) - timeLeft;
            timeDrawn = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(bowStack, worldIn, Player, timeDrawn, !ammoStack.isEmpty() || hasInfinity);
            if (timeDrawn < 0) return;

            if (!ammoStack.isEmpty() || hasInfinity) {
                boolean isTippedArrow = ammoStack.getItem() == Items.SPECTRAL_ARROW || ammoStack.getItem() == Items.TIPPED_ARROW;

                if (ammoStack.isEmpty()) {
                    ammoStack = new ItemStack(Items.ARROW);
                }

                float velocity = getPowerForTime(timeDrawn);
                if (!((double)velocity < 0.1D)) {
                    if (!worldIn.isClientSide) {
                        AbstractArrow arrowEntity = createArrow(worldIn, ammoStack, Player);

                        arrowEntity.shootFromRotation(Player, Player.getXRot(), Player.getYRot(), 0.0F, velocity * 3.0F, 1.0F);

                        if (velocity == 1.0F) arrowEntity.setCritArrow(true);

                        double damage = getArrowDamage(bowStack, arrowEntity);
                        arrowEntity.setBaseDamage(damage*1.6);

                        int knockback = getArrowKnockback(bowStack, arrowEntity);
                        arrowEntity.setKnockback(knockback);

                        // apply flame enchant
                        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, bowStack) > 0) {
                            arrowEntity.setSecondsOnFire(100);
                        }

                        // reduce bow durability
                        bowStack.hurtAndBreak(1, Player, (p_220009_1_) -> {
                            p_220009_1_.broadcastBreakEvent(Player.getUsedItemHand());
                        });

                        // set if arrow can be picked up from ground
                        if (hasInfinity && !isTippedArrow) {
                            arrowEntity.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                        }

                        // actually make the arrow entity exist in the world
                        worldIn.addFreshEntity(arrowEntity);
                    }

                    // sound
                    worldIn.playSound((Player)null, Player.getX(), Player.getY(), Player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (worldIn.getRandom().nextFloat() * 0.4F + 1.2F) + velocity * 0.5F);

                    // use an arrow
                    boolean shouldConsumeArrow = !hasInfinity || isTippedArrow;
                    if (shouldConsumeArrow) {
                        ammoStack.shrink(1);
                        if (ammoStack.isEmpty()) {
                            Player.getInventory().removeItem(ammoStack);
                        }
                    }

                    Player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }



    //override to use a custom arrow entity
    protected AbstractArrow createArrow(Level worldIn, ItemStack ammoStack, Player playerentity) {
        ArrowItem arrowitem = (ArrowItem)(ammoStack.getItem() instanceof ArrowItem ? ammoStack.getItem() : Items.ARROW);
        return arrowitem.createArrow(worldIn, ammoStack, playerentity);
    }

    protected double getArrowDamage(ItemStack bowStack, AbstractArrow arrowEntity) {
        int powerLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, bowStack);

        if (powerLevel > 0) return arrowEntity.getBaseDamage() + (double)powerLevel * 0.5D + 0.5D;
        else return arrowEntity.getBaseDamage();
    }

    protected int getArrowKnockback(ItemStack bowStack, AbstractArrow arrowEntity) {
        return EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, bowStack);
    }

    // Override to change what it uses as ammo
    //public Predicate<ItemStack> getInventoryAmmoPredicate() {
    //    return (ammoStack) -> {
    //        return ammoStack.getItem().isIn(ItemTags.ARROWS);
    //    };
    //}
}