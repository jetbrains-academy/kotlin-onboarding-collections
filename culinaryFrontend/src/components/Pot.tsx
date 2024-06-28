type PotProps = {
    potOptions: {
        visible: boolean,
        simmer: boolean,
        soup: boolean,
        pepper: boolean,
        salt: boolean,
        oregano: boolean,
        cucumber: boolean,
        tomato: boolean,
        carrot: boolean,
    }
}

export default function Pot({potOptions:{visible,simmer,soup,pepper,salt,oregano, cucumber, tomato,carrot}}: PotProps) {
    let basePotClasses = "App-pot"
    let potClass = basePotClasses + (soup ? "-soup" : "-water")
    return (
        <div className={"App-pot" + (visible ? "" : " App-invisible") + (simmer ? " App-animation-simmer" : "")}>
            <div className={"App-pot App-pot-back"}
            ></div>
            <div className={"App-pot App-pot-cucumber " + ((cucumber && !soup) ? "" : "App-invisible")}
            ></div>
            <div className={"App-pot App-pot-tomato " + ((tomato && !soup) ? "" : "App-invisible")}
            ></div>
            <div className={"App-pot App-pot-carrot " + ((carrot && !soup) ? "" : "App-invisible")}
            ></div>
            <div className={"App-pot " + potClass}
            ></div>
            <div className={"App-pot App-pot-pepper " + ((pepper && !soup) ? "" : "App-invisible")}
            ></div>
            <div className={"App-pot App-pot-salt " + ((salt && !soup) ? "" : "App-invisible")}
            ></div>
            <div className={"App-pot App-pot-oregano " + ((oregano && !soup) ? "" : "App-invisible")}
            ></div>
        </div>
    )
}
