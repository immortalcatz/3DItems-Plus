package quintinity.mods.mod3Ditems.settings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import quintinity.api.settings.GuiMoreOptions;
import quintinity.api.settings.IOptionHandler;
import quintinity.api.settings.SettingsAPI;

public class OptionButton extends GuiButton 
{
	public GuiMoreOptions parent;
	private boolean isMouseOver = false;
	private long startTime;
	
	public OptionButton(int id, int x, int y, String text)
    {
        super(id, x, y, 150, 20, text);
    }
    
    public void drawButton(Minecraft minecraft, int x, int y)
    {
    	super.drawButton(minecraft,x, y);
        if (this.drawButton)
        {
        	FontRenderer fontRenderer = GuiMoreOptions.getFontRenderer();
            boolean mouseOver = x >= this.xPosition && y >= this.yPosition && x < this.xPosition + this.width && y < this.yPosition + this.height;
            int hoverState = this.getHoverState(mouseOver);
            if (hoverState == 2 && !isMouseOver) {
            	startTime = System.currentTimeMillis();
            	isMouseOver = true;
            }
            else if (hoverState == 2 && isMouseOver) {
            	if (System.currentTimeMillis() - startTime > 1000) {
            		GuiSettings.setCurrentDescription(OptionHandler.getButtonDescription(this));
            	}
            }
            else if (hoverState != 2 && isMouseOver) {
            	isMouseOver = false;
            }
        }
    }
    
    public void setText(String text)
    {
    	this.displayString = text;
    }
}