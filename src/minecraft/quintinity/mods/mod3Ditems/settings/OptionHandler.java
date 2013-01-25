package quintinity.mods.mod3Ditems.settings;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import quintinity.api.settings.*;

public class OptionHandler implements IOptionHandler 
{
	public void registerButtons(ArrayList<OptionButton> list) 
	{
		OptionButton button = new OptionButton(0, "3DItems Settings...");
		button.setIsLink(true);
		list.add(button);
	}

	public OptionDescription getDescription(OptionButton button) 
	{
		return null;
	}

	public void buttonClicked(OptionButton button, Minecraft minecraft) 
	{
		if (button.id == 0) {
			GuiSettings gui = new GuiSettings(minecraft.currentScreen);
			minecraft.displayGuiScreen(gui);
		}
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