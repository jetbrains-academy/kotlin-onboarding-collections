import StartScreen from "./screens/StartScreen";
import React from "react";
import MainActionsScreen from "./screens/MainActionsScreen";
window.React = React

export enum GameState {
    START,
    GAME,
}

export type GameScreenProps = {
    state: GameState,
    gameStateSetter: (gs: GameState) => void,
}

export default function GameScreen({state, gameStateSetter}: GameScreenProps) {
    switch (state) {
        case GameState.START: {
            return <StartScreen gameStateSetter={gameStateSetter}/>
        }
        case GameState.GAME: {
            return <MainActionsScreen gameStateSetter={gameStateSetter}/>
        }
    }
}
