type SaladBowlProps = {
    saladBowlOptions: {
        visible: boolean,
        mixing: boolean,
        mixed: boolean,
        tomato: boolean,
        cucumber: boolean,
        carrot: boolean
    }
}

export default function SaladBowl({saladBowlOptions:{visible,mixing,mixed,tomato,cucumber,carrot}}: SaladBowlProps) {
    let baseSaladBowl = "App-salad-bowl"
    let SaladBowlClass = baseSaladBowl + (mixed ? "-mixed" : "")
    return (
        <div className={"App-salad" + (visible ? "" : " App-invisible") + (mixing ? " App-animation-mixing" : "")}>
            <div className={"App-salad App-salad-tomato " + ((tomato && !mixed) ? "" : "App-invisible")}
            ></div>
            <div className={"App-salad App-salad-cucumber " + ((cucumber && !mixed) ? "" : "App-invisible")}
            ></div>
            <div className={"App-salad App-salad-carrot " + ((carrot && !mixed) ? "" : "App-invisible")}
            ></div>
            <div className={"App-salad " + SaladBowlClass}
            ></div>
        </div>
    )
}
