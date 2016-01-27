
/*
 * The JTS Topology Suite is a collection of Java classes that
 * implement the fundamental operations required to validate a given
 * geo-spatial data set to a known topological specification.
 * 
 * Copyright (C) 2016 Vivid Solutions
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * (http://www.eclipse.org/legal/epl-v10.html), and the Vivid Solutions BSD
 * License v1.0 (found at the root of the repository).
 * 
 */

package com.vividsolutions.jtstest;

import java.io.File;
import java.util.Iterator;
import java.util.StringTokenizer;

import junit.framework.TestCase;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jtstest.testbuilder.JTSTestBuilderFrame;
import com.vividsolutions.jtstest.testbuilder.model.TestRunnerTestCaseAdapter;
import com.vividsolutions.jtstest.testbuilder.model.XMLTestWriter;
import com.vividsolutions.jtstest.testrunner.TestReader;
import com.vividsolutions.jtstest.testrunner.TestRun;
import com.vividsolutions.jtstest.util.StringUtil;


/**
 * @version 1.7
 */
// MD - all tests disabled for now, since input data is missing
public class TestReaderTest extends TestCase {

  public TestReaderTest(String Name_) {
    super(Name_);
  }

  public static void main(String[] args) {
    String[] testCaseName = {TestReaderTest.class.getName()};
    junit.textui.TestRunner.main(testCaseName);
  }

  public void testDummy()
  {
    
  }
  
  public void XtestWktFile() {
    TestReader testReader = new TestReader();
    TestRun testRun = testReader.createTestRun(new File(
        "\\\\pluto\\data\\jts\\testing\\testreader_wktfile.xml"), 0);
    printParsingProblems(testReader);
    assertNull(testRun.getWorkspace());
    assertEquals(1, testRun.getTestCases().size());
    com.vividsolutions.jtstest.testrunner.TestCase testCase = (com.vividsolutions.jtstest.testrunner.TestCase) testRun.getTestCases().get(0);
    assertTrue(testCase.getGeometryA().equals(new GeometryFactory().createPoint(new Coordinate(10, 20))));
    assertTrue(testCase.getGeometryB().equals(new GeometryFactory().createPoint(new Coordinate(30, 40))));

    XMLTestWriter xmlTestWriter = new XMLTestWriter();

    String expectedXML =
        "<case>" + StringUtil.newLine +
        "  <desc>same point</desc>" + StringUtil.newLine +
        "  <a file=\"\\\\pluto\\data\\jts\\testing\\testreader_wktfile_point.xml\"/>" + StringUtil.newLine +
        "  <b>" + StringUtil.newLine +
        "    POINT (30 40)" + StringUtil.newLine +
        "  </b>" + StringUtil.newLine +
        "<test>" + StringUtil.newLine +
        "  <op name=\"relate\" arg1=\"A\" arg2=\"B\" arg3=\"0FFFFFFF2\">" + StringUtil.newLine +
        "    true" + StringUtil.newLine +
        "  </op>" + StringUtil.newLine +
        "</test>" + StringUtil.newLine +
        "</case>" + StringUtil.newLine;
    assertEquals(normalize(expectedXML), normalize(xmlTestWriter.getTestXML(new TestRunnerTestCaseAdapter(testCase))));

    testCase.setGeometryA(new GeometryFactory().createPoint(new Coordinate(2, 3)));
    expectedXML =
        "<case>" + StringUtil.newLine +
        "  <desc>same point</desc>" + StringUtil.newLine +
        "  <a>" + StringUtil.newLine +
        "    POINT (2 3)" + StringUtil.newLine +
        "  </a>" + StringUtil.newLine +
        "  <b>" + StringUtil.newLine +
        "    POINT (30 40)" + StringUtil.newLine +
        "  </b>" + StringUtil.newLine +
        "<test>" + StringUtil.newLine +
        "  <op name=\"relate\" arg1=\"A\" arg2=\"B\" arg3=\"0FFFFFFF2\">" + StringUtil.newLine +
        "    true" + StringUtil.newLine +
        "  </op>" + StringUtil.newLine +
        "</test>" + StringUtil.newLine +
        "</case>" + StringUtil.newLine;
    assertEquals(normalize(expectedXML), normalize(xmlTestWriter.getTestXML(new TestRunnerTestCaseAdapter(testCase))));
  }

