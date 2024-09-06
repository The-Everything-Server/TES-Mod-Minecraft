package com.thesaltynewfie.tesmod.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mojang.brigadier.context.CommandContext;
import com.thesaltynewfie.tesmod.commands.utils.Message;
import com.thesaltynewfie.tesmod.config.ConfigHelper;
import com.thesaltynewfie.tesmod.global.global;
import com.thesaltynewfie.tesmod.Types.Response;
import com.thesaltynewfie.tesmod.Types.Token;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.command.ServerCommandSource;
import com.thesaltynewfie.tesmod.network.Network;
import com.thesaltynewfie.tesmod.Types.Auth;

import java.io.File;

public class AuthCommand {
    public static int AuthCommand(String username, String password, CommandContext<ServerCommandSource> ctx) {
        Auth auth = new Auth();
        ObjectMapper mapper = new ObjectMapper();

        auth.setUsername(username);
        auth.setPassword(password);

        try {
            Response resp = Network.Post("http://dev.thesaltynewfie.ca/api/auth/", mapper.writeValueAsString(auth));

            Token token = new Token();
            token = mapper.readValue(resp.getBody(), Token.class);

            File configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), "tesmod.ini");
            ConfigHelper conf = new ConfigHelper(configFile);

            conf.setValue("api", "token", token.getToken());

            Message.gamePrint(ctx, token.getToken());
        } catch(Exception e) {
            global.LOGGER.error(e.toString());
        }
        return 1;
    }
}
