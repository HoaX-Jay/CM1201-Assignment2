import java.util.*;
import java.io.*;




public class myCode {
  public static void main (String[] args) throws IOException{
    ArrayList<String> nonSWList = deleteStopWords("input.txt","stopwords.txt");
    //System.out.println(nonSWList);
    ArrayList<String> insertionSortedList = insertionSort(nonSWList);
    //System.out.println(insertionSortedList);




    ArrayList<String> mergeSortedList = new ArrayList<>();
    mergeSortedList.addAll(nonSWList);
    mergeSort(mergeSortedList);

    System.out.println("            WAGWAN          ");

    System.out.println(mergeSortedList);

    System.out.println("            MEGONE          ");
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
  public static void mergeSort(ArrayList<String> mergeSorted) {
    if(mergeSorted.size()>1){
      int middle = mergeSorted.size()/2;
      ArrayList<String> leftList = new ArrayList<>(mergeSorted.subList(0,middle));
      ArrayList<String> rightList = new ArrayList<>(mergeSorted.subList(middle, mergeSorted.size()));

      mergeSort(leftList);
      mergeSort(rightList);

      int i = 0, j = 0, k = 0;
      while(i < leftList.size() && j < rightList.size()){
        if (leftList.get(i).compareTo(rightList.get(j))<0) {
          mergeSorted.set(k,leftList.get(i));
          i++;

        }else{
          mergeSorted.set(k,rightList.get(j));
          j++;
        }
        k++;
      }
      while(i < leftList.size()){
        mergeSorted.set(k,leftList.get(i));
      }
      while(j < rightList.size()){
        mergeSorted.set(k,rightList.get(j));
        j++;
        k++;
      }

      // System.out.println(leftList);
      // System.out.println(rightList);


    }
  }








}
