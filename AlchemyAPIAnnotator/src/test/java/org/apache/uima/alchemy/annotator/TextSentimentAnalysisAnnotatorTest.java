/**
 * 	Licensed to the Apache Software Foundation (ASF) under one
 * 	or more contributor license agreements.  See the NOTICE file
 * 	distributed with this work for additional information
 * 	regarding copyright ownership.  The ASF licenses this file
 * 	to you under the Apache License, Version 2.0 (the
 * 	"License"); you may not use this file except in compliance
 * 	with the License.  You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * 	Unless required by applicable law or agreed to in writing,
 * 	software distributed under the License is distributed on an
 * 	"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * 	KIND, either express or implied.  See the License for the
 * 	specific language governing permissions and limitations
 * 	under the License.
 */
package org.apache.uima.alchemy.annotator;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.alchemy.ts.sentiment.SentimentFS;
import org.apache.uima.alchemy.utils.TestUtils;
import org.apache.uima.jcas.JCas;
import org.junit.Ignore;

/**
 * SentimentAnalysis service test
 */

public class TextSentimentAnalysisAnnotatorTest {

  private static final String YOUR_KEY_HERE = "api-key";

  @SuppressWarnings("unchecked")
  @Ignore
  public void annotatorIntegrationTest() throws Exception {
    String doc = "The BBC's Jim Muir says the Tunisians have been struggling to cope with the deluge of refugees";
    String xmlPath = "desc/TextSentimentAnalysisAEDescriptor.xml";
    Map<String, Object> parameterSettings = new HashMap<String, Object>();
    parameterSettings.put("apikey", YOUR_KEY_HERE);
    JCas sentimentJCas = TestUtils.executeAE(TestUtils.getAE(xmlPath, parameterSettings), doc);
    List<SentimentFS> sentimentList = (List<SentimentFS>) TestUtils.getAllFSofType(
            SentimentFS.type, sentimentJCas);
    assertTrue(sentimentList != null);
    assertTrue(sentimentList.size() == 1);
    SentimentFS sentimentFS = sentimentList.get(0);
    assertTrue(sentimentFS != null);
    assertTrue(sentimentFS.getSentimentType() != null
            && "negative".equals(sentimentFS.getSentimentType()));
    assertTrue(sentimentFS.getScore() != null && Double.valueOf(sentimentFS.getScore()) < 0);
  }

}
