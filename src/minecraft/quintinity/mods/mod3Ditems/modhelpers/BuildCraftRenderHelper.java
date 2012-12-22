package quintinity.mods.mod3Ditems.modhelpers;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.EntityItem;
import net.minecraft.src.Item;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.RenderEngine;
import net.minecraft.src.RenderManager;
import net.minecraft.src.Tessellator;
import net.minecraft.src.Timer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import quintinity.mods.mod3Ditems.ItemRenderer3D;

public class BuildCraftRenderHelper 
{
	private static RenderBlocks renderBlocks = new RenderBlocks();
    private static Random random = new Random();
    public static boolean field_77024_a = true;
    public static float zLevel = 0.0F;
    public static boolean field_82407_g = false;
    public static RenderManager manager;
    
    public static void init()
    {
    	manager = RenderManager.instance;
    }
    
    public static void renderAsPipedItem(EntityItem par1EntityItem)
    {
        random.setSeed(187L);
        ItemStack var10 = par1EntityItem.item;
        Timer timer = ItemRenderer3D.timer;
        
        if (var10.getItem() != null)
        {
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            int var16;
            float var19;
            float var20;
            float var24;

            if (var10.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.blocksList[var10.itemID].getRenderType()))
            {	
            	GL11.glScalef(0.5F, 0.5F, 0.5F);
                loadTexture(Block.blocksList[var10.itemID].getTextureFile());
                float var22 = 0.25F;
                var16 = Block.blocksList[var10.itemID].getRenderType();
                var24 = 1.0F;
                renderBlocks.renderBlockAsItem(Block.blocksList[var10.itemID], var10.getItemDamage(), var24);
            }
            else
            {
                int var15;
                float var17;
                GL11.glTranslatef(0F, -0.1F, 0);
                GL11.glScalef(0.75F, 0.75F, 0.75F);
                if (var10.getItem().requiresMultipleRenderPasses())
                {
                    loadTexture(Item.itemsList[var10.itemID].getTextureFile());
                    for (var15 = 0; var15 < var10.getItem().getRenderPasses(var10.getItemDamage()); ++var15)
                    {
                        random.setSeed(187L); //Fixes Vanilla bug where layers would not render aligns properly.
                        var16 = var10.getItem().getIconFromItemStackForMultiplePasses(var10, var15);
                        var17 = 1.0F;

                        if (field_77024_a)
                        {
                            int var18 = Item.itemsList[var10.itemID].getColorFromItemStack(var10, var15);
                            var19 = (float)(var18 >> 16 & 255) / 255.0F;
                            var20 = (float)(var18 >> 8 & 255) / 255.0F;
                            float var21 = (float)(var18 & 255) / 255.0F;
                            GL11.glColor4f(var19 * var17, var20 * var17, var21 * var17, 1.0F);
                        }
                        drawItem(var16, 1);
                    }
                }
                else
                {
                    var15 = var10.getIconIndex();
                    loadTexture(var10.getItem().getTextureFile());

                    if (field_77024_a)
                    {
                        var16 = Item.itemsList[var10.itemID].getColorFromItemStack(var10, 0);
                        var17 = (float)(var16 >> 16 & 255) / 255.0F;
                        var24 = (float)(var16 >> 8 & 255) / 255.0F;
                        var19 = (float)(var16 & 255) / 255.0F;
                        var20 = 1.0F;
                        GL11.glColor4f(var17 * var20, var24 * var20, var19 * var20, 1.0F);
                    }

                    drawItem(var15, 1);
                }
            }

            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        }
    }

    private static void drawItem(int iconIndex, int quantity) 
    {
		Tessellator tesselator = Tessellator.instance;
		float var4 = (iconIndex % 16 * 16 + 0) / 256.0F;
		float var5 = (iconIndex % 16 * 16 + 16) / 256.0F;
		float var6 = (iconIndex / 16 * 16 + 0) / 256.0F;
		float var7 = (iconIndex / 16 * 16 + 16) / 256.0F;
		float var8 = 1.0F;
		float var9 = 0.5F;
		float var10 = 0.25F;

		for (int var11 = 0; var11 < quantity; ++var11) {
			GL11.glPushMatrix();

			if (var11 > 0) {
				float var12 = (random.nextFloat() * 2.0F - 1.0F) * 0.3F;
				float var13 = (random.nextFloat() * 2.0F - 1.0F) * 0.3F;
				float var14 = (random.nextFloat() * 2.0F - 1.0F) * 0.3F;
				GL11.glTranslatef(var12, var13, var14);
			}

			GL11.glRotatef(180.0F - RenderManager.instance.playerViewY, 0.0F, 1.0F, 0.0F);
			tesselator.startDrawingQuads();
			tesselator.setNormal(0.0F, 1.0F, 0.0F);
			tesselator.addVertexWithUV((0.0F - var9), (0.0F - var10), 0.0D, var4, var7);
			tesselator.addVertexWithUV((var8 - var9), (0.0F - var10), 0.0D, var5, var7);
			tesselator.addVertexWithUV((var8 - var9), (1.0F - var10), 0.0D, var5, var6);
			tesselator.addVertexWithUV((0.0F - var9), (1.0F - var10), 0.0D, var4, var6);
			tesselator.draw();
			GL11.glPopMatrix();
		}
	}
    
    private static void loadTexture(String par1Str)
    {
        RenderEngine var2 = manager.renderEngine;
        var2.bindTexture(var2.getTexture(par1Str));
    }
}
