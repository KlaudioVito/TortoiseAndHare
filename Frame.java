/*
 * CSC 221 - Assignment 2
 * The Tortoise and the Hare Race
 * by Klaudio Vito
 * 9/23/2015
 */
package assignment_2;
import javax.swing.*;
//main frame from where the classes are going to be called
@SuppressWarnings("serial")
public class Frame extends JFrame {
	Race mPanel;
	Frame(){
		setTitle("The Tortoise and the Hare");
		mPanel = new Race();
		setSize(1050,560);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(mPanel);
		setVisible(true);
	}
	public static void main(String[] args){
		new Frame();
	}
}
