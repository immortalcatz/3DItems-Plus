package quintinity.api.settings;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;

public interface IOptionHandler 
{
	public void registerButtons(ArrayList<OptionButton> list);
	
	public OptionDescription getDescription(OptionButton button);
	
	public void buttonClicked(OptionButton button, Minecraft minecraft);
}