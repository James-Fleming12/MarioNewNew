import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements KeyListener, ActionListener{
	
	character test =  new character();
	Ground gr = new Ground();
	ArrayList<Ground> groundList = new ArrayList<>();
	ArrayList<Block> blockList = new ArrayList<>();
	boolean onRight = false;
	boolean onLeft = false;
	Block block = new Block();
	boolean onBlock = false;
	int whichBlock = 0; 
	public void paint(Graphics g) {
		super.paintComponent(g);
		test.paint(g);
		for(Ground gr : groundList) {
			gr.paint(g);
		}
		for(Block bl : blockList) {
			bl.paint(g);
		}
		
		for(int i = 0; i<blockList.size(); i++) {
			if(blockList.get(i).getX()-50<test.getX() && blockList.get(i).getX()+30>test.getX()) {
				if(test.getY()<=blockList.get(i).getY()-64 && test.getY()>=blockList.get(i).getY()-70) {
					test.setY(blockList.get(i).getY()-64);
					test.setFallSpeed(0);
					test.setGravity(0);
					test.setY(test.getY()+100);
				}else {
					test.setFallSpeed(-1);
					test.setGravity(1.5);
				}
//				if(test.collisionPoint(blockList.get(i).getX(), blockList.get(i).getX()+64, blockList.get(i).getY(), blockList.get(i).getY()+64)) {
//					test.setFallSpeed(0);
//					test.setGravity(0);
//				}else {
//					test.setFallSpeed(-1);
//					
//				}
			}
		}
		
		
		//collision is under paint because we want to check 
		// multiple times per second ! not just a keypress
		if(test.getY()>=gr.getY()-64) {
			test.setY(gr.getY()-64);
			test.setFallSpeed(0);
			test.setGravity(0);
		}
		//check if there's overlap in the two objects x and y intervals
		//Collision for Left
		if(test.getX()>=400) {
			onRight=true;
		}
		
		if(test.getX()<0) {
			onLeft=true;
		}
		if(onRight) {
			test.setX(test.getX()-5);
			for(Ground gr : groundList) {
				gr.setX(gr.getX()-5);
			}
			for(Block bl : blockList) {
				bl.setX(bl.getX()-5);
			}
		}
		if(onLeft) {
			test.setX(test.getX()+5);
			for(Ground gr : groundList) {
				gr.setX(gr.getX()+5);
			}
			for(Block bl : blockList) {
				bl.setX(bl.getX()+5);
			}
		}

	}
	

	
	
	
	public Frame() {
		JFrame f = new JFrame("Mario");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800,600);
		f.add(this);
		f.addKeyListener(this);
		t = new Timer(16, this);
		t.start();
		f.setVisible(true);
		for(int i = 0; i<3; i++) {
			Ground temp = new Ground(1000*i); 
			groundList.add(temp);
		}
		blockList.add(new Block());
	}
	
	public static void main(String[] arg) {
		Frame f = new Frame();		
	}	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		if(arg0.getKeyCode()==39) {
			test.setSpeed(5);
			test.changePicture("/imgs/Mario running.gif");
		}
		if(arg0.getKeyCode()==38) {
			test.jump();
		}
		if(arg0.getKeyCode()==37) {
			test.setSpeed(-5);
			test.changePicture("/imgs/left mario running.gif");
		}


		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==39) {
			test.setSpeed(0);
			test.changePicture("/imgs/Mario standing.png");
			onRight=false;
		}
		if(arg0.getKeyCode()==37) {
			test.setSpeed(0);
			onLeft =false; 
			test.changePicture("/imgs/mario leftstanding.png");
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
//	
//	public void mouseClicked(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		System.out.println("Y" + arg0.get);
//		System.out.println("X" + arg0.getX);
//	}
//	
	Timer t;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}
}
