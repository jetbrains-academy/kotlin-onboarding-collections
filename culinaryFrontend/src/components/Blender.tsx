type BlenderProps = {
    blenderOptions: {
        visible: boolean;
        full: boolean;
        shake: boolean;
        citrus: boolean;
        berry: boolean;
    }
}

export default function Blender({blenderOptions: {visible, full, shake, citrus, berry}}: BlenderProps) {
    let baseBlenderJarClasses = "App-blender-jar"
    let blenderJarClass = baseBlenderJarClasses + (full ? "-full" : "")
    return (
        <div className={"App-blender" + (visible ? "" : " App-invisible") + (shake ? " App-animation-shake" : "")}>
            <div className={"App-blender " + blenderJarClass}
            ></div>
            <div className={"App-blender App-blender-citrus " + ((citrus && !full) ? "" : "App-invisible")}
            ></div>
            <div className={"App-blender App-blender-berry " + ((berry && !full) ? "" : "App-invisible")}
            ></div>
        </div>
    )
}
