package quintinity.mods.mod3Ditems;
import java.util.Random;
import org.lwjgl.opengl.GL11;
import quintinity.api.ItemIconManager;
import quintinity.mods.mod3Ditems.modhelpers.*;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.*;
import net.minecraft.src.*;
import net.minecraft.util.*;
import net.minecraftforge.client.IItemRenderer;

public class ForgeRenderer implements IItemRenderer 
{
	public Random random = new Random();
	public IItemRenderer origRenderer = null;
	
	public ForgeRenderer(IItemRenderer orig)
	{
		origRenderer = orig;
	}
	
	public ForgeRenderer() {}
	
	public boolean handleRenderType(ItemStack item, ItemRenderType type) 
	{
		return type == ItemRenderType.ENTITY || handleOrigRenderType(item, type);
	}

	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) 
	{
		if (helper == ItemRendererHelper.ENTITY_BOBBING) {
			return Settings.getItemBobbing();
		}
		return shouldUseOrigRenderHelper(type, item, helper);
	}

	public boolean handleOrigRenderType(ItemStack item, ItemRenderType type)
	{
		if (origRenderer == null) {
			return false;
		}
		return origRenderer.handleRenderType(item, type);
	}
	
	public boolean shouldUseOrigRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		if (origRenderer == null) {
			return false;
		}
		return origRenderer.shouldUseRenderHelper(type, item, helper);
	}
	
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) 
	{
		if (!(type == ItemRenderType.ENTITY)) {
			if (handleOrigRenderType(item, type)) {
				origRenderer.renderItem(type, item, data);
			}
		}
		else {
			float itemScale = Settings.getItemScale();
			float blockScale = Settings.getBlockScale();
			RenderBlocks blockRenderer = (RenderBlocks)data[0];
			EntityItem entity = (EntityItem)data[1];
			Timer timer = RenderHelper3D.timer;
			int width = RenderHelper3D.getTextureWidth();
	        random.setSeed(187L);
	        float var12 = MathHelper.sin(((float)entity.age + timer.renderPartialTicks) / 10.0F + entity.hoverStart) * 0.1F + 0.1F;
	        float var13 = (((float)entity.age + timer.renderPartialTicks) / 20.0F + entity.hoverStart) * (180F / (float)Math.PI);
	        byte var14 = 1;
	        
	        if (ModRenderHelper.isBuildCraftItem(entity)) {
	        	BuildCraftRenderHelper.renderAsPipedItem(entity);
	        	return;
	        }
	        
	        if (entity.func_92014_d().stackSize > 1)
	        {
	            var14 = 2;
	        }
	
	        if (entity.func_92014_d().stackSize > 5)
	        {
	            var14 = 3;
	        }
	
	        if (entity.func_92014_d().stackSize > 20)
	        {
	            var14 = 4;
	        }
			int var15;
	        float var17;
	        float var16;
	        float var18;
	        
	        if (item.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.blocksList[item.itemID].getRenderType()))
	        {
	            GL11.glRotatef(var13, 0.0F, 1.0F, 0.0F);
	            RenderHelper3D.loadTexture(Block.blocksList[item.itemID].getTextureFile());
	            float var24 = 0.25F;
	            var15 = Block.blocksList[item.itemID].getRenderType();
	
	            if (var15 == 1 || var15 == 19 || var15 == 12 || var15 == 2)
	            {
	                var24 = 0.5F;
	            }
	
	            GL11.glScalef(blockScale, blockScale, blockScale);
	
	            for (int var23 = 0; var23 < var14; ++var23)
	            {
	                GL11.glPushMatrix();
	
	                if (var23 > 0)
	                {
	                    var18 = (random.nextFloat() * 2.0F - 1.0F) * 0.2F / var24;
	                    var16 = (random.nextFloat() * 2.0F - 1.0F) * 0.2F / var24;
	                    var17 = (random.nextFloat() * 2.0F - 1.0F) * 0.2F / var24;
	                    GL11.glTranslatef(var18, var16, var17);
	                }
	
	                var18 = 1.0F;
	                GL11.glTranslatef(0F, (float)((blockScale - 1) * 0.3), 0F);
	                blockRenderer.renderBlockAsItem(Block.blocksList[item.itemID], item.getItemDamage(), var18);
	                GL11.glPopMatrix();
	            }
	        }
	        else
	        {
		        if (!Settings.getItemRotation()) {
		        	var13 = 0F;
		        }
	            int var19;
	            float var20;
	
	            if (item.getItem().requiresMultipleRenderPasses())
	            {
	                GL11.glScalef(itemScale, itemScale, itemScale);
	                RenderHelper3D.loadTexture(Item.itemsList[item.itemID].getTextureFile());
	
	                for (var19 = 0; var19 <= item.getItem().getRenderPasses(item.getItemDamage()); ++var19)
	                {
	                    if (Settings.getRenderIn3D())
	                    {
	                        GL11.glRotatef(var13, 0.0F, 1.0F, 0.0F);
	
	                        if (var19 == 0)
	                        {
	                            GL11.glTranslatef(0.5F, 0.0F, 0.0F);
	                        }
	                    }
	                    random.setSeed(187L);
	                    var15 = item.getItem().getIconFromDamageForRenderPass(item.getItemDamage(), var19);
	                    var20 = 1.0F;
	
	                    if (true)
	                    {
	                        int var21 = Item.itemsList[item.itemID].getColorFromItemStack(item, var19);
	                        var16 = (float)(var21 >> 16 & 255) / 255.0F;
	                        var17 = (float)(var21 >> 8 & 255) / 255.0F;
	                        float var22 = (float)(var21 & 255) / 255.0F;
	                        GL11.glColor4f(var16 * var20, var17 * var20, var22 * var20, 1.0F);
	                    }
	
	                    if (RenderHelper3D.leatherIDs.contains(item.itemID)) {
	                    	if (var19 < 2) { RenderHelper3D.renderWithOverlay(var15, var14, entity.func_92014_d(), random, width, true, var19); }
	                    	else {
	                    		RenderHelper3D.renderWithOverlay(var15, var14, entity.func_92014_d(), random, width, true, 0); 
	                    	}
	                    	
	                    }
	                    else if (var19 == 2)
	                    {
	                        RenderHelper3D.renderWithOverlay(var15, var14, entity.func_92014_d(), random, width, true, var19);
	                    }
	                    else
	                    {
	                        RenderHelper3D.render(var15, var14, entity.func_92014_d(), random, RenderManager.instance, width);
	                    }
	
	                    if (Settings.getRenderIn3D())
	                    {
	                        GL11.glRotatef(-var13, 0.0F, 1.0F, 0.0F);
	                    }
	                }
	            }
	            else
	            {
	            	GL11.glScalef(itemScale, itemScale, itemScale);
	                //var19 = item.getIconIndex();
	            	var19 = ItemIconManager.getIconForItem(item);
	            	RenderHelper3D.loadTexture(ItemIconManager.getTextureFile(item));
	                //ItemRenderer3D.loadTexture(item.getItem().getTextureFile());
	
	                if (Settings.getRenderIn3D())
	                {
	                    GL11.glRotatef(var13, 0.0F, 1.0F, 0.0F);
	                    GL11.glTranslatef(0.5F, 0.0F, 0.0F);
	                }
	                else if (!Settings.getRenderIn3D())
	                {
	                    //GL11.glRotatef(180.0F - RenderManager.instance.playerViewY, 0.0F, 1.0F, 0.0F);
	                }
	
	                if (true)
	                {
	                    var15 = Item.itemsList[item.itemID].getColorFromItemStack(item, 0);
	                    var20 = (float)(var15 >> 16 & 255) / 255.0F;
	                    var18 = (float)(var15 >> 8 & 255) / 255.0F;
	                    var16 = (float)(var15 & 255) / 255.0F;
	                    var17 = 1.0F;
	                    GL11.glColor4f(var20 * var17, var18 * var17, var16 * var17, 1.0F);
	                }
	
	                RenderHelper3D.renderWithOverlay(var19, var14, entity.func_92014_d(), random, width);
	            }
	        }
		}
	}
}