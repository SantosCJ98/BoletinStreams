package clases;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Mostrar {

	Database bd = new Database();

	public void showLegalAgeStudentAgeCount() {

		System.out.printf("%s: %d\n", "Número de alumnos mayores de edad", bd.legalAgeStudentCount());

	}

	public void showStudentNamesOrderAlphabetically() {

		System.out.println("Nombres de alumnos (Orden alfabético):");

		bd.studentNamesOrderAlphabetically().forEach(nombre -> System.out.println(nombre.getNombre()));

	}

	public void showFistTwoStudentsNames() {

		System.out.println("Nombres de los dos primeros alumnos:");

		bd.fistTwoStudentsNames().forEach(alumno -> System.out.println(alumno.getNombre()));

	}

	public void showStudentsNamesExceptTheFistOne() {

		System.out.println("Nombres de los alumnos: (Excepto el primero)");

		bd.studentsNamesExceptTheFistOne().forEach(alumno -> System.out.println(alumno.getNombre()));

	}

	public void showStudentsNamesUntilFirstNotLegalAgeOne() {

		System.out.println("Nombre de los alumnos (hasta que encontramos uno menor de edad):");

		bd.studentsNamesUntilFirstNotLegalAgeOne().forEach(alumno -> System.out.println(alumno.getNombre()));

	}

	public void showDifferentSubjectsOrderedAlphabetically() {

		System.out.println("Asignaturas:");

		bd.differentSubjectsOrderedAlphabetically().forEach(System.out::println);

	}

	public void showStudentsSinceFirstNotLegalAgeOne() {

		System.out.println("Nombre de los alumnos (desde que encontramos uno menor de edad):");

		bd.studentsSinceFirstNotLegalAgeOne().forEach(alumno -> System.out.println(alumno.getNombre()));

	}

	public void showStudentsGrantsAndSum() {

		System.out.println("Becas:");

		/*
		 * 
		 * bd.queryAllStudents().stream() .forEach(alumno ->
		 * System.out.println(alumno.getNombre() + ": " + alumno.getBeca()));
		 * 
		 * System.out.printf("Suma de becas: %d\n",
		 * bd.queryAllStudents().stream().mapToInt(alumno -> alumno.getBeca()).sum());
		 * 
		 */

		int suma = bd.queryAllStudents().stream()
				.peek(alumno -> System.out.printf("%s: %d\n", alumno.getNombre(), alumno.getBeca()))
				.mapToInt(Student::getBeca).sum();

		System.out.printf("Suma de becas: %d\n", suma);
	}

	public void getStudentsOlderThan20() {

		System.out.println(bd.studentsOlderThan20());

	}

	public void showYoungestStudentName() {

		// bd.youngestStudentName().forEach(nombre -> System.out.println("Alumno más
		// joven: " + nombre.getNombre()));

		String nombre = bd.queryAllStudents().stream().min(Comparator.comparingInt(Student::getEdad))
				.map(Student::getNombre).orElse("No encontrado");

		System.out.printf("Alumno más joven: %s\n", nombre);

	}

	public void showOldestStudentOlderThan23() {

		// System.out.print("Alumno más veterano mayor de 23: ");

		/*
		 * boolean condicion = (bd.queryAllStudents().stream() .anyMatch(edad ->
		 * edad.getEdad() == bd.queryAllStudents().stream() .mapToInt(alumno ->
		 * Integer.valueOf(alumno.getEdad())).max().getAsInt() && edad.getEdad() > 23));
		 * 
		 * if (condicion) {
		 * 
		 * bd.queryAllStudents().stream() .filter(edad -> edad.getEdad() ==
		 * bd.queryAllStudents().stream() .mapToInt(alumno ->
		 * Integer.valueOf(alumno.getEdad())).max().getAsInt() && edad.getEdad() > 23)
		 * .forEach(alumno -> System.out.println(alumno.getNombre()));
		 * 
		 * }
		 * 
		 * else {
		 * 
		 * System.out.println("No encontrado");
		 * 
		 * }
		 * 
		 */

		String resultado = bd.queryAllStudents().stream().filter(alumno -> alumno.getEdad() > 23)
				.max(Comparator.comparingInt(Student::getEdad)).map(Student::getNombre).orElse("No encontrado");

		System.out.printf("Alumno más veterano mayor de 23: %s\n", resultado);

	}

	public void showStudentNamesWithCommasOrderedByAge() {

		System.out.print("Alumnos: ");

		System.out.println(bd.studentNamesWithCommasOrderedByAge());

	}

	public void showStudentCountInEachGroup() {

		System.out.println("Número de alumnos en cada grupo:");

		for (Map.Entry<String, Long> mapa : bd.studentCountInEachGroup().entrySet()) {
			System.out.printf("%s: %d %s\n", mapa.getKey(), mapa.getValue(),
					mapa.getValue() == 1 ? "alumno" : "alumnos");
		}

	}

	public void showGrantSummary() {

		System.out.println("Estadística de becas:");

		System.out.printf("Máxima: %d, Mínima: %d, Media: %.2f\n", bd.grantSummary().getMax(),
				bd.grantSummary().getMin(), bd.grantSummary().getAverage());

	}

	public void showAreAnyStudentUnderLegalAge() {

		System.out.printf("¿Algún alumno menor de edad?: %s\n", bd.areAnyStudentUnderLegalAge() ? "Sí" : "No");

	}

	public void showAllStudentHaveGrant() {

		System.out.printf("¿Todos los alumnos tienen beca?: %s\n", bd.allStudentHaveGrant() ? "Sí" : "No");

	}

	public void showFirstStudentWithoutGrant() {

		System.out.printf("Nombre del primer alumno sin beca: %s\n",
				bd.firstStudentWithoutGrant().isPresent() ? bd.firstStudentWithoutGrant().get().getNombre()
						: "No encontrado");

	}

	public void showHowManyStudentWithOrWithoutGrant() {

		System.out.println("Alumnos con o sin beca");

		for (Map.Entry<Boolean, Long> mapa : bd.howManyStudentWithOrWithoutGrant().entrySet()) {
			System.out.printf("%s: %d\n", mapa.getKey() ? "Con beca" : "Sin beca", mapa.getValue());
		}

	}

	public void showNumberOfSubjectsOfEachStudent() {

		System.out.println("Número de asignaturas de cada alumno: ");

		bd.queryAllStudents().stream().forEach(x -> System.out.printf("%s: %d\n", x.getNombre(), x.getNotas().size()));

	}

	public void showNumberOfPassersStudentsOfEachSubject() {

		for (Map.Entry<String, Long> mapa : bd.numberOfPassersStudentsOfEachSubject().entrySet()) {
			System.out.printf("%s -  %d aprobados\n", mapa.getKey(), mapa.getValue());
		}

	}
}