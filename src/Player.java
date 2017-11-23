import java.awt.Graphics;

public class Player extends Entidade{

	//Atributos
	protected int velocidade, energia;
	protected boolean[] movimentos;
	
	
	//Construtor
	public Player(int x, int y) {
		super(x, y, 20, 20);
		this.velocidade = 3;
		this.energia = 20;
		this.movimentos = new boolean[4];
	}
	
	//Metodos
	public void desenhar(Graphics g) {
		g.setColor(this.cor);
		g.fillOval(this.x, this.y, this.energia, this.energia);
		if(this.energia >= 0) {
			g.drawString("" + this.energia, this.x, this.y);
		}
		else {
			this.energia = 0;
		}
	}
	
	public void comer(int energia) {
		if(this.energia <= 195) {
			this.energia += energia;
		}
	}
	
	public void gastarEnergia() {
		this.energia--;
		this.largura = this.energia;
		this.altura = this.energia;
	}
	
	public void mover() {
		//Eixo X
		if(this.movimentos[1] == true && this.x > 0) {
			this.x -= this.velocidade;
		}
		if(this.movimentos[3] == true && this.x < (Jogo.LARGURA-this.largura-6)) {
			this.x += this.velocidade;
		}
		//Eixo Y
		if(this.movimentos[0] == true && this.y > 0) {
			this.y -= this.velocidade;
		}
		if(this.movimentos[2] == true && this.y < (Jogo.ALTURA-this.altura-30)) {
			this.y += this.velocidade;
		}
	}
	
	public void botaoPressionado(char key) {
		switch(key){
			//Eixo X
			case 'a':
				this.movimentos[1] = true;
				break;
			case 'd':
				this.movimentos[3] = true;
				break;
			//Eixo Y
			case 'w':
				this.movimentos[0] = true;
				break;
			case 's':
				this.movimentos[2] = true;
				break;
		}
	}
	
	public void botaoSolto(char key) {
		//Eixo X
		if(key == 'a') {
			this.movimentos[1] = false;
		}
		else if(key == 'd') {
			this.movimentos[3] = false;
		}
		//Eixo Y
		if(key == 'w') {
			this.movimentos[0] = false;
		}
		else if(key == 's') {
			this.movimentos[2] = false;
		}
		this.setVelocidade();
	}
	
	public void setVelocidade() {
		if(this.energia < 30) {
			this.velocidade = 3;
		}
		else if(this.energia < 50) {
			this.velocidade = 2;
		}
		else{
			this.velocidade = 1;
		}
	}
	
	public void reset() {
		this.x = 135;
		this.y = 135;
		this.energia = 20;
		this.velocidade = 3;
	}
	
}
