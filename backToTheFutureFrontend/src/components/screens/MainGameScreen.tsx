import {GameState} from "../GameScreen";

type MainGameScreenProps = {
    gameStateSetter: (gs: GameState) => void
}

export default function MainGameScreen({gameStateSetter}: MainGameScreenProps) {
    return (
        <div className="App-main-container">
            <div className="App-buttons-container">
                <button className="App-button-base App-game-button-bottom-base App-button-back" onClick={() => {
                    gameStateSetter(GameState.START)
                }
                }></button>
            </div>
            <div className="App-functions-container">
                <button className={"App-button-base App-button-action App-button-eat "}></button>
                <button className={"App-button-base App-button-action App-button-sleep "}></button>
                <button className={"App-button-base App-button-action App-button-clean "}></button>
                <button className={"App-button-base App-button-action App-button-play "}></button>
            </div>
        </div>
    )
}
