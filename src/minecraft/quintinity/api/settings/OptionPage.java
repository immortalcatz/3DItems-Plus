package quintinity.api.settings;
import java.util.ArrayList;

public class OptionPage 
{
	public String title;
	public String id;
	public String buttonName;
	public IOptionHandler optionHandler;
	public ArrayList<OptionButton> buttons;
	
	public OptionPage(String title, String id, String buttonName)
	{
		this.title = title;
		this.id = id;
		this.buttonName = buttonName;
		buttons = new ArrayList<OptionButton>();
	}
	
	public OptionPage(String title, String id, String buttonName, IOptionHandler handler)
	{
		this(title, id, buttonName);
		this.optionHandler = handler; 
	}
	
	public void setOptionHandler(IOptionHandler handler)
	{
		this.optionHandler = handler;
	}
	
	public void addButton(OptionButton button)
	{
		buttons.add(button);
	}
	
	public void addButtons(ArrayList<OptionButton> buttons)
	{
		buttons.addAll(buttons);
	}
}