  public void XtestWktFileWorkspace() {
    TestReader testReader = new TestReader();
    TestRun testRun = testReader.createTestRun(new File(
        "\\\\pluto\\data\\jts\\testing\\testReader_wktFileWorkspace.xml"), 0);
    printParsingProblems(testReader);
    assertEquals("\\\\pluto\\data\\jts\\testing\\x", testRun.getWorkspace().toString());
    assertEquals(1, testRun.getTestCases().size());
    com.vividsolutions.jtstest.testrunner.TestCase testCase = (com.vividsolutions.jtstest.testrunner.TestCase) testRun.getTestCases().get(0);
    assertTrue(testCase.getGeometryA().equals(new GeometryFactory().createPoint(new Coordinate(5, 6))));
    assertTrue(testCase.getGeometryB().equals(new GeometryFactory().createPoint(new Coordinate(7, 8))));

    XMLTestWriter xmlTestWriter = new XMLTestWriter();

    String expectedXML =
        "<case>" + StringUtil.newLine +
        "  <desc>same point</desc>" + StringUtil.newLine +
        "  <a file=\"testReader_wktFileWorkspace_point.xml\"/>" + StringUtil.newLine +
        "  <b>" + StringUtil.newLine +
        "    POINT (7 8)" + StringUtil.newLine +
        "  </b>" + StringUtil.newLine +
        "<test>" + StringUtil.newLine +
        "  <op name=\"relate\" arg1=\"A\" arg2=\"B\" arg3=\"0FFFFFFF2\">" + StringUtil.newLine +
        "    true" + StringUtil.newLine +
        "  </op>" + StringUtil.newLine +
        "</test>" + StringUtil.newLine +
        "</case>" + StringUtil.newLine;
    assertEquals(normalize(expectedXML), normalize(xmlTestWriter.getTestXML(new TestRunnerTestCaseAdapter(testCase))));

    testCase.setGeometryA(new GeometryFactory().createPoint(new Coordinate(9, 10)));
    expectedXML =
        "<case>" + StringUtil.newLine +
        "  <desc>same point</desc>" + StringUtil.newLine +
        "  <a>" + StringUtil.newLine +
        "    POINT (9 10)" + StringUtil.newLine +
        "  </a>" + StringUtil.newLine +
        "  <b>" + StringUtil.newLine +
        "    POINT (7 8)" + StringUtil.newLine +
        "  </b>" + StringUtil.newLine +
        "<test>" + StringUtil.newLine +
        "  <op name=\"relate\" arg1=\"A\" arg2=\"B\" arg3=\"0FFFFFFF2\">" + StringUtil.newLine +
        "    true" + StringUtil.newLine +
        "  </op>" + StringUtil.newLine +
        "</test>" + StringUtil.newLine +
        "</case>" + StringUtil.newLine;
    assertEquals(normalize(expectedXML), normalize(xmlTestWriter.getTestXML(new TestRunnerTestCaseAdapter(testCase))));
  }

  public void XtestWktFileWorkspace2() {
    TestReader testReader = new TestReader();
    TestRun testRun = testReader.createTestRun(new File(
        "\\\\pluto\\data\\jts\\testing\\testReader_wktFileWorkspace2.xml"), 0);
    printParsingProblems(testReader);
    assertNull(testRun.getWorkspace());
    assertEquals(1, testRun.getTestCases().size());
    com.vividsolutions.jtstest.testrunner.TestCase testCase = (com.vividsolutions.jtstest.testrunner.TestCase) testRun.getTestCases().get(0);
    assertTrue(testCase.getGeometryA().equals(new GeometryFactory().createPoint(new Coordinate(3, 4))));
    assertTrue(testCase.getGeometryB().equals(new GeometryFactory().createPoint(new Coordinate(1, 2))));

    XMLTestWriter xmlTestWriter = new XMLTestWriter();

    String expectedXML =
        "<case>" + StringUtil.newLine +
        "  <desc>same point</desc>" + StringUtil.newLine +
        "  <a file=\"testReader_wktFileWorkspace2_point.xml\"/>" + StringUtil.newLine +
        "  <b>" + StringUtil.newLine +
        "    POINT (1 2)" + StringUtil.newLine +
        "  </b>" + StringUtil.newLine +
        "<test>" + StringUtil.newLine +
        "  <op name=\"relate\" arg1=\"A\" arg2=\"B\" arg3=\"0FFFFFFF2\">" + StringUtil.newLine +
        "    true" + StringUtil.newLine +
        "  </op>" + StringUtil.newLine +
        "</test>" + StringUtil.newLine +
        "</case>" + StringUtil.newLine;
    assertEquals(normalize(expectedXML), normalize(xmlTestWriter.getTestXML(new TestRunnerTestCaseAdapter(testCase))));

    testCase.setGeometryA(new GeometryFactory().createPoint(new Coordinate(11, 12)));
    expectedXML =
        "<case>" + StringUtil.newLine +
        "  <desc>same point</desc>" + StringUtil.newLine +
        "  <a>" + StringUtil.newLine +
        "    POINT (11 12)" + StringUtil.newLine +
        "  </a>" + StringUtil.newLine +
        "  <b>" + StringUtil.newLine +
        "    POINT (1 2)" + StringUtil.newLine +
        "  </b>" + StringUtil.newLine +
        "<test>" + StringUtil.newLine +
        "  <op name=\"relate\" arg1=\"A\" arg2=\"B\" arg3=\"0FFFFFFF2\">" + StringUtil.newLine +
        "    true" + StringUtil.newLine +
        "  </op>" + StringUtil.newLine +
        "</test>" + StringUtil.newLine +
        "</case>" + StringUtil.newLine;
    assertEquals(normalize(expectedXML), normalize(xmlTestWriter.getTestXML(new TestRunnerTestCaseAdapter(testCase))));
  }

