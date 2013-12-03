package project;

import java.util.HashSet;
import java.util.Set;

public class World
{
	Set<PhysicsObject> physicsObjects = new HashSet<PhysicsObject>();
	void World()
	{
		
	}
	void update()
	{
		for(PhysicsObject physObj : physicsObjects)
		{
			physObj.update();
		}
	}
	void addObject(PhysicsObject obj)
	{
		physicsObjects.add(obj);
	}
}