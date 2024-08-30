package com.thesaltynewfie.tesmod.commands;

import com.mojang.brigadier.context.CommandContext;
import com.thesaltynewfie.tesmod.network.Network;
import com.thesaltynewfie.tesmod.network.Types.Order;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import com.thesaltynewfie.tesmod.commands.utils.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesaltynewfie.tesmod.global.global;

public class Gift {
    public static int GiftCommand(CommandContext<ServerCommandSource> ctx) {
        try {
            String result = Network.Get("http://192.168.4.123:3000/api/minecraft/gift");

            ObjectMapper objectMapper = new ObjectMapper();
            Order order = objectMapper.readValue(result, Order.class);

            for(int i = 0; i < order.getItems().size(); i++) {
                Message.gamePrint(ctx, "i: " + i);
                int ItemID = order.getItems().get(i).getItemId();
                int amount = order.getItems().get(i).getQuantity();
                ItemStack stack = new ItemStack(Item.byRawId(ItemID), amount);
                ctx.getSource().getPlayer().giveItemStack(stack);
            }

        } catch (Exception e) {
            ctx.getSource().sendFeedback(() -> Text.literal("Encountered an error."), false);
            global.LOGGER.error(e.toString());
        }
        return 1;
    }
}
