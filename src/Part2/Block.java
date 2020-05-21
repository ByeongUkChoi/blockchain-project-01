package Part2;

import java.util.Date;

public class Block {
	public String hash;
	public String previousHash;
	public String data;
	public long timeStamp;
	public int nonce;
	
	public Block(String data, String previousHash) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();
	}
	
	public String calculateHash() {
		String hash = BlockUtil.applySha256(previousHash + data + 
				Integer.toString(nonce) + 
				Long.toString(timeStamp));
		return hash;
	}
	// difficulty = 2
	public void mineBlock(int difficulty) {
		// target = 00
		String target = BlockUtil.getDifficultyString(difficulty);
		while(!hash.substring(0, difficulty).equals(target)) {
			nonce++;
			hash = calculateHash();
			System.out.println("nonce:"+nonce+" target:"+target+" hash:"+hash);
		}
		System.out.println("block mined! nonce:"+nonce+" target:"+target+" hash:"+hash);
	}

	public boolean addTransaction(Transaction tx) {

	}
}
