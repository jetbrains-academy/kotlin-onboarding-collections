import StartScreen from "./screens/StartScreen";
import React from "react";
window.React = React

export enum GameState {
    START,
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
    }
}
