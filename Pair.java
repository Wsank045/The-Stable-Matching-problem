/**
 * Cette classe contient des couples de valeur (x,y)
 * Elle est utilisée comme dictionnaire avec x=key, y=value
 * Elle est utilisée aussi comme couple pour x=employeur, y=étudiant
 * 
 * @author Wilfried SANKARA
 *
 */


public class Pair {
	int x;//Rang de l'employeur
	int y;//Rang de l'étudiant
	
	public Pair(int a, int b) {
		x=a;
		y=b;
	}
	
	public String toString() {
		return ("("+x+","+y+")");
	}
	
}
