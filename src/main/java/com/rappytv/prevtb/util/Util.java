package com.rappytv.prevtb.util;

import com.rappytv.prevtb.main.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.item.*;

public class Util {

    public static void msg(String text) {
        Main.getMain().getApi().displayMessageInChat(text);
    }

    public static boolean isAxe(ItemStack i) {
        return i.getItem() instanceof ItemAxe;
    }

    public static boolean isPickaxe(ItemStack i) {
        return i.getItem() instanceof ItemPickaxe;
    }

    public static boolean isShovel(ItemStack i) {
        return i.getItem() instanceof ItemSpade;
    }

    public static void pickaxeUsed(ItemStack itemStack) {
        Item i = itemStack.getItem();

        int itemWarnInt = (Main.warnPercentagePickaxe * i.getMaxDamage(itemStack)) / 100;
        int itemUsedInt = i.getMaxDamage(itemStack) - i.getDamage(itemStack);

        if(itemUsedInt == itemWarnInt) {
            msg(Main.warnMsg.replace("{durability}", Main.warnPercentagePickaxe + ""));
            Minecraft.getMinecraft().displayGuiScreen(new GuiChat());
        }
    }

    public static void axeUsed(ItemStack itemStack) {
        Item i = itemStack.getItem();

        int itemWarnInt = (Main.warnPercentageAxe * i.getMaxDamage(itemStack)) / 100;
        int itemUsedInt = i.getMaxDamage(itemStack) - i.getDamage(itemStack);

        if(itemUsedInt == itemWarnInt) {
            msg(Main.warnMsg.replace("{durability}", Main.warnPercentageAxe + ""));
            Minecraft.getMinecraft().displayGuiScreen(new GuiChat());
        }
    }

    public static void shovelUsed(ItemStack itemStack) {
        Item i = itemStack.getItem();

        int itemWarnInt = (Main.warnPercentageShovel * i.getMaxDamage(itemStack)) / 100;
        int itemUsedInt = i.getMaxDamage(itemStack) - i.getDamage(itemStack);

        if(itemUsedInt == itemWarnInt) {
            msg(Main.warnMsg.replace("{durability}", Main.warnPercentageShovel + ""));
            Minecraft.getMinecraft().displayGuiScreen(new GuiChat());
        }
    }
}
