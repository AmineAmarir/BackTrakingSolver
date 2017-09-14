package com.amamarir.BackTrakingSolver;

import com.amamarir.BackTrakingSolver.BT.Variable;

public class VariableDemo implements Variable<VariableDemo, Integer> {

	private Integer valeur;
	
	public Integer get() {
		return valeur;
	}

	public void set(Integer t) {
		this.valeur = t;
	}

	public boolean isEmpty() {
		return this.valeur == null || this.valeur == 0;
	}

	public void setEmpty() {
		this.valeur = null;
	}

	public Variable<VariableDemo, Integer> clone() {
		VariableDemo clone = new VariableDemo();
		clone.valeur = this.valeur;
		return clone;
	}

	@Override
	public String toString() {
		return "" + valeur + "";
	}

	@Override
	public boolean equals(Object obj) {
		VariableDemo o = (VariableDemo) obj;
		return o.valeur.equals(this.valeur);
	}
	
}
