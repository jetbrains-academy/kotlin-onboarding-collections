import {GameState} from "../GameScreen";
import axios from "axios";
import {useEffect, useState} from "react";
import {old} from "common-types";
import JsPhoto = old.school.JsPhoto;
import PhotoAlbum from "../PhotoAlbum";

type MainActionsScreenProps = {
    gameStateSetter: (gs: GameState) => void
}

const possibleOptions = ["Select function", "find", "groupBy"]
const defaultOption = "Select condition"

export default function MainActionsScreen({gameStateSetter}: MainActionsScreenProps) {
    let [photos, photosSetter] = useState<Array<JsPhoto>>([])
    let [userFunction, userFunctionSetter] = useState<string>(possibleOptions[0])

    let [indexToHighLight, indexToHighLightSetter] = useState<number>(-1)

    let [selectedColor, selectedColorSetter] = useState<string>(defaultOption)
    let [selectedGroupByMethod, selectedGroupByMethodSetter] = useState<string>(defaultOption)


    let [possibleColors, possibleColorsSetter] = useState<Array<string>>([])

    // Load initial data
    useEffect(() => {
        axios.get("/functions/colors").then((response) => {
            possibleColorsSetter(response.data as Array<string>)
        })
    }, []);

    function initCollectionOfPhotos(url: string) {
        axios.get(url).then(async (response) => {
            photosSetter(response.data as Array<JsPhoto>)
            console.log(photos)
        })
    }

    function initListOfPhotos() {
        initCollectionOfPhotos("/mode/list")
    }

    function initSetOfPhotos() {
        initCollectionOfPhotos("/mode/set")
    }

    function initMapOfPhotos() {
        initCollectionOfPhotos("/mode/map")
    }

    const baseClassesForFilters = "App-dropdown-container"

    function wasGameInitialized() {
        return photos.length != 0;
    }

    function findPhoto(color: String) {
        if (color === defaultOption || color == "") {
            indexToHighLightSetter(-1)
            return
        } else {
            axios.post("/functions/find", {
                "names": photos.map((photo) => photo.name),
                "color": color
            }, {headers: {'Content-Type': 'application/json'}})
                .then((response) => {
                    for (let index in photos) {
                        if (photos[index].name.toLowerCase() === response.data) {
                            indexToHighLightSetter(+index)
                            break
                        }
                    }
                })
        }
    }

    function shouldBeBlocked() {
        return userFunction == possibleOptions[0]
    }

    return (
        <div className="App-main-container">
            <div className="App-buttons-container">
                <button className="App-button-base App-game-button-bottom-base App-button-back" onClick={() => gameStateSetter(GameState.START)}></button>
            </div>
            {
                wasGameInitialized() ? <div className="App-functions-container">
                    <div className="App-dropdown-container">
                        <select name="actions-dropdown"
                                id="actions"
                                value={userFunction}
                                onChange={(e) => {
                                    userFunctionSetter(e.target.value.toString())
                                    selectedColorSetter(defaultOption)
                                    selectedGroupByMethodSetter(defaultOption)
                                    indexToHighLightSetter(-1)
                                }}
                        >
                            {
                                possibleOptions.map( (option) =>
                                    <option key={option}>{option}</option> )
                            }
                        </select>
                    </div>
                    <div className={userFunction === possibleOptions[0] ? baseClassesForFilters + " App-not-clickable" : baseClassesForFilters + " App-display-none"}>
                        <select name="actions-dropdown-base"
                                id="conditions-base">
                            <option value="">{defaultOption}</option>
                        </select>
                    </div>
                    <div className={userFunction === possibleOptions[1] ? shouldBeBlocked() ? baseClassesForFilters + " App-not-clickable" : baseClassesForFilters : baseClassesForFilters + " App-display-none"}>
                        <select name="actions-dropdown-find"
                                id="conditions-find"
                                value = {selectedColor}
                                onChange={(e) => {
                                    selectedColorSetter(e.target.value.toString())
                                    findPhoto(e.target.value.toString())
                                }}
                        >
                            <option value="">{defaultOption}</option>
                            {
                                possibleColors.map( (color) =>
                                    <option key={color}>{color}</option> )
                            }
                        </select>
                    </div>
                    <div className={userFunction === possibleOptions[2] ? shouldBeBlocked() ? baseClassesForFilters + " App-not-clickable" : baseClassesForFilters : baseClassesForFilters + " App-display-none"}>
                        <select name="actions-dropdown-group-by"
                                id="conditions-group-by"
                                value = {selectedGroupByMethod}
                                onChange={(e) => selectedGroupByMethodSetter(e.target.value.toString())}
                        >
                            <option value="">{defaultOption}</option>
                            <option value="">background color</option>
                            <option value="">accessories</option>
                        </select>
                    </div>
                </div> : <div className="App-base-text">
                    <div className="font-link-base">Please, initialize the photos!</div>
                </div>
            }
            <PhotoAlbum photos={photos} indexToHighLight={indexToHighLight}></PhotoAlbum>
            <div className="App-buttons-container">
                <button className="App-button-base App-game-button-bottom-base App-button-collection App-button-list" onClick={() => initListOfPhotos()}></button>
                <button className="App-button-base App-game-button-bottom-base App-button-collection App-button-set" onClick={() => initSetOfPhotos()}></button>
                <button className="App-button-base App-game-button-bottom-base App-button-collection App-button-map" onClick={() => initMapOfPhotos()}></button>
            </div>
        </div>
    );
}