import javax.swing.JPanel;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.util.Random;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gameplay extends JPanel implements KeyListener,ActionListener
{
	private ImageIcon titleImage;

	private int[] snakexlength=new int[750];
	private int[] snakeylength=new int[750];

	private boolean left=false;
	private boolean right=false;
	private boolean up=false;
	private boolean down=false;

	private ImageIcon rightmouth;
	private ImageIcon leftmouth;
	private ImageIcon downmouth;
	private ImageIcon upmouth;

	private int length=3;

	private Timer timer;
	private int delay=100;
	private ImageIcon snakeimage;

	private int fruitxpos[]={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
	private int fruitypos[]={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};

	private ImageIcon fruitimage;

	private Random random=new Random();

	private int xpos=random.nextInt(34); //total number of x positions horizontally
	private int ypos=random.nextInt(23);

	private int score=0;
	private int moves=0;

	public Gameplay()
	{
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer=new Timer(delay,this);
		timer.start();
	}

	public void paint(Graphics g)
	{

		if(moves==0) //when games starts
		{
			snakexlength[2]=50; //initial indices of snake body
			snakexlength[1]=75;
			snakexlength[0]=100; //initial index of head

			snakeylength[2]=100;
			snakeylength[1]=100;
			snakeylength[0]=100;
		}
		g.setColor(Color.white); // title border
		g.drawRect(24,10,851,55); 

		titleImage=new ImageIcon("snaketitle.jpg"); //draw title
		titleImage.paintIcon(this,g,25,11);

		g.setColor(Color.WHITE); //game border
		g.drawRect(24,74,851,557);

		g.setColor(Color.black); //gameplay background
		g.fillRect(25,75,850,575);

		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN,14));//display score
		g.drawString("Score : "+score,780,30);

		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN,14));//display length
		g.drawString("Length : "+length,780,50);

		rightmouth = new ImageIcon("rightmouth.png"); //draw snake initially
		rightmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);

		for(int a=0;a<length;a++) //traversal of snake
		{
			if(a==0&&right)
			{
				rightmouth = new ImageIcon("rightmouth.png"); 
				rightmouth.paintIcon(this,g,snakexlength[a],snakeylength[a]);
			}
			if(a==0&&left)
			{
				leftmouth = new ImageIcon("leftmouth.png"); 
				leftmouth.paintIcon(this,g,snakexlength[a],snakeylength[a]);
			}
			if(a==0&&down)
			{
				downmouth = new ImageIcon("downmouth.png"); 
				downmouth.paintIcon(this,g,snakexlength[a],snakeylength[a]);
			}
			if(a==0&&up)
			{
				upmouth = new ImageIcon("upmouth.png"); 
				upmouth.paintIcon(this,g,snakexlength[a],snakeylength[a]);
			}

			if(a!=0) //body part, a is face of snake when =0
			{
				snakeimage = new ImageIcon("snakeimage.png"); 
				snakeimage.paintIcon(this,g,snakexlength[a],snakeylength[a]);
			}
		}

		fruitimage=new ImageIcon("enemy.png");

		if(fruitxpos[xpos]==snakexlength[0]&&fruitypos[ypos]==snakeylength[0])//if location of fruit is location of head
		{
			score+=5;
			length++;
			xpos=random.nextInt(34);
			ypos=random.nextInt(23);
		}

		fruitimage.paintIcon(this,g,fruitxpos[xpos],fruitypos[ypos]);

		for(int b=1;b<length;b++)
		{
			if(snakexlength[b]==snakexlength[0]&&snakeylength[b]==snakeylength[0])
			{
				right=false;
				left=false;
				down=false;
				up=false;

				g.setColor(Color.white);
				g.setFont(new Font("arial",Font.BOLD,50));
				g.drawString("Game Over",300,300);

				g.setFont(new Font("arial",Font.BOLD,20));
				g.drawString("Hit SPACE to replay",350,340);
				

			}
		}

		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		timer.start();
		if(right)
		{
			for(int r=length-1;r>=0;r--) //traversal of body follows head
			{
				snakeylength[r+1]=snakeylength[r];
			}
			for(int r=length;r>=0;r--)
			{
				if(r==0)
				{
					snakexlength[r]=snakexlength[r]+25;
				}
				else
				{
					snakexlength[r]=snakexlength[r-1];
				}
				if(snakexlength[r]>850)
				{
					snakexlength[r]=25;
				}
			}
			repaint();
		}
		if(left)
		{
			for(int r=length-1;r>=0;r--) //traversal of body follows head
			{
				snakeylength[r+1]=snakeylength[r];
			}
			for(int r=length;r>=0;r--)
			{
				if(r==0)
				{
					snakexlength[r]=snakexlength[r]-25;
				}
				else
				{
					snakexlength[r]=snakexlength[r-1];
				}
				if(snakexlength[r]<25)
				{
					snakexlength[r]=850;
				}
			}
			repaint();
		}
		if(down)
		{
			for(int r=length-1;r>=0;r--) //traversal of body follows head
			{
				snakexlength[r+1]=snakexlength[r];
			}
			for(int r=length;r>=0;r--)
			{
				if(r==0)
				{
					snakeylength[r]=snakeylength[r]+25;
				}
				else
				{
					snakeylength[r]=snakeylength[r-1];
				}
				if(snakeylength[r]>625)
				{
					snakeylength[r]=75;
				}
			}
			repaint();
		}
		if(up)
		{
			for(int r=length-1;r>=0;r--) //traversal of body follows head
			{
				snakexlength[r+1]=snakexlength[r];
			}
			for(int r=length;r>=0;r--)
			{
				if(r==0)
				{
					snakeylength[r]=snakeylength[r]-25;
				}
				else
				{
					snakeylength[r]=snakeylength[r-1];
				}
				if(snakeylength[r]<75)
				{
					snakeylength[r]=625;
				}
			}
			repaint();
		}

	}

	@Override
	public void keyTyped(KeyEvent e)
	{

	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_SPACE)
		{
			moves=0;
			score=0;
			length=3;
			repaint();
		}

		if(e.getKeyCode()==KeyEvent.VK_RIGHT) //if right arrow is pressed
		{
			moves++;
			right=true;
			if(!left) 
			{
				right=true;
			}
			else // if snake is moving towards left initially, it cannot turn to right
			{
				right=false;
				left=true;
			}
			down=false;
			up=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT) //left arrow key
		{
			moves++;
			left=true;
			if(!right) 
			{
				left=true;
			}
			else 
			{
				left=false;
				right=true;
			}
			down=false;
			up=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP) //up arrow key
		{
			moves++;
			up=true;
			if(!down) 
			{
				up=true;
			}
			else 
			{
				up=false;
				down=true;
			}
			left=false;
			right=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN) //down arrow key
		{
			moves++;
			down=true;
			if(!up) 
			{
				down=true;
			}
			else 
			{
				down=false;
				up=true;
			}
			left=false;
			right=false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{

	}
}