package block.chain;

import java.util.Date;

/**
 * 第五步、挖矿
 */
public class Block1 {
    public String hash;
    public String previousHash;
    private String data; //数据
    private long timeStamp; //时间戳
    private int nonce;//增加一个随机数

    //构造函数
    public Block1(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    //计算hash值（把新增的随机数也计算在内）
    public String calculateHash() {
        return StringUtil.applySha256(previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + data);
    }

    public void mineBlock(int difficulty) {
        //创建一个string值由难度的位数来决定
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }
}
