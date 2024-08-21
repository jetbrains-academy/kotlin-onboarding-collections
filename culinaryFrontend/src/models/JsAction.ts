import {JsItemType} from "./JsItemType";
import {JsActionType} from "./JsActionType";


export class JsAction {
    type: JsActionType
    parameter: JsItemType | null

    constructor(type: JsActionType, parameter: JsItemType | null = null) {
        this.type = type
        this.parameter = parameter
    }
}
