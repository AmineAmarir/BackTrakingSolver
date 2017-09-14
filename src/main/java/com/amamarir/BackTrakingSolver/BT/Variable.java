package com.amamarir.BackTrakingSolver.BT;

public interface Variable<V extends Variable<V, T>, T> extends Cloneable {

	public T get();
	public void set(T t);
	public boolean isEmpty();
	public void setEmpty();
	public Variable<V, T> clone();
}
