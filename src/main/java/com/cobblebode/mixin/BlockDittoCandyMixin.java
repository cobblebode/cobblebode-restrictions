package com.cobblebode.mixin;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@Mixin(Item.class)
public class BlockDittoCandyMixin {

    private static final Set<String> BLOCKED_CANDIES = Set.of(
            "cobblemon:health_candy",
            "cobblemon:mighty_candy",
            "cobblemon:tough_candy",
            "cobblemon:smart_candy",
            "cobblemon:courage_candy",
            "cobblemon:quick_candy"
    );

    @Inject(method = "method_7847", at = @At("HEAD"), cancellable = true, remap = false)
    private void cobblebode$blockDittoCandies(
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

            String itemId = BuiltInRegistries.ITEM.getKey(stack.getItem()).toString();
            if (!BLOCKED_CANDIES.contains(itemId)) {
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
            cir.cancel();
        } catch (Throwable ignored) {
            // Se algo falhar, deixa seguir normalmente.
        }
    }
}
