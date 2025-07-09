function loginApi(data) {
    return $axios({
        'url' : '/user/login',
        'method': 'post',
        data
    })
}

function sendApi(data) {
    return $axios({
        'url' : '/user/sendMsg',
        'method': 'post',
        data
    })
}
function sendMsgApi(data) {
    return $axios({
        'url' : '/user/sendMsg',
        'method': 'post',
        data
    })
}

function loginoutApi(data) {
    return $axios({
        'url' : '/user/loginout',
        'method': 'post',
        data
    })
}