package com.rappytv.prevtb.modules;

import com.rappytv.prevtb.util.Util;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.ModuleCategoryRegistry;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
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

        return Util.isPickaxe(i) || Util.isAxe(i) || Util.isShovel(i);
    }

    @Override
    public String getDisplayName() {
        return "Durability";
    }

    @Override
    public String getDisplayValue() {
        ItemStack itemStack = Minecraft.getMinecraft().player.getHeldItemMainhand();
        Item i = itemStack.getItem();

        String durability = "0/0";

        if(Util.isPickaxe(itemStack) || Util.isAxe(itemStack) || Util.isShovel(itemStack)) {
            durability = (i.getMaxDamage(itemStack) - i.getDamage(itemStack)) + "/" + i.getMaxDamage(itemStack);
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