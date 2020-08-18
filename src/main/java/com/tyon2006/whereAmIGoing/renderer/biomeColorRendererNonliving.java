package com.tyon2006.whereAmIGoing.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class biomeColorRendererNonliving {

    @SubscribeEvent
    public static void onRenderWorldLast(RenderWorldLastEvent event) {
        Minecraft minecraft = Minecraft.getMinecraft();
        RenderManager renderManager = minecraft.getRenderManager();

        minecraft.entityRenderer.enableLightmap();
        GlStateManager.pushMatrix();
        boolean currentlyRenderingShadows = renderManager.isRenderShadow();
        renderManager.setRenderShadow(false);

        //NEEDED
        RenderHelper.enableStandardItemLighting();

        float red = 0.74f;
        float green = 0.64f;
        float blue = 0.61f;
        float alpha = 1f;
        
        GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, RenderHelper.setColorBuffer(red, green, blue, alpha));
        for (int i = 0; i < 8; ++i) {
            GlStateManager.glLight(GL11.GL_LIGHT0 + i, GL11.GL_DIFFUSE, RenderHelper.setColorBuffer(red, green, blue, alpha));
        }
        GlStateManager.color(red, green, blue, alpha);

        renderManager.setRenderShadow(currentlyRenderingShadows);
        GlStateManager.popMatrix();
        minecraft.entityRenderer.disableLightmap();
    }
}

