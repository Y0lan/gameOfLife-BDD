package fr.esgi.gameoflife.core

import arrow.syntax.function.andThen
import arrow.syntax.function.curried

typealias State = Boolean
typealias Row = MutableList<State>
typealias World = MutableList<Row>

private const val DEAD = false
private const val LIVE = true

data class Position(val row: Int, val column: Int)

val translate =
    { first: Position, other: Position -> Position(first.row + other.row, first.column + other.column) }.curried()

val getCellValue =
    { world: World, position: Position -> world.getOrNull(position.row)?.getOrNull(position.column) ?: DEAD }.curried()

fun evolve(world: World): List<List<State>> {
    return world.mapIndexed { row, list ->
        list.mapIndexed { col, cell ->
            val evolveCell = countAliveNeighbours(world) andThen computeNextState(cell)
            evolveCell(Position(row, col))
        }
    }
}

val countAliveNeighbours = { world: World, position: Position ->
    val neighbourPositions = listOf(
        Position(-1, -1),
        Position(-1, 0),
        Position(-1, 1),
        Position(0, -1),
        Position(0, 1),
        Position(1, -1),
        Position(1, 0),
        Position(1, 1)
    )
    neighbourPositions
        .map(translate(position) andThen getCellValue(world))
        .filter(::isAlive)
        .count()
}.curried()

val computeNextState = { cell: State, numberOfAliveNeighbours: Int ->
    when (numberOfAliveNeighbours) {
        2 -> cell
        3 -> LIVE
        else -> DEAD
    }
}.curried()

fun isAlive(cell: State) = cell == LIVE
