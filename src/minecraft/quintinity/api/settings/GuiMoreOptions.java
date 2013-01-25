package quintinity.api.settings;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.src.*;
import net.minecraft.util.StringTranslate;

public class GuiMoreOptions extends GuiScreen
{
	public GuiScreen parent;
	public GuiButton doneButton;
	public GuiButton prevButton;
	public GuiButton nextButton;
	public SettingsAPI options;
	private static Minecraft minecraft;
	public OptionDescription currentDesc;
	private int currentPage = 0;
	private final int buttonsPerPage = 14;
	private int pageCount = 1;
	private ArrayList<ArrayList<OptionButton>> pages;
	
	
	public GuiMoreOptions(GuiScreen parent)
	{
		this.parent = parent;
		this.options = SettingsAPI.getInstance();
		minecraft = FMLClientHandler.instance().getClient();
	}
	
	public void initGui(int page)
	{
		currentPage = page;
		this.controlList.clear();
		ArrayList<OptionButton> buttons = SettingsAPI.getInstance().buttons;
		pageCount = (int)Math.ceil((float)buttons.size() / (float)buttonsPerPage);
		StringTranslate translater = StringTranslate.getInstance();
		this.controlList.add(doneButton = new GuiButton(0, this.width / 2 - 100, this.height / 6 + 168, translater.translateKey("gui.done")));
		this.controlList.add(prevButton = new GuiButton(0, this.width / 2 - 130, this.height / 6 + 168, 20, 20, "<"));
		this.controlList.add(nextButton = new GuiButton(0, this.width / 2 + 110, this.height / 6 + 168, 20, 20, ">"));
		toggleButtons(page);
		int count = 0;
		if (buttons.size() > 0) {
			for (int i = page * buttonsPerPage; i < ((page + 1) * buttonsPerPage); i++) {
				try {
					OptionButton button = buttons.get(i);
					if (i % 2 == 0) { button.xPosition = this.width / 2 - 152; }
					else { button.xPosition = this.width / 2 + 2; }
					button.yPosition = this.height / 6 + 23 * (count >> 1);
					button.parent = this;
					controlList.add(button);
					++count;
				}
				catch (Exception e) {
					return;
				}
			}
		}
	}
	
	public void initGui()
	{
		initGui(0);
	}
	
	protected void actionPerformed(GuiButton button)
    {
        if (button.enabled)
        {
            if (button == doneButton) {
            	mc.displayGuiScreen(parent);
            }
            else if (button == prevButton) {
            	initGui(currentPage - 1);
            }
            else if (button == nextButton) {
            	initGui(currentPage + 1);
            }
            else if (button instanceof OptionButton) {
            	OptionButton optionButton = (OptionButton)button;
            	IOptionHandler handler = options.buttonToHandlerMap.get(optionButton);
            	if (handler != null) {
            		handler.buttonClicked(optionButton, minecraft);
            	}
            }
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
	
    public void drawScreen(int x, int y, float par3)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, "More Options", this.width / 2, 10, 16777215);
        this.drawCenteredString(this.fontRenderer, "Page " + (currentPage + 1) + "/" + pageCount, this.width / 2, 25, -6250336);
        super.drawScreen(x, y, par3);
        int offset = 0;
        if (y + 20 > this.height / 2) { offset = -100; }
        if (currentDesc != null) {
        	drawDescription(currentDesc, x, y, offset);
        	currentDesc = null;
        }
    }
    
    public static FontRenderer getFontRenderer()
    {
    	return minecraft.fontRenderer;
    }
    
    public static void setCurrentDescription(OptionDescription desc)
    {
    	if (minecraft.currentScreen != null && minecraft.currentScreen instanceof GuiMoreOptions) {
    		((GuiMoreOptions)minecraft.currentScreen).currentDesc = desc;
    	}
    }
    
    public void toggleButtons(int page)
    {
		if (page - 1 < 0) { prevButton.enabled = false; }
		else { prevButton.enabled = true; }
		
		if (page + 1 > pageCount - 1) { nextButton.enabled = false; }
		else { nextButton.enabled = true; }
    }
}