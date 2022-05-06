/**
 * Cette classe implémente une file à priorité 
 * En utilisant le modèle du monceau min
 *
 */
public class HeapPriorityQueue implements PriorityQueue {
	
	private Pair[] storage;
	private int tail;
	
	 public HeapPriorityQueue() {
		this(100);
	}
	 
	 public HeapPriorityQueue(int size) {
		 storage= new Pair[size];
		 tail=-1;
	 }
	
	@Override
	public int size() {
		return tail+1;
	}

	@Override
	public boolean isEmpty() {
		return tail<0;
		
	}

	@Override
	public Pair insert(int key, int value) throws IllegalArgumentException {
		
		if(tail==storage.length-1)
			throw new IllegalArgumentException();
		
		Pair  elem=new Pair(key,value);
		storage[++tail]=elem;
		
		upHeap(tail);
		
		return elem;
	}


	

	@Override
	public Pair min() {
		
		if(isEmpty())
			return null;
		return storage[0];
		
	}

	@Override
	public Pair removeMin() {
		
		if( isEmpty() )
			return null;

		Pair  ret = storage [ 0 ];

		if( tail == 0 ) {
			tail          = -1;
			storage [ 0 ] = null;
			return ret;
		}

		storage [ 0 ]      = storage [ tail ];
		storage [ tail-- ] = null;

		downHeap ( 0 );

		return ret;
	}
	
	private void downHeap(int location) {
		int    left  = (location * 2) + 1;
		int    right = (location * 2) + 2;

		//Both children null or out of bound
		if( left > tail ) return;

		//left in right out;
		if( left == tail ) {
			if( (storage [ location ].x > ( storage [ left ].x )))
				swap ( location, left );
			return;
		}

		int    toSwap = (storage [ left ].x<( storage [ right ].x )) ?
		                left : right;

		if( storage [ location ].x> ( storage [ toSwap ].x )) {
			swap ( location, toSwap );
			downHeap ( toSwap );
		}
	}

	private void upHeap(int location) {
		if( location == 0 ) return;

		int    parent = parent ( location );

		if( storage [ parent ].x > ( storage [ location ].x ) ) {
			swap ( location, parent );
			upHeap ( parent );
		}
		
	}
	
	private int parent( int location ) {
		return (location - 1) / 2;
	} /* parent */
	
	private void swap( int location1, int location2 ) {
		Pair   temp = storage [ location1 ];
		storage [ location1 ] = storage [ location2 ];
		storage [ location2 ] = temp;
	}
}
