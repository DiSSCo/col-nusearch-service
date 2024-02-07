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
// https://github.com/gbif/checklistbank/blob/master/checklistbank-nub/src/main/java/org/gbif/nub/utils/RsGbifOrg.java

import com.google.common.base.Joiner;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class knowing the url layout of rs.gbif.org to access authority and dictionary files.
 */
public class RsGbifOrg {

  private RsGbifOrg() {
    // Utility class not intended for instantiation
  }

  private static final Logger LOG = LoggerFactory.getLogger(RsGbifOrg.class);
  private static final Joiner PATH_JOINER = Joiner.on("/").skipNulls();
  private static final String DICT_DIR = "dictionaries";
  public static final String DOMAIN = "http://rs.gbif.org/";
  public static final String FILENAME_BLACKLIST = "blacklisted.txt";

  /**
   * @param path given as array of individual names that will be concatenated
   * @return url to file inside rs.gbif.org
   */
  public static URL url(String ... path) {
    try {
      if (path == null){
        return URI.create(DOMAIN).toURL();
      }
      return URI.create(DOMAIN + PATH_JOINER.join(path)).toURL();
    } catch (MalformedURLException e) {
      LOG.error("Cannot insert rs.gbif.org url for path " + PATH_JOINER.join(path), e);
    }
    return null;
  }

  /**
   * @param filename of authority dictionary file requested
   * @return url to file inside to authority folder of rs.gbif.org
   */
  public static URL authorityUrl(String filename) {
    return url(DICT_DIR, "authority", filename);
  }

  /**
   * @param filename of synonyms file requested
   * @return url to file inside to synonyms dictionary folder of rs.gbif.org
   */
  public static URL synonymUrl(String filename) {
    return url(DICT_DIR, "synonyms", filename);
  }
}