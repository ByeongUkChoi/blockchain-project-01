package Part2;

import java.security.PublicKey;

public class TransactionOutput {
    public String id;
    public PublicKey recipient;
    public float value;
    public String parentTransactionId;

    TransactionOutput(PublicKey recipient, float value, String parentTransactionId) {
        this.recipient = recipient;
        this.value = value;
        this.parentTransactionId = parentTransactionId;
    }

    public boolean isMine(PublicKey publicKey) {
        return (recipient == publicKey);
    }

}
