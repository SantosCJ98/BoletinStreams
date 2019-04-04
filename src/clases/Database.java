package clases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Database {

	private List<Student> alumnos;

	public List<Student> queryAllStudents() {

		return alumnos;

	}

	Database() {

		alumnos = new ArrayList<>();

		alumnos.add(new Student(1L, "Germán Ginés", 23, "1º CFGS DAM", 2000,
				Arrays.asList(new Grade("PROGR", 8), new Grade("LM", 3))));

		alumnos.add(new Student(2L, "Baldomero", 21, "1º CFGS DAM", 0,
				Arrays.asList(new Grade("PROGR", 5), new Grade("LM", 4))));

		alumnos.add(new Student(3L, "Ana Guerra", 17, "1º CFGS SMR", 4000, Arrays.asList(new Grade("PROGR", 8))));

	}

	public static void main(String args[]) {

		Database bd = new Database();

		bd.showLegalAgeStudentCount();

		System.out.println();

		bd.showStudentNamesOrderAlphabetically();

		System.out.println();

		bd.showFistTwoStudentsNames();

		System.out.println();

		bd.showStudentsNamesExceptTheFistOne();

		System.out.println();

		bd.showStudentsNamesUntilFirstNotLegalAgeOne();

		System.out.println();

		bd.showStudentsSinceFirstNotLegalAgeOne();

		System.out.println();

		bd.showStudentsGrantsAndSum();

		System.out.println();

		bd.getStudentsOlderThan20();
		
		System.out.println();
		
		bd.showYoungestStudentName();
		
		System.out.println();
		
		bd.showOldestStudentOlderThan23();
		
		bd.showStudentNamesWithCommasOrderedByAge();

	}

	public void showLegalAgeStudentCount() {

		System.out.printf("Número de alumnos mayores de edad: %d\n",
				alumnos.stream().filter(edad -> edad.getEdad() >= 18).count());

	}

	public void showStudentNamesOrderAlphabetically() {

		System.out.println("Nombres de alumnos (Orden alfabético):");

		alumnos.stream().sorted((x, y) -> x.getNombre().compareTo(y.getNombre()))
				.forEach(nombre -> System.out.println(nombre.getNombre()));

	}

	public void showFistTwoStudentsNames() {

		System.out.println("Nombres de los dos primeros alumnos:");

		alumnos.stream().limit(2).forEach(alumno -> System.out.println(alumno.getNombre()));

	}

	public void showStudentsNamesExceptTheFistOne() {

		System.out.println("Nombres de los alumnos: (Excepto el primero)");

		alumnos.stream().skip(1).forEach(alumno -> System.out.println(alumno.getNombre()));

	}

	public void showStudentsNamesUntilFirstNotLegalAgeOne() {

		System.out.println("Nombre de los alumnos (hasta que encontramos uno menor de edad):");

		alumnos.stream().takeWhile(alumno -> alumno.getEdad() >= 18)
				.forEach(alumno -> System.out.println(alumno.getNombre()));

	}

	public void showStudentsSinceFirstNotLegalAgeOne() {

		System.out.println("Nombre de los alumnos (desde que encontramos uno menor de edad):");

		alumnos.stream().dropWhile(alumno -> alumno.getEdad() >= 18)
				.forEach(alumno -> System.out.println(alumno.getNombre()));

	}

	// Pendiente el 7

	public void showStudentsGrantsAndSum() {

		System.out.println("Becas:");

		alumnos.stream().forEach(alumno -> System.out.println(alumno.getNombre() + ": " + alumno.getBeca()));

		System.out.printf("Suma de becas: %d",
				alumnos.stream().mapToInt(alumno -> Integer.valueOf(alumno.getBeca())).sum());

	}

	public List<String> getStudentsOlderThan20() {

		List<String> lista = new ArrayList<>();

		alumnos.stream().filter(alumno -> alumno.getEdad() >= 20).forEach(alumno -> lista.add(alumno.getNombre()));

		return lista;

	}
	
	public void showYoungestStudentName() {

	// alumnos.stream().mapToInt(alumno -> Integer.valueOf(alumno.getEdad())).min(); Edad
		
		alumnos.stream().filter(edad -> edad.getEdad() == alumnos.stream().mapToInt(alumno -> Integer.valueOf(alumno.getEdad())).min().getAsInt()).forEach(nombre -> System.out.println("Alumno más joven: " + nombre.getNombre()));;

	}
	
	
	public void showOldestStudentOlderThan23() {
		
		alumnos.stream().filter(edad -> edad.getEdad() == alumnos.stream().mapToInt(alumno -> Integer.valueOf(alumno.getEdad())).max().getAsInt() && edad.getEdad() > 23).forEach(nombre -> System.out.println("Alumno más veterano mayor de 23: " + nombre.getNombre()));
		
	}
	
	public void showStudentNamesWithCommasOrderedByAge() {

		System.out.print("Alumnos: ");

		alumnos.stream().sorted((x, y) -> Integer.valueOf(x.getEdad()).compareTo(Integer.valueOf(y.getEdad())))
				.forEach(nombre -> System.out.print(nombre.getNombre() + ", "));

	}

	

}
