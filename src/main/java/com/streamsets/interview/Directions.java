package com.streamsets.interview;

//Esta clase seria una lista enlazada doble circular con los puntos cardinales.
class Directions{

	private class Node {
		char direction;
		int xmove;
		int ymove;
		Node right;
		Node left;
		
		Node(char c, int xmove, int ymove){
			this.direction=c;
			this.xmove = xmove;
			this.ymove = ymove;
		}
	}

	private Node origin;
	
	public Directions(){
		origin = null;
		addNodo('W',-1,0);
		addNodo('S',0,-1);
		addNodo('E',1,0);
		addNodo('N',0,1);
	}

	
	private void addNodo(char cp, int xmove, int ymove) {
		Node aux = new Node(cp, xmove, ymove);
		if(origin==null) {
			aux.right=aux;
			aux.left=aux;
		}else {
			aux.right = origin;
			aux.left = origin.left;
			origin.left.right = aux;
			origin.left = aux;
		}
		origin = aux;
	}
	
	//El origen de la lista marcaria el punto cardinal actual del robot.
	public void setInicialDirection(char cp) {
		if(origin.direction==cp) return;
		Node aux = origin;
		while(aux.right.direction!=cp) {
			aux=aux.right;
		}
		origin = aux.right;
	}
	
	public char changeOrientation(char spin) {
		if(spin=='R' || spin=='r')
			origin = origin.right;
		else
			origin = origin.left;
		return origin.direction;
	}

	public char getCurrentDirection() {
		return origin.direction;
	}

	public int getXMove() {
		return origin.xmove;
	}

	public int getYMove() {
		return origin.ymove;
	}
}
