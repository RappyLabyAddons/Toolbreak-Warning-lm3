package com.rappytv.prevtb.util;

import com.rappytv.prevtb.main.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Util {

    public static boolean pickWarned = false;
    public static boolean axeWarned = false;
    public static boolean shovelWarned = false;

    public static void msg(String text) {
        Main.getMain().getApi().displayMessageInChat(text);
    }


    public static boolean isPickaxe(ItemStack i) {
        if(i.getItem().equals(Item.getItemById(270))) return true;
        else if(i.getItem().equals(Item.getItemById(274))) return true;
        else if(i.getItem().equals(Item.getItemById(257))) return true;
        else if(i.getItem().equals(Item.getItemById(285))) return true;
        else return i.getItem().equals(Item.getItemById(278));
    }

    public static boolean isAxe(ItemStack i) {
        if(i.getItem().equals(Item.getItemById(271))) return true;
        else if(i.getItem().equals(Item.getItemById(275))) return true;
        else if(i.getItem().equals(Item.getItemById(258))) return true;
        else if(i.getItem().equals(Item.getItemById(286))) return true;
        else return i.getItem().equals(Item.getItemById(279));
    }

    public static boolean isShovel(ItemStack i) {
        if(i.getItem().equals(Item.getItemById(269))) return true;
        else if(i.getItem().equals(Item.getItemById(273))) return true;
        else if(i.getItem().equals(Item.getItemById(256))) return true;
        else if(i.getItem().equals(Item.getItemById(284))) return true;
        else return i.getItem().equals(Item.getItemById(277));
    }

    public static void pickaxeUsed(ItemStack itemStack) {
        Item i = itemStack.getItem();

        int itemWarnInt = (Main.warnPercentagePickaxe * i.getMaxDamage(itemStack)) / 100;
        int itemUsedInt = i.getMaxDamage(itemStack) - i.getDamage(itemStack);
        if(Main.debug) {
            msg("\u00A7c\u00A7l------ Event triggered ------\n\u00A7eEvent: \u00A74Pickaxe in main hand\n\u00A7eTool used: \u00A74" + itemUsedInt + "\n\u00A7eTool warn: \u00A74" + itemWarnInt + "\n\u00A7c\u00A7l---------------------------");
        }

        if(itemUsedInt == itemWarnInt) {
            if(!pickWarned) {
                msg(Main.warnMsg.replace("{durability}", Main.warnPercentagePickaxe + ""));
                Minecraft.getMinecraft().displayGuiScreen(new GuiChat());
                pickWarned = true;
            }
        } else {
            pickWarned = false;
        }
    }

    public static void axeUsed(ItemStack itemStack) {
        Item i = itemStack.getItem();

        int itemWarnInt = (Main.warnPercentageAxe * i.getMaxDamage(itemStack)) / 100;
        int itemUsedInt = i.getMaxDamage(itemStack) - i.getDamage(itemStack);
        if(Main.debug) {
            msg("\u00A7c\u00A7l------ Event triggered ------\n\u00A7eEvent: \u00A74Axe in main hand\n\u00A7eTool used: \u00A74" + itemUsedInt + "\n\u00A7eTool warn: \u00A74" + itemWarnInt + "\n\u00A7c\u00A7l---------------------------");
        }

        if(itemUsedInt == itemWarnInt) {
            if(!axeWarned) {
                msg(Main.warnMsg.replace("{durability}", Main.warnPercentageAxe + ""));
                Minecraft.getMinecraft().displayGuiScreen(new GuiChat());
                axeWarned = true;
            }
        } else {
            axeWarned = false;
        }
    }

    public static void shovelUsed(ItemStack itemStack) {
        Item i = itemStack.getItem();

        int itemWarnInt = (Main.warnPercentageShovel * i.getMaxDamage(itemStack)) / 100;
        int itemUsedInt = i.getMaxDamage(itemStack) - i.getDamage(itemStack);
        if(Main.debug) {
            msg("\u00A7c\u00A7l------ Event triggered ------\n\u00A7eEvent: \u00A74Shovel in main hand\n\u00A7eTool used: \u00A74" + itemUsedInt + "\n\u00A7eTool warn: \u00A74" + itemWarnInt + "\n\u00A7c\u00A7l---------------------------");
        }

        if(itemUsedInt == itemWarnInt) {
            if(!shovelWarned) {
                msg(Main.warnMsg.replace("{durability}", Main.warnPercentageShovel + ""));
                Minecraft.getMinecraft().displayGuiScreen(new GuiChat());
                shovelWarned = true;
            }
        } else {
            shovelWarned = false;
        }
    }
}
