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
	public void solveCollisions()
	{
		Iterator<Pair<PhysicsObject>> it = collisions.iterator();
		while(it.hasNext())
		{
			Pair<PhysicsObject> collision = it.next();
			for(PhysicsFixture fixture1 : collision.getElement1().getFixtures())
			{
				for(PhysicsFixture fixture2 : collision.getElement2().getFixtures())
				{
					if(fixture1 instanceof CircleFixture && fixture2 instanceof CircleFixture)
					{
						Vector2 p1 = Vector2.add(collision.getElement1().position, fixture1.getLocalPosition());
						Vector2 p2 = Vector2.add(collision.getElement2().position, fixture2.getLocalPosition());
						Vector2 deltaP = Vector2.delta(p1, p2);
						//System.out.println(deltaP.x);
						//System.out.println(deltaP.y);
						float dist = Vector2.distance(p1, p2);
						float overlap = ((CircleFixture)fixture1).getRadius() + ((CircleFixture)fixture2).getRadius();
						if(dist < overlap)
						{
							
//							collision.getElement1().position = 
//									Vector2.add(collision.getElement1().position,
//											Vector2.multiply(deltaP.normalize(), dist / overlap));
//							
//							collision.getElement2().position = 
//									Vector2.add(collision.getElement2().position,
//											Vector2.multiply(deltaP.normalize(), -dist / overlap));
							collision.getElement1().addForce(Vector2.multiply(deltaP.normalize(), (overlap / dist) * 1f/600f));
							collision.getElement2().addForce(Vector2.multiply(deltaP.normalize(), -(overlap / dist) * 1f/600f));
						}
					}
				}
			}
		}
	}
}







