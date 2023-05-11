/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinalVersionsOfGrade12JavaProjects;
import java.io.File;//required for file access (reading)
import java.io.FileNotFoundException;
import java.io.FileWriter;//required for file access (writing)
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;

/**
 * filename: algorithmslab.java
 * assignment: Algorithms Lab
 * Description: To explore a variety of sorting algorithms. For each sorting algorithm you will be comparing algorithmic efficient through 3 
 * different metrics: iterations taken, time taken and effort to implement (based on your subjective experience).
 * These comparisons will take place by using the algorithms to sort varying text files 
 * of data of a variety of lengths and formats (see the table in the Test Data section 
 * below). Your goal will be to determine the answer to a variety of inquiry questions 
 * listed in the Analysis Questions section.
 * @author vict0582
 * by: Victor Jiang
 * Teacher: Mr J.F.
 * Course: ICS 4U1
 * due date: April 6, 2023
 */
public class FinalVersionAlgorithmsLabof2023 { // NOTE WE AREALWASY SORTING THE ARRAYS IN ASCENDING ORDER (LEAST TO GREATEST)
   
    public static void saveFile(String fileName, int[] array, int iterations, long timeInMillis){
        //In java we can read from and write to files with relative ease.
        //We MUST place file access inside a try catch that catches at least  
        //IOException or our code will not compile.
        try{
            //creates (or opens) the file called filename.txt 
            FileWriter writeArray = new FileWriter(fileName+ ".txt");
            for (int x = 0; x< array.length;x++){
                //the data written must be a String or char.
                String line = Integer.toString(array[x]);
                writeArray.write(line+'\n');//writes the data stored in line to the file                  
            }
            //closes the file (should be done to avoid memory leaks, and file conflicts)
            writeArray.write("Iterations: " + iterations + '\n');
            writeArray.write("Time (ms) to sort: " + timeInMillis + '\n');
            writeArray.close();
        }catch (IOException  e){
            System.out.println("File could not be created!");
        }
      }
    
    public static int[] generateArrayInt(String fileName){
        //In java we can read from and write to files with relative ease.
        //We MUST place file access inside a try catch that catches at least the 
        //FileNotFoundException or our code will not compile.
        int array[];
        try{
            File saveFile = new File(fileName+".txt");
            //We still can use scanners to read input.
            Scanner fileScanner =new Scanner(saveFile);//notice how the parameters have changed?
            int fileLength = 0; //used to determine the length of our array
            while(fileScanner.hasNextLine()){//as long as there is a next line.
                fileScanner.nextLine();
                fileLength++;//increase the counter so we learn how many lines are in the file.
            }//end of while
            array = new int[fileLength]; // create a new integer array called "array" with length equal to the number of lines we read from the file
            fileScanner =new Scanner(saveFile); // create scanner to save to file
            int counter = 0;// counter is 0
            while(fileScanner.hasNextLine()){//a loop that will continue as long as there is another line to be read from the file
                array[counter] = Integer.valueOf(fileScanner.nextLine()); // read the next line from the file and convert it to an integer; store the integer in the appropriate index of the array
                counter++;//count iterations to see if fill index
            }
            fileScanner.close();// close the scanner object
        }catch (FileNotFoundException e){// catch exception
            System.out.println("File not found!");
            return new int[1];
        }
        return array;
    }
   
    public static int[] generateArrayInt(String type, int size){// this method is used to generate integers of a certain size either in ascending, descending, or random order
        if (size<=0){ 
        }
        int array[] = new int[size];
        switch (type){
            case "random":// case that the user chooses random order for the data to be presented to sort
                for (int x = 0;x<size;x++){
                    array[x] =(int)(Math.random() *size);
                }
                break;// break to prevent fallthrough
            case "ascending":// data presented from smallest to greatest order
                for (int x = 0;x<size;x++){
                    array[x] = x;
                }
                break;
            case "descending":// data presented in largest to least order
                for (int x = 0; x<size; x++){
                    array[x] = size-x;
                }
                break;
        }//end of switch
        return array;
    }//end of generateArrayInt

