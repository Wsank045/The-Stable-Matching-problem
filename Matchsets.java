
public class Matchsets {
	int employer;
	int student;
	
	public Matchsets(int aEmployer, int aStudent) {
		this.employer=aEmployer;
		this.student=aStudent;
	}
	
	public String toString() {
		return "("+employer+","+student+")";
	}
}
