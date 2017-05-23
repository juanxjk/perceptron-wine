import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
		final List<Wine> wines = Files.readFile();
		List<Wine> wines1 = searchMaker(wines, 1);
		List<Wine> wines2 = searchMaker(wines, 2);
		List<Wine> wines3 = searchMaker(wines, 3);

		/*- - - Cria um coleção de Vinhos para serem treinados - - - - - - - - - - - - - - - - - - - - - - - - - - - */
		List<Wine> trainningWines = new ArrayList<>();
		trainningWines.addAll(wines);
		int n = trainningWines.size();
		Random rand = new Random();
		List<Wine> notTrainingWines = new ArrayList<Wine>();
		do {
			Wine w = trainningWines.remove(rand.nextInt(trainningWines.size()));
			notTrainingWines.add(w);
		} while (trainningWines.size() != n / 2);
		Collections.shuffle(trainningWines);
		/*- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
		
		
	}

	private static List<Wine> searchMaker(List<Wine> wines, int makerNumber) {
		final List<Wine> wine = new ArrayList<Wine>();
		for (Wine w : wines)
			if (w.getValues()[0] == makerNumber)
				wine.add(w);
		return wine;
	}

}
