import {Nullable, old} from "common-types";
import JsPhoto = old.school.JsPhoto;

export class PhotoInfo {
    imageClass: Nullable<string>
    description: Nullable<string>
    shouldBeHighlighted: Boolean

    constructor(photo: JsPhoto, shouldBeHighlighted: Boolean = false) {
        this.imageClass = photo.name != "" ? "App-photo-image-" + photo.name.toLowerCase() : null
        this.description = photo.description
        this.shouldBeHighlighted = shouldBeHighlighted
    }
}