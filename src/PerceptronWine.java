import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class PerceptronWine {

	/**
	 * Função de ativação.
	 * 
	 * @param value
	 * @return
	 */
	private static final int NUM_PERCEPTRONS = 3;

	public static int activationFunction(double value) { // FIXME
		if (value <= 0)
			return 0;
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

		/*- - - FASE DE TREINAMENTO = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =*/
		/*- - - DECLARAÇÃO DOS PESOS - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
		double[] biasWeight = new double[NUM_PERCEPTRONS];
		double[][] weights = new double[NUM_PERCEPTRONS][13];
		System.out.println("= = = PESOS = = =");
		System.out.println("Bias: " + Arrays.toString(biasWeight));
		for (double[] d : weights) {
			System.out.println(Arrays.toString(d));
		}

		double alpha = 0.1;
		// Arrays.fill(f, 0);
		int[] g = new int[NUM_PERCEPTRONS];
		// Arrays.fill(g, 0);
		for (int epoch = 0; epoch < 50; epoch++) {
			for (Wine w : trainningWines) {
				System.out.println("- - - Amostra - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
				System.out.println("Vinho: " + w);
				double[] sum = new double[NUM_PERCEPTRONS];
				for (int p = 0; p < NUM_PERCEPTRONS; p++) { /*- Iteração por Perceptron.*/
					System.out.println("Perceptron " + (p + 1));
					System.out.println("Bias atual: " + biasWeight[p]);
					System.out.println(Arrays.toString(weights[p]));
					sum[p] = biasWeight[p];
					for (int i = 0; i < 13; i++) {
						sum[p] += weights[p][i] * w.getValues()[i];
					}
					System.out.println("sum: " + sum[p]);
					g[p] = activationFunction(sum[p]);
					
					/*- Atualização de pesos*/
					System.out.println("y^ = " + y_(w.getWineClass())[p]);
					System.out.println("y = " + g[p]);
					biasWeight[p] = biasWeight[p] + alpha * (y_(w.getWineClass())[p] - g[p]);
					for (int i = 0; i < 13; i++) {
						weights[p][i] = weights[p][i] + alpha * (y_(w.getWineClass())[p] - g[p]) * w.getValues()[i];
					}
					System.out.println("Bias novo: " + biasWeight[p]);
					System.out.println(Arrays.toString(weights[p]));
				}
				System.out.println("y de todos perceptrons: " + Arrays.toString(g));
			}
			Collections.shuffle(trainningWines);
		}
		for (double[] d : weights) {
			System.out.println(Arrays.toString(d));
		}
		/*- - - FASE DE TESTES = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =*/
		System.out.println("= = = FASE DE TESTES = = =");
		int tp;
	}

	public static int[] y_(double d) {
		if (d == 1.0)
			return new int[] { 1, 0, 0 };
		if (d == 2.0)
			return new int[] { 0, 1, 0 };
		return new int[] { 0, 0, 1 };
	}

	private static List<Wine> searchMaker(List<Wine> wines, int makerNumber) {
		final List<Wine> wine = new ArrayList<Wine>();
		for (Wine w : wines)
			if (w.getValues()[0] == makerNumber)
				wine.add(w);
		return wine;
	}

}