  public void XtestGetWorkspaceXML() throws Exception {
    JTSTestBuilderFrame.instance().openXmlFilesAndDirectories(new File[] {new File(
        "\\\\pluto\\data\\jts\\testing\\testReader_getWorkspaceXML.xml")});
    String expectedXML =
        "<run>" +
        "  <desc>ABCDEF</desc>" +
        "  <workspace file=\"\\\\pluto\\data\\jts\\testing\"/>" +
        "  <precisionModel type=\"FLOATING\"/>" +
        "  <case>" +
        "    <a>POINT (5 6)</a>" +
        "    <b>POINT (7 8)</b>" +
        "  <test>" +
        "    <op name=\"relate\" arg1=\"A\" arg2=\"B\" arg3=\"0FFFFFFF2\">" +
        "    true" +
        "  </op>" +
        "  </test>" +
        "  </case>" +
        "</run>";
    assertEquals(normalize(expectedXML), normalize(JTSTestBuilderFrame.instance().getRunXml()));
  }

  public void XtestPrecisionModel_noType_scale() {
    TestReader testReader = new TestReader();
    TestRun testRun = testReader.createTestRun(new File(
        "\\\\pluto\\data\\jts\\testing\\precisionModel_noType_scale.xml"), 0);
    assertNotNull(testRun);
    assertTrue(! testRun.getPrecisionModel().isFloating());
    assertEquals(1, testRun.getPrecisionModel().getScale(), 1E-15);
  }

  public void XtestPrecisionModel_noType_noScale() {
    TestReader testReader = new TestReader();
    TestRun testRun = testReader.createTestRun(new File(
        "\\\\pluto\\data\\jts\\testing\\precisionModel_noType_noScale.xml"), 0);
    assertNull(testRun);
    assertTrue(testReader.getParsingProblems().get(0).toString().indexOf("Missing type attribute in <precisionModel>") > -1);
  }

  public void XtestPrecisionModel_fixed_scale() {
    TestReader testReader = new TestReader();
    TestRun testRun = testReader.createTestRun(new File(
        "\\\\pluto\\data\\jts\\testing\\precisionModel_fixed_scale.xml"), 0);
    assertNotNull(testRun);
    assertTrue(! testRun.getPrecisionModel().isFloating());
    assertEquals(1, testRun.getPrecisionModel().getScale(), 1E-15);
  }

  public void XtestPrecisionModel_fixed_noScale() {
    TestReader testReader = new TestReader();
    TestRun testRun = testReader.createTestRun(new File(
        "\\\\pluto\\data\\jts\\testing\\precisionModel_fixed_noScale.xml"), 0);
    assertNull(testRun);
    assertTrue(testReader.getParsingProblems().get(0).toString().indexOf("Missing scale attribute in <precisionModel>") > -1);
  }

  public void XtestPrecisionModel_floating_scale() {
    TestReader testReader = new TestReader();
    TestRun testRun = testReader.createTestRun(new File(
        "\\\\pluto\\data\\jts\\testing\\precisionModel_floating_scale.xml"), 0);
    assertNull(testRun);
    assertTrue(testReader.getParsingProblems().get(0).toString().indexOf("scale attribute not allowed in floating <precisionModel>") > -1);
  }

  public void XtestPrecisionModel_floating_noScale() {
    TestReader testReader = new TestReader();
    TestRun testRun = testReader.createTestRun(new File(
        "\\\\pluto\\data\\jts\\testing\\precisionModel_floating_noScale.xml"), 0);
    assertNotNull(testRun);
    assertTrue(testRun.getPrecisionModel().isFloating());
    assertEquals(0, testRun.getPrecisionModel().getScale(), 1E-15);
  }

  private void printParsingProblems(TestReader testReader) {
    for (Iterator i = testReader.getParsingProblems().iterator(); i.hasNext(); ) {
      String problem = (String) i.next();
      System.out.println(problem);
    }
  }

  private String normalize(String xml) {
    String normalizedXML = "";
    StringTokenizer tokenizer = new StringTokenizer(xml.toUpperCase());
    while (tokenizer.hasMoreTokens()) {
      normalizedXML += tokenizer.nextToken();
    }
    return normalizedXML;
  }

}