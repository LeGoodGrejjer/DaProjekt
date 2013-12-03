package project;

import java.awt.Graphics;

public class PolygonFixture extends PhysicsFixture
{
	private Vector2[] verts;
	public PolygonFixture(Vector2[] verts)
	{
		this.verts = verts;
	}
	public Vector2[] getVerices()
	{
		return verts;
	}
	public void debugDraw(Graphics g, Vector2 position) 
	{
		
	}
	public AABB generateAABB() 
	{
		return new AABB();
	}
}
