import java.util.HashMap;

public class WordNet
{
	private HashMap<Integer, String> ids;
	private HashMap<String, SET<Integer>> nouns;
    private Digraph g;
    private SAP s;
	
    public WordNet(String synsets, String hypernyms)
    {	
    	if (synsets == null || hypernyms == null)
    	{
    		throw new NullPointerException();
    	}
    	
    	ids = new HashMap<>();
    	nouns = new HashMap<>();
    	
    	// Parse synsets
        int largestId = -1;
        In inSynsets = new In(synsets);
        
        while (inSynsets.hasNextLine())
        {
            String line = inSynsets.readLine();
            String[] tokens = line.split(",");
            
            // Synset ID
            int id = Integer.parseInt(tokens[0]);
            if (id > largestId)
            {
                largestId = id;
            }

            // Nouns in synset
            String synset = tokens[1];
            String[] nouns = synset.split(" ");
            for (String noun : nouns)
            {
            	ids.put(id, noun);
            	
               if (this.nouns.containsKey(noun))
               {
            	   this.nouns.get(noun).add(id);
               }
               else
               {
            	   SET thing = new SET<Integer>();
            	   thing.add(id);
            	   this.nouns.put(noun, thing);
               }
            }
        }
        
        inSynsets.close();
        
        g = new Digraph(largestId + 1);
        
        // Parse hypernyms
        In inHypernyms = new In(hypernyms);
        while (inHypernyms.hasNextLine())
        {
            String line = inHypernyms.readLine();
            String[] tokens = line.split(",");
            
            int v = Integer.parseInt(tokens[0]);
            
            for (int i = 1; i < tokens.length; i++)
            {
            	g.addEdge(v, Integer.parseInt(tokens[i]));
            }
        }
        
        inHypernyms.close();
        
        s = new SAP(g);
        
        DirectedCycle c = new DirectedCycle(g);
        
		if (c.hasCycle()) 
		{
			throw new IllegalArgumentException();
		}
		
		int roots = 0;
		
		for (int i = 0; i < g.V(); i++)
		{
			if (!g.adj(i).iterator().hasNext())
			{
				roots++;
			}
		}

		if (roots != 1)
		{
			throw new IllegalArgumentException();
		}
    }

    public Iterable<String> nouns()
    {
    	return nouns.keySet();
    }

    public boolean isNoun(String word)
    {
    	if (word == null)
    	{
    		throw new NullPointerException();
    	}
    	
    	return nouns.containsKey(word);
    }

    public int distance(String nounA, String nounB)
    {    	
    	if (nounA == null || nounB == null)
    	{
    		throw new NullPointerException();
    	}
    	
    	if (!isNoun(nounA) || !isNoun(nounB))
    	{
    		throw new IllegalArgumentException();
    	}
    	
    	return s.length(nouns.get(nounA), nouns.get(nounB));
    }

    public String sap(String nounA, String nounB)
    {
    	if (nounA == null || nounB == null)
    	{
    		throw new NullPointerException();
    	}
    	
    	if (!isNoun(nounA) || !isNoun(nounB))
    	{
    		throw new IllegalArgumentException();
    	}
        
    	String ancestor = "";
        for (int noun : nouns.get(ids.get(s.ancestor(nouns.get(nounA), nouns.get(nounB)))))
        {
        	ancestor += ids.get(noun) + " ";
        }
        
        return ancestor.trim(); 
    }
    
    private void testNouns(String nounA, String nounB)
    {
        System.out.print("'" + nounA + "' and '" + nounB + "': ");
        System.out.print("sap: '" + sap(nounA, nounB));
        System.out.println("', distance=" + distance(nounA, nounB));
    }

    // for unit testing of this class
    public static void main(String[] args)
    {
		String synsetsFile = "testInput/synsets.txt";
		String hypernymsFile = "testInput/hypernyms.txt";

		WordNet wordnet = new WordNet(synsetsFile, hypernymsFile);
        wordnet.testNouns("municipality", "region");
        wordnet.testNouns("individual", "edible_fruit");
        wordnet.testNouns("Black_Plague", "black_marlin");
        wordnet.testNouns("American_water_spaniel", "histology");
        wordnet.testNouns("Brown_Swiss", "barrel_roll");
        
        wordnet.testNouns("chocolate", "brownie");
        wordnet.testNouns("cookie", "brownie");
        wordnet.testNouns("martini", "beer");
    }
}