import { Axios } from "./Axios"


export const getMapping = (url, options) =>{
    return Axios.get(url, options);
}

export const putMapping = (url, data, options) =>{
    return Axios.put(url, data, options);
}

export const postMapping = (url, data, options) =>{
    return Axios.post(url, data, options);
}

export const deleteMapping = (url, options) =>{
    return Axios.delete(url, options);
}
