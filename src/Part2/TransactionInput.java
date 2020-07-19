package Part2;

/**
 * 트랜잭션 아웃풋이 곧 인풋이 된다.
 */
public class TransactionInput {
    public String transactionOutputId;
    public TransactionOutput UTXO;

    public TransactionInput(String transactionOutputId) {
        this.transactionOutputId = transactionOutputId;
    }
}
