package project;

import java.awt.Graphics;
import java.io.ObjectInputStream.GetField;
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
	public void solveCollisions(Graphics g)
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
						
						float dist = Vector2.distance(p1, p2);
						float overlap = ((CircleFixture)fixture1).getRadius() + ((CircleFixture)fixture2).getRadius();
						if(dist < overlap)
						{
							Vector2 deltaP = Vector2.delta(p1, p2);
//							collision.getElement1().position = 
//									Vector2.add(collision.getElement1().position,
//											Vector2.multiply(deltaP.normalize(), dist / overlap));
//							
//							collision.getElement2().position = 
//									Vector2.add(collision.getElement2().position,
//											Vector2.multiply(deltaP.normalize(), -dist / overlap));
							collision.getElement1().addForce(Vector2.multiply(deltaP.normalize(), (overlap / dist) * 1f/1000f));
							collision.getElement2().addForce(Vector2.multiply(deltaP.normalize(), -(overlap / dist) * 1f/1000f));
						}
					}
					else if(fixture1 instanceof CircleFixture && fixture2 instanceof PolygonFixture)
					{
						circlePolyCollision(g, collision.getElement1(), collision.getElement2(), (CircleFixture)fixture1, (PolygonFixture)fixture2);
					}
					else if(fixture1 instanceof PolygonFixture && fixture2 instanceof CircleFixture)
					{
						circlePolyCollision(g, collision.getElement2(), collision.getElement1(), (CircleFixture)fixture2, (PolygonFixture)fixture1);
					}
				}
			}
		}
	}
	private void circlePolyCollision(Graphics g, PhysicsObject circleObject, PhysicsObject polyObject, 
			CircleFixture circleFixture, PolygonFixture polyFixture)
	{
		Vector2[] verts = polyFixture.getVerices();
		for(int i = 0; i < verts.length; i++)
		{
			//COMPAIR POINT
			Vector2 p1p2 = Vector2.delta(verts[1], verts[0]);
			g.drawLine((int)verts[0].x, (int)verts[0].y, (int)(verts[1].x), (int)(verts[1].y));
			Vector2 p1p0 = Vector2.delta(circleObject.position, Vector2.add(verts[0], polyObject.position));
			g.drawLine((int)circleObject.position.x, (int)circleObject.position.y, 
					(int)(verts[0].x + polyObject.position.x), (int)(verts[0].y + polyObject.position.y));
			Vector2 vertPos = new Vector2(verts[0].x + polyObject.position.x, verts[0].y + polyObject.position.y);
			Vector2 Cprojected = Vector2.add(Vector2.project(p1p0, p1p2), vertPos);
			g.drawLine((int)circleObject.position.x, (int)circleObject.position.y, 
					(int)(Cprojected.x), (int)(Cprojected.y));
			
			//float dist = Vector2.distance(Vector2.add(Cprojected, verts[0]) , circleObject.position);
			float dist = Vector2.distance(Cprojected, circleObject.position);
			System.out.println(dist);
			
			
			//FORCE DIR
			if(dist < circleFixture.getRadius())
			{
				circleObject.addForce(Vector2.multiply(Cprojected.leftNormal().normalize(), 1f/1000f));
			}
		}
	}
}







