package graph_mst_using_kruskal_algorithm;

public class EdgePoints {

	private Integer rightJoint;
	private Integer leftJoint;
	
	public EdgePoints(int i, int j) {
		this.rightJoint = i;
		this.leftJoint = j;
	}
	
	public Integer getRightJoint() {
		return rightJoint;
	}
	public void setRightJoint(Integer rightJoint) {
		this.rightJoint = rightJoint;
	}
	public Integer getLeftJoint() {
		return leftJoint;
	}
	public void setLeftJoint(Integer leftJoint) {
		this.leftJoint = leftJoint;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean returnCode = true;
		
		if (!(obj instanceof EdgePoints)) {
			return false;
		}
		
		if (!(this.rightJoint.equals(((EdgePoints) obj).rightJoint) && 
				this.leftJoint.equals(((EdgePoints) obj).leftJoint))) {
			returnCode = false;
		}
		return returnCode ;
	}
	
	@Override
	public int hashCode() {
		return this.rightJoint.hashCode() + this.leftJoint.hashCode();
	}
	
	@Override
	public String toString() {
		return this.leftJoint +"-"+ this.rightJoint;
	}
	
}
