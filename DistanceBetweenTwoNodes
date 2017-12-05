/**-Analysis-
*Given a Binary Search Tree and two keys in the tree, the program will find the
*distance between two nodes with the keys, a and b. The user will input the Binary
*Search Tree elements, the first key, and the second key.The distance that will be 
*calculated is the minimum number of edges between a and b, which will be the output
*that will be returned in this program.
*
*-Design-
*In the first method, getDistance(int a, int b), input parameters a and b will be 
*taken in order to find the distance between two nodes. The method will traverse
*through the Binary Search Tree in order to find the necessary nodes regarding all
*situations (if the tree is empty,if the node is in the right/left section, etc). 
*The corresponding node will be recursively called by the program in order to find
*the distance. In public void insert(int key), will be used to add the element to 
*the tree. The public void breadthFirst() method will print the elements of the Binary
*Search Tree by each level starting from the root. The public void insert(int key)
*and public void breadthFirst() methods were provided.
**/

//MAIN

class BST {
	TreeNode root;

    // find the distance between two nodes with given keys a and b
    public int getDistance(int a, int b) {
        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }
        return getDistance(root, a, b);
    }
    //overloaded method
    public int getDistance(TreeNode node, int a, int b) {
        // if the tree is empty
        if (node == null) {
            return 0;
        }     
        // the left section is where both keys are present
        if (node.element > a && node.element > b) {
            // recursively call the dist_util() function on the left subtree
            return getDistance(node.left, a, b);
        }

        // the right section is where both keys are present
        if (node.element < a && node.element < b) {
            // recursively call the dist_util() function
            // on the right section
            return getDistance(node.right, a, b);        
        }

        // both nodes lie in the opposite side of the current root
        if (node.element >= a && node.element <= b) {
            // get the distance of the current root to both a
            // and b and add them to get the final distance
            return getDistance(node, a) + getDistance(node, b);
        }
        return 0;
    }
    
    // overloaded method
    public int getDistance(TreeNode node, int a) {
        // if the current node is the required node
        if (node.element == a) {
            return 0;
        } else if (node.element > a) {
            // recursively call left section
            return 1 + getDistance(node.left, a);
        }
        // recursively call right section
        return 1 + getDistance(node.right, a);
    }
    
   //insert method to add elements to BST 
	public void insert(int key){
		TreeNode newNode = new TreeNode(key);
        //if root is empty, make a new node
		if(root == null){
			root = newNode;
		} else {
			TreeNode current = root;
			TreeNode parent;

			while(true) {
				parent = current;

				if(key < current.element) {
					current = current.left;
					if(current == null){
						parent.left = newNode;
						return;
					}
				} 
                    else {
					current = current.right;
					if(current == null){
						parent.right = newNode;
						return;
					}
				}
			}
		}
	}
    //print the elements of the BST level by level staring from root
    public void breadthFirst(){
		Queue q = new LinkedList<TreeNode>();
		if(root!= null)
			q.add(root);
		while(!q.isEmpty()){
			TreeNode current = (TreeNode) q.remove();
			System.out.println(current.element);
			if(current.left != null) 
				q.add(current.left);
			if(current.right != null) 
				q.add(current.right);
		}
	}

	private class TreeNode {
		int element;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(){}
		public TreeNode(int e){
			this.element = e;
		}
	}
}



class DriverMain{
	public static void main(String args[]){
		Scanner input = new Scanner(System.in);
		String str = input.nextLine();
        int a = input.nextInt();
        int b = input.nextInt();		
        input.close();
		int[] arr = Arrays.stream(str.substring(0, str.length()).split("\\s"))
				.map(String::trim).mapToInt(Integer::parseInt).toArray();
        
        BST myTree = new BST();
		for(int i = 0; i < arr.length; i++){
            myTree.insert(arr[i]);
        }
		//myTree.breadthFirst();
        
        System.out.println(myTree.getDistance(a, b));
	}
}
