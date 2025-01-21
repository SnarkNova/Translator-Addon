package me.snarknova.translator;

import me.snarknova.translator.modules.Translator;
import com.mojang.logging.LogUtils;
import meteordevelopment.meteorclient.addons.GithubRepo;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import org.slf4j.Logger;

public class AddonTemplate extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();
    public static final Category CATEGORY = new Category("Translator");

    @Override
    public void onInitialize() {
        Modules.get().add(new Translator());
    }

    @Override
    public void onRegisterCategories() {
        Modules.registerCategory(CATEGORY);
    }

    @Override
    public String getPackage() {
        return "me.snarknova.translator";
    }

    @Override
    public GithubRepo getRepo() {
        return new GithubRepo("SnarkNova", "Translator-Addon");
    }
}
