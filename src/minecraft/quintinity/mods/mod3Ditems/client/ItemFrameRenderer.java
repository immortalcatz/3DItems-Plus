package quintinity.mods.mod3Ditems.client;
import java.util.Random;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.*;

import org.lwjgl.opengl.GL11;
import quintinity.mods.mod3Ditems.ItemRenderer3D;
import quintinity.mods.mod3Ditems.Settings;

@SideOnly(Side.CLIENT)
public class ItemFrameRenderer extends Render
{
    private final RenderBlocks blockRenderer = new RenderBlocks();
    private Random random;
    
    public ItemFrameRenderer()
    {
    	this.setRenderManager(RenderManager.instance);
    	random = new Random();
    }
    
    public void func_82404_a(EntityItemFrame par1EntityItemFrame, double par2, double par4, double par6, float par8, float par9)
    {
        GL11.glPushMatrix();
        float var10 = (float)(par1EntityItemFrame.posX - par2) - 0.5F;
        float var11 = (float)(par1EntityItemFrame.posY - par4) - 0.5F;
        float var12 = (float)(par1EntityItemFrame.posZ - par6) - 0.5F;
        int var13 = par1EntityItemFrame.xPosition + Direction.offsetX[par1EntityItemFrame.field_82332_a];
        int var14 = par1EntityItemFrame.yPosition;
        int var15 = par1EntityItemFrame.zPosition + Direction.offsetZ[par1EntityItemFrame.field_82332_a];
        GL11.glTranslatef((float)var13 - var10, (float)var14 - var11, (float)var15 - var12);
        this.func_82403_a(par1EntityItemFrame);
        this.func_82402_b(par1EntityItemFrame);
        GL11.glPopMatrix();
    }

