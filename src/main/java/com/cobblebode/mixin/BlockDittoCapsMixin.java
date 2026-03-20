package com.cobblebode.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
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

    @Inject(method = "useOnEntity", at = @At("HEAD"), cancellable = true)
    private void cobblebode$blockDittoCaps(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        try {
            if (player == null || entity == null) {
                return;
            }

            // Só bloqueia do lado do servidor
            if (player.getWorld().isClient()) {
                return;
            }

            // Verifica se a entidade alvo é um Pokémon do Cobblemon
            if (!entity.getClass().getName().equals("com.cobblemon.mod.common.entity.pokemon.PokemonEntity")) {
                return;
            }

            // pokemonEntity.getPokemon()
            Object pokemon = entity.getClass().getMethod("getPokemon").invoke(entity);
            if (pokemon == null) {
                return;
            }

            // pokemon.getSpecies().getName()
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

            player.sendMessage(Text.literal("§cNão é possível usar esse item nesse pokémon."), false);
            cir.setReturnValue(ActionResult.FAIL);
        } catch (Throwable ignored) {
            // Se algo falhar, deixa o item seguir normalmente.
        }
    }
}
