package Part2;

import java.security.PublicKey;
import java.util.ArrayList;

public class Transaction {
    public String transactionId;
    public PublicKey sender;
    public PublicKey reciepient;
    public float value;
    public byte[] signature;

    public ArrayList<TransactionInput> inputs = new ArrayList<>();
    public ArrayList<TransactionOutput> outputs = new ArrayList<>();

    public Transaction(PublicKey sender, PublicKey reciepient, float value, ArrayList<TransactionInput> inputs) {
        this.sender = sender;
        this.reciepient = reciepient;
        this.value = value;
        this.inputs = inputs;
    }

    // tx 검증. 중간에 에러시 중단됨
    public boolean processTransaction() {

        // 1. 서명 검증
        if(verifySignature() == false) return false;

        // 2.
    }

    public boolean verifySignature() {
        String data = BlockUtil.getStringFromKey(sender)+BlockUtil.getStringFromKey(reciepient)+Float.toString(value);
        return BlockUtil.verifyECDSASig(sender, data, signature);
    }
    public void generateSignature(PrivateKey privateKey) {
        String data = BlockUtil.getStringFromKey(sender)+BlockUtil.getStringFromKey(reciepient)+Float.toString(value);
        signature = BlockUtil.applyECDSASig(privateKey, data);

    }
}
