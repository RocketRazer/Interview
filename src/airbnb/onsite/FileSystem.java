package airbnb.onsite;

import airbnb.onsite.datastructure.BiConsumerExample;

import java.util.HashMap;
import java.util.Map;

/**
 * create(path, value), set(path, value), get(path), watch(path, callback)
 *
 * Example
 * create("/a",1)
 * get("/a") //得到1
 * create("/a/b",2)
 * get("/a/b") //得到2
 * create("/c/d",1) //Error，因为它的上一级“/c”并不存在
 * get("/c") //Error,因为“/c”不存在
 *
 *
 * follow up是写一个watch函数，比如watch("/a",new Runnable(){System.out.println("helloword");})后，
 * 每当create("/a/b"，1) 等在/a之下的目录不产生error的话，都会执行绑在“/a”上的callback函数
 *
 *
 * 比如 watch("/a",System.out.println("yes"))
 * watch("/a/b",System.out.println("no"))
 * 当create("/a/b/c",1)时，两个callback函数都会被触发，会output yes 和no
 *
 * NOTE: 这里用的Runnable会一直运行不停，Runnable用得不熟表示不知道怎么停下来，就用System.exit(0)测试了
 */

public class FileSystem {
    /**
     * START Implementation
     */
    Map<String, Integer> pathMap;
    Map<String, Runnable> callBackMap;
    Map<String, BiConsumerExample> biConsumerBackMap;

    public FileSystem() {
        this.pathMap = new HashMap<>();
        this.callBackMap = new HashMap<>();
        // set root path
        this.pathMap.put("", 0);
    }
    /**
     * Create a path with value
     * @param path
     * @param value
     * @return true(create success) false(fail: exists or does not have parent directory)
     */
    public boolean create(String path, Integer value) {
        if (pathMap.containsKey(path)) {
            return false;
        }

        int lastSlashIndex = path.lastIndexOf("/");
        String parentPath = path.substring(0, lastSlashIndex);
        if (!pathMap.containsKey(parentPath)) {
            return false;
        }

        pathMap.put(path, value);

        return true;
    }

    /**
     * Set a path value
     * @param path
     * @param value
     * @return true(set succesful), false(fail: path does not exist)
     */
    public boolean set(String path, Integer value) {
        if (!pathMap.containsKey(path)) {
            return  false;
        }

        pathMap.put(path, value);

        String curPath = path;
        while (curPath.length() > 0) {
            if (callBackMap.containsKey(curPath)) {
                callBackMap.get(curPath).run();
            }

            int lastSlashIndex = curPath.lastIndexOf("/");
            curPath = curPath.substring(0, lastSlashIndex);
        }
        return true;
    }

    /**
     * get a value from a path
     * @param path
     * @return
     */
    public Integer get(String path) {
        if (pathMap.containsKey(path)) {
            return pathMap.get(path);
        } else {
            return null;
        }
    }


    /**
     * Add runnable for path
     * @param path
     * @param runnable
     * @return true if path exits otherwise false
     */
    public boolean watch(String path, Runnable runnable) {
        if (!pathMap.containsKey(path)) {
            return false;
        }

        callBackMap.put(path, runnable);
        return true;
    }



    public static void main(String[] args) {
        FileSystem fs = new FileSystem();
        System.out.println(fs.get("/a")); // null
        System.out.println(fs.set("/a", 2)); // false
        System.out.println(fs.create("/a", 1)); // true
        System.out.println(fs.get("/a")); // 1
        System.out.println(fs.create("/a/b", 2)); // true
        System.out.println(fs.create("/b/c", 3)); // false

        System.out.println(fs.watch("/a/b", new Runnable() {
            @Override
            public void run() {
                System.out.println("callback on /a/b");
                System.exit(0);
            }
        }));

        System.out.println(fs.watch("/a", new Runnable() {
            @Override
            public void run() {
                System.out.println("callback on /a");
                System.exit(0);
            }
        }));
        System.out.println(fs.set("/a/b", 10)); // trigger 2 callbacks and true
    }

}
