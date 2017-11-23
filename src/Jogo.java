//Fabricio Vidal da Costa Junior
//22/11/2017

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Jogo extends JPanel implements ActionListener, KeyListener{
	
	public static final int LARGURA = 300, ALTURA = 300;
	public static JFrame tela = new JFrame("AgarJar - (22/11/2017)");
	public static Jogo jogo = new Jogo();
	public static Timer t = new Timer(16, jogo);
	public static long frameCount = 0;
	public static ArrayList<Comida> comidas = new ArrayList<Comida>();
	public static Random r = new Random();
	public static int randomTime = 1;
	public static int recorde;
	public static boolean gameOver = false;
	public static long inicio, fim, tempoTotal;
	public static PrintWriter write;
	
	public static Player p1 = new Player(135, 130);
	private static BufferedReader br;
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		//SETUP
		tela.setVisible(true);
		tela.setSize(LARGURA, ALTURA);
		tela.setResizable(false);
		tela.setLocationRelativeTo(null);
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jogo.setBackground(Color.black);
		tela.add(jogo);
		tela.addKeyListener(jogo);
		inicio = System.currentTimeMillis();
		
		atualizarRecorde();
		
		t.start();
		
	}
	
	//DRAW
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(comidas.size() > 0) {
			for(Comida c : comidas) {
				if(c.getTipo() == 2) {
					c.vibrar();
				}
				c.envelhecer();
				c.desenhar(g);
				if(p1.collideRect(c)) {
					comidas.remove(c);
					p1.comer(c.getEnergia());
					break;
				}
			}
		}
		
		p1.desenhar(g);
		g.drawString(recorde+"", 0, ALTURA-30);
		logo(g);
		
		if(gameOver) {
			g.drawString("GAME OVER", 115, 140);
			g.drawString(tempoTotal + " segundos", 112, 160);
			if(recorde < tempoTotal && frameCount != 0) {
				try {
					write = new PrintWriter("recorde.txt", "UTF-8");
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				write.print(tempoTotal);
				write.close();
			}
		}
	}
	
	//PROCESSAMENTOS
	@Override
	public void actionPerformed(ActionEvent event) {
		if(!gameOver) {
			frameCount++;
			everyTick();
			p1.mover();
			if(p1.energia < 1) {
				gameOver();
			}
			repaint();
		}
		else {
			frameCount = 0;
		}
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent key) {
		p1.botaoPressionado(key.getKeyChar());
		if(key.getKeyCode() == KeyEvent.VK_ENTER) {
			reset();
		}
	}

	@Override
	public void keyReleased(KeyEvent key) {
		p1.botaoSolto(key.getKeyChar());
	}

	@Override
	public void keyTyped(KeyEvent key) {
		
	}
	
	//Outros Metodos
	public void gerarComida() {
		comidas.add(new Comida());
	}
	
	public void everyTick() {
		if(frameCount % 31 == 0) {
			p1.gastarEnergia();
		}
		if(frameCount % randomTime == 0) {
			gerarComida();
			randomTime = r.nextInt(124) + 33;
		}
	}
	
	public void gameOver() {
		fim = System.currentTimeMillis();
		tempoTotal = (fim-inicio)/1000;
		gameOver = true;
	}
	
	public void logo(Graphics g) {
		g.setFont(new Font("Arial", Font.PLAIN, 10)); 
		g.drawString("Fabricio Junior", 0, 10);
	}
	
	public void reset() {
		p1.reset();
		comidas.clear();
		inicio = System.currentTimeMillis();
		atualizarRecorde();
		gameOver = false;
	}
	
	public static void atualizarRecorde() {
		try {
			br = new BufferedReader(new FileReader("recorde.txt"));
			String linha = br.readLine();
			recorde = Integer.parseInt(linha);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
