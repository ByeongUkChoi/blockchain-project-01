package Part2;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;
import java.util.HashMap;
import java.util.Map;

public class Wallet {
	public PrivateKey privateKey;
	public PublicKey publicKey;
	
	public HashMap<String, TransactionOutput> UTXO_Wallet = new HashMap<>();
	
	public Wallet() {
		generateKeyPair();
	}

	/**
	 * 잔고 확인
	 */
	public float getBalance() {
	    float total = 0;
	    for (Map.Entry<String, TransactionOutput> item: Main.UTXOs.entrySet()) {
	    	TransactoinOutput UTXO = item.getValue();
	    	if(UTXO.isMine(publicKey)) {
	    		total += UTXO.value;
				UTXO_Wallet.put(UTXO.id, UTXO);
			}
		}
	    return total;
	}
	
	public void generateKeyPair() {
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA","BC");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
	
			keyGen.initialize(ecSpec, random); 
	        KeyPair keyPair = keyGen.generateKeyPair();
	
	        privateKey = keyPair.getPrivate();
	        publicKey = keyPair.getPublic();
	        
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
