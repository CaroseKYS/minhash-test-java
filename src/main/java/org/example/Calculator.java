package org.example;

import org.apache.lucene.analysis.Analyzer;
import org.codelibs.minhash.MinHash;

import java.io.IOException;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间:  </p>
 *
 * @author kangys
 * @version v1.0
 */
public class Calculator {
    // The number of bits for each hash value.
    private int hashBit;
    // A base seed for hash functions.
    private int seed;
    // The number of hash functions.
    private int num;

    Analyzer analyzer = MinHash.createAnalyzer(hashBit, seed, num);

    public Calculator(){
        this.hashBit = 1;
        this.seed = 0;
        this.num = 128;
        this.initAnalyzer();
    }
    public Calculator(int hashBit, int seed, int num){
        this.hashBit = hashBit;
        this.seed = seed;
        this.num = num;
        this.initAnalyzer();
    }

    public float jaccard(String str1, String str2) throws IOException {
        byte[] minhash1 = MinHash.calculate(analyzer, str1);
        byte[] minhash2 = MinHash.calculate(analyzer, str2);
        return MinHash.compare(minhash1, minhash2);
    }

    private void initAnalyzer() {
        this.analyzer = MinHash.createAnalyzer(this.hashBit, this.seed, this.num);
    }

}
