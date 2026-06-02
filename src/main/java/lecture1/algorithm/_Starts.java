package lecture1.algorithm;

import java.util.Scanner;

public class _Starts {
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			String stars = "";
			while (true) {
				System.out.print("1から５までの数字を入力してください：");
				int count = scanner.nextInt();
				if (0 < count && count < 6) {
					stars = createStars(count);
					break;
				}
			}
			System.out.println(stars);
		}
		
	}
	
	public static String createStars(int count) {
		String stars = "";
		for (int i = 0; i < 5; i++) {
			if (i < count) {
				stars += "★";
			} else {
				stars += "☆";
			}
		}
		return stars;
	}
}
