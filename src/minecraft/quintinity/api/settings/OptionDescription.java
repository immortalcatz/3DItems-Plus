package quintinity.api.settings;
import java.util.ArrayList;

public class OptionDescription 
{
	private final int maxLines = 8;
	private String[] lines = new String[maxLines];
	
	public OptionDescription()
	{
		for (int i = 0; i < maxLines; i ++) {
			lines[i] =  "";
		}
	}
	
	public void setLine(String text, int line) 
	{
		if (!(line > maxLines - 1)) {
			lines[line] = text;
		}
	}
	
	public String[] getLines()
	{
		return lines;
	}
}