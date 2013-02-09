package quintinity.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import quintinity.api.Version.VersionCheckResult;

public class ThreadVersion implements Runnable 
{
	
	public String address;
	public IVersionChecker checker;
	public Version currentVersion;
	
	public ThreadVersion(IVersionChecker checker, Version currentVersion, String url)
	{
		this.checker = checker;
		this.currentVersion = currentVersion;
		this.address = url;
	}
	
	public void run()
	{
		try {
	    	URL url = new URL(address);
	    	BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        String line = reader.readLine();
	        reader.close();
	        Version latestVersion = new Version(line);
	        if (latestVersion.isValid && latestVersion.isNewerThan(currentVersion)) {
	        	checker.versionChecked(VersionCheckResult.NEW_VERSION_AVAILIABLE, latestVersion);
	        }
	        else if (latestVersion.isValid && !latestVersion.isNewerThan(currentVersion)) {
	        	checker.versionChecked(VersionCheckResult.UP_TO_DATE, latestVersion);
	        }
	        else if (!latestVersion.isValid) {
	        	checker.versionChecked(VersionCheckResult.ERROR, null);
	        }
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		checker.versionChecked(VersionCheckResult.ERROR, null);
    	}
	}
}
