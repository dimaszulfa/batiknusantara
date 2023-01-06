package com.dimaszulfa.batiknusantara.ui.puzzle

import com.dimaszulfa.batiknusantara.util.PuzzleUtil
import com.dimaszulfa.batiknusantara.util.StatePair


class ShuffleAlgorithm {

    companion object {

        fun getValidShuffledState(
            puzzleState: ArrayList<Int>,
            goalPuzzleState: ArrayList<Int>,
            blankTileMarker: Int
        ): StatePair {
            while (puzzleState == goalPuzzleState) {
                algoritmaFisherYates(puzzleState)


                if (!PuzzleUtil.isSolvable(puzzleState, blankTileMarker)) {
                    PuzzleUtil.swapTiles(puzzleState, blankTileMarker)
                }
            }

            return StatePair(puzzleState, puzzleState.indexOf(blankTileMarker))
        }

        private fun algoritmaFisherYates(puzzleState: ArrayList<Int>) {
            val x_s = 0
            var x_f = puzzleState.size - 1

            while (x_s != x_f){
                val random = puzzleState.random()
                val shuffle = puzzleState[random]
                puzzleState[random] = puzzleState[x_f]
                puzzleState[x_f] = shuffle
                x_f -= 1
            }
        }
    }

}