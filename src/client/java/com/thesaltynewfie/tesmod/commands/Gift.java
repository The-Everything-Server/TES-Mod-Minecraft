package com.thesaltynewfie.tesmod.commands;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mojang.brigadier.context.CommandContext;
import com.thesaltynewfie.tesmod.network.Network;
import com.thesaltynewfie.tesmod.Types.GiftItem;
import com.thesaltynewfie.tesmod.Types.Response;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.command.ServerCommandSource;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesaltynewfie.tesmod.global.global;
import net.minecraft.util.Identifier;

import java.util.List;

public class Gift {
    public static int GiftCommand(String code, CommandContext<ServerCommandSource> ctx) {
        try {
            Response result = Network.Get("http://dev.thesaltynewfie.ca/api/gift/minecraft/" + code);

            ObjectMapper objectMapper = new ObjectMapper();
            List<GiftItem> order = objectMapper.readValue(result.getBody(), new TypeReference<List<GiftItem>>() {});

            for(GiftItem item : order) {
                String ItemID = item.getItemId();
                int amount = item.getQuantity();
                ctx.getSource().getPlayer().giveItemStack(new ItemStack(Registries.ITEM.get(Identifier.tryParse(ItemID)), amount));
            }
        } catch (Exception e) {
            global.LOGGER.error(e.toString());
        }
        return 1;
    }
}
