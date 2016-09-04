/*
 * CSC 221 - Assignment 2
 * The Tortoise and the Hare Race
 * by Klaudio Vito
 * 9/23/2015
 */
package assignment_2;
//Tortoise class used to set and get the position of the tortoise
public class Tortoise {
	private int x,y;//position x and y
	Tortoise(){;};//empty constructor, the initial position will be defined in the Race clas
	public int getX(){ return x;}// get position X of Tortoise
	public void setX(int x2){ x = x2;}// set position X of Tortoise
	public int getY(){return y;}//get position Y of Tortoise
	public void setY(int y2){ y = y2;}//get position Y of Tortoise
}
