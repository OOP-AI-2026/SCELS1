package util;

public class Check {

	public static void expect(String label, boolean condition) {
		if (condition) {
			System.out.println("[PASS] " + label);
		} else {
			System.out.println("[FAIL] " + label);
		}
	}
}
