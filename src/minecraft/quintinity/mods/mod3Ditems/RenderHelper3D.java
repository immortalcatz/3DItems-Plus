package quintinity.mods.mod3Ditems;
import java.lang.reflect.*;
import java.util.*;
import net.minecraft.block.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.*;
import net.minecraft.src.*;
import net.minecraft.util.Timer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import quintinity.api.ItemIconManager;
import quintinity.mods.mod3Ditems.settings.Settings;

import cpw.mods.fml.client.FMLClientHandler;

public class RenderHelper3D
{
    public static Minecraft minecraft;
    public static RenderBlocks blockRenderer = new RenderBlocks();
    public static Timer timer;
    private static int textureWidth = 16;
    private static long startTime = 0;
	public static ArrayList<Integer> leatherIDs = new ArrayList<Integer>();
	
    public static int getTextureWidth()
    {
    	if (System.currentTimeMillis() - startTime >= 2000) {
    		startTime = System.currentTimeMillis();
    		try
            {
                Class clazz;
                int width;
                if (Mod3DItems.optifineInstalled)
                {
                    clazz = Class.forName("Config");
                    Method method = clazz.getDeclaredMethod("getIconWidthItems", (Class[])null);
                    width = ((Integer)method.invoke((Object)null, (Object[])null)).intValue();
                    textureWidth = width;
                }
                else if (Mod3DItems.mcpatcherInstalled)
                {
                    clazz = Class.forName("com.pclewis.mcpatcher.mod.TileSize");
                    Field field = clazz.getField("int_size");
                    width = ((Integer)field.get((Object)null)).intValue();
                    textureWidth = width;
                }
                else
                {
                	textureWidth = 16;
                }
            }
            catch (Exception var3)
            {
            	textureWidth = 16;
            }
    	}
    	return textureWidth;
    }

    public static void init()
    {
    	FMLClientHandler client = FMLClientHandler.instance();
        minecraft = client.getClient();
        leatherIDs.add(Item.helmetLeather.shiftedIndex);
        leatherIDs.add(Item.plateLeather.shiftedIndex);
        leatherIDs.add(Item.legsLeather.shiftedIndex);
        leatherIDs.add(Item.bootsLeather.shiftedIndex);
        try 
        {
	        Class clazz = Minecraft.class;
	        Field field = clazz.getDeclaredFields()[9];
	        field.setAccessible(true);
	        timer = (Timer)field.get(minecraft);
        }
        catch (Exception e) 
        {
        	e.printStackTrace();
        }
    }
    
    public static void render(int var0, int var1, ItemStack var2, Random var3, RenderManager var4, int var5)
    {
        Tessellator var6 = Tessellator.instance;
        float var7 = (float)(var0 % 16 * 16 + 0) / 256.0F;
        float var8 = (float)(var0 % 16 * 16 + 16) / 256.0F;
        float var9 = (float)(var0 / 16 * 16 + 0) / 256.0F;
        float var10 = (float)(var0 / 16 * 16 + 16) / 256.0F;
        float var11 = 1.0F;
        float var12 = 0.5F;
        float var13 = 0.25F;

        for (int var14 = 0; var14 < var1; ++var14)
        {
            GL11.glPushMatrix();

            if (var14 > 0)
            {
                float var15 = (var3.nextFloat() * 2.0F - 1.0F) * 0.3F;
                float var16 = (var3.nextFloat() * 2.0F - 1.0F) * 0.3F;
                float var17 = (var3.nextFloat() * 2.0F - 1.0F) * 0.3F;
                GL11.glTranslatef(var15, var16, var17);
            }

            if (Settings.getRenderIn3D())
            {
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                renderItemIn3D(var6, var8, var9, var7, var10, var5);
                GL11.glPopMatrix();
            }
            else if (!Settings.getRenderIn3D())
            {
                GL11.glRotatef(180.0F - var4.playerViewY, 0.0F, 1.0F, 0.0F);
                var6.startDrawingQuads();
                var6.setNormal(0.0F, 1.0F, 0.0F);
                var6.addVertexWithUV((double)(0.0F - var12), (double)(0.0F - var13), 0.0D, (double)var7, (double)var10);
                var6.addVertexWithUV((double)(var11 - var12), (double)(0.0F - var13), 0.0D, (double)var8, (double)var10);
                var6.addVertexWithUV((double)(var11 - var12), (double)(1.0F - var13), 0.0D, (double)var8, (double)var9);
                var6.addVertexWithUV((double)(0.0F - var12), (double)(1.0F - var13), 0.0D, (double)var7, (double)var9);
                var6.draw();
                GL11.glPopMatrix();
            }
        }
    }

