Feature: Prueba
#  Scenario Outline: obtener todos los posts de una api externa
#    Given nothing
#    When i look for the posts
#    Then i should get status <status>
#    Then body shouldn´t be empty
#
#    Examples:
#    |status|
#    |200   |
#
#  Scenario Outline: obtener post para un id dado
#    Given post id <id>
#    When i look for the post
#    Then i should get status <status>
#
#    Examples:
#      |id|status|
#      |1 |200   |
#
#  Scenario Outline: obtener los comentarios de un post
#    Given post id <id>
#    When i look for the comments
#    Then i should get status <status>
#    Then body shouldn´t be empty
#
#    Examples:
#      |id|status|
#      |1 |200   |
#
#  Scenario Outline: obtener los commetns por PostId
#    Given postId <postId>
#    When i look for the comments by PostId
#    Then i should get status <status>
#    Then body shouldn´t be empty
#
#    Examples:
#      |postId|status|
#      |1     |200   |
#
#  Scenario Outline: insertar un post
#    Given post id <id>
#    Given postId <postId>
#    Given title <title>
#    Given post body <body>
#    When i insert the post
#    Then i should get status <status>
#
#    Examples:
#      |id|postId|title   |body   |status|
#      |1 |1     |my title|my body|200   |
#
#  Scenario Outline: modifica un post
#    Given post id <id>
#    Given postId <postId>
#    Given title <title>
#    Given post body <body>
#    When i put the post
#    Then i should get status <status>
#
#    Examples:
#      |id|postId|title   |body   |status|
#      |1 |1     |my title|my body|200   |
#
#  Scenario Outline: cambia un post
#    Given post id <id>
#    Given postId <postId>
#    Given title <title>
#    When i patch the post
#    Then i should get status <status>
#
#    Examples:
#      |id|postId|title   |body   |status|
#      |1 |1     |my title|my body|200   |

  Scenario Outline: elimina un post
    Given post id <id>
    When i delete the post
    Then i should get status <status>

    Examples:
      |id|status|
      |1 |200   |