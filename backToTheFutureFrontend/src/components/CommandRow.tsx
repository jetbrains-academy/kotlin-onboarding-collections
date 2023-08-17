import {Command} from "../models/Command";

type CommandRowProps = {
    commands: Array<Command>
}

export default function CommandRowProps({commands}: CommandRowProps) {
    function getCommandClass(command: Command) {
        switch (command) {
            case Command.Clean: {
                return "App-command-container-clean"
            }
            case Command.Eat: {
                return "App-command-container-eat"
            }
            case Command.Play: {
                return "App-command-container-play"
            }
            case Command.Sleep: {
                return "App-command-container-sleep"
            }
        }
    }

    return(
        <div className={"App-commands-container-row "}>
            {
                commands.map((command) => (
                        <div className={"App-command-container " + getCommandClass(command)}></div>
                    )
                )
            }
        </div>
    )
}
