package ai;

import java.util.ArrayList;

import main.Panel;

public class PathFinder {
	
	Panel panel;
	Node[][] node;
	ArrayList<Node> openList = new ArrayList<>();
	public ArrayList<Node> pathList = new ArrayList<>();
	Node startNode, goalNode, currentNode;
	boolean goalReached = false;
	int step = 0;
	
	public PathFinder(Panel panel) {
		
		this.panel = panel;
		instantiateNode();
	}
	
	public void instantiateNode() {
		
		node = new Node[panel.maxWorldCol][panel.maxWorldRow];
		
		int col = 0;
		int row = 0;
		
		while(col < panel.maxWorldCol && row < panel.maxWorldRow) {
			
			node[col][row] = new Node(col, row);
			col++;
			
			if (col == panel.maxWorldCol) {
				col = 0;
				row++;
			}
		}
	}
	
	public void resetNodes() {
		
		int col = 0;
		int row = 0;
		
		while (col < panel.maxWorldCol && row < panel.maxWorldRow) {
			
			node[col][row].open = false;
			node[col][row].checked = false;
			node[col][row].solid = false;
			
			col++;
			if (col == panel.maxWorldCol) {
				col = 0;
				row++;
			}
		}
		
		openList.clear();
		pathList.clear();
		goalReached = false;
		step = 0;
	}
	
	public void setNode(int startCol, int startRow, int goalCol, int goalRow) {
		
		resetNodes();
		
		//set start and goal node
		startNode = node[startCol][startRow];
		currentNode = startNode;
		goalNode = node[goalCol][goalRow];
		openList.add(currentNode);
		
		int col = 0;
		int row = 0;
		
		while (col < panel.maxWorldCol && row < panel.maxWorldRow) {
			
			//set solid node
			//check tiles
			int tileNum = panel.tm.mapTileNum[panel.currentMap][col][row];
			if(panel.tm.tile[tileNum].collision == true) {
				node[col][row].solid = true;
			}
			
			//check interactive tiles
			for (int i = 0; i < panel.iTile[1].length; i++) {
				if (panel.iTile[panel.currentMap][i] != null && panel.iTile[panel.currentMap][i].destructible == true) {
					int iCol = panel.iTile[panel.currentMap][i].worldX/panel.tileSize;
					int iRow = panel.iTile[panel.currentMap][i].worldY/panel.tileSize;
					node[iCol][iRow].solid = true;
				}
			}
			
			//set cost
			getCost(node[col][row]);
			
			col++;
			if (col == panel.maxWorldCol) {
				col = 0;
				row++;
			}	
		}	
	}

	public void getCost(Node node) {
		
		//g cost
		int xDistance = Math.abs(node.col - startNode.col);
		int yDistance = Math.abs(node.row - startNode.row);
		node.gCost = xDistance + yDistance;
		//h cost
		xDistance = Math.abs(node.col - goalNode.col);
		yDistance = Math.abs(node.row - goalNode.row);
		node.hCost = xDistance + yDistance;
		//f cost
		node.fCost = node.gCost + node.hCost;
	}
	
	public boolean search() {
		
		while (goalReached == false && step < 500) {
			
			int col = currentNode.col;
			int row = currentNode.row;
			
			//check the current node
			currentNode.checked = true;
			openList.remove(currentNode);
			
			//open the up node
			if (row - 1 >= 0) {
				openNode(node[col][row-1]);
			}
			//open the left node
			if (col - 1 >= 0) {
				openNode(node[col-1][row]);
			}
			//open the down node
			if (row + 1 < panel.maxWorldRow) {
				openNode(node[col][row+1]);
			}
			//open the down node
			if (col + 1 < panel.maxWorldCol) {
				openNode(node[col+1][row]);
			}
			
			//find the best node
			int bestNodeIndex = 0;
			int bestNodefCost = 999;
			
			for (int i = 0; i < openList.size(); i++) {
				
				//check if f cost is better
				if (openList.get(i).fCost < bestNodefCost) {
					bestNodeIndex = i;
					bestNodefCost = openList.get(i).fCost;
				}
				
				//if f cost is equal, check if g cost is better
				else if (openList.get(i).fCost == bestNodefCost) {
					if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
						bestNodeIndex = i;
					}
				}
			}
			
			// if there is no node in the openList, end the loop
			if (openList.size() == 0) {
				break;
			}
			
			//after the loop, openList[bestNodeIndex] is the next step
			currentNode = openList.get(bestNodeIndex);
			
			if (currentNode == goalNode) {
				goalReached = true;
				trackThePath();
			}
			step++;
		}
		
		return goalReached;
	}
	
	public void openNode(Node node) {
		
		if (node.open == false && node.checked == false && node.solid == false) {
			node.open = true;
			node.parent = currentNode;
			openList.add(node);
		}
	}
	
	public void trackThePath() {
		
		Node current = goalNode;
		
		while(current != startNode) {
			
			pathList.add(0,current);
			current = current.parent;
		}
		
	}
 
}
