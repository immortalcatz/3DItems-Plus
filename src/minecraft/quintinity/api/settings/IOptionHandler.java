package quintinity.api.settings;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;

public interface IOptionHandler 
{
	//public OptionDescription getDescription(OptionButton button);
	
	public void buttonClicked(OptionPage page, OptionButton button, Minecraft minecraft);
	
	public void sliderClicked(OptionPage page, OptionSlider slider, Minecraft minecraft);
}