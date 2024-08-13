export class JsPhoto {
    name: string
    description: string | null = null

    constructor(name: string = "", description: string | null = null) {
        this.name = name
        this.description = description
    }
}
