import java.util.NoSuchElementException;

public final class MyArrayList<T> {
   // the only necessary variable for the class:
   int DEFAULT_STARTING_SIZE = 10;
   private T[] array;

   // default constructor and one where a capacity can be set:


   public MyArrayList(final int cap) {
      array = removeNulls((T[]) new Object[cap]);
   }

   public MyArrayList() {
      array = removeNulls((T[]) new Object[DEFAULT_STARTING_SIZE]);
   }

   // takes a standard array and returns the same array but with one extra spot at the end:
   private static <T> T[] expandArr(final T[] arr) {
      final T[] newArr = (T[]) new Object[arr.length + 1];
      System.arraycopy(arr, 0, newArr, 0, arr.length);
      return newArr;
   }

   // takes an array and returns the same array but without null values
   // and the values are shifted accordingly:
   private static <T> T[] removeNulls(final T[] arr) {
      final int numNonNulls = getNonNulls(arr);
      final T[] newArray = (T[]) new Object[numNonNulls];
      for (int i = 0, j = 0; i < numNonNulls && j < arr.length; i++) {
         if (arr[i] != null) {
            newArray[i] = arr[j];
            j++;
         }
      }
      return newArray;
   }

   // gets the number of non-null values of an array:
   private static <T> int getNonNulls(final T[] arr) {
      int nonNulls = 0;
      for (final T elem : arr) {
         if (elem != null) {
            nonNulls++;
         }
      }
      return nonNulls;
   }

   // gets the index of a certain value in an array:
   // throws an exception if not found:
   private static <T> int getIndex(final T[] arr, final T val) {
      for (int i = 0; i < arr.length; i++) {
         if (arr[i].equals(val)) {
            return i;
         }
      }
      throw new NoSuchElementException("Element " + val + " was not found in the array");

   }

   // takes two standard arrays and returns a new array which is the result of adding
   // the two arrays together
   private static <T> T[] addArrays(final T[] firstArr, final T[] secondArr) {
      final int newSize = firstArr.length + secondArr.length;
      final T[] newArray = (T[]) new Object[newSize];
      System.arraycopy(firstArr, 0, newArray, 0, firstArr.length);
      for (int i = firstArr.length, j = 0; i < newSize; i++, j++) {
         newArray[i] = secondArr[j];
      }
      return newArray;
   }

   // returns a new array that is a sublist from begin to end non-inclusive:
   private static <T> T[] getSubList(final T[] arr, final int begin, final int end) {
      final int newSize = end - begin;
      final T[] newArr;
      newArr = (T[]) new Object[newSize];
      System.arraycopy(arr, begin, newArr, 0, newSize);
      return newArr;
   }

   // helpful accessor method:
   public int size() {
      return array.length;
   }

   // clears the array:
   public void clear() {
      final int oldSize = array.length;
      array = removeNulls((T[]) new Object[oldSize]);
   }

   // adds a value to the end of the array:
   public boolean add(final T element) {
      array = expandArr(array);
      array[array.length - 1] = element;
      return true;
   }

   // returns the value at a specific index:
   public T get(final int index) {
      checkForValidIndex(index);
      return array[index];
   }

   // checks if the index is out of bounds or not
   // throws an error if it is:
   private void checkForValidIndex(final int index) {
      if (index >= array.length) {
         throw new IndexOutOfBoundsException(
               "Index " + index + " out of bounds for length " + size());
      }
      if (index < 0) {
         throw new IndexOutOfBoundsException("Can't have a negative index for an array");
      }
   }

   // sets a specific index to a specific value:
   public T set(final int index, final T element) {
      checkForValidIndex(index);
      final T result = array[index];
      array[index] = element;
      return result;
   }

   // removes the element at a specific index:
   public T remove(final int index) {
      checkForValidIndex(index);
      final T ret = array[index];
      final T[] subList1 = getSubList(array, 0, index);
      final T[] subList2 = getSubList(array, index + 1, size());
      array = addArrays(subList1, subList2);
      return ret;
   }

   // removes the first occurrence of a specific element in the array
   public boolean remove(final T element) {
      final int index = getIndex(array, element);
      remove(index);
      return true;
   }

   // overrides the toString method:
   @Override
   public String toString() {
      StringBuilder string = new StringBuilder("[");
      for (int i = 0; i < size(); i++) {
         if (i != size() - 1) {
            string.append(array[i]).append(", ");
         } else {
            string.append(array[i]);
         }
      }
      string.append("]");
      return string.toString();
   }
}