    private void func_82403_a(EntityItemFrame par1EntityItemFrame)
    {
        GL11.glPushMatrix();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.renderManager.renderEngine.getTexture("/terrain.png"));
        GL11.glRotatef(par1EntityItemFrame.rotationYaw, 0.0F, 1.0F, 0.0F);
        Block var2 = Block.netherBrick;
        float var3 = 0.0625F;
        float var4 = 0.75F;
        float var5 = var4 / 2.0F;
        GL11.glPushMatrix();
        blockRenderer.func_83019_b(0.0D, (double)(0.5F - var5 + 0.0625F), (double)(0.5F - var5 + 0.0625F), (double)(var3 * 0.5F), (double)(0.5F + var5 - 0.0625F), (double)(0.5F + var5 - 0.0625F));
        blockRenderer.func_82774_a(185);
        blockRenderer.renderBlockAsItem(var2, 0, 1.0F);
        blockRenderer.clearOverrideBlockTexture();
        blockRenderer.func_83017_b();
        GL11.glPopMatrix();
        blockRenderer.func_82774_a(214);
        GL11.glPushMatrix();
        blockRenderer.func_83019_b(0.0D, (double)(0.5F - var5), (double)(0.5F - var5), (double)(var3 + 1.0E-4F), (double)(var3 + 0.5F - var5), (double)(0.5F + var5));
        blockRenderer.renderBlockAsItem(var2, 0, 1.0F);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        blockRenderer.func_83019_b(0.0D, (double)(0.5F + var5 - var3), (double)(0.5F - var5), (double)(var3 + 1.0E-4F), (double)(0.5F + var5), (double)(0.5F + var5));
        blockRenderer.renderBlockAsItem(var2, 0, 1.0F);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        blockRenderer.func_83019_b(0.0D, (double)(0.5F - var5), (double)(0.5F - var5), (double)var3, (double)(0.5F + var5), (double)(var3 + 0.5F - var5));
        blockRenderer.renderBlockAsItem(var2, 0, 1.0F);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        blockRenderer.func_83019_b(0.0D, (double)(0.5F - var5), (double)(0.5F + var5 - var3), (double)var3, (double)(0.5F + var5), (double)(0.5F + var5));
        blockRenderer.renderBlockAsItem(var2, 0, 1.0F);
        GL11.glPopMatrix();
        blockRenderer.func_83017_b();
        blockRenderer.clearOverrideBlockTexture();
        GL11.glPopMatrix();
    }

    private void func_82402_b(EntityItemFrame par1EntityItemFrame)
    {
        ItemStack var2 = par1EntityItemFrame.func_82335_i();

        if (var2 != null)
        {
            EntityItem var3 = new EntityItem(par1EntityItemFrame.worldObj, 0.0D, 0.0D, 0.0D, var2);
            var3.item.stackSize = 1;
            var3.hoverStart = 0.0F;
            GL11.glPushMatrix();
            GL11.glTranslatef(-0.453125F * (float)Direction.offsetX[par1EntityItemFrame.field_82332_a], -0.18F, -0.453125F * (float)Direction.offsetZ[par1EntityItemFrame.field_82332_a]);
            GL11.glRotatef(180.0F + par1EntityItemFrame.rotationYaw, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef((float)(-90 * par1EntityItemFrame.getRotation()), 0.0F, 0.0F, 1.0F);

            switch (par1EntityItemFrame.getRotation())
            {
                case 1:
                    GL11.glTranslatef(-0.16F, -0.16F, 0.0F);
                    break;
                case 2:
                    GL11.glTranslatef(0.0F, -0.32F, 0.0F);
                    break;
                case 3:
                    GL11.glTranslatef(0.16F, -0.16F, 0.0F);
            }

            if (var3.item.getItem() == Item.map)
            {
                this.renderManager.renderEngine.bindTexture(this.renderManager.renderEngine.getTexture("/misc/mapbg.png"));
                Tessellator var4 = Tessellator.instance;
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScalef(0.00390625F, 0.00390625F, 0.00390625F);
                GL11.glTranslatef(-65.0F, -107.0F, -1.0F);
                GL11.glNormal3f(0.0F, 0.0F, -1.0F);
                var4.startDrawingQuads();
                byte var5 = 7;
                var4.addVertexWithUV((double)(0 - var5), (double)(128 + var5), 0.0D, 0.0D, 1.0D);
                var4.addVertexWithUV((double)(128 + var5), (double)(128 + var5), 0.0D, 1.0D, 1.0D);
                var4.addVertexWithUV((double)(128 + var5), (double)(0 - var5), 0.0D, 1.0D, 0.0D);
                var4.addVertexWithUV((double)(0 - var5), (double)(0 - var5), 0.0D, 0.0D, 0.0D);
                var4.draw();
                MapData var6 = Item.map.getMapData(var3.item, par1EntityItemFrame.worldObj);

                if (var6 != null)
                {
                    this.renderManager.itemRenderer.mapItemRenderer.renderMap((EntityPlayer)null, this.renderManager.renderEngine, var6);
                }
            }
            else
            {
                if (var3.item.getItem() == Item.compass)
                {
                    double var8 = TextureCompassFX.field_82391_c.field_76868_i;
                    double var10 = TextureCompassFX.field_82391_c.field_76866_j;
                    TextureCompassFX.field_82391_c.field_76868_i = 0.0D;
                    TextureCompassFX.field_82391_c.field_76866_j = 0.0D;
                    TextureCompassFX.func_82390_a(par1EntityItemFrame.posX, par1EntityItemFrame.posZ, (double)MathHelper.wrapAngleTo180_float((float)(180 + par1EntityItemFrame.field_82332_a * 90)), false, true);
                    TextureCompassFX.field_82391_c.field_76868_i = var8;
                    TextureCompassFX.field_82391_c.field_76866_j = var10;
                    this.renderManager.renderEngine.func_82772_a(TextureCompassFX.field_82391_c, -1);
                }

                float var9 = this.renderManager.playerViewY;
                this.renderManager.playerViewY = 180.0F;
                RenderItem.field_82407_g = true;
                //RenderManager.instance.renderEntityWithPosYaw(var3, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
                
                renderItem(var3.item);
                RenderItem.field_82407_g = false;
                this.renderManager.playerViewY = var9;
                GL11.glEnable(GL11.GL_LIGHTING);

                if (var3.item.getItem() == Item.compass)
                {
                    TextureCompassFX.field_82391_c.onTick();
                    this.renderManager.renderEngine.func_82772_a(TextureCompassFX.field_82391_c, -1);
                }
            }

            GL11.glPopMatrix();
        }
    }
    
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.func_82404_a((EntityItemFrame)par1Entity, par2, par4, par6, par8, par9);
    }
    
    private void renderItem(ItemStack item)
    {
    	int width = ItemRenderer3D.getTextureWidth();
    	int itemCount = 1;
        int icon;
    	float var16, var17, var18;

    	if (item.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.blocksList[item.itemID].getRenderType()))
        {
        	GL11.glScalef(0.3F, 0.3F, 0.3F);
        	GL11.glTranslatef(0F, 0.6F, 0F);
            ItemRenderer3D.loadTexture(Block.blocksList[item.itemID].getTextureFile());
            float var24 = 0.25F;
            int type = Block.blocksList[item.itemID].getRenderType();

            if (type == 1 || type == 19 || type == 12 || type == 2)
            {
                var24 = 0.5F;
            }
            for (int var23 = 0; var23 < itemCount; ++var23)
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
                GL11.glRotatef(-90F, 0F, 1F, 0F);
                blockRenderer.renderBlockAsItem(Block.blocksList[item.itemID], item.getItemDamage(), var18);
                GL11.glPopMatrix();
            }
        }
        else
        {
            int var19;
            float var20;
        	GL11.glScalef(0.5F, 0.5F, 0.5F);
        	GL11.glTranslatef(0.5F, -0.1F, -0.05F);
            if (item.getItem().requiresMultipleRenderPasses())
            {
                ItemRenderer3D.loadTexture(Item.itemsList[item.itemID].getTextureFile());

                for (var19 = 0; var19 <= item.getItem().getRenderPasses(item.getItemDamage()); ++var19)
                {
                    //GL11.glTranslatef(0.5F, 0.0F, 0.0F);
                    random.setSeed(187L);
                    icon = item.getItem().getIconFromDamageForRenderPass(item.getItemDamage(), var19);
                    var20 = 1.0F;

                    if (true)
                    {
                        int var21 = Item.itemsList[item.itemID].getColorFromItemStack(item, var19);
                        var16 = (float)(var21 >> 16 & 255) / 255.0F;
                        var17 = (float)(var21 >> 8 & 255) / 255.0F;
                        float var22 = (float)(var21 & 255) / 255.0F;
                        GL11.glColor4f(var16 * var20, var17 * var20, var22 * var20, 1.0F);
                    }

                    if (ItemRenderer3D.leatherIDs.contains(item.itemID)) {
                    	if (var19 < 2) { ItemRenderer3D.renderFrameItemWithOverlay(icon, itemCount, item, random, RenderManager.instance, width, true, var19); }
                    	else {
                    		ItemRenderer3D.renderFrameItemWithOverlay(icon, itemCount, item, random, RenderManager.instance, width, true, 0); }
                    }
                    else if (var19 == 2)
                    {
                        ItemRenderer3D.renderFrameItemWithOverlay(icon, itemCount, item, random, RenderManager.instance, width, true, var19);
                    }
                    else
                    {
                        ItemRenderer3D.renderFrameItem(icon, itemCount, item, random, RenderManager.instance, width);
                    }
                }
            }
            else
            {
            	//GL11.glScalef(0.5F, 0.5F, 0.5F);
            	//GL11.glTranslatef(0.5F, -0.1F, -0.05F);
                var19 = item.getIconIndex();
                ItemRenderer3D.loadTexture(item.getItem().getTextureFile());
                if (true)
                {
                    icon = Item.itemsList[item.itemID].getColorFromItemStack(item, 0);
                    var20 = (float)(icon >> 16 & 255) / 255.0F;
                    var18 = (float)(icon >> 8 & 255) / 255.0F;
                    var16 = (float)(icon & 255) / 255.0F;
                    var17 = 1.0F;
                    GL11.glColor4f(var20 * var17, var18 * var17, var16 * var17, 1.0F);
                }
                ItemRenderer3D.renderFrameItemWithOverlay(var19, itemCount, item, random, width);
            }
        }
    }
}
