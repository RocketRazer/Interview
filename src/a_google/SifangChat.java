package a_google;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Your previous Go content is preserved below:

// To execute Go code, please declare a func main() in a package "main"

package main

import "fmt"

func main() {
  for i := 0; i < 5; i++ {
    fmt.Println("Hello, World!")
  }
}


/*
Your previous Java content is preserved below:

/*

(1), (a), Bicycle. Manhatten Distance
people < bicycles
priority queue (p1-b2, p1-b1, p2-b1)

(b), Leetcode1011, did not say InOrder. Minimum weight of ship for an array of frieght [2,4,4,7,8,7], for n days. N=2, then w=16.
[2,4,4,7], [8,7] w = 17.

Leetcode 410


(2), logging, start(eventId, time) end(eventid, stopTime), log of Id start end. Ranking with start time. Log only one time

id1, startTime1, endTime1
id2, startTime2, endTime2

double linkedList


(id1, 10, null) ->  (id2, 20, null) ->  (id3, 30, null)

(id1, 10, null) ->  (id2, 20, 40) ->  (id3, 30, null)

head -> (id1, 10, 50) ->  (id2, 20, 40) ->  (id3, 30, null) -> tail

log:
(id1, 10, 50)
(id2, 20, 40)

head -> (id3, 30, null) -> tail




(3), leetcode topological sorting


(4), buy and sell stock 1234
stock1: one time
stock2: unlimited
stock3: two time
stock4: k time: dp

(5a), output the events
last 30 days
maintain number1 position for at least continuously 24 hours

ev1, t1  00:00 am      ev1
ev2, t2                ev1
ev1, t3                ev1
ev3, t4                ev1
ev3, t5  24:02 am               ev1      (t5 - t1)
ev3, t6                ev3
ev1, t8
ev1, t9  48:00 am

Map<Event, Integer> count

eventMax = ev3
maxCount = 2
timeFirst: 24:02

(5b), minimum cost to connect cities. 最小生成树
union find + priority queue
electricity
(city1, city2, 10)
(city1, city3, 20)
(city2, city3, 15)

*/
public class SifangChat {

    public static boolean confusingNumber(int N) {
        List<Character> confuseNumber = Arrays.asList('0', '1', '6', '8', '9');
        Set<Character> set = new HashSet(confuseNumber);


        char[] char_arr = (String.valueOf(N)).toCharArray();

        for (char c : char_arr) {
            if (!set.contains(c)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(confusingNumber(6));
    }

}
