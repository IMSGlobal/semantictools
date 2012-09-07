package org.semantictools.context.renderer;

import java.text.MessageFormat;
import java.util.List;

import org.semantictools.context.renderer.model.HttpHeaderInfo;
import org.semantictools.context.renderer.model.HttpMethod;
import org.semantictools.context.renderer.model.ResponseInfo;
import org.semantictools.context.renderer.model.MethodDocumentation;
import org.semantictools.context.renderer.model.ServiceDocumentation;

public class ServiceDocumentationPrinter extends HtmlPrinter {

  private ServiceDocumentation doc;
  
  public ServiceDocumentationPrinter(URLRewriter rewriter) {
    super(rewriter);
  }
  
  public String print(ServiceDocumentation doc) {
    this.doc = doc;
    init(doc);
    
    beginHTML();
    printTitle();
    printSubtitle();
    printStatus();
    printEditors();
    printAuthors();
    printAbstract();
    printTocMarker();
    printIntroduction();
    printRepresentation();
    printServiceMethods();
    printReferences();
    endHTML();
    
    insertTableOfContents();
    
    return popText();
  }


  private void printServiceMethods() {
    Heading heading = createHeading("Service Methods");
    beginHeading(heading);
    printPostMethod();
    printGetMethod();
    printPutMethod();
    printDeleteMethod();
    
    endHeading();
  }

  private void printGetMethod() {
    if (!doc.contains(HttpMethod.GET)) return;
    Heading heading = createHeading("GET");
    print(heading);
    MethodDocumentation method = doc.getGetDocumentation();
    print(method.getSummary());

    indent().print("<UL>");
    pushIndent();
    
    List<HttpHeaderInfo> requestHeaders = method.getRequestHeaders();
    Caption requestHeadersCaption = null;
    
    if (!requestHeaders.isEmpty()) {
      requestHeadersCaption = new Caption(CaptionType.Table, "Required HTTP Headers for GET Request", "getHeader", null);
      assignNumber(requestHeadersCaption);
      indent().print("<LI>The request must contain the HTTP Headers listed in ");
      printLink(requestHeadersCaption);
      println(".</LI>");
      
    }
    
    printListItem(method.getRequestBodyRequirement());
    
    popIndent();
    indent().println("</UL>");
   
    printParagraph("&nbsp;");
    printRequestHeaders(method, requestHeadersCaption);
    
    
    beginParagraph();
    printParagraph("&nbsp;");
    
    printResponse(method, "GET");
    
  }

  private void printPutMethod() {
    if (!doc.contains(HttpMethod.PUT)) return;
    Heading heading = createHeading("PUT");
    print(heading);
    MethodDocumentation method = doc.getPutDocumentation();
    print(method.getSummary());

    indent().print("<UL>");
    pushIndent();
    
    List<HttpHeaderInfo> requestHeaders = method.getRequestHeaders();
    Caption requestHeadersCaption = null;
    
    if (!requestHeaders.isEmpty()) {
      requestHeadersCaption = new Caption(CaptionType.Table, "Required HTTP Headers for PUT Request", "getHeader", null);
      assignNumber(requestHeadersCaption);
      indent().print("<LI>The request must contain the HTTP Headers listed in ");
      printLink(requestHeadersCaption);
      println(".</LI>");
      
    }
    
    printListItem(method.getRequestBodyRequirement());
    
    popIndent();
    indent().println("</UL>");
   
    printParagraph("&nbsp;");
    printRequestHeaders(method, requestHeadersCaption);
    
    
    beginParagraph();
    printParagraph("&nbsp;");
    

    String pattern = " describes the possible responses from the {0} method.  In all cases, the response body is empty.";
    printResponse(method, pattern, "PUT");
    
  }
  
  private void printDeleteMethod() {
    if (!doc.contains(HttpMethod.DELETE)) return;
    Heading heading = createHeading("DELETE");
    print(heading);
    MethodDocumentation method = doc.getDeleteDocumentation();
    print(method.getSummary());

    indent().print("<UL>");
    pushIndent();
    
    List<HttpHeaderInfo> requestHeaders = method.getRequestHeaders();
    Caption requestHeadersCaption = null;
    
    if (!requestHeaders.isEmpty()) {
      requestHeadersCaption = new Caption(CaptionType.Table, "Required HTTP Headers for DELETE Request", "getHeader", null);
      assignNumber(requestHeadersCaption);
      indent().print("<LI>The request must contain the HTTP Headers listed in ");
      printLink(requestHeadersCaption);
      println(".</LI>");
      
    }
    
    printListItem(method.getRequestBodyRequirement());
    
    popIndent();
    indent().println("</UL>");
   
    printParagraph("&nbsp;");
    printRequestHeaders(method, requestHeadersCaption);
    
    
    beginParagraph();
    printParagraph("&nbsp;");
    

    String pattern = " describes the possible responses from the {0} method.  In all cases, the response body is empty.";
    printResponse(method, pattern, "DELETE");
    
  }
  
  private void printResponse(MethodDocumentation method, String methodName) {

    String pattern = " describes the possible responses from the {0} method.";
    printResponse(method, pattern, methodName);
    
  }

