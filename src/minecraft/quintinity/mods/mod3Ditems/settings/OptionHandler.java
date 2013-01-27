package quintinity.mods.mod3Ditems.settings;
import static quintinity.api.TextColor.*;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.StringTranslate;
import quintinity.api.settings.*;
import quintinity.mods.mod3Ditems.Mod3DItems;

public class OptionHandler implements IOptionHandler 
{
	public OptionPage page;
	public OptionButton rotation;
	public StringTranslate st;
	
	public OptionHandler()
	{
		st = StringTranslate.getInstance();
		page = new OptionPage("3DItems Settings", "main", "3DItems Settings...", this);
		page.addButton(new OptionButton(1, "3D Items: " + Utils.booleanToString(Settings.getRenderIn3D())));
		page.addButton(new OptionSlider(3, "Item Scale: " + Float.toString(Settings.getItemScale()).substring(0, 3), Settings.getItemScale()));
		page.addButton(new OptionSlider(4, "Block Scale: " + Float.toString(Settings.getBlockScale()).substring(0, 3), Settings.getBlockScale()));
		page.addButton(new OptionButton(5, "Item Bobbing: " + Utils.booleanToString(Settings.getItemBobbing())));
		page.addButton(rotation = new OptionButton(6, "Item Rotation: " + Utils.booleanToString(Settings.getItemRotation())));
		page.addButton(new OptionButton(7, "Item Render Distance: " + YELLOW + Utils.trimFloat(Settings.getRenderDistance()) + "x"));
        rotation.enabled = Settings.getRenderIn3D();
		SettingsAPI.registerMainOptionPage(page);
	}
	
	public void buttonClicked(OptionPage page, OptionButton button, Minecraft minecraft) 
	{
		if (button.id == 0) {
			GuiSettings gui = new GuiSettings(minecraft.currentScreen);
			minecraft.displayGuiScreen(gui);
		}
		if (button.enabled)
        {
        	if (button.id == 1) {
        		Settings.setRenderIn3D(!Settings.getRenderIn3D());
        		button.displayString = "3D Items: " + Utils.booleanToString(Settings.getRenderIn3D());
        		rotation.enabled = Settings.getRenderIn3D();
        	}
        	else if (button.id == 2) {
        		Settings.setRenderFrameIn3D(!Settings.getRenderFrameIn3D());
        		button.displayString = "3D Frame Items: " + Utils.booleanToString(Settings.getRenderFrameIn3D());
        	}
        	else if (button.id == 5) {
        		Settings.setItemBobbing(!Settings.getItemBobbing());
        		button.displayString = "Item Bobbing: " + Utils.booleanToString(Settings.getItemBobbing());
        	}
        	else if (button.id == 6) {
        		Settings.setItemRotation(!Settings.getItemRotation());
        		button.displayString = "Item Rotation: " + Utils.booleanToString(Settings.getItemRotation());
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
        		button.displayString = "Item Render Distance: " + YELLOW + Utils.trimFloat(Settings.getRenderDistance()) + "x";
        	}
        	saveSettings();
        }
	}
	
	public void saveSettings()
	{
		Settings settings = Mod3DItems.instance.settings;
		settings.setProperty("Enabled", Settings.getRenderIn3D());
		settings.setProperty("3DFrameItems", Settings.getRenderFrameIn3D());
    	settings.setProperty("ItemScale", Utils.trimFloat(Settings.getItemScale()));
    	settings.setProperty("BlockScale", Utils.trimFloat(Settings.getBlockScale()));
    	settings.setProperty("ItemBobbing", Settings.getItemBobbing());
    	settings.setProperty("ItemRotation", Settings.getItemRotation());
    	settings.setProperty("RenderDistance", Settings.getRenderDistance());
    	settings.save();
	}
	
	public void sliderClicked(OptionPage page, OptionSlider slider, Minecraft minecraft) 
	{
		if (slider.id == 3) {
    		Settings.setItemScale(Float.parseFloat(Double.toString(((1.5 * slider.sliderValue) + 0.5)).substring(0, 3)));
    		slider.displayString = "Item Scale: " + Float.toString(Settings.getItemScale()).substring(0, 3);
    	}
    	else if (slider.id == 4) {
    		Settings.setBlockScale(Float.parseFloat(Double.toString(((1.5 * slider.sliderValue) + 0.5)).substring(0, 3)));
    		slider.displayString = "Block Scale: " + Float.toString(Settings.getBlockScale()).substring(0, 3);
    	}
		saveSettings();
	}
	
	public static OptionDescription getButtonDescription(OptionButton button) 
	{
		OptionDescription desc = new OptionDescription();
		if (button.id == 1) {
			desc.setLine("Item entity rendering type", 0);
			desc.setLine("ON - Item entities are rendered as 3D models", 2);
			desc.setLine("OFF - Item entities are rendered as flat 2D textures", 3);
			return desc;
		}
		if (button.id == 2) {
			desc.setLine("Item Frame entity rendering type", 0);
			desc.setLine("ON - Items in item frames are rendered as 3D models", 2);
			desc.setLine("OFF - Items in item frames are rendered as 2D textures", 3);
			return desc;
		}
		if (button.id == 3) {
			desc.setLine("Item entity size", 0);
			desc.setLine("The size of item entities, between 0.5 and 2.0. This value", 2);
			desc.setLine("does not effect pickup distance.", 3);
			return desc;
		}
		if (button.id == 4) {
			desc.setLine("Block entity size", 0);
			desc.setLine("The size of block entities, between 0.5 and 2.0. This value", 2);
			desc.setLine("does not effect pickup distance.", 3);
			return desc;
		}
		return null;
	}
}