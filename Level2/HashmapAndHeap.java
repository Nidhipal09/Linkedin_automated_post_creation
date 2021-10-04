package Level2;

import java.util.*;

public class HashmapAndHeap {
    public static void noOfEmployees(HashMap<String, String> hm) {
        HashMap<String, HashSet<String>> tree = new HashMap<>();
        String ceo = "";

        for (String emp : hm.keySet()) {
            String man = hm.get(emp);

            if (man.equals(emp))
                ceo = man;
            else {
                if (tree.containsKey(man)) {
                    HashSet<String> emps = tree.get(man);
                    emps.add(emp);
                } else {
                    HashSet<String> emps = new HashSet<>();
                    emps.add(emp);
                    tree.put(man, emps);
                }
            }
        }

        HashMap<String, Integer> ans = new HashMap<>();
        getSize(tree, ceo, ans);

        for (String man : ans.keySet()) {
            System.out.println(man + " " + ans.get(man));
        }
    }

    public static int getSize(HashMap<String, HashSet<String>> tree, String man, HashMap<String, Integer> ans) {
        // store x and return y

        if (!tree.containsKey(man)) {
            ans.put(man, 0);
            return 1;
        }
        int size = 0;
        for (String child : tree.get(man)) {
            size += getSize(tree, child, ans);
        }
        ans.put(man, size);

        return size + 1;

    }

    public static void finditinerary(HashMap<String, String> map) {
        HashMap<String, Boolean> originalSrc = new HashMap<>();

        for (String src : map.keySet()) {
            String dest = map.get(src);
            if (originalSrc.containsKey(src)) {
                originalSrc.put(src, false);
            } else {
                originalSrc.put(src, true);
            }
            originalSrc.put(dest, false);
        }

        String osrc = "";
        for (String src : originalSrc.keySet()) {
            if (originalSrc.get(src)) {
                osrc = src;
                break;
            }
        }

        while (map.containsKey(osrc)) {
            System.out.print(osrc + " -> ");
            osrc = map.get(osrc);
        }

        System.out.print(osrc + ".");
    }

    public static boolean arrayPairsDivisibleByk(int[] arr, int k) {
        HashMap<Integer, Integer> remFreq = new HashMap<>();

        for (int num : arr) {
            int rem = num % k;
            if (remFreq.containsKey(rem)) {
                int freq = remFreq.get(rem);
                remFreq.put(rem, freq + 1);
            } else {
                remFreq.put(rem, 1);
            }
        }

        for (Integer rem : remFreq.keySet()) {
            if (rem == 0 || 2 * rem == k) {
                int freq = remFreq.get(rem);
                if (freq % 2 != 0)
                    return false;
            } else {
                int freq = remFreq.get(rem);
                if (!remFreq.containsKey(k - rem))
                    return false;
                else {
                    int comp = remFreq.get(k - rem);

                    if (freq != comp)
                        return false;
                }
            }
        }

        return true;
    }

    public static ArrayList<Integer> distinctElementsInWindowOfSizek(int[] arr, int k) {
        ArrayList<Integer> ans = new ArrayList<>();

        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < k - 1; i++) {
            if (hm.containsKey(arr[i])) {
                int freq = hm.get(arr[i]);
                hm.put(arr[i], freq + 1);
            } else {
                hm.put(arr[i], 1);
            }
        }

        for (int i = k - 1, j = 0; i < arr.length; i++, j++) { // aquire print release
            if (hm.containsKey(arr[i])) { // acquire
                int freq = hm.get(arr[i]);
                hm.put(arr[i], freq + 1);
            } else {
                hm.put(arr[i], 1);
            }
            ans.add(hm.size()); // print

            if (hm.get(arr[j]) > 1) { // release
                hm.put(arr[j], hm.get(arr[j]) - 1);
            } else
                hm.remove(arr[j]);

        }

