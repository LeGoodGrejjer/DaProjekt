package project;

public class Vector2 
{
	float x, y;
	//final static Vector2 zero = new Vector2();
	
	public Vector2()
	{
		this.x = 0f;
		this.y = 0f;
	}
	public Vector2(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	public static float distance(Vector2 v1, Vector2 v2)
	{
		return (float)Math.sqrt((v1.x - v2.x) * (v1.x - v2.x) + (v1.y - v2.y) * (v1.y - v2.y));
	}
	void translate(Vector2 deltaVector)
	{
		this.x += deltaVector.x;
		this.y += deltaVector.y;
	}
	static final Vector2 zero()
	{
		return new Vector2();
	}
	public static float dot(Vector2 v1, Vector2 v2)
	{
		return v1.x*v2.x + v1.y*v2.y;
	}
	public static Vector2 add(Vector2 v1, Vector2 v2)
	{
		return new Vector2(v1.x + v2.x, v1.y + v2.y);
	}
}
