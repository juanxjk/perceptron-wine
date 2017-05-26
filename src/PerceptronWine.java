import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PerceptronWine {

	/**
	 * Função de ativação.
	 * 
	 * @param value
	 * @return
	 */
	public static double activationFunction(double value) { // FIXME
		if (value >= 2 && value < 3)
			return 2;
		if (value >= 3)
			return 3;
		return 1;
	}

	public static void main(String[] args) {
		final List<Wine> wines = Files.readFile();
		List<Wine> wines1 = searchMaker(wines, 1);
		List<Wine> wines2 = searchMaker(wines, 2);
		List<Wine> wines3 = searchMaker(wines, 3);
		Collections.shuffle(wines);

		/*- - - CRIA UM COLEÇÃO DE VINHOS PARA SEREM TREINADOS - - - - - - - - - - - - - - - - - - - - - - - - - - - */
		List<Wine> trainningWines = new ArrayList<>();
		trainningWines.addAll(wines);
		int n = trainningWines.size();
		Random rand = new Random();
		final List<Wine> testWines = new ArrayList<Wine>();

		while (trainningWines.size() != n / 2) {
			Wine w = trainningWines.remove(rand.nextInt(trainningWines.size()));
			testWines.add(w);
		}

		/*- - - DECLARAÇÃO DOS PESOS - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
		double[] weights = new double[13];
		Arrays.fill(weights, rand.nextDouble());

		/*- - - TREINAMENTO - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -*/
		double alpha = 1 / 35;
		for (int step = 0; step < 5; step++) {
			for (Wine w : trainningWines) {
				double f = 0.0;
				for (int i = 0; i < 13; i++) {
					f += weights[i] * w.getValues()[i + 1];
				}
				f = activationFunction(f); // TODO: Verificar se é válido
				for (int i = 0; i < 13; i++) {
					weights[i] = weights[i] + alpha * (w.getValues()[0] - f) * w.getValues()[i + 1];
				}
				System.out.println(f);
			}
		}
		int tp;
		// TODO: TESTS
	}

	private static List<Wine> searchMaker(List<Wine> wines, int makerNumber) {
		final List<Wine> wine = new ArrayList<Wine>();
		for (Wine w : wines)
			if (w.getValues()[0] == makerNumber)
				wine.add(w);
		return wine;
	}

}
