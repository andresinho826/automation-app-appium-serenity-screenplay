Feature: Compra de producto en la app Frisby

  Background: Usuario autenticado
    Given que el usuario ha iniciado sesión correctamente
    And está en la Home page

  Scenario: Agregar un combo al carrito y confirmar pedido
    When da click en el botón domicilio
    And selecciona la dirección
    And selecciona una categoría
    And selecciona los acompañantes
    And da click en el botón agregar
    And se dirige al carrito de compra
    And da click en el botón confirmar pedido
    Then el sistema confirma el pedido
