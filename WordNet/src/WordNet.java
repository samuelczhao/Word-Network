public class WordNet
{    
    public WordNet(String synsets, String hypernyms)
    {
    	// TODO:  You may use the code below to open and parse the
    	// synsets and hypernyms file.  However, you MUST add your
    	// own code to actually store the file contents into the
    	// data structures you create as fields of the WordNet class.
    	
        // Parse synsets
        int largestId = -1;				// TODO: You might find this value useful 
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
               // TODO: you should probably do something here
            }
            
            // tokens[2] is gloss, but we're not using that
        }
        inSynsets.close();
        
        // Parse hypernyms
        In inHypernyms = new In(hypernyms);
        while (inHypernyms.hasNextLine())
        {
            String line = inHypernyms.readLine();
            String[] tokens = line.split(",");
            
            int v = Integer.parseInt(tokens[0]);
            
            for (int i=1; i < tokens.length; i++)
            {
               // TODO: you should probably do something here
            }
        }
        inHypernyms.close();

        // TODO: Remember to remove this when your constructor is done!
    	throw new UnsupportedOperationException();
    }

    public Iterable<String> nouns()
    {
    	throw new UnsupportedOperationException();
    }

    public boolean isNoun(String word)
    {
    	throw new UnsupportedOperationException();
    }

    public int distance(String nounA, String nounB)
    {
    	throw new UnsupportedOperationException();
    }

    public String sap(String nounA, String nounB)
    {
    	throw new UnsupportedOperationException();
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