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
		
		//Left NORMALS
		for(int i = 1; i < verts.length; i++)
		{
			Vector2 line = Vector2.add(Vector2.add(verts[i], position), Vector2.add(verts[i-1], position));
			Vector2 halfLine = Vector2.divide(line , 2f);
			Vector2 line2Normal = Vector2.delta(Vector2.add(verts[i], position), Vector2.add(verts[i-1], position));
			Vector2 normal = line2Normal.leftNormal();
			
			//System.out.println(normal.y);
			
			g.drawLine((int)halfLine.x, (int)halfLine.y,
					(int)halfLine.x + (int)(normal.x * 15f), (int)halfLine.y + (int)(normal.y * 15f));
		}
		Vector2 line = Vector2.add(Vector2.add(verts[0], position), Vector2.add(verts[verts.length-1], position));
		Vector2 halfLine = Vector2.divide(line , 2f);
		Vector2 line2Normal = Vector2.delta(Vector2.add(verts[0], position), Vector2.add(verts[verts.length-1], position));
		Vector2 normal = line2Normal.leftNormal();
		
		//System.out.println(normal.y);
		
		g.drawLine((int)halfLine.x, (int)halfLine.y,
				(int)halfLine.x + (int)(normal.x * 15f), (int)halfLine.y + (int)(normal.y * 15f));
		
	}
	public AABB generateAABB() 
	{
		Vector2 size = Vector2.AABBSize(verts);
		Vector2 p = Vector2.middle(verts, localPosition);
		//System.out.println(p.x);
		return new AABB(p, size);
	}
	public Vector2 getLocalPosition() 
	{
		return localPosition;
	}
}
