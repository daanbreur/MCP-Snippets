package clientname.mods.impl;

import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import clientname.gui.hud.ScreenPosition;
import clientname.mods.ModDraggable;

public class ModArmorStatus extends ModDraggable {

    private ScreenPosition pos = ScreenPosition.fromRelativePosition(0.5, 0.5);

    @Override
    public int getWidth() {
        return 64;
    }

    @Override
    public int getHeight() {
        return 64;
    }

    @Override
    public void render(ScreenPosition pos) {
        for (int i = 0; i < mc.thePlayer.inventory.armorInventory.length; i++)
        {
            ItemStack itemStack = mc.thePlayer.inventory.armorInventory[i];
            renderItemStack(pos, i, itemStack);
        }
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        renderItemStack(pos, 3, new ItemStack(Items.diamond_helmet));
        renderItemStack(pos, 2, new ItemStack(Items.diamond_chestplate));
        renderItemStack(pos, 1, new ItemStack(Items.diamond_leggings));
        renderItemStack(pos, 0, new ItemStack(Items.diamond_boots));
    }

    private void renderItemStack(ScreenPosition pos, int i, ItemStack itemStack) {

        if(itemStack == null) {
            return;
        }

        GL11.glPushMatrix();

        int yAdd = (-16 * i) + 48;

        if(itemStack.getItem().isDamageable()) {
            double damage = ((itemStack.getMaxDamage() - itemStack.getItemDamage()) / (double) itemStack.getMaxDamage()) * 100;
            if (damage < 25) {
                font.drawString(String.format("%.2f%%", damage), pos.getAbsoluteX() + 20, pos.getAbsoluteY() + yAdd + 5, 0xFFFF0000);
            }
            else if (damage < 50) {
                font.drawString(String.format("%.2f%%", damage), pos.getAbsoluteX() + 20, pos.getAbsoluteY() + yAdd + 5, 0xFFFF6600);
            }
            else {
                font.drawString(String.format("%.2f%%", damage), pos.getAbsoluteX() + 20, pos.getAbsoluteY() + yAdd + 5, -1);
            }
        }

        RenderHelper.enableGUIStandardItemLighting();
        mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, pos.getAbsoluteX(), pos.getAbsoluteY() + yAdd);

        GL11.glPopMatrix();

    }

    @Override
    public void save(ScreenPosition pos) {
        this.pos = pos;
    }

    @Override
    public ScreenPosition load() {
        return pos;
    }

}

