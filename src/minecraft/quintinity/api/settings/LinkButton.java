package quintinity.api.settings;

public class LinkButton extends OptionButton 
{
	public OptionPage page;
	
	public LinkButton(String text, OptionPage page) 
	{
		super(0, text);
		this.page = page;
	}
}