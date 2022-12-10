/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.uima.lucas.indexer.analysis;

import java.io.StringReader;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.WhitespaceTokenizer;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author faessler
 *
 */
public class RegExpFilterTest {
	@Test
	public void testIncrementToken() throws Exception{
		Tokenizer ts = new WhitespaceTokenizer(new StringReader("token1 token2 token3 token42"));

		RegExpFilter filter = new RegExpFilter(ts, "[a-z][0-9]", "REPL");
		
		TermAttribute termAtt = (TermAttribute) filter.addAttribute(TermAttribute.class);
		
		filter.incrementToken();
		assertEquals("tokeREPL", termAtt.term());
		filter.incrementToken();
		assertEquals("tokeREPL", termAtt.term());
		filter.incrementToken();
		assertEquals("tokeREPL", termAtt.term());
		filter.incrementToken();
		assertEquals("tokeREPL2", termAtt.term());
	}
}

