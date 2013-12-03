package project;

public class AABB
{
	private Vector2 position;
	private Vector2 size;
	public AABB()
	{
		position = Vector2.zero;
		size = new Vector2(1f, 1f);
	}
	public AABB(Vector2 position, Vector2 size)
	{
		this.position = position;
		this.size = size;
	}
	public boolean colliding(AABB other)
	{
		if(this.position.x - this.size.x < other.position.x + other.size.x && this.position.x + this.size.x > other.position.x - other.size.x)
		{
			if(this.position.y - this.size.y < other.position.y + other.size.y && this.position.y + this.size.y > other.position.y - other.size.y)
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
		this.position = position;
	}
}
