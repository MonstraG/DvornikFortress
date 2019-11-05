package input

//todo make help draw its contents based on gamestate
val Help = arrayOf(
    "\tesq: exit game",
    "\tq: rise up",
    "\tz: go down",
    "\tarrows: move around",
    "",
    "\td: dig mode",
    "\tc: leave current mode",
    "\tenter: apply order",
    "\tspace: pause/continue"
)
//todo add color to key letters.

val buttonMap = mapOf(
    "Esq" to { Keys.esq() },
    "Up" to { Keys.upArrow() },
    "Down" to { Keys.downArrow() },
    "Right" to { Keys.rightArrow() },
    "Left" to { Keys.leftArrow() },
    "Q" to { Keys.q() },
    "Z" to { Keys.z() },
    "D" to { Keys.d() },
    "Enter" to { Keys.enter() },
    "C" to { Keys.c() },
    "Space" to { Keys.space() }
)