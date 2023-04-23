package died.guia06;

import java.util.ArrayList;
import java.util.List;

public class Alumno implements Comparable<Alumno> {

	private String nombre;
	private Integer nroLibreta;
	private List<Curso> cursando;
	private List<Curso> aprobados;

	public Alumno(String nombre, Integer nroLibreta) {
		this.nombre = nombre;
		this.nroLibreta = nroLibreta;
		this.cursando = new ArrayList<Curso>();
		this.aprobados = new ArrayList<Curso>();
	}

	@Override
	public String toString() {
		return "Alumno [nombre=" + nombre + ", nroLibreta=" + nroLibreta + "]";
	}

	public int creditosObtenidos() {
		return aprobados.stream()
				.mapToInt(Curso::getCreditos)
				.sum();
	}

	public void aprobar(Curso c) {
		this.cursando.remove(c);
		this.aprobados.add(c);
	}

	public void inscripcionAceptada(Curso c) {
		this.cursando.add(c);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nroLibreta == null) ? 0 : nroLibreta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		if (nroLibreta == null) {
			if (other.nroLibreta != null)
				return false;
		} else if (!nroLibreta.equals(other.nroLibreta))
			return false;
		return true;
	}

	@Override
	public int compareTo(Alumno o) {
		return this.nombre.compareTo(o.getNombre());
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNroLibreta() {
		return nroLibreta;
	}

	public void setNroLibreta(Integer nroLibreta) {
		this.nroLibreta = nroLibreta;
	}

	public List<Curso> getCursando() {
		return cursando;
	}

	public void setCursando(List<Curso> cursando) {
		this.cursando = cursando;
	}

	public List<Curso> getAprobados() {
		return aprobados;
	}

	public void setAprobados(List<Curso> aprobados) {
		this.aprobados = aprobados;
	}

	public int cursosDelCicloLectivo(Integer cicloLectivo) {
		return (int) cursando.stream()
				.filter(c -> c.getCicloLectivo() == cicloLectivo)
				.count();
	}

}