    public static void renderWithOverlay(int icon, int var1, ItemStack item, Random var3, int width)
    {
        renderWithOverlay(icon, var1, item, var3, width, false, 0);
    }

    public static void renderWithOverlay(int var0, int var1, ItemStack var2, Random var3, int var5, boolean var6, int var7)
    {
    	RenderManager var4 = RenderManager.instance;
        Tessellator var8 = Tessellator.instance;
        float var9 = (float)(var0 % 16 * 16 + 0) / 256.0F;
        float var10 = (float)(var0 % 16 * 16 + 16) / 256.0F;
        float var11 = (float)(var0 / 16 * 16 + 0) / 256.0F;
        float var12 = (float)(var0 / 16 * 16 + 16) / 256.0F;
        float var13 = 1.0F;
        float var14 = 0.5F;
        float var15 = 0.25F;

        for (int var16 = 0; var16 < var1; ++var16)
        {
            GL11.glPushMatrix();

            if (var16 > 0)
            {
                float var17 = (var3.nextFloat() * 2.0F - 1.0F) * 0.3F;
                float var18 = (var3.nextFloat() * 2.0F - 1.0F) * 0.3F;
                float var19 = (var3.nextFloat() * 2.0F - 1.0F) * 0.3F;
                GL11.glTranslatef(var17, var18, var19);
            }

            if (Settings.getRenderIn3D())
            {
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                bindCorrectTexture(var2);

                if (var6)
                {
                    setColor(var2, var7);
                }

                renderItemIn3D(var8, var10, var11, var9, var12, var5);
                renderEffectOverlay(var0, var1, var2, var4, var5);
                GL11.glPopMatrix();
            }
            else if (!Settings.getRenderIn3D())
            {
                if (var6)
                {
                    setColor(var2, var7);
                }
                GL11.glRotatef(180.0F - var4.playerViewY, 0.0F, 1.0F, 0.0F);
                var8.startDrawingQuads();
                var8.setNormal(0.0F, 1.0F, 0.0F);
                var8.addVertexWithUV((double)(0.0F - var14), (double)(0.0F - var15), 0.0D, (double)var9, (double)var12);
                var8.addVertexWithUV((double)(var13 - var14), (double)(0.0F - var15), 0.0D, (double)var10, (double)var12);
                var8.addVertexWithUV((double)(var13 - var14), (double)(1.0F - var15), 0.0D, (double)var10, (double)var11);
                var8.addVertexWithUV((double)(0.0F - var14), (double)(1.0F - var15), 0.0D, (double)var9, (double)var11);
                var8.draw();
                GL11.glPopMatrix();
            }
        }
    }

