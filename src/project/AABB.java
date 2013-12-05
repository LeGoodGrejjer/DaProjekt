package project;

import java.awt.Graphics;

public class AABB
{
	private Vector2 localPosition;
	private Vector2 position;
	private Vector2 size;
	public AABB()
	{
		position = Vector2.zero();
		localPosition = Vector2.zero();
		size = new Vector2(1f, 1f);
	}
	public AABB(Vector2 position, Vector2 size)
	{
		//this.localPosition = Vector2.multiply(position, 2f);
		this.localPosition = position;
		this.position = Vector2.zero();
		this.size = size;
	}
	public AABB(Vector2 size)
	{
		this.size = size;
		position = Vector2.zero();
		this.localPosition = Vector2.zero();
	}
	public boolean colliding(AABB other)
	{
		if(this.position.x - this.size.x / 2f < other.position.x + other.size.x / 2f && this.position.x + this.size.x / 2f > other.position.x - other.size.x / 2f)
		{
			if(this.position.y - this.size.y / 2f < other.position.y + other.size.y / 2f && this.position.y + this.size.y / 2f > other.position.y - other.size.y / 2f)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	public void setPosition(Vector2 position)
	{
		this.position = Vector2.add(this.localPosition, position);
	}
	public void debugDraw(Graphics g)
	{
		//g.drawRect((int)position.x - (int)size.x / 2, (int)position.y - (int)size.y / 2, (int)size.x, (int)size.y);
	}
	public AABB combineAABB()
	{
		//TODO kunna slå ihop ett gäng AABB för att kunna göra en stor runt trianglar
		return null;
	}
}
