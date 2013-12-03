package project;

public class Vector2 
{
	float x, y;
	final static Vector2 zero = new Vector2();
	public Vector2()
	{
		x = 0;
		y = 0;
	}
	public Vector2(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	public float distance(Vector2 v1, Vector2 v2)
	{
		return (float) Math.sqrt(v1.x * v2.x + v1.y * v2.y);
	}
	public void Translate(Vector2 deltaVector)
	{
		x += deltaVector.x;
		y += deltaVector.y;
	}
}
