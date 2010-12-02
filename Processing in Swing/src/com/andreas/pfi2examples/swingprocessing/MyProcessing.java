package com.andreas.pfi2examples.swingprocessing;

import java.util.ArrayList;

import processing.core.PApplet;

public class MyProcessing extends PApplet {

	/* List of MyProcessingClass items */
	private ArrayList<MyProcessingClass> items;

	/* Temporary item */
	private MyProcessingClass temp;
	private boolean paintTemp = false;

	@Override
	public void setup() {
		/* Enable smooth rendering */
		smooth();

		/* Create the list of MyProcessingClass items */
		items = new ArrayList<MyProcessingClass>();
	}

	@Override
	public void draw() {
		/* Repaint the background */
		background(0);

		/*
		 * Draw all the items in the list. Reveal the synchronized block will
		 * protect the access for the list and make it safe.
		 */
		// synchronized (items) {
		for (MyProcessingClass item : items)
			item.display();
		// }

		/* Paint the temporary object if we haven't released it yet */
		if (paintTemp)
			temp.display2();
	}

	public void mousePressed() {
		/* Create a new temporary item */
		temp = new MyProcessingClass(this, mouseX, mouseY, random(15, 25));
		/* Start painting the temporary item */
		paintTemp = true;
	}

	public void mouseDragged() {
		temp.setNewPosition(mouseX, mouseY);

		/* Set the color, green for OK, red for NOT OK */
		boolean ok = true;
		for (MyProcessingClass item : items) {
			if (item.overlapping(temp)) {
				ok = false;
				break;
			}
		}
		if (ok)
			temp.setOk(true);
		else
			temp.setOk(false);
	}

	public void mouseReleased() {
		/*
		 * We use a boolean to track if we've not overlapping any other item, if
		 * we're not overlapping we add the item to the list
		 */
		boolean okToAdd = true;
		for (MyProcessingClass item : items) {
			if (item.overlapping(temp)) {
				okToAdd = false;
				break;
			}
		}

		/* If okToAdd is still true, we can add the item to the list */
		if (okToAdd)
			items.add(temp);

		/* Stop painting the temporary item */
		paintTemp = false;
	}

	/**
	 * Clear the list of items.
	 */
	public void clearList() {
		/* Reveal the synchronized block to make the access safe. */
		// synchronized (items) {
		items.clear();
		items.trimToSize();
		// }
	}
}
