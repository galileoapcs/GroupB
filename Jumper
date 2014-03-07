import info.gridworld.actor.Actor; 
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower; 
import info.gridworld.actor.Rock; 
import info.gridworld.grid.Grid; 
import info.gridworld.grid.Location; 
 
import java.awt.Color; 
 
/** 
 * A <code>Jumper</code> is an actor that will jump over Rocks and Flowers 
 * and turn. 
 */ 
public class Jumper extends Bug 
{ 

 public Jumper() 
 { 
 setColor(Color.BLUE); 
 } 
 
 
 public Jumper(Color color) 
 { 
 setColor(color); 
 } 
 
 public void act() 
 { 
 if (canJump()) 
 jump(); 
 else 
 turn(); 
 }  

 
 public void jump() 
 { 
 Grid<Actor> gr = getGrid(); 
 if (gr == null)
 return; 
 
 Location loc = getLocation(); 
 Location one = loc.getAdjacentLocation(getDirection()).getAdjacentLocation(getDirection()); 
 
 if (gr.isValid(one)) 
 moveTo(one); 
 else 
 removeSelfFromGrid(); 
 } 
 
 
 public boolean canJump() 
 { 
 Grid<Actor> gr = getGrid(); 
 if (gr == null) 
 return false; 
 
 Location loc = getLocation(); 
 Location one = loc.getAdjacentLocation(getDirection()); 
 Location two = one.getAdjacentLocation(getDirection()); 
 if (!gr.isValid(one) || !gr.isValid(two)) 
 return false; 
 
 Actor neighbor = gr.get(one); 
 Actor neighbor2 = gr.get(two); 
 if (neighbor instanceof Rock)
	 if (neighbor2 == null || neighbor instanceof Flower)
	 return true;
	 
	 else 
	 return false;
	 
 
 if (neighbor2 instanceof Rock)
 return false;
 
 else
 return true;
 
 }
}
