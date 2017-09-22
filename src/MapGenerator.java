import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

public class MapGenerator implements TextGenerator {
	// mgt stands for MapGeneratorText
	private TrainingText mgt;
	private Random RandNum;
	private TreeMap<NGram, ArrayList<NGram>> NGramMap;

	MapGenerator(int randseed) {
		RandNum = new Random(randseed);
	}

	MapGenerator() {
		RandNum = new Random();
	}

	public int train(Scanner source, String delimiter, int k) {
		NGramMap = new TreeMap<NGram, ArrayList<NGram>>();
		mgt = new TrainingText(source, delimiter, k);
		// Only go to one less than the mgt size to ensure you don't
		// go out of bounds.
		for (int i = 0; i < mgt.size() - 1; i++) {
			NGram CurGram = mgt.get(i);
			if (!NGramMap.containsKey(CurGram)) {
				NGramMap.put(CurGram, new ArrayList<NGram>());
			}
			NGramMap.get(CurGram).add(mgt.get(i + 1));
		}
		return NGramMap.keySet().size();
	}

	public String generateText(int length) {
		StringBuilder NewText = new StringBuilder();
		NGram CurGram = mgt.get(RandNum.nextInt(mgt.size()));
		for (int i = 0; i < length; i++) {
			// Change CurGram before adding to text
			// because we don't want first NGram.
			CurGram = NGramMap.get(CurGram).get(RandNum.nextInt(NGramMap.get(CurGram).size()));
			NewText.append(CurGram.toString());
		}
		String text=""+NewText;
		return text;
	}
}