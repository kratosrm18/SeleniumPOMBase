Feature: Pet Store - Modulo Crear Mascota

  Background:
    Given usuario se autentica

  @CP02 @CasoPositivo
  Scenario: CP02 - Usuario quiere agregar una nueva mascota a la tienda con valores por defecto
    When usuario selecciona la opcion agregar nueva mascota
    And usuario ejecuta la operacion de creacion
    Then se recibe una respuesta exitosa del servidor

  #No entiendo bien el contexto de los parametros que se necesitan, hablando respecto al negocio
  #Asumiré que todos los parametros son seleccionables o ingresables
  #Entiendo que en la practica debería por lo menos los ID deberian ser autogranerables
  @CP07 @CasoPositivo
  Scenario Outline: CP07 - Usuario quiere agregar una nueva mascota a la tienda con valores personalizados
    When usuario selecciona la opcion agregar nueva mascota
    And usuario limpia los parametros por defecto
    And usuario selecciona <id> de la mascota
    And usuario introduce el <nombre> de la mascota
    And usuario selecciona <idCategoria> de la cateogria
    And usuario selecciona <nombreCategoria> de la categoria
    And usuario ingresa el <url> de la foto de la mascota
    And usuario ingresa <tag> de la mascota
    And usuario selecciona el <status> de la mascota
    And usuario establece los parametros
    And usuario ejecuta la operacion de creacion
    Then se recibe una respuesta exitosa del servidor

    Examples:
      | id | nombre  | idCategoria | nombreCategoria | url                  | tag      | status    |
      | 25 | "Michi" | 2           | "Cats"          | "/./photo_michi.png" | "tagIDK" | "pending" |

  @CP08 @CasoNegativo
  Scenario Outline: CP08 - Usuario quiere agregar una nueva mascota a la tienda con valores erroneos
    When usuario selecciona la opcion agregar nueva mascota
    And usuario limpia los parametros por defecto
    And usuario selecciona <id> erroneo de la mascota
    And usuario introduce el <nombre> de la mascota
    And usuario selecciona <idCategoria> de la cateogria
    And usuario selecciona <nombreCategoria> de la categoria
    And usuario ingresa el <url> de la foto de la mascota
    And usuario ingresa <tag> de la mascota
    And usuario selecciona el <status> de la mascota
    And usuario establece los parametros
    And usuario ejecuta la operacion de creacion
    Then se recibe una respuesta fallida del servidor

    Examples:
      | id  | nombre  | idCategoria | nombreCategoria | url                  | tag      | status    |
      | "X" | "Michi" | 2           | "Cats"          | "/./photo_michi.png" | "tagIDK" | "pending" |