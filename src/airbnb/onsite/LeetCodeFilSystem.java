package airbnb.onsite;

import java.util.*;

class LeetCodeFileSystem {
    TrieNode root;

    public LeetCodeFileSystem() {
        root = new TrieNode("", true);
    }

    public List<String> ls(String path) {
        // find the last node
        TrieNode lastNode = findNode(path);

        List<String> res = new ArrayList<>();

        if (lastNode.isDirectory) {
            for (String str : lastNode.subDirectories.keySet()) {
                res.add(str);
            }
            Collections.sort(res);
        } else {
            res.add(lastNode.name);
        }


        return res;
    }

    public void mkdir(String path) {
        findNode(path);
    }

    private TrieNode findNode(String path) {
        TrieNode cur = root;

        //如果是root 需要特殊处理一下
        if (path.equals("/")) {
            return root;
        }

        String[] directories = path.substring(1).split("/");
        for (String dir : directories) {
            if (cur.subDirectories.containsKey(dir)) {
                cur = cur.subDirectories.get(dir);
            } else {
                //如果sub directory 不存在直接建立新node
                TrieNode newNode = new TrieNode(dir, true);
                cur.subDirectories.put(dir, newNode);
                cur = newNode;
            }
        }

        return cur;
    }

    public void addContentToFile(String filePath, String content) {
        TrieNode lastNode = findNode(filePath);
        lastNode.isDirectory = false;
        lastNode.content += content;
    }

    public String readContentFromFile(String filePath) {
        TrieNode lastNode = findNode(filePath);

        if (lastNode.isDirectory) {
            System.out.println("the filePath is a directory, not a file");
            return "";
        } else {
            return lastNode.content;
        }
    }

    class TrieNode {
        String name;
        boolean isDirectory;

        String content;
        Map<String, TrieNode> subDirectories;

        public TrieNode (String name, boolean isDirectory) {
            this.name = name;
            this.isDirectory = isDirectory;
            subDirectories = new HashMap<>();
            content = "";
        }
    }


    public static void main(String[] args) {
        LeetCodeFileSystem fs = new LeetCodeFileSystem();
        System.out.println(fs.ls("/"));
        fs.mkdir("/a/b/c");
        fs.addContentToFile("/a/b/c/d", "hello");
        System.out.println(fs.ls("/"));
        System.out.println(fs.readContentFromFile("/a/b/c/d"));

    }
}





