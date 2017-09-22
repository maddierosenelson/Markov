import java.util.Arrays;
import java.util.*;
import java.util.List;

public class NGram implements Comparable<NGram> {

	private String[] contents;
	private String separator;

	public NGram(List<String> source, String sep) {
		separator = sep;
		contents = new String[source.size()];
		contents = Arrays.copyOf(source.toArray(new String[source.size()]), source.size());
	}

	public int compareTo(NGram other) {
		int ThisLength = contents.length;
		int OtherLength = other.contents.length;
		// Check lengths to avoid going out of bounds.
		if (ThisLength < OtherLength || ThisLength == OtherLength) {
			for (int i = 0; i < ThisLength; i++) {
				if (!contents[i].equals(other.contents[i])) {
					return contents[i].compareTo(other.contents[i]);
				}
				return ThisLength - OtherLength;
			}
		}
		if (ThisLength > OtherLength) {
			for (int i = 0; i < ThisLength; i++) {
				return contents[i].compareTo(other.contents[i]);
			}
			return OtherLength - ThisLength;
		}
		return 0;
	}

	public boolean equals(Object o) {
		int count = 0;
		if (o instanceof NGram) {
			NGram NewGram = (NGram) o;
			if (NewGram.contents.length == contents.length) {
				for (int i = 0; i < contents.length; i++) {
					if (NewGram.contents[i].equals(contents[i])) {
						count++;
					}
				}
				if (count == contents.length) {
					return true;
				}
			}
		}
		return false;

	}

	public int hashCode() {
		int answer = 0;
		for (int i = 0; i < contents.length; i++) {
			// Multiply by a prime number to avoid collisions.
			answer = answer * 37 + contents[i].hashCode();
		}
		return answer;
	}

	public String toString() {
		int StringLength = contents.length;
		// Include separator to ensure spaces if using words.
		return "" + contents[StringLength - 1] + separator;
	}
}