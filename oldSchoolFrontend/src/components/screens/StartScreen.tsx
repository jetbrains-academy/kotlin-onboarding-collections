import logo from "../../assets/logo.svg";
import {GameState} from "../GameScreen";

type StartScreenProps = {
    gameStateSetter: (gs: GameState) => void
}

export default function StartScreen({gameStateSetter}: StartScreenProps) {
    return (
        <div className="App-main-container">
            <img src={logo} className="App-logo" alt="logo"/>
            <p className="App-big-name font-link-bold">Old School</p>
            <p className="App-small-name font-link-base">by Kotlin Course</p>
            <div className="App-buttons-container App-display-flex">
                <button className="App-button-base App-button-start" onClick={() => gameStateSetter(GameState.GAME)}></button>
            </div>
        </div>
    );
}
