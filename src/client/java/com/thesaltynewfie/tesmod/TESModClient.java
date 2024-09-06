package com.thesaltynewfie.tesmod;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.thesaltynewfie.tesmod.commands.AuthCommand;
import com.thesaltynewfie.tesmod.commands.DebugCommand;
import com.thesaltynewfie.tesmod.commands.DiscordMessageCommand;
import com.thesaltynewfie.tesmod.commands.Gift;
import com.thesaltynewfie.tesmod.ui.ProfileGUI;
import com.thesaltynewfie.tesmod.ui.ProfileScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.server.command.CommandManager;
import org.lwjgl.glfw.GLFW;

public class TESModClient implements ClientModInitializer {
	private static KeyBinding keyBinding;

	@Override
	public void onInitializeClient() {
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(CommandManager.literal("auth")
					.then(CommandManager.argument("username", StringArgumentType.string())
							.then(CommandManager.argument("password", StringArgumentType.string())
									.executes(context -> AuthCommand.AuthCommand(
											StringArgumentType.getString(context, "username"),
											StringArgumentType.getString(context, "password"),
											context
									)))));

			dispatcher.register(CommandManager.literal("gift")
					.then(CommandManager.argument("code", StringArgumentType.string())
							.executes(context -> Gift.GiftCommand(
									StringArgumentType.getString(context, "code"),
									context
							))));

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

		keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.tesmod.profile-key",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_P,
				"category.tes-mod.profile"
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (keyBinding.wasPressed()) {
				MinecraftClient.getInstance().setScreen(new ProfileScreen(new ProfileGUI()));
			}
		});
	}
}