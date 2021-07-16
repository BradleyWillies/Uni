package wmr;

import java.util.HashSet;

public class Line {

	private String name;
	private HashSet<SubLine> subLines;
	
	public Line(String name) {
		this.name = name;
		subLines = new HashSet<SubLine>();
	}
	
	public String getName() {
		return name;
	}
	
	public HashSet<SubLine> getSublines() {
		return subLines;
	}
	
	public void addSubline(SubLine subLine) {
		subLines.add(subLine);
	}
	
	// this method is not required I don't think #################### 
//	public HashSet<String> getTermini() {
//		HashSet<String> termini = new HashSet<String>();
//		for (SubLine subLine : subLines) {
//			for (Station terminus : subLine.getTermini()) {
//				termini.add(terminus.getName());
//			}
//		}
//		return termini;
//	}
	
}
