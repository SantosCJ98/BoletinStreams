package clases;

import java.util.List;

public class Student {
	
	long id;
	
	String nombre;
	
	int edad;
	
	String grupo;
	
	int beca;
	
	List<Grade> notas;

	Student(long id, String nombre, int edad, String grupo, int beca, List<Grade> notas) {
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.grupo = grupo;
		this.beca = beca;
		this.notas = notas;
	}

	private long getId() {
		return id;
	}

	String getNombre() {
		return nombre;
	}

	int getEdad() {
		return edad;
	}

	private String getGrupo() {
		return grupo;
	}

	int getBeca() {
		return beca;
	}

	List<Grade> getNotas() {
		return notas;
	}

}