    private static void renderEffectOverlay(int var0, int var1, ItemStack var2, RenderManager var3, int var4)
    {
        Tessellator var5 = Tessellator.instance;

        if (var2 != null && var2.hasEffect())
        {
            GL11.glDepthFunc(GL11.GL_EQUAL);
            GL11.glDisable(GL11.GL_LIGHTING);
            minecraft.renderEngine.bindTexture(minecraft.renderEngine.getTexture("%blur%/misc/glint.png"));
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
            float var6 = 0.76F;
            GL11.glColor4f(0.5F * var6, 0.25F * var6, 0.8F * var6, 1.0F);
            GL11.glMatrixMode(GL11.GL_TEXTURE);
            GL11.glPushMatrix();
            float var7 = 0.125F;
            GL11.glScalef(var7, var7, var7);
            float var8 = (float)(System.currentTimeMillis() % 3000L) / 3000.0F * 8.0F;
            GL11.glTranslatef(var8, 0.0F, 0.0F);
            GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
            renderItemIn3D(var5, 0.0F, 0.0F, 1.0F, 1.0F, var4);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(var7, var7, var7);
            var8 = (float)(System.currentTimeMillis() % 4873L) / 4873.0F * 8.0F;
            GL11.glTranslatef(-var8, 0.0F, 0.0F);
            GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
            renderItemIn3D(var5, 0.0F, 0.0F, 1.0F, 1.0F, var4);
            GL11.glPopMatrix();
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDepthFunc(GL11.GL_LEQUAL);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    public static void renderItemIn3D(Tessellator var0, float var1, float var2, float var3, float var4, int var5)
    {
        float var6 = 1.0F;
        float var7 = 0.0625F;
        var0.startDrawingQuads();
        var0.setNormal(0.0F, 0.0F, 1.0F);
        var0.addVertexWithUV(0.0D, 0.0D, 0.0D, (double)var1, (double)var4);
        var0.addVertexWithUV((double)var6, 0.0D, 0.0D, (double)var3, (double)var4);
        var0.addVertexWithUV((double)var6, 1.0D, 0.0D, (double)var3, (double)var2);
        var0.addVertexWithUV(0.0D, 1.0D, 0.0D, (double)var1, (double)var2);
        var0.draw();
        var0.startDrawingQuads();
        var0.setNormal(0.0F, 0.0F, -1.0F);
        var0.addVertexWithUV(0.0D, 1.0D, (double)(0.0F - var7), (double)var1, (double)var2);
        var0.addVertexWithUV((double)var6, 1.0D, (double)(0.0F - var7), (double)var3, (double)var2);
        var0.addVertexWithUV((double)var6, 0.0D, (double)(0.0F - var7), (double)var3, (double)var4);
        var0.addVertexWithUV(0.0D, 0.0D, (double)(0.0F - var7), (double)var1, (double)var4);
        var0.draw();
        float var8 = 1.0F / (float)(32 * var5);
        float var9 = 1.0F / (float)var5;
        var0.startDrawingQuads();
        var0.setNormal(-1.0F, 0.0F, 0.0F);
        int var10;
        float var11;
        float var12;
        float var13;

        for (var10 = 0; var10 < var5; ++var10)
        {
            var11 = (float)var10 / ((float)var5 * 1.0F);
            var12 = var1 + (var3 - var1) * var11 - var8;
            var13 = var6 * var11;
            var0.addVertexWithUV((double)var13, 0.0D, (double)(0.0F - var7), (double)var12, (double)var4);
            var0.addVertexWithUV((double)var13, 0.0D, 0.0D, (double)var12, (double)var4);
            var0.addVertexWithUV((double)var13, 1.0D, 0.0D, (double)var12, (double)var2);
            var0.addVertexWithUV((double)var13, 1.0D, (double)(0.0F - var7), (double)var12, (double)var2);
        }

        var0.draw();
        var0.startDrawingQuads();
        var0.setNormal(1.0F, 0.0F, 0.0F);

        for (var10 = 0; var10 < var5; ++var10)
        {
            var11 = (float)var10 / ((float)var5 * 1.0F);
            var12 = var1 + (var3 - var1) * var11 - var8;
            var13 = var6 * var11 + var9;
            var0.addVertexWithUV((double)var13, 1.0D, (double)(0.0F - var7), (double)var12, (double)var2);
            var0.addVertexWithUV((double)var13, 1.0D, 0.0D, (double)var12, (double)var2);
            var0.addVertexWithUV((double)var13, 0.0D, 0.0D, (double)var12, (double)var4);
            var0.addVertexWithUV((double)var13, 0.0D, (double)(0.0F - var7), (double)var12, (double)var4);
        }

        var0.draw();
        var0.startDrawingQuads();
        var0.setNormal(0.0F, 1.0F, 0.0F);

        for (var10 = 0; var10 < var5; ++var10)
        {
            var11 = (float)var10 / ((float)var5 * 1.0F);
            var12 = var4 + (var2 - var4) * var11 - var8;
            var13 = var6 * var11 + var9;
            var0.addVertexWithUV(0.0D, (double)var13, 0.0D, (double)var1, (double)var12);
            var0.addVertexWithUV((double)var6, (double)var13, 0.0D, (double)var3, (double)var12);
            var0.addVertexWithUV((double)var6, (double)var13, (double)(0.0F - var7), (double)var3, (double)var12);
            var0.addVertexWithUV(0.0D, (double)var13, (double)(0.0F - var7), (double)var1, (double)var12);
        }

        var0.draw();
        var0.startDrawingQuads();
        var0.setNormal(0.0F, -1.0F, 0.0F);

        for (var10 = 0; var10 < var5; ++var10)
        {
            var11 = (float)var10 / ((float)var5 * 1.0F);
            var12 = var4 + (var2 - var4) * var11 - var8;
            var13 = var6 * var11;
            var0.addVertexWithUV((double)var6, (double)var13, 0.0D, (double)var3, (double)var12);
            var0.addVertexWithUV(0.0D, (double)var13, 0.0D, (double)var1, (double)var12);
            var0.addVertexWithUV(0.0D, (double)var13, (double)(0.0F - var7), (double)var1, (double)var12);
            var0.addVertexWithUV((double)var6, (double)var13, (double)(0.0F - var7), (double)var3, (double)var12);
        }

        var0.draw();
    }

    public static void bindCorrectTexture(ItemStack item)
    {
        RenderEngine var1 = minecraft.renderEngine;
        var1.bindTexture(var1.getTexture(ItemIconManager.getTextureFile(item)));
    }

    public static void loadTexture(String name)
    {
        RenderEngine var1 = minecraft.renderEngine;
        var1.bindTexture(var1.getTexture(name));
    }

    private static void setColor(ItemStack item, int color)
    {
        if (item.getItem().requiresMultipleRenderPasses())
        {
            int var2 = Item.itemsList[item.itemID].getColorFromItemStack(item, color);
            float var3 = (float)(var2 >> 16 & 255) / 255.0F;
            float var4 = (float)(var2 >> 8 & 255) / 255.0F;
            float var5 = (float)(var2 & 255) / 255.0F;
            GL11.glColor4f(var3, var4, var5, 1.0F);
        }
        else
        {
        	int newColor = Item.itemsList[item.itemID].getColorFromItemStack(item, 0);
        	float r = (float)(newColor >> 16 & 255) / 255.0F;
            float g = (float)(newColor  >> 8 & 255) / 255.0F;
            float b = (float)(newColor & 255) / 255.0F;
            float a = 1.0F;
            GL11.glColor4f(r * a, g * a, b * a, 1.0F);
        }
    }

    private static void renderItemStack3D(ItemStack var0, double var1, double var3, double var5, float var7, float var8)
    {
        int var9 = getTextureWidth();
        boolean var10 = true;
        RenderManager var11 = RenderManager.instance;
        Random var12 = new Random();
        var12.setSeed(187L);
        GL11.glPushMatrix();
        byte var13 = 1;
        GL11.glTranslatef((float)var1, (float)var3, (float)var5);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        Block var14 = Block.blocksList[var0.itemID];
        int var16;
        float var19;
        float var20;
        float var24;

        if (var14 != null && RenderBlocks.renderItemIn3d(var14.getRenderType()))
        {
            loadTexture("/terrain.png");
            float var22 = 0.25F;
            var16 = var14.getRenderType();

            if (var16 == 1 || var16 == 19 || var16 == 12 || var16 == 2)
            {
                var22 = 0.5F;
            }

            GL11.glScalef(var22, var22, var22);

            for (int var23 = 0; var23 < var13; ++var23)
            {
                GL11.glPushMatrix();

                if (var23 > 0)
                {
                    var24 = (var12.nextFloat() * 2.0F - 1.0F) * 0.2F / var22;
                    var19 = (var12.nextFloat() * 2.0F - 1.0F) * 0.2F / var22;
                    var20 = (var12.nextFloat() * 2.0F - 1.0F) * 0.2F / var22;
                    GL11.glTranslatef(var24, var19, var20);
                }

                var24 = 1.0F;
                blockRenderer.renderBlockAsItem(var14, var0.getItemDamage(), var24);
                GL11.glPopMatrix();
            }
        }
        else
        {
            int var15;
            float var17;

            if (var0.getItem().requiresMultipleRenderPasses())
            {
                GL11.glScalef(0.5F, 0.5F, 0.5F);
                loadTexture("/gui/items.png");

                for (var15 = 0; var15 <= 1; ++var15)
                {
                    if (Settings.getRenderIn3D() && var15 == 0)
                    {
                        GL11.glTranslatef(0.5F, 0.0F, 0.0F);
                    }

                    var12.setSeed(187L);
                    var16 = var0.getItem().getIconFromDamageForRenderPass(var0.getItemDamage(), var15);
                    var17 = 1.0F;

                    if (var10)
                    {
                        int var18 = Item.itemsList[var0.itemID].getColorFromItemStack(var0, var15);
                        var19 = (float)(var18 >> 16 & 255) / 255.0F;
                        var20 = (float)(var18 >> 8 & 255) / 255.0F;
                        float var21 = (float)(var18 & 255) / 255.0F;
                        GL11.glColor4f(var19 * var17, var20 * var17, var21 * var17, 1.0F);
                    }

                    if (var15 == 1)
                    {
                        renderWithOverlay(var16, var13, var0, var12, var9, true, var15);
                    }
                    else
                    {
                        render(var16, var13, var0, var12, var11, var9);
                    }

                    if (Settings.getRenderIn3D())
                    {
                        ;
                    }
                }
            }
            else
            {
                var15 = var0.getIconIndex();

                if (var14 != null)
                {
                    loadTexture("/terrain.png");
                }
                else
                {
                    loadTexture("/gui/items.png");
                }

                if (Settings.getRenderIn3D())
                {
                    GL11.glScalef(0.5F, 0.5F, 0.5F);
                    GL11.glTranslatef(0.5F, 0.0F, 0.0F);
                }
                else if (!Settings.getRenderIn3D())
                {
                    GL11.glScalef(0.5F, 0.5F, 0.5F);
                    GL11.glRotatef(180.0F - var11.playerViewY, 0.0F, 1.0F, 0.0F);
                }

                if (var10)
                {
                    var16 = Item.itemsList[var0.itemID].getColorFromItemStack(var0, 0);
                    var17 = (float)(var16 >> 16 & 255) / 255.0F;
                    var24 = (float)(var16 >> 8 & 255) / 255.0F;
                    var19 = (float)(var16 & 255) / 255.0F;
                    var20 = 1.0F;
                    GL11.glColor4f(var17 * var20, var24 * var20, var19 * var20, 1.0F);
                }

                renderWithOverlay(var15, var13, var0, var12, var9);
            }
        }

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    public static void renderItemStackInGUI(int var0, int var1, ItemStack var2)
    {
        if (var2.itemID != 0)
        {
            GL11.glEnable(GL11.GL_COLOR_MATERIAL);
            GL11.glPushMatrix();
            GL11.glTranslatef((float)var0, (float)var1, 50.0F);
            GL11.glScalef(1.1F, 1.1F, 0.0F);
            GL11.glScalef(-30.0F, 30.0F, 30.0F);
            GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);

            if (var2.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.blocksList[var2.itemID].getRenderType()))
            {
                GL11.glScalef(1.2F, 1.2F, 0.0F);
                GL11.glRotatef(35.0F, 1.0F, 0.0F, 0.0F);
                GL11.glTranslatef(0.0F, 0.25F, 0.0F);
            }

            if (!(var2.getItem() instanceof ItemBlock))
            {
                GL11.glDisable(GL11.GL_LIGHTING);
            }

            GL11.glRotatef(Mod3DItems.angle, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(0.0F, minecraft.thePlayer.yOffset, 0.0F);
            renderItemStack3D(var2, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);

            if (!(var2.getItem() instanceof ItemBlock))
            {
                GL11.glEnable(GL11.GL_LIGHTING);
            }

            GL11.glPopMatrix();
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        }
    }

    private static boolean checkRenderType(ItemStack var0)
    {
        int[] var1 = new int[] {0, 10, 11, 13, 16, 21, 26, 31};
        Block var2 = Block.blocksList[var0.itemID];

        if (!(var2 instanceof BlockChest) && !(var2 instanceof BlockEnderChest))
        {
            int var3 = var2.getRenderType();

            for (int var4 = 0; var4 < var1.length; ++var4)
            {
                if (var1[var4] == var3)
                {
                    return true;
                }
            }

            return false;
        }
        else
        {
            return true;
        }
    }
    
    public static void renderFrameItemWithOverlay(int icon, int var1, ItemStack item, Random var3, int width)
    {
        renderFrameItemWithOverlay(icon, var1, item, var3, RenderManager.instance, width, false, 0);
    }

    public static void renderFrameItemWithOverlay(int var0, int var1, ItemStack var2, Random var3, RenderManager var4, int var5, boolean var6, int var7)
    {
        Tessellator var8 = Tessellator.instance;
        float var9 = (float)(var0 % 16 * 16 + 0) / 256.0F;
        float var10 = (float)(var0 % 16 * 16 + 16) / 256.0F;
        float var11 = (float)(var0 / 16 * 16 + 0) / 256.0F;
        float var12 = (float)(var0 / 16 * 16 + 16) / 256.0F;
        float var13 = 1.0F;
        float var14 = 0.5F;
        float var15 = 0.25F;

        for (int var16 = 0; var16 < var1; ++var16)
        {
            GL11.glPushMatrix();

            if (var16 > 0)
            {
                float var17 = (var3.nextFloat() * 2.0F - 1.0F) * 0.3F;
                float var18 = (var3.nextFloat() * 2.0F - 1.0F) * 0.3F;
                float var19 = (var3.nextFloat() * 2.0F - 1.0F) * 0.3F;
                GL11.glTranslatef(var17, var18, var19);
            }

            if (Settings.getRenderFrameIn3D())
            {
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                bindCorrectTexture(var2);

                if (var6)
                {
                    setColor(var2, var7);
                }

                renderItemIn3D(var8, var10, var11, var9, var12, var5);
                renderEffectOverlay(var0, var1, var2, var4, var5);
                GL11.glPopMatrix();
            }
            else if (!Settings.getRenderFrameIn3D())
            {
            	GL11.glTranslatef(-0.5F, 0.25F, 0.05F);
                //GL11.glRotatef(180.0F - var4.playerViewY, 0.0F, 1.0F, 0.0F);
                var8.startDrawingQuads();
                var8.setNormal(0.0F, 1.0F, 0.0F);
                var8.addVertexWithUV((double)(0.0F - var14), (double)(0.0F - var15), 0.0D, (double)var9, (double)var12);
                var8.addVertexWithUV((double)(var13 - var14), (double)(0.0F - var15), 0.0D, (double)var10, (double)var12);
                var8.addVertexWithUV((double)(var13 - var14), (double)(1.0F - var15), 0.0D, (double)var10, (double)var11);
                var8.addVertexWithUV((double)(0.0F - var14), (double)(1.0F - var15), 0.0D, (double)var9, (double)var11);
                var8.draw();
                GL11.glPopMatrix();
            }
        }
    }
    
    public static void renderFrameItem(int var0, int var1, ItemStack var2, Random var3, RenderManager var4, int var5)
    {
        Tessellator var6 = Tessellator.instance;
        float var7 = (float)(var0 % 16 * 16 + 0) / 256.0F;
        float var8 = (float)(var0 % 16 * 16 + 16) / 256.0F;
        float var9 = (float)(var0 / 16 * 16 + 0) / 256.0F;
        float var10 = (float)(var0 / 16 * 16 + 16) / 256.0F;
        float var11 = 1.0F;
        float var12 = 0.5F;
        float var13 = 0.25F;

        for (int var14 = 0; var14 < var1; ++var14)
        {
            GL11.glPushMatrix();

            if (var14 > 0)
            {
                float var15 = (var3.nextFloat() * 2.0F - 1.0F) * 0.3F;
                float var16 = (var3.nextFloat() * 2.0F - 1.0F) * 0.3F;
                float var17 = (var3.nextFloat() * 2.0F - 1.0F) * 0.3F;
                GL11.glTranslatef(var15, var16, var17);
            }

            if (Settings.getRenderFrameIn3D())
            {
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                renderItemIn3D(var6, var8, var9, var7, var10, var5);
                GL11.glPopMatrix();
            }
            else if (!Settings.getRenderFrameIn3D())
            {
            	GL11.glTranslatef(-0.5F, 0.25F, 0.05F);
                //GL11.glRotatef(180.0F - var4.playerViewY, 0.0F, 1.0F, 0.0F);
                var6.startDrawingQuads();
                var6.setNormal(0.0F, 1.0F, 0.0F);
                var6.addVertexWithUV((double)(0.0F - var12), (double)(0.0F - var13), 0.0D, (double)var7, (double)var10);
                var6.addVertexWithUV((double)(var11 - var12), (double)(0.0F - var13), 0.0D, (double)var8, (double)var10);
                var6.addVertexWithUV((double)(var11 - var12), (double)(1.0F - var13), 0.0D, (double)var8, (double)var9);
                var6.addVertexWithUV((double)(0.0F - var12), (double)(1.0F - var13), 0.0D, (double)var7, (double)var9);
                var6.draw();
                GL11.glPopMatrix();
            }
        }
    }
}