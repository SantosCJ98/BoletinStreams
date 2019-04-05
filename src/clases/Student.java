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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + beca;
		result = prime * result + edad;
		result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((notas == null) ? 0 : notas.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Student))
			return false;
		Student other = (Student) obj;
		if (beca != other.beca)
			return false;
		if (edad != other.edad)
			return false;
		if (grupo == null) {
			if (other.grupo != null)
				return false;
		} else if (!grupo.equals(other.grupo))
			return false;
		if (id != other.id)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (notas == null) {
			if (other.notas != null)
				return false;
		} else if (!notas.equals(other.notas))
			return false;
		return true;
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
