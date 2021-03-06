/*******************************************************************************
 * Copyright 2012 Pearson Education
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.semantictools.jsonld.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.node.ObjectNode;
import org.semantictools.jsonld.LdContext;
import org.semantictools.jsonld.LdContextParseException;

public interface LdContextReader {
  
  /**
   * Parse the contents of the given InputStream into an LdContext object.
   * @param stream  A stream containing the entire contents of a JSON document.  This
   * method parses the &#064;context property from the document but ignores any other fields.
   * 
   * @throws JsonParseException
   * @throws IOException
   */
  LdContext parseExternalContext(InputStream stream) throws LdContextParseException, IOException;
  
  LdContext parserExternalContext(Reader reader)  throws LdContextParseException, IOException;
  
  /**
   * Use the given Jackson parser to parse a JSON context in mid-stream.
   * The Jackson parser must be positioned after the &#064;context property within some JSON object.
   */
  LdContext parseContextField(JsonParser parser) throws LdContextParseException, IOException;
  
  LdContext parseContext(JsonNode node) throws LdContextParseException, IOException;
  
  void setErrorHandler(ErrorHandler handler);
  ErrorHandler getErrorHandler();

}
