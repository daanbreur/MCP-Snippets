package clientname.mods.impl;

import clientname.gui.hud.ScreenPosition;
import clientname.mods.ModDraggable;
import clientname.utils.Render;

public class ModFPS extends ModDraggable {

    private boolean isChroma = true;
    private boolean betterUI = false;

    @Override
    public int getWidth() {
        return 50;
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos) {
        if (isChroma) {
//            Render.drawChromaString("FPS: " + mc.getDebugFPS(), pos.getAbsoluteX(), pos.getAbsoluteY(), true);
            Render.drawChromaString(betterUI ? mc.getDebugFPS() + " FPS" : "FPS: " + mc.getDebugFPS(), pos.getAbsoluteX(), pos.getAbsoluteY(), false);
        }
        else {
            font.drawString(betterUI ? mc.getDebugFPS() + " FPS" : "FPS: " + mc.getDebugFPS(), pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
        }
    }

    public boolean isChroma() {
        return isChroma;
    }

    public void setChroma(boolean chroma) {
        isChroma = chroma;
    }

    public void setBetterUI(boolean betterUI) {
        this.betterUI = betterUI;
    }
}
