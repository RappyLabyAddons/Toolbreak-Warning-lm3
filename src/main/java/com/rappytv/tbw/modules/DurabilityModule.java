package com.rappytv.tbw.modules;

import com.rappytv.tbw.main.Main;
import com.rappytv.tbw.util.Util;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.ModuleCategoryRegistry;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class DurabilityModule extends SimpleModule {

    @Override
    public boolean isShown() {
        EntityPlayer p = Minecraft.getMinecraft().player;
        if(p == null) return false;
        ItemStack i = p.getHeldItemMainhand();
        if(i == null) return false;
        if(!i.isItemStackDamageable()) return false;
        if(p.isCreative()) return false;

        return Util.isSword(i) || Util.isPickaxe(i) || Util.isAxe(i) || Util.isShovel(i);
    }

    @Override
    public String getDisplayName() {
        return "Durability";
    }

    @Override
    public String getDisplayValue() {
        ItemStack itemStack = Minecraft.getMinecraft().player.getHeldItemMainhand();

        String durability = "0/0";

        if(Util.isSword(itemStack) || Util.isPickaxe(itemStack) || Util.isAxe(itemStack) || Util.isShovel(itemStack)) {
            String maxDamage = Main.format ? Util.formatNumber(itemStack.getMaxDamage()) : Integer.toString(itemStack.getMaxDamage());
            String damage = Main.format ? Util.formatNumber(itemStack.getMaxDamage() - itemStack.getItemDamage()) : Integer.toString(itemStack.getMaxDamage() - itemStack.getItemDamage());

            durability = damage + "/" + maxDamage;
        }
        return durability;
    }

    @Override
    public String getDefaultValue() {
        return String.valueOf(0);
    }

    @Override
    public ControlElement.IconData getIconData() {
        return new ControlElement.IconData(new ResourceLocation("prevtb/textures/icon.png"));
    }

    @Override
    public void loadSettings() {}

    @Override
    public String getSettingName() {
        return "durability";
    }

    @Override
    public String getControlName() {
        return "Tool durability";
    }

    @Override
    public String getDescription() {
        return "Displays the durability of your currently held pickaxe, axe, or shovel";
    }

    @Override
    public int getSortingId() {
        return 0;
    }

    @Override
    public ModuleCategory getCategory() {
        return ModuleCategoryRegistry.CATEGORY_INFO;
    }
}