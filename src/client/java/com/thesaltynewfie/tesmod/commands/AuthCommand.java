package com.thesaltynewfie.tesmod.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mojang.brigadier.context.CommandContext;
import com.thesaltynewfie.tesmod.commands.utils.Message;
import com.thesaltynewfie.tesmod.global.global;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientCommandSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.command.ServerCommandSource;
import com.thesaltynewfie.tesmod.network.Network;
import com.thesaltynewfie.tesmod.network.Types.Auth;

public class AuthCommand {
    public static int AuthCommand(String username, String password, CommandContext<ServerCommandSource> ctx) {
        Auth auth = new Auth();
        ObjectMapper mapper = new ObjectMapper();

        auth.setUsername(username);
        auth.setPassword(password);

        try {
            String result = Network.Post("http://192.168.4.123:3000/api/auth/", mapper.writeValueAsString(auth));

            Message.gamePrint(ctx, result);
        } catch(Exception e) {
            global.LOGGER.error(e.toString());
        }
        return 1;
    }
}
