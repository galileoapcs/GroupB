package Tetris;

import info.gridworld.actor.*;
import info.gridworld.grid.*;

import java.util.ArrayList;
import java.awt.Color;

public class TetrisBlock_Z extends TetrisBlock{


		/**
		 * value of the current rotation position {0,1,2 or 3}
		 */
		
		protected int rotationPos;
		/**
		 * blocks will have three TetrisBug objects in it... they will be added in the
		 * constructor
		 */
		protected ArrayList<TetrisBug> blocks;
		/**
		 * used as a convenient reference to the Grid
		 */
		protected Grid<Actor> gr;

		/**
		 * default constructor
		 */
		public TetrisBlock_Z() {
			
			super();
			gr = super.gr;
			rotationPos = super.rotationPos;
			blocks = super.blocks;

			// ==> LAMEST GAME OVER EVER !!! <==
			// if the Grid does not have room for the TetrisBlock.. GameOver
			if (gr.get(new Location(0, 6)) != null
					|| gr.get(new Location(1, 4)) != null)
					 {
				javax.swing.JOptionPane.showMessageDialog(null, "Score: "
						+ TetrisGame.score, "GAME OVER!", 0);
				System.exit(0);
			}
			TetrisBug a;
			TetrisBug b;
			
			// create TetrisBugs for ArrayList blocks and put them in Grid gr
			a = new TetrisBug(Color.BLUE);
			b = new TetrisBug(Color.BLUE);
			a.putSelfInGrid(gr, new Location(0, 6));
			b.putSelfInGrid(gr, new Location(1, 4));
			blocks.add(a);
			blocks.add(b);

			// TetrisBlock subclasses will add two more TetrisBug objects to blocks

		}

		/**
		 * TetrisBlock and its TetrisBugs must face down (direction 180) If they can
		 * move down, they will. Otherwise, it will ask TetrisGame to create a new
		 * TetrisBlock since this one is stuck at the bottom.
		 */
		public void act() {
			setDirection(180);
			for (TetrisBug tb : blocks){
				tb.setDirection(180);
			}
				
			if (canMoveDown())	{		
				moveDown();
			} else if (!TetrisGame.currentBlock.canMoveDown()) {
				TetrisGame.nextTetrisBlock();
			}
		}

		/**
		 * Move the TetrisBlock and its TetrisBugs one cell. (they should already be
		 * facing down) Note: The order in which all the TetrisBugs move is important
		 * and depends on the current rotationPos.
		 */
		public void moveDown() {
			if (rotationPos==0){
				move();
				blocks.get(2).move();
				blocks.get(1).move();
				blocks.get(0).move();
			}
			if (rotationPos==1){
				move();
				blocks.get(2).move();
				blocks.get(1).move();
				blocks.get(0).move();
			}
			
		}

		/**
		 * Returns true if the TetrisBlock and its TetrisBugs can move (they should
		 * already be facing down) Otherwise, returns false.
		 */
		public boolean canMoveDown() {
			if (rotationPos==0){
				return blocks.get(2).canMove() && canMove() && blocks.get(1).canMove();
			}
			if (rotationPos == 1){
				return canMove() && blocks.get(1).canMove();
			}
			else {
				return true;
			}
		}

		/**
		 * Sets the direction of the TetrisBlock and its TetrisBugs to 90 (right) If
		 * they can move, they will move one cell (to the right)
		 */
		public void moveRight() {
			setDirection(90);
			for (TetrisBug tb : blocks){
				tb.setDirection(90);
			}
			if (rotationPos==0){
			if (blocks.get(1).canMove() && canMove()) {
				blocks.get(1).move();
				move();
				blocks.get(0).move();
				blocks.get(2).move();
				
			}
			} 
			if (rotationPos == 1){
				if (blocks.get(2).canMove() && blocks.get(0).canMove() && blocks.get(1).canMove()){
					blocks.get(2).move();
					blocks.get(1).move();
					blocks.get(0).move();
					move();
				}
			}
		}

		/**
		 * Sets the direction of the TetrisBlock and its TetrisBugs to 90 (right) If
		 * they can move, they will move one cell (to the right)
		 */
		public void moveLeft() {
			setDirection(-90);
			for (TetrisBug tb : blocks){
				tb.setDirection(-90);
			}
			if (rotationPos==0){
			if (blocks.get(0).canMove() && blocks.get(2).canMove()) {
				blocks.get(2).move();
				move();
				blocks.get(0).move();
				blocks.get(1).move();
			}
			} 
			if (rotationPos == 1){
				if (canMove() && blocks.get(1).canMove() && blocks.get(2).canMove()){
					blocks.get(2).move();					
					move();
					blocks.get(1).move();
					blocks.get(0).move();
				}
			}

		}

		/**
		 * If the TetrisBlock and its TetrisBugs can rotate, then they will all move
		 * to their proper location for the given rotation designated by
		 * rotationPos... Update rotationPos.
		 */
		public void rotate() {
			Location nextLoc0;
			Location nextLoc1;
			Location nextLoc2;
			if (rotationPos == 0) {
				// only one block must move
				nextLoc1 = new Location(blocks.get(1).getLocation().getRow() + 2,
						blocks.get(1).getLocation().getCol());
				if (gr.isValid(nextLoc1) && gr.get(nextLoc1) == null) {
					blocks.get(1).moveTo(nextLoc1);
				}
				nextLoc0 = new Location(blocks.get(0).getLocation().getRow() + 1,
						blocks.get(0).getLocation().getCol() + 1);
				if (gr.isValid(nextLoc0) && gr.get(nextLoc0) == null) {
					blocks.get(0).moveTo(nextLoc0);
				}
				nextLoc2 = new Location(blocks.get(2).getLocation().getRow() - 1,
						blocks.get(2).getLocation().getCol() + 1);
				if (gr.isValid(nextLoc2) && gr.get(nextLoc2) == null) {
					blocks.get(2).moveTo(nextLoc2);
				}
				rotationPos +=1;
			} else if (rotationPos == 1) {
				nextLoc2 = new Location(blocks.get(2).getLocation().getRow() + 1,
						blocks.get(2).getLocation().getCol() - 1);
				if (gr.isValid(nextLoc2) && gr.get(nextLoc2) == null) {
					blocks.get(2).moveTo(nextLoc2);
				}
				nextLoc0 = new Location(blocks.get(0).getLocation().getRow() - 1,
						blocks.get(0).getLocation().getCol() - 1);
				if (gr.isValid(nextLoc0) && gr.get(nextLoc0) == null) {
					blocks.get(0).moveTo(nextLoc0);
				}
				nextLoc1 = new Location(blocks.get(1).getLocation().getRow() - 2,
						blocks.get(1).getLocation().getCol());
				if (gr.isValid(nextLoc1) && gr.get(nextLoc1) == null) {
					blocks.get(1).moveTo(nextLoc1);
				}
				rotationPos -= 1;
				}

		}

	}

