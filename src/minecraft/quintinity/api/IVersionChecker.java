package quintinity.api;

public interface IVersionChecker 
{
	public void versionChecked(Version.VersionCheckResult result, Version latestVersion);
}