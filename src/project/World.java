package project;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class World
{
	Set<PhysicsObject> physicsObjects;// = new HashSet<PhysicsObject>();
	private Iterator<PhysicsObject> it;
	private PhysicsObject current;
	Collisions AABBCollisions = new Collisions();
	
	public World()
	{
		physicsObjects = new HashSet<PhysicsObject>();
	}
	void update()
	{
		int i = 0;
		for(PhysicsObject prev : physicsObjects)
		{
			it = physicsObjects.iterator();
			while(it.hasNext())
			{
				current = it.next();
				if(prev.equals(current))
					continue;

				if(current.getAABB().colliding(prev.getAABB()))
				{
					AABBCollisions.add(new Pair<PhysicsObject>(current, prev));
					i++;
				}
			}
		}
		System.out.println(i + " collisions.");
		it = physicsObjects.iterator();
		while(it.hasNext())
		{
			it.next().update();
			AABBCollisions.removeEndedCollisions();
			//AABBCollisions.solveCollisions(g);
		}
		AABBCollisions.debugDraw();
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
		AABBCollisions.solveCollisions(g);
	}
}