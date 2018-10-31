package block.chain;

import java.security.Security;
import java.util.ArrayList;
import java.util.List;

public class NoobChain3 {
    public static List<Block> blockchain = new ArrayList<>();
    public static int difficulty = 5;
    public static Wallet walletA;
    public static Wallet walletB;

    public static void main(String[] args) {
        //调用Bouncey castle作为安全性的提供类
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        //创建两个钱包
        walletA = new Wallet();
        walletB = new Wallet();

        //测试公钥和私钥
        System.out.println("Private and public keys:");
        System.out.println(StringUtil.getStringFromKey(walletA.privateKey));
        System.out.println(StringUtil.getStringFromKey(walletA.publicKey));

        //创建一个交易从WalletA地址到walletB地址
        Transaction transaction = new Transaction(walletA.publicKey, walletB.publicKey, 5, null);

        //用wallectA的私钥进行签名
        transaction.generateSignature(walletA.privateKey);

        //通过wallectA的公钥验证签名是否工作
        System.out.println("Is signature verified");
        System.out.println(transaction.verifiySignature());
    }
}