        return ans;
    }

    public static int largestSubarrayWith0Sum(int[] arr) {
        int index = -1;
        int sum = 0;
        int maxlength = 0;

        HashMap<Integer, Integer> map = new HashMap<>(); // sum -> index
        map.put(0, -1);

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            if (map.containsKey(sum)) {
                int length = i - map.get(sum);
                maxlength = Math.max(length, maxlength);
            } else
                map.put(sum, i);
        }

        return maxlength;
    }

    public static int allSubarraysWith0Sum(int[] arr) {

        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>(); // sum -> count
        map.put(0, 1);
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            if (map.containsKey(sum)) {
                int freq = map.get(sum);
                count += freq;
                map.put(sum, freq + 1);
            } else
                map.put(sum, 1);
        }

        return count;
    }

    public static int largestSubarrayWithContigousElements(int[] arr) {
        int maxlength = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];
            int max = arr[i];

            HashSet<Integer> set = new HashSet<>();
            set.add(arr[i]);

            for (int j = i + 1; j < arr.length; j++) {
                if (set.contains(arr[j]))
                    break;
                set.add(arr[j]);

                max = Math.max(arr[j], max);
                min = Math.min(arr[j], min);

                if ((max - min) == j - i) {
                    int length = (j - i + 1);
                    maxlength = Math.max(length, maxlength);
                }
            }
        }
        return maxlength;
    }

    public static String smallestSSOfAStringContainingAnotherString(String s1, String s2) {

        String ans = "";
        HashMap<Character, Integer> map2 = new HashMap<>();
        for (int k = 0; k < s2.length(); k++) {
            char ch = s2.charAt(k);
            map2.put(ch, map2.getOrDefault(ch, 0) + 1);
        }

        int matchCount = 0, desiredMatchCount = s2.length();
        HashMap<Character, Integer> map1 = new HashMap<>();

        int i = -1, j = -1; // i in releasing j in aquiring

        while (true) {
            boolean flag1 = false;
            boolean flag2 = false;

            // acquire
            while (j < s1.length() - 1 && matchCount < desiredMatchCount) {
                j++;
                char ch = s1.charAt(j);
                map1.put(ch, map1.getOrDefault(ch, 0) + 1);

                if (map1.getOrDefault(ch, 0) <= map2.getOrDefault(ch, 0)) {
                    matchCount++;
                }

                flag1 = true;
            }

            // collect answers and release
            while (i < j && matchCount == desiredMatchCount) {
                String potentialAns = s1.substring(i + 1, j + 1);
                if (ans.length() == 0 || potentialAns.length() < ans.length())
                    ans = potentialAns;
                i++;

                char ch = s1.charAt(i);
                if (map1.get(ch) == 1)
                    map1.remove(ch);
                else
                    map1.put(ch, map1.get(ch) - 1);

                if (map1.getOrDefault(ch, 0) < map2.getOrDefault(ch, 0))
                    matchCount--;

                flag2 = true;
            }

            if (flag1 == false && flag2 == false) {
                break;
            }

        }

        return ans;
    }

    public static int smallestSSOfAStringContainingAllUniqueCharactersOfItself(String s) {

        int len = s.length();
        HashSet<Character> unique = new HashSet<>();
        for (int k = 0; k < s.length(); k++) {
            char ch = s.charAt(k);
            unique.add(ch);
        }

        HashMap<Character, Integer> map = new HashMap<>();

        int i = -1, j = -1; // i in releasing j in aquiring

        while (true) {
            boolean flag1 = false;
            boolean flag2 = false;

            // acquire
            while (j < s.length() - 1 && map.size() < unique.size()) {
                j++;
                char ch = s.charAt(j);
                map.put(ch, map.getOrDefault(ch, 0) + 1);

                flag1 = true;
            }

            // collect answers and release
            while (i < j && map.size() == unique.size()) {
                int potlen = j - i;
                if (potlen < len)
                    len = potlen;

                i++;

                char ch = s.charAt(i);
                if (map.get(ch) == 1)
                    map.remove(ch);
                else
                    map.put(ch, map.get(ch) - 1);

                flag2 = true;
            }

            if (flag1 == false && flag2 == false) {
                break;
            }

        }

        return len;
    }

    public static int LongestSubstringwithoutRepeatingCharacters(String s) {

        int len = 0;

        HashMap<Character, Integer> map = new HashMap<>();

        int i = -1, j = -1; // i in releasing j in aquiring

        while (true) {
            boolean flag1 = false;
            boolean flag2 = false;

            // acquire
            while (j < s.length() - 1) {
                flag1 = true;
                j++;
                char ch = s.charAt(j);
                map.put(ch, map.getOrDefault(ch, 0) + 1);

                if (map.get(ch) == 2)
                    break;
                else {
                    int potlen = j - i;
                    if (potlen > len)
                        len = potlen;
                }

            }

            // collect answers and release
            while (i < j) {
                flag2 = true;
                i++;

                char ch = s.charAt(i);
                map.put(ch, map.get(ch) - 1);

                if (map.get(ch) == 1)
                    break;

            }

            if (flag1 == false && flag2 == false) {
                break;
            }

        }

        return len;
    }

    public static int CountOfSubstringWithAllUniqueCharacters(String s) {

        int count = 0;

        HashMap<Character, Integer> map = new HashMap<>();

        int i = -1, j = -1; // i in releasing j in aquiring

        while (true) {
            boolean flag1 = false;
            boolean flag2 = false;

            // acquire
            while (j < s.length() - 1) {
                flag1 = true;

                j++;
                char ch = s.charAt(j);
                map.put(ch, map.getOrDefault(ch, 0) + 1);

                if (map.get(ch) == 2)
                    break;
                else {
                    count += j - i;
                }

            }

            // collect answers and release
            while (i < j) {
                flag2 = true;
                i++;

                char ch = s.charAt(i);
                map.put(ch, map.get(ch) - 1);

                if (map.get(ch) == 1) {
                    count += j - i;
                    break;
                }

            }

            if (flag1 == false && flag2 == false) {
                break;
            }

        }

        return count;
    }

    public static int LongestSubstringwithKExactlyUniqueCharacters(String s, int k) {

        int len = 0;

        HashMap<Character, Integer> map = new HashMap<>();

        int i = -1, j = -1; // i in releasing j in aquiring

        while (true) {
            boolean flag1 = false;
            boolean flag2 = false;

            // acquire
            while (j < s.length() - 1) {
                flag1 = true;
                j++;
                char ch = s.charAt(j);
                map.put(ch, map.getOrDefault(ch, 0) + 1);

                if (map.size() == k) {
                    int potlen = j - i;
                    if (potlen > len)
                        len = potlen;
                } else if (map.size() > k)
                    break;

            }

            // collect answers and release
            while (i < j) {
                flag2 = true;
                i++;

                char ch = s.charAt(i);
                if (map.get(ch) == 1)
                    map.remove(ch);
                else
                    map.put(ch, map.get(ch) - 1);

                if (map.size() == k) {
                    int potlen = j - i;
                    if (potlen > len)
                        len = potlen;
                    break;
                }

            }

            if (flag1 == false && flag2 == false) {
                break;
            }

        }

        return len;
    }

    public static int CountOfSubstringwithKExactlyUniqueCharacters(String s, int k) {

        int count = 0;

        HashMap<Character, Integer> mapb = new HashMap<>(); // for k
        HashMap<Character, Integer> maps = new HashMap<>(); // for k-1

        int i = -1, js = -1, jb = -1; // i in releasing j in aquiring

        while (true) {
            boolean flag1 = false;
            boolean flag2 = false;
            boolean flag3 = false;

            // acquire
            while (jb < s.length() - 1) {
                flag1 = true;
                jb++;
                char ch = s.charAt(jb);
                mapb.put(ch, mapb.getOrDefault(ch, 0) + 1);

                if (mapb.size() == k + 1) {
                    removeInMap(mapb, ch);
                    jb--;
                    break;
                }
            }

            // collect answers and release
            while (js < jb) {
                flag2 = true;
                js++;

                char ch = s.charAt(js);
                maps.put(ch, maps.getOrDefault(ch, 0) + 1);

                if (maps.size() == k) {
                    removeInMap(maps, ch);
                    js--;
                    break;
                }

            }

            while (i < js) {
                flag3 = true;

                if (mapb.size() == k && maps.size() == k - 1) {
                    count += jb - js;
                }

                i++;
                char ch = s.charAt(i);
                removeInMap(maps, ch);
                removeInMap(mapb, ch);

                if (maps.size() < k - 1 || mapb.size() < k)
                    break;

            }

            if (flag1 == false && flag2 == false && flag3 == false) {
                break;
            }

        }

        return count;
    }

    public static void removeInMap(HashMap<Character, Integer> map, char ch) {
        if (map.get(ch) == 1)
            map.remove(ch);
        else
            map.put(ch, map.get(ch) - 1);
    }

    public static int countOfEquivalentSubArrays(int[] arr) {

        int count = 0;

        HashSet<Integer> unique = new HashSet<>();
        for (int k = 0; k < arr.length; k++) {
            unique.add(arr[k]);
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        int i = -1, j = -1; // i in releasing j in aquiring

        while (true) {
            boolean flag1 = false;
            boolean flag2 = false;

            // acquire
            while (j < arr.length - 1) {
                flag1 = true;

                j++;
                int n = arr[j];
                map.put(n, map.getOrDefault(n, 0) + 1);

                if (map.size() == unique.size()) {
                    count += arr.length - j;
                    break;
                }

            }

            // collect answers and release
            while (i < j) {
                flag2 = true;

                i++;

                int n = arr[i];
                if (map.get(n) == 1)
                    map.remove(n);
                else
                    map.put(n, map.get(n) - 1);

                if (map.size() == unique.size()) {
                    count += arr.length - j;
                } else
                    break;

            }

            if (flag1 == false && flag2 == false) {
                break;
            }

        }

        return count;
    }

    public static int maxConsecutiveOnes(int[] arr, int k) {
        int len = 0;

        int count0 = 0;
        int i = -1;

        for (int j = 0; j < arr.length; j++) {
            if (arr[j] == 0)
                count0++;

            while (count0 > k) {
                i++;
                if (arr[i] == 0)
                    count0--;
            }

            int potlen = j - i;
            if (potlen > len)
                len = potlen;
        }
        return len;
    }

    public static int maxConsecutiveOnes1(int[] arr) {
        int len = 0;

        int count0 = 0;
        int i = -1;

        for (int j = 0; j < arr.length; j++) {
            if (arr[j] == 0)
                count0++;

            while (count0 > 1) {
                i++;
                if (arr[i] == 0)
                    count0--;
            }

            int potlen = j - i;
            if (potlen > len)
                len = potlen;
        }
        return len;
    }

    public static int LongestSubstringwithAtMostkUniqueChar(String s, int k) {

        int len = 0;

        HashMap<Character, Integer> map = new HashMap<>();

        int i = -1, j = -1; // i in releasing j in aquiring

        while (true) {
            boolean flag1 = false;
            boolean flag2 = false;

            // acquire
            while (j < s.length() - 1) {
                flag1 = true;
                j++;
                char ch = s.charAt(j);
                map.put(ch, map.getOrDefault(ch, 0) + 1);

                if (map.size() <= k) {
                    int potlen = j - i;
                    if (potlen > len)
                        len = potlen;
                } else {
                    break;
                }

            }

            // collect answers and release
            while (i < j) {
                flag2 = true;
                i++;

                char ch = s.charAt(i);
                if (map.get(ch) == 1)
                    map.remove(ch);
                else
                    map.put(ch, map.get(ch) - 1);

                if (map.size() <= k) {
                    int potlen = j - i;
                    if (potlen > len)
                        len = potlen;
                    break;
                }

            }

            if (flag1 == false && flag2 == false) {
                break;
            }

        }

        return len;
    }

    public static int countOfSubstringwithAtMostkUniqueChar(String s, int k) {

        int count = 0;

        HashMap<Character, Integer> map = new HashMap<>();

        int i = -1, j = -1; // i in releasing j in aquiring

        while (true) {
            boolean flag1 = false;
            boolean flag2 = false;

            // acquire
            while (j < s.length() - 1) {
                flag1 = true;
                j++;
                char ch = s.charAt(j);
                map.put(ch, map.getOrDefault(ch, 0) + 1);

                if (map.size() <= k) {
                    count += j - i;

                } else {
                    break;
                }

            }

            if (j == s.length() - 1 && map.size() <= k)
                break;

            // collect answers and release
            while (i < j) {
                flag2 = true;
                i++;

                char ch = s.charAt(i);
                if (map.get(ch) == 1)
                    map.remove(ch);
                else
                    map.put(ch, map.get(ch) - 1);

                if (map.size() <= k) {
                    count += j - i;

                    break;
                }

            }

            if (flag1 == false && flag2 == false) {
                break;
            }

        }

        return count;
    }

    public static int countSubarraysWithkSum(int[] arr, int k) {

        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>(); // sum -> count
        map.put(0, 1);
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);

            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }

    public static void findAnagrams(String s, String p) {
        HashMap<Character, Integer> pmap = new HashMap<>();

        for (int i = 0; i < p.length(); i++) {
            char ch = p.charAt(i);
            pmap.put(ch, pmap.getOrDefault(ch, 0) + 1);
        }

        HashMap<Character, Integer> smap = new HashMap<>();

        for (int i = 0; i < p.length(); i++) {
            char ch = s.charAt(i);
            smap.put(ch, smap.getOrDefault(ch, 0) + 1);
        }

        int i = p.length();
        int count = 0;

        String ans = "";
        while (i < s.length()) {
            if (compare(pmap, smap)) {
                count++;
                ans += (i - p.length()) + " ";
            }
            char cha = s.charAt(i);
            smap.put(cha, smap.getOrDefault(cha, 0) + 1);

            char chr = s.charAt(i - p.length());
            if (smap.get(chr) == 1)
                smap.remove(chr);
            else
                smap.put(chr, smap.get(chr) - 1);

            i++;

        }
        if (compare(pmap, smap)) {
            count++;
            ans += (i - p.length()) + " ";
        }
        System.out.println(count);
        System.out.println(ans);
    }

    public static boolean compare(HashMap<Character, Integer> map1, HashMap<Character, Integer> map2) {
        for (Character ch : map1.keySet()) {
            int freq = map1.get(ch);
            if (!map2.containsKey(ch) || map2.get(ch) != freq)
                return false;
        }
        return true;
    }

    public static boolean areKAnagrams(String s1, String s2, int k) {
        if (s1.length() != s2.length())
            return false;

        HashMap<Character, Integer> map1 = new HashMap<>();

        for (int i = 0; i < s1.length(); i++) {
            char ch = s1.charAt(i);
            map1.put(ch, map1.getOrDefault(ch, 0) + 1);
        }

        for (int i = 0; i < s2.length(); i++) {
            char ch = s2.charAt(i);
            if (map1.containsKey(ch) && map1.get(ch) > 0)
                map1.put(ch, map1.get(ch) - 1);
        }

        int count = 0;
        for (char ch : map1.keySet())
            count += map1.get(ch);

        return count <= k;
    }

    public static int[] anagramMappings(int[] arr1, int[] arr2) {
        int n=arr1.length;
		int[] ans=new int[n];
        
        HashMap<Integer, Pair> map2 = new HashMap<>();
        for(int i=0; i<arr2.length; i++){  
            int val = arr2[i];
            if(map2.containsKey(val)){
                Pair p=map2.get(val);
                p.al.add(i);
            }else{
                Pair p=new Pair();
                p.al.add(i);
                map2.put(val, p);
            }
        }

        for(int i=0; i<arr1.length; i++){
            int val=arr1[1];
            Pair p= map2.get(val);
            p.idx
        }

		return ans;
	}

    public static class Pair {
        int idx;
        ArrayList<Integer> al;

    }

    public static boolean validAnagrams(String s1, String s2) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s1.length(); i++) {
            char ch = s1.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for (int i = 0; i < s2.length(); i++) {
            char ch = s2.charAt(i);

            if (!map.containsKey(ch))
                return false;
            if (map.get(ch) == 1)
                map.remove(ch);
            else
                map.put(ch, map.get(ch) - 1);
        }

        if (map.size() == 0)
            return true;
        return false;
    }

    public static ArrayList<ArrayList<String>> groupAnagrams(String[] strs) {

        HashMap<HashMap<Character, Integer>, ArrayList<String>> map = new HashMap<>();

        for (String s : strs) {
            HashMap<Character, Integer> amap = new HashMap<>();

            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                amap.put(ch, amap.getOrDefault(ch, 0) + 1);
            }

            if (map.containsKey(amap)) {
                map.get(amap).add(s);
            } else {
                ArrayList<String> al = new ArrayList<>();
                al.add(s);
                map.put(amap, al);
            }
        }

        ArrayList<ArrayList<String>> ans = new ArrayList<>();
        for (ArrayList<String> a : map.values()) {
            ans.add(a);
        }

        return ans;
    }

    public static ArrayList<ArrayList<String>> groupShiftedStrings(String[] strs) {

        HashMap<String, ArrayList<String>> map = new HashMap<>();

        for (String s : strs) {

            String key = getKey(s);

            if (map.containsKey(key)) {
                map.get(key).add(s);
            } else {
                ArrayList<String> al = new ArrayList<>();
                al.add(s);
                map.put(key, al);
            }
        }

        ArrayList<ArrayList<String>> ans = new ArrayList<>();
        for (ArrayList<String> a : map.values()) {
            ans.add(a);
        }

        return ans;
    }

    public static String getKey(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < s.length(); i++) {
            char ch1 = s.charAt(i);
            char ch2 = s.charAt(i - 1);
            int k = ch1 - ch2;
            if (k < 0)
                k += 26;
            sb.append(k + "#"); // not k becoz 1#2 != 12
        }

        return sb.toString();
    }

    public static boolean isIsomorphic(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;

        HashMap<Character, Character> maps = new HashMap<>();
        HashMap<Character, Boolean> mapb = new HashMap<>();

        for (int i = 0; i < s1.length(); i++) {
            char ch1 = s1.charAt(i);
            char ch2 = s2.charAt(i);

            if (!maps.containsKey(ch1)) {
                if (mapb.containsKey(ch2))
                    return false;

                maps.put(ch1, ch2);
                mapb.put(ch2, true);
            } else {
                if (maps.get(ch1) != ch2)
                    return false;

            }
        }

        return true;
    }

    public static boolean wordPattern(String pattern, String s) {

        String[] words = s.split(" ");

        if (pattern.length() != words.length)
            return false;

        HashMap<Character, String> maps = new HashMap<>();
        HashMap<String, Boolean> mapb = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            String word = words[i];

            if (!maps.containsKey(ch)) {
                if (mapb.containsKey(word))
                    return false;

                maps.put(ch, word);
                mapb.put(word, true);
            } else {
                if (!maps.get(ch).equals(word))
                    return false;

            }
        }

        return true;
    }

    public static int longestSubArrayWithSumDivisiblebyk(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int sum = 0, r = 0, maxlen = 0;
        map.put(0, -1); // rem-index

        for (int i = 0; i < arr.length; i++) {
            int x = arr[i];

            sum += x;
            int rem = sum % k;
            if (rem < 0)
                rem += k;

            if (!map.containsKey(rem))
                map.put(rem, i);
            else {
                int len = i - map.get(rem);
                maxlen = Math.max(maxlen, len);
            }

        }

        return maxlen;
    }

    public static int countSubarrayswithsumdivisiblebyk(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int sum = 0, r = 0, count = 0;
        map.put(0, 1); // rem-count

        for (int i = 0; i < arr.length; i++) {
            int x = arr[i];

            sum += x;
            int rem = sum % k;
            if (rem < 0)
                rem += k;

            if (!map.containsKey(rem))
                map.put(rem, 1);
            else {
                count += map.get(rem);
                map.put(rem, map.get(rem) + 1);
            }

        }

        return count;
    }

    public static int longestSubarrayWithEqualNoOf0and1(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int sum = 0, maxlen = 0;
        map.put(0, -1); // sum-index

        for (int i = 0; i < arr.length; i++) {
            int x = arr[i];

            if (x == 0)
                sum += -1;
            else
                sum += 1;

            if (!map.containsKey(sum))
                map.put(sum, i);
            else {
                int len = i - map.get(sum);
                maxlen = Math.max(maxlen, len);
            }

        }

        return maxlen;

    }

    public static int countOfSubarraysWithEqualNoOf0and1(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int sum = 0, count = 0;
        map.put(0, 1); // sum-count

        for (int i = 0; i < arr.length; i++) {
            int x = arr[i];

            if (x == 0)
                sum += -1;
            else
                sum += 1;

            if (!map.containsKey(sum))
                map.put(sum, 1);
            else {
                count += map.get(sum);
                map.put(sum, map.get(sum) + 1);
            }

        }

        return count;
    }

    public static int longestSubarrayWithEqualNoOf01ans2(int[] arr) {

        int count0 = 0, count1 = 0, count2 = 0, maxlen = 0;

        HashMap<String, Integer> map = new HashMap<>();

        map.put("0#0", -1); // count1-count0 # count2-count1,index

        for (int i = 0; i < arr.length; i++) {
            int x = arr[i];

            if (x == 0)
                count0 += 1;
            else if (x == 1)
                count1 += 1;
            else
                count2 += 1;

            int d1 = count1 - count0, d2 = count2 - count1;

            String key = "" + d1 + "#" + d2;

            if (!map.containsKey(key))
                map.put(key, i);
            else {
                int len = i - map.get(key);
                maxlen = Math.max(maxlen, len);
            }

        }

        return maxlen;
    }

    public static int countOfSubarraysWithEqualNoOf01and2(int[] arr) {
        int count0 = 0, count1 = 0, count2 = 0, count = 0;

        HashMap<String, Integer> map = new HashMap<>();

        map.put("0#0", 1); // count1-count0 # count2-count1,index

        for (int i = 0; i < arr.length; i++) {
            int x = arr[i];

            if (x == 0)
                count0 += 1;
            else if (x == 1)
                count1 += 1;
            else
                count2 += 1;

            int d1 = count1 - count0, d2 = count2 - count1;

            String key = "" + d1 + "#" + d2;

            if (!map.containsKey(key))
                map.put(key, 1);
            else {
                count += map.get(key);
                map.put(key, map.get(key) + 1);
            }

        }

        return count;
    }

    public static boolean pairsWithEqualSum(int[] arr) {

        int sum = 0;
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                sum = arr[i] + arr[j];
                if (set.contains(sum))
                    return true;
                else
                    set.add(sum);
            }
        }

        return false;
    }

    //practice
    public static boolean pairWithtarSum(int[] arr, int tar) {
  
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {
           
            if (set.contains(tar - arr[i]))
                return true;
            else
                set.add(arr[i]);
           
        }

        return false;
    }

    public static String numanddeno(int num, int den) {
        StringBuilder ans = new StringBuilder();
        HashMap<Integer, Integer> map = new HashMap<>();
         // rem, index
        int flag=0;
        
        while(true){
            
            int rem = num % den;
            int div = num / den;
            
            if(rem==0){
                ans.append(div);
                break;
            } 
            else{
                if(flag == 1){
                    if(map.containsKey(rem)){
                        ans.append(")");
                        int index = map.get(rem);
                        ans.insert(index, "(");
                        break;
                    }
                    map.put(rem, ans.length());
                }
                ans.append(div);
                
                
                if(flag==0){
                  ans.append(".");
                  flag = 1;
                }
                num = rem;
                num *= 10;
            }
        }
        
        return ans.toString();
    }

    public static String numanddeno1(int num, int den) {
        StringBuilder ans = new StringBuilder();
        
        int rem = num % den;
        int q = num / den;
        ans.append(q);
        
        if(rem!=0){
            ans.append(".");
            
            HashMap<Integer, Integer> map = new HashMap<>(); // rem, index
            
            while(rem!=0){
                if(map.containsKey(rem)){
                    int index = map.get(rem);
                    ans.insert(index, "(");
                    ans.append(")");
                    break;
                }
                else{
                   map.put(rem, ans.length());
                   rem *= 10;
                   q = rem / den;
                   rem = rem % den;
                   ans.append(q);
                }
            }
        }
        
        return ans.toString();
    }
    
    public static int rabbits(int[] arr) {
        int count = 0;
        
         HashMap<Integer, Integer> map = new HashMap<>(); 
        for(int i=0; i<arr.length; i++){
            int x = arr[i], y=x+1;
            
            if(map.containsKey(y)){
                int value=map.get(y);
                if(value == y){
                    map.remove(y);
                    map.put(y,1);
                    count += y;
                }
                else map.put(y,map.get(y)+1);
            }
            else{
                map.put(y, 1);
            }
        }
        
        for(int key :map.keySet()){
            count += key;
        }
        return count;
    }

    public static int rabbits1(int[] arr) {
        int count = 0;
        
        HashMap<Integer, Integer> map = new HashMap<>(); 
        for(int i=0; i<arr.length; i++){
            int x = arr[i];
            map.put(x, map.getOrDefault(x,0)+1);
        }
        
        for(int key :map.keySet()){
            int gs = key+1;  // group size
            int reportees = map.get(key);
            int ng = (int)Math.ceil(reportees*1.0/gs*1.0); // no of groups
            count += ng * gs;
        }
        
        return count;
    }

    public static boolean arithmeticExpression(int[] arr) {
        
        if(arr.length == 1) return true;
        
        HashSet<Integer> set = new HashSet<>();                 
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for(int x: arr){
           if(x < min) min=x;
           if(x > max) max=x;
           
           set.add(x);
        }
        
        int d = (max-min)/(arr.length-1);
        
        for(int i=0; i<arr.length; i++){
            int val = min + i*d;
            if(!set.contains(val)) return false;
        }
        
        return true;
    }

    public static void smallestSubarrayWithHFE(int[] arr) {
        
        HashMap<Integer, Integer> fmap = new HashMap<>();  // freq
        HashMap<Integer, Integer> fomap = new HashMap<>();  // first occurrence
        
        int hf=0, ei=0, si=0, len=1;
        
        for(int i=0; i<arr.length; i++){
            int x = arr[i];
            
            if(fmap.containsKey(x)){
                fmap.put(x, fmap.get(x)+1);
            }
            else{
                fmap.put(x,1);
                fomap.put(x,i);
            }
            
            if(fmap.get(x) > hf){
                hf = fmap.get(x);
                si = fomap.get(x);
                ei = i;
                len = ei-si+1;
            }
            else if(fmap.get(x) == hf){
                int length = i-fomap.get(x)+1;
                if(length<len){
                    hf = fmap.get(x);
                    si = fomap.get(x);
                    ei = i;
                    len = ei-si+1;
                }
            }
            
        }
        
        System.out.println(arr[si]);
        System.out.println(si);
        System.out.println(ei);
    }

    public static void completeTask(int n, int m, int[] arr) {
		
		HashSet<Integer> set = new HashSet<>();
		ArrayList<Integer> set1 = new ArrayList<>();
		ArrayList<Integer> set2 = new ArrayList<>();
		
		for(int x:arr) set.add(x);
		
		boolean flag=true;
		
		for(int i=1; i<=n; i++){
		    if(!set.contains(i)){
		        if(flag){
		          set1.add(i);
		        } 
		        else{
		            set2.add(i);
		        }
                flag = !flag;
		    }
		}
		
		for(int x:set1) System.out.print(x+" ");
		System.out.println();
		for(int x:set2) System.out.print(x+" ");

	}

    public static int PairsWithGivenSumInTwoSortedMatrices(int[][] num1, int[][] num2, int k) {
		int count =0;
		HashMap<Integer, Integer> map = new HashMap<>();
		
		for(int i=0; i<num1.length; i++){
		   for(int j=0; j<num1.length; j++){
		      map.put(num1[i][j], map.getOrDefault(num1[i][j],0)+1);
		   } 
		}
		
		for(int i=0; i<num2.length; i++){
		   for(int j=0; j<num2.length; j++){
		      int comp = k - num2[i][j];
		      
		      if(map.containsKey(comp)){
		        count += map.get(comp);    
		      }
		   } 
		}
		

		return count;
	}
    
    public static int firstnonrepeatingChar(String s) {
		HashMap<Character, Integer> map = new HashMap<>();
		
		for(int i=0; i<s.length(); i++){
		      char ch = s.charAt(i);
		      map.put(ch, map.getOrDefault(ch,0)+1);
		   
		}
		
		for(int i=0; i<s.length(); i++){
		   char ch = s.charAt(i);
		   int freq = map.get(ch);
		   if(freq == 1) return i;
		}
		

		return -1;
	 }

     



}
