package com.arseniy.actors

enum class ActorStates {
    ALIVE,
    DEAD
}

abstract class Actor {
    var state = ActorStates.ALIVE
}