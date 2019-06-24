$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/java/com/sample/tests/features/Sample.feature");
formatter.feature({
  "name": "Sample search",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "Basic search scenario",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "I am on the \"Search\" page",
  "keyword": "Given "
});
formatter.step({
  "name": "I enter \"\u003cDestination\u003e\" text into the \"Destination\" field",
  "keyword": "When "
});
formatter.step({
  "name": "click on the \"Today\u0027s date\" element",
  "keyword": "And "
});
formatter.step({
  "name": "click on the \"\u003cType\u003e\" element",
  "keyword": "And "
});
formatter.step({
  "name": "click on the \"Search\" button",
  "keyword": "And "
});
formatter.step({
  "name": "I should see the \"Search Results\" screen",
  "keyword": "Then "
});
formatter.step({
  "name": "the \"Title\" field has \"\u003cDestination\u003e\" text",
  "keyword": "And "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "Destination",
        "Type"
      ]
    },
    {
      "cells": [
        "Leeds",
        "Business"
      ]
    },
    {
      "cells": [
        "Manchester",
        "Leisure"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Basic search scenario",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "I am on the \"Search\" page",
  "keyword": "Given "
});
formatter.match({
  "location": "SampleSteps.i_am_on_the_page(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I enter \"Leeds\" text into the \"Destination\" field",
  "keyword": "When "
});
formatter.match({
  "location": "SampleSteps.i_enter_text_into_the_field(String,String)"
});
formatter.result({
  "error_message": "java.lang.NullPointerException\n\tat java.util.Objects.requireNonNull(Objects.java:203)\n\tat org.openqa.selenium.support.ui.FluentWait.\u003cinit\u003e(FluentWait.java:106)\n\tat org.openqa.selenium.support.ui.WebDriverWait.\u003cinit\u003e(WebDriverWait.java:85)\n\tat org.openqa.selenium.support.ui.WebDriverWait.\u003cinit\u003e(WebDriverWait.java:45)\n\tat com.sample.ui.controls.Control.waitUntil(Control.java:109)\n\tat com.sample.ui.controls.Control.exists(Control.java:119)\n\tat com.sample.ui.controls.Control.exists(Control.java:122)\n\tat com.sample.ui.controls.Control.click(Control.java:160)\n\tat com.sample.tests.controls.LocationLookupEdit.setText(LocationLookupEdit.java:19)\n\tat com.sample.tests.steps.SampleSteps.i_enter_text_into_the_field(SampleSteps.java:24)\n\tat ✽.I enter \"Leeds\" text into the \"Destination\" field(file:src/test/java/com/sample/tests/features/Sample.feature:5)\n",
  "status": "failed"
});
formatter.step({
  "name": "click on the \"Today\u0027s date\" element",
  "keyword": "And "
});
formatter.match({
  "location": "SampleSteps.click_on_the_element(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "click on the \"Business\" element",
  "keyword": "And "
});
formatter.match({
  "location": "SampleSteps.click_on_the_element(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "click on the \"Search\" button",
  "keyword": "And "
});
formatter.match({
  "location": "SampleSteps.click_on_the_element(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "I should see the \"Search Results\" screen",
  "keyword": "Then "
});
formatter.match({
  "location": "SampleSteps.i_should_see_the_screen(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "the \"Title\" field has \"Leeds\" text",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.scenario({
  "name": "Basic search scenario",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "I am on the \"Search\" page",
  "keyword": "Given "
});
formatter.match({
  "location": "SampleSteps.i_am_on_the_page(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I enter \"Manchester\" text into the \"Destination\" field",
  "keyword": "When "
});
formatter.match({
  "location": "SampleSteps.i_enter_text_into_the_field(String,String)"
});
formatter.result({
  "error_message": "java.lang.NullPointerException\n\tat java.util.Objects.requireNonNull(Objects.java:203)\n\tat org.openqa.selenium.support.ui.FluentWait.\u003cinit\u003e(FluentWait.java:106)\n\tat org.openqa.selenium.support.ui.WebDriverWait.\u003cinit\u003e(WebDriverWait.java:85)\n\tat org.openqa.selenium.support.ui.WebDriverWait.\u003cinit\u003e(WebDriverWait.java:45)\n\tat com.sample.ui.controls.Control.waitUntil(Control.java:109)\n\tat com.sample.ui.controls.Control.exists(Control.java:119)\n\tat com.sample.ui.controls.Control.exists(Control.java:122)\n\tat com.sample.ui.controls.Control.click(Control.java:160)\n\tat com.sample.tests.controls.LocationLookupEdit.setText(LocationLookupEdit.java:19)\n\tat com.sample.tests.steps.SampleSteps.i_enter_text_into_the_field(SampleSteps.java:24)\n\tat ✽.I enter \"Manchester\" text into the \"Destination\" field(file:src/test/java/com/sample/tests/features/Sample.feature:5)\n",
  "status": "failed"
});
formatter.step({
  "name": "click on the \"Today\u0027s date\" element",
  "keyword": "And "
});
formatter.match({
  "location": "SampleSteps.click_on_the_element(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "click on the \"Leisure\" element",
  "keyword": "And "
});
formatter.match({
  "location": "SampleSteps.click_on_the_element(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "click on the \"Search\" button",
  "keyword": "And "
});
formatter.match({
  "location": "SampleSteps.click_on_the_element(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "I should see the \"Search Results\" screen",
  "keyword": "Then "
});
formatter.match({
  "location": "SampleSteps.i_should_see_the_screen(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "the \"Title\" field has \"Manchester\" text",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
});