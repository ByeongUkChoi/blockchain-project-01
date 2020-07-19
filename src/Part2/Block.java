package Part2;

import java.util.ArrayList;
import java.util.Date;

public class Block {
	public String hash;
	public String previousHash;
	public String data;
	public long timeStamp;
	public int nonce;

	public ArrayList<Transaction> transactionList = new ArrayList<>();
	
	public Block(String previousHash) {
		//this.data = data;
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
		if(tx == null) return false;
		// genesis block이 이닐때 트랜잭션이 실패할 경우 추가하지 않음
		if((!"0".equals(previousHash))) {
			if(tx.processTransaction() != true) {
			    // TODO: 에러 발생 시키고 함수 타입을 void로 해도 될것 같음. 아니면 받는 함수 사용하는 곳에서 처리가 필요함.
				return false;
			}
		}
		transactionList.add(tx);
		System.out.println("===Transaction added to Block");
		return true;
	}
}
