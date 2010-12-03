package mah.k3.pfi2.swingprocessing;

import processing.core.PApplet;

/**
 * Processing class, this one is just a simple blue Circle.
 * 
 * @author andreas
 * 
 */
public class MyProcessingClass {
	/*
	 * We need a reference to the processing sketch in which this class will be
	 * used
	 */
	private PApplet parent;

	/* Object variables */
	private float x;
	private float y;
	private float r;

	private boolean ok = true;

	/**
	 * Constructor, takes a reference to the PApplet as argument as well as a
	 * position.
	 * 
	 * @param parent
	 */
	public MyProcessingClass(PApplet parent, float x, float y, float r) {
		this.parent = parent;
		this.x = x;
		this.y = y;
		this.r = r;
	}

	/**
	 * Primary paint method
	 */
	public void display() {
		parent.pushMatrix();
		parent.fill(0, 0, 255);
		parent.ellipse(x, y, r * 2, r * 2);
		parent.popMatrix();
	}

	/**
	 * Secondary paint method, this paints the object transparent
	 */
	public void display2() {
		parent.pushMatrix();
		if (!ok)
			parent.fill(255, 0, 0, 100);
		else
			parent.fill(0, 255, 0, 100);
		parent.ellipse(x, y, r * 2, r * 2);
		parent.popMatrix();
	}

	public void setNewPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void setOk( boolean ok) {
		this.ok = ok;
	}

	/* A BUNCH OF SETTERS AND GETTERS used instead of accessing the variables directly as we perhaps would do in pure processing
	 * here the variables are private and cant be accessed form outside this object.
	 * */
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getR() {
		return r;
	}

	public void setR(float r) {
		this.r = r;
	}

	/**
	 * Tests the supplied MyProcessingClass item if it's over this circle, then
	 * it returns true or false depending on the result.
	 * 
	 * @param circle
	 * @return
	 */
	public boolean overlapping(MyProcessingClass circle) {
		/*
		 * distance between the two circles must be larger than the two radians
		 * added together
		 */
		if (PApplet.dist(circle.x, circle.y, x, y) < (circle.r + r)) {
			return true;
		}
		return false;
	}
}
