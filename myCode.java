import java.util.*;
import java.io.*;




public class myCode {
  public static void main (String[] args) throws IOException{
    ArrayList<String> nonSWList = deleteStopWords("input.txt","stopwords.txt");
    //System.out.println(nonSWList);
    ArrayList<String> insertionSortedList = insertionSort(nonSWList);
    System.out.println(insertionSortedList);
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
  public static ArrayList<String> insertionSort(ArrayList<String> nonSWList){
    //copy contents from un-sorted list to another list so intial list can be used again for next sort
    ArrayList<String> insertionSortedList = new ArrayList<>();
    insertionSortedList.addAll(nonSWList);

    for (int i = 1; i < insertionSortedList.size() ;i++ ) {
      String hold = insertionSortedList.get(i);
      int j = i-1;
      while(j >= 0 && hold.compareTo(insertionSortedList.get(j)) < 0){
        insertionSortedList.set(j+1, insertionSortedList.get(j));
        j--;
      }
      insertionSortedList.set(j+1, hold);
    }
    return insertionSortedList;
  }

  // merge sort pseudocode
  // {if more than one element}
  //   {divide into two subsets}
  //   {divide left and right into two more subsets }
  //   {repeat above until you have many two element lists}
  //   {merge together left and right subsets}
  public static ArrayList<String> mergeSort(ArrayList<String> nonSWList){
    ArrayList<String> mergeSortedList = new ArrayList<>();
    mergeSortedList.addAll(nonSWList);
    //catches out lists with one or no elements
    if(mergeSortedList.size() <= 1){
      return mergeSortedList;
    }



    return mergeSortedList;
  }

}
