type ScaleProps = {
    text: string,
    value: number
}

export default function Scale({text, value}: ScaleProps) {
    const percentage = (value <= 5 ? 5 : value) + '%';

    return (
        <div className={"App-scale-container "}>
            <div className={"App-scale-container-text font-link-bold "}>{text}</div>
            <div className={"App-scale-container-value font-link-bold"} style={{width : percentage}}></div>
        </div>
    )
}
