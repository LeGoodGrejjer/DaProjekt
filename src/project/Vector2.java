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
		float a = this.x, b = this.y;
		float len_v = (float)Math.sqrt(a * a + b * b);
		a /= len_v;
		b /= len_v;
		return new Vector2(a, b);
	}
	public static Vector2 delta(Vector2 v1, Vector2 v2)
	{
		return new Vector2(v1.x - v2.x, v1.y - v2.y);
	}
	public static Vector2 absDelta(Vector2 v1, Vector2 v2)
	{
		return new Vector2(Math.abs(v1.x - v2.x), Math.abs(v1.y - v2.y));
	}
	public static Vector2 divide(Vector2 v1, Vector2 v2)
	{
		return new Vector2(v1.x / v2.x, v1.y / v2.y);
	}
	public static Vector2 divide(Vector2 v1, float v2)
	{
		return new Vector2(v1.x / v2, v1.y / v2);
	}
	public static Vector2 middle(Vector2[] vectors, Vector2 localPosition)
	{
		//Vector2 sum = localPosition;
		//sum = Vector2.add(sum, vectors[i]);
		//System.out.println(vectors.length);
		Vector2 largest = vectors[0];
		Vector2 smallest = vectors[0];
		for(int i = 0; i < vectors.length; i++)
		{
			if(vectors[i].x > largest.x)
			{
				largest = new Vector2(vectors[i].x, largest.y);
			}
			if(vectors[i].y > largest.y)
			{
				largest = new Vector2(largest.x, vectors[i].y);
			}
			
			if(vectors[i].x < smallest.x)
			{
				smallest = new Vector2(vectors[i].x, smallest.y);
			}
			if(vectors[i].y < smallest.y)
			{
				smallest = new Vector2(smallest.x, vectors[i].y);
			}
		}
		return new Vector2((largest.x + smallest.x) / 2f + localPosition.x, (largest.y + smallest.y) / 2f + localPosition.y);
	}
	public static Vector2 AABBSize(Vector2[] vectors)
	{
		Vector2 largest = vectors[0];
		Vector2 smallest = vectors[0];
		for(int i = 0; i < vectors.length; i++)
		{
			if(vectors[i].x > largest.x)
			{
				largest = new Vector2(vectors[i].x, largest.y);
			}
			if(vectors[i].y > largest.y)
			{
				largest = new Vector2(largest.x, vectors[i].y);
			}
			
			if(vectors[i].x < smallest.x)
			{
				smallest = new Vector2(vectors[i].x, smallest.y);
			}
			if(vectors[i].y < smallest.y)
			{
				smallest = new Vector2(smallest.x, vectors[i].y);
			}
			//System.out.println(smallest.x);
		}
		
		return Vector2.absDelta(largest, smallest);
	}
	public Vector2 leftNormal()
	{
		return (new Vector2(this.y, -this.x)).normalize();
	}
}
