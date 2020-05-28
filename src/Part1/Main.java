package Part1;

import java.util.ArrayList;

public class Main {
	
	public static ArrayList<Block> blockChain = new ArrayList<>();
	public static int difficulty = 2;	// �� 2�ڸ��� 00
	
	public static void main(String[] args) {
		System.out.println("start");
		addBlock(new Block("first", "0"));
		addBlock(new Block("second",blockChain.get(blockChain.size()-1).hash));
		String value = BlockUtil.getJson(blockChain);
		System.out.println(value);
	}
	
	public static void addBlock(Block newBlock) {
		newBlock.mineBlock(difficulty);
		blockChain.add(newBlock);
	}
}
