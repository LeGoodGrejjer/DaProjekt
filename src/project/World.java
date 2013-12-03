package project;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class World
{
	Set<PhysicsObject> physicsObjects;// = new HashSet<PhysicsObject>();
	private Iterator<PhysicsObject> it;
	private PhysicsObject prev;
	private PhysicsObject current;
	public World()
	{
		physicsObjects = new HashSet<PhysicsObject>();
	}
	void update()
	{
		it = physicsObjects.iterator();
		while(it.hasNext())
		{
			prev = it.next();
			
			if(it.hasNext())
				current = it.next();
			else 
				break;
			if(current.getAABB().colliding(prev.getAABB()))
			{
				System.out.println(current.toString() + "      COLLIDING AABB WITH     " + prev.toString());
			}
		}
		it = physicsObjects.iterator();
		while(it.hasNext())
		{
			it.next().update();
		}
	}
	void addObject(PhysicsObject obj)
	{
		this.physicsObjects.add(obj);
	}
	void debugDraw(Graphics g)
	{
		for(PhysicsObject physObj : physicsObjects)
		{
			physObj.debugDraw(g);
		}
	}
}