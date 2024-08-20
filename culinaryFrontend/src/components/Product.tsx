import {JsItemType} from '../models/JsItemType'


type ProductProps = {
    product: JsItemType,
    inFridge?: boolean
}

export default function Product({product, inFridge=false}: ProductProps) {
    let baseProductClasses = "App-product-image"
    let productClass = baseProductClasses + "-" + JsItemType[product]
    return(
        <div className={baseProductClasses + " " + productClass + " " + (inFridge? "App-product-fridge" : "App-product-counter")}></div>
    )
}