    public static int[] insertionSort(int array[]){
        int count =0; //used to count the number of iterations (not necessary)
        for (int i =1; i<array.length; i++){ //loops through each element (left to right)
            int target = array[i]; //target to be placed in sorted postion.
            int j = i -1; //elements left of the target
            count++;
            while (j>=0 && array[j] > target){ // loop while j is in bounds and the element to the left of target is greater than target
                count++;// count iterations
                //while the target is not in left most sorted postion
                array[j+1] = array[j];// swaps elements to the right.
                j--;// move "j" to the right
            }//end of while
            array[j+1] = target;// place the now tarted element in the correct sorted posistion
        }// end of for
            System.out.println("Array sorted with insertion sort after: " + count + " iterations.");// display iterations in console
            int[] result = new int[array.length + 1];//here we are creating a new array to hold the sorted array with the count of iterations
            result[0] = count;// set the first element to count the # of iterations
            for (int i = 1; i <= array.length; i++) {// loop through the orginal array to do so
                result[i] = array[i-1];// now be able to copy each element of the original array to the new array
                }
                return result;// return # of iterations with the count of them
            }//end of insertionSort
    
    public static int[] selectionSort(int array[]){
        int selection = 0;//# of comparisons
        for(int start = 0; start<array.length; start ++){// loop through array with leftmost unsorted element
            int minimum = start;    //set that element as make it the minimun
            for (int j = start; j<array.length; j++){ // loop through unsorted elements to find the min
                selection++;// increase count of iteration
                if (array[j]<array[minimum]){// if the currect element is smaller than the assumed minimun (start), set the minimum to be the current element
                    minimum = j;
                }
                int temp = array[start];// Store the value of the leftmost unsorted element
                array[start]= array[minimum];// change the leftmost unsorted item with the minimum
                array[minimum]=temp;// now change the minimun with the value of the leftmost unsorted element 
            }// end of inner for
        }// end of for
        System.out.println("Array sorted with selection sort after: " + selection + " iterations.");
        int[] result1 = new int[array.length + 1]; // same method as above, create new array to hold sorted array
        result1[0] = selection;
            for (int i = 1; i <= array.length; i++) {
                result1[i] = array[i-1];
            }
        return result1;// return # of iterans
    }//end of selection sort
    
    public static int[] bubbleSort (int array[]){
        int bubble = 0;
        for (int daniel = 0; daniel<array.length; daniel++){ // loop through each element
            for(int i = 0; i<array.length-1; i++){// now compare adjacent elements
                bubble++;//count iterations
                if(array[i]>array[i+1]){// if statement to check if adjacent elements need to be switched
                    int temp = array[i+1];// if yes, the swap the elements
                    array[i+1]=array[i];// move the current element to the right
                    array[i]=temp;// now move the stored next element to the left
                }
            }
        }
        System.out.println("Array sorted with bubble sort after: " + bubble + " iterations.");// the same method as above, create new array to hold sorted array
        int[] result2 = new int[array.length + 1];
        result2[0] = bubble;
            for (int i = 1; i <= array.length; i++) {
                result2[i] = array[i-1];
            }
        return result2;
     } //end of bubble sort

    public static int[] shellSort (int array[]){ // modified shellsort method
        // link for reference: https://www.javatpoint.com/shell-sort
        int shell3 = 0;// counter to count number of iterations
        int gap = array.length/2; // to inititalize to split the array into two
        while (gap >0){ // loop while the gap is greater than zero
            for(int x = gap; x<array.length; x++){
                shell3++; // count iterations
                int temp = array[x]; // assign the value at index x to the a temporary variable named temp
                int k = x; // set x to k (k to x)
                while (k>=gap && array[k-gap] >temp){// this runs while k, which was x is greater or equal to gap, and when the value at k-gap is greater than temp
                    array[k] = array[k-gap]; // move the value at index k-gap to index gap
                    k-=gap; //decrease k by gap
                } 
                array[k]=temp;// be sure to assign the value of temp to the k index
            }
            gap /=2;// make sure to divide the gap by 2
        }//end of while
        System.out.println("Array sorted with shell sort after: " + shell3 + " iterations.");// same method as above in other algorithms, create new array to hold sorted array
        int[] result3 = new int[array.length + 1];
        result3[0] = shell3;
            for (int i = 1; i <= array.length; i++) {
                result3[i] = array[i-1];
            }// end of for
        return result3;
    }// end of shell
    
