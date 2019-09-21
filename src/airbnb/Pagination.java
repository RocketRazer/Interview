package airbnb;

import java.util.*;

/**
 *
 5
 13
 1,28,310.6,SF
 4,5,204.1,SF
 20,7,203.2,Oakland
 6,8,202.2,SF
 6,10,199.1,SF
 1,16,190.4,SF
 6,29,185.2,SF
 7,20,180.1,SF
 6,21,162.1,SF
 2,18,161.2,SF
 2,30,149.1,SF
 3,76,146.2,SF
 2,14,141.1,San Jose

 以上是一个 Sample 输入，和希望的输出，1,28,100.3,Paris 代表 Host ID, List ID, Points, City.
 这是 Airbnb 根据用户搜索条件得出的一些 list，然后我们要分页，第一行的 5 代表每一页最多展示 5 个 list，13 应该是代表有 13 个 List.所以我们是要分成 3 页。
 规则是:每一页最多展示一个 host 的 list，但是如果再没有其他 host 的 list 可以展示了，就按照原有的顺序填补就可(根据 Points，也 就是排名)。
 应得到的输出:

 希望输出:
 Page 1
 1,28,310.6,SF
 4,5,204.1,SF
 20,7,203.2,Oakland
 6,8,202.2,SF
 7,20,180.1,SF

 Page 2
 6,10,199.1,SF
 1,16,190.4,SF
 2,18,161.2,SF
 3,76,146.2,SF
 6,29,185.2,SF --这时不得不重复了，从原有队列拉出第一个

 Page 3
 6,21,162.1,SF
 2,30,149.1,SF
 2,14,141.1,San Jose

 先找不同 host ID 的 post,填到一页里，如果找不到不同 host ID 的 post，就从开头顺序拉取，填满 为止。就这样简单，不要想太复杂!
 比较直接的做法可以把这些 List 全部放在一个 LinkedList 里，从头到尾扫 另一个 HashSet 记录每一 页的重复值，重复就下一个，没重复就 Remove from the list.
 如果实在找不到不重的，就从头拉一 个即可。
 */
public class Pagination {
    public List<String>displayPages(List<String> input, int pageSize) {
        List<String> res = new ArrayList<>();

        LinkedList<SearchResult> originalResult = new LinkedList<>();
        for (String val : input) {
            originalResult.add(new SearchResult(val));
        }

        while (originalResult.size() > 0) {
            Set<String> idSet = new HashSet<>();
            Iterator<SearchResult> iterator = originalResult.iterator();


            int count = 0;
            while (count < pageSize && iterator.hasNext()) {
                SearchResult cur = iterator.next();
                if (idSet.contains(cur.host_id)) {
                    continue;
                } else {
                    res.add(cur.origialVal);
                    count++;
                    idSet.add(cur.host_id);
                    iterator.remove();
                }
            }


            idSet.clear();
        }


        return res;
    }

    public static void main(String[] args) {
        List<String> input = new ArrayList<>();

        input.add("1,28,310.6,SF");
        input.add("4,5,204.1,SF");
        input.add("20,7,203.2,Oakland");
        input.add("6,8,202.2,SF");
        input.add("6,10,199.1,SF");
        input.add("1,16,190.4,SF");
        input.add("6,29,185.2,SF");
        input.add("7,20,180.1,SF");
        input.add("6,21,162.1,SF");
        input.add("2,18,161.2,SF");
        input.add("2,30,149.1,SF");
        input.add("3,76,146.2,SF");
        input.add("2,14,141.1,San Jose");

        Pagination pagination = new Pagination();
        List<String> res = pagination.displayPages(input, 5);

        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));

            if ((i + 1) % 5 == 0) {
                System.out.println();
                System.out.println("Next Page");
            }
        }

    }

    class SearchResult {
        public String host_id;
        public String origialVal;
        public Double score;

        public SearchResult(String origialVal) {
            String[] valueArr = origialVal.split(",");
            host_id = valueArr[0];
            score = Double.parseDouble(valueArr[2]);
            this.origialVal = origialVal;
        }

        public Double getScore() {
            return score;
        }
    }
}
