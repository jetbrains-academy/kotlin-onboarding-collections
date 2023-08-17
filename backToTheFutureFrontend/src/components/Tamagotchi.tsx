import {State} from "../models/TamagotchiState";
import Scale from "./Scale";

type TamagotchiProps = {
    eat: number,
    sleep: number,
    play: number,
    clean: number,
    state: State
}

export default function Tamagotchi({eat, sleep, play, clean, state}: TamagotchiProps) {
    function getImageClass() {
        if (eat + sleep + play + clean == 0) {
            return "App-tamagotchi-image-alert"
        }
        if (state == State.Cleaning) {
            return "App-tamagotchi-image-cleaning"
        }
        if (state == State.Eating) {
            return "App-tamagotchi-image-eating"
        }
        if (state == State.Sleeping) {
            return "App-tamagotchi-image-sleeping"
        }
        if (state == State.Playing) {
            return "App-tamagotchi-image-playing"
        }
        return "App-tamagotchi-image-base"
    }

    return (
        <div className="App-tamagotchi-container">
            <div className={"App-tamagotchi-image-container"}>
                <div className={"App-tamagotchi-image " + getImageClass()}></div>
            </div>
            <div className={"App-scales-container "}>
                <div className={"App-scales-container-row App-scales-container-row-top "}>
                    <Scale text={"Eat"} value={eat}></Scale>
                    <Scale text={"Play"} value={play}></Scale>
                </div>
                <div className={"App-scales-container-row "}>
                    <Scale text={"Sleep"} value={sleep}></Scale>
                    <Scale text={"Clean"} value={clean}></Scale>
                </div>
            </div>
        </div>
    )
}