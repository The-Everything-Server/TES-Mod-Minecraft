package com.thesaltynewfie.tesmod.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mojang.brigadier.context.CommandContext;
import com.thesaltynewfie.tesmod.commands.utils.Message;
import com.thesaltynewfie.tesmod.network.Network;
import com.thesaltynewfie.tesmod.Types.DiscordMessage;
import com.thesaltynewfie.tesmod.Types.Response;
import net.minecraft.server.command.ServerCommandSource;

public class DiscordMessageCommand {
    public static int MessageCommand(String message, String channel_id, CommandContext<ServerCommandSource> ctx) {
        try {
            DiscordMessage msg = new DiscordMessage();
            ObjectMapper objectMapper = new ObjectMapper();

            String name = ctx.getSource().getPlayer().getName().getLiteralString();
            String _msg = name + ": " + message;

            msg.setMessage(_msg);
            msg.setChannelId(channel_id);

            String json = objectMapper.writeValueAsString(msg);

            Message.gamePrint(ctx, "Json: " + json);

            Response resp = Network.Post("http://dev.thesaltynewfie.ca/api/discord/message", json);

            Message.gamePrint(ctx, "Result: " + resp.getBody());

            return 1;
        } catch (Exception e) {
            Message.gamePrint(ctx, "Encountered an error: " + e.toString());
        }
        return 0;
    }
}
