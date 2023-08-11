import {DuckInfo} from "../models/DuckInfo";
import {PARTITION_FUNCTION} from "./screens/MainActionsScreen";

type DuckProps = {
    duck: DuckInfo,
    pressedFunction: String
}

export default function Duck({duck, pressedFunction}: DuckProps) {
    let baseDucksClasses = "App-duck-image "
    let baseLabelClasses = "App-duck-container-label "

    function toAddBest() {
        if (pressedFunction != PARTITION_FUNCTION) {
            return false
        }
        return duck.hasKotlinAttribute
    }

    return(
        <div className={"App-duck-container" + (toAddBest() ? " App-duck-container-best" : "")}>
            <div className={duck.imageClass == null ? baseDucksClasses : baseDucksClasses + duck.imageClass}></div>
            <div className={duck.description == null ? baseLabelClasses + "App-display-none" : baseLabelClasses}>{duck.description}</div>
        </div>
    )
}
