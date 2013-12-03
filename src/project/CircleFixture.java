package project;

public class CircleFixture extends PhysicsFixture 
{
	private float radius;
	public CircleFixture(float r)
	{
		radius = r;
	}
	float getRadius()
	{
		return radius;
	}
}
