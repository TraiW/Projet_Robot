package RobotManagement.Model;


@SuppressWarnings("rawtypes")

// Noeud d'un graphe
public class Node implements Comparable {

  public final int id; // identifiant
  public final int val; // valeur

  // constructeur
  public Node(int i, int v){
    id = i;
    val = v;
  }

  // fonction de comparaison
  public int compareTo(Object o1) {
    Node n = (Node) o1;
    if (this.val == n.val) return (this.id - n.id);
    return this.val - n.val;
  }
}