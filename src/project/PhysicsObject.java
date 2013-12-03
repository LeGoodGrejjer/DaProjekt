package project;

import java.util.HashSet;
import java.util.Set;

public class PhysicsObject 
{
	private AABB aabb;
	private Set<PhysicsFixture> fixtures = new HashSet<PhysicsFixture>();
	void PhysicsObject(PhysicsFixture fixture)
	{
		fixtures.add(fixture);
		aabb = generateAABB();
	}
	AABB generateAABB()
	{
		return new AABB();
	}
	void update()
	{
		aabb.collision(new AABB());
	}
}
