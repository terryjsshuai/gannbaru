package block.chain;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 第五步、挖矿
 */
public class NoobChain2 {
    public static List<Block1> blockchain = new ArrayList<>();
    public static int difficulty = 5;

    public static void main(String[] args) {
        //把区块增加到数组中去
        blockchain.add(new Block1("Hi im the first block", "0"));
        System.out.println("Trying to Mine block 1... ");
        blockchain.get(0).mineBlock(difficulty);
        blockchain.add(new Block1("Yo im the second block", blockchain.get(blockchain.size() - 1).hash));

        System.out.println("Trying to Mine block 2... ");
        blockchain.get(1).mineBlock(difficulty);
        blockchain.add(new Block1("Hey im the third block", blockchain.get(blockchain.size() - 1).hash));

        System.out.println("Trying to Mine block 3... ");
        blockchain.get(2).mineBlock(difficulty);

        System.out.println("\nBlockchain is Valid: " + isChainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nThe block chain: ");
        System.out.println(blockchainJson);
    }

    public static Boolean isChainValid() {
        Block1 currentBlock;
        Block1 previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');
        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current Hashes not equal");
                return false;
            }
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
            //增加hash值是否已经计算过
            if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }
}
