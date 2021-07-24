

 public class Shape implements ShapeInterface{
	 Trie<Triangle> triangleTrie = new Trie<Triangle>();
	 Trie<Edge> edgeTrie = new Trie<Edge>();
	 Trie<Point> pointTrie = new Trie<Point>();
	 ArrayListAss<Triangle> triangleArr = new ArrayListAss<Triangle>();
	 ArrayListAss<Point> pointArr = new ArrayListAss<Point>();
	 ArrayListAss<Edge> edgeArr = new ArrayListAss<Edge>();
	 int num_triangle=0;
	 int num_point =0;
	 public boolean ADD_TRIANGLE(float [] triangle_coord){
		Point P1 = new Point(triangle_coord[0], triangle_coord[1], triangle_coord[2]);
		Point P2 = new Point(triangle_coord[3], triangle_coord[4], triangle_coord[5]);
		Point P3 = new Point(triangle_coord[6], triangle_coord[7], triangle_coord[8]);
		float x1 = triangle_coord[0];
		float y1 = triangle_coord[1];
		float z1 = triangle_coord[2];
		float x2 = triangle_coord[3];
		float y2 = triangle_coord[4];
		float z2 = triangle_coord[5];
		float x3 = triangle_coord[6];
		float y3 = triangle_coord[7];
		float z3 = triangle_coord[8];
		float test1 = ((y1-y2)*(z1-z3) - (y1-y3)*(z1-z2));
		float test2 = ((x1-x2)*(z1-z3) - (x1-x3)*(z1-z2));
		float test3 = ((x1-x2)*(y1-y3) - (x1-x3)*(y1-y2));
		boolean bool = true; 
		if(Math.abs(test1)<0.0001f && Math.abs(test2)<0.0001f && Math.abs(test3)<0.0001f) {
			bool = false;
		}
		if(bool) {
			num_triangle++;
			Edge e1 = new Edge(P1,P2);
			Edge e2 = new Edge(P2,P3);
			Edge e3 = new Edge(P3,P1);
			Triangle t = new Triangle(P1,P2,P3,num_triangle,e1,e2,e3);
			boolean boolp1 = pointTrie.insert(P1.toString(), P1);
			boolean boolp2 = pointTrie.insert(P2.toString(), P2);
			boolean boolp3 = pointTrie.insert(P3.toString(), P3);
			boolean boole1 = edgeTrie.insert(e1.toString(), e1);
			boolean boole2 = edgeTrie.insert(e2.toString(), e2);
			boolean boole3 = edgeTrie.insert(e3.toString(), e3);
			boolean boolt = triangleTrie.insert(t.toString(), t);
			
			if(boolp1) {
				num_point++;
				P1.trianglesInc.add(t);
				pointArr.add(P1);
				P1.entry_num = num_point;
				t.trianglePointArr[0] = P1;
				t.P1 = P1;
			}
			else {
				P1 = pointTrie.search(P1.toString()).value;
				P1.trianglesInc.add(t);
				t.P1 = P1;
				t.trianglePointArr[0] = P1;
			} 
			
			if(boolp2) {
				num_point++;
				P2.trianglesInc.add(t);
				pointArr.add(P2);
				P2.entry_num = num_point;
				t.trianglePointArr[1] = P2;
				t.P2 = P2;
			}
			else {
				P2 = pointTrie.search(P2.toString()).value;
				P2.trianglesInc.add(t);
				t.P2 = P2;
				t.trianglePointArr[1] = P2;
			}
			
			if(boolp3) {
				num_point++;
				P3.trianglesInc.add(t);
				pointArr.add(P3);
				P3.entry_num = num_point;
				t.trianglePointArr[2] = P3;
				t.P3 = P3;
			}
			else {
				P3 = pointTrie.search(P3.toString()).value;
				P3.trianglesInc.add(t);
				t.P3 = P3;
				t.trianglePointArr[2] = P3;
			}
			
			if(boole1) {
				e1.trianglesInc.add(t);
				edgeArr.add(e1);
				P1.edgesInc.add(e1);
				P2.edgesInc.add(e1);
				t.e1 = e1;
				t.triangleEdgeArr[0] = e1;
			}
			else {
				e1 = edgeTrie.search(e1.toString()).value;
				e1.trianglesInc.add(t);
				t.e1 = e1;
				t.triangleEdgeArr[0] = e1;
			}
			
			if(boole2) {
				e2.trianglesInc.add(t);
				edgeArr.add(e2);
				P2.edgesInc.add(e2);
				P3.edgesInc.add(e2);
				t.e2 = e2;
				t.triangleEdgeArr[1] = e2;
				
			}
			else {
				e2 = edgeTrie.search(e2.toString()).value;
				e2.trianglesInc.add(t);
				t.e2 = e2;
				t.triangleEdgeArr[1] = e2;
			}
			
			
			if(boole3) {
				e3.trianglesInc.add(t);
				edgeArr.add(e3);
				P3.edgesInc.add(e3);
				P1.edgesInc.add(e3);
				t.e3 = e3;
				t.triangleEdgeArr[2] = e3;
			}
			else {
				e3 = edgeTrie.search(e3.toString()).value;
				e3.trianglesInc.add(t);
				t.e3 = e3;
				t.triangleEdgeArr[2] = e3;
			}
			
			triangleArr.add(t);
			
			
		}
		return bool;
		}
	 
	 public int TYPE_MESH(){
		 int num_1=0;
		 int num_2=0;
		 int num_3=0;
		 for(int i=0; i<edgeArr.size(); i++) {
			 try {
				if(edgeArr.get(i).trianglesInc.size()==1) {
					 num_1++;
				 }
				else if(edgeArr.get(i).trianglesInc.size()==2) {
					num_2++;
				}
				else {
					num_3++;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} }
			 if(num_2 == edgeArr.size())
				 return 1;
			 if(num_3>0) return 3;
			 if(num_1>0 && (num_1 + num_2) == edgeArr.size()) {
				// System.out.println("number of 1 triangle edges " + num_1);
				 //System.out.println("number of 2 triangle edges " + num_2);
				 //System.out.println("number of 3 triangle edges " + num_3);
				 return 2;
			 }
			 else return -1;
			 }
	 
	 public EdgeInterface [] BOUNDARY_EDGES(){
		 ArrayListAss<Edge> bEdgeArr = new ArrayListAss<Edge>();
		 for(int i=0; i<edgeArr.size(); i++) {
			 try {
				if(edgeArr.get(i).trianglesInc.size()==1) {
					 bEdgeArr.add(edgeArr.get(i));
				 }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 if(bEdgeArr.size()==0) {
			 return null;
		 }
		 try {
			msort(bEdgeArr, 0, bEdgeArr.size()-1);
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		 Edge[] bEdgearray = new Edge[bEdgeArr.size()];
		 System.out.println(bEdgeArr.size());
		 for(int i=0; i<bEdgeArr.size(); i++) {
			 try {
				bEdgearray[i] = bEdgeArr.get(i);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }		 
		 return bEdgearray;
	 }
	 
	 private void merge(ArrayListAss<Edge> list, int l, int mid, int r) throws Exception {
	    	int num1 = mid - l +1;
	    	int num2 = r-mid;
	    	ArrayListAss<Edge> left = new ArrayListAss<Edge>();
	    	ArrayListAss<Edge> right = new ArrayListAss<Edge>();
	    	for(int i=0; i<num1; i++) {
	    		left.add(list.get(l+i));
	    	}
	    	for(int j=0; j<num2; j++) {
	    		right.add(list.get(mid+1+j));
	    	}
	    	int i=0;
	    	int j=0;
	    	int k=l;
	    	while(i<num1 && j<num2) {
	    			if(left.get(i).length()<=right.get(j).length()) {
	        			list.set(k,left.get(i));
	        			i++; k++;
	        		}
	        		else {
	        			list.set(k, right.get(j));
	        			j++; k++;
	        		} }
	    	while(i<num1) {
	    		list.set(k,left.get(i));
	    		i++; k++;
	    	}
	    	while(j<num2) {
	    		list.set(k, right.get(j));
				j++; k++;
	    	}  }
	 
	 private void msort(ArrayListAss<Edge> list, int l, int r) throws Exception {
	    	if(l<r) {
	    		int mid = (l+r)/2;
	    		msort(list, l, mid);
	    		msort(list, mid+1, r);
	    		merge(list, l, mid, r);
	    	}
	    }
	 
	 private void mergeTriangle(ArrayListAss<Triangle> list, int l, int mid, int r) throws Exception {
	    	int num1 = mid - l +1;
	    	int num2 = r-mid;
	    	ArrayListAss<Triangle> left = new ArrayListAss<Triangle>();
	    	ArrayListAss<Triangle> right = new ArrayListAss<Triangle>();
	    	for(int i=0; i<num1; i++) {
	    		left.add(list.get(l+i));
	    	}
	    	for(int j=0; j<num2; j++) {
	    		right.add(list.get(mid+1+j));
	    	}
	    	int i=0;
	    	int j=0;
	    	int k=l;
	    	while(i<num1 && j<num2) {
	    			if(left.get(i).insert_num<right.get(j).insert_num) {
	        			list.set(k,left.get(i));
	        			i++; k++;
	        		}
	        		else {
	        			list.set(k, right.get(j));
	        			j++; k++;
	        		} }
	    	while(i<num1) {
	    		list.set(k,left.get(i));
	    		i++; k++;
	    	}
	    	while(j<num2) {
	    		list.set(k, right.get(j));
				j++; k++;
	    	}  }
	 
	 private void msortTriangle(ArrayListAss<Triangle> list, int l, int r) throws Exception {
	    	if(l<r) {
	    		int mid = (l+r)/2;
	    		msortTriangle(list, l, mid);
	    		msortTriangle(list, mid+1, r);
	    		mergeTriangle(list, l, mid, r);
	    	}
	    }
	 
	 public TriangleInterface [] NEIGHBORS_OF_TRIANGLE(float [] triangle_coord){
		Point P1 = new Point(triangle_coord[0], triangle_coord[1], triangle_coord[2]);
		Point P2 = new Point(triangle_coord[3], triangle_coord[4], triangle_coord[5]);
		Point P3 = new Point(triangle_coord[6], triangle_coord[7], triangle_coord[8]);
		Triangle t = new Triangle(P1,P2,P3);
		if(triangleTrie.search(t.toString())!=null) {
		t = triangleTrie.search(t.toString()).value; }
		else return null; 
		
		ArrayListAss<Triangle> triangleList = new ArrayListAss<Triangle>();
		for(int i=0; i<t.e1.trianglesInc.size(); i++) {
			try {
				if(!t.e1.trianglesInc.get(i).toString().equals(t.toString())) {
					triangleList.add(t.e1.trianglesInc.get(i));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i=0; i<t.e2.trianglesInc.size(); i++) {
			try {
				if(!t.e2.trianglesInc.get(i).toString().equals(t.toString())) {
					triangleList.add(t.e2.trianglesInc.get(i));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i=0; i<t.e3.trianglesInc.size(); i++) {
			try {
				if(!t.e3.trianglesInc.get(i).toString().equals(t.toString())) {
					triangleList.add(t.e3.trianglesInc.get(i));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			msortTriangle(triangleList, 0, triangleList.size()-1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Triangle[] triangleArr = new Triangle[triangleList.size()];
		for(int i=0; i<triangleList.size(); i++) {
			try {
				triangleArr[i] = triangleList.get(i);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		 return triangleArr;
		 }
	 
	 public EdgeInterface [] EDGE_NEIGHBOR_TRIANGLE(float [] triangle_coord){
		 Point P1 = new Point(triangle_coord[0], triangle_coord[1], triangle_coord[2]);
			Point P2 = new Point(triangle_coord[3], triangle_coord[4], triangle_coord[5]);
			Point P3 = new Point(triangle_coord[6], triangle_coord[7], triangle_coord[8]);
			Triangle t = new Triangle(P1,P2,P3);
			if(triangleTrie.search(t.toString())!=null) {
				t = triangleTrie.search(t.toString()).value;
			}
			else return null;
			Edge[] edgeArr = new Edge[] {t.e1, t.e2, t.e3};
					 
		 return edgeArr; 
		 }
	 
	 public PointInterface [] VERTEX_NEIGHBOR_TRIANGLE(float [] triangle_coord){
		 Point P1 = new Point(triangle_coord[0], triangle_coord[1], triangle_coord[2]);
			Point P2 = new Point(triangle_coord[3], triangle_coord[4], triangle_coord[5]);
			Point P3 = new Point(triangle_coord[6], triangle_coord[7], triangle_coord[8]);
			Triangle t = new Triangle(P1,P2,P3);
			if(triangleTrie.search(t.toString())!=null) {
				t = triangleTrie.search(t.toString()).value;
			}
			else return null;
			Point[] pointArr = new Point[] {t.P1, t.P2, t.P3}; 
			
		 return pointArr;
		 }
	 
	 public TriangleInterface [] EXTENDED_NEIGHBOR_TRIANGLE(float [] triangle_coord){
		    Point P1 = new Point(triangle_coord[0], triangle_coord[1], triangle_coord[2]);
			Point P2 = new Point(triangle_coord[3], triangle_coord[4], triangle_coord[5]);
			Point P3 = new Point(triangle_coord[6], triangle_coord[7], triangle_coord[8]);
			Triangle t = new Triangle(P1,P2,P3);
			if(triangleTrie.search(t.toString())!=null) {
				t = triangleTrie.search(t.toString()).value;
			}
			else return null; 
			ArrayListAss<Triangle> triangleList = new ArrayListAss<Triangle>();
			for(int i=0; i<t.P1.trianglesInc.size(); i++) {
				try {
					if(!t.P1.trianglesInc.get(i).toString().equals(t.toString())) {
						triangleList.add(t.P1.trianglesInc.get(i));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for(int i=0; i<t.P2.trianglesInc.size(); i++) {
				try {
					if(!t.P2.trianglesInc.get(i).toString().equals(t.toString())) {
						triangleList.add(t.P2.trianglesInc.get(i));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for(int i=0; i<t.P3.trianglesInc.size(); i++) {
				try {
					if(!t.P3.trianglesInc.get(i).toString().equals(t.toString())) {
						triangleList.add(t.P3.trianglesInc.get(i));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				msortTriangle(triangleList, 0, triangleList.size()-1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayListAss<Triangle> triangleList2 = new ArrayListAss<Triangle>();
			int cur_num=0;
			for(int i=0; i<triangleList.size(); i++) {
				try {
					if(triangleList.get(i).insert_num!=cur_num) {
						triangleList2.add(triangleList.get(i)); 
						cur_num = triangleList.get(i).insert_num;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Triangle[] triangleArr = new Triangle[triangleList2.size()];
			for(int i=0; i<triangleList2.size(); i++) {
				try {
					triangleArr[i] = triangleList2.get(i);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			 return triangleArr;
			 }
	 
	 public TriangleInterface [] INCIDENT_TRIANGLES(float [] point_coordinates){
		 Point P = new Point(point_coordinates[0], point_coordinates[1], point_coordinates[2]);
		 if(pointTrie.search(P.toString())!=null) {
				P = pointTrie.search(P.toString()).value;
			}
			else return null; 
		 Triangle[] pointTriangles = new Triangle[P.trianglesInc.size()];
		 for(int i=0; i<P.trianglesInc.size(); i++) {
			 try {
				pointTriangles[i] = P.trianglesInc.get(i);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 return pointTriangles;
		 }
	 
	 public PointInterface [] NEIGHBORS_OF_POINT(float [] point_coordinates){
		 Point P = new Point(point_coordinates[0], point_coordinates[1], point_coordinates[2]);
		 if(pointTrie.search(P.toString())!=null) {
				P = pointTrie.search(P.toString()).value;
			}
			else return null; 
		 ArrayListAss<Point> pointList = new ArrayListAss<Point>();
		 for(int i=0; i<P.edgesInc.size(); i++) {
			 try {
				 //System.out.println(P.edgesInc.get(i));
				if(!P.edgesInc.get(i).P1.toString().equals(P.toString())) {
					 pointList.add(P.edgesInc.get(i).P1);
				 }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 try {
				if(!P.edgesInc.get(i).P2.toString().equals(P.toString())) {
					 pointList.add(P.edgesInc.get(i).P2);
				 }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 Point[] pointArr = new Point[pointList.size()];
		 for(int i=0; i<pointList.size(); i++) {
			 try {
				pointArr[i] = pointList.get(i);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 
		 return pointArr;
		 }
	 
	 public EdgeInterface [] EDGE_NEIGHBORS_OF_POINT(float [] point_coordinates){
		 Point P = new Point(point_coordinates[0], point_coordinates[1], point_coordinates[2]);
		 if(pointTrie.search(P.toString())!=null) {
				P = pointTrie.search(P.toString()).value;
			}
			else return null; 
		 Edge[] edgeArr = new Edge[P.edgesInc.size()];
		 for(int i=0; i<P.edgesInc.size(); i++) {
			 try {
				edgeArr[i] = P.edgesInc.get(i);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 
		 return edgeArr;
		 }
	 
	 public TriangleInterface [] FACE_NEIGHBORS_OF_POINT(float [] point_coordinates){ 
		 Point P = new Point(point_coordinates[0], point_coordinates[1], point_coordinates[2]);
		 if(pointTrie.search(P.toString())!=null) {
				P = pointTrie.search(P.toString()).value;
			}
			else return null; 
		 Triangle[] pointTriangles = new Triangle[P.trianglesInc.size()];
		 for(int i=0; i<P.trianglesInc.size(); i++) {
			 try {
				pointTriangles[i] = P.trianglesInc.get(i);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 return pointTriangles;
		 }
	 
	 public TriangleInterface [] TRIANGLE_NEIGHBOR_OF_EDGE(float [] edge_coordinates){ 
		 Point P1 = new Point(edge_coordinates[0], edge_coordinates[1], edge_coordinates[2]);
			Point P2 = new Point(edge_coordinates[3], edge_coordinates[4], edge_coordinates[5]);
		Edge E = new Edge(P1,P2);
		if(edgeTrie.search(E.toString())!=null) {
			E = edgeTrie.search(E.toString()).value;
		}
		else return null; 
		Triangle[] edgeTriangles = new Triangle[E.trianglesInc.size()];
		for(int i=0; i<E.trianglesInc.size(); i++) {
			try {
				edgeTriangles[i] = E.trianglesInc.get(i);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 return edgeTriangles;
		 }
	 
	 public void DFSgraph(int i, int n) {
		 boolean[] visited = new boolean[n];
		 DFShelper(visited, i);
	 }
	 
	 public void DFShelper(boolean[] v, int i) {
		 //System.out.println(i);
		 v[i] = true;
		Triangle t = triangleArr.get(i);
		for(int k=0; k<3; k++) {
			Edge e = t.triangleEdgeArr[k];
			//System.out.println(e);
		for(int j=0; j<e.trianglesInc.size(); j++) {
			//System.out.println(" i= " + i + " " + e.trianglesInc.get(j));
			if(!v[e.trianglesInc.get(j).insert_num-1]) {
				//System.out.println(e.trianglesInc.get(j).insert_num);
				DFShelper(v, (e.trianglesInc.get(j).insert_num-1));
			}
		}
		}
		 
	 }
	 
	 public int COUNT_CONNECTED_COMPONENTS(){
		 boolean[] visited = new boolean[triangleArr.size()];
		 //System.out.println(visited[0]);
		 int count=0;
		 for(int i=0; i<triangleArr.size(); i++) {
			 if(!visited[i]) {
				 //System.out.println("number of times helper called from main" + i);
				 DFShelper(visited, i);
				 count++;
			 }
		 }
		 return count;
		 }
	 
	 public boolean IS_CONNECTED(float [] triangle_coord_1, float [] triangle_coord_2){
		 Point P1 = new Point(triangle_coord_1[0], triangle_coord_1[1], triangle_coord_1[2]);
			Point P2 = new Point(triangle_coord_1[3], triangle_coord_1[4], triangle_coord_1[5]);
			Point P3 = new Point(triangle_coord_1[6], triangle_coord_1[7], triangle_coord_1[8]);
			Triangle t1 = new Triangle(P1,P2,P3);
			Point P4 = new Point(triangle_coord_2[0], triangle_coord_2[1], triangle_coord_2[2]);
			Point P5 = new Point(triangle_coord_2[3], triangle_coord_2[4], triangle_coord_2[5]);
			Point P6 = new Point(triangle_coord_2[6], triangle_coord_2[7], triangle_coord_2[8]);
			Triangle t2 = new Triangle(P4,P5,P6);
			
			if(triangleTrie.search(t1.toString())!=null) {
				t1 = triangleTrie.search(t1.toString()).value;
			}
			else {
				//System.out.println("T1 not found");
				return false;
			}
			if(triangleTrie.search(t2.toString())!=null) {
				t2 = triangleTrie.search(t2.toString()).value;
			}
			else {
				//System.out.println("T2 not found");
				return false;
			}
			boolean b = CheckPath(t1,t2);
		 return b;
		 }
	 
	 public void CheckPathHelper(boolean[] v, int i, Triangle t2) {
		//System.out.println("Printing triangle 1" + t1);
		//System.out.println(t2);
		 v[i] = true;
		Triangle t = triangleArr.get(i);
		for(int k=0; k<3; k++) {
			Edge e = t.triangleEdgeArr[k];
			for(int j=0; j<e.trianglesInc.size(); j++) {
				if(e.trianglesInc.get(j).toString().equals(t2.toString())) {
					v[e.trianglesInc.get(j).insert_num -1] = true;
					return;
				}
			if(!v[e.trianglesInc.get(j).insert_num-1]) {
				CheckPathHelper(v,(e.trianglesInc.get(j).insert_num-1),t2);
			}
		}
		}
	 }
	 
	 public boolean CheckPath(Triangle t1, Triangle t2) {
		 boolean[] visited = new boolean[triangleArr.size()];
		 CheckPathHelper(visited,t1.insert_num-1,t2);
		 return visited[t2.insert_num-1]; 
		 }
	 
	 public PointInterface [] CENTROID () {
		 boolean[] visitedTriangle = new boolean[triangleArr.size()];
		 boolean[] visitedPoint = new boolean[pointArr.size()];
		 int count=0;
		 ArrayListAss<Point> centroidList = new ArrayListAss<Point>();
		 for(int i=0; i<triangleArr.size(); i++) {
			 if(!visitedTriangle[i]) {
				 ArrayListAss<Point> compPointList = new ArrayListAss<Point>();
				 DFShelper2(visitedTriangle, i, compPointList, visitedPoint);
				 float xtotal=0;
				 float ytotal=0; 
				 float ztotal=0;
				 for(int j=0; j<compPointList.size(); j++) {
					 xtotal = xtotal + compPointList.get(j).x;
					 ytotal = ytotal + compPointList.get(j).y;
					 ztotal = ztotal + compPointList.get(j).z;
				 }
				 xtotal = (xtotal/compPointList.size());
				 ytotal = (ytotal/compPointList.size());
				 ztotal = (ztotal/compPointList.size());
				 Point P = new Point(xtotal, ytotal, ztotal);
				 centroidList.add(P);
				 count++;
			 }
		 }
		 msortPoints(centroidList,0,centroidList.size()-1);
		 Point[] centroidArr = new Point[centroidList.size()];
		 for(int i=0; i<centroidList.size(); i++) {
			 centroidArr[i] = centroidList.get(i);
		 }
		 return centroidArr;
		 }
	 
	 public void DFShelper2(boolean[] vTriangle, int i, ArrayListAss<Point> compPointList, boolean[] vPoint) {
		vTriangle[i] = true;
		Triangle t = triangleArr.get(i);
		if(!vPoint[t.P1.entry_num-1]) {
			compPointList.add(t.P1);
			vPoint[t.P1.entry_num-1] = true;
		}
		if(!vPoint[t.P2.entry_num-1]) {
			compPointList.add(t.P2);
			vPoint[t.P2.entry_num-1] = true;
		}
		if(!vPoint[t.P3.entry_num-1]) {
			compPointList.add(t.P3);
			vPoint[t.P3.entry_num-1] = true;
		}
		for(int k=0; k<3; k++) {
			Edge e = t.triangleEdgeArr[k];
		for(int j=0; j<e.trianglesInc.size(); j++) {
			if(!vTriangle[e.trianglesInc.get(j).insert_num-1]) {
				DFShelper2(vTriangle, (e.trianglesInc.get(j).insert_num-1), compPointList, vPoint);
			}
		}
		}
		 
	 }
	 
	 private void mergePoints(ArrayListAss<Point> list, int l, int mid, int r) {
	    	int num1 = mid - l +1;
	    	int num2 = r-mid;
	    	ArrayListAss<Point> left = new ArrayListAss<Point>();
	    	ArrayListAss<Point> right = new ArrayListAss<Point>();
	    	for(int i=0; i<num1; i++) {
	    		left.add(list.get(l+i));
	    	}
	    	for(int j=0; j<num2; j++) {
	    		right.add(list.get(mid+1+j));
	    	}
	    	int i=0;
	    	int j=0;
	    	int k=l;
	    	while(i<num1 && j<num2) {
	    			if(left.get(i).x<right.get(j).x) {
	        			list.set(k,left.get(i));
	        			i++; k++;
	        		}
	        		else if(left.get(i).x>right.get(j).x) {
	        			list.set(k, right.get(j));
	        			j++; k++;
	        		}
	        		else{
	        			if(left.get(i).y<right.get(j).y) {
		        			list.set(k,left.get(i));
		        			i++; k++;
		        		}
		        		else if(left.get(i).y>right.get(j).y) {
		        			list.set(k, right.get(j));
		        			j++; k++;
		        		}
		        		else {
		        			if(left.get(i).z<right.get(j).z) {
			        			list.set(k,left.get(i));
			        			i++; k++;
			        		}
			        		else {
			        			list.set(k, right.get(j));
			        			j++; k++;
			        		}
		        		}
	        			
	        			}
	        		}
	    	while(i<num1) {
	    		list.set(k,left.get(i));
	    		i++; k++;
	    	}
	    	while(j<num2) {
	    		list.set(k, right.get(j));
				j++; k++;
	    	}  }
	 
	 private void msortPoints(ArrayListAss<Point> list, int l, int r){
	    	if(l<r) {
	    		int mid = (l+r)/2;
	    		msortPoints(list, l, mid);
	    		msortPoints(list, mid+1, r);
	    		mergePoints(list, l, mid, r);
	    	}
	    }
	 
	 public PointInterface CENTROID_OF_COMPONENT (float [] point_coordinates){
		 Point P = new Point(point_coordinates[0], point_coordinates[1], point_coordinates[2]);
		 if(pointTrie.search(P.toString())!=null) {
				P = pointTrie.search(P.toString()).value;
			}
			else return null;
		 Triangle t = P.trianglesInc.get(0);
		 boolean[] visitedTriangle = new boolean[triangleArr.size()];
		 boolean[] visitedPoint = new boolean[pointArr.size()];
		 int count=0;
		 ArrayListAss<Point> pointList = new ArrayListAss<Point>();
		 compCentroid(t, visitedTriangle, visitedPoint,pointList);
		 float xtotal=0;
		 float ytotal=0; 
		 float ztotal=0;
		 for(int j=0; j<pointList.size(); j++) {
			 xtotal = xtotal + pointList.get(j).x;
			 ytotal = ytotal + pointList.get(j).y;
			 ztotal = ztotal + pointList.get(j).z;
		 }
		 xtotal = (xtotal/pointList.size());
		 ytotal = (ytotal/pointList.size());
		 ztotal = (ztotal/pointList.size());
		 Point P2 = new Point(xtotal, ytotal, ztotal);
		 return P2; 
		 }
	 
	 public void compCentroid(Triangle t, boolean[] vTriangle, boolean[] vPoint, ArrayListAss<Point> pointList) {
		 	vTriangle[t.insert_num-1] = true;
			if(!vPoint[t.P1.entry_num-1]) {
				pointList.add(t.P1);
				vPoint[t.P1.entry_num-1] = true;
			}
			if(!vPoint[t.P2.entry_num-1]) {
				pointList.add(t.P2);
				vPoint[t.P2.entry_num-1] = true;
			}
			if(!vPoint[t.P3.entry_num-1]) {
				pointList.add(t.P3);
				vPoint[t.P3.entry_num-1] = true;
			}
			for(int k=0; k<3; k++) {
				Edge e = t.triangleEdgeArr[k];
			for(int j=0; j<e.trianglesInc.size(); j++) {
				if(!vTriangle[e.trianglesInc.get(j).insert_num-1]) {
					compCentroid(e.trianglesInc.get(j), vTriangle, vPoint, pointList); 
				}
			}
			}
	 }
	 
	 public int MAXIMUM_DIAMETER(){
		 boolean[] visited = new boolean[triangleArr.size()];
		 int count=0;
		 ArrayListAss<Triangle> maxCompTriangles = new ArrayListAss<Triangle>();
		 int max=0;
		 for(int i=0; i<triangleArr.size(); i++) {
			 if(!visited[i]) {
				 ArrayListAss<Triangle> compTriangles = new ArrayListAss<Triangle>();
				 DFSdiam(visited, i, compTriangles);
				 if(compTriangles.size()>max) {
					 maxCompTriangles = compTriangles;
					 max = compTriangles.size();
				 }
				 count++;
			 }
		 }
		 int diameter=0;
		 for(int i=0; i<maxCompTriangles.size(); i++) {
			 int[] level = new int[triangleArr.size()];
			 boolean[] visited2 = new boolean[triangleArr.size()];
			 Queue<Triangle> triangleQ = new Queue<Triangle>(max);
			 visited2[maxCompTriangles.get(i).insert_num-1] = true;
			 triangleQ.enqueue(maxCompTriangles.get(i));
			 level[maxCompTriangles.get(i).insert_num-1] = 0;
			 while(triangleQ.size()!=0) {
				 Triangle t = triangleQ.dequeue();
				 for(int k=0; k<3; k++) {
						Edge e = t.triangleEdgeArr[k];
					for(int j=0; j<e.trianglesInc.size(); j++) {
						if(!visited2[e.trianglesInc.get(j).insert_num-1]) {
							visited2[e.trianglesInc.get(j).insert_num-1] = true;
							triangleQ.enqueue(e.trianglesInc.get(j));
							level[e.trianglesInc.get(j).insert_num-1] = 1+ level[t.insert_num-1];
						}
					}
					} 
			 }
			 int max2=0;
			 for(int l=0; l<triangleArr.size(); l++) {
				 if(level[l]>max2) max2 = level[l];
			 }
			 if(max2>diameter) diameter = max2;
			 
		 }
		 //System.out.println(maxCompTriangles.get(0));
		 return diameter;
		 }
	 
	 public void DFSdiam(boolean[] v, int i, ArrayListAss<Triangle> compTriangles) {
		 v[i] = true;
		Triangle t = triangleArr.get(i);
		//System.out.println(t);
		compTriangles.add(t);
		for(int k=0; k<3; k++) {
			Edge e = t.triangleEdgeArr[k];
			//System.out.println(e);
		for(int j=0; j<e.trianglesInc.size(); j++) {
			//System.out.println(" i= " + i + " " + e.trianglesInc.get(j));
			if(!v[e.trianglesInc.get(j).insert_num-1]) {
				DFSdiam(v, (e.trianglesInc.get(j).insert_num-1), compTriangles);
			}
		}
		}
		 
	 }
	 
	 public PointInterface [] CLOSEST_COMPONENTS(){
		 boolean[] visited = new boolean[triangleArr.size()];
		 int[] comp_num = new int[pointArr.size()];
		 int count=1;		 
		 for(int i=0; i<triangleArr.size(); i++) {
			 if(!visited[i]) {
				 DFShelper3(visited, i, count, comp_num);
				 count++;
			 }
		 }
		 Point[] closest_points = new Point[2];
		 float distMin = Float.MAX_VALUE;
		 
		 for(int i=0; i<pointArr.size(); i++) {
			 Point Pi = pointArr.get(i);
			 //System.out.println(Pi.entry_num);
			 int compNumi = comp_num[Pi.entry_num-1];
			 for(int j=0; j<pointArr.size(); j++) {
				 Point Pj = pointArr.get(j);
				 int compNumj = comp_num[Pj.entry_num-1];
				 if(compNumi!=compNumj) {
					 float dist = ((Pi.x - Pj.x)*(Pi.x - Pj.x) + (Pi.y - Pj.y)*(Pi.y - Pj.y) + (Pi.z - Pj.z)*(Pi.z - Pj.z));
					 if (dist<distMin) {
						 distMin = dist;
						 Point[] points = new Point[] {Pi,Pj};
						 closest_points = points;
					 }
				 }
			 }
		 }
		 return closest_points;
		 }
	 
	 public void DFShelper3(boolean[] v, int i, int count, int[] comp_num) {
		 //System.out.println(i);
		 v[i] = true;
		Triangle t = triangleArr.get(i);
		for(int m=0; m<3; m++) {
			//System.out.println(t.trianglePointArr[m].entry_num);
			comp_num[t.trianglePointArr[m].entry_num-1] = count;
		}
		for(int k=0; k<3; k++) {
			Edge e = t.triangleEdgeArr[k];
		for(int j=0; j<e.trianglesInc.size(); j++) {
			if(!v[e.trianglesInc.get(j).insert_num-1]) {
				for(int n=0; n<3; n++) {
					comp_num[e.trianglesInc.get(j).trianglePointArr[n].entry_num-1] = count;
				}
				DFShelper3(v, (e.trianglesInc.get(j).insert_num-1), count, comp_num);
			}
		}
		}
		 
	 }
	 
	 
	 
	 }
	 
	 
	 
 
 
 

 




