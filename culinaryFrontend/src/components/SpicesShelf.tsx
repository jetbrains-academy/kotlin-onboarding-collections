type SpicesShelfProps = {
    visible: boolean
}

export default function SpicesShelf({visible}: SpicesShelfProps) {
    let SpicesShelfClass = "App-spices-shelf"
    return(
        <div className={SpicesShelfClass + (visible ? "" : " App-invisible")}></div>
    )
}
