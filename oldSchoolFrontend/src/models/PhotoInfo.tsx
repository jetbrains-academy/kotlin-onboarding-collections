import {JsPhoto} from "./Photo";

export class PhotoInfo {
    imageClass: string | null
    description: string | null
    shouldBeHighlighted: Boolean

    constructor(photo: JsPhoto, shouldBeHighlighted: Boolean = false) {
        this.imageClass = photo.name != "" ? "App-photo-image-" + photo.name.toLowerCase() : null
        this.description = photo.description
        this.shouldBeHighlighted = shouldBeHighlighted
    }
}
