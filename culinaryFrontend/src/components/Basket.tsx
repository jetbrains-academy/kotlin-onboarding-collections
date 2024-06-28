type BasketProps = {
    type: string,
    visible: boolean
}

export default function Basket({type, visible}: BasketProps) {
    let BasketClass = "App-" + type + "_basket"
    return(
        <div className={BasketClass + (visible ? "" : " App-invisible")}></div>
    )
}
