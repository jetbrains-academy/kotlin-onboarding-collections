import {culinary} from "common-types";
import JsItemType = culinary.JsItemType;

type ProductProps = {
    product: JsItemType
}

export default function Product({product}: ProductProps) {
    let baseProductClasses = "App-product-image"
    let productClass = baseProductClasses + "-" + product.name
    return(
        <div className={baseProductClasses + " " + productClass}></div>
    )
}
