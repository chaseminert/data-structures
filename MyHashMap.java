import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class MyHashMap<KEY, VALUE> {
   public static final int STARTING_MAX_SIZE = 4;
   public static final double LOAD_FACTOR = 0.75;

   private int currentMaxSize;
   private int currentSize;
   private LinkedList<Pair<KEY, VALUE>>[] list;


   public MyHashMap() {
      currentMaxSize = STARTING_MAX_SIZE;
      currentSize = 0;
      list = (LinkedList<Pair<KEY, VALUE>>[]) new LinkedList[STARTING_MAX_SIZE];
   }

   public void put(KEY newKey, VALUE newVal) {
       int hashKey = hash(newKey);
       LinkedList<Pair<KEY, VALUE>> old = list[hashKey];

       boolean keyAlreadyExists = false;
       int indexOfKey = -1;

       if (old != null) {
          for (int i = 0; i < old.size(); i++) {
             KEY keyVal = old.get(i).getKey();
             if (keyVal.equals(newKey)) {
                keyAlreadyExists = true;
                indexOfKey = i;
                break;
             }
          }
       }

       if (keyAlreadyExists) {
          old.set(indexOfKey, new Pair<>(newKey, newVal));
       } else {
          list[hashKey] = new LinkedList<Pair<KEY, VALUE>>();
          list[hashKey].add(new Pair<>(newKey, newVal));
          currentSize++;
       }
       double fillRatio = currentSize / (double) currentMaxSize; // how full the hashmap is
       if (fillRatio > LOAD_FACTOR) {
          rehashAll();
       }
   }

   public VALUE get(KEY key) {
      int listIndex = -1;
      int hashKey = hash(key);
      LinkedList<Pair<KEY, VALUE>> itemsList = list[hashKey];
      for (Pair<KEY, VALUE> pair : itemsList) {
         KEY currentKey = pair.getKey();
         if (currentKey.equals(key)) {
            return pair.getValue();
         }
      }
      return null;
   }

   public void remove(KEY key) {
      int hashKey = hash(key);
      LinkedList<Pair<KEY, VALUE>> sublist = list[hashKey];
      for (int i = 0; i < sublist.size(); i++) {
         Pair<KEY, VALUE> pair = sublist.get(i);
         KEY currentKey = pair.getKey();
         if (currentKey.equals(key)) {
            sublist.remove(i);
            return;
         }
      }
   }

   public Set<KEY> keySet() {
      Set<KEY> keySet = new HashSet<>();
      for (LinkedList<Pair<KEY, VALUE>> sublist : list) {
         if (sublist == null) {
            continue;
         }
         for (Pair<KEY, VALUE> pair : sublist) {
            keySet.add(pair.getKey());
         }
      }
      return keySet;
   }

   public String toString() {

      StringBuilder string = new StringBuilder();
      int listLength = list.length;
      System.out.println("list length: " + listLength);
      for (int i = 0; i < list.length; i++) {
         System.out.println("i: " + i);
         LinkedList<Pair<KEY, VALUE>> sublist = list[i];

         if (sublist == null) {
            continue;
         }
         int sublistSize = sublist.size();
         for (int j = 0; j < sublistSize; j++) {
            System.out.println("j: " + j);
            Pair<KEY, VALUE> pair = sublist.get(j);

            string.append(pair.getKey().toString()).append(" -> ")
                  .append(pair.getValue().toString()).append("\n");
         }
      }
      int lastIndex = string.length();
      string.delete(lastIndex-2, lastIndex);
      return string.toString();
   }

   public void debugPrint() {
      for (LinkedList<Pair<KEY, VALUE>> sublist : list) {
         System.out.println(sublist);
      }
   }

   private void rehashAll() {
      currentMaxSize *= 2;
      LinkedList<Pair<KEY, VALUE>>[] newList = (LinkedList<Pair<KEY, VALUE>>[]) new LinkedList[currentMaxSize];
      for (LinkedList<Pair<KEY, VALUE>> sublist : list) {
         if (sublist == null) {
            continue;
         }
         for (Pair<KEY, VALUE> pair : sublist) {
            KEY key = pair.getKey();
            int newHash = hash(key);
            newList[newHash] = new LinkedList<Pair<KEY, VALUE>>();
            newList[newHash].add(pair);
         }
      }
      list = newList;
   }

   private int hash(KEY key) {
      return key.hashCode() % currentMaxSize;
   }


}
