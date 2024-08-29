import {GameState} from "../GameScreen";
import {useState} from "react";
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

    const refillUrl = "/functions/refill-fridge"
    const soupUrl = "/functions/tomato-soup"
    const spiceUrl = "/functions/soup-spices"
    const tasteUrl = "/functions/check-soup"

    type BlenderOptions = {
        visible: boolean,
        full: boolean,
        shake: boolean,
        berry: number,
        citrus: number,
    }

    const initialBlenderOptions: BlenderOptions = {
        visible: false,
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

    let [counterProducts, counterProductsSetter] = useState<Array<JsItemType>>([])
    let [fridgeProducts, fridgeProductsSetter] = useState<Array<JsItemType>>([])
    let [infoText, infoTextSetter] = useState<String>("Press \"Cook!\" button to start")
    let [spicesShelfVis, spicesShelfVisSetter] = useState<boolean>(false)
    let [blenderOptions, setBlenderOptions] = useState<BlenderOptions>(initialBlenderOptions);
    let [potOptions, setPotOptions] = useState<PotOptions>(initialPotOptions);
    let [saladBowlOptions, setSaladBowlOptions] = useState<SaladBowlOptions>(initialSaladBowlOptions);
    let [berryBasketOptions, setBerryBasketOptions] = useState<BasketOptions>(initialBerryBasketOptions);
    let [citrusBasketOptions, setCitrusBasketOptions] = useState<BasketOptions>(initialCitrusBasketOptions);
    let [buttonBlocker, setButtonBlocker] = useState<String>("");

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

    let infoTextActionMap: { [key: string]: string } = {
        "SHOW_ON_COUNTER": "<SHOW_ON_COUNTER_TEXT>",
        "PUT_IN_POT": "<PUT_IN_POT_TEXT>",
        "SIMMER": "<SIMMER_TEXT>",
        "ADD_TO_SALAD": "<ADD_TO_SALAD_TEXT>",
        "MIX_SALAD": "<MIX_SALAD_TEXT>",
        "BLEND": "<BLEND_TEXT>",
        "ADD_TO_BLENDER": "<ADD_TO_BLENDER_TEXT>",
        "REMOVE_FROM_COUNTER": "<REMOVE_FROM_COUNTER_TEXT>",
    }

    let infoTextItemMap: { [key: string]: string } = {
        "CITRUS_BASKET": "<CITRUS_BASKET_TEXT>",
        "BERRY_BASKET": "<BERRY_BASKET_TEXT>",
        "ROT_TOMATO": "<ROT_TOMATO_TEXT>",
        "FRESH_TOMATO": "<FRESH_TOMATO_TEXT>",
        "CUT_TOMATO": "<CUT_TOMATO_TEXT>",
        "ROT_CUCUMBER": "<ROT_CUCUMBER_TEXT>",
        "FRESH_CUCUMBER": "<FRESH_CUCUMBER_TEXT>",
        "CUT_CUCUMBER": "<CUT_CUCUMBER_TEXT>",
        "ROT_CARROT": "<ROT_CARROT_TEXT>",
        "FRESH_CARROT": "<FRESH_CARROT_TEXT>",
        "CUT_CARROT": "<CUT_CARROT_TEXT>",
        "BERRY": "<BERRY_TEXT>",
        "CITRUS": "<CITRUS_TEXT>",
        "SALT": "<SALT_TEXT>",
        "PEPPER": "<PEPPER_TEXT>",
        "OREGANO": "<OREGANO_TEXT>",
    }

    let counterVisMap: { [key: string]: (arg: boolean) => void } = {
        "BERRY_BASKET": berryBasketVisSetter,
        "???SALAD_BOWL???": SaladBowlVisSetter,
        "CITRUS_BASKET": citrusBasketVisSetter,
        "???SPICES_SHELF???": spicesShelfVisSetter,
        "BLENDER": blenderVisSetter,
        "POT": potVisSetter
    }

    function showOnCounter(arg: string | null) {
        console.log("showOnCounter", arg)
        if (!arg)
            return
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

    function equipKitchen(actions: Array<JsAction>) {
        console.log("equipKitchen()")
        let equipmentMap: { [key: string]: () => void } = {
            "ADD_TO_BLENDER": () => {
                blenderVisSetter(true)
            },
            "BLEND": () => {
                blenderVisSetter(true)
            },
            "MIX_SALAD": () => {
                SaladBowlVisSetter(true)
            },
            "ADD_TO_SALAD": () => {
                SaladBowlVisSetter(true)
            },
            "PUT_IN_POT": () => {
                potVisSetter(true)
            },
            "SIMMER": () => {
                potVisSetter(true)
            },
            "SALT": () => {
                spicesShelfVisSetter(true)
                setPotOptions(prevOptions => ({
                    ...prevOptions,
                    soup: true
                }));
            },
            "PEPPER": () => {
                spicesShelfVisSetter(true)
                setPotOptions(prevOptions => ({
                    ...prevOptions,
                    soup: true
                }));
            },
            "OREGANO": () => {
                spicesShelfVisSetter(true)
                setPotOptions(prevOptions => ({
                    ...prevOptions,
                    soup: true
                }));
            },
        }

        for (const action of actions) {
            // check is SALT PEPPER or OREGANO is shown on the counter
            if (String(action.type) == "SHOW_ON_COUNTER" || String(action.type) == "PUT_IN_POT") {
                if (String(action.parameter) in equipmentMap) {
                    equipmentMap[String(action.parameter)]()
                }
            } else {  // check is POT BLENDER or SALAD-bowl is used in cooking
                if (String(action.type) in equipmentMap) {
                    equipmentMap[String(action.type)]()
                }
            }
        }
    }

    function removeFromCounter(arg: string | null) {
        console.log("removeFromCounter", arg)
        if (!arg)
            return
        if (arg in counterVisMap) {
            counterVisMap[arg](false)
        } else {
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
        "REMOVE_FROM_COUNTER": removeFromCounter
    };

    function refill(){
        setBlenderOptions(initialBlenderOptions);
        setPotOptions(initialPotOptions);
        setSaladBowlOptions(initialSaladBowlOptions)
        setCitrusBasketOptions(initialCitrusBasketOptions)
        setBerryBasketOptions(initialBerryBasketOptions)
        spicesShelfVisSetter(false)
        counterProductsSetter([])
        fridgeProductsSetter([])
        infoTextSetter("Fridge refilling...")
        let items = Array<JsItemType>()
        axios.get(refillUrl).then(async (response) => {
            const receivedItems: Array<keyof typeof JsItemType> = response.data;
            items = receivedItems.map(item => JsItemType[item]);
            console.log("Refill GOT: " + items)
            fridgeProductsSetter(items)
            infoTextSetter("Fridge is refilled!")
        })
    }

    function cook() {
        setBlenderOptions(initialBlenderOptions);
        setPotOptions(initialPotOptions);
        setSaladBowlOptions(initialSaladBowlOptions)
        setCitrusBasketOptions(initialCitrusBasketOptions)
        setBerryBasketOptions(initialBerryBasketOptions)
        spicesShelfVisSetter(false)
        counterProductsSetter([])
        setButtonBlocker("cook")

        const delay = (ms: number) => new Promise(res => setTimeout(res, ms));

        let actions = Array<JsAction>()
        axios.get(soupUrl).then(async (response) => {
            actions = response.data as Array<JsAction>
            console.log("GOT: " + actions)
            if (actions.length == 0){
                infoTextSetter("Not enough vegetables to make soup!")
                setButtonBlocker("")
                return
            }
            infoTextSetter("Let's go!")
            equipKitchen(actions)
            console.log("equipKitchen() DONE")
            await delay(1500);
            for (const action of actions) {
                console.log(infoTextActionMap[String(action.type)] + " " + (action.parameter ? infoTextItemMap[String(action.parameter)] : ""))
                infoTextSetter(infoTextActionMap[String(action.type)] + " " + (action.parameter ? infoTextItemMap[String(action.parameter)] : ""))
                actionMap[String(action.type)](
                    action.parameter ? String(action.parameter) : null
                )
                await delay(1500);
            }
            infoTextSetter("Cooking is done!")
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
                infoTextSetter("You need to cook the soup first!")
                setButtonBlocker("")
                return
            }
            infoTextSetter("Let's add some spices!")
            equipKitchen(actions)
            console.log("equipKitchen() DONE")
            await delay(1500);
            for (const action of actions) {
                console.log(infoTextActionMap[String(action.type)] + " " + (action.parameter ? infoTextItemMap[String(action.parameter)] : ""))
                infoTextSetter(infoTextActionMap[String(action.type)] + " " + (action.parameter ? infoTextItemMap[String(action.parameter)] : ""))
                actionMap[String(action.type)](
                    action.parameter ? String(action.parameter) : null
                )
                await delay(1500);
            }
            infoTextSetter("Adding the spices is done!")
            setButtonBlocker("")
        })
    }

    function taste() {
        axios.get(tasteUrl).then(async (response) => {
            let isTasteGood = response.data as boolean
            console.log("isTasteGood: " + isTasteGood)
            if(isTasteGood){
                infoTextSetter("It tastes great! 🎉")
            } else {
                infoTextSetter("It tastes so bad... Try it again!")
            }
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
                <button
                    className={"App-button-base App-button-action " + (shouldShow("refill") ? "" : "App-button-disable")}
                    onClick={() => refill()}>Refill!
                </button>
                <button
                    className={"App-button-base App-button-action " + (fridgeProducts.length > 0 && shouldShow("cook") ? "" : "App-button-disable")}
                    onClick={() => cook()}>Soup!
                </button>
                <button
                    className={"App-button-base App-button-action " + (potOptions.soup && shouldShow("spice") ? "" : "App-button-disable")}
                    onClick={() => spice()}>Spice!
                </button>
                <button
                    className={"App-button-base App-button-action " + (potOptions.soup && shouldShow("taste") ? "" : "App-button-disable")}
                    onClick={() => taste()}>Taste!
                </button>

            </div>
        </div>
    );
}
