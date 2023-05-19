Feature: Pet Store - Modulo Actualizar Mascota

  Background:
    Given usuario se autentica

  @CP03 @CasoPositivo
  Scenario: CP03 - Usuario quiere actualizar datos de una mascota (con valores por defecto)
    When usuario selecciona la opcion actualizar mascota
    And usuario ejecuta la operacion actualizar
    Then se recibe una respuesta exitosa del servidor

  @CP09 @CasoPositivo
  Scenario Outline: CP09 - Usuario quiere actualizar datos de una mascota (con valores personalizados)
    When usuario selecciona la opcion actualizar mascota
    And usuario limpia los parametros por defecto
    And usuario selecciona <id> de la mascota
    And usuario introduce el <nombre> de la mascota
    And usuario selecciona <idCategoria> de la cateogria
    And usuario selecciona <nombreCategoria> de la categoria
    And usuario ingresa el <url> de la foto de la mascota
    And usuario ingresa <tag> de la mascota
    And usuario selecciona el <status> de la mascota
    And usuario establece los parametros
    And usuario ejecuta la operacion actualizar
    Then se recibe una respuesta exitosa del servidor

    Examples:
      | id | nombre  | idCategoria | nombreCategoria | url                  | tag      | status    |
      | 25 | "Michi" | 2           | "Cats"          | "/./photo_michi.png" | "tagIDK" | "pending" |

  @CP10 @CasoNegativo
  Scenario Outline: CP10 - Usuario quiere actualizar datos de una mascota
    When usuario selecciona la opcion actualizar mascota
    And usuario limpia los parametros por defecto
    And usuario selecciona <id> erroneo de la mascota
    And usuario introduce el <nombre> de la mascota
    And usuario selecciona <idCategoria> de la cateogria
    And usuario selecciona <nombreCategoria> de la categoria
    And usuario ingresa el <url> de la foto de la mascota
    And usuario ingresa <tag> de la mascota
    And usuario selecciona el <status> de la mascota
    And usuario establece los parametros
    And usuario ejecuta la operacion actualizar
    Then se recibe una respuesta fallida del servidor

    Examples:
      | id  | nombre  | idCategoria | nombreCategoria | url                  | tag      | status    |
      | "X" | "Michi" | 2           | "Cats"          | "/./photo_michi.png" | "tagIDK" | "pending" |