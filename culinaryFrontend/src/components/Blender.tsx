type BlenderProps = {
    blenderOptions: {
        visible: boolean;
        full: boolean;
        shake: boolean;
        citrus: number;
        berry: number;
    }
}

export default function Blender({blenderOptions: {visible, full, shake, citrus, berry}}: BlenderProps) {
    let baseBlenderJarClasses = "App-blender-jar"
    let blenderJarClass = baseBlenderJarClasses + (full ? "-full" : "")
    return (
        <div className={"App-blender" + (visible ? "" : " App-invisible") + (shake ? " App-animation-shake" : "")}>
            <div className={"App-blender " + blenderJarClass}
            ></div>
            {!full && (
                <>
                    {Array.from({length: citrus}).map((_, i) => (
                        <div key={i} className={"App-blender App-blender-citrus "}
                             style={{top: `${-1 * (citrus - i - 1)}vmin`}}
                        ></div>
                    ))}
                    {Array.from({length: berry}).map((_, i) => (
                        <div key={i} className={"App-blender App-blender-berry "}
                             style={{top: `${-1 * (berry - i - 1)}vmin`}}
                        ></div>
                    ))}
                </>
            )}
        </div>
    )
}
