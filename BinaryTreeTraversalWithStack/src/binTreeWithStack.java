import java.util.Random;
import java.util.Stack;

public class binTreeWithStack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//TreeSet<Integer> tree = new TreeSet<>() ;
		Random randomGenerator = new Random();
		for (int idx = 1; idx <= 8; ++idx){
			int randomInt = randomGenerator.nextInt(100);
			insert(randomInt);
		}

		System.out.println("Post Order traversal with recusrsion.");
		traverse(root);
		System.out.println();
		System.out.println();
		System.out.println("Post Order traversal without recusrsion.");
		traverseStack(root);
	}

	private static void traverseStack(Node root2) {
		Stack<Node> stack = new Stack<>();
		Node temp = root2;

		do {

			if (temp.right != null && !temp.right.state) {
				stack.push(temp);
				stack.push(temp.right);
			}
			else if (temp.right == null && (temp.left != null && !temp.left.state)) stack.push(temp);
			if(temp.left != null && !temp.left.state){
				temp = temp.left;
			}
			else if (temp.right == null || temp.right.state ){
				temp.state = true;
				System.out.print(temp.value + " ");
				if(!stack.isEmpty())temp = stack.pop();
				else temp = null;
			}
			else{
				temp = stack.pop();
			}
		}while(!stack.isEmpty() || temp != null);
	}


	public static Node root;

	private static void traverse(Node root2) {
		Node temp = root2 ;
		if(temp == null){}
		else{
			traverse(temp.left);
			traverse(temp.right);
			System.out.print(temp.value + " ");
		}
	}

	public static void insert(int value){
		Node node = new Node(value);
		if ( root == null ) {
			root = node;
			return;
		}
		insertRec(root, node);
	}

	private static void insertRec(Node latestRoot, Node node){
		if ( latestRoot.value > node.value){
			if ( latestRoot.left == null ){
				latestRoot.left = node;
				node.state = false;
				return;
			}
			else{
				insertRec(latestRoot.left, node);
			}
		}
		else{
			if (latestRoot.right == null){
				latestRoot.right = node;
				node.state = false;
				return;
			}
			else{
				insertRec(latestRoot.right, node);
			}
		}
	}

}



