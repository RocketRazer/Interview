package airbnb.onsite.datastructure;


public class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }


    public void insertWord (String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (cur.children.containsKey(c)) {
                cur = cur.children.get(c);
            } else {
                TrieNode newNode = new TrieNode(c);
                cur.children.put(c, newNode);
                cur = newNode;
            }
        }

        cur.word = word;
        cur.hasWord = true;
    }


    public boolean startsWith(String prefix) {
        TrieNode lastCharNode = findLastCharNode(prefix);
        return lastCharNode == null ? false : true;
    }

    public boolean hasWord(String word) {
        TrieNode lastCharNode = findLastCharNode(word);
        return lastCharNode == null ? false : lastCharNode.hasWord;
    }


    private TrieNode findLastCharNode(String str) {
        TrieNode cur = root;

        for (char c : str.toCharArray()) {
            if (cur.children.containsKey(c)) {
                cur = cur.children.get(c);
            } else {
                //注意这里不要 写成 cur = null； 会破坏trie的结构
                return null;
            }
        }

        return cur;
    }

    // TODO check whether this method can be replaced with exisiting method
    public ReturnType search(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!cur.children.containsKey(c)) {
                return new ReturnType(false, false);
            }
            cur = cur.children.get(c);
        }
        return new ReturnType(true, cur.hasWord);
    }

}
    
    