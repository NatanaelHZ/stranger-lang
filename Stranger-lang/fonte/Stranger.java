import java.io.File;
import java.util.Scanner;

public class Stranger {
	public static void main(String[] args) {
		try {
				File file = new File(args[0]);
				Scanner input = new Scanner(file);
				Interpretador interpretador = new Interpretador();

				while (input.hasNextLine()) {
					String line = input.nextLine();
					interpretador.setLinhas(line.toLowerCase());
					//System.out.println(line);
				}

				interpretador.analizador();

				//interpretador.mostraListaVariaveis();
				//interpretador.mostrarLinhas();

				input.close();
			} catch (Exception e) {
				System.out.println("Nao foi possivel abrir o arquivo " + args[0]);
				System.out.println("Ele existe mesmo?");
				e.printStackTrace();
		}
	}
}