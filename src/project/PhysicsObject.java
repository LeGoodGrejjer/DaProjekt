package project;

import java.util.HashSet;
import java.util.Set;

public class PhysicsObject 
{
	Vector2 position;
	private AABB aabb;
	private Set<PhysicsFixture> fixtures = new HashSet<PhysicsFixture>();
	public AABB getAABB()
	{
		return aabb;
	}
	public PhysicsObject(PhysicsFixture fixture, Vector2 position)
	{
		fixtures.add(fixture);
		aabb = generateAABB();
		this.position = position;
	}
	private AABB generateAABB()
	{
		return new AABB();
	}
	void update(PhysicsFixture fixture)
	{
		aabb.setPosition(position);
	}
}
