Feature: felipe

  Scenario: scraping con selenium
    Given usuario ingresa a la web
    When usuario selecciona residentado
    And usuario ingresa a Panopto en otra ventana
    ##When usuario selecciona residentado
    And usuario ingresa a videoteca mis clases
    Then usuario scrapea xd
