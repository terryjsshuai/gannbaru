package block.chain;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 第三步、是时候进行一下测试了
 */
public class NoobChain1 {
    public static List<Block> blockchain = new ArrayList<>();

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        //循环遍历区块链来检查hash值
        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);
            //比对hash值和计算的hash值
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current Hashes not equal");
                return false;
            }
            //比对前一个区块的hash值和previousHash值
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //增加区块到数组中去
        blockchain.add(new Block("Hi im the first block", "0"));
        blockchain.add(new Block("Yo im the second block", blockchain.get(blockchain.size() - 1).hash));
        blockchain.add(new Block("Hey im the third block", blockchain.get(blockchain.size() - 1).hash));
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockchainJson);
    }
}
