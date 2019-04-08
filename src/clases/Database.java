package clases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Database {

	List<Student> alumnos;

	Database() {

		alumnos = queryAllStudents();

	}

	public List<Student> queryAllStudents() {

		alumnos = new ArrayList<>();

		alumnos.add(new Student(1L, "Germán Ginés", 23, "1º CFGS DAM", 2000,
				Arrays.asList(new Grade("PROGR", 8), new Grade("LM", 3))));

		alumnos.add(new Student(2L, "Baldomero", 21, "1º CFGS DAM", 0,
				Arrays.asList(new Grade("PROGR", 5), new Grade("LM", 4))));

		alumnos.add(new Student(3L, "Ana Guerra", 17, "1º CFGS SMR", 4000, Arrays.asList(new Grade("PROGR", 8))));

		return alumnos;

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

		bd.showDifferentSubjectsOrderedAlphabetically();

		System.out.println();

		bd.showStudentsGrantsAndSum();

		System.out.println();

		bd.getStudentsOlderThan20();

		System.out.println();

		bd.showYoungestStudentName();

		System.out.println();

		bd.showOldestStudentOlderThan23();

		System.out.println();

		bd.showStudentNamesWithCommasOrderedByAge();

		System.out.println();

		bd.showStudentCountInEachGroup();

		System.out.println();

		bd.showGrantSummary();

		System.out.println();

		bd.showAreAnyStudentUnderLegalAge();

		System.out.println();

		bd.showAllStudentHaveGrant();

		System.out.println();

		bd.showFirstStudentWithoutGrant();

		System.out.println();

		bd.showHowManyStudentWithOrWithoutGrant();

		System.out.println();

		bd.showNumberOfSubjectsOfEachStudent();

		System.out.println();

		bd.showNumberOfPassersStudentsOfEachSubject();

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

	public void showDifferentSubjectsOrderedAlphabetically() {

		System.out.println("Asignaturas:");

		alumnos.stream().map(alumno -> alumno.getNotas()).flatMap(alumno -> alumno.stream())
				.sorted((x, y) -> x.getAsignatura().compareTo(y.getAsignatura())).map(nota -> nota.getAsignatura())
				.distinct().forEach(System.out::println);

	}

	public void showStudentsGrantsAndSum() {

		System.out.println("Becas:");

		alumnos.stream().forEach(alumno -> System.out.println(alumno.getNombre() + ": " + alumno.getBeca()));

		System.out.printf("Suma de becas: %d", alumnos.stream().mapToInt(alumno -> alumno.getBeca()).sum());

	}

	public List<String> getStudentsOlderThan20() {

		List<String> lista = new ArrayList<>();

		alumnos.stream().filter(alumno -> alumno.getEdad() >= 20).forEach(alumno -> lista.add(alumno.getNombre()));

		return lista;

	}

	public void showYoungestStudentName() {


		alumnos.stream().filter(
				edad -> edad.getEdad() == alumnos.stream().mapToInt(alumno -> alumno.getEdad()).min().getAsInt())
				.forEach(nombre -> System.out.println("Alumno más joven: " + nombre.getNombre()));
		;

	}

	public void showOldestStudentOlderThan23() {

		System.out.print("Alumno más veterano mayor de 23: ");
		
		boolean condicion = (alumnos.stream().anyMatch(edad -> edad.getEdad() == alumnos.stream()
				.mapToInt(alumno -> Integer.valueOf(alumno.getEdad())).max().getAsInt() && edad.getEdad() > 23));

		if (condicion) {

			alumnos.stream()
					.filter(edad -> edad.getEdad() == alumnos.stream()
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

		String separadoporcomas = alumnos.stream()
				.sorted((x, y) -> Integer.valueOf(x.getEdad()).compareTo(Integer.valueOf(y.getEdad())))
				.map(alumno -> alumno.getNombre()).collect(Collectors.joining(", "));

		System.out.println(separadoporcomas);
	}

	public void showStudentCountInEachGroup() {

		System.out.println("Número de alumnos en cada grupo:");

		Map<String, Long> mapastream = new TreeMap<>(
				alumnos.stream().collect(Collectors.groupingBy(alumno -> alumno.getGrupo(), Collectors.counting())));

		for (Map.Entry<String, Long> mapa : mapastream.entrySet()) {
			System.out.println(mapa.getKey() + ": " + mapa.getValue() + " alumnos");
		}

	}

	public void showGrantSummary() {

		System.out.println("Estadística de becas:");

		IntSummaryStatistics calculos = alumnos.stream().collect(Collectors.summarizingInt(a -> (int) a.getBeca()));

		System.out.printf("Máxima: %d, Mínima: %d, Media: %.2f\n", calculos.getMax(), calculos.getMin(),
				calculos.getAverage());

	}

	public void showAreAnyStudentUnderLegalAge() {

		System.out.print("¿Algún alumno menor de edad? ");

		if (alumnos.stream().anyMatch(x -> x.getEdad() < 18)) {

			System.out.println("Sí");

		}

		else {

			System.out.println("No");

		}

	}

	public void showAllStudentHaveGrant() {

		System.out.print("¿Todos los alumnos tienen beca? ");

		if (alumnos.stream().allMatch(x -> x.getBeca() > 0)) {

			System.out.println("Sí");

		}

		else {

			System.out.println("No");

		}

	}

	public void showFirstStudentWithoutGrant() {

		System.out.print("Nombre del primer alumno sin beca: ");

		Optional<Student> primeracoincidencia = alumnos.stream().filter(x -> x.getBeca() == 0).findFirst();

		primeracoincidencia.stream().forEach(x -> System.out.println(x.getNombre()));

	}

	public void showHowManyStudentWithOrWithoutGrant() {

		System.out.println("Alumnos con o sin beca");

		Map<Boolean, Long> mapastream = alumnos.stream()
				.collect(Collectors.partitioningBy(alumno -> alumno.getBeca() > 0, Collectors.counting()));

		for (Map.Entry<Boolean, Long> mapa : mapastream.entrySet()) {
			System.out.printf("%s: %d\n", mapa.getKey() ? "Con beca" : "Sin beca", mapa.getValue());
		}

	}

	public void showNumberOfSubjectsOfEachStudent() {

		System.out.println("Número de asignaturas de cada alumno: ");

		alumnos.stream().forEach(x -> System.out.printf("%s: %d\n", x.getNombre(), x.getNotas().size()));

	}

	public void showNumberOfPassersStudentsOfEachSubject() {

		Stream<Grade> asignaturas = alumnos.stream().map(alumno -> alumno.getNotas()).flatMap(alumno -> alumno.stream())
				.sorted((x, y) -> x.getAsignatura().compareTo(y.getAsignatura()));

		Map<String, Long> mapastream = new TreeMap<>(asignaturas.collect(Collectors.groupingBy(
				nota -> nota.getAsignatura(), Collectors.filtering(a -> a.getNota() >= 5, Collectors.counting()))));

		for (Map.Entry<String, Long> mapa : mapastream.entrySet()) {
			System.out.printf("%s -  %d aprobados\n", mapa.getKey(), mapa.getValue());
		}

	}

}
