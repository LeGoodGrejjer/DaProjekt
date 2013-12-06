package project;

import java.awt.Graphics;

public abstract class PhysicsFixture 
{
	public abstract void debugDraw(Graphics g, Vector2 position);
	public abstract AABB generateAABB();
	public abstract Vector2 getLocalPosition();
}
