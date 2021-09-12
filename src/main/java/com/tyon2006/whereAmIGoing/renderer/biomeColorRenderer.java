package com.tyon2006.whereAmIGoing.renderer;

import java.nio.FloatBuffer;
import java.util.Set;

import com.tyon2006.whereAmIGoing.config.ConfigManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static org.lwjgl.opengl.GL11.*;

public class biomeColorRenderer {

    private static boolean needToPop = false;

    @SubscribeEvent
    public static void onLivingRenderPre(RenderLivingEvent.Pre<EntityLivingBase> event) {

		if (event.getEntity() instanceof EntityPlayerMP) return;
		if ((event.getEntity().dimension) != 0) return;
		
		Entity entity = event.getEntity();
		NBTTagCompound entityNBT = entity.getEntityData();
		
		if(entityNBT.hasKey("waigIsRare"))
		{
	        entityNBT.setBoolean("waigColorized", true);
	        return;
		}
		
        needToPop = true;
        GlStateManager.pushMatrix();
        GlStateManager.pushAttrib();
		
		Biome checkBiome = Biome.getBiome(entityNBT.getInteger("waigBiomeID"));
		Set<Type> biomeTypesSet = BiomeDictionary.getTypes(checkBiome);
	
        float red = 1f;
        float green = 1f;
        float blue = 1f;
        float alpha = 1f;
        int count = 1;
                		
    	if (event.getEntity().getEntityData().getBoolean("waigColorized")) {
           
    		red = entityNBT.getFloat("waigColorizedRed");
            green = entityNBT.getFloat("waigColorizedGreen");
            blue = entityNBT.getFloat("waigColorizedBlue");
            if(ConfigManager.enableDebug) {
            	System.out.println(entity.getName());
            	System.out.println("already checked " + red + " " + green + " " + blue);
            }
    		
    	} else if (!(event.getEntity().getEntityData().hasKey("waigColorized"))){
            if(ConfigManager.enableDebug) {
            	System.out.println(event.getEntity().getEntityData().hasKey("waigColorized"));
            	System.out.println(entity.getName());
            	System.out.println("colorizing mob in: " + checkBiome.getBiomeName());
            }
    		entityNBT.setBoolean("waigColorized", true);
    		
    		red=1f;
    		green=1f;
    		blue=1f;
    		
    		if (biomeTypesSet.contains(Type.COLD)) {
    			red = red + 0.62f;
    			green = green + 0.91f;
    			blue = blue + 1.0f;
    			count++;
    		}
    		if (biomeTypesSet.contains(Type.MOUNTAIN)) {
    			red = red + 0.80f;
    			green = green + 0.80f;
    			blue = blue + 0.80f;
    			count++;
    		}
    		if (biomeTypesSet.contains(Type.PLAINS)) {
    			red = red + 0.62f;
    			green = green + 1.0f;
    			blue = blue + 0.76f;
    			count++;
    		}
    		if (biomeTypesSet.contains(Type.JUNGLE)) {
    			red = red + 0.79f;
    			green = green + 1.0f;
    			blue = blue + 0.62f;
    			count++;
    		}
    		if (biomeTypesSet.contains(Type.SANDY)) {
    			red = red + 1.0f;
    			green = green + 1.0f;
    			blue = blue + 0.63f;
    			count++;
    		}
    		if (biomeTypesSet.contains(Type.SPOOKY)) {
    			red = red + 0.78f;
    			green = green + 0.62f;
    			blue = blue + 1.0f;
    			count++;
    		}
    		if (biomeTypesSet.contains(Type.SWAMP)) {
    			red = red + 1.0f;
    			green = green + 0.87f;
    			blue = blue + 0.64f;
    			count++;
    		}
    		if (biomeTypesSet.contains(Type.MESA)) {
    			red = red + 1.0f;
    			green = green + 0.62f;
    			blue = blue + 0.62f;
    			count++;
    		}
    		if (biomeTypesSet.contains(Type.FOREST)) {
    			red = red + 0.62f;
    			green = green + 1.0f;
    			blue = blue + 0.69f;
    			count++;
    		}
    		
            if(ConfigManager.enableDebug) {
            	System.out.println(entity.getName());
            	System.out.println(red + " " + green + " " + blue);
            }
    		
    		red = red / count;
    		green = green / count;
    		blue = blue / count; 
    		
            if(ConfigManager.enableDebug) {
            	System.out.println(red + " " + " " + green + " " + blue);
            }
            
            entityNBT.setFloat("waigColorizedRed", red);
            entityNBT.setFloat("waigColorizedGreen", green);
            entityNBT.setFloat("waigColorizedBlue", blue);    		
    	}
		
        FloatBuffer colourBuffer = RenderHelper.setColorBuffer(red, green, blue, alpha);
        glLightModel(GL_LIGHT_MODEL_AMBIENT, colourBuffer);
        for (int i = 0; i < 8; ++i) {
            GlStateManager.glLight(GL_LIGHT0 + i, GL_DIFFUSE, colourBuffer);
        }

        GlStateManager.color(red, green, blue, alpha);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, 
        		GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
        		GlStateManager.SourceFactor.ONE, 
        		GlStateManager.DestFactor.ZERO);
    }
    
    @SubscribeEvent
    public static void onLivingRenderPost(RenderLivingEvent.Post<EntityLivingBase> event) {
        if (needToPop) {
            needToPop = false;
            GlStateManager.disableBlend();
            GlStateManager.disableOutlineMode();
            GlStateManager.popAttrib();
            GlStateManager.popMatrix();
        }
    }
}
