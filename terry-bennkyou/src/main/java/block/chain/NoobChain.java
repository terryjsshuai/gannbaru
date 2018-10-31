package block.chain;

/**
 * 第三步、是时候进行一下测试了
 */
public class NoobChain {

    public static void main(String[] args) {
        //创世纪区块
        Block genesisBlock = new Block("Hi im the first block", "0");
        System.out.println("Hash for block 1 : " + genesisBlock.hash);

        //第二个区块，链接在创世纪区块之后
        Block secondBlock = new Block("Yo im the second block", genesisBlock.hash);
        System.out.println("Hash for block 2 : " + secondBlock.hash);

        //第三个区块，链接在第二个区块之后
        Block thirdBlock = new Block("Hey im the third block", secondBlock.hash);
        System.out.println("Hash for block 3 : " + thirdBlock.hash);
    }
}
