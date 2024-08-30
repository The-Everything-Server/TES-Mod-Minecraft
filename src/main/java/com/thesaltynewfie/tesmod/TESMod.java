package com.thesaltynewfie.tesmod;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.thesaltynewfie.tesmod.commands.DebugCommand;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import com.thesaltynewfie.tesmod.commands.Gift;
import com.thesaltynewfie.tesmod.global.global;
import com.thesaltynewfie.tesmod.commands.DiscordMessageCommand;

public class TESMod implements ModInitializer {
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(CommandManager.literal("gift")
					.executes(Gift::GiftCommand));

			dispatcher.register(CommandManager.literal("discord")
					.then(CommandManager.argument("message", StringArgumentType.string())
							.then(CommandManager.argument("channel_id", StringArgumentType.string())
									.executes(context -> DiscordMessageCommand.MessageCommand(
											StringArgumentType.getString(context, "message"),
											StringArgumentType.getString(context, "channel_id"),
											context)))));

			dispatcher.register(CommandManager.literal("debug")
					.executes(DebugCommand::DebugCommand));
		});

		global.LOGGER.info("From global");
	}
}