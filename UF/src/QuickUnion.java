
import java.util.Arrays;

public class QuickUnion implements IUF {	
	
	private int[] id;
	
	QuickUnion(int N) {
		id = new int[N];
		
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}
	
	private int getRoot(int n) {
		int root = id[n];
		while (id[root] != root) {
			root = id[root];
		}
		
		return root;
	}
	
	@Override
	public void unite(int p, int q) {
		// Just changing one value in the array!
		id[getRoot(p)] = getRoot(q);
	}

	@Override
	public boolean areConnected(int p, int q) {
		return getRoot(p) == getRoot(q);
	}
}
