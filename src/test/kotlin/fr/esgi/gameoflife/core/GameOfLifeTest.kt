package fr.esgi.gameoflife.core

import kotlin.math.sqrt
import io.cucumber.datatable.DataTable
import org.junit.Test
import kotlin.test.assertEquals
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When


typealias Row = MutableList<Boolean>
typealias World = MutableList<Row>
typealias State = Boolean

const val DEAD = false
const val LIVE = true

fun getCenterCellState(grid: List<List<State>>): State {
    val center: Int = grid.size / 2
    return grid[center][center]
}

fun convertDatatableToWorld(dataTable: DataTable): World {
    val world: World = mutableListOf<MutableList<State>>()
    val dataTableAsList = dataTable.asList()
    val gridSize = sqrt(dataTableAsList.size.toDouble()).toInt()
    fun convertStringToBoolean(string: String): Boolean {
        return when (string) {
            "." -> DEAD
            else -> LIVE
        }
    }

    var numberOfElement = 0
    val currentLine = mutableListOf<State>()
    dataTableAsList.forEach {
        currentLine.add(convertStringToBoolean(it))
        numberOfElement++
        if (numberOfElement == gridSize) {
            numberOfElement = 0
            world.add(currentLine.toMutableList())
            currentLine.clear()
        }
    }
    return world
}


internal class GameOfLifeTest {

    private val worlds = mutableListOf<World>()

    @Given("the following setup")
    fun dead_cell_with_neighbors(cucumberScenarioInput: DataTable) {
        this.worlds.add(convertDatatableToWorld(cucumberScenarioInput))
    }

    @When("I evolve the board")
    fun evolveTheBoard() {
        this.worlds.add(evolve(this.worlds.last()))
    }

    @Then("the center cell should be dead")
    @Test
    fun center_cell_should_be_dead() {
        assertEquals(getCenterCellState(this.worlds.last()), DEAD)
    }

    @Then("the center cell should be alive")
    @Test
    fun center_cell_should_be_alive() {
        assertEquals(getCenterCellState(this.worlds.last()), LIVE)
    }

    @Then("I should see the following board")
    @Test
    fun next_state_should_look_like_this_state(evolvedStateDataTable: DataTable) {
        val evolvedStateWorld = convertDatatableToWorld(evolvedStateDataTable)
        assertEquals(this.worlds.last(), evolvedStateWorld)
    }
}
