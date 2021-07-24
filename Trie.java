
public class Trie<T> {
	TrieNode<T> root;
	
	public Trie(TrieNode<T> root) {
		this.root = root;
	}
	
	public Trie() {
		root = new TrieNode<T>();
	}
	
	public TrieNode<T> search(String word) {
    	int level;
    	int length = word.length();
    	int index=0;
    	char curchar;
    	TrieNode<T> shift = root;
    	for(level = 0; level<length; level++) {
    		curchar = word.charAt(level);
    		index = word.charAt(level);
    		if(shift.children[index]==null) {
    			return null;
    		}
    		shift = shift.children[index];
    }
    	if((level == length) && (shift.isComplete) && (shift!=null)) {
    		return shift;
    	}
    	else {
    		return null;
    	}
    }
	
	 public boolean insert(String word, Object value) {
	    	int level;
	    	int length = word.length();
	    	int index;
	    	char curchar;
	    	TrieNode<T> shift = root;
	    	for(level = 0; level<length; level++) {
	    		curchar = word.charAt(level);
	    		index = word.charAt(level);
	    		//System.out.println(index);
	    		if(shift.children[index] == null) {
	    			shift.children[index] = new TrieNode<T>(curchar);
	    		}
	    		shift = shift.children[index];
	    }
	    	if(!shift.isComplete) {
	    	shift.isComplete = true;
	    	shift.value = (T) value;
	    	return true;
	    	}
	    	else {
	    		return false;
	    	}
	    }

}
