package block.chain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Wallet {
    public PrivateKey privateKey;
    public PublicKey publicKey;
    public Map<String, TransactionOutput> UTXOs = new HashMap<>(); //钱包自身的UTXO

    public Wallet() {
        generateKeyPair();
    }

    public void generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
            keyGen.initialize(ecSpec, random);
            KeyPair keyPair = keyGen.generateKeyPair();
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //返回余额并保存钱包自身的UTXO
    public float getBalance() {
        float total = 0;
        for (Map.Entry<String, TransactionOutput> item : NoobChain4.UTXOs.entrySet()) {
            TransactionOutput UTXO = item.getValue();
            if (UTXO.isMine(publicKey)) { //查看输出是否属于我即货币是不是我的
                UTXOs.put(UTXO.id, UTXO); //添加到未使用的交易列表中
                total += UTXO.value;
            }
        }
        return total;
    }

    //创建并返回属于这个钱包的一个新交易
    public Transaction sendFunds(PublicKey _recipient, float value) {
        if (getBalance() < value) { //搜集余额并检查金额
            System.out.println("#Not Enough funds to send transaction. Transaction Discarded.");
            return null;
        }
        //创建输入列表
        List<TransactionInput> inputs = new ArrayList<TransactionInput>();
        float total = 0;
        for (Map.Entry<String, TransactionOutput> item : UTXOs.entrySet()) {
            TransactionOutput UTXO = item.getValue();
            total += UTXO.value;
            inputs.add(new TransactionInput(UTXO.id));
            if (total > value) break;
        }
        Transaction newTransaction = new Transaction(publicKey, _recipient, value, inputs);
        newTransaction.generateSignature(privateKey);
        for (TransactionInput input : inputs) {
            UTXOs.remove(input.transactionOutputId);
        }
        return newTransaction;
    }
}

