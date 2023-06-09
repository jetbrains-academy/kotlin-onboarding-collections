import {PhotoInfo} from "../models/PhotoInfo";

type PhotoProps = {
    photoInfo: PhotoInfo
    wasAppliedFilter: boolean
}

export default function Photo({photoInfo, wasAppliedFilter}: PhotoProps) {
    const baseClasses = "App-photo-image "

    const baseClassesForBg = "App-photo-gray-bg "
    return (<div className={photoInfo.shouldBeHighlighted ? baseClassesForBg : wasAppliedFilter ? baseClassesForBg  + "App-photo-gray-bg-grayscale" : baseClassesForBg}>
        {
            <div className={photoInfo.imageClass == null ? baseClasses : baseClasses + photoInfo.imageClass}>
                <div className="App-photo-image"></div>
                <div className="App-photo-description">{photoInfo.description}</div>
            </div>
        }
    </div>)
}