import {GameState} from "../GameScreen";

type MainActionsScreenProps = {
    gameStateSetter: (gs: GameState) => void
}

export default function MainActionsScreen({gameStateSetter}: MainActionsScreenProps) {
    return (
        <div className="App-main-container">
            <div className="App-buttons-container">
                <button className="App-button-base App-game-button-bottom-base App-button-back" onClick={() => gameStateSetter(GameState.START)}></button>
            </div>
        </div>
    );
}