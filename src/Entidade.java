import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Entidade {
	
	//Atributos
	protected int x, y, largura, altura;
	protected Color cor;
	
	//Construtores
	public Entidade(int x, int y, int largura, int altura) {
		this.x = x;
		this.y = y;
		this.largura = largura;
		this.altura = altura;
		this.cor = Color.white;
	}
	
	public Entidade(int largura, int altura) {
		Random r = new Random();
		this.x = r.nextInt(Jogo.LARGURA-largura-6);
		this.y = r.nextInt(Jogo.ALTURA-altura-30);
		this.largura = largura;
		this.altura = altura;
	}
	
	//Metodos
	public void desenhar(Graphics g) {
		g.setColor(this.cor);
		g.fillOval(this.x, this.y, this.largura, this.altura);
	}
	
	public boolean collideRect(Entidade e) {
		int distX = Math.abs(this.x+this.largura/2 - e.getX()+e.getLargura()/2);
		int distY = Math.abs(this.y+this.altura/2 - e.getY()+e.getAltura()/2);
		int separacaoHorizontal = this.largura/2 + e.getLargura()/2;
		int separacaoVertical = this.altura/2 + e.getAltura()/2;
		if(distX < separacaoHorizontal && distY < separacaoVertical) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void vibrar() {
		Random r = new Random();
		this.x += 1 - r.nextInt(3);
		this.y += 1 - r.nextInt(3);
		if(this.x < 0) {
			this.x = 0;
		}
		else if(this.x > (Jogo.LARGURA-this.largura-6)) {
			this.x = (Jogo.LARGURA-this.largura-6);
		}
		if(this.y < 0) {
			this.y = 0;
		}
		else if(this.y > (Jogo.ALTURA-this.altura-30)) {
			this.y = (Jogo.ALTURA-this.altura-30);
		}
	}
	
	public String posicao() {
		return "(" + this.x + " - " + this.y + ")";
	}
	
	//Metodos Especiais
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public Color getCor() {
		return cor;
	}

	public void setCor(Color cor) {
		this.cor = cor;
	}

}
