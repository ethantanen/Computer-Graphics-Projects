
public class Edge {
	
	
	double[] v1,v2;
	int fl,fr;
	int pl,sl,pr,sr;
	
	public Edge(double[] v1_, double[] v2_, int fl_, int fr_, int pl_, int sl_, int pr_, int sr_){
		v1 = v1_;
		v2 = v2_;
		fl = fl_;
		fr = fr_;
		pl = pl_;
		sl = sl_;
		pr = pr_;
		sr = sr_;
	}

	public Edge() {
		// TODO Auto-generated constructor stub
	}

	public void updateEdge(double[] v1_, double[] v2_) {
		v1 = v1_;
		v2 = v2_;
		
	}
}
