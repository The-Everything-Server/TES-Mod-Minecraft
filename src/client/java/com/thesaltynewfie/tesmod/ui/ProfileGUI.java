package com.thesaltynewfie.tesmod.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesaltynewfie.tesmod.commands.utils.Message;
import com.thesaltynewfie.tesmod.config.ConfigHelper;
import com.thesaltynewfie.tesmod.global.global;
import com.thesaltynewfie.tesmod.network.Types.*;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import com.thesaltynewfie.tesmod.network.Network;

import java.io.File;

public class ProfileGUI extends LightweightGuiDescription {
    public ProfileGUI() {
        Response result = new Response();
        Profile profile = new Profile();
        ObjectMapper mapper = new ObjectMapper();
        boolean serverError = false;
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(256, 240);
        root.setInsets(Insets.ROOT_PANEL);

        // grab here so we can make an error screen if the server is not connecting
        try {
            result = Network.Get("http://192.168.4.123:3000/api/me");
            profile = mapper.readValue(result.getBody(), Profile.class);
        } catch (Exception e) {
            global.LOGGER.error(e.toString());
        }

        if(result.getStatusCode() != 401) {
            WLabel label = new WLabel(Text.literal("Welcome back, " + profile.getUsername()), 0x000000);
            root.add(label, 0, 0, 2, 1);

            WLabel label2 = new WLabel(Text.literal("Currency: " + profile.getCurrency()), 0x000000);
            root.add(label2, 0, 1, 2, 1);

            WButton button = new WButton(Text.literal("Gifts"));
            root.add(button, 0, 12, 4, 1);

            WButton tradeBtn = new WButton(Text.literal("Trade"));
            root.add(tradeBtn, 5, 12, 4, 1);

            tradeBtn.setOnClick(() -> {
                handleTrade();
            });
        } else {
            WLabel label = new WLabel(Text.literal("Please sign in"), 0x000000);

            WTextField usernameField = new WTextField();
            WLabel usernameLabel = new WLabel(Text.literal("Username: "), 0x000000);

            WTextField passwordField = new WTextField();
            WLabel passwordLabel = new WLabel(Text.literal("Password: "), 0x000000);

            WButton confirm = new WButton(Text.literal("Login"));

            confirm.setOnClick(() -> {
                handleLogin(usernameField.getText(), passwordField.getText());
                WLabel finishLabel = new WLabel(Text.literal("Close this menu and re open!"));
                root.add(finishLabel, 0,6);
            });

            root.add(label, 0, 0, 2, 1);
            root.add(usernameField, 4,1,4,1);
            root.add(usernameLabel, 0, 1);
            root.add(passwordField, 4,2,4,1);
            root.add(passwordLabel, 0, 2);
            root.add(confirm, 0,4,4,1);
        }
        
        root.validate(this);
    }

    private void handleLogin(String username, String password) {
        Auth auth = new Auth();
        ObjectMapper mapper = new ObjectMapper();

        auth.setUsername(username);
        auth.setPassword(password);

        try {
            Response resp = Network.Post("http://192.168.4.123:3000/api/auth/", mapper.writeValueAsString(auth));

            Token token = new Token();
            token = mapper.readValue(resp.getBody(), Token.class);

            File configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), "tesmod.ini");
            ConfigHelper conf = new ConfigHelper(configFile);

            conf.setValue("api", "token", token.getToken());
        } catch(Exception e) {
            global.LOGGER.error(e.toString());
        }
    }

    private void handleGifts() {
        try {
            Response result = Network.Get("http://192.168.4.123:3000/api/minecraft/gift");

            ObjectMapper objectMapper = new ObjectMapper();
            Order order = objectMapper.readValue(result.getBody(), Order.class);

            for(int i = 0; i < order.getItems().size(); i++) {
                String ItemID = order.getItems().get(i).getItemId();
                int amount = order.getItems().get(i).getQuantity();
                ItemStack stack = Registries.ITEM.get(Identifier.tryParse(ItemID)).getDefaultStack();
                MinecraftClient.getInstance().player.getInventory().insertStack(new ItemStack(Registries.ITEM.get(Identifier.tryParse(ItemID)), amount));
            }

        } catch (Exception e) {
            global.LOGGER.error(e.toString());
        }
    }

    private void handleTrade() {
        // Everything in here is debug right now, cause what better place to test this right!
        ItemStack stack = MinecraftClient.getInstance().player.getInventory().getMainHandStack();

        ItemStack stack1 = Registries.ITEM.get(Identifier.tryParse("minecraft", "dirt")).getDefaultStack();

        MinecraftClient.getInstance().player.getInventory().insertStack(stack1);

        global.LOGGER.info(stack1.getTranslationKey());
    }
}
