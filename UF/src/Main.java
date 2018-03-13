

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Input data:");
		int N = sc.nextInt();				
		IUF uf = new QuickUnion(N);
		while (sc.hasNextLine()) {
			int p = sc.nextInt();
			
			if (p == -1) {
				break;
			}
			
			int q = sc.nextInt();
			
			System.out.println("(" + p + "," + q + ")");
			
			if (!uf.areConnected(p, q)) {
				uf.unite(p, q);
			}
		}
		
		System.out.println("Testing:");
		System.out.println("4 i 7: " + uf.areConnected(4, 7));
		System.out.println("9 i 7: " + uf.areConnected(9, 7));
				
		sc.close();
	}
}
