package com.example.universalremote

/**
 * Commands supported by the remote UI.
 * Individual TV protocols may support different subsets.
 */
enum class TvCommand {
    POWER,
    VOLUME_UP,
    VOLUME_DOWN,
    MUTE,
    CHANNEL_UP,
    CHANNEL_DOWN,
    UP,
    DOWN,
    LEFT,
    RIGHT,
    OK,
    BACK,
    HOME,
    MENU,
    INPUT
}

sealed interface TvConnectionState {
    data object Disconnected : TvConnectionState
    data object Connecting : TvConnectionState
    data class Connected(val deviceName: String) : TvConnectionState
    data class Error(val message: String) : TvConnectionState
}

/**
 * Common interface for all TV connection methods.
 */
interface TvConnection {
    val state: TvConnectionState

    fun connect()

    fun sendCommand(command: TvCommand)

    fun disconnect()
}