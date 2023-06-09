import {PhotoInfo} from "../models/PhotoInfo";
import Photo from "./Photo";
import {old} from "common-types";
import JsPhoto = old.school.JsPhoto;

type PhotoAlbumProps = {
    photos: Array<JsPhoto>
    indexToHighLight: number
}

export default function PhotoAlbum({photos, indexToHighLight}: PhotoAlbumProps) {
    function getPhotoInfoByIndex(index: number): PhotoInfo {
        if (photos.length > index) {
            return new PhotoInfo(photos[index], indexToHighLight == index)
        }
        return new PhotoInfo(new JsPhoto("", null))
    }

    return(
        <div className="App-photo-album-container">
            <div className="App-photo-purple-bg App-photo-purple-bg-left">
                <div className="App-photo-white-bg App-photo-white-bg-left">
                    <div className="App-photo-row App-photo-row-top">
                        <Photo photoInfo={getPhotoInfoByIndex(0)}></Photo>
                        <Photo photoInfo={getPhotoInfoByIndex(1)}></Photo>
                        <Photo photoInfo={getPhotoInfoByIndex(2)}></Photo>
                    </div>
                    <div className="App-photo-row pp-photo-row-bottom">
                        <Photo photoInfo={getPhotoInfoByIndex(3)}></Photo>
                        <Photo photoInfo={getPhotoInfoByIndex(4)}></Photo>
                        <Photo photoInfo={getPhotoInfoByIndex(5)}></Photo>
                    </div>
                </div>
            </div>
            <div className="App-photo-purple-separator"></div>
            <div className="App-photo-purple-bg App-photo-purple-bg-right">
                <div className="App-photo-white-bg App-photo-white-bg-right">
                    <div className="App-photo-row App-photo-row-top">
                        <Photo photoInfo={getPhotoInfoByIndex(6)}></Photo>
                        <Photo photoInfo={getPhotoInfoByIndex(7)}></Photo>
                        <Photo photoInfo={getPhotoInfoByIndex(8)}></Photo>
                    </div>
                    <div className="App-photo-row pp-photo-row-bottom">
                        <Photo photoInfo={getPhotoInfoByIndex(9)}></Photo>
                        <Photo photoInfo={getPhotoInfoByIndex(10)}></Photo>
                        <Photo photoInfo={getPhotoInfoByIndex(11)}></Photo>
                    </div>
                </div>
            </div>
        </div>
    )
}