import {GameState} from "../GameScreen";
import {useEffect, useState} from "react";
import {duck} from "common-types";
import JsDuck = duck.shop.JsDuck;
import axios from "axios";
import DuckContainer from "../DuckContainer";

type MainActionsScreenProps = {
    gameStateSetter: (gs: GameState) => void
}

export default function MainActionsScreen({gameStateSetter}: MainActionsScreenProps) {
    let [ducks, ducksSetter] = useState<Array<JsDuck>>([])

    function initDuckShop(url: string) {
        axios.get(url).then(async (response) => {
            ducksSetter(response.data as Array<JsDuck>)
        })
    }

    function initListOfDucks() {
        initDuckShop("/mode/list")
    }

    function initSetOfDucks() {
        initDuckShop("/mode/set")
    }

    function initMapOfDucks() {
        initDuckShop("/mode/map")
    }

    function wasGameInitialized() {
        return ducks.length != 0;
    }

    return (
        <div className="App-main-container">
            <div className="App-buttons-container">
                <button className="App-button-base App-game-button-bottom-base App-button-back" onClick={() => gameStateSetter(GameState.START)}></button>
            </div>
            {
                wasGameInitialized() ?
                    <div>
                        <div className="App-functions-container"></div>
                        <DuckContainer ducks={ducks}></DuckContainer>
                    </div>
                    : <div className="App-base-text">
                    <div className="font-link-base">Please initialize the duck shop!</div>
                </div>
            }
            <div className="App-buttons-container">
                <button className="App-button-base App-button-collection App-button-list" onClick={() => initListOfDucks()}></button>
                <button className="App-button-base App-button-collection App-button-set" onClick={() => initSetOfDucks()}></button>
                <button className="App-button-base App-button-collection App-button-map" onClick={() => initMapOfDucks()}></button>
            </div>
        </div>
    );
}
