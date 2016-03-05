package datetime;

import java.time.Instant;

public class InstantExamples {

	public static void main(String[] args) {
		//generate 3 seconds
		Instant.ofEpochSecond(3);
		Instant.ofEpochSecond(3, 0);
		Instant.ofEpochSecond(2, 1_000_000_000);
		Instant.ofEpochSecond(4, -1_000_000_000);
	}
}
