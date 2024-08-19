export class JsDuck {
    constructor(name: string = "", description: string | null = null, hasKotlinAttribute: boolean = false) {
        this.name = name
        this.description = description
        this.hasKotlinAttribute = hasKotlinAttribute
    }

    name: string
    description: string | null = null
    hasKotlinAttribute: boolean = false
}
