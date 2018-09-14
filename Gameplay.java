package breakbricks;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
	
	private boolean play=false;
	private int score=0;
	private int totalBricks=21;
	
	private Timer timer;
	private int delay=8;
	private int playerX=310;// start pos of slider
	private int ballposX=120;
	private int ballposY=350;
	private int ballXdir=-1;
	private int ballYdir=-2;
	private MapGenerator m;
	
	public Gameplay()
	{
		m=new MapGenerator(3,7);
		addKeyListener(this);
		setFocusable(true);//text pr focus jo dikhta apne ko vo
		setFocusTraversalKeysEnabled(false);// tab se next box me jata vo jane dena ki nai
		timer= new Timer(delay,this);
		timer.start();	
	}
	public void paint (Graphics g)
	{
		//background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		//borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		g.fillRect(0,568, 692, 3);
		//scores
		g.setColor(Color.white);
		g.drawString(score+"", 650, 30);
		//paddle
		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100, 8);
		//map of bricks
		m.draw((Graphics2D) g);
		//the ball
		g.setColor(Color.YELLOW);
		g.fillOval(ballposX, ballposY, 20, 20);
		if(totalBricks<=0)
		{
			play=false;
			ballXdir=0;
			ballYdir=0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif",Font.BOLD,50));
			g.drawString("Congarts, You Won!", 150, 300);
			
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Press Enter to replay ", 230, 500);
		}
		
		else if(ballposY>570)
		{
			play=false;
			ballXdir=0;
			ballYdir=0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif",Font.BOLD,50));
			g.drawString("Game Over, Score: "+score, 150, 300);
			
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Press Enter to replay ", 230, 500);
		}
		
		g.dispose();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//timer.start();
		if(play)
		{
			if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8)))
			{
				ballYdir=-ballYdir;
			}
		}
		
		A:for(int i=0;i<m.map.length;i++)
			for(int j=0;j<m.map[0].length;j++)
				if(m.map[i][j]>0)
				{
					Rectangle brickrect =new Rectangle(j*m.brickWidth+80, i*m.brickHight+80, m.brickWidth, m.brickHight);
					Rectangle ballrect =new Rectangle(ballposX,ballposY,20,20);
					if(ballrect.intersects(brickrect))
					{
						m.setBrickValue(0, i, j);
						totalBricks--;
						score+=5;
						
						if(ballposX + 19 <= brickrect.x || ballposX + 1 >= brickrect.x+ brickrect.width )
							ballXdir=-ballXdir;
						else
							ballYdir= -ballYdir;
						break A;
					}
				}
		
		ballposX+=ballXdir;
		ballposY+=ballYdir;

		if(ballposX<0)// left border and ball intersection
			ballXdir=-ballXdir;
		if(ballposY<0)// top border
			ballYdir=-ballYdir;
		if(ballposX>670)//right border
			ballXdir=-ballXdir;
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			if(playerX>=600)
			{
				playerX=600;
			}
			else
				moveRight();
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			if(playerX<10)
				playerX=10;
			else
				moveLeft();
		}
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			play=true;
			score=0;
			totalBricks=21;
			playerX=310;// start pos of slider
			ballposX=120;
			ballposY=350;
			ballXdir=-1;
			ballYdir=-2;		
			
			m=new MapGenerator(3,7);
			
			repaint();
			
		}
	}
	public void moveRight()
	{
		play=true;
		playerX+=20;
	}
	public void moveLeft()
	{
		play=true;
		playerX-=20;
	}
	

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
