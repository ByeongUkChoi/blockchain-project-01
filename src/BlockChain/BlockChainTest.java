package BlockChain;

class Block{
	int val;
	Block next;
	Block(int data){
		this.val = data;
	}
}

public class BlockChainTest {
	
	public static void main(String[] args) {
		Block block = new Block(1);
		block.next = new Block(2);
		block.next.next = new Block(3);
		block.next.next.next = new Block(4);
		
		print(block);
	}
	
	public static void print(Block block) {
		while(block != null) {
			System.out.println(block.val);
			block = block.next;
		}
	}
}
