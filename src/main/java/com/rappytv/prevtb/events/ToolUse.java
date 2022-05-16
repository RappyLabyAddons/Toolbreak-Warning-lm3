package com.rappytv.prevtb.events;

import com.rappytv.prevtb.main.Main;
import com.rappytv.prevtb.util.Util;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ToolUse {

    @SubscribeEvent
    public void onUse(BlockEvent.BreakEvent e) {
        if(!Main.enabled) return;
        EntityPlayer p = e.getPlayer();
        ItemStack i = p.getHeldItemMainhand();

        if(i == null) return;
        if(!i.isItemStackDamageable()) return;
        if(p.isCreative()) return;

        if(Util.isPickaxe(i)) {
            Util.pickaxeUsed(i);
        }
        else if(Util.isAxe(i)) {
            Util.axeUsed(i);
        }
        else if(Util.isShovel(i)) {
            Util.shovelUsed(i);
        }
    }
}
