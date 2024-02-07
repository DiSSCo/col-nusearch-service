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
package eu.dissco.nusearch.utils;

// Copied and adapted from GBIF:
// https://github.com/gbif/checklistbank/blob/master/checklistbank-nub/src/main/java/org/gbif/nub/lookup/similarity/DistanceUtils.java
public class DistanceUtils {

  public static double convertEditDistanceToSimilarity(int edits, String s1, String s2) {
    int length = Math.min(10, Math.min(s1.length(), s2.length())) ;
    double dist =  Math.pow(edits, 1.4) / (double) length;
    double sim = edits > length || dist > 1.0d ? 0 : 100d * (1d - dist);
    return Math.round(sim);
  }
}