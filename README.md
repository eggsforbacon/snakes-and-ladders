# README: Snakes and Ladders

---
#### Made with <3 by [@1KVueltasAlCampo](https://www.github.com/1KVueltasAlCampo) and [zacwastaken](https://www.github.com/zacwastaken)

---
    
### About the Game:

This is an emulation of the original Snakes and Ladders game. The game plays out with a
fixed number of players, represented in-game by a <b>"O"</b> of one of the following colors:

1. ![#c1272d](https://via.placeholder.com/15/c1272d/000000?text=+) `#c1272d` as "RED"
2. ![#f15a24](https://via.placeholder.com/15/f15a24/000000?text=+) `#f15a24` as "ORANGE"
3. ![#0283ba](https://via.placeholder.com/15/0283ba/000000?text=+) `#0283ba` as "CYAN"
4. ![#00008e](https://via.placeholder.com/15/00008e/000000?text=+) `#00008e` as "DARK BLUE"
5. ![#c29b02](https://via.placeholder.com/15/c29b02/000000?text=+) `#c29b02` as "YELLOW"
6. ![#39b54a](https://via.placeholder.com/15/39b54a/000000?text=+) `#39b54a` as "GREEN"
7. ![#dd3358](https://via.placeholder.com/15/dd3358/000000?text=+) `#dd3358` as "PINK"
8. ![#93278f](https://via.placeholder.com/15/93278f/000000?text=+) `#93278f` as "PURPLE"
9. ![#a0bf00](https://via.placeholder.com/15/a0bf00/000000?text=+) `#a0bf00` as "LIME"

The game has the possibility to run by itself by rolling the dice every second. The game can be 
played by up to <b>9</b> players and at most a board of 14x14 (The recommended range for the board
is of 4x4 - 10x10, although going up to 14x14 will load the game at the cost of more loading time).

### Main Menu 

The main menu has 3 options: "New Game", "Leaderboard" and "Exit". The former option creates a new
game, and asks the user to input the size of the board, and the amount of snakes and ladders that the
game will have. It also asks for the user to determine the colors that will be playing. The game will
warn the user if there are less than 2 players selected or if the size exceeds the recommended. Once
the game has started, a timer will start running, and at that point the user has 4 options: roll the
dice, "Auto-RUN!", end the game or restart it.

---

### Game

#### Roll the Dice

By rolling the dice, the user will move one piece at a time, and it will show accordingly. The board
represents every ladder with a green number that will appear twice on the board. If a player steps on
the first occurrence of said number on the board, the game will move the player to the other occurrence.
It also represents every snake as a red capital letter, who behave similarly to the ladders, except
they react to the player stepping on the last occurrence of the letter that represents the snake. Once a
player reaches (or exceeds) the end, the game will halt the timer, and request a name to identify the
winner with. Once the name is confirmed, the winner is stored in memory along with its score and the 
game ends, closing the board and relaunching the main menu.

#### Auto-RUN!

This option will run the game automatically and will roll the dice every second. Every rule described
previously still applies to this option.

#### End and Restart

Ending the game will cut the execution of the game prematurely and will not save the winner.
Restarting the game will prompt the user again for the board size, the players and the snakes and ladders
for the new game.

### Board

The board is displayed as a grid with a checkerboard pattern, with every tile on it labeled with a number
in black in a snake fashion (left to right, up, right to left, up and so on). The game pieces will be displayed
at the center of the tile they are currently in, and, if any, the snake / ladder label will be at the bottom
of each tile. There is a sidebar at the right of the screen with the Auto-RUN! button, the dice, the restart and
end game buttons, the list of colors playing, and the timer.

### Leaderboard

The second option in the main menu will open a list of names when clicked. This list contains the names
of the winners in chronological order. When an entry is right-clicked, a window containing the score, and
the name of the player that was clicked will open up. Pressing the return button at the bottom of the leaderboard
window will close it and relaunch the main menu.

Finally, the exit button on the main menu will close the game immediately.

---

## Known Bugs

- Inconsistent addition of winners to leaderboard.
- Board creation may bug out (extremely rare bug)

---

##### This document is part of a complete project that is uploaded on GitHub. If you found it elsewhere on the web, please refer to [this GitHub project](https://github.com/zacwastaken/snakes-and-ladders).

---

### ScreenShots

![This is the game's preloader](/docs/demo/preloader.png?raw=true "Preloader")

![This is the main menu](/docs/demo/main-menu.png?raw=true "Main menu")

![Create a new game](/docs/demo/new-game.png?raw=true "New Game")

![Board of new game](/docs/demo/board.png?raw=true "Fresh board")

![Board of a game in progress](/docs/demo/game.png?raw=true "Current Game")

![A won game](/docs/demo/game-won.png?raw=true "Game won")

![The leaderboard](/docs/demo/leaderboard.png?raw=true "Leaderboard")

---