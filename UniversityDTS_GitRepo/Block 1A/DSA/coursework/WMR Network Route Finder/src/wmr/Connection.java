package wmr;

public class Connection {

	private Station fromToStation;
	private Station toFromStation;
	private int travelTime;
	
	public Connection(Station fromToStation, Station toFromStation, int travelTime) {
		this.fromToStation = fromToStation;
		this.toFromStation = toFromStation;
		this.travelTime = travelTime;
	}
	
	public Station getFromToStation() {
		return fromToStation;
	}
	
	public Station getToFromStation() {
		return toFromStation;
	}
	
	public int getTravelTime() {
		return travelTime;
	}
	
}
