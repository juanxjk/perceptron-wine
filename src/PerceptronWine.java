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
	private static final boolean DEBUG = false;

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
		if (DEBUG)
			System.out.println("= = = PESOS = = =");
		if (DEBUG)
			System.out.println("Bias: " + Arrays.toString(biasWeight));
		if (DEBUG)
			for (double[] d : weights) {
				System.out.println(Arrays.toString(d));
			}

		double alpha = 0.1;
		// Arrays.fill(f, 0);
		int[] g = new int[NUM_PERCEPTRONS];

		// Arrays.fill(g, 0);

		for (int epoch = 0; epoch < 100000; epoch++) {
			for (Wine w : trainningWines) {
				if (DEBUG)
					System.out
							.println("- - - Amostra - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
				if (DEBUG)
					System.out.println("Vinho: " + w);
				double[] sum = new double[NUM_PERCEPTRONS];
				for (int p = 0; p < NUM_PERCEPTRONS; p++) { /*- Iteração por Perceptron.*/
					if (DEBUG)
						System.out.println("Perceptron " + (p + 1));
					if (DEBUG)
						System.out.println("Bias atual: " + biasWeight[p]);
					if (DEBUG)
						System.out.println(Arrays.toString(weights[p]));
					sum[p] = biasWeight[p];
					for (int i = 0; i < 13; i++) {
						sum[p] += weights[p][i] * w.getValues()[i];
					}
					if (DEBUG)
						System.out.println("sum: " + sum[p]);
					g[p] = activationFunction(sum[p]);

					/*- Atualização de pesos*/
					if (DEBUG)
						System.out.println("y^ = " + y_(w.getWineClass())[p]);
					if (DEBUG)
						System.out.println("y = " + g[p]);
					biasWeight[p] = biasWeight[p] + alpha * (y_(w.getWineClass())[p] - g[p]);
					for (int i = 0; i < 13; i++) {
						weights[p][i] = weights[p][i] + alpha * (y_(w.getWineClass())[p] - g[p]) * w.getValues()[i];
					}
					if (DEBUG)
						System.out.println("Bias novo: " + biasWeight[p]);
					if (DEBUG)
						System.out.println(Arrays.toString(weights[p]));
				}
				if (DEBUG)
					System.out.println("y de todos perceptrons: " + Arrays.toString(g));
			}
			Collections.shuffle(trainningWines);
		}
		if (DEBUG)
			for (double[] d : weights) {
				System.out.println(Arrays.toString(d));
			}
		/*- - - FASE DE TESTES = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =*/
		wines1 = searchMaker(testWines, 1);
		wines2 = searchMaker(testWines, 2);
		wines3 = searchMaker(testWines, 3);
		System.out.println("= = = FASE DE TESTES = = =");
		// TODOS
		testWines(testWines, biasWeight, weights);

	}

	public static void testWines(List<Wine> testWines, double[] biasWeight, double[][] weights) {
		int tp = 0; /*- True positive*/
		int fp = 0; /*- False positive*/
		int tn = 0; /*- True negative*/
		int fn = 0; /*- False negative*/
		int nt = 0;

		int[] _tp = new int[3]; /*- True positive*/
		int[] _fp = new int[3]; /*- False positive*/
		int[] _tn = new int[3]; /*- True negative*/
		int[] _fn = new int[3]; /*- False negative*/
		int[] n = new int[3];

		int[] g = new int[NUM_PERCEPTRONS];
		Collections.shuffle(testWines);
		int acerto = 0;
		int g_ = 0;

		for (Wine w : testWines) {
			double[] sum = new double[NUM_PERCEPTRONS];
			for (int p = 0; p < NUM_PERCEPTRONS; p++) { /*- Iteração por Perceptron.*/
				sum[p] = biasWeight[p];
				for (int i = 0; i < 13; i++) {
					sum[p] += weights[p][i] * w.getValues()[i];
				}
				g[p] = activationFunction(sum[p]);
			}

			// for (int i = 0; i < 3; i++) {
			// if (w.getWineClass() == (i + 1)) {
			// if (isEquals(y_(w.getWineClass()), g)) {
			// tp++;
			// acerto++;
			// } else {
			// fn++;
			// }
			// } else {
			// if (isEquals(y_(w.getWineClass()), g)) {
			// fp++;
			// } else {
			// tn++;
			// }
			// }
			// }
			// for (int i = 0; i < 3; i++) {
			// System.out.println("Classe " + (i + 1));
			//
			// double rightAnswerRate = (tp);
			// double sens = (((double) tp / (tp + fn)) * 100);
			// double esp = (((double) tn / (tn + fp)) * 100);
			// System.out.println("Taxa de acerto: " + rightAnswerRate + " %");
			// System.out.println("Sensibilidade: " + sens + " %");
			// System.out.println("Especificidade: " + esp + " %");
			// }

			for (int i = 0; i < 3; i++) {
				if (w.getWineClass() == (i + 1)) {
					if (isEquals(y_(w.getWineClass()), g)) {
						_tp[i]++;
						acerto++;
					} else {
						_fn[i]++;
					}
					n[i]++;
				} else {
					if (isEquals(y_(w.getWineClass()), g)) {
						_fp[i]++;
					} else {
						_tn[i]++;
					}
				}
				nt++;
			}

		}

		for (int i = 0; i < 3; i++) {
			System.out.println("Classe " + (i + 1));
			double rightAnswerRate = ((double)(_tp[i]+_tn[i])/(testWines.size()))*100;
			double sens = (((double) _tp[i] / (_tp[i] + _fn[i])) * 100);
			double esp = (((double) _tn[i] / (_tn[i] + _fp[i])) * 100);
			System.out.println("Verdadeiro Positivo: " + _tp[i]);
			System.out.println("Verdadeiro Negativo: " + _tn[i]);
			System.out.println("Falso Positivo: " + _fp[i]);
			System.out.println("Falso Negativo: " + _fn[i]);
			System.out.println("Taxa de acerto: " + rightAnswerRate);
			System.out.println("Sensibilidade: " + sens + " %");
			System.out.println("Especificidade: " + esp + " %");
			System.out.println(n[i]);
		}

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
			if (w.getWineClass() == makerNumber)
				wine.add(w);
		return wine;
	}

	public static boolean isEquals(int[] values, int[] values2) {
		if (values.length != values2.length)
			throw new IllegalArgumentException();
		for (int i = 0; i < values.length; i++) {
			if (values[i] != values2[i])
				return false;
		}
		return true;
	}
}
