package clases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.LinkedHashMap;
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

		alumnos.add(new Student(3L, "Ana Guerra", 17, "1º CFGM SMR", 4000, Arrays.asList(new Grade("PROGR", 8))));

	}

	public List<Student> queryAllStudents() {

		return alumnos;

	}

	public long legalAgeStudentCount() {

		return alumnos.stream().filter(edad -> edad.getEdad() >= 18).count();

	}

	public Stream<Student> studentNamesOrderAlphabetically() {

		return alumnos.stream().sorted((x, y) -> x.getNombre().compareTo(y.getNombre()));

	}

	public Stream<Student> fistTwoStudentsNames() {

		return alumnos.stream().limit(2);

	}

	public Stream<Student> studentsNamesExceptTheFistOne() {

		return alumnos.stream().skip(1);

	}

	public Stream<Student> studentsNamesUntilFirstNotLegalAgeOne() {

		return alumnos.stream().takeWhile(alumno -> alumno.getEdad() >= 18);

	}

	public Stream<Student> studentsSinceFirstNotLegalAgeOne() {

		return alumnos.stream().dropWhile(alumno -> alumno.getEdad() >= 18);

	}

	public Stream<String> differentSubjectsOrderedAlphabetically() {

		return alumnos.stream().flatMap(alumno -> alumno.getNotas().stream()).map(nota -> nota.getAsignatura())
				.distinct().sorted();

	}

	public List<String> studentsOlderThan20() {

		return alumnos.stream().filter(alumno -> alumno.getEdad() >= 20).map(x -> x.getNombre())
				.collect(Collectors.toList());

	}

	public Stream<Student> youngestStudentName() {

		return alumnos.stream().filter(
				edad -> edad.getEdad() == alumnos.stream().mapToInt(alumno -> alumno.getEdad()).min().getAsInt());

	}

	public String studentNamesWithCommasOrderedByAge() {

		return alumnos.stream().sorted(Comparator.comparingInt(Student::getEdad)).map(Student::getNombre)
				.collect(Collectors.joining(", "));
	}

	public TreeMap<String, Long> studentCountInEachGroup() {

		return new TreeMap<>(alumnos.stream().sorted(Comparator.comparing(Student::getGrupo))
				.collect(Collectors.groupingBy(Student::getGrupo, Collectors.counting())));

	}

	public IntSummaryStatistics grantSummary() {

		return alumnos.stream().collect(Collectors.summarizingInt(Student::getBeca));

	}

	public boolean areAnyStudentUnderLegalAge() {

		return alumnos.stream().anyMatch(x -> x.getEdad() < 18);
	}

	public boolean allStudentHaveGrant() {

		return alumnos.stream().allMatch(x -> x.getBeca() > 0);

	}

	public Optional<Student> firstStudentWithoutGrant() {

		return alumnos.stream().filter(x -> x.getBeca() == 0).findFirst();

	}

	public Map<Boolean, Long> howManyStudentWithOrWithoutGrant() {

		return alumnos.stream()
				.collect(Collectors.partitioningBy(alumno -> alumno.getBeca() > 0, Collectors.counting()));

	}

	public Map<String, Long> numberOfPassersStudentsOfEachSubject() {

		return new TreeMap<>(alumnos.stream().flatMap(alumno -> alumno.getNotas().stream()).collect(Collectors
				.groupingBy(Grade::getAsignatura, Collectors.filtering(a -> a.getNota() >= 5, Collectors.counting()))));

	}

}
