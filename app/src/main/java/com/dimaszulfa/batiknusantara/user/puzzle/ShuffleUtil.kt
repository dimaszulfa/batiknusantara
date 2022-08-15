package com.dimaszulfa.batiknusantara.user.puzzle


class ShuffleUtil {
    /**
     * Companion object containing the methods for shuffling the puzzle tiles.
     */
    companion object {

        fun getValidShuffledState(
            puzzleState: ArrayList<Int>,
            goalPuzzleState: ArrayList<Int>,
            blankTileMarker: Int
        ): StatePair {
            while (puzzleState == goalPuzzleState) {
                puzzleState.shuffle()


                if (!PuzzleUtil.isSolvable(puzzleState, blankTileMarker)) {
                    PuzzleUtil.swapTiles(puzzleState, blankTileMarker)
                }
            }

            return StatePair(puzzleState, puzzleState.indexOf(blankTileMarker))
        }
    }
}