/*
 * CSC 221 - Assignment 2
 * The Tortoise and the Hare Race
 * by Klaudio Vito
 * 9/23/2015
 */
package assignment_2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.SecureRandom;
//implementation of the race with GUI
@SuppressWarnings("serial")
public class Race extends JPanel {
	Image hare; //initiate image of hare
	Image tortoise;//initiate image of tortoise
	Image finish; //initiate image of finish line
	Image background; //initiate image of the background
	
	ImageIcon fns = new ImageIcon(getClass().getClassLoader().getResource("images/finish.jpg"));//tell compiler where to look for image
	ImageIcon bckGround = new ImageIcon(getClass().getClassLoader().getResource("images/background.jpg"));//tell compiler where to look for image
	ImageIcon hare_img_icon = new ImageIcon(getClass().getClassLoader().getResource("images/hare.jpg"));//tell compiler where to look for image
	ImageIcon tortoise_img_icon = new ImageIcon(getClass().getClassLoader().getResource("images/tortoise.jpg"));//tell compiler where to look for image
	
	public static Hare currentHare = new Hare(); //create a currentHare object from class Hare
	public static Tortoise currentTortoise = new Tortoise();//create a currentTortoise object from class Tortoise
	
	SecureRandom randomNumbers = new SecureRandom(); //create randomNumbers from class SecureRandom
	
	//divide screen into 70 equal squares
	int squareX = getHeight()/70; 
	int squareY = getWidth()/70;
	
	public Race (){//class constructor
		
		finish = fns.getImage();//get finish image from directory
		background = bckGround.getImage();//get background image from directory
		hare = hare_img_icon.getImage();//get hare image from directory
		tortoise = tortoise_img_icon.getImage();//get tortoise image from directory
		
		prepareImage(finish,this); //make image usable for GUI
		prepareImage(background,this);//make image usable for GUI
		prepareImage(hare,this);//make image usable for GUI
		prepareImage(tortoise,this);//make image usable for GUI
		
		currentHare.setX(0); //set initial X position of Hare
		currentHare.setY(120-getHeight());//set initial Y position of Hare
		currentTortoise.setX(0);//set initial X position of Tortoise
		currentTortoise.setY(250 - getHeight());//set initial Y position of Tortoise
		
		JButton startApp = new JButton ("Start");
		startApp.addActionListener(new Start());
		add(startApp);
		
		//the following key listener uses the ENTER key from the keyboard to start the app
		startApp.addKeyListener(new KeyAdapter(){
	          public void keyPressed(KeyEvent e) {
	              if(e.getKeyCode()==KeyEvent.VK_ENTER){
	                  startApp.doClick();
	              }
	          }
	      });
				
		JButton restartApp = new JButton ("Reset Position");// initialization of restartApp JButton
		restartApp.addActionListener(new Restart());// tell the compiler to wait for click then call the Restart() function
		add(restartApp);//add the button to the windows
	
		JButton quitApp = new JButton ("Quit"); //create a quitApp button from JButton class
		quitApp.addActionListener(new Quit()); //add an action to quitApp from class Quit which is defined later
		add(quitApp); //add quitApp button to window
		
		setDoubleBuffered(true); //set default buffer size
	}
	
