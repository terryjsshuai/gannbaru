package block.chain;

import java.security.PublicKey;

public class TransactionOutput {
    public String id;
    public PublicKey reciepient; //持有者的公钥
    public float value; //持有者的金额
    public String parentTransactionId; //交易编号

    //构造器
    public TransactionOutput(PublicKey reciepient, float value, String parentTransactionId) {
        this.reciepient = reciepient;
        this.value = value;
        this.parentTransactionId = parentTransactionId;
        this.id = StringUtil.applySha256(StringUtil.getStringFromKey(reciepient) + Float.toString(value) + parentTransactionId);
    }

    //用来验证是否属于你
    public boolean isMine(PublicKey publicKey) {
        return (publicKey == reciepient);
    }
}