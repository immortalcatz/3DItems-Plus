package quintinity.api.settings;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;

public interface IOptionHandler 
{
	public void registerButtons(ArrayList<GuiOptionButton> list);
	
	public OptionDescription getDescription(GuiOptionButton button);
	
	public void buttonClicked(GuiOptionButton button, Minecraft minecraft);
}