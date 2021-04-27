import {localhost, network} from "@/network/request";

export async function initPage() {
    return await network({
        url: '/initPage',
        method: 'get'
    }).then(res => {
        return res
    })
}