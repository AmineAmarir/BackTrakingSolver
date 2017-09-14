package com.amamarir.BackTrakingSolver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.amamarir.BackTrakingSolver.BT.BackTraking;
import com.amamarir.BackTrakingSolver.BT.Variable;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		final List<Variable<VariableDemo, Integer>> variables = initVariables();
		final List<DomainDemo> domains = initDomains(variables);
		
		final BackTraking<VariableDemo, DomainDemo, ConstraintsDemo, List<Variable<VariableDemo, Integer>>, Integer> bt = new BackTraking<VariableDemo, DomainDemo, ConstraintsDemo, List<Variable<VariableDemo, Integer>>, Integer>(
				variables, domains, new ConstraintsDemo());

		bt.execute();

	}

	private static List<Variable<VariableDemo, Integer>> initVariables() {

		final List<Variable<VariableDemo, Integer>> out = new ArrayList<Variable<VariableDemo, Integer>>();
		VariableDemo var = null;
		Scanner sc = null;
		try {
			sc = new Scanner(new File("sudoku_example.ini"));
			int i = 0;
			while (i < 81) {
				var = new VariableDemo();
				int value = sc.nextInt();
				if(value == 0){
					var.setEmpty();
				}else{
					var.set(value);
				}
				out.add(var);
				i ++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
		return out;
	}

	private static List<DomainDemo> initDomains(List<Variable<VariableDemo, Integer>> variables) {
		final List<DomainDemo> out = new ArrayList<DomainDemo>();
		DomainDemo domain = null;
		for(Variable<VariableDemo, Integer> variable : variables){
			domain = new DomainDemo();
			if(variable.isEmpty()){
				for(int i = 1; i <= 9; i++){
					domain.add(i);
				}
			}
			out.add(domain);
		}
		return out;
	}
}
