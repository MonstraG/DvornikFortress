package input

//todo make help draw its contents based on gamestate
val Help = arrayOf(
    "\tesq: exit",
    "\tq: rise up",
    "\tz: go down",
    "\tarrows: move around",
    "",
    "\td: dig mode",
    "\tenter: apply order"
)
//todo add color to key letters.

val buttonMap = mapOf(
    27 to { Keys.esq() },
    38 to { Keys.upArrow() },
    40 to { Keys.downArrow() },
    39 to { Keys.rightArrow() },
    37 to { Keys.leftArrow() },
    81 to { Keys.q() },
    90 to { Keys.z() },
    68 to { Keys.d() },
    10 to { Keys.enter() },
    67 to { Keys.c() }
)