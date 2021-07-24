
public class Edge implements EdgeInterface {
	Point P1;
	Point P2;
	Point[] pointArr = new Point[2];
	public Edge(Point P1, Point P2) {
		this.P1 = P1;
		this.P2 = P2;
		pointArr[0] = this.P1;
		pointArr[1] = this.P2;
	}
	

	@Override
	public PointInterface[] edgeEndPoints() {
		Point[] pointArr = new Point[2];
		pointArr[0] = P1;
		pointArr[1] = P2;
		return pointArr;
	} 
	
	public String toString() {
		if(P1.compareTo(P2)<=0) {
			return P1.toString() + " " + P2.toString();
		}
		else {
			return P2.toString() + " " + P1.toString();
		}
	}
	public float length() {
		return ((P1.x - P2.x)*(P1.x - P2.x) + (P1.y - P2.y)*(P1.y - P2.y) + (P1.z - P2.z)*(P1.z - P2.z));
	}
	
	public ArrayListAss<Triangle> trianglesInc = new ArrayListAss<Triangle>();

}
