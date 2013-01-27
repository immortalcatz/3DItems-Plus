package quintinity.mods.mod3Ditems.settings;
import java.text.DecimalFormat;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import quintinity.api.settings.GuiMoreOptions;
import quintinity.api.settings.IOptionHandler;
import quintinity.api.settings.OptionButton;
import quintinity.api.settings.OptionDescription;
import quintinity.api.settings.SettingsAPI;
import quintinity.mods.mod3Ditems.Mod3DItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.src.*;
import net.minecraft.util.StringTranslate;
import cpw.mods.fml.client.FMLClientHandler;
import static quintinity.api.TextColor.*;

public class GuiSettings extends GuiScreen
{
    private final GuiScreen parentScreen;
    protected String screenTitle = "3DItems Settings";
	public OptionDescription currentDesc;
	public static Minecraft minecraft;
	private OptionButton rotation;
	
    public GuiSettings(GuiScreen gui)
    {
        this.parentScreen = gui;
        minecraft = FMLClientHandler.instance().getClient();
    }

    public void initGui()
    {/*
        StringTranslate st = StringTranslate.getInstance();
        //23 height
        this.controlList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, st.translateKey("gui.done")));
        this.controlList.add(new OptionButton(1, this.width / 2 - 152, this.height / 6, "3D Items: " + booleanToString(Settings.getRenderIn3D())));
        //this.controlList.add(new OptionButton(2, this.width / 2 + 2, this.height / 6, "3D Frame Items: " + booleanToString(Settings.getRenderFrameIn3D())));
        this.controlList.add(new OptionSlider(3, this.width / 2 - 152, this.height / 6 + 23, "Item Scale: " + Float.toString(Settings.getItemScale()).substring(0, 3), this, Settings.getItemScale()));
        this.controlList.add(new OptionSlider(4, this.width / 2 + 2, this.height / 6 + 23, "Block Scale: " + Float.toString(Settings.getBlockScale()).substring(0, 3), this, Settings.getBlockScale()));
        this.controlList.add(new OptionButton(5, this.width / 2 - 152, this.height / 6 + 46, "Item Bobbing: " + booleanToString(Settings.getItemBobbing())));
        this.controlList.add(rotation = new OptionButton(6, this.width / 2 + 2, this.height / 6 + 46, "Item Rotation: " + booleanToString(Settings.getItemRotation())));
        this.controlList.add(new OptionButton(7, this.width / 2 + 2, this.height / 6, "Item Render Distance: " + YELLOW + trimFloat(Settings.getRenderDistance()) + "x"));
        rotation.enabled = Settings.getRenderIn3D();
        */
    }

    protected void actionPerformed(GuiButton button)
    {
    	/*
        if (button.enabled)
        {
        	if (button.id == 200) {
        		Settings settings = Mod3DItems.instance.settings;
        		settings.setProperty("Enabled", Settings.getRenderIn3D());
        		settings.setProperty("3DFrameItems", Settings.getRenderFrameIn3D());
            	settings.setProperty("ItemScale", trimFloat(Settings.getItemScale()));
            	settings.setProperty("BlockScale", trimFloat(Settings.getBlockScale()));
            	settings.setProperty("ItemBobbing", Settings.getItemBobbing());
            	settings.setProperty("ItemRotation", Settings.getItemRotation());
            	settings.setProperty("RenderDistance", Settings.getRenderDistance());
            	settings.save();
        		mc.displayGuiScreen(parentScreen);
        	}
        	else if (button.id == 1) {
        		Settings.setRenderIn3D(!Settings.getRenderIn3D());
        		button.displayString = "3D Items: " + booleanToString(Settings.getRenderIn3D());
        		rotation.enabled = Settings.getRenderIn3D();
        	}
        	else if (button.id == 2) {
        		Settings.setRenderFrameIn3D(!Settings.getRenderFrameIn3D());
        		button.displayString = "3D Frame Items: " + booleanToString(Settings.getRenderFrameIn3D());
        	}
        	else if (button.id == 3) {
        		OptionSlider slider = (OptionSlider)button;
        		Settings.setItemScale(Float.parseFloat(Double.toString(((1.5 * slider.sliderValue) + 0.5)).substring(0, 3)));
        		button.displayString = "Item Scale: " + Float.toString(Settings.getItemScale()).substring(0, 3);
        	}
        	else if (button.id == 4) {
        		OptionSlider slider = (OptionSlider)button;
        		Settings.setBlockScale(Float.parseFloat(Double.toString(((1.5 * slider.sliderValue) + 0.5)).substring(0, 3)));
        		button.displayString = "Block Scale: " + Float.toString(Settings.getBlockScale()).substring(0, 3);
        	}
        	else if (button.id == 5) {
        		Settings.setItemBobbing(!Settings.getItemBobbing());
        		button.displayString = "Item Bobbing: " + booleanToString(Settings.getItemBobbing());
        	}
        	else if (button.id == 6) {
        		Settings.setItemRotation(!Settings.getItemRotation());
        		button.displayString = "Item Rotation: " + booleanToString(Settings.getItemRotation());
        	}
        	else if (button.id == 7) {
        		Settings.setRenderDistance(Settings.getRenderDistance() + 0.5F);
        		if (Settings.getRenderDistance() > 3.0F) {
        			Settings.setRenderDistance(1F);
        		}
        		WorldClient world = minecraft.theWorld;
        		if (world != null) {
	        		for (int i = 0; i < world.loadedEntityList.size(); i++) {
	        			Entity entity = (Entity)world.loadedEntityList.get(i);
	        			if (entity instanceof EntityItem) {
	        				entity.renderDistanceWeight = 1.0D * Settings.getRenderDistance();
	        			}
	        		}
        		}
        		button.displayString = "Item Render Distance: " + YELLOW + trimFloat(Settings.getRenderDistance()) + "x";
        	}
        }*/
    }

