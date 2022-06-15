package com.rappytv.tbw.util;

import com.rappytv.tbw.main.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class Util {

    public static boolean swordWarned = false;
    public static boolean pickWarned = false;
    public static boolean axeWarned = false;
    public static boolean shovelWarned = false;

    private static int[] sword = {268, 272, 267, 283, 276};
    private static int[] pick = {270, 274, 257, 285, 278};
    private static int[] axe = {271, 275, 258, 286, 279};
    private static int[] spade = {269, 273, 256, 284, 277};

    public static void msg(String text, boolean prefix) {
        Main.getMain().getApi().displayMessageInChat(prefix ? Main.prefix + text : text);
    }

    public static String formatNumber(int number) {
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        formatter.setDecimalFormatSymbols(symbols);

        return formatter.format(number);
    }

    public static boolean isSword(ItemStack i) {
        if(i.getItem().equals(Item.getItemById(sword[0]))) return true;
        else if(i.getItem().equals(Item.getItemById(sword[1]))) return true;
        else if(i.getItem().equals(Item.getItemById(sword[2]))) return true;
        else if(i.getItem().equals(Item.getItemById(sword[3]))) return true;
        else return i.getItem().equals(Item.getItemById(sword[4]));
    }

    public static boolean isPickaxe(ItemStack i) {
        if(i.getItem().equals(Item.getItemById(pick[0]))) return true;
        else if(i.getItem().equals(Item.getItemById(pick[1]))) return true;
        else if(i.getItem().equals(Item.getItemById(pick[2]))) return true;
        else if(i.getItem().equals(Item.getItemById(pick[3]))) return true;
        else return i.getItem().equals(Item.getItemById(pick[4]));
    }

    public static boolean isAxe(ItemStack i) {
        if(i.getItem().equals(Item.getItemById(axe[0]))) return true;
        else if(i.getItem().equals(Item.getItemById(axe[1]))) return true;
        else if(i.getItem().equals(Item.getItemById(axe[2]))) return true;
        else if(i.getItem().equals(Item.getItemById(axe[3]))) return true;
        else return i.getItem().equals(Item.getItemById(axe[4]));
    }

    public static boolean isShovel(ItemStack i) {
        if(i.getItem().equals(Item.getItemById(spade[0]))) return true;
        else if(i.getItem().equals(Item.getItemById(spade[1]))) return true;
        else if(i.getItem().equals(Item.getItemById(spade[2]))) return true;
        else if(i.getItem().equals(Item.getItemById(spade[3]))) return true;
        else return i.getItem().equals(Item.getItemById(spade[4]));
    }

    public static void swordUsed(ItemStack itemStack) {
        int itemWarnInt = (Main.warnPercentageSword * itemStack.getMaxDamage()) / 100;
        int itemUsedInt = itemStack.getMaxDamage() - itemStack.getItemDamage();
        if(Main.debug) {
            msg("\u00A7c\u00A7l------ Event triggered ------\n\u00A7eEvent: \u00A74Sword in main hand\n\u00A7eTool used: \u00A74" + (Main.format ? formatNumber(itemUsedInt) : itemUsedInt) + "\n\u00A7eTool warn: \u00A74" + (Main.format ? formatNumber(itemWarnInt) : itemWarnInt) + "\n\u00A7c\u00A7l---------------------------", false);
        }

        if(itemUsedInt == itemWarnInt) {
            if(!swordWarned) {
                msg(Main.warnMsg.replace("{durability}", Main.warnPercentageSword + ""), true);
                msg(Main.warnMsg.replace("{durability}", Main.warnPercentageSword + ""), true);
                msg(Main.warnMsg.replace("{durability}", Main.warnPercentageSword + ""), true);
                Minecraft.getMinecraft().player.playSound(SoundEvents.BLOCK_ANVIL_USE, 100f, 0f);
                swordWarned = true;
            }
        } else {
            swordWarned = false;
        }
    }

    public static void pickaxeUsed(ItemStack itemStack) {
        int itemWarnInt = (Main.warnPercentagePickaxe * itemStack.getMaxDamage()) / 100;
        int itemUsedInt = itemStack.getMaxDamage() - itemStack.getItemDamage();
        if(Main.debug) {
            msg("\u00A7c\u00A7l------ Event triggered ------\n\u00A7eEvent: \u00A74Pickaxe in main hand\n\u00A7eTool used: \u00A74" + (Main.format ? formatNumber(itemUsedInt) : itemUsedInt) + "\n\u00A7eTool warn: \u00A74" + (Main.format ? formatNumber(itemWarnInt) : itemWarnInt) + "\n\u00A7c\u00A7l---------------------------", false);
        }

        if(itemUsedInt == itemWarnInt) {
            if(!pickWarned) {
                msg(Main.warnMsg.replace("{durability}", Main.warnPercentagePickaxe + ""), true);
                Minecraft.getMinecraft().displayGuiScreen(new GuiChat());
                pickWarned = true;
            }
        } else {
            pickWarned = false;
        }
    }

    public static void axeUsed(ItemStack itemStack) {
        int itemWarnInt = (Main.warnPercentageAxe * itemStack.getMaxDamage()) / 100;
        int itemUsedInt = itemStack.getMaxDamage() - itemStack.getItemDamage();
        if(Main.debug) {
            msg("\u00A7c\u00A7l------ Event triggered ------\n\u00A7eEvent: \u00A74Axe in main hand\n\u00A7eTool used: \u00A74" + (Main.format ? Util.formatNumber(itemUsedInt) : itemUsedInt) + "\n\u00A7eTool warn: \u00A74" + (Main.format ? Util.formatNumber(itemWarnInt) : itemWarnInt) + "\n\u00A7c\u00A7l---------------------------", false);
        }

        if(itemUsedInt == itemWarnInt) {
            if(!axeWarned) {
                msg(Main.warnMsg.replace("{durability}", Main.warnPercentageAxe + ""), true);
                Minecraft.getMinecraft().displayGuiScreen(new GuiChat());
                axeWarned = true;
            }
        } else {
            axeWarned = false;
        }
    }

    public static void shovelUsed(ItemStack itemStack) {
        int itemWarnInt = (Main.warnPercentageShovel * itemStack.getMaxDamage()) / 100;
        int itemUsedInt = itemStack.getMaxDamage() - itemStack.getItemDamage();
        if(Main.debug) {
            msg("\u00A7c\u00A7l------ Event triggered ------\n\u00A7eEvent: \u00A74Shovel in main hand\n\u00A7eTool used: \u00A74" + (Main.format ? formatNumber(itemUsedInt) : itemUsedInt) + "\n\u00A7eTool warn: \u00A74" + (Main.format ? formatNumber(itemWarnInt) : itemWarnInt) + "\n\u00A7c\u00A7l---------------------------", false);
        }

        if(itemUsedInt == itemWarnInt) {
            if(!shovelWarned) {
                msg(Main.warnMsg.replace("{durability}", Main.warnPercentageShovel + ""), true);
                Minecraft.getMinecraft().displayGuiScreen(new GuiChat());
                shovelWarned = true;
            }
        } else {
            shovelWarned = false;
        }
    }
}
