Feature: Navigation bar

  #Scenario Outline: I can access the subpages through the navigation bar
 #   Given I navigate to www.freerangetesters.com
  #  When I go to <Section> using the navigation bar
   # Examples:
    #  | Section   |
     # | Cursos    |
      #| Mentorías |
      #| Talleres  |
     # | Blog      |
     # | Recursos  |

  Scenario: Coureses are precented to potential customers
    Given I navigate to www.freerangetesters.com
    When I go to Cursos using the navigation bar
    And select Introducción al Testing


    Scenario: