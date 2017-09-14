package com.amamarir.BackTrakingSolver.BT;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BackTraking<V extends Variable<V, T>, D extends Domain<V, T>, C extends Constraints<L, V, T>, L extends List<Variable<V, T>>, T> {

	private L variables;
	private List<D> domains;
	private C constraints;
	private Set<L> solutions = new HashSet<L>();

	public BackTraking(L l, List<D> d, C c) {
		this.variables = l;
		this.domains = d;
		this.constraints = c;
	}

	public void execute() {
		long startTime = System.currentTimeMillis();
		System.out.println("-------- Début exécution ---------");
		compute(variables, 0);
		System.out.println("--------- Fin exécution ---------");
		System.out.println("==> Nombre de solutions : " + solutions.size());
		int i = 1;
		for(L element : solutions){
			System.out.println("-------- Début solution n°" + i + " ---------");
			print(element);
			System.out.println("-------- Fin solution n°" + i + " ---------");
			i++;
		}
		System.out.println("Temps d'execution : " + (System.currentTimeMillis() - startTime) + " ms");
	}

	private void compute(L variabs, int step) {
		while (step < variables.size()) {
			if (variables.get(step).isEmpty()) {
				for (int i = 0; i < domains.get(step).size(); i++) {
					variabs.get(step).set(domains.get(step).get(i));
					if (constraints.test(variabs)) {
						compute(variabs, step);
					} else {
						variabs.get(step).setEmpty();
						if(i + 1 == domains.get(step).size()){
							return;
						}
					}
				}
			} else
				step++;
		}
		if (checkCompleted(variabs)) {
			solutions.add(cloneAllVariables(variabs));
		}
	}

	private void print(L t) {
		System.out.println("---------------------------------------------");
		int[][] table = new int[9][9];
		int j = 0;
		for (int i = 0; i < 9; i++) {
			table[j][i] = (Integer) (t.get(j * 9 + i).isEmpty() ? 0 : t.get(j * 9 + i).get());
			if (i == 8 && j != 8) {
				i = -1;
				j++;
			}
		}
		for (int i = 0; i < 9; i++) {
			for (int k = 0; k < 9; k++) {
				System.out.print(table[i][k] + " ");
			}
			System.out.println();
		}
		System.out.println("---------------------------------------------");
	}

	private boolean checkCompleted(L variabs) {
		boolean complete = true;

		for (int i = 0; i < variabs.size() && complete; i++) {
			if (variabs.get(i).isEmpty())
				complete = false;
		}
		return complete;
	}
	
	private L cloneAllVariables(L variabs) {
		@SuppressWarnings("unchecked")
		L clone = (L) new ArrayList<Variable<V, T>>();
		for (Variable<V, T> v : variabs)
			clone.add(v.clone());
		return clone;
	}
}
