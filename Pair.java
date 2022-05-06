/**
 * Cette classe contient des couples de valeur (x,y)
 * Elle est utilis�e comme dictionnaire avec x=key, y=value
 * Elle est utilis�e aussi comme couple pour x=employeur, y=�tudiant
 * 
 * @author Wilfried SANKARA
 *
 */


public class Pair {
	int x;//Rang de l'employeur
	int y;//Rang de l'�tudiant
	
	public Pair(int a, int b) {
		x=a;
		y=b;
	}
	
	public String toString() {
		return ("("+x+","+y+")");
	}
	
}
