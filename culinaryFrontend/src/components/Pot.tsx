type PotProps = {
    potOptions: {
        visible: boolean,
        simmer: boolean,
        soup: boolean,
        pepper: boolean,
        salt: boolean,
        oregano: boolean,
        cucumber: number,
        tomato: number,
        carrot: number,
        soupHue: number
    }
}

export default function Pot({potOptions:{visible,simmer,soup,pepper,salt,oregano, cucumber, tomato,carrot,soupHue}}: PotProps) {
    let basePotClasses = "App-pot"
    let potClass = basePotClasses + (soup ? "-soup" : "-water")
    return (
        <div className={"App-pot" + (visible ? "" : " App-invisible") + (simmer ? " App-animation-simmer" : "")}>
            <div className={"App-pot App-pot-back"}
            ></div>

            {!soup && (
                <>
                    {Array.from({length: cucumber}).map((_, i) => (
                        <div key={i} className={"App-pot App-pot-cucumber "}
                             style={{top: `${-1 * (cucumber - i - 1)}vmin`}}
                        ></div>
                    ))}
                    {Array.from({length: tomato}).map((_, i) => (
                        <div key={i} className={"App-pot App-pot-tomato "}
                             style={{top: `${-1 * (tomato - i - 1)}vmin`}}
                        ></div>
                    ))}
                    {Array.from({length: carrot}).map((_, i) => (
                        <div key={i} className={"App-pot App-pot-carrot "}
                             style={{top: `${-1 * (carrot - i - 1)}vmin`}}
                        ></div>
                    ))}
                </>
            )}
            <div className={"App-pot " + potClass}
                 style={{filter: `hue-rotate(${soupHue}deg)`}}
            ></div>
            <div className={"App-pot App-pot-pepper " + ((pepper) ? "" : "App-invisible")}
            ></div>
            <div className={"App-pot App-pot-salt " + ((salt) ? "" : "App-invisible")}
            ></div>
            <div className={"App-pot App-pot-oregano " + ((oregano) ? "" : "App-invisible")}
            ></div>
        </div>
    )
}
