package quintinity.mods.mod3Ditems.blocks;

import java.lang.reflect.Field;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import quintinity.mods.mod3Ditems.ForgeRenderer;
import quintinity.mods.mod3Ditems.ItemRenderer3D;
import quintinity.mods.mod3Ditems.Settings;
import net.minecraft.src.Block;
import net.minecraft.src.ChunkCache;
import net.minecraft.src.EntityItem;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.RenderManager;
import net.minecraft.src.Tessellator;
import net.minecraft.src.World;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BlockRenderer implements ISimpleBlockRenderingHandler 
{
	public boolean renderIn3D;
	
	public static int lilyPadID;
	public static int cobwebID;

	public static void init()
	{
		lilyPadID = RenderingRegistry.getNextAvailableRenderId();
		cobwebID = RenderingRegistry.getNextAvailableRenderId();
	}
	
	public BlockRenderer(boolean renderIn3D)
	{
		this.renderIn3D = renderIn3D;
	}
	
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) 
	{
	}

	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelID, RenderBlocks renderer) 
	{
		if (modelID == lilyPadID) {
			World w = null;
			if (world instanceof World) {
				w = (World)world;
			}
			else if (world instanceof ChunkCache) {
				Field f = ChunkCache.class.getDeclaredFields()[4];
				f.setAccessible(true);
				try {
					w = (World) f.get(world);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			render(block.blockIndexInTexture, ItemRenderer3D.getTextureWidth(), block, w, x, y, z);
	        //new RenderBlocks(world).renderCrossedSquares(block, x, y, z);
			return true;
		}
		return false;
	}

	public boolean shouldRender3DInInventory() 
	{
		return renderIn3D;
	}

	public int getRenderId() 
	{
		return 0;
	}
	
	public static void render(int textureIndex, int width, Block block, World world, int x, int y, int z)
    {
        Tessellator var6 = Tessellator.instance;
        float var7 = (float)(textureIndex % 16 * 16 + 0) / 256.0F;
        float var8 = (float)(textureIndex % 16 * 16 + 16) / 256.0F;
        float var9 = (float)(textureIndex / 16 * 16 + 0) / 256.0F;
        float var10 = (float)(textureIndex / 16 * 16 + 16) / 256.0F;
        float var11 = 1.0F;
        float var12 = 0.5F;
        float var13 = 0.25F;
		var6.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
		var6.setColorOpaque_I(block.getBlockColor());
        renderLilypad(var6, var8, var9, var7, var10, width, x, y + 1, z);
    }
	
	public static void renderLilypad(Tessellator var0, float var1, float var2, float var3, float var4, int var5, double x, double y, double z)
    {
        float var6 = 1.0F;
        float var7 = 0.0425F;
        var0.setNormal(0.0F, 0.0F, 1.0F);
        var0.addVertexWithUV(x, y - var7, z, (double)var1, (double)var4);
        var0.addVertexWithUV((double)var6 + x, y - var7, z, (double)var3, (double)var4);
        var0.addVertexWithUV((double)var6 + x, y - var7, z + 1, (double)var3, (double)var2);
        var0.addVertexWithUV(x, y - var7, z + 1, (double)var1, (double)var2);

        var0.setNormal(0.0F, 0.0F, -1.0F);
        
        var0.addVertexWithUV(x, y, z + 1, (double)var1, (double)var2);
        var0.addVertexWithUV((double)var6 + x, y, (double)z + 1, (double)var3, (double)var2);
        var0.addVertexWithUV((double)var6 + x, y, (double)z, (double)var3, (double)var4);
        var0.addVertexWithUV(x, y, (double)z, (double)var1, (double)var4);
        float var8 = 1.0F / (float)(32 * var5);
        float var9 = 1.0F / (float)var5;
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
            var0.addVertexWithUV((double)var13 + x, y - var7, z, (double)var12, (double)var4);
            var0.addVertexWithUV((double)var13 + x, y, z, (double)var12, (double)var4);
            var0.addVertexWithUV((double)var13 + x, y, z + 1, (double)var12, (double)var2);
            var0.addVertexWithUV((double)var13 + x, y - var7, z + 1, (double)var12, (double)var2);
        }

        var0.setNormal(1.0F, 0.0F, 0.0F);

        for (var10 = 0; var10 < var5; ++var10)
        {
            var11 = (float)var10 / ((float)var5 * 1.0F);
            var12 = var1 + (var3 - var1) * var11 - var8;
            var13 = var6 * var11 + var9;
            var0.addVertexWithUV((double)var13 + x, y - var7, z + 1, (double)var12, (double)var2);
            var0.addVertexWithUV((double)var13 + x, y, z + 1, (double)var12, (double)var2);
            var0.addVertexWithUV((double)var13 + x, y, z, (double)var12, (double)var4);
            var0.addVertexWithUV((double)var13 + x, y - var7, z, (double)var12, (double)var4);
        }

        var0.setNormal(0.0F, 1.0F, 0.0F);

        for (var10 = 0; var10 < var5; ++var10)
        {
            var11 = (float)var10 / ((float)var5 * 1.0F);
            var12 = var4 + (var2 - var4) * var11 - var8;
            var13 = var6 * var11 + var9;
            var0.addVertexWithUV(x, y, z +  var13, (double)var1, (double)var12);
            var0.addVertexWithUV(var6 + x, y, z + var13, (double)var3, (double)var12);
            var0.addVertexWithUV(var6 + x, y - var7, z + var13, (double)var3, (double)var12);
            var0.addVertexWithUV(x, y - var7, z + var13, (double)var1, (double)var12);
        }

        var0.setNormal(0.0F, -1.0F, 0.0F);

        for (var10 = 0; var10 < var5; ++var10)
        {
            var11 = (float)var10 / ((float)var5 * 1.0F);
            var12 = var4 + (var2 - var4) * var11 - var8;
            var13 = var6 * var11;
            var0.addVertexWithUV(var6 + x, var13 + y, 0.0D + z, (double)var3, (double)var12);
            var0.addVertexWithUV(x, (double)var13 + y, 0.0D + z, (double)var1, (double)var12);
            var0.addVertexWithUV(x, (double)var13 + y, (double)(0.0F - var7 + z), (double)var1, (double)var12);
            var0.addVertexWithUV(var6 + x, (double)var13 + y, (double)(0.0F - var7 + z), (double)var3, (double)var12);
        }  
    }
	
	public static void renderItemIn3D(Tessellator var0, float var1, float var2, float var3, float var4, int var5, double x, double y, double z)
    {
        float var6 = 1.0F;
        float var7 = 0.0425F;
        var0.setNormal(0.0F, 0.0F, 1.0F);
        var0.addVertexWithUV(x, y, z, (double)var1, (double)var4);
        var0.addVertexWithUV((double)var6 + x, y, z, (double)var3, (double)var4);
        var0.addVertexWithUV((double)var6 + x, 1.0D + y, z, (double)var3, (double)var2);
        var0.addVertexWithUV(x, y + 1, z, (double)var1, (double)var2);

        var0.setNormal(0.0F, 0.0F, -1.0F);
        
        var0.addVertexWithUV(x, 1.0D + y, (double)(0.0F - var7 + z), (double)var1, (double)var2);
        var0.addVertexWithUV((double)var6 + x, 1.0D + y, (double)(0.0F - var7 + z), (double)var3, (double)var2);
        var0.addVertexWithUV((double)var6 + x, 0.0D + y, (double)(0.0F - var7 + z), (double)var3, (double)var4);
        var0.addVertexWithUV(0.0D + x, 0.0D + y, (double)(0.0F - var7 + z), (double)var1, (double)var4);
        float var8 = 1.0F / (float)(32 * var5);
        float var9 = 1.0F / (float)var5;
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
            var0.addVertexWithUV((double)var13 + x, 0.0D + y, (double)(0.0F - var7 + z), (double)var12, (double)var4);
            var0.addVertexWithUV((double)var13 + x, 0.0D + y, 0.0D + z, (double)var12, (double)var4);
            var0.addVertexWithUV((double)var13 + x, 1.0D + y, 0.0D + z, (double)var12, (double)var2);
            var0.addVertexWithUV((double)var13 + x, 1.0D + y, (double)(0.0F - var7+ z), (double)var12, (double)var2);
        }

        var0.setNormal(1.0F, 0.0F, 0.0F);

        for (var10 = 0; var10 < var5; ++var10)
        {
            var11 = (float)var10 / ((float)var5 * 1.0F);
            var12 = var1 + (var3 - var1) * var11 - var8;
            var13 = var6 * var11 + var9;
            var0.addVertexWithUV((double)var13 + x, 1.0D + y, (double)(0.0F - var7 + z), (double)var12, (double)var2);
            var0.addVertexWithUV((double)var13 + x, 1.0D + y, 0.0D + z, (double)var12, (double)var2);
            var0.addVertexWithUV((double)var13 + x, 0.0D + y, 0.0D + z, (double)var12, (double)var4);
            var0.addVertexWithUV((double)var13 + x, 0.0D + y, (double)(0.0F - var7 + z), (double)var12, (double)var4);
        }

        var0.setNormal(0.0F, 1.0F, 0.0F);

        for (var10 = 0; var10 < var5; ++var10)
        {
            var11 = (float)var10 / ((float)var5 * 1.0F);
            var12 = var4 + (var2 - var4) * var11 - var8;
            var13 = var6 * var11 + var9;
            var0.addVertexWithUV(0.0D + x, (double)var13 + y, 0.0D + z, (double)var1, (double)var12);
            var0.addVertexWithUV((double)var6 + x, (double)var13 + y, 0.0D + z, (double)var3, (double)var12);
            var0.addVertexWithUV((double)var6 + x, (double)var13 + y, (double)(0.0F - var7 + z), (double)var3, (double)var12);
            var0.addVertexWithUV(0.0D + x, (double)var13 + y, (double)(0.0F - var7 + z), (double)var1, (double)var12);
        }

        var0.setNormal(0.0F, -1.0F, 0.0F);

        for (var10 = 0; var10 < var5; ++var10)
        {
            var11 = (float)var10 / ((float)var5 * 1.0F);
            var12 = var4 + (var2 - var4) * var11 - var8;
            var13 = var6 * var11;
            var0.addVertexWithUV((double)var6 + x, (double)var13 + y, 0.0D + z, (double)var3, (double)var12);
            var0.addVertexWithUV(0.0D + x, (double)var13 + y, 0.0D + z, (double)var1, (double)var12);
            var0.addVertexWithUV(0.0D + x, (double)var13 + y, (double)(0.0F - var7 + z), (double)var1, (double)var12);
            var0.addVertexWithUV((double)var6 + x, (double)var13 + y, (double)(0.0F - var7 + z), (double)var3, (double)var12);
        }  
    }
}