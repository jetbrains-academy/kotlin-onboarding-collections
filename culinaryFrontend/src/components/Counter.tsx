import {culinary} from "common-types";
import JsItemType = culinary.JsItemType;
import Product from "./Product";

type CounterProps = {
    products: Array<JsItemType>,
}

export default function Counter({products}: CounterProps) {
    function getProductByIndex(index: number): JsItemType {
        if (products.length > index) {
            return products[index]
        }
        return JsItemType.valueOf("ROT_TOMATO")
    }

    const half = Math.ceil(products.length / 2);
    const topProducts = products.slice(0, half);
    const bottomProducts = products.slice(half);

    return(
        <div className="App-counter-container">
            <div className="App-products-row App-products-row-top">
            {topProducts.map((product, index) => (
                    <Product product={product} ></Product>
            ))}
            </div>
            <div className="App-products-row App-products-row-bottom">
                {bottomProducts.map((product, index) => (
                    <Product product={product} ></Product>
                ))}
            </div>
        </div>
    )
}
