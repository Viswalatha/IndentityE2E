Feature: Get Registration Number from Text file and very Make and Model from Cazoo

  @Test
  Scenario: Get car details and verify make and model
    Given go to the carzoo website
    Then get reg numbers and verify make and model on text file through carzoo site
