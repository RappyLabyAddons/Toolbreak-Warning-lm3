package com.rappytv.prevtb.events;

import com.rappytv.prevtb.main.Main;
import com.rappytv.prevtb.util.Util;
import net.labymod.api.events.MessageSendEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ChatEvent implements MessageSendEvent {

    @Override
    public boolean onSend(String s) {
        String[] args = s.split(" ");
        if(args[0].equalsIgnoreCase("/ptbreak")) {
            if(args.length < 2) {
                Util.msg("\u00A7cPlease provide a valid argument! Args: enable, disable, debug");
                return true;
            }
            if(args[1].equalsIgnoreCase("enable")) {
                Main.enabled = true;

                Main.getMain().getConfig().addProperty("warn.enabled", Main.enabled);
                Main.getMain().saveConfig();
                Util.msg("\u00A7eAddon successfully \u00A7aenabled\u00A7e!");
                return true;
            } else if(args[1].equalsIgnoreCase("disable")) {
                Main.enabled = false;

                Main.getMain().getConfig().addProperty("warn.enabled", Main.enabled);
                Main.getMain().saveConfig();
                Util.msg("\u00A7eAddon successfully \u00A7cdisabled\u00A7e!");
                return true;
            } else if(args[1].equalsIgnoreCase("debug")) {
                if(args.length >= 3) {
                    if(args[2].equalsIgnoreCase("on")) {
                        Main.enabled = true;
                        Main.debug = true;

                        Main.getMain().getConfig().addProperty("warn.debug", Main.debug);
                        Main.getMain().saveConfig();
                        Util.msg("\u00A72Debug mode \u00A7esuccessfully \u00A7aenabled\u00A7e!");
                    } else if(args[2].equalsIgnoreCase("once")) {
                        EntityPlayer p = Minecraft.getMinecraft().player;
                        ItemStack itemStack = p.getHeldItemMainhand();
                        Item i = itemStack.getItem();
                        Util.msg("\u00A72Debug mode \u00A7esuccessfully \u00A7aenabled \u00A7eonce!");

                        if(Util.isPickaxe(itemStack)) {
                            int itemWarnInt = (Main.warnPercentagePickaxe * i.getMaxDamage(itemStack)) / 100;
                            int itemUsedInt = i.getMaxDamage(itemStack) - i.getDamage(itemStack);

                            Util.msg("\u00A7c\u00A7l------ Event triggered ------\n\u00A7eEvent: \u00A74Pickaxe in main hand\n\u00A7eTool used: \u00A74" + itemUsedInt + "\n\u00A7eTool warn: \u00A74" + itemWarnInt + "\n\u00A7c\u00A7l---------------------------");
                        } else if(Util.isAxe(itemStack)) {
                            int itemWarnInt = (Main.warnPercentageAxe * i.getMaxDamage(itemStack)) / 100;
                            int itemUsedInt = i.getMaxDamage(itemStack) - i.getDamage(itemStack);

                            Util.msg("\u00A7c\u00A7l------ Event triggered ------\n\u00A7eEvent: \u00A74Axe in main hand\n\u00A7eTool used: \u00A74" + itemUsedInt + "\n\u00A7eTool warn: \u00A74" + itemWarnInt + "\n\u00A7c\u00A7l---------------------------");
                        } else if(Util.isShovel(itemStack)) {
                            int itemWarnInt = (Main.warnPercentageShovel * i.getMaxDamage(itemStack)) / 100;
                            int itemUsedInt = i.getMaxDamage(itemStack) - i.getDamage(itemStack);

                            Util.msg("\u00A7c\u00A7l------ Event triggered ------\n\u00A7eEvent: \u00A74Shovel in main hand\n\u00A7eTool used: \u00A74" + itemUsedInt + "\n\u00A7eTool warn: \u00A74" + itemWarnInt + "\n\u00A7c\u00A7l---------------------------");
                        } else {
                            Util.msg("\u00A7c\u00A7l------ No Event triggered ------\n\u00A7eEvent: \u00A74-\n\u00A7eTool used: \u00A74-\n\u00A7eTool warn: \u00A74-\n\u00A7c\u00A7l------------------------------");
                        }
                    } else if(args[2].equalsIgnoreCase("off")) {
                        Main.debug = false;

                        Main.getMain().getConfig().addProperty("warn.debug", Main.debug);
                        Main.getMain().saveConfig();
                        Util.msg("\u00A72Debug mode \u00A7esuccessfully \u00A7cdisabled\u00A7e!");
                    } else {
                        Util.msg("\u00A7cPlease provide a valid argument! Args: on, off");
                    }
                } else {
                    Util.msg("\u00A7cPlease provide a valid argument! Args: on, once, off");
                }
                return true;
            } else {
                Util.msg("\u00A7cPlease provide a valid argument! Args: enable, disable, debug");
                return true;
            }
        } else return false;
    }
}