import java.io.File;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class BruteGenerator implements TextGenerator {
	// bgt stands for BruteGeneratorText
	private TrainingText bgt;
	private Random RandNum;

	BruteGenerator(int randseed) {
		RandNum = new Random(randseed);
	}

	BruteGenerator() {
		RandNum = new Random();
	}

	public int train(Scanner source, String delimiter, int k) {
		bgt = new TrainingText(source, delimiter, k);
		return bgt.size();
	}

	public String generateText(int length) {
		StringBuilder NewText = new StringBuilder();
		NGram CurGram;
		CurGram = bgt.get(RandNum.nextInt(bgt.size()));
		for (int i = 0; i < length; i++) {
			ArrayList<NGram> AfterCurGram = new ArrayList<NGram>();
			int j = 0;
			while (j < bgt.size()) {
				// index of returns the first occurrence of the NGram after
				// the given starting position j.
				j = bgt.indexOf(CurGram, j);
				// Need this if loop to ensure you don't go out of bounds.
				if (j + 1 < bgt.size()) {
					AfterCurGram.add(bgt.get(j + 1));
				}
				j++;
			}
			int RandSelect = RandNum.nextInt(AfterCurGram.size());
			CurGram = AfterCurGram.get(RandSelect);
			NewText.append(CurGram.toString());
			AfterCurGram.clear();
		}
		String text=""+NewText;
		return text;
	}
}
