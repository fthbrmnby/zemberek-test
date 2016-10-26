package com.mycompany.zemberek;

import java.util.ArrayList;
import java.util.List;
import zemberek.morphology.parser.MorphParse;
import zemberek.morphology.parser.tr.TurkishWordParserGenerator;
import zemberek.tokenizer.SimpleSentenceBoundaryDetector;
import zemberek.tokenizer.ZemberekLexer;

public class ZemberekPipeline {
    private final TurkishWordParserGenerator parser;
    private final SimpleSentenceBoundaryDetector sentence;
    private final ZemberekLexer tokenizer;
    
    public ZemberekPipeline(TurkishWordParserGenerator parser, 
            SimpleSentenceBoundaryDetector sentence,
            ZemberekLexer tokenizer) {
        this.parser = parser;
        this.sentence = sentence;
        this.tokenizer = tokenizer;
    }
    
    // Analyze words morphologically.
    private List<MorphParse> parse(String word) {
        List<MorphParse> parses = parser.parse(word);
        return parses;
    }
    
    // Split documents into sentences.
    private List<String> getSentences(String doc) {
        List<String> sentences = sentence.getSentences(doc);
        return sentences;
    }
    
    // Split sentences into words.
    private List<String> tokenize(String sentence) {
        List<String> tokens = tokenizer.tokenStrings(sentence);
        return tokens;
    }
    
    // Whole pipeline
    public List<MorphParse> pipeline(String document) {
        List<String> sentences = getSentences(document);
        List<String> tokens = new ArrayList();
        for (String sentence : sentences) {
            tokens.addAll(tokenize(sentence));
        }
        
        List<MorphParse> morphWords = new ArrayList();
        for (String word : tokens) {
            morphWords.addAll(parse(word));
        }
        return morphWords;
    }
}
