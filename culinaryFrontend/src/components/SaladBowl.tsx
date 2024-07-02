import Product from "./Product";

type SaladBowlProps = {
    saladBowlOptions: {
        visible: boolean,
        mixing: boolean,
        mixed: boolean,
        tomato: number,
        cucumber: number,
        carrot: number
    }
}

export default function SaladBowl({saladBowlOptions:{visible,mixing,mixed,tomato,cucumber,carrot}}: SaladBowlProps) {
    return (
        <div className={"App-salad" + (visible ? "" : " App-invisible") + (mixing ? " App-animation-mixing" : "")}>
            {!mixed ? (
                <>
                    {Array.from({length: carrot}).map((_, i) => (
                        <div key={i} className={"App-salad App-salad-carrot "}
                             style={{top: `${-1 * (carrot - i - 1)}vmin`}}
                        ></div>
                    ))}
                    {Array.from({length: tomato}).map((_, i) => (
                        <div key={i} className={"App-salad App-salad-tomato "}
                             style={{top: `${-1 * (tomato - i - 1)}vmin`}}
                        ></div>
                    ))}
                    {Array.from({length: cucumber}).map((_, i) => (
                        <div key={i} className={"App-salad App-salad-cucumber "}
                             style={{top: `${-1 * (cucumber - i - 1)}vmin`}}
                        ></div>
                    ))}
                </>) : (
                <>
                    <div className={"App-salad App-salad-mixed-tomato " + ((tomato > 0) ? "" : "App-invisible")}
                    ></div>
                    <div className={"App-salad App-salad-mixed-cucumber " + ((cucumber > 0) ? "" : "App-invisible")}
                    ></div>
                    <div className={"App-salad App-salad-mixed-carrot " + ((carrot > 0) ? "" : "App-invisible")}
                    ></div>
                </>
            )}
            <div className={"App-salad App-salad-bowl "}
            ></div>
        </div>
    )
}
