import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Files {

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
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wines;
	}

}
