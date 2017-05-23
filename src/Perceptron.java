import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Perceptron {

	/**
	 * Função de ativação.
	 * 
	 * @param value
	 * @return
	 */
	public static boolean activationFunction(double value) {
		if (value > 0)
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		final List<Wine> wines = readFile();
		List<Wine> wines1 = searchMaker(wines, 1);
		List<Wine> wines2 = searchMaker(wines, 2);
		List<Wine> wines3 = searchMaker(wines, 3);

		// -------------------------------------------------------
		List<Wine> evaluationWines1 = wines1.subList(0, wines1.size() / 2);
		List<Wine> trainningWines1 = wines1.subList(wines1.size() / 2, wines1.size());

		List<Wine> evaluationWines2 = wines2.subList(0, wines2.size() / 2);
		List<Wine> trainningWines2 = wines2.subList(wines2.size() / 2, wines2.size());

		List<Wine> evaluationWines3 = wines3.subList(0, wines3.size() / 2);
		List<Wine> trainningWines3 = wines3.subList(wines3.size() / 2, wines3.size());
		// -------------------------------------------------------

		List<Wine> evaluationWines = new ArrayList<Wine>();
		evaluationWines.addAll(evaluationWines1);
		evaluationWines.addAll(evaluationWines2);
		evaluationWines.addAll(evaluationWines3);

		List<Wine> trainningWines = new ArrayList<Wine>();
		trainningWines.addAll(trainningWines1);
		trainningWines.addAll(trainningWines2);
		trainningWines.addAll(trainningWines3);

	}

	/** Ler o arquivo .csv e passa para o formato de classe. */
	public static List<Wine> readFile() {
		final List<Wine> wines = new ArrayList<Wine>();
		try {
			String line;
			BufferedReader br = new BufferedReader(new FileReader("data.csv"));
			while ((line = br.readLine()) != null) {
				String wines_string[] = line.split(",");
				List<Double> winesDouble = new ArrayList<Double>();
				for (String w : wines_string) {
					winesDouble.add(Double.parseDouble(w));
				}
				Wine w = new Wine(winesDouble);
				wines.add(w);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wines;
	}

	private static List<Wine> searchMaker(List<Wine> wines, int makerNumber) {
		final List<Wine> wine = new ArrayList<Wine>();
		for (Wine w : wines)
			if (w.getValues()[0] == makerNumber)
				wine.add(w);
		return wine;
	}

}
