import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.omg.CORBA.DoubleSeqHelper;

public class Wine {
	/**
	 * 1 producer; 2 alcohol; 3 malicAcid; 4 ash; 5 alcalinityOfAsh; 6
	 * magnesium; 7 totalPhenols; 8 flavanoids; 9 nonflavanoidPhenols; 10
	 * proanthocyanins; 11 colorIntensity; 12 hue; 13 oD280; 14 Proline;
	 */
	/**
	 * Lista com todos os valores obtidos no arquivo .csv
	 */
	private double[] values = new double[14];

	/**
	 * @param listValues
	 */
	public Wine(List<Double> listValues) {
		this.setValues(listValues);
	}

	/**
	 * @return
	 */
	public double[] getValues() {
		return values;
	}

	/**
	 * @param values
	 * @throws IllegalArgumentException
	 */
	public void setValues(double[] values) throws IllegalArgumentException {
		if (values.length != this.values.length)
			throw new IllegalArgumentException(
					"Tamanho inválido."); /*- Lança uma excessão para quem estará chamando. */
		this.values = values;
	}

	/**
	 * @param listValues
	 */
	public void setValues(List<Double> listValues) {
		if (listValues.size() != this.values.length)
			throw new IllegalArgumentException("Tamanho inválido. Size: " + listValues.size());
		for (int i = 0; i < listValues.size(); i++) {
			this.values[i] = listValues.get(i);
		}

	}

	@Override
	public String toString() {
		String s = Arrays.toString(this.values) + "\n";
		return s;
	}
}
