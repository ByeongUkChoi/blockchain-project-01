package Part2;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;

public class Transaction {
    public String transactionId;
    // 보내는 사람
    public PublicKey sender;
    // 받는 사람
    public PublicKey reciepient;
    // 보내는 돈
    public float value;
    public byte[] signature;

    public ArrayList<TransactionInput> inputs = new ArrayList<>();
    public ArrayList<TransactionOutput> outputs = new ArrayList<>();
    public static int sequence = 0;

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

        // 2. 메인함수에 임시 저장된 txoutId로 UTXO (사용되지 않은 것만 가져와야함)
        for (TransactionInput i : inputs) {
            i.UTXO = Main.UTXOs.get(i.transactionOutputId);
        }

        // 3. 최소단위 0.1f를 넘는지 확인
        if(getInputValue() < Main.minimunTransaction) return false;

        // 4. TxOutput 생성하기 value:40 (송금), 60 (잔고. 보내고 남은돈. 다시 나한테 보내지는 돈)
        transactionId = calculateHash();
        // 보내고 남은 돈
        float leftOver = getInputValue() - value;
        // 상대방에게 보내는 돈 40
        outputs.add(new TransactionOutput(this.reciepient, value, transactionId));
        // 상대방에게 보내고 남은, 나에게 보내는 돈 60
        outputs.add(new TransactionOutput(this.sender, value, transactionId));

        // 5. output to Unspent list
        for (TransactionOutput o : outputs) {
            Main.UTXOs.put(o.id, o);
        }

        // 6. remove Txinput. transactionInput을 지워줌으로써 전 트랜잭선의 output이 지워진다.
        for (TransactionInput i : inputs) {
            Main.UTXOs.remove(i.transactionOutputId);
        }

        return true;
    }

    public String calculateHash() {
        sequence++;
        return BlockUtil.applySha256(BlockUtil.getStringFromKey(sender)+
                BlockUtil.getStringFromKey(reciepient)+
                Float.toString(value)+sequence);
    }

    public float getInputValue() {
        float total = 0;
        for (TransactionInput i : inputs) {
            if(i.UTXO == null) continue;
            total += i.UTXO.value;
        }
        return total;
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
