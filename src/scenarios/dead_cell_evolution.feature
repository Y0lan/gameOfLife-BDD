Feature: Evolving a dead cell
  In order to create a functioning rules engine
  As a programmer of Conway's Game of Life
  I can evolve a single dead cell based

  Scenario: Dead cell with 0 neighbors stays dead
    Given the following setup
      | . | . | . |
      | . | . | . |
      | . | . | . |
    When I evolve the board
    Then the center cell should be dead

  Scenario: Dead cell with 1 neighbors stays dead
    Given the following setup
      | . | x | . |
      | . | . | . |
      | . | . | . |
    When I evolve the board
    Then the center cell should be dead

  Scenario: Dead cell with 2 neighborss stays dead
    Given the following setup
      | . | x | . |
      | . | . | x |
      | . | . | . |
    When I evolve the board
    Then the center cell should be dead

  Scenario: Dead cell with 3 neighborss comes to life
    Given the following setup
      | x | x | x |
      | . | . | . |
      | . | . | . |
    When I evolve the board
    Then the center cell should be alive

  Scenario: Dead cell with 4 neighborss stays dead
    Given the following setup
      | x | x | x |
      | . | . | x |
      | . | . | . |
    When I evolve the board
    Then the center cell should be dead

  Scenario: Dead cell with 5 neighborss stays dead
    Given the following setup
      | x | x | x |
      | x | . | x |
      | . | . | . |
    When I evolve the board
    Then the center cell should be dead

  Scenario: Dead cell with 6 neighborss stays dead
    Given the following setup
      | x | x | x |
      | x | . | x |
      | x | . | . |
    When I evolve the board
    Then the center cell should be dead

  Scenario: Dead cell with 7 neighborss stays dead
    Given the following setup
      | x | x | x |
      | x | . | x |
      | x | x | . |
    When I evolve the board
    Then the center cell should be dead

  Scenario: Dead cell with 8 neighborss stays dead
    Given the following setup
      | x | x | x |
      | x | . | x |
      | x | x | x |
    When I evolve the board
    Then the center cell should be dead