import java.util.*;
import java.io.*;
import java.text.*;

public class myCode {
  // initialise variable for use in merge sort
  public static int movesVar = 0;
  public static void main (String[] args) throws IOException{
    ArrayList<String> nonSWList = deleteStopWords("input.txt","stopwords.txt");
    insertionSort(nonSWList);
    System.out.println("MERGE SORTED LIST");
    System.out.println("=====================");
    // ArrayList<String> mergeSortedList = new ArrayList<>();
    // mergeSortedList.addAll(nonSWList);
    int amount = nonSWList.size();
    for (int size : new int []{100,200,amount}){
      ArrayList<String> mergeSortedList = new ArrayList<>(nonSWList.subList(0,size));
      long mergeStart = System.nanoTime();
      mergeSort(mergeSortedList);
      long mergeStop = System.nanoTime();
      double mergeTime = mergeStop - mergeStart ;
      mergeTime = mergeTime / 1000000000;//seconds
      //creates formart for decimal numbers so that they do now represent as 1.2345E4
      DecimalFormat df = new DecimalFormat("#.###############");
      System.out.println("Time taken for " + size + " words : " + (df.format(mergeTime)) + "s" );
    }
    //mergeSortedList = mergeSort(mergeSortedList);
    System.out.println("Moves : " + movesVar);
    System.out.println("=====================");
  }

  public static ArrayList<String> deleteStopWords(String inputFile, String stopWordsFile) throws IOException{
  //reads stop-words file and adds all items to array
    ArrayList<String> SWList = new ArrayList<>();
    BufferedReader swReader = new BufferedReader(new FileReader(stopWordsFile));
    String line;
    while ((line = swReader.readLine()) != null){
      SWList.add(line);
    }
    swReader.close();
//reads input file and adds all words to an array in lowercase with punctuation
    ArrayList<String> inputList = new ArrayList<>();
    BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
    while((line = inputReader.readLine()) != null){
      String[] placeHolder = line.split("\\s+");
      for (String place : placeHolder) {
        inputList.add(place.toLowerCase());
      }
      line = inputReader.readLine();
    }
    inputReader.close();
//creates new array list to add to after comparing two previous arrays to find non-stop words
    ArrayList<String> nonSWList = new ArrayList<>();
    for (String item : inputList ) {
      if (!SWList.contains(item)) {
        nonSWList.add(item);
      }
    }
    return nonSWList;
  }
  // insertion sort pseudocode
  // for i =1 (n-1) do
  //   item = A[i]
  //   j = i - 1
  //   while j >= 0 AND A [j] = item do
  //     A[j+1] < A[j]
  //     j = j -1
  //   A[j+1] < item
  public static void insertionSort(ArrayList<String> nonSWList){
    // initialise and copy contents from un-sorted list to another list so intial list can be used again for next sort
    ArrayList<String> insertionSortedList = new ArrayList<>();
    insertionSortedList.addAll(nonSWList);
    int amount = nonSWList.size();
    //intialise variables* for calculating amount of words/ counter
    //500 included in the list as it is required but file without stop words is <500words
    int[] numbers = {100,200,500};
    int numIndex = 0;
    int wordsSorted = 0;
    int moves = 0;
    //output with border to make it presentable
    System.out.println("INSERTION SORTED LIST");
    System.out.println("=====================");
    // initialises and starts times of insertion sort
    long insertionStart = System.nanoTime();
    //insetion sort algorithm start
    for (int i = 1; i < insertionSortedList.size() ;i++ ) {
      // store current word in temporary holding variable
      String hold = insertionSortedList.get(i);
      int j = i-1;
      //moves word greater than hold to right side
      while(j >= 0 && hold.compareTo(insertionSortedList.get(j)) < 0){
        insertionSortedList.set(j+1, insertionSortedList.get(j));
        j--;
      }
      //insert word into the correct position
      insertionSortedList.set(j+1, hold);
      moves++;
      //output time taken for 100,200, 500 words (not 500 words in file)
      if (wordsSorted == numbers[numIndex]) {
        double insertionTime = System.nanoTime()-insertionStart;
        insertionTime = insertionTime / 1_000_000_000.0;
        System.out.println("Time taken for " + numbers[numIndex] + " words : " + insertionTime + "s" );
        numIndex++;
      }
      wordsSorted++;
    }
    // outputs amount of moves from global variable
    System.out.println("Moves : " + moves);
    System.out.println("=====================");
  }
  // merge sort pseudocode
  // input: two arrays/lists : list1 and list2
  // input: SortedList
  // while list1 not empty and list2 not empty
  //   if next element in list1 <= next element in list2
  //     move the next element of list1 to next empty space in Sortedlist
  //   else
  //     move the next element of list2 to the next empty space in Sortedlist
  // while list1 not empty do
  //   move the remaining elements of list1 to SortedList
  // while list2 not empty do
  //   move the remaining elements of list2 to Sortedlist
  //
  public static void mergeSort(ArrayList<String> mergeSorted) {
    if(mergeSorted.size() > 1){
      //finds the middle number index of the original list
      int middle = mergeSorted.size()/2;
      //creates two ArrayLists that contain the left and right part of input list
      ArrayList<String> leftList = new ArrayList<>(mergeSorted.subList(0,middle));
      ArrayList<String> rightList = new ArrayList<>(mergeSorted.subList(middle, mergeSorted.size()));
      //recursively call mergeSort on both sides
      mergeSort(leftList);
      mergeSort(rightList);
      // intialise three counters for merging
      int i = 0, j = 0, k = 0 ;
      // merging algorithm
      while(i < leftList.size() && j < rightList.size()){
        if (leftList.get(i).compareTo(rightList.get(j)) < 0) {
          // if current element in left side is <, add i to merged
          mergeSorted.set(k,leftList.get(i));
          i++;
        } else{
          //otherwise add current element in right list to merged
          mergeSorted.set(k,rightList.get(j));
          j++;
        }
        k++;
        //keeping track on moves made
        movesVar++;
      }
      //add remaining elements from left list to merged list
      while(i < leftList.size()){
        mergeSorted.set(k,leftList.get(i));
        i++;
        k++;
        movesVar++;
      }
      // add remaining elements from right list to merged list
      while(j < rightList.size()){
        mergeSorted.set(k, rightList.get(j));
        j++;
        k++;
        movesVar++;
      }
    }
  }
}
