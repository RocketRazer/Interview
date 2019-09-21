package a_google;


import java.util.*;

public class TimeMap {
//    Map<String, TreeMap<Integer, String>> map;
//
//    /** Initialize your data structure here. */
//    public TimeMap() {
//        map = new HashMap<>();
//
//    }
//
//    public void set(String key, String value, int timestamp) {
//        if (!map.containsKey(key)) {
//            map.put(key, new TreeMap<>());
//        }
//
//        map.get(key).put(timestamp, value);
//    }
//
//    public String get(String key, int timestamp) {
//        if (!map.containsKey(key)) return null;
//
//        TreeMap<Integer, String> treeMap = map.get(key);
//        Integer floorKey = treeMap.floorKey(timestamp);
//        return floorKey == null ? null : treeMap.get(floorKey);
//
//    }
//}


    Map<String, List<Data>> map;

    /** Initialize your data structure here. */
    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<Data>());
        }

        map.get(key).add(new Data(value, timestamp));
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) return "";

        return binarySearch(map.get(key), timestamp);
    }

    protected String binarySearch(List<Data> list, int time) {
        int low = 0, high = list.size() - 1;
        while (low < high) {
            int mid = (low + high) >> 1;
            if (list.get(mid).time == time) return list.get(mid).val;
            if (list.get(mid).time < time) {
                if (list.get(mid+1).time > time) return list.get(mid).val;
                low = mid + 1;
            }
            else high = mid -1;
        }
        return list.get(low).time <= time ? list.get(low).val : "";
    }

     private String binarySearch2(List<Data> list, int timestamp) {
         if (list == null || list.size() == 0) return "";

         int l = 0, r = list.size() - 1;
         while (l + 1 < r) {
             int m = (r - l) / 2 + l;

             Data mid = list.get(m);

             if (mid.time == timestamp) {
                 return mid.val;
             } else if (timestamp > mid.time) {
                 l = m;
             } else {
                 r = m;
             }
         }

         if (list.get(r).time <= timestamp) {
             return list.get(r).val;
         } else if (list.get(l).time <= timestamp) {
             return list.get(l).val;
         } else {
             return "";
         }
     }
}

class Data {
    int time;
    String val;

    public Data(String val, int timestamp) {
        this.val = val;
        this.time = timestamp;
    }
}


/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */