package wmr;

public class WMRlinesFileLine {

	public String trainLineName;
	public String fromStation;
	public String toStation;
	public int travelTime;
	
	public WMRlinesFileLine(String trainLineName, String fromStation, String toStation, int travelTime) {
		this.trainLineName = trainLineName;
		this.fromStation = fromStation;
		this.toStation = toStation;
		this.travelTime = travelTime;
	}
	
}
