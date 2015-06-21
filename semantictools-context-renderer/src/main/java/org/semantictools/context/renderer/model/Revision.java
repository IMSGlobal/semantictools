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
package org.semantictools.context.renderer.model;

public class Revision {
  private String version;
  private String date;
  private String comment;

  public String getVersion() {
    return version;
  }
  public void setVersion(String version) {
    this.version = version;
  }
  public String getDate() {
    return date;
  }
  public void setDate(String date) {
    this.date = date;
  }
  public String getComment() {
    return comment;
  }
  public void setComment(String comment) {
    this.comment = comment;
  }

  public int hashCode() {
    return version.hashCode();
  }

  public boolean equals(Object object) {
    if (object instanceof Revision) {
      Revision revision = (Revision) object;
      return revision.getVersion().equals(version);
    }
    return false;
  }

}
