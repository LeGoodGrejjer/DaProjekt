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
		Vector2 p = Vector2.middle(verts, localPosition);
		for(int i = 1; i < verts.length; i++)
		{
			g.drawLine((int)verts[i - 1].x + (int)position.x, (int)verts[i - 1].y + (int)position.y, 
					(int)verts[i].x + (int)position.x, (int)verts[i].y + (int)position.y);
			
			g.drawLine((int)verts[i - 1].x + (int)position.x, (int)verts[i - 1].y + (int)position.y, 
					(int)p.x + (int)position.x, (int)p.y + (int)position.y);
		}
		g.drawLine((int)verts[0].x + (int)position.x, (int)verts[0].y + (int)position.y, 
				(int)verts[verts.length-1].x + (int)position.x, (int)verts[verts.length-1].y + (int)position.x);
		
		g.drawLine((int)verts[verts.length-1].x + (int)position.x, (int)verts[verts.length-1].y + (int)position.y, 
				(int)p.x + (int)position.x, (int)p.y + (int)position.y);
	}
	public AABB generateAABB() 
	{
		Vector2 p = Vector2.middle(verts, localPosition);
		Vector2 size = Vector2.AABBSize(verts);
		System.out.println(p.x);
		return new AABB(p, size);
	}
	public Vector2 getLocalPosition() 
	{
		return localPosition;
	}
}
