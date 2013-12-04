package project;

import java.awt.Graphics;

public class PolygonFixture extends PhysicsFixture
{
	private Vector2[] verts;
	private Vector2 localPosition;
	public PolygonFixture(Vector2[] verts)
	{
		this.verts = verts;
		localPosition = Vector2.zero();
	}
	public Vector2[] getVerices()
	{
		return verts;
	}
	public void debugDraw(Graphics g, Vector2 position) 
	{
		for(int i = 1; i < verts.length; i++)
		{
			g.drawLine((int)verts[i - 1].x, (int)verts[i - 1].y, (int)verts[i].x, (int)verts[i].y);
		}
		
	}
	public AABB generateAABB() 
	{
		return new AABB();
	}
	public Vector2 getLocalPosition() 
	{
		return localPosition;
	}
}
