import {duck} from "common-types";
import JsDuck = duck.shop.JsDuck;
import {DuckInfo} from "../models/DuckInfo";
import Duck from "./Duck";

type DuckContainerProps = {
    ducks: Array<JsDuck>,
    pressedFunction: String
}

export default function DuckContainer({ducks, pressedFunction}: DuckContainerProps) {
    function getDuckByIndex(index: number): DuckInfo {
        if (ducks.length > index) {
            return new DuckInfo(ducks[index])
        }
        return new DuckInfo(new JsDuck("", null, false))
    }

    return(
        <div className="App-ducks-container">
            <div className="App-ducks-row App-ducks-row-top">
                <Duck duck={getDuckByIndex(0)} pressedFunction={pressedFunction}></Duck>
                <Duck duck={getDuckByIndex(1)} pressedFunction={pressedFunction}></Duck>
                <Duck duck={getDuckByIndex(2)} pressedFunction={pressedFunction}></Duck>
            </div>
            <div className="App-ducks-row App-ducks-row-bottom">
                <Duck duck={getDuckByIndex(3)} pressedFunction={pressedFunction}></Duck>
                <Duck duck={getDuckByIndex(4)} pressedFunction={pressedFunction}></Duck>
                <Duck duck={getDuckByIndex(5)} pressedFunction={pressedFunction}></Duck>
            </div>
        </div>
    )
}
