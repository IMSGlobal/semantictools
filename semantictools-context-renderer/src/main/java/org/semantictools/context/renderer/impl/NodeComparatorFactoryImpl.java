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
package org.semantictools.context.renderer.impl;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.codehaus.jackson.node.ObjectNode;
import org.semantictools.context.renderer.NodeComparatorFactory;
import org.semantictools.context.renderer.model.TreeNode;
import org.semantictools.json.JsonManager;

public class NodeComparatorFactoryImpl implements NodeComparatorFactory {
  
  private JsonManager jsonManager;
  
  

  public NodeComparatorFactoryImpl(JsonManager jsonManager) {
    this.jsonManager = jsonManager;
  }

  @Override
  public Comparator<TreeNode> getComparator(String frameURI) {
    ObjectNode sample = jsonManager.getObjectNodeByTypeURI(frameURI);
    
    return new NodeComparator(sample);
  }
  
  static class NodeComparator implements Comparator<TreeNode> {

    private Map<String, Integer> fieldName2Index = new HashMap<String, Integer>();
    
    public NodeComparator(ObjectNode sample) {
      fieldName2Index.put("@context", 0);
      fieldName2Index.put("@type", 1);
      fieldName2Index.put("@id", 2);
      if (sample == null) {
        return;
      }
      int index = 3;
      Iterator<String> sequence = sample.getFieldNames();
      while (sequence.hasNext()) {
        String name = sequence.next();
        if (name.startsWith("@")) {
          continue;
        }
        fieldName2Index.put(name, index);
        index++;
      }
    }

    @Override
    public int compare(TreeNode a, TreeNode b) {
      
      String aName = a.getLocalName();
      String bName = b.getLocalName();
      
      Integer aIndex = fieldName2Index.get(aName);
      Integer bIndex = fieldName2Index.get(bName);
      
      return (aIndex!=null && bIndex!=null) ? aIndex.intValue() - bIndex.intValue() : 
             (aIndex!=null && bIndex==null) ?  -1 : 
             (aIndex==null && bIndex!=null) ?   1 :
            aName.compareTo(bName);
    }
  }

}
