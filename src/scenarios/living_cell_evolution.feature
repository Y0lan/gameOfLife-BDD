Feature: Evolving a living cell
  In order to create a game following the rules correctly
  As a programmer of Conway's Game of Life
  I can evolve a single living cell based on these scenario

  Scenario: Living cell with 0 neighbor dies
    Given this universe
      | . | . | . |
      | . | x | . |
      | . | . | . |
    When I evolve the board
    Then the center cell should be dead

  Scenario: Living cell with 1 neighbor dies
    Given this universe
      | . | x | . |
      | . | x | . |
      | . | . | . |
    When I evolve the board
    Then the center cell should be dead

  Scenario: Living cell with 2 neighbors lives
    Given this universe
      | . | x | . |
      | . | x | x |
      | . | . | . |
    When I evolve the board
    Then the center cell should be alive

  Scenario: Living cell with 3 neighbors lives
    Given this universe
      | x | x | x |
      | . | x | . |
      | . | . | . |
    When I evolve the board
    Then the center cell should be alive

  Scenario: Living cell with 4 neighbors dies
    Given this universe
      | x | x | x |
      | . | x | x |
      | . | . | . |
    When I evolve the board
    Then the center cell should be dead

  Scenario: Living cell with 5 neighbors dies
    Given this universe
      | x | x | x |
      | x | x | x |
      | . | . | . |
    When I evolve the board
    Then the center cell should be dead

  Scenario: Living cell with 6 neighbors dies
    Given this universe
      | x | x | x |
      | x | x | x |
      | x | . | . |
    When I evolve the board
    Then the center cell should be dead

  Scenario: Living cell with 7 neighbors dies
    Given this universe
      | x | x | x |
      | x | x | x |
      | x | x | . |
    When I evolve the board
    Then the center cell should be dead

  Scenario: Living cell with 8 neighbors dies
    Given this universe
      | x | x | x |
      | x | x | x |
      | x | x | x |
    When I evolve the board
    Then the center cell should be dead
