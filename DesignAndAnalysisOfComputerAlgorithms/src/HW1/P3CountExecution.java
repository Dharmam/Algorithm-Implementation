package HW1;


public class P3CountExecution {

	public static void main(String[] args) {

		for (int i = 0; i < 10; i++) {
			System.out.println("For n = " + i + " value = " + ALG1(i));
		}
	}

	private static int ALG1(int n) {
		int count = 0;
		// 1
		int s = 0;
		// 2
		for (int i = 1; i <= n; i++) {
			// 3
			for (int j = 1	; j <= i; j++) {
				// 4
				s += (j * j);
				count++;
			}
		}
		return count;

	}
}
