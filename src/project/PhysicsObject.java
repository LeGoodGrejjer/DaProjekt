package project;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

public class PhysicsObject 
{
	boolean isRigid = true;
	float drag = 0.995f;
	Vector2 position;
	private Vector2 velocity;
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
		velocity = Vector2.zero();
	}
	void update()
	{
		aabb.setPosition(position);
		position = Vector2.add(position, velocity);
		velocity = Vector2.multiply(velocity, drag);
	}
	void debugDraw(Graphics g)
	{
		for(PhysicsFixture fixture : fixtures)
		{
			fixture.debugDraw(g, position);
			aabb.debugDraw(g);
		}
		
	}
	public Set<PhysicsFixture> getFixtures()
	{
		return fixtures;
	}
	public void addForce(Vector2 force)
	{
		if(isRigid)
		velocity = Vector2.add(velocity, force);
	}
}
