public class SAP
{
	private Digraph g;
	
    public SAP(Digraph G)
    {
    	g = new Digraph(G);
    }

    public int length(int v, int w)
    {
    	BreadthFirstDirectedPaths V = new BreadthFirstDirectedPaths(g, v);
    	BreadthFirstDirectedPaths W = new BreadthFirstDirectedPaths(g, w);
    	int min = Integer.MAX_VALUE;
    	for (int i = 0; i < g.V(); i++)
    	{
    		if (V.hasPathTo(i) && W.hasPathTo(i))
    		{
    			if (V.distTo(i) + W.distTo(i) < min)
    			{
    				min = V.distTo(i) + W.distTo(i);
    			}
    		}
    	}
    	
    	if (min == Integer.MAX_VALUE)
    	{
    		return -1;
    	}
    	
    	return min;
    }

    public int ancestor(int v, int w)
    {
    	BreadthFirstDirectedPaths V = new BreadthFirstDirectedPaths(g, v);
    	BreadthFirstDirectedPaths W = new BreadthFirstDirectedPaths(g, w);
		int min = Integer.MAX_VALUE;
		int minNode = -1;
		
		for (int i = 0; i < g.V(); i++) {
			if (V.hasPathTo(i) && W.hasPathTo(i))
    		{
    			if (V.distTo(i) + W.distTo(i) < min)
    			{
    				min = V.distTo(i) + W.distTo(i);
					minNode = i;
				}
			}
		}

		return minNode;
    }

    public int length(Iterable<Integer> v, Iterable<Integer> w)
    {
    	BreadthFirstDirectedPaths V = new BreadthFirstDirectedPaths(g, v);
    	BreadthFirstDirectedPaths W = new BreadthFirstDirectedPaths(g, w);
    	int min = Integer.MAX_VALUE;
    	for (int i = 0; i < g.V(); i++)
    	{
    		if (V.hasPathTo(i) && W.hasPathTo(i))
    		{
    			if (V.distTo(i) + W.distTo(i) < min)
    			{
    				min = V.distTo(i) + W.distTo(i);
    			}
    		}
    	}
    	
    	if (min == Integer.MAX_VALUE)
    	{
    		return -1;
    	}
    	
    	return min;
    }

    public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
    {
    	BreadthFirstDirectedPaths V = new BreadthFirstDirectedPaths(g, v);
    	BreadthFirstDirectedPaths W = new BreadthFirstDirectedPaths(g, w);
		int min = Integer.MAX_VALUE;
		int minNode = -1;
		
		for (int i = 0; i < g.V(); i++) {
			if (V.hasPathTo(i) && W.hasPathTo(i))
    		{
    			if (V.distTo(i) + W.distTo(i) < min)
    			{
    				min = V.distTo(i) + W.distTo(i);
					minNode = i;
				}
			}
		}

		return minNode;
    }
    
    public static void main(String[] args)
    {
    	String digraphFile = "testInput/digraph1.txt";
    	
        In in = new In(digraphFile);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty())
        {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
