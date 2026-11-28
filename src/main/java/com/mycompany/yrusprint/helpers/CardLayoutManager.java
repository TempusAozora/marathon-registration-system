package com.mycompany.yrusprint.helpers;

import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Component;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class CardLayoutManager {
	private JPanel panel;
	private CardLayout cardLayout;
	private Component defaultComponent;
	private Deque<String> stack = new ArrayDeque<>();

	private Map<String, Component> constraintsMap = new HashMap<>();
	private Map<Component, String> componentsMap = new HashMap<>();

	public CardLayoutManager(JPanel panel, CardLayout cardLayout) {
		this.panel = panel;
		this.cardLayout = cardLayout;
	}

	public void add(Component comp, String constraint) {
		this.panel.add(comp, constraint);
		if (componentsMap.isEmpty()) defaultComponent = comp;

		constraintsMap.put(constraint, comp);
		componentsMap.put(comp, constraint);
	}

	public void show(Component comp) {
		if (!componentsMap.containsKey(comp)) return;
		
		String constraintName = componentsMap.get(comp);
		this.stack.push(constraintName);
		this.cardLayout.show(panel, constraintName);
	}

	public void back() {
		if (this.stack.isEmpty()) return;
		
		this.stack.pop();
		if (!this.stack.isEmpty()) {
			this.cardLayout.show(panel, stack.peek());
		} else {
			this.cardLayout.show(panel, componentsMap.get(defaultComponent));
		}
	}

	public void reset() {
		stack = new ArrayDeque<>();
	}
}
