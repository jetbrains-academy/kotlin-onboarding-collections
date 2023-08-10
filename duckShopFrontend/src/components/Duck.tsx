import {DuckInfo} from "../models/DuckInfo";

type DuckProps = {
    duck: DuckInfo
}

export default function Duck({duck}: DuckProps) {
    let baseDucksClasses = "App-duck-image "
    let baseLabelClasses = "App-duck-container-label "

    return(
        <div className="App-duck-container">
            <div className={duck.imageClass == null ? baseDucksClasses : baseDucksClasses + duck.imageClass}></div>
            <div className={duck.description == null ? baseLabelClasses + "App-display-none" : baseLabelClasses}>{duck.description}</div>
        </div>
    )
}
