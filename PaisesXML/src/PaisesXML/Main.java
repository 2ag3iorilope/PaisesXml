package PaisesXML;

import java.io.File;
import java.util.Scanner;







public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.printf("Sartu lan egingo duzun fitxategiaren izena:");
		String fitxategiIzenaString = sc.nextLine() + ".xml";
		
		String helbideOsoaString = DirektorioaUtils.EskatuDirektorioa(sc);
		
		File fitxategia = DirektorioaUtils.KonprobatuEdoSortuFitxategia(helbideOsoaString, fitxategiIzenaString);
		
		
		int option;
		
		do {
			System.out.println("\n=============================");
			System.out.println("           MENUA            ");
			System.out.println("=============================");
			System.out.println("1. 🗂️ Fitxategia bete datuekin");
			System.out.println("2. 🗑️ Fitxategia erakutsi");
			System.out.println("3. 🔍 Bilatu eta erakutsi erregistroa");
			System.out.println("4. 📊 Kapitala Bilatu");
			System.out.println("5. ➕ Erregistro bat ezabatu");
			System.out.println("6. ❌ Ikusi Ezabatutako Erregistroak");
			System.out.println("7. 📁 Gehitu Erregistro bat");
			System.out.println("8. 📜 Aldatu erregistro bat");
			System.out.println("9.  Irten");
			System.out.println("=============================");
			System.out.print("Aukeratu aukera (1-9): ");

			option = sc.nextInt();
			sc.nextLine();

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
				
				break;
			case 5:
				
				break;
			case 6:
				
				break;
			case 7:
				
				break;
			case 8:
		
				break;
			case 9:
				System.out.println("Irteten...");
				break;

			default:
				System.out.println("Aukera ezegokia. Saiatu berriro.");
			}
		} while (option != 9);
		sc.close();
	}
		
	}
	


