/**-Analysis-
*Given the MaxHeap class, where each node is greater than or equal to any of it's 
*children, a MinHeap was created. In the MinHeap class, each node is less than or
*equal to any of it's children. Both of these classes were extentions from the
*Comparable class. In this program, new objects were added/removed to and from the
*heap, swapping nodes until the correct node conditions were met (having each node
*be less than or equal to any of it's children).
*
*-Design-
*The methods that I provided code for were public void add(E newObject) and 
*public E remove(), leaving the other methods untouched. In public void add(E newObject),
*a new object was added into the heap. The method checks and sets the index of the last node
*then depending on the results, swaps it out if the value is greater than the parent.
*The method loops until the current index is the parent index. The remove method removes
*the root from the heap. It loops through to find the maximum between the two child nodes.
*The method checks both sides of the heap, and swaps values if the current node indexed
*is less than the maximum node. The method continues until the tree is a heap and the 
*removed object is returned.
*
**/

import java.util.*;
import java.lang.*;
import java.io.*;

class MaxHeap<E extends Comparable<E>> {
	  private java.util.ArrayList<E> list = new java.util.ArrayList<>();

	  /** Create a default heap */
	  public MaxHeap() {
	  }

	  /** Create a heap from an array of objects */
	  public MaxHeap(E[] objects) {
	    for (int i = 0; i < objects.length; i++)
	      add(objects[i]);
	  }

	  /** Add a new object into the heap */
	  public void add(E newObject) {
	    list.add(newObject); // Append to the heap
	    int currentIndex = list.size() - 1; // The index of the last node

	    while (currentIndex > 0) {
	      int parentIndex = (currentIndex - 1) / 2;
	      // Swap if the current object is greater than its parent
	      if (list.get(currentIndex).compareTo(
	          list.get(parentIndex)) > 0) {
	        E temp = list.get(currentIndex);
	        list.set(currentIndex, list.get(parentIndex));
	        list.set(parentIndex, temp);
	      }
	      else
	        break; // the tree is a heap now

	      currentIndex = parentIndex;
	    }
	  }

	  /** Remove the root from the heap */
	  public E remove() {
	    if (list.size() == 0) return null;

	    E removedObject = list.get(0);
	    list.set(0, list.get(list.size() - 1));
	    list.remove(list.size() - 1);

	    int currentIndex = 0;
	    while (currentIndex < list.size()) {
	      int leftChildIndex = 2 * currentIndex + 1;
	      int rightChildIndex = 2 * currentIndex + 2;

	      // Find the maximum between two children
	      if (leftChildIndex >= list.size()) break; // The tree is a heap
	      int maxIndex = leftChildIndex;
	      if (rightChildIndex < list.size()) {
	        if (list.get(maxIndex).compareTo(
	            list.get(rightChildIndex)) < 0) {
	          maxIndex = rightChildIndex;
	        }
	      }

	      // Swap if the current node is less than the maximum
	      if (list.get(currentIndex).compareTo(
	          list.get(maxIndex)) < 0) {
	        E temp = list.get(maxIndex);
	        list.set(maxIndex, list.get(currentIndex));
	        list.set(currentIndex, temp);
	        currentIndex = maxIndex;
	      }
	      else
	        break; // The tree is a heap
	    }

	    return removedObject;
	  }

	  /** Get the number of nodes in the tree */
	  public int getSize() {
	    return list.size();
	  }
	}

----------------------------------------------------------------------------------------------------------------

class MinHeap<E extends Comparable<E>> {
	private java.util.ArrayList<E> list = new java.util.ArrayList<>();

	/** Create a default heap */
	public MinHeap() {
	}

	/** Create a heap from an array of objects */
	public MinHeap(E[] objects) {
	    for (int i = 0; i < objects.length; i++) {
			add(objects[i]);
        }
	}

	/** Add a new object into the heap */
	public void add(E newObject) {
        list.add(newObject); // Append to the heap
        int currentIndex = list.size() - 1; // The index of the last node
        while (currentIndex > 0) {
        int parentIndex = (currentIndex - 1) / 2;
        // Swap if the current object is greater than its parent
        if (list.get(currentIndex).compareTo(list.get(parentIndex)) < 0) {
            E temp = list.get(currentIndex);
            list.set(currentIndex, list.get(parentIndex));
            list.set(parentIndex, temp);
        } else {
            break; 
        } 
        currentIndex = parentIndex; 
        }
    }

    /** Remove the root from the heap */
	public E remove() {
        if (list.size() == 0) {
            return null;
        }
        E removedObject = list.get(0);
        list.set(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);
        int currentIndex = 0;
        while (currentIndex < list.size()) {
            int leftChildIndex = 2 * currentIndex + 1;
            int rightChildIndex = 2 * currentIndex + 2;
            // Find the maximum between two children
            if (leftChildIndex >= list.size()) {
                break;
            }
            int minIndex = leftChildIndex;
            if (rightChildIndex < list.size()) {
                if (list.get(minIndex).compareTo(list.get(rightChildIndex)) > 0) {
                    minIndex = rightChildIndex;
                }
            }
            // Swap if the current node is less than the maximum
            if (list.get(currentIndex).compareTo(list.get(minIndex)) > 0) {
                E temp = list.get(minIndex);
                list.set(minIndex, list.get(currentIndex));
                list.set(currentIndex, temp);
                currentIndex = minIndex;
            } else{ 
                break; // The tree is a heap
            }
        }
        return removedObject;
    } 

    /** Get the number of nodes in the tree */
	public int getSize() {
		return list.size();
	}
}

------------------------------------------------------------------------------------------------
class DriverMain{
	public static void main(String args[]){
		MinHeap minHeap  = new MinHeap();
        Scanner input = new Scanner(System.in);
		String str = input.nextLine();
		input.close();
		int[] list = Arrays.stream(str.substring(0, str.length()).split("\\s"))
				.map(String::trim).mapToInt(Integer::parseInt).toArray();		

        // Add elements to the min heap
        for (int i = 0; i < list.length; i++)
			minHeap.add(list[i]);
	    
        // Remove elements from the min heap
        for (int i = 0; i < list.length; i++)
	      System.out.print(minHeap.remove() +" ");
	}
}
