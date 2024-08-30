package com.thesaltynewfie.tesmod.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mojang.brigadier.context.CommandContext;
import com.thesaltynewfie.tesmod.commands.utils.Message;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registry;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.Identifier;

public class DebugCommand {

    public static int DebugCommand(CommandContext<ServerCommandSource> ctx) {

        return 1;
    }
}
