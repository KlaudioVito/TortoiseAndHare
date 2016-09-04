/*
 * CSC 221 - Assignment 2
 * The Tortoise and the Hare Race
 * by Klaudio Vito
 * 9/23/2015
 */
package assignment_2;
//Hare class to set and get hare's position
public class Hare {
	private int x,y;//position x and y
	Hare(){;}; //empty constructor, the initial position will be defined in the Race class
	public int getX(){ return x;} // get position X of Hare
	public void setX(int x2){ x = x2;}// set position X of Hare
	public int getY(){return y;}//get position Y of Hare
	public void setY(int y2){ y = y2;}//get position Y of Hare

}
