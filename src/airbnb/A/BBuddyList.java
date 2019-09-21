package airbnb.A;

/**
 * 我有一个感兴趣的城市wishlist，我的朋友们每个人也有感兴趣的城市wishlist,
 * 如果朋友和我感兴趣的城市占总共他总城市个数的至少一半，那么他就是你的buddy，相同城市越多，similarity越高
 *
 * 1. 按照similarity从高到低把你的buddy排序，输出名字及相同的城市
 * 2. 推荐k个城市，这些城市是不在你的wishlist的城市，从similarity高到低的buddy里面依次找
 *
 * Example
 * Your wishlist: a,b,c,d
 * Buddy1 wishlist: a,b,e,f  (有两个和你的一样，所以是你的buddy)
 * Buddy2 wishlist: a,c,d,g  (有三个和你的一样，也是你的buddy)
 *
 * - k = 10，
 *   buddy1和buddy2的wishlist中不在你的wishlist中的城市都可以加入推荐中，
 *   因为buddy2的重合度更高，
 *   所以先输出buddy2中的，所以推荐为 g,e,f
 *
 * - k = 2，推荐是g,e 或 g,f
 */

import java.util.*;

public class BBuddyList {
    private List<Buddy> buddies;
    private Set<String> myWishList;

    // ouput一个list of travel buddy按相似度从高往低排序
    public BBuddyList(Set<String> myWishList, Map<String, Set<String>> friendsWishList) {
        this.buddies = new ArrayList<>();
        this.myWishList = myWishList;
        for (String name : friendsWishList.keySet()) {
            Set<String> friendWishList = friendsWishList.get(name);
            Set<String> intersection = new HashSet<>(friendWishList);
            intersection.retainAll(myWishList);
            int similarity = intersection.size();
            // 如果你想去的city和另一个人想去的city的相似度高于 50%的话你们就 是travel buddy
            if (similarity >= friendWishList.size() / 2) {
                buddies.add(new Buddy(name, similarity, friendWishList));
            }
        }
    }

    public List<Buddy> getSortedBuddies() {
        Collections.sort(buddies);
        List<Buddy> res = new ArrayList<>(buddies);

        for (Buddy b : res) {
            System.out.print(b.name + " : ");
            for (String city : b.wishList) {
                //if (myWishList.contains(city)) {
                    System.out.print(city + " ");
                //}
            }
            System.out.println();
        }

        return res;
    }

    /**
     *
     *
     followup是给了一个max值，找出你的buddy的wishlist里不在你的wishlist里的最多max个城市，根 据buddy和你的重合程度来排序
     例如 你的wishlist是 a,b,c,d
     buddy1 的wishlist 是 a,b,e,f, 有两个和你的一样，所以是你的buddy buddy2 的wishlist 是 a,c,d,g, 有三个和你的一样，也是你的buddy
     问题是输出一个size最多为max的推荐城市列表 当size为10时，buddy1和buddy2的wishlist中不在你 的wishlist中的城市都可以加入推荐中，
     因为buddy2的重合度更高，所以先输出buddy2中的，所以 推荐为 g,e,f
     当size为2时，推荐是g,e 或 g,f
     *
     *
     */
    public List<String> recommendCities(int k) {
        List<String> res = new ArrayList<>();
        List<Buddy> buddies = getSortedBuddies();

        int i = 0;
        while (k > 0 && i < buddies.size()) {
            Set<String> diff = new HashSet<>(buddies.get(i).wishList);
            diff.removeAll(myWishList);
            //这里要注意 要判断 diff.size() 和 k 的大小关系分情况处理
            if (diff.size() <= k) {
                res.addAll(diff);
                k -= diff.size();
                i++;
            } else {
                Iterator<String> it = diff.iterator();
                while (k > 0) {
                    res.add(it.next());
                    k--;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Set<String> myWishList = new HashSet<>(Arrays.asList(new String[] { "a", "b", "c", "d" }));
        Set<String> wishList1 = new HashSet<>(Arrays.asList(new String[] { "a", "b", "e", "f" }));
        Set<String> wishList2 = new HashSet<>(Arrays.asList(new String[] { "a", "c", "d", "g" }));
        Set<String> wishList3 = new HashSet<>(Arrays.asList(new String[] { "c", "f", "e", "g" }));
        Map<String, Set<String>> friendWishLists = new HashMap<>();
        friendWishLists.put("Buddy1", wishList1);
        friendWishLists.put("Buddy2", wishList2);
        friendWishLists.put("Buddy3", wishList3);
        BBuddyList bl = new BBuddyList(myWishList, friendWishLists);
        System.out.println(bl.recommendCities(10));
        System.out.println(bl.recommendCities(2));
    }

}

class Buddy implements Comparable<Buddy> {
    String name;
    int similarity;
    Set<String> wishList;
    Buddy(String name, int similarity, Set<String> wishList) {
        this.name = name;
        this.similarity = similarity;
        this.wishList = wishList;
    }
    @Override
    public int compareTo(Buddy that) {
        if (this.similarity == that.similarity) {
          return this.name.compareTo(that.name);
        }
        return that.similarity - this.similarity;
    }
}



