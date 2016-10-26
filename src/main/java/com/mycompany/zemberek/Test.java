package com.mycompany.zemberek;

import java.io.IOException;
import java.util.List;
import zemberek.morphology.parser.MorphParse;
import zemberek.morphology.parser.tr.TurkishWordParserGenerator;
import zemberek.tokenizer.SimpleSentenceBoundaryDetector;
import zemberek.tokenizer.ZemberekLexer;

public class Test {
    public static void main(String[] args) throws IOException {
        
        ZemberekPipeline zemberek = new ZemberekPipeline(
                TurkishWordParserGenerator.createWithDefaults(),
                new SimpleSentenceBoundaryDetector(),
                new ZemberekLexer());
        
        List<MorphParse> tokens = zemberek.pipeline("Amerika Birleşik Devletleri siber saldırıların "
                + "hedefi oldu. Birçok internet sitesi ve sosyal medya "
                + "uygulamasına gün boyunca erişim sağlanamadı. "
                + "Saldırıların arkasında Wikileaks destekçilerinin "
                + "olduğu düşünülüyor.");
        
        for (MorphParse token : tokens) {
            System.out.println(token.formatLong());
        }
    }
}
