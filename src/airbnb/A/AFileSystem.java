package airbnb.A;


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
public class AFileSystem {

    Map<String, Integer> valueByPath;
    Map<String, Runnable> watchByPath;

    public AFileSystem() {
        valueByPath = new HashMap<>();
        watchByPath = new HashMap<>();
        valueByPath.put("", 0);
    }

    /**
     * Create a path with value
     * @param path
     * @param val
     * @return true(create success) false(fail: exists or does not have parent directory)
     */
    public boolean create (String path, int val) {
        if (valueByPath.containsKey(path)) {
            return  false;
        }

        int lastBackSlashIdx = path.lastIndexOf("/");
        String parentPath = path.substring(0, lastBackSlashIdx);
        if (valueByPath.containsKey(parentPath)) {
            valueByPath.put(path, val);

            triggerCallback(path);

            return true;
        } else {
            return false;
        }
    }

    private void triggerCallback(String path) {
        while (path.length() > 0) {
            if (watchByPath.containsKey(path)) {
                watchByPath.get(path).run();
            }

            int lastSlashIndex = path.lastIndexOf("/");
            path = path.substring(0, lastSlashIndex);
        }
    }


    /**
     * get a value from a path
     * @param path
     * @return
     */
    public Integer get(String path) {
        if (valueByPath.containsKey(path)) {
            return valueByPath.get(path);
        } else {
            return null;
        }
    }

    /**
     * Set a path value
     * @param path
     * @param val
     * @return true(set succesful), false(fail: path does not exist)
     */
    public boolean set(String path, int val) {
        if (valueByPath.containsKey(path)) {
            valueByPath.put(path, val);

            triggerCallback(path);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Add runnable for path
     * @param path
     * @param runnable
     * @return true if path exits otherwise false
     */
    public boolean watch(String path, Runnable runnable) {
        if (valueByPath.containsKey(path)) {
            watchByPath.put(path, runnable);
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        AFileSystem fs = new AFileSystem();
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
