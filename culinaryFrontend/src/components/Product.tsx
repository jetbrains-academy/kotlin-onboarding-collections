import {JsItemType} from '../models/JsItemType'


type ProductProps = {
    product: JsItemType
}

export default function Product({product}: ProductProps) {
    let baseProductClasses = "App-product-image"
    let productClass = baseProductClasses + "-" + JsItemType[product]
    return(
        <div className={baseProductClasses + " " + productClass}></div>
    )
}
