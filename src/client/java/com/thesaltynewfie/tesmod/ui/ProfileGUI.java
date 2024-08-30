package com.thesaltynewfie.tesmod.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesaltynewfie.tesmod.global.global;
import com.thesaltynewfie.tesmod.network.Types.Profile;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WSprite;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import com.thesaltynewfie.tesmod.network.Network;

public class ProfileGUI extends LightweightGuiDescription {
    public ProfileGUI() {
        String result;
        Profile profile = new Profile();
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = Network.Get("http://192.168.4.123:3000/api/me");
            profile = mapper.readValue(result, Profile.class);

            global.LOGGER.info(result);

        } catch (Exception e) {
            global.LOGGER.error(e.toString());
        }
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(256, 240);
        root.setInsets(Insets.ROOT_PANEL);

        WLabel label = new WLabel(Text.literal("Welcome back, " + profile.getUsername()), 0x000000);
        root.add(label, 0, 1, 2, 1);

        WLabel label2 = new WLabel(Text.literal("Currency: " + profile.getCurrency()), 0x000000);
        root.add(label2, 0, 2, 2, 1);

        WButton button = new WButton(Text.literal("Gifts"));
        root.add(button, 0, 3, 4, 1);

        root.validate(this);
    }
}
