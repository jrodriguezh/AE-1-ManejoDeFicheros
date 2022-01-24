import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {

		List<Coche> listaCoche = new ArrayList<Coche>();

		Scanner sc = new Scanner(System.in);

		File fn = new File("coches.dat");// Apuntamos al fichero definido de manera relativa
		if (fn.exists()) {// Averiguamos si existe

			try (FileInputStream fis = new FileInputStream(fn); ObjectInputStream ois = new ObjectInputStream(fis);) {

				List<Coche> listaAux = new ArrayList<Coche>();
				listaCoche = (List<Coche>) ois.readObject();
				Coche coche = new Coche();

				System.out.println("Buscando coches en el almac�n...");
				System.out.println("Guardando coches en el listado");

				for (Coche c : listaAux) {
					listaCoche.add(coche);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		boolean continuar = true;

		do {

			escribirMenu(); // Invocamos al metodo que escribir� las opciones del menu

			int opcion = sc.nextInt();

			switch (opcion) { // Creamos un switch para cada una de las opciones del cliente.

			case 1:
				Coche nuevo = new Coche();
				System.out.println("Introduce los datos del coche:");
				sc.nextLine();
				System.out.println("ID:");
				boolean validarId = false;
				int id3 = 0;
				if (listaCoche.size() != 0) {

					while (validarId == false) {
						id3 = sc.nextInt();
						for (int i = 0; i < listaCoche.size(); i++) {
							Coche c = listaCoche.get(i);
							if (c.getId() == id3) {
								System.out.println("Este ID ya existe, introduzca otro");
								validarId = false;
							} else {
								validarId = true;

							}

						}
					}
				} else {
					id3 = sc.nextInt();
				}

				nuevo.setId(id3);

				sc.nextLine();
				System.out.println("Matricula:");
				boolean validarMatricula = false;
				String matricula = "";
				if (listaCoche.size() != 0) {

					while (validarMatricula == false) {
						matricula = sc.nextLine();
						for (int i = 0; i < listaCoche.size(); i++) {
							Coche c = listaCoche.get(i);
							if (c.getMatricula().equalsIgnoreCase(matricula)) {
								System.out.println("Esta matricula ya existe, introduzca otra");
								validarMatricula = false;
							} else {
								validarMatricula = true;

							}

						}
					}
				} else {
					matricula = sc.nextLine();

				}
				nuevo.setMatricula(matricula);
				System.out.println("Marca:");
				nuevo.setMarca(sc.nextLine());

				System.out.println("Modelo:");
				nuevo.setModelo(sc.nextLine());

				System.out.println("Color:");
				nuevo.setColor(sc.nextLine());

				listaCoche.add(nuevo);

				break;

			case 2:
				System.out.println("Introduce el ID del coche a borrar:");
				int id = sc.nextInt();
				for (int j = 0; j < listaCoche.size(); j++) {
					Coche c = listaCoche.get(j);

					if (c.getId() == id) {

						listaCoche.remove(j);
						System.out.println("Coche borrado");
					}else { System.out.println("No hay ning�n coche con ese ID");
						
					}
				}
				break;
			case 3:
				System.out.println("Introduce el ID del coche a buscar:");
				int id2 = sc.nextInt();
				String resultado = "";
				for (Coche c : listaCoche) {
					if (c.getId() == id2) {

						resultado = resultado + c; // No es necesario realizar un acumulador ya que no se va a duplicar el ID.
					}
				}

				if ("".equalsIgnoreCase(resultado)) {
					resultado = "No existe ning�n coche con ese ID";
				} 
					
					System.out.println(resultado);
			

				break;
			case 4:
				for (Coche c : listaCoche) {
					System.out.println(c);
				}
				break;
			case 5:
				System.out.println("Guardando el archivo de texto...");
				try (FileWriter fw = new FileWriter("coches.txt"); BufferedWriter pw = new BufferedWriter(fw);) { // Hemos decidido que se sobreescriba siempre el archivo, en lugar de ir a�adiendo m�s coches al mismo.

					for (Coche c : listaCoche) {
						pw.write(c.exportar());
						pw.newLine();
						pw.flush();
					}
					System.out.println("El fichero se ha exportado correctamente"); 

				} catch (IOException e) {
					
					e.printStackTrace();
				}
				break;
			case 6:
				System.out.println("Guardando el archivo...");
				try (FileOutputStream fos = new FileOutputStream(fn);
						ObjectOutputStream oos = new ObjectOutputStream(fos)) {

					oos.writeObject(listaCoche);
					System.out.println("Archivo guardado");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				continuar = false;
				System.out.println("Saliendo del programa...");
				break;

			default:
				System.out.println("Opci�n no valida");

			}

		} while (continuar);
	}

	public static void escribirMenu() {
		System.out.println("Elija una de las siguientes opciones:");
		System.out.println("1. A�adir nuevo coche");
		System.out.println("2. Borrar coche por id");
		System.out.println("3. Consulta coche por id");
		System.out.println("4. Listado de coches");
		System.out.println("5. Exportar coches a archivo de texto");
		System.out.println("6. Terminar el programa");
	}

}
