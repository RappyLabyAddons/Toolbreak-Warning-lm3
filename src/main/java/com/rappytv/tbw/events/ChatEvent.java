package com.rappytv.tbw.events;

import com.rappytv.tbw.main.Main;
import com.rappytv.tbw.util.Util;
import net.labymod.api.events.MessageSendEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ChatEvent implements MessageSendEvent {

    @Override
    public boolean onSend(String s) {
        String[] args = s.split(" ");
        if(args[0].equalsIgnoreCase("/tbw")) {
            if(args.length < 2) {
                Util.msg("\u00A7cPlease provide a valid argument! Args: enable, disable, format, debug", true);
                return true;
            }
            if(args[1].equalsIgnoreCase("enable")) {
                Main.enabled = true;

                Main.getMain().getConfig().addProperty("enabled", Main.enabled);
                Main.getMain().saveConfig();
                Util.msg("\u00A7eAddon successfully \u00A7aenabled\u00A7e!", true);
                return true;
            } else if(args[1].equalsIgnoreCase("disable")) {
                Main.enabled = false;

                Main.getMain().getConfig().addProperty("enabled", Main.enabled);
                Main.getMain().saveConfig();
                Util.msg("\u00A7eAddon successfully \u00A7cdisabled\u00A7e!", true);
                return true;
            } else if(args[1].equalsIgnoreCase("format")) {
                if(args.length >= 3) {
                    if(args[2].equalsIgnoreCase("on")) {
                        Main.format = true;

                        Main.getMain().getConfig().addProperty("format", Main.format);
                        Main.getMain().saveConfig();
                        Util.msg("\u00A72Number formatting \u00A7esuccessfully \u00A7aenabled\u00A7e!", true);
                        return true;
                    } else if(args[2].equalsIgnoreCase("off")) {
                        Main.format = false;

                        Main.getMain().getConfig().addProperty("format", Main.format);
                        Main.getMain().saveConfig();
                        Util.msg("\u00A7eNumber formatting successfully \u00A7cdisabled\u00A7e!", true);
                        return true;
                    } else {
                        Util.msg("\u00A7cPlease provide a valid argument! Args: on, off", true);
                    }
                } else {
                    Util.msg("\u00A7cPlease provide a valid argument! Args: on, off", true);
                }
                return true;
            } else if(args[1].equalsIgnoreCase("debug")) {
                if(args.length >= 3) {
                    if(args[2].equalsIgnoreCase("on")) {
                        Main.enabled = true;
                        Main.debug = true;

                        Main.getMain().getConfig().addProperty("debug", Main.debug);
                        Main.getMain().saveConfig();
                        Util.msg("\u00A72Debug mode \u00A7esuccessfully \u00A7aenabled\u00A7e!", true);
                    } else if(args[2].equalsIgnoreCase("once")) {
                        EntityPlayer p = Minecraft.getMinecraft().player;
                        ItemStack itemStack = p.getHeldItemMainhand();
                        Util.msg("\u00A72Debug mode \u00A7esuccessfully \u00A7aenabled \u00A7eonce!", true);

                        if(Util.isSword(itemStack)) {
                            int itemWarnInt = (Main.warnPercentageSword * itemStack.getMaxDamage()) / 100;
                            int itemUsedInt = itemStack.getMaxDamage() - itemStack.getItemDamage();

                            Util.msg("\u00A7c\u00A7l------ Event triggered ------\n\u00A7eEvent: \u00A74Sword in main hand\n\u00A7eTool used: \u00A74" + (Main.format ? Util.formatNumber(itemUsedInt) : itemUsedInt) + "\n\u00A7eTool warn: \u00A74" + (Main.format ? Util.formatNumber(itemWarnInt) : itemWarnInt) + "\n\u00A7c\u00A7l---------------------------", false);
                        } else if(Util.isPickaxe(itemStack)) {
                            int itemWarnInt = (Main.warnPercentagePickaxe * itemStack.getMaxDamage()) / 100;
                            int itemUsedInt = itemStack.getMaxDamage() - itemStack.getItemDamage();

                            Util.msg("\u00A7c\u00A7l------ Event triggered ------\n\u00A7eEvent: \u00A74Pickaxe in main hand\n\u00A7eTool used: \u00A74" + (Main.format ? Util.formatNumber(itemUsedInt) : itemUsedInt) + "\n\u00A7eTool warn: \u00A74" + (Main.format ? Util.formatNumber(itemWarnInt) : itemWarnInt) + "\n\u00A7c\u00A7l---------------------------", false);
                        } else if(Util.isAxe(itemStack)) {
                            int itemWarnInt = (Main.warnPercentageAxe * itemStack.getMaxDamage()) / 100;
                            int itemUsedInt = itemStack.getMaxDamage() - itemStack.getItemDamage();

                            Util.msg("\u00A7c\u00A7l------ Event triggered ------\n\u00A7eEvent: \u00A74Axe in main hand\n\u00A7eTool used: \u00A74" + (Main.format ? Util.formatNumber(itemUsedInt) : itemUsedInt) + "\n\u00A7eTool warn: \u00A74" + (Main.format ? Util.formatNumber(itemWarnInt) : itemWarnInt) + "\n\u00A7c\u00A7l---------------------------", false);
                        } else if(Util.isShovel(itemStack)) {
                            int itemWarnInt = (Main.warnPercentageShovel * itemStack.getMaxDamage()) / 100;
                            int itemUsedInt = itemStack.getMaxDamage() - itemStack.getItemDamage();

                            Util.msg("\u00A7c\u00A7l------ Event triggered ------\n\u00A7eEvent: \u00A74Shovel in main hand\n\u00A7eTool used: \u00A74" + (Main.format ? Util.formatNumber(itemUsedInt) : itemUsedInt) + "\n\u00A7eTool warn: \u00A74" + (Main.format ? Util.formatNumber(itemWarnInt) : itemWarnInt) + "\n\u00A7c\u00A7l---------------------------", false);
                        } else {
                            Util.msg("\u00A7c\u00A7l------ No Event triggered ------\n\u00A7eEvent: \u00A74-\n\u00A7eTool used: \u00A74-\n\u00A7eTool warn: \u00A74-\n\u00A7c\u00A7l------------------------------", false);
                        }
                    } else if(args[2].equalsIgnoreCase("off")) {
                        Main.debug = false;

                        Main.getMain().getConfig().addProperty("debug", Main.debug);
                        Main.getMain().saveConfig();
                        Util.msg("\u00A72Debug mode \u00A7esuccessfully \u00A7cdisabled\u00A7e!", true);
                    } else {
                        Util.msg("\u00A7cPlease provide a valid argument! Args: on, off", true);
                    }
                } else {
                    Util.msg("\u00A7cPlease provide a valid argument! Args: on, once, off", true);
                }
                return true;
            } else {
                Util.msg("\u00A7cPlease provide a valid argument! Args: enable, disable, format, debug", true);
                return true;
            }
        } else return false;
    }
}