package com.amamarir.BackTrakingSolver;

import java.util.List;

import com.amamarir.BackTrakingSolver.BT.Constraints;
import com.amamarir.BackTrakingSolver.BT.Variable;

public class ConstraintsDemo implements Constraints<List<Variable<VariableDemo, Integer>>, VariableDemo, Integer> {

	public boolean test(List<Variable<VariableDemo, Integer>> t) {
		int[][] table = new int[9][9];
		int j = 0; 
		for (int i = 1; i <= 9; i++) {
			table[j][i - 1] = t.get(j * 9 + i - 1).isEmpty() ? 0 : t.get(j * 9 + i - 1).get();
			if (i == 9 && j != 8) {
				i = 0;
				j++;
			}
		}
		boolean test = false;
		for (int i = 0; i < 9 && !test; i++) {
			for (int k = 0; k < 9 && !test; k++) {
				if (check(table, table[i][k], i, k) || checkCage(table, table[i][k], i, k)) {
					test = true;
				}

			}
		}
		
		return !test;
	}

	private boolean check(int[][] sudoku, int n, int ligne, int colonne) {
		if (n == 0)
			return false;
		boolean exist = false;

		for (int x = 0; x < 9 && !exist; x++) {
			if (sudoku[x][colonne] == n && x != ligne)
				exist = true;
		}
		for (int y = 0; y < 9 && !exist; y++) {
			if (sudoku[ligne][y] == n && y != colonne)
				exist = true;
		}
		return exist;
	}

	private boolean checkCage(int[][] sudoku, int n, int ligne, int colonne) {
		if (n == 0)
			return false;
		boolean enCage = false;
		int cageX = ((ligne / 3) * 3);
		int cageY = ((colonne / 3) * 3);

		for (int y = 0; y < 3 && !enCage; y++)
			for (int x = 0; x < 3 && !enCage; x++)
				if (sudoku[cageX + x][cageY + y] == n && cageX + x != ligne && cageY + y != colonne)
					enCage = true;

		return enCage;
	}

}
