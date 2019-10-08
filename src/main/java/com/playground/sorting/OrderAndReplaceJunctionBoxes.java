package com.playground.sorting;

import java.util.ArrayList;
import java.util.*;

/*
* Amazon Online:
* We need to replace old and retiring junction boxes.
* The junction boxes are provided in the following format [ALPHANUMERIC ID] space separeted by [VERSION]
* The versions are alpha numeric for old junction boxes and numeric for new junction boxes
* for example :
* old boxes : AS2ss little boy, ar21e fat dude
* new boxes : happy 123 234, lucky 4345 6543
*
* The old box versions are lexicographically ordered, meaning box with version apple is older than box with version zen
*
* We need to return a sorted order of devices where,
*  old boxes appears first and new boxes added to the output in the order they are received.
*  If there is a tie on old box versions, we will have to resolve the tie with box names in lexicographical order
*
* Box IDs are unique.
* */
public class OrderAndReplaceJunctionBoxes {

    // Complete the freqQuery function below.
    public static List<String> orderedJunctionBoxes(int numberOfBoxes,
                                             List<String> boxList) {
        // WRITE YOUR CODE HERE
        List<String> newList = new ArrayList<>();
        List<String> oldList = new ArrayList<>();
        for (String s : boxList) {
            String version = s.substring(s.indexOf(" "));
            char c = version.charAt(1);
            if (c >= 48 && c <= 57) {
                newList.add(s);
            } else {
                oldList.add(s);
            }
        }

        oldList.sort((o1, o2) -> {
            String version1 = o1.substring(o1.indexOf(" "));
            String version2 = o2.substring(o2.indexOf(" "));

            int compare = version1.compareTo(version2);
            if (compare != 0) {
                return compare;
            } else {
                String identifier1 = o1.substring(0, o1.indexOf(" "));
                String identifier2 = o2.substring(0, o2.indexOf(" "));
                return identifier1.compareTo(identifier2);
            }
        });
        oldList.addAll(newList);
        return oldList;
    }

    public static void main(String[] args) {
        System.out.println(orderedJunctionBoxes(6,
                Arrays.asList("ykc 82 01", "e0 first qpx", "09z cat hamster", "06f 12 25 6", "az0 first qpx", "236 cat dog rabbit snake")));
    }
}
