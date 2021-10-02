package wmr;

/**
 * A WMRlinesFileLine represents a single line within the WMRlines.csv data file
 * 
 * @author Bradley Willies
 * @version 27/06/2021
 */
public class WMRlinesFileLine {

	// a WMRlinesFileLine has the following fields:
	public String trainLineName;
	public String fromStation;
	public String toStation;
	public int travelTime;
	
	/**
	 * constructor to create a WMRlinesFileLine with the provided line attributes
	 * 
	 * @param trainLineName
	 * @param fromStation
	 * @param toStation
	 * @param travelTime
	 */
	public WMRlinesFileLine(String trainLineName, String fromStation, String toStation, int travelTime) {
		this.trainLineName = trainLineName;
		this.fromStation = fromStation;
		this.toStation = toStation;
		this.travelTime = travelTime;
	}
	
}
