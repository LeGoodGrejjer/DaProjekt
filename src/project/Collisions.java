package project;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Collisions 
{
	Set<Pair<PhysicsObject>> collisions = new HashSet<Pair<PhysicsObject>>();
	public Collisions()
	{
		
	}
	public void add(Pair<PhysicsObject> collision)
	{
		collisions.add(collision);
	}
	public Set<Pair<PhysicsObject>> getCollisions()
	{
		return this.collisions;
	}
	public void removeEndedCollisions()
	{
		Iterator<Pair<PhysicsObject>> it = collisions.iterator();
		while(it.hasNext())
		{
			Pair<PhysicsObject> pair = it.next();
			if(!(pair.getElement1().getAABB().colliding(pair.getElement2().getAABB()) || 
					pair.getElement2().getAABB().colliding(pair.getElement1().getAABB())))
			{
				it.remove();
			}
		}
	}
	public void debugDraw()
	{
		for(Pair<PhysicsObject> pair : collisions)
		{
			System.out.println(pair.getElement1().name + " collidiong with " + pair.getElement2().name);
		}
		System.out.print('\n');
	}
}
