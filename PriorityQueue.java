
/**
 * Interface de file à priorité
 *
 */
public interface PriorityQueue {
	
	public int size();
	
	public boolean isEmpty();
	
	public Pair insert(int key,int value);
	
	public Pair min();
	
	public Pair removeMin();
	
	
}
