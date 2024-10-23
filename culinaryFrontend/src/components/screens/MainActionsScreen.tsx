import {GameState} from "../GameScreen";
import {useEffect, useState} from "react";
import {JsAction} from '../../models/JsAction'
import {JsItemType} from '../../models/JsItemType'
import axios from "axios";
import Counter from "../Counter";
import Blender from "../Blender";
import Pot from "../Pot";
import Basket from "../Basket";
import SpicesShelf from "../SpicesShelf";
import SaladBowl from "../SaladBowl";
import Fridge from "../Fridge";

type MainActionsScreenProps = {
    gameStateSetter: (gs: GameState) => void
}

export default function MainActionsScreen({gameStateSetter}: MainActionsScreenProps) {

    const taskTypeUrl   = "/functions/current-task"
    const refillUrl     = "/functions/refill-fridge"
    const soupUrl       = "/functions/tomato-soup"
    const spiceUrl      = "/functions/soup-spices"
    const tasteUrl      = "/functions/check-soup"
    const saladListUrl  = "/functions/salad-list"
    const saladSequenceUrl    = "/functions/salad-sequence"
    const smoothieUrl     = "/functions/smoothie"

    let infoTextActionMap: { [key: string]: string } = {
        "SHOW_ON_COUNTER": "Placing the current ingredient on the cooking space:",
        "PUT_IN_POT": "Putting into the pot:",
        "SIMMER": "Cooking!",
        "ADD_TO_SALAD": "Adding to the salad:",
        "MIX_SALAD": "Mixing the salad.",
        "BLEND": "Blending.",
        "ADD_TO_BLENDER": "Adding to the blender:",
        "REMOVE_FROM_COUNTER": "Removing the current ingredient from the cooking space:",
        "CUT_ON_COUNTER": "Cutting",
    }

    let infoTextItemMap: { [key: string]: string } = {
        "CITRUS_BASKET": "citrus basket",
        "BERRY_BASKET": "berry basket",
        "ROT_TOMATO": "rotten tomato",
        "FRESH_TOMATO": "fresh tomato",
        "CUT_TOMATO": "cut tomato",
        "ROT_CUCUMBER": "rotten cucumber",
        "FRESH_CUCUMBER": "fresh cucumber",
        "CUT_CUCUMBER": "cut cucumber",
        "ROT_CARROT": "rotten carrot",
        "FRESH_CARROT": "fresh carrot",
        "CUT_CARROT": "cut carrot",
        "BERRY": "a berry",
        "CITRUS": "an orange",
        "SALT": "salt",
        "PEPPER": "pepper",
        "OREGANO": "oregano",
    }

    const startupMsg = "Press the button to start"
    const getTaskErrMsg = "Can't retrieve task type. Please check the backend."

    const refillSuccMsg = "The fridge has been refilled!"
    const refillErrMsg = "Failed to refill the fridge. Check your implementation."
    const soupEmptyListMsg = "You need at least three fresh tomatoes in the fridge! Please refill!"

    const cookingNoActionsMsg = "Not enough ingredients to cook"
    const cookingDoneMsg = "Cooking is complete!"
    const cookingErrMsg = "Failed to cook. Check your implementation."

    const noSoupSpicingMsg = "You need to cook the soup first!"
    const beforeSpicingMsg = "Let's add some spices!"
    const afterSpicingMsg = "Spices have been added!"
    const spicingErrMsg = "Failed to add spices. Check your implementation."

    const tasteGoodMsg = "It tastes great! 🎉"
    const tasteBadMsg = "It tastes terrible... Pkease try cooking the soup again."
    const tasteErrMsg = "Failed to retrieve taste status. Check your implementation."

    const saladEmptyListMsg = "Please refill the fridge!"

    const soupName = "tomato soup"
    const saladListName = "salad (list)"
    const saladSequenceName = "salad (sequence)"
    const smoothieName = "smoothie"

    const cookingDelay = 1000 // ms

    type BlenderOptions = {
        visible: boolean,
        full: boolean,
        shake: boolean,
        berry: number,
        citrus: number,
    }

    const initialBlenderOptions: BlenderOptions = {
        visible: true,
        full: false,
        shake: false,
        berry: 0,
        citrus: 0,
    }

    type PotOptions = {
        visible: boolean,
        simmer: boolean,
        soup: boolean,
        pepper: boolean,
        salt: boolean,
        oregano: boolean,
        cucumber: number,
        tomato: number,
        carrot: number,
        soupHue: number,
        spiced: boolean
    }

    const initialPotOptions: PotOptions = {
        visible: true,
        simmer: false,
        soup: false,
        pepper: false,
        salt: false,
        oregano: false,
        cucumber: 0,
        tomato: 0,
        carrot: 0,
        soupHue: 0,
        spiced: false
    }

    type SaladBowlOptions = {
        visible: boolean,
        mixing: boolean,
        mixed: boolean,
        tomato: number,
        cucumber: number,
        carrot: number,
    }

    const initialSaladBowlOptions: SaladBowlOptions = {
        visible: false,
        mixing: false,
        mixed: false,
        tomato: 0,
        cucumber: 0,
        carrot: 0,
    }

    type BasketOptions = {
        type: string,
        visible: boolean,
        count: number,
    }

    const initialBerryBasketOptions: BasketOptions = {
        type: "berry",
        visible: false,
        count: 5,
    }

    const initialCitrusBasketOptions: BasketOptions = {
        type: "citrus",
        visible: false,
        count: 5,
    }

    useEffect(() => {
        axios.get(taskTypeUrl).then(async (response) => {
            console.log(`Current task type: ${response.data as String}`)
            setCurrentTask(response.data as String)
            SaladBowlVisSetter((response.data as String)==="SALAD")
        }).catch(error => {
            infoTextSetter(getTaskErrMsg)
        })
    }, [])

    let [counterProducts, counterProductsSetter] = useState<Array<JsItemType>>([])
    let [fridgeProducts, fridgeProductsSetter] = useState<Array<JsItemType>>([])
    let [infoText, infoTextSetter] = useState<String>(startupMsg)
    let [spicesShelfVis, spicesShelfVisSetter] = useState<boolean>(true)
    let [blenderOptions, setBlenderOptions] = useState<BlenderOptions>(initialBlenderOptions);
    let [potOptions, setPotOptions] = useState<PotOptions>(initialPotOptions);
    let [saladBowlOptions, setSaladBowlOptions] = useState<SaladBowlOptions>(initialSaladBowlOptions);
    let [berryBasketOptions, setBerryBasketOptions] = useState<BasketOptions>(initialBerryBasketOptions);
    let [citrusBasketOptions, setCitrusBasketOptions] = useState<BasketOptions>(initialCitrusBasketOptions);
    let [buttonBlocker, setButtonBlocker] = useState<String>("");
    let [currentTask, setCurrentTask] = useState<String>("");

    function berryBasketVisSetter(value: boolean){
        setBerryBasketOptions(prevOptions => ({
                ...prevOptions,
                visible: value
            }))
    }

    function citrusBasketVisSetter(value: boolean){
        setCitrusBasketOptions(prevOptions => ({
            ...prevOptions,
            visible: value
        }))
    }

    function blenderVisSetter(value: boolean) {
        setBlenderOptions(prevOptions => ({
            ...prevOptions,
            visible: value
        }));
    }

    function potVisSetter(value: boolean) {
        setPotOptions(prevOptions => ({
            ...prevOptions,
            visible: value
        }));
    }

    function SaladBowlVisSetter(value: boolean) {
        setSaladBowlOptions(prevOptions => ({
            ...prevOptions,
            visible: value
        }));
    }

    let counterVisMap: { [key: string]: (arg: boolean) => void } = {
        "BERRY_BASKET": berryBasketVisSetter,
        "CITRUS_BASKET": citrusBasketVisSetter,
        "BLENDER": blenderVisSetter,
        "POT": potVisSetter
    }

    function showOnCounter(arg: string | null) {
        console.log("showOnCounter", arg)
        if (!arg) {
            return
        }
        if (arg in counterVisMap) {
            counterVisMap[arg](true)
        } else {
            removeFromFridge(arg)
            counterProductsSetter((prevState) => [
                ...prevState,
                JsItemType[arg as keyof typeof JsItemType],
            ]);
        }
    }

    function putInPot(arg: string | null) {
        if (arg == null)
            return
        removeFromCounter(arg)
        let potMap: { [key: string]: () => void } = {
            "PEPPER": () => {
                setPotOptions(prevOptions => ({
                    ...prevOptions,
                    pepper: true,
                    soupHue: 57,  // Dark green
                }));
                setTimeout(() => {
                    setPotOptions(prevOptions => ({
                        ...prevOptions,
                        pepper: false
                    }));
                }, 750);
            },
            "SALT": () => {
                setPotOptions(prevOptions => ({
                    ...prevOptions,
                    salt: true,
                    soupHue: 320,  // Pink
                }));
                setTimeout(() => {
                    setPotOptions(prevOptions => ({
                        ...prevOptions,
                        salt: false
                    }));
                }, 750);
            },
            "OREGANO": () => {
                setPotOptions(prevOptions => ({
                    ...prevOptions,
                    oregano: true,
                    soupHue: 113,  // Green
                }));
                setTimeout(() => {
                    setPotOptions(prevOptions => ({
                        ...prevOptions,
                        oregano: false
                    }));
                }, 750);
            },
            "CUT_TOMATO": () => {
                setPotOptions(prevOptions => ({
                    ...prevOptions,
                    tomato: prevOptions.tomato+1
                }));
            },
            "CUT_CARROT": () => {
                setPotOptions(prevOptions => ({
                    ...prevOptions,
                    carrot: prevOptions.carrot+1
                }));
            },
            "CUT_CUCUMBER": () => {
                setPotOptions(prevOptions => ({
                    ...prevOptions,
                    cucumber: prevOptions.cucumber+1
                }));
            },
        }
        potMap[arg]()
    }

    function simmer(arg: string | null) {
        setPotOptions(prevOptions => ({
            ...prevOptions,
            simmer: true
        }));
        setTimeout(() => {
            setPotOptions(prevOptions => ({
                ...prevOptions,
                soup: true,
                soupHue: 0,
            }));
        }, 500);
        setTimeout(() => {
            setPotOptions(prevOptions => ({
                ...prevOptions,
                simmer: false
            }));
        }, 1000);
    }

    function addToSalad(arg: string | null) {
        console.log("addToSalad()")
        console.log(arg)
        if (arg == null)
            return
        removeFromCounter(arg)
        let saladMap: { [key: string]: () => void } = {
            "CUT_TOMATO": () => {
                setSaladBowlOptions(prevOptions => ({
                    ...prevOptions,
                    tomato: prevOptions.tomato+1
                }));
            },
            "CUT_CUCUMBER": () => {
                setSaladBowlOptions(prevOptions => ({
                    ...prevOptions,
                    cucumber: prevOptions.cucumber+1
                }));
            },
            "CUT_CARROT": () => {
                setSaladBowlOptions(prevOptions => ({
                    ...prevOptions,
                    carrot: prevOptions.carrot+1
                }));
            },
        }
        saladMap[arg]()
    }

    function mixSalad(arg: string | null) {
        setSaladBowlOptions(prevOptions => ({
            ...prevOptions,
            mixing: true
        }));
        setTimeout(() => {
            setSaladBowlOptions(prevOptions => ({
                ...prevOptions,
                mixed: true
            }));
        }, 500);
        setTimeout(() => {
            setSaladBowlOptions(prevOptions => ({
                ...prevOptions,
                mixing: false
            }));
        }, 1000);
    }

    function blend(arg: string | null) {
        setBlenderOptions(prevOptions => ({
            ...prevOptions,
            shake: true
        }));
        setTimeout(() => {
            setBlenderOptions(prevOptions => ({
                ...prevOptions,
                full: true
            }));
        }, 500);
        setTimeout(() => {
            setBlenderOptions(prevOptions => ({
                ...prevOptions,
                shake: false
            }));
        }, 1000);
    }

    function addToBlender(arg: string | null) {
        if (arg == null)
            return
        removeFromCounter(arg)
        let blenderMap: { [key: string]: () => void } = {
            "CITRUS": () => {
                setCitrusBasketOptions(prevOptions => ({
                    ...prevOptions,
                    count: (prevOptions.count > 0) ? prevOptions.count-1 : 0
                    })
                )
                setBlenderOptions(prevOptions => ({
                    ...prevOptions,
                    citrus: prevOptions.citrus+1
                }));
            },
            "BERRY": () => {
                setBerryBasketOptions(prevOptions => ({
                        ...prevOptions,
                        count: (prevOptions.count > 0) ? prevOptions.count-1 : 0
                    })
                )
                setBlenderOptions(prevOptions => ({
                    ...prevOptions,
                    berry: prevOptions.berry+1
                }));
            },
        }
        blenderMap[arg]()
    }

    let cuttedMap: { [key: string]: string } = {
        "FRESH_TOMATO": "CUT_TOMATO",
        "FRESH_CUCUMBER": "CUT_CUCUMBER",
        "FRESH_CARROT": "CUT_CARROT",
    }

    function cutOnCounter(arg: string | null){
        console.log("cutOnCounter", arg)
        if (!arg){
            return
        }
        console.log("counter: ", counterProducts)
        if (cuttedMap.hasOwnProperty(arg)) {
            let cuttedArg = cuttedMap[arg];
            console.log("counter: ", counterProducts)
            console.log("filter: ", counterProducts.filter(item => JsItemType[item] !== arg))
            counterProductsSetter((prevState) => {
                const index = prevState.findIndex(item => JsItemType[item] === arg);
                if (index > -1) {
                    const newState = [...prevState];
                    newState.splice(index, 1, JsItemType[cuttedArg as keyof typeof JsItemType]);
                    return newState;
                }
                return prevState;
            });
        }
    }
    
    function removeFromCounter(arg: string | null) {
        console.log("removeFromCounter", arg)
        if (!arg){
            return
        }
        else {
            console.log("counter: ", counterProducts)
            console.log("filter: ", counterProducts.filter(item => JsItemType[item] !== arg))
            counterProductsSetter((prevState) => {
                const index = prevState.findIndex(item => JsItemType[item] === arg);
                if (index > -1) {
                    const newState = [...prevState];
                    newState.splice(index, 1);
                    return newState;
                }
                return prevState;
            });
        }
    }

    function removeFromFridge(arg: string | null) {
        console.log("removeFromFridge", arg)
        if (!arg)
            return
        console.log("counter: ", fridgeProducts)
        console.log("filter: ", fridgeProducts.filter(item => JsItemType[item] !== arg))
        fridgeProductsSetter((prevState) => {
            const index = prevState.findIndex(item => JsItemType[item] === arg);
            if (index > -1) {
                const newState = [...prevState];
                newState.splice(index, 1);
                return newState;
            }
            return prevState;
        });
    }


    let actionMap: { [key: string]: (arg: string | null) => void } = {
        "SHOW_ON_COUNTER": showOnCounter,
        "PUT_IN_POT": putInPot,
        "SIMMER": simmer,
        "ADD_TO_SALAD": addToSalad,
        "MIX_SALAD": mixSalad,
        "BLEND": blend,
        "ADD_TO_BLENDER": addToBlender,
        "REMOVE_FROM_COUNTER": removeFromCounter,
        "CUT_ON_COUNTER": cutOnCounter
    };

    function refill(){
        setBlenderOptions(initialBlenderOptions);
        setPotOptions(initialPotOptions);
        setSaladBowlOptions(initialSaladBowlOptions)
        SaladBowlVisSetter(currentTask==="SALAD")
        setCitrusBasketOptions(initialCitrusBasketOptions)
        setBerryBasketOptions(initialBerryBasketOptions)
        counterProductsSetter([])
        let items = Array<JsItemType>()
        axios.get(refillUrl).then(async (response) => {
            const receivedItems: Array<keyof typeof JsItemType> = response.data;
            items = receivedItems.map(item => JsItemType[item]);
            console.log("Refill GOT: " + items)
            fridgeProductsSetter(items)
            infoTextSetter(refillSuccMsg)
        }).catch(error => {
            infoTextSetter(refillErrMsg);
        })
    }


    function cook(url: string, dishName: string, emptyListMsg: string = ""){
        setBlenderOptions(initialBlenderOptions);
        setPotOptions(initialPotOptions);
        setSaladBowlOptions(initialSaladBowlOptions)
        SaladBowlVisSetter(currentTask==="SALAD")
        setCitrusBasketOptions(initialCitrusBasketOptions)
        setBerryBasketOptions(initialBerryBasketOptions)
        counterProductsSetter([])
        setButtonBlocker(dishName)

        const delay = (ms: number) => new Promise(res => setTimeout(res, ms));

        let actions = Array<JsAction>()
        axios.get(url).then(async (response) => {
            console.log("GOT: " + JSON.stringify(response.data, null, 2))
            actions = response.data as Array<JsAction>
            if (actions.length == 0){
                infoTextSetter(`${cookingNoActionsMsg} ${dishName}! ${emptyListMsg}`)
                setButtonBlocker("")
                return
            }
            infoTextSetter("Let's go!")
            await delay(cookingDelay);
            for (const action of actions) {
                console.log(infoTextActionMap[String(action.type)] + " " + (action.parameter ? infoTextItemMap[String(action.parameter)] : ""))
                infoTextSetter(infoTextActionMap[String(action.type)] + " " + (action.parameter ? infoTextItemMap[String(action.parameter)] : ""))
                actionMap[String(action.type)](
                    action.parameter ? String(action.parameter) : null
                )
                await delay(cookingDelay);
            }
            infoTextSetter(cookingDoneMsg)
        }).catch(error => {
            infoTextSetter(cookingErrMsg)
        }).finally(() => {
            setButtonBlocker("")
        })
    }

    function spice() {
        setButtonBlocker("spice")
        const delay = (ms: number) => new Promise(res => setTimeout(res, ms));

        let actions = Array<JsAction>()
        axios.get(spiceUrl).then(async (response) => {
            actions = response.data as Array<JsAction>
            console.log("GOT: " + actions)
            if (actions.length == 0){
                infoTextSetter(noSoupSpicingMsg)
                setButtonBlocker("")
                return
            }
            infoTextSetter(beforeSpicingMsg)
            await delay(cookingDelay);
            for (const action of actions) {
                console.log(infoTextActionMap[String(action.type)] + " " + (action.parameter ? infoTextItemMap[String(action.parameter)] : ""))
                infoTextSetter(infoTextActionMap[String(action.type)] + " " + (action.parameter ? infoTextItemMap[String(action.parameter)] : ""))
                actionMap[String(action.type)](
                    action.parameter ? String(action.parameter) : null
                )
                await delay(cookingDelay);
            }
            infoTextSetter(afterSpicingMsg)
            setPotOptions(prevOptions => ({
                ...prevOptions,
                spiced: true
            }));
        }).catch(error => {
            infoTextSetter(spicingErrMsg)
        }).finally(() => {
            setButtonBlocker("")
        })
    }

    function taste() {
        axios.get(tasteUrl).then(async (response) => {
            let isTasteGood = response.data as boolean
            console.log("isTasteGood: " + isTasteGood)
            if(isTasteGood){
                infoTextSetter(tasteGoodMsg)
            } else {
                infoTextSetter(tasteBadMsg)
            }
        }).catch(error => {
            infoTextSetter(tasteErrMsg)
        })
    }

    function shouldShow(buttonName: string){
        return !buttonBlocker;
    }

    return (
        <div className="App-main-container">
            <div className="App-buttons-container">
                <button className="App-button-base App-game-button-bottom-base App-button-back" onClick={() => {
                    gameStateSetter(GameState.START)
                }
                }></button>
            </div>
            {
                <div className={"App-kitchen-space"}>
                    <div className="App-info-container">
                        <div className="App-info-container-text font-link-base">{infoText}</div>
                    </div>
                    <div className={"App-kitchen-container"}>
                        <div className={"App-left-kitchen-container"}>
                            <SpicesShelf visible={spicesShelfVis}></SpicesShelf>
                            <Fridge products={fridgeProducts}></Fridge>
                        </div>
                        <div className={"App-center-kitchen-container"}>
                            <div className={"App-basket-kitchen-container"}>
                                <Basket basketOptions={berryBasketOptions}></Basket>
                                <SaladBowl saladBowlOptions={saladBowlOptions}></SaladBowl>
                                <Basket basketOptions={citrusBasketOptions}></Basket>
                            </div>
                            <Counter products={counterProducts}></Counter>
                        </div>
                        <div className={"App-right-kitchen-container"}>
                            <Blender blenderOptions={blenderOptions}></Blender>
                            <Pot potOptions={potOptions}></Pot>
                        </div>
                    </div>
                </div>
            }
            <div className="App-buttons-container">
                {(currentTask === "SOUP" || currentTask === "SALAD") && (
                    <button
                        className={"App-button-base App-button-action " + (shouldShow("refill") ? "" : "App-button-disable")}
                        onClick={() => refill()}>Refill
                    </button>
                )}
                {currentTask === "SOUP" && (
                    <>
                        <button
                            className={"App-button-base App-button-action " + (fridgeProducts.length > 0 && shouldShow(soupName) ? "" : "App-button-disable")}
                            onClick={() => cook(soupUrl, soupName, soupEmptyListMsg)}>Soup
                        </button>
                        <button
                            className={"App-button-base App-button-action " + (potOptions.soup && !potOptions.spiced && shouldShow("spice") ? "" : "App-button-disable")}
                            onClick={() => spice()}>Spice
                        </button>
                        <button
                            className={"App-button-base App-button-action " + (potOptions.soup && potOptions.spiced && shouldShow("taste") ? "" : "App-button-disable")}
                            onClick={() => taste()}>Taste
                        </button>
                    </>
                )}
                {currentTask === "SALAD" && (
                    <>
                        <button
                            className={"App-button-base App-button-action-wide " + (fridgeProducts.length > 0 && shouldShow(saladListName) ? "" : "App-button-disable")}
                            onClick={() => cook(saladListUrl, saladListName, saladEmptyListMsg)}>Salad list
                        </button>
                        <button
                            className={"App-button-base App-button-action-wide " + (fridgeProducts.length > 0 && shouldShow(saladSequenceName) ? "" : "App-button-disable")}
                            onClick={() => cook(saladSequenceUrl, saladSequenceName, saladEmptyListMsg)}>Salad seq.
                        </button>
                    </>
                )}
                {currentTask === "SMOOTHIE" && (
                    <button
                        className={"App-button-base App-button-action-wide " + (shouldShow(smoothieName) ? "" : "App-button-disable")}
                        onClick={() => cook(smoothieUrl, smoothieName)}>Smoothie
                    </button>
                )}
            </div>
        </div>
    );
}
