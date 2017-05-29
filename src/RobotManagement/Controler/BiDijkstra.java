package RobotManagement.Controler;
import RobotManagement.Model.*;

//Algorithme de Dijkstra bidirectionnel
public class BiDijkstra {
	public final Graph g; // le graphe de travail
	public final int n; // nombre de sommets de g
	public final int source; // source du plus court chemin recherche
	public final int dest; // destination du plus court chemin recherche
	//Fenetre f; // fenetre pour la visualisation
	public final Dijkstra forward; // recherche de plus courts chemins depuis la source
	public final Dijkstra backward; // recherche de plus courts chemins depuis la destination
	public boolean side; // sens de la prochaine iteration
	public int last; // sommet traite par la derniere iteration
	public int iSP, jSP; // indices pour le chemin particulier 

	// constructeur
	public BiDijkstra(Graph g, int source, int dest) {
		this.g = g;
		n = g.n;
		this.source = source;
		this.dest = dest;
		forward = new Dijkstra(g, source, dest);
		backward = new Dijkstra(g.reverse(), dest, source);
		side = true;
		last = source;
		iSP = -1;
		jSP = -1;
	}

	// test de terminaison
	boolean isOver() {
		return (side ? forward.settled[last] : backward.settled[last]);
	}

	// changer side
	void flip() {
		side = !side;
	}

	// une iteration de Dijkstra bidirectionnel
	void oneStep() {
		last = (side ? forward.oneStep() : backward.oneStep());
		flip();
	}

	
	// chemins indirects possibles
	void computeIJ() {
		int best = forward.dist[last] + backward.dist[last];
		for (Node nodeI : backward.unsettled) {
			int i = nodeI.id;
			if (forward.settled[i]) {
				int j = backward.pred[i];
				if (j!= -1 && backward.settled[j] && forward.dist[i] + g.weight(i,j) + backward.dist[j] < best) {
					best = forward.dist[i] + g.weight(i,j) + backward.dist[j];
					iSP = i;
					jSP = j;
				}
			}
		}
	}

	// renvoyer la longueur du plus cours chemin
	int getMinPath() {
		if (iSP != -1) return forward.dist[iSP] + g.weight(iSP,jSP) + backward.dist[jSP];
		else return forward.dist[last] + backward.dist[last];
	}

	// algorithme de Dijkstra bidirectionnel
	int compute() {
		while (!isOver())
			oneStep();
		computeIJ();
		return getMinPath();
	}
}