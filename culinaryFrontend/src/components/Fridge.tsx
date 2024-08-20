import React, { useEffect, useCallback} from 'react';
import Product from "./Product";
import {JsItemType} from "../models/JsItemType";

type FridgeProps = {
    products: Array<JsItemType>,
}

export default function Fridge({products}: FridgeProps) {

    const third = Math.ceil(products.length / 3);
    const firstShelf = products.slice(0, third);
    const secondShelf = products.slice(third, third * 2);
    const thirdShelf = products.slice(third * 2);


    const adjustShelvesPosition = useCallback(() => {
        const fridge = document.querySelector('.App-fridge');
        const firstShelfElements = document.querySelectorAll('.App-fridge-first-shelf');
        const secondShelfElements = document.querySelectorAll('.App-fridge-second-shelf');
        const thirdShelfElements = document.querySelectorAll('.App-fridge-third-shelf');
        if (fridge) {
            const fridgeHeight = fridge.clientHeight;
            const firstNewTopValue = fridgeHeight * 0.15; // Adjust the multiplier as needed
            const secondNewTopValue = fridgeHeight * 0.3; // Adjust the multiplier as needed
            const thirdNewTopValue = fridgeHeight * 0.425; // Adjust the multiplier as needed
            firstShelfElements.forEach(element => {
                (element as HTMLElement).style.top = `${firstNewTopValue}px`;
            });
            secondShelfElements.forEach(element => {
                (element as HTMLElement).style.top = `${secondNewTopValue}px`;
            });
            thirdShelfElements.forEach(element => {
                (element as HTMLElement).style.top = `${thirdNewTopValue}px`;
            });
        }
    }, [products]);

    useEffect(() => {
        adjustShelvesPosition();
        const handleResize = () => {
            adjustShelvesPosition();
        };
        window.addEventListener('resize', handleResize);
        return () => {
            window.removeEventListener('resize', handleResize);
        };
    }, [adjustShelvesPosition]);

    return(
        <div className={"App-fridge"}>
            <div className={"App-fridge App-fridge-back"}
            ></div>
            {firstShelf.map((product, index) => (
                <div style={{left: `${2 + 2 * (index)}vmin`}}
                className={"App-fridge-first-shelf"}>
                    <Product product={product} inFridge={true} ></Product>
                </div>
            ))}
            {secondShelf.map((product, index) => (
                <div style={{left: `${2 + 2 * (index)}vmin`}}
                     className={"App-fridge-second-shelf"}>
                    <Product product={product} inFridge={true} ></Product>
                </div>
            ))}
            {thirdShelf.map((product, index) => (
                <div style={{left: `${2 + 2 * (index)}vmin`}}
                key={`third-shelf-${index}`}
                className={"App-fridge-third-shelf"}>
                    <Product product={product} inFridge={true} ></Product>
                </div>
            ))}
        </div>
    )
}
