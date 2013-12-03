package project;

import java.awt.Graphics;

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
	public void debugDraw(Graphics g, Vector2 position)
	{
		g.drawOval((int)position.x - (int)radius, (int)position.y - (int)radius, (int)radius * 2, (int)radius * 2);
	}

	public AABB generateAABB() 
	{
		return new AABB(new Vector2(radius * 2, radius * 2));
	}
}
