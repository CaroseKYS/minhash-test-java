package org.example;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;
import org.codelibs.minhash.MinHash;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws IOException {
        Tokenizer tokenizer = new WhitespaceTokenizer();
        // The number of bits for each hash value.
        int hashBit = 1;
        // A base seed for hash functions.
        int seed = 0;
        // The number of hash functions.
        int num = 128;
        // Analyzer for 1-bit 128 hash with default Tokenizer (WhitespaceTokenizer).
        Analyzer analyzer = MinHash.createAnalyzer(hashBit, seed, num);
        // Analyzer for 1-bit 128 hash with custom Tokenizer.
        Analyzer analyzer2 = MinHash.createAnalyzer(tokenizer, hashBit, seed, num);

        String text1 = "Fess is very powerful and easily deployable Enterprise Search Server.";
        // Calculate a minhash value. The size is hashBit*num.
        byte[] minhash1 = MinHash.calculate(analyzer, text1);

        String text2 = "Fess is very powerful and easily deployable Search Server.";
        byte[] minhash2 = MinHash.calculate(analyzer, text2);
        
        // Compare a different text.
        String text3 = "Solr is the popular, blazing fast open source enterprise search platform";
        byte[] minhash3 = MinHash.calculate(analyzer, text3);

        System.out.println("similarity between text1 and text2: " + MinHash.compare(minhash1, minhash2));
        System.out.println("similarity between text2 and text3: " + MinHash.compare(minhash2, minhash3));
        System.out.println("similarity between text1 and text3: " + MinHash.compare(minhash1, minhash3));

        // Compare a different text.
        String text4 = "minhash is a probabilistic data structure for estimating the similarity between datasets";
        byte[] minhash4 = MinHash.calculate(analyzer2, text4);

        String text5 = "minhash is a probability data structure for estimating the similarity between documents";
        byte[] minhash5 = MinHash.calculate(analyzer2, text5);

        System.out.println("similarity between text4 and text5: " + MinHash.compare(minhash4, minhash5));
    }
}
