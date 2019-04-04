package clases;

public class Grade {
	
	String asignatura;
	
	float nota;

	Grade(String asignatura, float nota) {
		this.asignatura = asignatura;
		this.nota = nota;
	}

	String getAsignatura() {
		return asignatura;
	}

	private float getNota() {
		return nota;
	}

}
