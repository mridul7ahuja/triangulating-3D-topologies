
public class TrieNode<T> {
	TrieNode[] children = new TrieNode[128];
	boolean isComplete;
	T value;
	char charac;
	public TrieNode(char c){
		isComplete = false;
		value = null;
		charac = c;
		for(int i=0; i<128; i++) {
			children[i] = null;
		}
	}
	public TrieNode() {
		isComplete = false;
		value = null;
		for(int i=0; i<128; i++) {
			children[i] = null;
		}
	}

    public T getValue() {
        return value;
    } }