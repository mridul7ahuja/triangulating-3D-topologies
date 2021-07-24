
public class Point implements PointInterface {
	float x;
	float y;
	float z;
	public Point(float x, float y, float z) {
		this.x = x;
		this.y= y;
		this.z = z;
	}
	ArrayListAss<Triangle> trianglesInc = new ArrayListAss<Triangle>();
	ArrayListAss<Edge> edgesInc = new ArrayListAss<Edge>();
	
	public int entry_num;

	@Override
	public float getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public float getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public float getZ() {
		// TODO Auto-generated method stub
		return z;
	}

	@Override
	public float[] getXYZcoordinate() {
		float[] arr = new float[3];
		arr[0] = x;
		arr[1] = y;
		arr[2] = z;
		return arr;
	}

	@Override
	public String toString() {
		String str = Float.toString(x) + " " + Float.toString(y) + " " + Float.toString(z);
		return str;
	}

	public int compareTo(Point p2) {
		if(this.x>p2.getX()) {
			return 1;
		}
		else if(this.x<p2.getX()) {
			return -1;
		}
		else {
			if(this.y>p2.getY()) {
				return 1;
			}
			else if(this.y<p2.getY()) {
				return -1;
			}
			else {
				if(this.z>p2.getZ()) {
					return 1;
				}
				else if(this.z<p2.getZ()) {
					return -1;
				}
				else {
					return 0;
				}
			}
		}
	}
	

	
}
