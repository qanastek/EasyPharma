package application.models.Utils;

import java.util.Random;

public final class Toolbox {
	
	public static Random rand = new Random();

	public static int generateInt() {
		return generateNbr(Integer.MAX_VALUE);
	}

	public static int generateNbr(int max) {
		return rand.nextInt(max);
	}
	
}
