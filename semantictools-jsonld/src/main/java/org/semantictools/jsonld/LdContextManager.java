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
package org.semantictools.jsonld;

import java.io.IOException;

import org.semantictools.jsonld.impl.LdContextEnhanceException;

/**
 * A utility for accessing JSON-LD context objects.
 * @author Greg McFall
 *
 */
public interface LdContextManager {
  
  /**
   * Returns the JSON-LD context with the specified URI.
   * The return value may be from a local cache.
   */
  LdContext findContext(String contextURI) throws IOException, LdContextParseException;
  
  /**
   * Returns the specified context enhanced with information useful for validation,
   * or null if no such enhanced context is found.
   */
  LdContext findEnhancedContext(String contextURI)  throws IOException, LdContextParseException, LdContextEnhanceException;
  

}
