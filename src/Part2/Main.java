package Part2;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	
	public static ArrayList<Block> blockChain = new ArrayList<>();
	public static int difficulty = 2;	// 菊 2磊府啊 00
	
	public static Wallet walletA;
	public static Wallet walletB;
	
	public static Transation genesisTransaction;
	public static HashMap<String, TransactionOutput> UTXOs = new HashMap<>();
	
	public static minimunTransaction = 0.1f;
	
	public static void main(String[] args) {
		
		//1. wallet 积己
		Wallet walletA = new Wallet();
		Wallet walletB = new Wallet();
		Wallet coinbase = new Wallet();
	
		//2. 力呈矫胶 飘罚黎记 积己
		genesisTransaction = new Transaction(coinbase.publicKey, walletA.publicKey, 100f, null);
		genesisTransaction.generateSignature(coinbase.privateKey);
		genesisTransaction.transactionId="0";
		genesisTransaction.outputs.add(new TransactionOutput(genesisTransaction.reciepient, genesisTransaction.value, genesisTransaction.transactionId));
		//3. main save utxo
		UTXOs.put(genesisTransaction.outputs.get(0).id, genesisTransaction.outputs.get(0));

		//4. genesisBloc 积己
		Block genesisBlock = new Block("0");
		genesisBlock.addTransaction(genesisTransaction);
		addBlock(genesisBlock);

		//5. Block1 积己
		Block block1 = new Block(genesisBlock.hash);
		Transaction tx = walletA.sendFunds(WalletB, 40f);
		block1.addTransaction(tx);
		addBlock(block1);
	
	}
	
	public static void addBlock(Block newBlock) {
		newBlock.mineBlock(difficulty);
		blockChain.add(newBlock);
	}
}
