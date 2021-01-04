package fr.esgi.gameoflife.core

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

internal class GameOfLifeTest {
    private fun getCenterCellState(grid: List<List<State>>): State {
        val center: Int = grid.size / 2
        return grid[center][center]
    }
    private fun given_an_initial_state(): World {
        return mutableListOf(
            mutableListOf(DEAD, DEAD, DEAD),
            mutableListOf(DEAD, DEAD, DEAD),
            mutableListOf(DEAD, DEAD, DEAD)
        )
    }

    private var initialState = given_an_initial_state()
    private var nextState = evolve(initialState)

    private fun convertDatatableToWorld(dataTable: DataTable): World {
        fun convertStringToBoolean(string: String): Boolean {
            return when (string) {
                "." -> DEAD
                else -> LIVE
            }
        }

        val world = mutableListOf(
            mutableListOf(DEAD,DEAD,DEAD),
            mutableListOf(DEAD,DEAD,DEAD),
            mutableListOf(DEAD,DEAD,DEAD)
        )
        val list = dataTable.asList()
        var row = 0;
        var col = 0;
        list.forEach {
            if (row == 3) row = 0;
            if (col == 3) {
                col = 0;
                row++;
            }
            world[row][col++] = convertStringToBoolean(it);
        }
        return world
    }

    @Given("this universe")
    fun dead_cell_with_neighbors(cucumberScenarioInput: DataTable) {
        this.initialState = convertDatatableToWorld(cucumberScenarioInput)
    }

    @When("I evolve the board")
    fun evolveTheBoard() {
        this.nextState = evolve(this.initialState)
    }

    @Then("the center cell should be dead")
    @Test
    fun center_cell_should_be_dead(){
        assertEquals(getCenterCellState(this.nextState), DEAD)
    }

    @Then("the center cell should be alive")
    @Test
    fun center_cell_should_be_alive(){
        assertEquals(getCenterCellState(this.nextState), LIVE)
    }
}
