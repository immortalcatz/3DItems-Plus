package quintinity.api.config;
import java.io.*;
import java.util.*;

public class ConfigFile 
{
	public File file;
	private HashMap<String, String> properties = new HashMap<String, String>();
	private HashMap<String, String> descriptions = new HashMap<String, String>();
	
	public ConfigFile(File file)
	{
		this.file = file;
	}
	
	public final void load()
	{
		try {
			if (file.getParentFile() != null) {
				file.getParentFile().mkdirs();
			}
			if (!file.exists()) {
				if (!file.createNewFile()) {
					System.out.println("Unable to create config file!");
					return;
				}
			}
			properties.clear();
			FileInputStream in = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				if (line.length() > 0 && !(line.charAt(0) == '#')) {
					String[] bits = line.split("=");
					if (!(bits.length < 2)) {
						String name = bits[0];
						String value = bits[1];
						properties.put(name, value);
					}
				}
			}
			reader.close();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public final void save()
	{
		try {
			if (file.getParentFile() != null) {
				file.getParentFile().mkdirs();
			}
			if (!file.exists()) {
				if (!file.createNewFile()) {
					System.out.println("Unable to create config file!");
					return;
				}
			}
			FileWriter fw = new FileWriter(file, false);
			PrintWriter writer = new PrintWriter(fw);
			writeHeader(writer);
			for (Map.Entry<String, String> entry : properties.entrySet()) {
				if (descriptions.get(entry.getKey()) != null) {
					writer.println("");
					writer.println(descriptions.get(entry.getKey()));
				}
			    writer.println(entry.getKey() + "=" + entry.getValue());
			}
			writeFooter(writer);
			writer.close();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void writeHeader(PrintWriter writer)
	{
	}
	
	public void writeFooter(PrintWriter writer)
	{	
	}
	
	public String getProperty(String name, String defaultValue)
	{
		String value = properties.get(name);
		if (value == null) {
			properties.put(name, defaultValue);
			return defaultValue;
		}
		else {
			return value;
		}
	}
	
	public int getProperty(String name, int defaultValue)
	{
		String value = properties.get(name);
		if (value == null) {
			properties.put(name, Integer.toString(defaultValue));
			return defaultValue;
		}
		else {
			int v = defaultValue;
			try {
				v = Integer.parseInt(value);
			}
			catch (NumberFormatException e) {
				v = defaultValue;
			}
			return v;
		}
	}
	
	public float getProperty(String name, float defaultValue)
	{
		String value = properties.get(name);
		if (value == null) {
			properties.put(name, Float.toString(defaultValue));
			return defaultValue;
		}
		else {
			float v = defaultValue;
			try {
				v = Float.parseFloat(value);
			}
			catch (NumberFormatException e) {
				v = defaultValue;
			}
			return v;
		}
	}
	
	public boolean getProperty(String name, boolean defaultValue)
	{
		String value = properties.get(name);
		if (value == null) {
			properties.put(name, Boolean.toString(defaultValue));
			return defaultValue;
		}
		else {
			if (isBoolean(value)) {
				return Boolean.parseBoolean(value);
			}
			else {
				properties.put(name, Boolean.toString(defaultValue));
				return defaultValue;
			}
		}
	}
	
	public void setProperty(String name, String value)
	{
		properties.put(name, value);
	}
	
	public void setProperty(String name, int value)
	{
		setProperty(name, Integer.toString(value));
	}
	
	public void setProperty(String name, float value)
	{
		setProperty(name, Float.toString(value));
	}
	
	public void setProperty(String name, boolean value)
	{
		setProperty(name, Boolean.toString(value));
	}
	
	public void setDescription(String name, String desc)
	{
		desc = desc.trim();
		if (desc.substring(0, 1) != "#") {
			desc = "#" + desc;
		}
		descriptions.put(name, desc);
	}
	
	private boolean isBoolean(String string)
	{
		if (string.equalsIgnoreCase("true") || string.equalsIgnoreCase("false")) {
			return true;
		}
		return false;
	}
}