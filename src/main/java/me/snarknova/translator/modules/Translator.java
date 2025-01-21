package me.snarknova.translator.modules;

import me.snarknova.translator.AddonTemplate;
import me.snarknova.translator.utils.TranslateUtil;
import me.snarknova.translator.utils.AsyncUtil;
import meteordevelopment.meteorclient.events.game.ReceiveMessageEvent;
import meteordevelopment.meteorclient.utils.player.ChatUtils;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.text.Text;

import java.net.MalformedURLException;

public class Translator extends Module {
    public Translator() {
        super(AddonTemplate.CATEGORY, "Translator", "Test");
    }

    @EventHandler
    private void onMessageReceive(ReceiveMessageEvent event) {
        String message = String.valueOf(event.getMessage().getString());
        if (message.contains("TRANSLATED")) { return; }
        else {
            AsyncUtil.run(() -> {
                try {
                    ChatUtils.sendMsg(Text.of(TranslateUtil.translate(message, "ko")+" TRANSLATED"));
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
