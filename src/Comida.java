import java.awt.Color;
import java.util.Random;

public class Comida extends Entidade{
	
	//Atributos
	private int tipo, energia, idade;

	//Construtor
	public Comida() {
		super(5, 5);
		Random r = new Random();
		this.idade = 0;
		this.tipo = r.nextInt(3) + 1;
		switch(this.tipo) {
			case 1:
				this.cor = Color.green;
				this.energia = 3;
				break;
			case 2:
				this.cor = Color.yellow;
				this.energia = 5;
				break;
			case 3:
				this.cor = Color.red;
				this.energia = -10;
				break;
		}
	}
	
	//Metodos
	public void reset() {
		Random r = new Random();
		this.x = r.nextInt(Jogo.LARGURA-largura-6);
		this.y = r.nextInt(Jogo.ALTURA-altura-30);
		this.idade = 0;
		this.tipo = r.nextInt(3) + 1;
		switch(this.tipo) {
			case 1:
				this.cor = Color.green;
				this.energia = 3;
				break;
			case 2:
				this.cor = Color.yellow;
				this.energia = 5;
				break;
			case 3:
				this.cor = Color.red;
				this.energia = -10;
				break;
		}
	}
	
	public void envelhecer() {
		this.idade++;
		if(this.idade >= 1875 && this.tipo != 3) {
			this.reset();
		}
	}
	
	//Metodos Especiais
	public int getEnergia() {
		return energia;
	}

	public void setEnergia(int energia) {
		this.energia = energia;
	}
	
	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

}
