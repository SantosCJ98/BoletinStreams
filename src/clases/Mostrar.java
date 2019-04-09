package clases;

import java.util.IntSummaryStatistics;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Mostrar {

	Database bd = new Database();
	
	public void showLegalAgeStudentAgeCount() {

		System.out.printf("%s: %d\n", "Número de alumnos mayores de edad", bd.LegalAgeStudentCount());

	}

	public void showStudentNamesOrderAlphabetically() {

		System.out.println("Nombres de alumnos (Orden alfabético):");

		bd.StudentNamesOrderAlphabetically().forEach(nombre -> System.out.println(nombre.getNombre()));

	}

	public void showFistTwoStudentsNames() {

		System.out.println("Nombres de los dos primeros alumnos:");

		bd.FistTwoStudentsNames().forEach(alumno -> System.out.println(alumno.getNombre()));

	}

	public void showStudentsNamesExceptTheFistOne() {

		System.out.println("Nombres de los alumnos: (Excepto el primero)");

		bd.StudentsNamesExceptTheFistOne().forEach(alumno -> System.out.println(alumno.getNombre()));

	}

	public void showStudentsNamesUntilFirstNotLegalAgeOne() {

		System.out.println("Nombre de los alumnos (hasta que encontramos uno menor de edad):");

		bd.StudentsNamesUntilFirstNotLegalAgeOne().forEach(alumno -> System.out.println(alumno.getNombre()));

	}
	
	public void showDifferentSubjectsOrderedAlphabetically() {
		
		System.out.println("Asignaturas:");
		
		bd.DifferentSubjectsOrderedAlphabetically().forEach(System.out::println);
		
	}

	public void showStudentsSinceFirstNotLegalAgeOne() {

		System.out.println("Nombre de los alumnos (desde que encontramos uno menor de edad):");

		bd.StudentsSinceFirstNotLegalAgeOne().forEach(alumno -> System.out.println(alumno.getNombre()));

	}

	public void showStudentsGrantsAndSum() {

		System.out.println("Becas:");

		bd.queryAllStudents().stream()
				.forEach(alumno -> System.out.println(alumno.getNombre() + ": " + alumno.getBeca()));

		System.out.printf("Suma de becas: %d\n",
				bd.queryAllStudents().stream().mapToInt(alumno -> alumno.getBeca()).sum());

	}
	
	public void getStudentsOlderThan20() {
		
		System.out.println(bd.StudentsOlderThan20());
		
	}
	
	public void showYoungestStudentName() {

		bd.YoungestStudentName().forEach(nombre -> System.out.println("Alumno más joven: " + nombre.getNombre()));

	}
	
	public void showOldestStudentOlderThan23() {

		System.out.print("Alumno más veterano mayor de 23: ");

		boolean condicion = (bd.queryAllStudents().stream().anyMatch(edad -> edad.getEdad() == bd.queryAllStudents().stream()
				.mapToInt(alumno -> Integer.valueOf(alumno.getEdad())).max().getAsInt() && edad.getEdad() > 23));

		if (condicion) {

			bd.queryAllStudents().stream()
					.filter(edad -> edad.getEdad() == bd.queryAllStudents().stream()
							.mapToInt(alumno -> Integer.valueOf(alumno.getEdad())).max().getAsInt()
							&& edad.getEdad() > 23)
					.forEach(alumno -> System.out.println(alumno.getNombre()));

		}

		else {

			System.out.println("No encontrado");

		}

	}
	
	public void showStudentNamesWithCommasOrderedByAge() {
		
		System.out.print("Alumnos: ");

		System.out.println(bd.StudentNamesWithCommasOrderedByAge());
		
	}
	
	public void showStudentCountInEachGroup() {

		System.out.println("Número de alumnos en cada grupo:");

		for (Map.Entry<String, Long> mapa : bd.StudentCountInEachGroup().entrySet()) {
			System.out.println(mapa.getKey() + ": " + mapa.getValue() + " alumnos");
		}

	}
	
	public void showGrantSummary() {

		System.out.println("Estadística de becas:");

		System.out.printf("Máxima: %d, Mínima: %d, Media: %.2f\n", bd.GrantSummary().getMax(), bd.GrantSummary().getMin(),
				bd.GrantSummary().getAverage());

	}
	
	public void showAreAnyStudentUnderLegalAge() {

		System.out.print("¿Algún alumno menor de edad? ");

		if (bd.AreAnyStudentUnderLegalAge()) {

			System.out.println("Sí");

		}

		else {

			System.out.println("No");

		}

	}
	
	public void showAllStudentHaveGrant() {

		System.out.print("¿Todos los alumnos tienen beca? ");

		if (bd.AllStudentHaveGrant()) {

			System.out.println("Sí");

		}

		else {

			System.out.println("No");

		}

	}
	
	public void showFirstStudentWithoutGrant() {

		System.out.print("Nombre del primer alumno sin beca: ");

		bd.FirstStudentWithoutGrant().forEach(x -> System.out.println(x.getNombre()));

	}
	
	public void showHowManyStudentWithOrWithoutGrant() {

		System.out.println("Alumnos con o sin beca");

		for (Map.Entry<Boolean, Long> mapa : bd.HowManyStudentWithOrWithoutGrant().entrySet()) {
			System.out.printf("%s: %d\n", mapa.getKey() ? "Con beca" : "Sin beca", mapa.getValue());
		}

	}
	
	public void showNumberOfSubjectsOfEachStudent() {

		System.out.println("Número de asignaturas de cada alumno: ");

		bd.queryAllStudents().stream().forEach(x -> System.out.printf("%s: %d\n", x.getNombre(), x.getNotas().size()));

	}
	
	public void showNumberOfPassersStudentsOfEachSubject() {

		for (Map.Entry<String, Long> mapa : bd.NumberOfPassersStudentsOfEachSubject().entrySet()) {
			System.out.printf("%s -  %d aprobados\n", mapa.getKey(), mapa.getValue());
		}

	}
}