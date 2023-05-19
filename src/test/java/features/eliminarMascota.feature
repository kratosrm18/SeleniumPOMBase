Feature: Pet Store - Modulo Eliminar Mascota

  Background:
    Given usuario se autentica

  @CP04 @CasoPositivo
  Scenario Outline: CP04 - Usuario quiere eliminar una mascota
    When usuario selecciona la opcion eliminar mascota
    And usuario ingresa el <id> de la mascota a eliminar
    And usuario ejecuta la operacion eliminar
    Then se recibe una respuesta exitosa del servidor

    Examples:
      | id |
      | 1  |