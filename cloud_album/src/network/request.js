import axios from "axios";
import store from "@/store/index"

export function network(config) {
    const instance = axios.create({
        baseURL: '/api/CloudAlbum',
        headers: {
            'userKey': store.state.userKey
        }
    });

    instance.interceptors.request.use(config => {
        return config
    }, error => {

    })

    instance.interceptors.response.use(res => {
        return res.data
    }, error => {

    })

    return instance(config);
}

export function localhost(config) {
    const instance = axios.create({
        baseURL: '/localhost/CloudAlbum_war_exploded',
        timeout: 5000,
        headers: {
            'userKey': store.state.userKey
        }
    });

    instance.interceptors.request.use(config => {
        return config
    }, error => {

    })

    instance.interceptors.response.use(res => {
        return res.data
    }, error => {

    })

    return instance(config);
}