import {Nullable} from "common-types";

export class JsPhoto {
    name: string
    description: Nullable<string> = null

    constructor(name: string = "", description: Nullable<string> = null) {
        this.name = name
        this.description = description
    }
}