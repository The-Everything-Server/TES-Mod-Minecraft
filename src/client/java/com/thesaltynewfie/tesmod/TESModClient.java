package com.thesaltynewfie.tesmod;

import com.thesaltynewfie.tesmod.ui.ProfileGUI;
import com.thesaltynewfie.tesmod.ui.ProfileScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class TESModClient implements ClientModInitializer {
	private static KeyBinding keyBinding;

	@Override
	public void onInitializeClient() {
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