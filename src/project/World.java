package project;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class World
{
	Set<PhysicsObject> physicsObjects = new HashSet<PhysicsObject>();
	private Iterator<PhysicsObject> it;
	private PhysicsObject prev;
	public World()
	{
		
	}
	void update()
	{
		it = physicsObjects.iterator();
		while(it.hasNext())
		{
			prev = it.next();
			
			if(it.hasNext() && it.next().getAABB().colliding(prev.getAABB()))
			{
				
			}
		}
	}
	void addObject(PhysicsObject obj)
	{
		physicsObjects.add(obj);
	}
}