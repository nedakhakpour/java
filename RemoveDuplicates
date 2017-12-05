import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;
import java.io.*;

--------------------------------------------------------------------------------

/**-Analysis-
*This program's task is to implement removeDuplicate method to get a sorted list and remove the 
*duplicate elements from the list. It is assumed that List elements are inetger and Input list is already sorted.
*
*-Design-
*The method takes a list from user, then creates a new empty list as well. Then verifies if the given list has no elements.
*If there are no elements, the empty new list is returned. If not, the program continues, adding the first element in the list
*into the new list. There is then a generic object variable "value" to hold the first element as well. A for loop is then
*called to iterate through the size of the given list. For each iteration, it checks if the value at index i is equal to the
*previous value. If not, then the new element is stored as "value", and the value is also added to the list. If they are equal,
*the value is skipped in the loop. This will continue until it is through with the list. The new list is returned. 
**/
class HW7_P2{
    public static List removeDuplicate(List list){
        //create a new linked list
        List noDuplicateList = new LinkedList();
        //check if the inputted list has no elements
        if(list.size() == 0){
            //if so, return the newly created empty list
            return noDuplicateList;
        }
        //add the first element of the given list into the newly created list
        noDuplicateList.add(list.get(0));
        //create a "prev" object to store the first value of whatever object is in the list
        Object value = list.get(0);
        //iterate through the whole list using a for loop
        for(int i=1; i<list.size(); i++) {
            //if the object at the indexed position of the list is not equal to the "prev" object
            if(!list.get(i).equals(value)) {
                //store the new indexed value into the prev object
                value = list.get(i);
                //add the value into the new list
                noDuplicateList.add(list.get(i));
           }
       } //continue iterating through the list until all the repeated values are omitted
        
        //return the new list
        return noDuplicateList;
   }
}
---------------------------------------------------------------------------------------------------

class DriverMain{
	public static void main(String args[]){
		Scanner input = new Scanner(System.in);
		String str = input.nextLine();
		input.close();
		int[] arr = Arrays.stream(str.substring(0, str.length()).split("\\s"))
				.map(String::trim).mapToInt(Integer::parseInt).toArray();		
		List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
		Collections.sort(list);
		System.out.println("sortedInput:" + list.toString());
		System.out.println("output:" + HW7_P2.removeDuplicate(list).toString());			
	}
}
