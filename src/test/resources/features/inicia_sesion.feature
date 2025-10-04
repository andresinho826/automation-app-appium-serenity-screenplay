Feature: Inicio sesion de usuarios en la app Frisby

  Scenario Outline: Inicio sesion de un usuario
    Given que el usuario está en la pantalla de Inicio de sesion
    When ingresa credenciales "<usuario>" "<contrasena>"
    And hace clic en el botón inicia sesion
    Then el sistema debería iniciar sesion exitosamente

    Examples:
      | usuario              | contrasena    |
      | andresqa91@gmail.com | contrasena123 |

