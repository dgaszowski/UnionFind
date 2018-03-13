

public class QuickFindUF implements IUF {
	 
	private int[] id;
	
	QuickFindUF (int N) {
		id = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}
	
	public void unite(int p, int q) {
		int pid = id[p];
		int qid = id[q];
		
		for (int i = 0; i < id.length; i++) {
			if (id[i] == pid) {
				id[i] = qid;
			}
		}
	}
	
	public boolean areConnected(int p, int q) {
		return (id[p] == id[q]);
	}

}
