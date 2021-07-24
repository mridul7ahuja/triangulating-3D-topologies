
public class Triangle implements TriangleInterface {
	Point P1;
	Point P2;
	Point P3;
	Edge e1;
	Edge e2;
	Edge e3;
	Edge[] triangleEdgeArr = new Edge[3];
	Point[] trianglePointArr = new Point[3];
	public Triangle(Point P1, Point P2, Point P3, int insertnum,Edge e1, Edge e2, Edge e3) {
		this.P1 = P1;
		this.P2 = P2;
		this.P3 = P3;
		this.insert_num = insertnum;
		this.e1 = e1;
		this.e2 = e2;
		this.e3 = e3;
		trianglePointArr[0] = this.P1;
		trianglePointArr[1] = this.P2;
		trianglePointArr[2] = this.P3;
		triangleEdgeArr[0] = this.e1;
		triangleEdgeArr[1] = this.e2;
		triangleEdgeArr[2] = this.e3;
	}
	public Triangle(Point P1, Point P2, Point P3) {
		this.P1 = P1;
		this.P2 = P2;
		this.P3 = P3;
	}
	
	
	
	
	public int insert_num;

	@Override
	public PointInterface[] triangle_coord() {
		Point[] pointArr = new Point[3];
		pointArr[0] = P1;
		pointArr[1] = P2;
		pointArr[2] = P3;
		return pointArr;
	}
	
	public String toString() {
		Point[] pointArr = new Point[] {P1,P2,P3};
		for(int i=0; i<2; i++) {
			int min = i;
			for(int j=i+1; j<3; j++) {
				if(pointArr[j].compareTo(pointArr[min])<0) 
					min = j;
				 }
				Point temp = pointArr[min];
				pointArr[min] = pointArr[i];
				pointArr[i] = temp;
			}
		
		return pointArr[0].toString() + " " + pointArr[1].toString() + " " + pointArr[2].toString();
		}
		
	}
