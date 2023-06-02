import {PhotoInfo} from "../models/PhotoInfo";

type PhotoProps = {
    photoInfo: PhotoInfo
}

export default function Photo({photoInfo}: PhotoProps) {
    const baseClasses = "App-photo-image "
    return (<div className="App-photo-gray-bg">
        {
            <div className={photoInfo.imageClass == null ? baseClasses : baseClasses + photoInfo.imageClass}>
                <div className="App-photo-image"></div>
                <div className="App-photo-description">{photoInfo.description}</div>
            </div>
        }
    </div>)
}