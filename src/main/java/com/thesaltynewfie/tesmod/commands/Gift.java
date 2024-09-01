package com.thesaltynewfie.tesmod.commands;

import com.mojang.brigadier.context.CommandContext;
import com.thesaltynewfie.tesmod.network.Network;
import com.thesaltynewfie.tesmod.network.Types.Order;
import com.thesaltynewfie.tesmod.network.Types.Response;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import com.thesaltynewfie.tesmod.commands.utils.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesaltynewfie.tesmod.global.global;
import net.minecraft.util.Identifier;

public class Gift {
    public static int GiftCommand(CommandContext<ServerCommandSource> ctx) {
        try {
            Response result = Network.Get("http://192.168.4.123:3000/api/minecraft/gift");

            ObjectMapper objectMapper = new ObjectMapper();
            Order order = objectMapper.readValue(result.getBody(), Order.class);

            for(int i = 0; i < order.getItems().size(); i++) {
                String ItemID = order.getItems().get(i).getItemId();
                int amount = order.getItems().get(i).getQuantity();
                ctx.getSource().getPlayer().giveItemStack(new ItemStack(Registries.ITEM.get(Identifier.tryParse(ItemID)), amount));
            }
        } catch (Exception e) {
            global.LOGGER.error(e.toString());
        }
        return 1;
    }
}
