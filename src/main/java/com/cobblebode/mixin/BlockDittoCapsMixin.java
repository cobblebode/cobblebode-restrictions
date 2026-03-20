package com.cobblebode.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = {
        "com.gmail.brendonlf.cobblemon_utility.Item.GoldenBottleCapItem",
        "com.gmail.brendonlf.cobblemon_utility.Item.AtkSilverBottleCapItem",
        "com.gmail.brendonlf.cobblemon_utility.Item.SpAtkSilverBottleCapItem",
        "com.gmail.brendonlf.cobblemon_utility.Item.SpDefSilverBottleCapItem",
        "com.gmail.brendonlf.cobblemon_utility.Item.SpeedSilverBottleCapItem",
        "com.gmail.brendonlf.cobblemon_utility.Item.DefSilverBottleCapItem",
        "com.gmail.brendonlf.cobblemon_utility.Item.HpSilverBottleCapItem",
        "com.gmail.brendonlf.cobblemon_utility.Item.ShiningAtkSilverBottleCapItem",
        "com.gmail.brendonlf.cobblemon_utility.Item.ShiningSpAtkSilverBottleCapItem",
        "com.gmail.brendonlf.cobblemon_utility.Item.ShiningSpDefSilverBottleCapItem",
        "com.gmail.brendonlf.cobblemon_utility.Item.ShiningSpeedSilverBottleCapItem",
        "com.gmail.brendonlf.cobblemon_utility.Item.ShiningDefSilverBottleCapItem",
        "com.gmail.brendonlf.cobblemon_utility.Item.ShiningHPSilverBottleCapItem",
        "com.gmail.brendonlf.cobblemon_utility.Item.WoodenBottleCapItem"
})
public class BlockDittoCapsMixin {

    @Inject(method = "method_7847", at = @At("HEAD"), cancellable = true, remap = false)
    private void cobblebode$blockDittoCaps(
            ItemStack stack,
            Player player,
            LivingEntity entity,
            InteractionHand hand,
            CallbackInfoReturnable<InteractionResult> cir
    ) {
        try {
            if (player == null || entity == null) {
                return;
            }

            if (player.level().isClientSide()) {
                return;
            }

            if (!entity.getClass().getName().equals("com.cobblemon.mod.common.entity.pokemon.PokemonEntity")) {
                return;
            }

            Object pokemon = entity.getClass().getMethod("getPokemon").invoke(entity);
            if (pokemon == null) {
                return;
            }

            Object species = pokemon.getClass().getMethod("getSpecies").invoke(pokemon);
            if (species == null) {
                return;
            }

            Object nameObj = species.getClass().getMethod("getName").invoke(species);
            if (!(nameObj instanceof String speciesName)) {
                return;
            }

            if (!speciesName.equalsIgnoreCase("ditto")) {
                return;
            }

            player.sendSystemMessage(Component.literal("§cNão é possível usar esse item nesse pokémon."));
            cir.setReturnValue(InteractionResult.FAIL);
        } catch (Throwable ignored) {
            // Se algo falhar, deixa seguir normalmente.
        }
    }
}
