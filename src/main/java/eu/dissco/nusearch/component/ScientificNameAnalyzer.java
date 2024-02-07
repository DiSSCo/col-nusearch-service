/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.dissco.nusearch.component;

// Copied and adapted from GBIF:
// https://github.com/gbif/checklistbank/blob/master/checklistbank-nub/src/main/java/org/gbif/checklistbank/lucene/ScientificNameAnalyzer.java

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.KeywordTokenizer;
import org.springframework.stereotype.Component;

/**
 * Keyword analyzer that uses the scientific name normalizer
 */
@Component
public class ScientificNameAnalyzer extends Analyzer {

  public static final int BUFFER_SIZE = 1024;

  @Override
  protected TokenStreamComponents createComponents(final String fieldName) {
    KeywordTokenizer source = new KeywordTokenizer(BUFFER_SIZE);

    TokenStream result = new ScientificNameNormalizerFilter(source, true, true);
    result = new LowerCaseFilter(result);

    return new TokenStreamComponents(source, result);
  }

}