  private void printResponse(MethodDocumentation method, String pattern, String methodName) {

    Caption responseCaption = new Caption(CaptionType.Table, 
        format("Possible responses from a {0} method", methodName), "postResponse", null);
    assignNumber(responseCaption);
    printLink(responseCaption);
    print(format(pattern, methodName));
    endParagraph();
    
    beginTable("propertiesTable");
    beginRow();
    printTH("HTTP Status");
    printTH("Description");
    endRow();
    for (ResponseInfo code : method.getStatusCodes()) {
      beginRow();
      printTD(code.getStatusCode() + "&nbsp;" + code.getLabel().replace(" ", "&nbsp;"));
      printTD(code.getDescription());
      endRow();
    }
    endTable();
    printCaption(responseCaption);
    
  }


  private void printPostMethod() {
    if (!doc.contains(HttpMethod.POST)) return;
    
    Heading heading = createHeading("POST");
    print(heading);
    MethodDocumentation method = doc.getPostDocumentation();
    print(method.getSummary());

    indent().print("<UL>");
    pushIndent();
    
    List<HttpHeaderInfo> requestHeaders = method.getRequestHeaders();
    Caption requestHeadersCaption = null;
    
    if (!requestHeaders.isEmpty()) {
      requestHeadersCaption = new Caption(CaptionType.Table, "Required HTTP Headers for POST Request", "postHeader", null);
      assignNumber(requestHeadersCaption);
      indent().print("<LI>The request must contain the HTTP Headers listed in ");
      printLink(requestHeadersCaption);
      println(".</LI>");
      
    }
    
    printListItem(method.getRequestBodyRequirement());
    
    popIndent();
    indent().println("</UL>");
   
    printParagraph("&nbsp;");
    printRequestHeaders(method, requestHeadersCaption);
    
    printParagraph("&nbsp;");
    printResponse(method, "POST");
    
//    
//    Caption responseCaption = new Caption(CaptionType.Table, "Possible responses from a POST method", "postResponse", null);
//    assignNumber(responseCaption);
//
//    printParagraph("&nbsp;");
//    beginParagraph();
//    printLink(responseCaption);
//    print(" describe the possible responses from the POST method.");
//    endParagraph();
//    
//    beginTable("propertiesTable");
//    beginRow();
//    printTH("HTTP Status");
//    printTH("Description");
//    endRow();
//    for (ResponseInfo code : method.getStatusCodes()) {
//      beginRow();
//      printTD(code.getStatusCode() + " " + code.getLabel());
//      printTD(code.getDescription());
//      endRow();
//    }
//    endTable();
//    printCaption(responseCaption);
    
  }
  
  private void printRequestHeaders(MethodDocumentation method, Caption caption) {

    List<HttpHeaderInfo> requestHeaders = method.getRequestHeaders();
    
    if (!requestHeaders.isEmpty()) {
      
      indent().print("<TABLE");
      printAttr("class", "propertiesTable");
      println(">");
      pushIndent();
      indent().println("<TR>");
      pushIndent();
      indent().println("<TH>Request Header Name</TH><TH>Value</TH>");
      popIndent();
      indent().println("</TR>");
      
      for (HttpHeaderInfo header : requestHeaders) {
        indent().println("<TR>");
        pushIndent();
        indent().print("<TD>");
        print(header.getHeaderName());
        print("</TD><TD>");
        print(header.getHeaderValue());
        println("</TD>");        
        popIndent();
        indent().println("</TR>");
      }
      popIndent();
      indent().println("</TABLE>");
      printCaption(caption);
    }
  }

  private void printRepresentation() {
    Heading heading = createHeading(doc.getRepresentationHeading());
    print(heading);
    print(doc.getRepresentationText());
    
    
  }

  private void printIntroduction() {
    
    Heading heading = createHeading("Introduction");
    print(heading);
    print(doc.getIntroduction());
    
  }

  private void printAbstract() {
    println("<HR/>");

    String abstractText = doc.getAbstactText();
    if (abstractText == null)
      return;

    indent().println("<H2>Abstract</H2>");
    indent().println("<DIV>");
    print(abstractText);
    println("</DIV>");
    
  }

  private void printAuthors() {

    List<String> authorList = doc.getAuthors();
    if (authorList != null && !authorList.isEmpty()) {
      indent().print("<DIV");
      printAttr("class", "contributorLabel");
      println(">Authors</DIV>");
      for (String author : authorList) {
        indent().print("<DIV");
        printAttr("class", "contributor");
        print(">").print(author).println("</DIV>");
      }
    }
    
  }

  private void printEditors() {
    
    List<String> editorList = doc.getEditors();

    if (editorList != null && !editorList.isEmpty()) {
      indent().print("<DIV");
      printAttr("class", "contributorLabel");
      println(">Editors</DIV>");
      for (String editor : editorList) {
        indent().print("<DIV");
        printAttr("class", "contributor");
        print(">").print(editor).println("</DIV>");
      }
    }
    
  }

  private void printStatus() {
    String status = doc.getStatus();
    if (status == null) return;
    print("<DIV");
    printAttr("class", "subtitle");
    print(">");
    print(status);
    println("</DIV>");
    
    
  }
  private void printSubtitle() {
    String subtitle = doc.getSubtitle();
    if (subtitle == null) return;
    print("<DIV");
    printAttr("class", "subtitle");
    print(">");
    print(subtitle);
    println("</DIV>");
    
    
  }

  private void printTitle() {
    print("<H1>");
    print(doc.getTitle());
    println("</H1>");
  }

  @Override
  protected String getPathToStyleSheet() {
    return doc.getCssHref();
  }

}
