import {GameState} from "../GameScreen";
import Tamagotchi from "../Tamagotchi";
import {useEffect, useState} from "react";
import {State} from "../../models/TamagotchiState";
import {Command} from "../../models/Command";
import CommandRowProps from "../CommandRow";

type MainGameScreenProps = {
    gameStateSetter: (gs: GameState) => void
}

const MAX_SIZE = 16

export default function MainGameScreen({gameStateSetter}: MainGameScreenProps) {
    let [state, stateSetter] = useState<State>(State.Base)
    let [commands, commandsSetter] = useState<Array<Command>>([])

    let [eatScale, eatScaleSetter] = useState<number>(100);
    let [cleanScale, cleanScaleSetter] = useState<number>(100);
    let [playScale, playScaleSetter] = useState<number>(100);
    let [sleepScale, sleepScaleSetter] = useState<number>(100);

    let [isInProgress, isInProgressSetter] = useState<boolean>(false)

    const [damages, setDamages] = useState(0)

    function randomIntFromInterval(min: number, max: number) { // min and max included
        return Math.floor(Math.random() * (max - min + 1) + min)
    }

    function setCommands(newCommands: Array<Command>) {
        const clonedCommands: Array<Command> = []
        newCommands.forEach(command => clonedCommands.push(command))
        commandsSetter(clonedCommands)
    }

    function removeFromQueue() {
        const command = commands[0]
        setCommands(commands.slice(1, MAX_SIZE))
        performCommand(command)
    }

    function performCommand(command: Command) {
        switch (command) {
            case Command.Clean: {
                clean()
                return
            }
            case Command.Eat: {
                eat()
                return;
            }
            case Command.Play: {
                play()
                return;
            }
            case Command.Sleep: {
                sleep()
                return;
            }
        }
    }

    useEffect(() => {
        if (commands.length > 0 && !isInProgress) {
            removeFromQueue()
        }
    }, [isInProgress])

    useEffect(() => {
        const timer = setTimeout(() => {
            setDamages(damages + 1)
            // TODO: get from server
            const states = [State.Playing, State.Cleaning, State.Eating, State.Sleeping]

            const randomState = states[randomIntFromInterval(0, states.length - 1)]
            const randomDamage = randomIntFromInterval(10, 20)

            decreaseValues(randomState, randomDamage)
        }, 3000)
        return () => clearTimeout(timer)
    }, [damages])

    // useEffect(() => {
    //     // TODO: get from server
    //     const states = [State.Playing, State.Cleaning, State.Eating, State.Sleeping]
    //
    //     const randomState = states[randomIntFromInterval(0, states.length - 1)]
    //     const randomDamage = randomIntFromInterval(0, 5)
    //
    //     decreaseValues(randomState, randomDamage)
    // }, [])

    function putIntoQueue(command: Command) {
        if (commands.length >= MAX_SIZE) {
            alert("You cannot add more actions! The queue is full!")
        } else {
            // TODO: send a query to the server
            if (!isInProgress) {
                performCommand(command)
            } else {
                setCommands(commands.concat([command]))
            }
        }
    }

    function decreaseValue(value: number, amountToDecrease: number) {
        const newValue = value - amountToDecrease
        if (newValue < 0) {
            return 0
        }
        return newValue
    }

    function increaseValue(value: number, amountToIncrease: number) {
        const newValue = value + amountToIncrease
        if (newValue > 100) {
            return 100
        }
        return newValue
    }

    function increaseValues(state: State, amountToIncrease: number) {
        switch (state) {
            case State.Eating: {
                eatScaleSetter(increaseValue(eatScale, amountToIncrease))
                return
            }
            case State.Cleaning: {
                cleanScaleSetter(increaseValue(cleanScale, amountToIncrease))
                return;
            }
            case State.Sleeping: {
                sleepScaleSetter(increaseValue(sleepScale, amountToIncrease))
                return;
            }
            case State.Playing: {
                playScaleSetter(increaseValue(playScale, amountToIncrease))
            }
        }
    }

    function decreaseValues(state: State, amountToDecrease: number) {
        switch (state) {
            case State.Eating: {
                eatScaleSetter(decreaseValue(eatScale, amountToDecrease))
                return
            }
            case State.Cleaning: {
                cleanScaleSetter(decreaseValue(cleanScale, amountToDecrease))
                return;
            }
            case State.Sleeping: {
                sleepScaleSetter(decreaseValue(sleepScale, amountToDecrease))
                return;
            }
            case State.Playing: {
                playScaleSetter(decreaseValue(playScale, amountToDecrease))
            }
        }
    }

    function runAction(newState: State) {
        isInProgressSetter(true)
        if (newState == State.Base) {
            isInProgressSetter(false)
            return
        }
        stateSetter(newState)
        setTimeout(() => {
                // TODO: decrease values
                stateSetter(State.Base);
                increaseValues(newState, 30)
                isInProgressSetter(false)
            },
            3500);
    }

    function eat() {
        runAction(State.Eating)
    }

    function sleep() {
        runAction(State.Sleeping)
    }

    function clean() {
        runAction(State.Cleaning)
    }

    function play() {
        runAction(State.Playing)
    }

    return (
        <div className="App-main-container">
            <div className="App-buttons-container">
                <button className="App-button-base App-game-button-bottom-base App-button-back" onClick={() => {
                    gameStateSetter(GameState.START)
                }
                }></button>
            </div>
            <div className="App-functions-container">
                <button className={"App-button-base App-button-action App-button-eat "}
                        onClick={() => putIntoQueue(Command.Eat)}></button>
                <button className={"App-button-base App-button-action App-button-sleep "}
                        onClick={() => putIntoQueue(Command.Sleep)}></button>
                <button className={"App-button-base App-button-action App-button-clean "}
                        onClick={() => putIntoQueue(Command.Clean)}></button>
                <button className={"App-button-base App-button-action App-button-play "}
                        onClick={() => putIntoQueue(Command.Play)}></button>
            </div>
            <Tamagotchi clean={cleanScale} eat={eatScale} play={playScale} sleep={sleepScale}
                        state={state}></Tamagotchi>
            <div className={"App-commands-container "}>
                <CommandRowProps commands={commands.slice(0, 8)}></CommandRowProps>
                <CommandRowProps commands={commands.slice(8, MAX_SIZE)}></CommandRowProps>
            </div>
        </div>
    )
}
