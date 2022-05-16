package com.rappytv.prevtb.main;

import com.rappytv.prevtb.events.ChatEvent;
import com.rappytv.prevtb.events.OnTickEvent;
import com.rappytv.prevtb.modules.DurabilityModule;
import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.*;
import net.labymod.utils.Consumer;
import net.labymod.utils.Material;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class Main extends LabyModAddon {

    public static boolean enabled = true;
    public static boolean debug = false;
    public static String warnMsg = "\u00A7cDo you really want to continue using this tool? Its durability is {durability}%!";
    public static int warnPercentagePickaxe = 5;
    public static int warnPercentageAxe = 5;
    public static int warnPercentageShovel = 5;
    public static Main instance;

    @Override
    public void onEnable() {
        // Sets the instance
        instance = this;

        getApi().getEventManager().register(new ChatEvent());
        getApi().registerForgeListener(new OnTickEvent());
        getApi().registerModule(new DurabilityModule());
    }

    @Override
    public void loadConfig() {
        enabled = !getConfig().has("warn.enabled") || getConfig().get("warn.enabled").getAsBoolean();
        debug = getConfig().has("warn.debug") ? getConfig().get("warn.debug").getAsBoolean() : debug;
        warnMsg = getConfig().has("warn.warnmsg") ? getConfig().get("warn.warnmsg").getAsString() : warnMsg;

        warnPercentagePickaxe = getConfig().has("warn.pick") ? getConfig().get("warn.pick").getAsInt() : warnPercentagePickaxe;
        warnPercentageAxe = getConfig().has("warn.axe") ? getConfig().get("warn.axe").getAsInt() : warnPercentageAxe;
        warnPercentageShovel = getConfig().has("warn.shovel") ? getConfig().get("warn.shovel").getAsInt() : warnPercentageShovel;
    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {
        BooleanElement enabledBool = new BooleanElement("Enabled", new ControlElement.IconData(Material.LEVER), new Consumer<Boolean>() {

            @Override
            public void accept(Boolean accepted) {
                enabled = accepted;

                getConfig().addProperty("warn.enabled", enabled);
                saveConfig();
            }
        }, enabled);

        BooleanElement debugBool = new BooleanElement("Debug Mode (not recommended)", new ControlElement.IconData(new ResourceLocation("prevtb/textures/matrix.png")), new Consumer<Boolean>() {

            @Override
            public void accept(Boolean accepted) {
                debug = accepted;

                getConfig().addProperty("warn.debug", debug);
                saveConfig();
            }
        }, debug);

        StringElement warnmsg = new StringElement("Warn Message", new ControlElement.IconData(Material.REDSTONE_TORCH_ON), warnMsg, new Consumer<String>() {

            @Override
            public void accept(String accepted) {
                warnMsg = accepted;

                getConfig().addProperty("warn.msg", warnMsg);
                saveConfig();
            }
        });

        SliderElement pickSlider = new SliderElement("Pickaxe Warn Percentage",
                new ControlElement.IconData(Material.IRON_PICKAXE), warnPercentagePickaxe);
        SliderElement axeSlider = new SliderElement( "Axe Warn Percentage",
                new ControlElement.IconData(Material.IRON_AXE), warnPercentageAxe);
        SliderElement spadeSlider = new SliderElement( "Shovel Warn Percentage",
                new ControlElement.IconData(Material.IRON_SPADE), warnPercentageShovel);

        {
            // Setting the slider's min & max values
            pickSlider.setRange(1, 25);

            // Setting slider steps
            pickSlider.setSteps(1);

            // Adding change listener
            pickSlider.addCallback(new Consumer<Integer>() {

                @Override
                public void accept(Integer accepted) {
                    warnPercentagePickaxe = accepted;

                    getConfig().addProperty("warn.pick", warnPercentagePickaxe);
                    saveConfig();
                }
            });
        }

        {
            // Setting the slider's min & max values
            axeSlider.setRange(1, 25);

            // Setting slider steps
            axeSlider.setSteps(1);

            // Adding change listener
            axeSlider.addCallback(new Consumer<Integer>() {

                @Override
                public void accept(Integer accepted) {
                    warnPercentageAxe = accepted;

                    getConfig().addProperty("warn.axe", warnPercentageAxe);
                    saveConfig();
                }
            });
        }

        {
            // Setting the slider's min & max values
            spadeSlider.setRange(1, 25);

            // Setting slider steps
            spadeSlider.setSteps(1);

            // Adding change listener
            spadeSlider.addCallback(new Consumer<Integer>() {

                @Override
                public void accept(Integer accepted) {
                    warnPercentageShovel = accepted;

                    getConfig().addProperty("warn.shovel", warnPercentageShovel);
                    saveConfig();
                }
            });
        }

        // Adding setting
        list.add(enabledBool);
        list.add(debugBool);
        list.add(warnmsg);
        list.add(pickSlider);
        list.add(axeSlider);
        list.add(spadeSlider);
    }

    public static Main getMain() {
        return instance;
    }
}
