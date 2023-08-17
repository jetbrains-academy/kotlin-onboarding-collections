import {GameState} from "../GameScreen";
import axios from "axios";
import {useEffect, useState} from "react";
import {old} from "common-types";
import JsPhoto = old.school.JsPhoto;
import PhotoAlbum from "../PhotoAlbum";

type MainActionsScreenProps = {
    gameStateSetter: (gs: GameState) => void
}

const possibleOptions = ["Select function", "Find by background color", "Group by condition"]
const defaultOption = "Select option"

const LIST_MODE = "List"
const SET_MODE = "Set"
const MAP_MODE = "Map"

export default function MainActionsScreen({gameStateSetter}: MainActionsScreenProps) {
    let [photos, photosSetter] = useState<Array<JsPhoto>>([])
    let [userFunction, userFunctionSetter] = useState<string>(possibleOptions[0])

    let [indexToHighLight, indexToHighLightSetter] = useState<number>(-1)

    let [selectedColor, selectedColorSetter] = useState<string>(defaultOption)
    let [selectedGroupByMethod, selectedGroupByMethodSetter] = useState<string>(defaultOption)

    let [mode, modeSetter] = useState<String>("")
    let [infoText, infoTextSetter] = useState<String>("")


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

            userFunctionSetter(possibleOptions[0])
            selectedColorSetter(defaultOption)
            selectedGroupByMethodSetter(defaultOption)
            indexToHighLightSetter(-1)
        })
    }

    function initListOfPhotos() {
        initCollectionOfPhotos("/mode/list")
        modeSetter(LIST_MODE)
        infoTextSetter("")
    }

    function initSetOfPhotos() {
        initCollectionOfPhotos("/mode/set")
        modeSetter(SET_MODE)
        infoTextSetter("")
    }

    function initMapOfPhotos() {
        initCollectionOfPhotos("/mode/map")
        modeSetter(MAP_MODE)
        infoTextSetter("")
    }

    const baseClassesForFilters = "App-dropdown-container"

    function wasGameInitialized() {
        return photos.length != 0;
    }

    function findPhoto(color: String) {
        if (color === defaultOption || color == "") {
            indexToHighLightSetter(-1)
            infoTextSetter("")
        } else {
            axios.post("/functions/find", {
                "names": photos.map((photo) => photo.name),
                "color": color
            }, {headers: {'Content-Type': 'application/json'}})
                .then((response) => {
                    console.log(response)

                    function updateIndex() {
                        infoTextSetter("")
                        for (let index in photos) {
                            if (photos[index].name.toLowerCase() === response.data) {
                                indexToHighLightSetter(+index)
                                infoTextSetter("Only the first photo with " + color + "\nbackground color has been found!")
                                return
                            }
                        }
                        infoTextSetter("")
                        indexToHighLightSetter(-1)
                        if (response.data == '') {
                            infoTextSetter("")
                            alert("No pictures were found!");
                        }
                    }

                    updateIndex()
                })
        }
    }

    function shouldBeBlocked() {
        return userFunction == possibleOptions[0]
    }

    function sendGroupByRequest(url: string) {
        axios.post(url, photos.map((photo) => photo.name), {headers: {'Content-Type': 'application/json'}})
            .then((response) => {
                let grouped = (response.data as Array<string>).filter(function (elem, index, self) {
                    return index === self.indexOf(elem);
                })
                let sorted: Array<JsPhoto> = []
                grouped.forEach(k => {
                    let n = photos.filter(obj => {
                        return obj.name === k
                    })
                    if (n.length > 0) {
                        n.forEach(item => sorted.push(item))
                    }
                })
                photosSetter(sorted)
            })
    }

    function groupByAction(type: String) {
        if (type == defaultOption || type == "") {
            return
        }
        if (type == "background color") {
            sendGroupByRequest("/functions/groupByByColor")
            infoTextSetter("Photos with the same background color\nwere grouped.")
        } else if (type == "hair type and hat") {
            sendGroupByRequest("/functions/groupByPhotosByHairAndHat")
            infoTextSetter("Photos with the same hair tone (dark or light) were grouped.\nInside each group photos were grouped by a hat presence.")
        }
    }

    const BASE_BUTTON_COLLECTION_CLASSES = "App-button-base App-button-collection"

    return (
        <div className="App-main-container">
            <div className="App-buttons-container">
                <button className="App-button-base App-game-button-bottom-base App-button-back"
                        onClick={() => gameStateSetter(GameState.START)}></button>
            </div>
            {
                wasGameInitialized() ?
                    <div>
                        <div className="App-info-container"><div className="App-info-container-text font-link-base">{infoText}</div></div>
                        <div className="App-functions-container">
                            <div className="App-dropdown-container">
                                <select name="actions-dropdown"
                                        id="actions"
                                        value={userFunction}
                                        onChange={(e) => {
                                            userFunctionSetter(e.target.value.toString())
                                            selectedColorSetter(defaultOption)
                                            selectedGroupByMethodSetter(defaultOption)
                                            indexToHighLightSetter(-1)
                                            infoTextSetter("")
                                        }}
                                >
                                    {
                                        possibleOptions.map((option) =>
                                            <option key={option}>{option}</option>)
                                    }
                                </select>
                            </div>
                            <div
                                className={userFunction === possibleOptions[0] ? baseClassesForFilters + " App-not-clickable" : baseClassesForFilters + " App-display-none"}>
                                <select name="actions-dropdown-base"
                                        id="conditions-base">
                                    <option value="">{defaultOption}</option>
                                </select>
                            </div>
                            <div
                                className={userFunction === possibleOptions[1] ? shouldBeBlocked() ? baseClassesForFilters + " App-not-clickable" : baseClassesForFilters : baseClassesForFilters + " App-display-none"}>
                                <select name="actions-dropdown-find"
                                        id="conditions-find"
                                        value={selectedColor}
                                        onChange={(e) => {
                                            findPhoto(e.target.value.toString())
                                            selectedColorSetter(e.target.value.toString())
                                        }}
                                >
                                    <option value={defaultOption}>Select color</option>
                                    {
                                        possibleColors.map((color) =>
                                            <option key={color} value={color}>{color}</option>)
                                    }
                                </select>
                            </div>
                            <div
                                className={userFunction === possibleOptions[2] ? shouldBeBlocked() ? baseClassesForFilters + " App-not-clickable" : baseClassesForFilters : baseClassesForFilters + " App-display-none"}>
                                <select name="actions-dropdown-group-by"
                                        id="conditions-group-by"
                                        value={selectedGroupByMethod}
                                        onChange={(e) => {
                                            groupByAction(e.target.value.toString())
                                            selectedGroupByMethodSetter(e.target.value.toString())
                                        }}
                                >
                                    <option value={defaultOption}>Select condition</option>
                                    <option value="background color">background color</option>
                                    <option value="hair type and hat">hair tone and hat</option>
                                </select>
                            </div>
                        </div>
                    </div> : <div className="App-base-text">
                        <div className="font-link-base">Please initialize the photos!</div>
                    </div>
            }
            <PhotoAlbum photos={photos} indexToHighLight={indexToHighLight}></PhotoAlbum>
            <div className="App-buttons-container">
                <button
                    className={BASE_BUTTON_COLLECTION_CLASSES + " App-button-list" + (mode == LIST_MODE ? " App-button-list-focused" : "")}
                    onClick={() => initListOfPhotos()}></button>
                <button
                    className={BASE_BUTTON_COLLECTION_CLASSES + " App-button-set" + (mode == SET_MODE ? " App-button-set-focused" : "")}
                    onClick={() => initSetOfPhotos()}></button>
                <button
                    className={BASE_BUTTON_COLLECTION_CLASSES + " App-button-map" + (mode == MAP_MODE ? " App-button-map-focused" : "")}
                    onClick={() => initMapOfPhotos()}></button>
            </div>
        </div>
    );
}