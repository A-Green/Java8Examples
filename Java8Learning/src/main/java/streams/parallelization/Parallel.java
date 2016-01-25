package streams.parallelization;

import java.util.stream.Stream;

public class Parallel {
	/*
	 * Example of how to set threads amount for parallel stream
	 * System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "12");
	 */

	public static void main(String[] args) {

		Long sum = Stream.iterate(1L, i -> i + 1)
			.limit(100)
			.parallel() // .sequential() for execution without parallelization
			.reduce(0L, Long::sum);
		System.out.println(sum);
	}

}