	class Start implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JLabel boom = new JLabel ("BOOM!! And they're off");
			boom.setBounds(500, 490, 11400, 30);
			boom.setFont(new Font("Algerian", Font.PLAIN, 30));
			boom.setForeground(Color.blue);
			add(boom);
			Timer timer = new Timer(4000, new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){	
					remove(boom);
				}
			}); timer.start();
			new Timer(1000, paintTimer).start(); //set timing for class paintTimer, 1 move / second
			JLabel hAct = new JLabel("<= Hare Activities");
			JLabel tAct = new JLabel("<= Tortoise Activities");
		
			
			hAct.setBounds(230, 5, 200, 25);
			tAct.setBounds(230, 490 , 200, 25);
			add(hAct);
			add(tAct);
			
		}
	}
	
	static class Quit implements ActionListener{ //Action Listener to quit app
		public void actionPerformed(ActionEvent e){
			System.exit(0);//exit if clicked
		}
	}
	
	static class Restart implements ActionListener{//Action Listener to restart the app
		public void actionPerformed(ActionEvent e){
			//if clicked
			currentHare.setX(0); //set X position of Hare to beginning of the race 
			currentHare.setY(120);//set Y position of Hare to beginning of the race
			currentTortoise.setX(0);//set X position of Tortoise to beginning of the race
			currentTortoise.setY(250);//set Y position of Tortoise to beginning of the race
		}
	}
	
	public void paint (Graphics g){ //class to draw the images in 2D
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;//initialize g2d graphics
		g2d.drawImage(background, 0, 35, getWidth(), getHeight()-70, this);//set background to be as large as the window
		g2d.drawImage(finish, getWidth()-50, getHeight()-135 , this);//the finish line should be displayed before the edge of the window
		g2d.drawImage(hare, currentHare.getX(), currentHare.getY(), this);//draw hare with corresponding position
		g2d.drawImage(tortoise, currentTortoise.getX(), currentTortoise.getY(), this);//draw tortoise with corresponding position
		g.dispose();
	}
	Action paintTimer = new AbstractAction(){//class used to performed timed actions
		public void actionPerformed(ActionEvent e){
			JLabel ouch = new JLabel("The Tortoise bit the Hare... OUCH!");//initiate "auch" label from JLabel to be displayed later
			JFormattedTextField tFastPlod = new JFormattedTextField ("Tortoise performed fast plod");//initiate "tFastPlod" label from JLabel to be displayed later
			JFormattedTextField tSlip = new JFormattedTextField("Oh no, the Tortoise slipped");//initiate "tSlip" label from JLabel to be displayed later
			JFormattedTextField tSlowPlod = new JFormattedTextField("The Tortoise had a slow plod");//initiate "tSlowPlod" label from JLabel to be displayed later
			JFormattedTextField hSleep = new JFormattedTextField("Sleeping Hare, Zzz...");//initiate "hSleep" label from JLabel to be displayed later
			JFormattedTextField hBigHop = new JFormattedTextField ("Big Hop by the Hare");//initiate "hBigHop" label from JLabel to be displayed later
			JFormattedTextField hBigSlip = new JFormattedTextField ("Hare slipped big, HAH!");//initiate "hBigSlip" label from JLabel to be displayed later
			JFormattedTextField hSmallHop = new JFormattedTextField ("The Hare just made a small hop");//initiate "hSmalleHop" label from JLabel to be displayed later
			JFormattedTextField hSmallSlip = new JFormattedTextField ("Small slip by the Hare");//initiate "hSmallSlip" label from JLabel to be displayed later
			
			ouch.setBounds(700, 5, 400, 30); //set position of "auch" label
			ouch.setFont(new Font("Arial", Font.PLAIN, 20));
			ouch.setForeground(Color.red);
			tFastPlod.setBounds(20, 490, 200, 25);//set position of "tFastPlod" label
			tFastPlod.setForeground(Color.MAGENTA);
			tSlip.setBounds(20, 490, 200, 25);//set position of "tSlip" label
			tSlip.setForeground(Color.MAGENTA);
			tSlowPlod.setBounds(20, 490, 200, 25);//set position of "tSlowPlod" label
			tSlowPlod.setForeground(Color.MAGENTA);
			hSleep.setBounds(20, 5, 200, 25);//set position of "hSleep" label
			hSleep.setForeground(Color.GREEN);
			hBigHop.setBounds(20, 5, 200, 25);//set position of "hBigHop" label
			hBigHop.setForeground(Color.GREEN);
			hBigSlip.setBounds(20, 5, 200, 25);//set position of "hBigSlip" label
			hBigSlip.setForeground(Color.GREEN);
			hSmallHop.setBounds(20, 5, 200, 25);//set position of "hSmallHop" label
			hSmallHop.setForeground(Color.GREEN);
			hSmallSlip.setBounds(20, 5, 200, 25);//set position of "hSmallSlip" label
			hSmallSlip.setForeground(Color.GREEN);
			
			int hareMove = 1 + randomNumbers.nextInt(10); //set moves of hare on the 1-10 range of random numbers
			int tortoiseMove = 1 + randomNumbers.nextInt(10);//set moves of tortoise on the 1-10 range of random numbers
			
			//the following 2 if-statements are used to stop the images from sliding out of the screen
			if(currentHare.getX() < 0)
				currentHare.setX(0);
			if(currentTortoise.getX() < 0)
				currentTortoise.setX(0);
			
			//the following if-statement displays the "auch" label if the hare and the tortoise are on the same line
			//and even though the initial position is the same square the "auch" label will not be displayed there
			if(currentTortoise.getX() != 0 && currentHare.getX() == currentTortoise.getX()){
				add(ouch);
		
				Timer timer = new Timer(3000, new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){	
						remove(ouch);
					}
				}); timer.start();
			}
			
			//the following block is used to determine the winner when the race is over
			if(currentHare.getX()+100 >= getWidth() || currentTortoise.getX()+100 >= getWidth()){
				if(currentHare.getX() < currentTortoise.getX()){
					JOptionPane.showMessageDialog(null, "Finished! TORTOISE WINS!!! YAY!!");
				}
				else if (currentHare.getX() > currentTortoise.getX()){
					JOptionPane.showMessageDialog(null, "Finished! Hare wins. Yuch!");
				}
					else if (currentHare.getX() == currentTortoise.getX())
					JOptionPane.showMessageDialog(null, "It's a tie!");
				System.exit(0);
			}
			
			//the following if-else statements are used to move the tortoise according to the table of moves 
			//and display  corresponding race activity
			if(tortoiseMove >= 1 && tortoiseMove <= 5){
				currentTortoise.setY(currentTortoise.getY() + squareY*3);
				currentTortoise.setX(currentTortoise.getX() + 15);
				add(tFastPlod);
				Timer timer = new Timer(400, new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){	
						remove(tFastPlod);
					}
				}); timer.start();
			}	
			else if (tortoiseMove >= 6 && tortoiseMove <= 7){
				currentTortoise.setY(currentTortoise.getY() - squareY*6);
				currentTortoise.setX(currentTortoise.getX() - 15);
				add(tSlip);
				Timer timer = new Timer(400, new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){	
						remove(tSlip);
					}
				});
				timer.start();
			}
			else if (tortoiseMove >= 8 && tortoiseMove <= 10){
				currentTortoise.setY(currentTortoise.getY() + squareY);
				currentTortoise.setX(currentTortoise.getX() + 15);
				add(tSlowPlod);
				Timer timer = new Timer(400, new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){	
						remove(tSlowPlod);
					}
				});
				timer.start();
			}
			
			//the following if-else statements are used to move the hare according to the table of moves
			//and display  corresponding race activity
			if(hareMove >= 1 && hareMove <= 2 ){
				currentHare.setX(currentHare.getX());
				currentHare.setY(currentHare.getY());
				add(hSleep);
				Timer timer = new Timer(400, new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){	
						remove(hSleep);
					}
				});
				timer.start();
			}
			else if(hareMove >= 3 && hareMove <= 4){
				currentHare.setX(currentHare.getX() + 25);
				currentHare.setY(currentHare.getY() + squareY*9);
				add(hBigHop);
				Timer timer = new Timer(400, new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){	
						remove(hBigHop);
					}
				});
				timer.start();
			}
			else if(hareMove == 5){
				currentHare.setX(currentHare.getX() - 25);
				currentHare.setY(currentHare.getY() - squareY*12) ;
				add(hBigSlip);
				Timer timer = new Timer(400, new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){	
						remove(hBigSlip);
					}
				});
				timer.start();
			}
			else if (hareMove >= 6 && hareMove <= 8){
				currentHare.setX(currentHare.getX() + 25);
				currentHare.setY(currentHare.getY() + squareY);
				add(hSmallHop);
				Timer timer = new Timer(400, new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){	
						remove(hSmallHop);
					}
				});
				timer.start();
			}
			else if (hareMove >= 9 && hareMove <= 10){
				currentHare.setX(currentHare.getX() - 15);
				currentHare.setY(currentHare.getY() - squareY*2);
				add(hSmallSlip);
				Timer timer = new Timer(400, new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){	
						remove(hSmallSlip);
					}
				});
				timer.start();
			}
			
		repaint();//repeat all of the above
		
		}
	};	
}