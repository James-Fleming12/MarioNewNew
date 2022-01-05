import java.awt.Graphics;
	import java.awt.Graphics2D;
	import java.awt.Image;
	import java.awt.Toolkit;
	import java.awt.event.MouseEvent;
	import java.awt.event.MouseListener;
	import java.awt.geom.AffineTransform;
	import java.net.URL;
	import java.awt.Graphics;
	import java.awt.Graphics2D;
	import java.awt.Image;
	import java.awt.Toolkit;
	import java.awt.event.MouseEvent;
	import java.awt.event.MouseListener;
	import java.awt.geom.AffineTransform;
	import java.net.URL;
	public class character {
		//add location attributes
		private Image img; 	
		private AffineTransform tx;
		int x, y;
		int speed; 
		public character() {
			img = getImage("/imgs/Mario standing.png"); //load the image for Tree
			tx = AffineTransform.getTranslateInstance(x, y);
			init(x, y); 				//initialize the location of the image
										//use your variables
			speed=0;
		}
	
		public void changePicture(String newFileName) {
			img = getImage(newFileName);
			init(x, y);
		}
		
		public void paint(Graphics g) {
			//these are the 2 lines of code needed draw an image on the screen
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(img, tx, null);
			x+=speed; 
			update();
		}
		//lll
		
		public void jump() {
//			int start = y; 
//			for(int i = -100; i<=100 ; i++) {
//				y=(int)(start+(-0.1*(i*i)+1000));
//				update();
//			}
			int start = y;
			int acc = 500;
			int accChange = 0;
			for(int i = 0; i<1000; i++) {
				y=start+(acc-accChange);
				accChange+=1; 
			}
		}
			//update the picture variable location
		private void update() {
			tx.setToTranslation(x,y);
			tx.scale(0.5, 0.5);
		}
		
		private void init(double a, double b) {
			tx.setToTranslation(a, b);
			tx.scale(.5, .5);
		}

		private Image getImage(String path) {
			Image tempImage = null;
			try {
				URL imageURL = character.class.getResource(path);
				tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return tempImage;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}
		public int getSpeed() {
			return speed;
		}

		public void setSpeed(int speed) {
			this.speed = speed;
		}


		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

	}


