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

		alumnos = new ArrayList<>();

		alumnos.add(new Student(1L, "Germán Ginés", 23, "1º CFGS DAM", 2000,
				Arrays.asList(new Grade("PROGR", 8), new Grade("LM", 3))));

		alumnos.add(new Student(2L, "Baldomero", 21, "1º CFGS DAM", 0,
				Arrays.asList(new Grade("PROGR", 5), new Grade("LM", 4))));

		alumnos.add(new Student(3L, "Ana Guerra", 17, "1º CFGS SMR", 4000, Arrays.asList(new Grade("PROGR", 8))));

	}

	public List<Student> queryAllStudents() {

		

		return alumnos;

	}

	public long LegalAgeStudentCount() {

				return alumnos.stream().filter(edad -> edad.getEdad() >= 18).count();

	}

	public Stream<Student> StudentNamesOrderAlphabetically() {

		return alumnos.stream().sorted((x, y) -> x.getNombre().compareTo(y.getNombre()));

	}

	public Stream<Student> FistTwoStudentsNames() {

		return alumnos.stream().limit(2);

	}

	public Stream<Student> StudentsNamesExceptTheFistOne() {

		return alumnos.stream().skip(1);

	}

	public Stream<Student> StudentsNamesUntilFirstNotLegalAgeOne() {

		return alumnos.stream().takeWhile(alumno -> alumno.getEdad() >= 18);
				

	}

	public Stream<Student> StudentsSinceFirstNotLegalAgeOne() {

		return alumnos.stream().dropWhile(alumno -> alumno.getEdad() >= 18);

	}

	public Stream<String> DifferentSubjectsOrderedAlphabetically() {

		return alumnos.stream().map(alumno -> alumno.getNotas()).flatMap(alumno -> alumno.stream())
				.sorted((x, y) -> x.getAsignatura().compareTo(y.getAsignatura())).map(nota -> nota.getAsignatura())
				.distinct();

	}

	public List<String> StudentsOlderThan20() {

		return alumnos.stream().filter(alumno -> alumno.getEdad() >= 20).map(x -> x.getNombre()).collect(Collectors.toList());

	}

	public Stream<Student> YoungestStudentName() {

		return alumnos.stream().filter(
				edad -> edad.getEdad() == alumnos.stream().mapToInt(alumno -> alumno.getEdad()).min().getAsInt());

	}

	public String StudentNamesWithCommasOrderedByAge() {

		String separadoporcomas = alumnos.stream()
				.sorted((x, y) -> Integer.valueOf(x.getEdad()).compareTo(Integer.valueOf(y.getEdad())))
				.map(alumno -> alumno.getNombre()).collect(Collectors.joining(", "));

		return separadoporcomas;
	}

	public TreeMap<String, Long> StudentCountInEachGroup() {

		return new TreeMap<>(
				alumnos.stream().collect(Collectors.groupingBy(alumno -> alumno.getGrupo(), Collectors.counting())));

	}

	public IntSummaryStatistics GrantSummary() {

		return alumnos.stream().collect(Collectors.summarizingInt(a -> a.getBeca()));
		
		
		
	}

	public boolean AreAnyStudentUnderLegalAge() {

		return alumnos.stream().anyMatch(x -> x.getEdad() < 18);
	}

	public boolean AllStudentHaveGrant() {

		if (alumnos.stream().allMatch(x -> x.getBeca() > 0)) {

			return true;

		}

		else {

			return false;

		}

	}

	public Stream<Student> FirstStudentWithoutGrant() {

		Optional<Student> primeracoincidencia = alumnos.stream().filter(x -> x.getBeca() == 0).findFirst();

		return primeracoincidencia.stream();

	}

	public Map<Boolean, Long> HowManyStudentWithOrWithoutGrant() {

		return alumnos.stream()
				.collect(Collectors.partitioningBy(alumno -> alumno.getBeca() > 0, Collectors.counting()));

		}

	

	public Map<String, Long> NumberOfPassersStudentsOfEachSubject() {

		Stream<Grade> asignaturas = alumnos.stream().map(alumno -> alumno.getNotas()).flatMap(alumno -> alumno.stream())
				.sorted((x, y) -> x.getAsignatura().compareTo(y.getAsignatura()));

		return new TreeMap<>(asignaturas.collect(Collectors.groupingBy(
				nota -> nota.getAsignatura(), Collectors.filtering(a -> a.getNota() >= 5, Collectors.counting()))));

		}

	}