    public static void displayArray(int array[]){// method to display the array
        for (int x =0; x < array.length;x++){
            System.out.println(array[x]);
        }       
    }

    public static void main(String []args){ // descending is greater to less, ascending is less to greater // need to do one by one
       //all the code below counts and calls the sorting algorithm methods and saves the sorted arrays in the desired data order, "random", "ascending", or "descending"
       // and the user changes the size of the array generated to sort to their desire, and the # of iterations are counted and printed in the console
       // however, the user must find that all results, (sorted array, time taken to sort (ms), and # of iterations to sort array can all be 
       //found on a text file in the programs location of this java file's location on the PC.
        
        //insertionsort
        long startTime = System.currentTimeMillis();// method that returns the current time in ms
        int arrayInsertion[] = generateArrayInt("descending", 1000);// generate the array size and how it is generated in what order
        int result[] = insertionSort(arrayInsertion); // call and sort the array using insertion sort, or whatever method desired, which is passes through arrayinsertion
        long timeTaken = System.currentTimeMillis() - startTime;// be able to calculate the time taken to sort the array
        int count = result[0];// get the count of iterations to sort the array from the insertionsort method
        int [] insertionSorted = Arrays.copyOfRange(result, 1, result.length);//create a new array containing the sorted integers in the array
        saveFile("generatedArrayFileSorted(Insertionsort)", insertionSorted, count, timeTaken);// save all the data using the saveFile method
        //selectionsort
        long startTime1 = System.currentTimeMillis();//all calls below are identical to the intial insertionsort call above
        int arraySelection[] = generateArrayInt("descending", 1000);
        int result1[] = selectionSort(arraySelection);
        long timeTaken1 = System.currentTimeMillis() - startTime1;
        int count1 = result1[0];
        int [] selectionSorted = Arrays.copyOfRange(result1, 1, result1.length);
        saveFile("generatedArrayFileSorted(Selectionsort)", selectionSorted, count1, timeTaken1);
        //bubblesort
        long startTime2 = System.currentTimeMillis();
        int arrayBubble[] = generateArrayInt("descending", 1000);
        int result2[] = bubbleSort(arrayBubble);
        long timeTaken2 = System.currentTimeMillis() - startTime2;
        int count2 = result2[0];
        int [] bubbleSorted = Arrays.copyOfRange(result2, 1, result2.length);
        saveFile("generatedArrayFileSorted(Bubblesort)", bubbleSorted, count2, timeTaken2);
        //shellsort
        long startTime3 = System.currentTimeMillis();
        int arrayShell[] = generateArrayInt("descending", 1000);
        int result3[] = shellSort(arrayShell);
        long timeTaken3 = System.currentTimeMillis() - startTime3;
        int count3 = result3[0];
        int [] shellSorted = Arrays.copyOfRange(result3, 1, result3.length);
        saveFile("generatedArrayFileSorted(Shellsort)", shellSorted, count3, timeTaken3);
        
        System.out.println("-----------------------------------");
        //the code down below sorts and runs each algorithm and displays it in the console instead          
        /**
        long time = System.currentTimeMillis(); // insertion sort
        insertionSort(generateArrayInt("random", 1000000));
        long timeTaken4 = System.currentTimeMillis() - time;
        System.out.println("Array sorted after " + timeTaken4 + " miliseconds with insertion sort.");
        System.out.println("--------");
        //display of time in the console
        
        long time2 = System.currentTimeMillis();// selection sort
        selectionSort(generateArrayInt("random",1000));
        long timeTaken5 = System.currentTimeMillis() - time2;
        System.out.println("Array sorted after " + timeTaken5 + " miliseconds with selection sort.");
        System.out.println("--------");
        
        long time3 = System.currentTimeMillis();//bubble sort
        bubbleSort(generateArrayInt("random",1000));
        long timeTaken6 = System.currentTimeMillis() - time3;
        System.out.println("Array sorted after " + timeTaken6 + " miliseconds with bubble sort.");
        System.out.println("--------");
        
        long time4 = System.currentTimeMillis();//shell sort
        shellSort(generateArrayInt("random",1000));
        long timeTaken7 = System.currentTimeMillis() - time4;
        System.out.println("Array sorted after " + timeTaken7 + " miliseconds with shell sort.");
        System.out.println("--------");
     */
    }
}