    public void drawScreen(int x, int y, float ticks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, this.screenTitle, this.width / 2, 20, 16777215);
        super.drawScreen(x, y, ticks);
        int offset = 0;
        if (y + 20 > this.height / 2) { offset = -100; }
        if (currentDesc != null) {
        	drawDescription(currentDesc, x, y, offset);
        	currentDesc = null;
        }
    }
    
    protected void drawDescription(OptionDescription desc, int x, int y, int yOffset)
    {
    	String[] lines = desc.getLines();
    	FontRenderer fontRenderer = GuiMoreOptions.getFontRenderer();
        int var4 = 300;
        int var5 = width / 2 - 150;
        int var6 = height / 6 + 75 + yOffset;
        byte var8 = 88;
        /*GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);

        this.zLevel = 300.0F;
        int var9 = -267386864;
        this.drawGradientRect(var5 - 3, var6 - 4, var5 + var4 + 3, var6 - 3, var9, var9);
        this.drawGradientRect(var5 - 3, var6 + var8 + 3, var5 + var4 + 3, var6 + var8 + 4, var9, var9);
        this.drawGradientRect(var5 - 3, var6 - 3, var5 + var4 + 3, var6 + var8 + 3, var9, var9);
        this.drawGradientRect(var5 - 4, var6 - 3, var5 - 3, var6 + var8 + 3, var9, var9);
        this.drawGradientRect(var5 + var4 + 3, var6 - 3, var5 + var4 + 4, var6 + var8 + 3, var9, var9);
        int var10 = 1347420415;
        int var11 = (var10 & 16711422) >> 1 | var10 & -16777216;
        this.drawGradientRect(var5 - 3, var6 - 3 + 1, var5 - 3 + 1, var6 + var8 + 3 - 1, var10, var11);
        this.drawGradientRect(var5 + var4 + 2, var6 - 3 + 1, var5 + var4 + 3, var6 + var8 + 3 - 1, var10, var11);
        this.drawGradientRect(var5 - 3, var6 - 3, var5 + var4 + 3, var6 - 3 + 1, var10, var10);
        this.drawGradientRect(var5 - 3, var6 + var8 + 2, var5 + var4 + 3, var6 + var8 + 3, var11, var11);*/
    	this.drawGradientRect(var5 - 3, var6 - 3, var5 + var4 + 3, var6 + var8 + 3, -1073741824, -1073741824);
        for (int i = 0; i < lines.length; i++) {
        	fontRenderer.drawStringWithShadow(lines[i], var5, var6 + (11 * i), -1);
        }
        //this.zLevel = 0.0F;
    }
    
    public static void setCurrentDescription(OptionDescription desc)
    {
    	if (minecraft.currentScreen != null && minecraft.currentScreen instanceof GuiSettings) {
    		((GuiSettings)minecraft.currentScreen).currentDesc = desc;
    	}
    }
    
    public String booleanToString(boolean b) 
    {
    	if (b) { return LIME + "ON"; }
    	return RED + "OFF";
    }
    
    public String trimFloat(float f)
    {
    	return Float.toString(f).substring(0, 3);
    }
}