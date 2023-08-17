import {GameState} from "../GameScreen";
import {useState} from "react";
import {duck} from "common-types";
import JsDuck = duck.shop.JsDuck;
import axios from "axios";
import DuckContainer from "../DuckContainer";

type MainActionsScreenProps = {
    gameStateSetter: (gs: GameState) => void
}

const MAX_DUCKS_SLOTS = 6
const LIST_MODE = "List"
const SET_MODE = "Set"
const MAP_MODE = "Map"

export const PARTITION_FUNCTION = "partition"

export default function MainActionsScreen({gameStateSetter}: MainActionsScreenProps) {
    let [ducks, ducksSetter] = useState<Array<JsDuck>>([])
    let [mode, modeSetter] = useState<String>("")
    let [pressedFunction, pressedFunctionSetter] = useState<String>("")
    let [infoText, infoTextSetter] = useState<String>("")

    function initDuckShop(url: string) {
        axios.get(url).then(async (response) => {
            ducksSetter(response.data as Array<JsDuck>)
        })
    }

    function initListOfDucks() {
        initDuckShop("/mode/list")
        modeSetter(LIST_MODE)
        infoTextSetter("")
        pressedFunctionSetter("")
    }

    function initSetOfDucks() {
        initDuckShop("/mode/set")
        modeSetter(SET_MODE)
        infoTextSetter("")
        pressedFunctionSetter("")
    }

    function initMapOfDucks() {
        initDuckShop("/mode/map")
        modeSetter(MAP_MODE)
        infoTextSetter("")
        pressedFunctionSetter("")
    }

    function wasGameInitialized() {
        return ducks.length != 0;
    }

    function canAddDuck() {
        return ducks.length < MAX_DUCKS_SLOTS
    }

    function canRemoveDuck() {
        return ducks.length > 0
    }

    function sendActionRequest(url: String, responseHandler: (response: any) => void) {
        axios.post("/functions/" + url, {
            "ducks": ducks.map((duck) => duck.name),
            "mode": mode
        }, {headers: {'Content-Type': 'application/json'}})
            .then((response) => {
                responseHandler(response)
            })
    }

    function shuffleDucks() {
        sendActionRequest("shuffle", (response) => {
            ducksSetter(response.data as Array<JsDuck>)
            pressedFunctionSetter("")
            infoTextSetter("Ducks have been \nshuffled randomly!")
        })
    }

    function sortDucks() {
        sendActionRequest("sort", (response) => {
            ducksSetter(response.data as Array<JsDuck>)
            pressedFunctionSetter("")
            infoTextSetter("Ducks have been sorted \naccording to the price of the stuff!")
        })
    }

    function partitionDucks() {
        sendActionRequest(PARTITION_FUNCTION, (response) => {
            let result = response.data as Array<Array<JsDuck>>
            if (result.length != 2) {
                return
            }
            if (result[0].length == 0) {
                alert("Sorry, ducks with Kotlin stuff haven’t been found!")
            } else {
                ducksSetter(result.reduce((accumulator, value) => accumulator.concat(value), []))
                pressedFunctionSetter(PARTITION_FUNCTION)
                infoTextSetter("Ducks with Kotlin stuff have been moved \nto the beginning of the collection!")
            }
        })
    }

    function filterDuck() {
        sendActionRequest("filter", (response) => {
            let filteredDucks = response.data as Array<JsDuck>
            if (filteredDucks.length == 0) {
                alert("Sorry, ducks with Kotlin stuff were not found!")
            } else {
                ducksSetter(response.data as Array<JsDuck>)
                pressedFunctionSetter("")
                infoTextSetter("Only ducks with Kotlin \nstuff have been left!")
            }
        })
    }

    function addDuck() {
        sendActionRequest("add", (response) => {
            let duck = response.data as JsDuck
            const clonedDucks: Array<JsDuck> = []
            ducks.push(duck)
            ducks.forEach(duck => clonedDucks.push(duck))
            ducksSetter(clonedDucks)
            pressedFunctionSetter("")
            infoTextSetter("A new random duck has been \ngenerated successfully!")
        })
    }

    function removeDuck() {
        sendActionRequest("remove", (response) => {
            let parsedDucks = response.data as Array<JsDuck>
            ducksSetter(parsedDucks)
            pressedFunctionSetter("")
            infoTextSetter("A random duck has been \nremoved successfully!")
            if (parsedDucks.length == 0) {
                modeSetter("")
                infoTextSetter("")
            }
        })
    }

    const BASE_BUTTON_COLLECTION_CLASSES = "App-button-base App-button-collection"
    const BASE_BUTTON_ACTION_CLASSES = "App-button-base App-button-action"

    return (
        <div className="App-main-container">
            <div className="App-buttons-container">
                <button className="App-button-base App-game-button-bottom-base App-button-back" onClick={() => {
                    gameStateSetter(GameState.START)
                    infoTextSetter("")
                }
                }></button>
            </div>
            {
                wasGameInitialized() ?
                    <div>
                        <div className="App-info-container">
                            <div className="App-info-container-text font-link-base">{infoText}</div>
                        </div>
                        <div className="App-functions-container">
                            <button
                                className={"App-button-base App-button-action App-button-add " + (canAddDuck() ? "" : "App-unclickable-button")}
                                onClick={() => addDuck()}></button>
                            <button
                                className={"App-button-base App-button-action App-button-remove" + (canRemoveDuck() ? "" : "App-unclickable-button")}
                                onClick={() => removeDuck()}></button>
                            <button
                                className={BASE_BUTTON_ACTION_CLASSES + " App-button-sort" + (mode == LIST_MODE ? "" : " App-unclickable-button")}
                                onClick={() => sortDucks()}></button>
                            <button
                                className={BASE_BUTTON_ACTION_CLASSES + " App-button-shuffle" + (mode == LIST_MODE ? "" : " App-unclickable-button")}
                                onClick={() => shuffleDucks()}></button>
                            <button className={BASE_BUTTON_ACTION_CLASSES + " App-button-filter"}
                                    onClick={() => filterDuck()}></button>
                            <button
                                className={BASE_BUTTON_ACTION_CLASSES + " App-button-partition" + (mode == MAP_MODE ? " App-unclickable-button" : "")}
                                onClick={() => partitionDucks()}></button>
                        </div>
                        <DuckContainer ducks={ducks} pressedFunction={pressedFunction}></DuckContainer>
                    </div>
                    : <div className="App-base-text">
                        <div className="font-link-base">Please initialize the duck shop!</div>
                    </div>
            }
            <div className="App-buttons-container">
                <button
                    className={BASE_BUTTON_COLLECTION_CLASSES + " App-button-list" + (mode == LIST_MODE ? " App-button-list-focused" : "")}
                    onClick={() => initListOfDucks()}></button>
                <button
                    className={BASE_BUTTON_COLLECTION_CLASSES + " App-button-set" + (mode == SET_MODE ? " App-button-set-focused" : "")}
                    onClick={() => initSetOfDucks()}></button>
                <button
                    className={BASE_BUTTON_COLLECTION_CLASSES + " App-button-map" + (mode == MAP_MODE ? " App-button-map-focused" : "")}
                    onClick={() => initMapOfDucks()}></button>
            </div>
        </div>
    );
}
