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
	public static Vector2 multiply(Vector2 v1, Vector2 v2)
	{
		return new Vector2(v1.x * v2.x, v1.y * v2.y);
	}
	public static Vector2 multiply(Vector2 v1, float v2)
	{
		return new Vector2(v1.x * v2, v1.y * v2);
	}
	public static Vector2 project(Vector2 a, Vector2 b)
	{
		float dp = Vector2.dot(a, b);
		float x = (dp / (b.x * b.x + b.y * b.y)) * b.x;
		float y = (dp / (b.x * b.x + b.y * b.y)) * b.y;
		return new Vector2(x, y);
	}
	public Vector2 normalize()
	{
		float x = this.x, y = this.y;
		float len_v = (float)Math.sqrt(x * x + y * y);
		x /= len_v;
		y /= len_v;
		return new Vector2(x, y);
	}
	public static Vector2 delta(Vector2 v1, Vector2 v2)
	{
		return new Vector2(v1.x - v2.x, v1.y - v2.y);
	}
}
