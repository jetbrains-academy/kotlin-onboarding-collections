import {Nullable} from "common-types";


export class JsDuck {
    constructor(name: string = "", description: Nullable<string> = null, hasKotlinAttribute: boolean = false) {
        this.name = name
        this.description = description
        this.hasKotlinAttribute = hasKotlinAttribute
    }

    name: string
    description: Nullable<string> = null
    hasKotlinAttribute: Boolean = false
}