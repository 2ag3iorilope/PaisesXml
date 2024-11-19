package PaisesXML;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The Class Main.
 */
public class Main {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.printf("Sartu lan egingo duzun fitxategiaren izena:");
		String fitxategiIzenaString = sc.nextLine() + ".xml";

		String helbideOsoaString = DirektorioaUtils.EskatuDirektorioa(sc);

		File fitxategia = DirektorioaUtils.KonprobatuEdoSortuFitxategia(helbideOsoaString, fitxategiIzenaString);

		int option = 0;

		do {
			System.out.println("\n=============================");
			System.out.println("           MENUA            ");
			System.out.println("=============================");
			System.out.println("1. 🗂️ Fitxategia bete datuekin");
			System.out.println("2. 🗑️ Fitxategia erakutsi");
			System.out.println("3. 🔍 Bilatu eta erakutsi erregistroa Kode Bidez");
			System.out.println("4. 📊 Bilatu eta erakutsi erregistroa hitz baten bidez");
			System.out.println("5. ➕ Erregistro bat ezabatu");
			System.out.println("6. ❌ Irten");
			System.out.println("=============================");
			System.out.print("Aukeratu aukera (1-6): ");

			try {
				option = sc.nextInt();
				sc.nextLine();
				if (option < 1 || option > 6) {
					System.out.println("Aukera ezegokia. Saiatu berriro.");
				} else {
					switch (option) {
					case 1:
						FitxategiOperazioak.fitxategiaBete(fitxategia);
						break;
					case 2:
						FitxategiOperazioak.irakurriEtaErakutsiXML(fitxategia);
						break;
					case 3:
						System.out.print("Sartu bilatu nahi duzun herrialdearen kodea: ");
						String kodeaBilatu = sc.nextLine();
						FitxategiOperazioak.bilatuKodearenArabera(fitxategia, kodeaBilatu);
						break;
					case 4:
						System.out.print("Sartu bilatu nahi duzun hitza: ");
						String hitzaBilatu = sc.nextLine();
						FitxategiOperazioak.bilatuHitzarekin(fitxategia, hitzaBilatu);
						break;
					case 5:
						System.out.print("Sartu ezabatu nahi duzun kodea: ");
						String kodeaEzabatu = sc.nextLine();
						FitxategiOperazioak.ezabatuKodearekin(fitxategia, kodeaEzabatu);
						break;
					case 6:
						System.out.println("Irteten...");
						break;
					}
				}
			} catch (InputMismatchException e) {

				System.out.println("Aukera ezegokia. Saiatu berriro.");
				sc.nextLine();
			}

		} while (option != 6);

		sc.close();
	}
}
