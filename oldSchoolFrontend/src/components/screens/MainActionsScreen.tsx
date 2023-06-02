import {GameState} from "../GameScreen";
import axios from "axios";
import {useState} from "react";
import {old} from "common-types";
import JsPhoto = old.school.JsPhoto;
import Photo from "../Photo";
import {PhotoInfo} from "../../models/PhotoInfo";

type MainActionsScreenProps = {
    gameStateSetter: (gs: GameState) => void
}

export default function MainActionsScreen({gameStateSetter}: MainActionsScreenProps) {
    let [photos, photosSetter] = useState<Array<JsPhoto>>([])

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

    function getPhotoInfoByIndex(index: number): PhotoInfo {
        if (photos.length > index) {
            return new PhotoInfo(photos[index])
        }
        return new PhotoInfo(new JsPhoto("", null))
    }

    return (
        <div className="App-main-container">
            <div className="App-buttons-container">
                <button className="App-button-base App-game-button-bottom-base App-button-back" onClick={() => gameStateSetter(GameState.START)}></button>
            </div>
            <div className="App-photo-album-container">
                <div className="App-photo-purple-bg App-photo-purple-bg-left">
                    <div className="App-photo-white-bg App-photo-white-bg-left">
                        <Photo photoInfo={getPhotoInfoByIndex(0)}></Photo>
                        <Photo photoInfo={getPhotoInfoByIndex(1)}></Photo>
                    </div>
                </div>
                <div className="App-photo-purple-separator"></div>
                <div className="App-photo-purple-bg App-photo-purple-bg-right">
                    <div className="App-photo-white-bg App-photo-white-bg-right">
                        <Photo photoInfo={getPhotoInfoByIndex(2)}></Photo>
                        <Photo photoInfo={getPhotoInfoByIndex(3)}></Photo>
                    </div>
                </div>
            </div>
            <div className="App-buttons-container">
                <button className="App-button-base App-game-button-bottom-base App-button-collection App-button-list" onClick={() => initListOfPhotos()}></button>
                <button className="App-button-base App-game-button-bottom-base App-button-collection App-button-set" onClick={() => initSetOfPhotos()}></button>
                <button className="App-button-base App-game-button-bottom-base App-button-collection App-button-map" onClick={() => initMapOfPhotos()}></button>
            </div>
        </div>
    );
}