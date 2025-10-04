Feature: Registro de usuarios en la app Frisby

  Scenario Outline: Registro exitoso de un nuevo usuario
    Given que el usuario está en la pantalla de Registrarse
    When ingresa los datos del formulario:
      | Número de documento | Correo electrónico | Nombres   | Apellidos   | Contraseña   | Confirmar contraseña      |
      | <documento>         | <email>            | <nombres> | <apellidos> | <contrasena> | <contrasena_confirmacion> |
    And hace clic en el botón Registrar
    Then el sistema debería mostrar el mensaje Bienvenido

    Examples:
      | documento   | email                | nombres | apellidos | contrasena    | contrasena_confirmacion |
      | 0099009900 | andresqa91@gmail.com | Andres  | Fernandez | contrasena123 | contrasena123           |
      | 32158741  | mariaqa@test.com     | Maria   | Lopez     | passTest2024  | passTest2024            |

