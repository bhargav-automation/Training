package linkedin;

public class xpathExperssions {
	
	
	String beforeSigin = "//a[contains(.,'Sign in')]";
	String userName = "//input[contains(@id,'username')]";
	String password = "//input[contains(@id,'password')]";
	String Sigin = "//button[contains(.,'Sign in')]";
	String Search = "//input[contains(@class,'search-global')]";
	String Searchbtn ="//button[contains(@class,'search-global')]";
	String locationBtn = "//span[contains(.,'Locations')]";
	String addCountry = "//input[@placeholder='Add a country/region']";
	String ApplyBtn ="//fieldset[contains(@class,'geoRegion')]//div[2]//button[2]";
	String relativeResult = "//h3[contains(.,'Showing')]";
	String Pagination = "//ul[contains(@class,'artdeco-pagination')]/li";
	
	String ListofMembers = "//ul[contains(@class,'search-result')]/li";
	String MemberProfile = "//ul[contains(@class,'search-result')]/li[1]//div[2]/a";
	String MemberName = "//ul[contains(@class,'search-result')]/li[1]//div[2]/a/h3/span/span/span";
	
	String MemberDesignation = "//ul[contains(@class,'search-result')]/li[1]//div[2]/p[1]";
	String MemberLocation = "//ul[contains(@class,'search-result')]/li[1]//div[2]/p[2]";
	String NextBtn = "//span[contains(.,'Next')]";
	

}
