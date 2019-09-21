package data_structure.trie;

public interface Trie {
    /**
     * @param word: a word
     * @return: nothing
     */
    void insert(String word);

    /**
     * @param word: A string
     * @return: if the word is in the trie.
     */
    boolean search(String word);


    /**
     * @param prefix: A string
     * @return: if there is any word in the trie that starts with the given prefix.
     */
    boolean startsWith(String prefix);

    /**
     * @param s: A string
     * @return return the last character's TrieNode if exists
     * */
    TrieNode searchLastCharacterNode(String s);
}
