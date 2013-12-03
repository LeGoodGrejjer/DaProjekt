package project;

public class Pair<E>
{
	private E fixture1;
	private E fixture2;
	public Pair(E fixture1, E fixture2)
	{
		this.fixture1 = fixture1;
		this.fixture2 = fixture2;
	}
	public E getElement1()
	{
		return this.fixture1;
	}
	public E getElement2()
	{
		return this.fixture2;
	}
	@SuppressWarnings("unchecked")
	public boolean equals(Object obj)
	{
		if(this.fixture1.equals(((Pair<E>)obj).getElement1()) && this.fixture2.equals(((Pair<E>)obj).getElement2()) ||
				this.fixture1.equals(((Pair<E>)obj).getElement2()) && this.fixture2.equals(((Pair<E>)obj).getElement1()))
			return true;
		return false;
	}
	public int hashCode()
	{
		return 0;
	}
}
