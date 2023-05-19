Feature: Pet Store - Modulo de listado de mascotas

  Background:
    Given usuario se autentica

  @CP01 @CasoPositivo
  Scenario Outline: CP01 - Usuario quiere ver la lista de mascotas filtrada por un determinado tag
    When usuario selecciona la opcion encontrar mascotas por tag
    And usuario ingresa el <tag> a buscar
    And usuario ejecuta la operacion de encontrar mascotas
    Then se recibe una respuesta exitosa del servidor

    Examples:
      | tag      |
      | "string" |

  @CP05 @CasoPositivo
  Scenario Outline: CP05 - Usuario quiere ver la lista de mascotas filtrada por un determinado status
    When usuario selecciona la opcion encontrar mascotas por status
    And usuario selecciona el <status> a buscar
    And usuario ejecuta la operacion de encontrar mascotas
    Then se recibe una respuesta exitosa del servidor

    Examples:
      | status    |
      | "pending" |

  @CP06 @CasoNegativo
  Scenario: CP06 - Usuario quiere ver la lista de mascotas filtrada por un determinado tag pero no introduce valor
    When usuario selecciona la opcion encontrar mascotas por tag
    And usuario ejecuta la operacion de encontrar mascotas
    Then se recibe una respuesta fallida del servidor