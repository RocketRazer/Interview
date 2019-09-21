package airbnb.onsite.datastructure;

/**
 * Trie Search Result
 */
public class ReturnType {
    public boolean hasPrefix;
    public boolean hasWord;

    ReturnType(boolean hasPrefix, boolean hasWord) {
        this.hasPrefix = hasPrefix;
        this.hasWord = hasWord;
    }
}
