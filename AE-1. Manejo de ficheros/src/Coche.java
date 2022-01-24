import java.io.Serializable;

public class Coche implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String matricula;
	private String marca;
	private String modelo;
	private String color;

	
	
	
	public Coche() {
		super();
	}


	public Coche(int id,String matricula, String marca, String modelo, String color) {
		super();
		this.id = id;
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	@Override
	public String toString() {
		return "Coche [id=" + id + ", matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo + ", color="
				+ color + "]";
	}
	
	public String exportar() {  //Cambiamos el formato por si se quisiera utilizar después para exportarlo a una BBDD
		return  id + "-" + matricula + "-" + marca + "-" + modelo + "-"
				+ color;
	}
	
	
	
	
	

}
