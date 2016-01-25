package streams.io;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class StreamFromFileExample {
	
	private static final String FILE_NAME = "D://data.txt";

	public static void main(String[] args) {
		long uniqueWords = 0;
		try (Stream<String> lines = Files.lines(Paths.get(FILE_NAME), Charset.defaultCharset())) {

			uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
					.distinct()
					.count();

			System.out.println(uniqueWords);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
