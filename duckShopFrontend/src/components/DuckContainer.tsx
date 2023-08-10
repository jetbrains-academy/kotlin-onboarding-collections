import {duck} from "common-types";
import JsDuck = duck.shop.JsDuck;
import {DuckInfo} from "../models/DuckInfo";
import Duck from "./Duck";

type DuckContainerProps = {
    ducks: Array<JsDuck>
}

export default function DuckContainer({ducks}: DuckContainerProps) {
    function getDuckByIndex(index: number): DuckInfo {
        if (ducks.length > index) {
            return new DuckInfo(ducks[index])
        }
        return new DuckInfo(new JsDuck("", null, false))
    }

    return(
        <div className="App-ducks-container">
            <div className="App-ducks-row App-ducks-row-top">
                <Duck duck={getDuckByIndex(0)}></Duck>
                <Duck duck={getDuckByIndex(1)}></Duck>
                <Duck duck={getDuckByIndex(2)}></Duck>
            </div>
            <div className="App-ducks-row App-ducks-row-bottom">
                <Duck duck={getDuckByIndex(3)}></Duck>
                <Duck duck={getDuckByIndex(4)}></Duck>
                <Duck duck={getDuckByIndex(5)}></Duck>
            </div>
        </div>
    )
}
