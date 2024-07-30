type BasketProps = {
    basketOptions: {
        type: string,
        visible: boolean,
        count: number,
    }
}

export default function Basket({basketOptions:{type, visible, count}}: BasketProps) {
    let BasketClass = "App-basket "
    let CurrentBasketClass = `App-basket-${type}-`
    return(
        <div className={BasketClass + (visible ? "" : " App-invisible")}>
            <div className={"App-basket App-basket-back"}
            ></div>
            {count > 0 && (
                <>
                    {Array.from({length: count}).map((_, i) => (
                        <div key={i} className={"App-basket App-basket-item " + CurrentBasketClass + (count - i)}
                        ></div>
                    ))}
                </>
            )}
            <div className={"App-basket App-basket-front"}
            ></div>
        </div>
    )
}
