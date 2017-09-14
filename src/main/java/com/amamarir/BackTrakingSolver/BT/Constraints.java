package com.amamarir.BackTrakingSolver.BT;

import java.util.List;
import java.util.function.Predicate;

public interface Constraints<L extends List<Variable<V, T>>, V extends Variable<V, T>, T> extends Predicate<L> {
	
}
