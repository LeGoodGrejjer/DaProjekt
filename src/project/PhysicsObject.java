package project;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

public class PhysicsObject 
{
	Vector2 position;
	private AABB aabb;
	String name;
	private Set<PhysicsFixture> fixtures = new HashSet<PhysicsFixture>();
	public AABB getAABB()
	{
		return aabb;
	}
	public PhysicsObject(PhysicsFixture fixture, Vector2 position)
	{
		fixtures.add(fixture);
		aabb = fixture.generateAABB();
		aabb.setPosition(position);
		this.position = position;
	}
	void update()
	{
		aabb.setPosition(position);
	}
	void debugDraw(Graphics g)
	{
		for(PhysicsFixture fixture : fixtures)
		{
			fixture.debugDraw(g, position);
			aabb.debugDraw(g);
		}
		
	}
}
