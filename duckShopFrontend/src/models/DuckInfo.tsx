import {Nullable} from "common-types";
import {duck} from "common-types";
import JsDuck = duck.shop.JsDuck;

export class DuckInfo {
    imageClass: Nullable<string>
    description: Nullable<string>
    hasKotlinAttribute: boolean

    constructor(duck: JsDuck) {
        this.imageClass = duck.name != "" ? "App-duck-image-" + this.duckName(duck.name) : null
        this.description = duck.description
        this.hasKotlinAttribute = duck.hasKotlinAttribute
    }

    duckName(name: String): String { return name.toLowerCase().replace(" ", "").replace(".", "") }
}
