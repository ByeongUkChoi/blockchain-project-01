package Part1;

import java.util.ArrayList;

public class Main {
	
	public static ArrayList<Block> blockChain = new ArrayList<>();
	public static int difficulty = 2;	// 앞 2자리가 00
	
	public static void main(String[] args) {
		addBlock(new Block);
	}
	
	public static addBlock(Block newBlock) {
		newBlock.mineBlock(difficulty);
		blockChain.add(newBlock);
	}
}
