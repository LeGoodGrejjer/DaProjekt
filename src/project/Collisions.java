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
							Vector2 normalN = Vector2.multiply(deltaP.normalize(), (collision.getElement1().bounce + collision.getElement2().bounce) / 2f);
							Vector2 oldVel = Vector2.delta(collision.getElement1().getVelocity(), collision.getElement2().getVelocity());
							Vector2 newVel = Vector2.delta(oldVel, Vector2.multiply(normalN, (2f*Vector2.dot(oldVel, normalN))));
							collision.getElement1().setVelocity(newVel);
							collision.getElement2().setVelocity(Vector2.multiply(newVel, -1f));
							collision.getElement1().position.translate(
											Vector2.multiply(deltaP.normalize(), dist / overlap));
							
							collision.getElement2().position.translate(
											Vector2.multiply(deltaP.normalize(), -dist / overlap));
							//collision.getElement1().addForce(Vector2.multiply(deltaP.normalize(), (overlap / dist) * 1f/10f));
							//collision.getElement2().addForce(Vector2.multiply(deltaP.normalize(), -(overlap / dist) * 1f/10f));
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
		for(int i = 1; i <= verts.length; i++)
		{
			int first = i - 1;
			int second = i;
			if(i == verts.length)
				second = 0;
			//COMPAIR POINT
			Vector2 p1p2 = Vector2.delta(verts[second], verts[first]);
//			g.drawLine((int)verts[first].x, (int)verts[first].y, (int)(verts[second].x), (int)(verts[second].y));
			Vector2 p1p0 = Vector2.delta(circleObject.position, Vector2.add(verts[first], polyObject.position));
//			g.drawLine((int)circleObject.position.x, (int)circleObject.position.y, 
//					(int)(verts[first].x + polyObject.position.x), (int)(verts[first].y + polyObject.position.y));
			Vector2 vertPos = new Vector2(verts[first].x + polyObject.position.x, verts[first].y + polyObject.position.y);
			Vector2 Cprojected = Vector2.add(Vector2.project(p1p0, p1p2), vertPos);
//			g.drawLine((int)circleObject.position.x, (int)circleObject.position.y, 
//					(int)(Cprojected.x), (int)(Cprojected.y));
			
			float dist = Vector2.distance(Cprojected, circleObject.position);
			
			
			Vector2 middle = Vector2.divide(Vector2.add(Vector2.add(verts[second], polyObject.position), 
					Vector2.add(verts[first], polyObject.position)), 2f);
			float distMP = Vector2.distance(middle, Cprojected);
			float distB = Vector2.distance(verts[second], verts[first]) / 2f;
			
			//if(i == 1)
			
			//FORCE DIR
			if(distMP < distB)
			{
				System.out.println(distMP);
				if(dist < circleFixture.getRadius())
				{
					//circleObject.addForce(Vector2.multiply(p1p2.leftNormal().normalize(), (circleFixture.getRadius() / dist) * 1f/100f));
					//Vector2 newVel = circleObject.getVelocity();
					//r = d-2(d dot n)n
					//if(circleObject.getVelocity())
					Vector2 normalN = Vector2.multiply(p1p2.leftNormal().normalize(), (circleObject.bounce + polyObject.bounce) / 2f);
					Vector2 newVel = Vector2.delta(circleObject.getVelocity(), Vector2.multiply(normalN, (2f*Vector2.dot(circleObject.getVelocity(), normalN))));
					circleObject.setVelocity(newVel);
					circleObject.position.translate(Vector2.multiply(p1p2.leftNormal().normalize(), (circleFixture.getRadius() - dist)));
				}
				break;
			}
			else
			{
				Vector2 p1 = Vector2.add(circleObject.position, circleFixture.getLocalPosition());
				Vector2 p2 = Vector2.add(verts[first], polyObject.position);
				
				dist = Vector2.distance(p1, p2);
				if(dist < circleFixture.getRadius())
				{
					Vector2 deltaP = Vector2.delta(p1, p2);
					Vector2 normalN = Vector2.multiply(deltaP.normalize(), (circleObject.bounce + polyObject.bounce) / 2f);
					Vector2 newVel = Vector2.delta(circleObject.getVelocity(), Vector2.multiply(normalN, (2f*Vector2.dot(circleObject.getVelocity(), normalN))));
					circleObject.setVelocity(newVel);
					circleObject.position.translate(Vector2.multiply(deltaP.normalize(), dist / circleFixture.getRadius()));
					break;
				}
			}
		}
	}
}







